// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.FaultID;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class MotorSubysytem extends SubsystemBase {
  /** Creates a new MotorSubysytem. */
  private final WPI_VictorSPX motor_victor;
  private final CANSparkMax motor_sparkMax;
  private final TalonFX motor_krakan;

  private final RelativeEncoder sparkMax_encoder;

  public MotorSubysytem() {

    motor_victor = new WPI_VictorSPX(MotorConstants.motor_victor_ID);
    motor_sparkMax = new CANSparkMax(MotorConstants.motor_sparkMax_ID, MotorType.kBrushless);
    motor_krakan = new TalonFX(MotorConstants.motor_krakan_ID);

    sparkMax_encoder = motor_sparkMax.getEncoder();

    motor_sparkMax.restoreFactoryDefaults();

    motor_victor.setInverted(MotorConstants.motor_victorReverse);//設定馬達正反轉
    motor_krakan.setInverted(MotorConstants.motor_krakanReverse);
    motor_sparkMax.setInverted(MotorConstants.motor_SparkMaxReverse);

    motor_victor.setNeutralMode(NeutralMode.Brake);//設定馬達煞車
    motor_krakan.setNeutralMode(NeutralModeValue.Brake);
    motor_sparkMax.setIdleMode(IdleMode.kBrake);

    motor_sparkMax.burnFlash();

  }

  public double getSparkMaxVelocity() {
    return sparkMax_encoder.getVelocity();//RPM(Revolutions per Minute)
  }

  public double getKrakanVelocity() {
    return motor_krakan.getVelocity().getValue();//RPS(Rotations per second)
  }

  public double getSparkMaxPosition() {
    return sparkMax_encoder.getPosition();//relative Postion轉幾圈
  }

  public double getKrakanPosition() {
    return motor_krakan.getPosition().getValue();//relative Position轉幾圈
  }

  public boolean isJam_sparkMax() {
    return !motor_sparkMax.getFault(FaultID.kOvercurrent);//馬達是否卡住
  }

  public boolean isJam_Krakan() {
    return !motor_krakan.getFault_StatorCurrLimit().getValue();//馬達是否卡住
  }

  public double getTemp_sparkMax() {
    return motor_sparkMax.getMotorTemperature();//馬達溫度
  }

  public double getTemp_krakan() {
    return motor_krakan.getDeviceTemp().getValueAsDouble();//馬達溫度
  }

  public void setMotor_Krakan(double value) {
    motor_krakan.set(value);//0 ~ 1
  }

  public void setMotor_Sparkmax(double value) {
    motor_sparkMax.set(value);//0 ~ 1
  }

  public void setMotor_Victor(double value) {
    motor_victor.set(value);//0 ~ 1
  }

  public void setMotorVoltage_Krakan(double voltage) {
    motor_krakan.setVoltage(voltage);//0 ~ 12
  }

  public void setMotorVoltage_Sparkmax(double voltage) {
    motor_sparkMax.setVoltage(voltage);//0 ~ 12
  }

  public void setMotorVoltage_Victor(double voltage) {
    motor_victor.setVoltage(voltage);//0 ~ 12
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Motor/SparkMaxTemp", getTemp_sparkMax());
    SmartDashboard.putBoolean("Motor/KrakanIsJam", isJam_Krakan());
  }
}
