// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DetectorConstants;

public class DetectorSubsystem extends SubsystemBase {
  /** Creates a new DetecterSubsystem. */
  private final DigitalInput limitSwitch;
  private final DigitalInput irSensor;

  public DetectorSubsystem() {
    irSensor = new DigitalInput(DetectorConstants.limitSwitch_ID);
    limitSwitch = new DigitalInput(DetectorConstants.irSensor_ID);
  }

  public boolean getLimitSwitch() {
    return !limitSwitch.get();//是否碰到極限開關
  }

  public boolean getIrSensor() {
    return !irSensor.get();//紅外線是否感測到
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Detector/GetLimitSwitch", getLimitSwitch());
    SmartDashboard.putBoolean("Detector/getIrSensor", getIrSensor());
  }
}
