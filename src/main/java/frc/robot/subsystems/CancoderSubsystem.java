// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CancoderConstants;

public class CancoderSubsystem extends SubsystemBase {
  /** Creates a new CancoderSubsystem. */
  private final CANcoder absolutedEncoder;

  private final CANcoderConfiguration absolutedEncoderConfig;
  public CancoderSubsystem() {
    absolutedEncoder = new CANcoder(CancoderConstants.absolutedEncoder_ID);
    absolutedEncoderConfig = new CANcoderConfiguration();

    absolutedEncoderConfig.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Signed_PlusMinusHalf;//範圍: -0.5 ~ 0.5
    // absolutedEncoderConfig.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Unsigned_0To1;//範圍: 0 ~ 1
    absolutedEncoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;//逆時鐘為正
    // absolutedEncoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;//順時針為正
    absolutedEncoderConfig.MagnetSensor.MagnetOffset = CancoderConstants.absolutedEncoderOffset;//誤差值
  }

  public double getAbsolutedPosition() {
    return absolutedEncoder.getAbsolutePosition().getValueAsDouble();//讀輸出軸與距離原點的距離，範圍在上方被設定為-0.5 ~0.5
  }

  public double getAbsolutedAngle() {
    return absolutedEncoder.getAbsolutePosition().getValueAsDouble()*360;//-180 ~ 180
  }

  public double getVelocity() {
    return absolutedEncoder.getVelocity().getValueAsDouble();//讀輸出軸得RPM
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Cancoder/AbsolutedPosition", getAbsolutedPosition());
    SmartDashboard.putNumber("Cancoder/Velocity", getVelocity());
    SmartDashboard.putNumber("Cancoder/AbsolutedAngle", getAbsolutedAngle());
  }
}
