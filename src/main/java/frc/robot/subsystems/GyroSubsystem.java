// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GyroSubsystem extends SubsystemBase {
  /** Creates a new GyroSubsystem. */
  private final Pigeon2 gyro;
  private final Pigeon2Configuration gyroConfig;//gyro的設定

  public GyroSubsystem() {
    gyro = new Pigeon2(0);
    gyroConfig = new Pigeon2Configuration();
    gyroConfig.MountPose.MountPoseYaw = 0;//設定旋轉的誤差值
    gyroConfig.MountPose.MountPosePitch = 0;//設定前後傾的誤差值
    gyroConfig.MountPose.MountPoseRoll = 0;//設定左右傾的誤差值
    gyro.getConfigurator().apply(gyroConfig);

    // Shuffleboard.getTab("Gyro Tab").add(gyro);
  }

  public double getAngle() {
    return gyro.getAngle();//讀機器人旋轉的角度(relative range：-360 ~ 360)
  }

  public double getAngle_Yaw(){
    return gyro.getYaw().getValueAsDouble();//讀機器人旋轉的角度(relative range：-368640 ~ 368640)
  }

  public double getPitch() {
    return gyro.getPitch().getValueAsDouble();//讀機器人前後傾的角度
  }

  public double getRoll() {
    return gyro.getRoll().getValueAsDouble();//讀機器人左右傾的角度
  }

  public double getYaw() {
    return gyro.getYaw().getValueAsDouble();//讀機器人旋轉的角度(relative)
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Gyro/Angle", getAngle());
    SmartDashboard.putNumber("Gyro/Pitch", getPitch());
    SmartDashboard.putNumber("Gyro/Roll", getRoll());
    SmartDashboard.putNumber("Gyro/Yaw", getYaw());

  }
}
