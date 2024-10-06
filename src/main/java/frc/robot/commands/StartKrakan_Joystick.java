// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MotorSubysytem;

public class StartKrakan_Joystick extends Command {
  /** Creates a new StartKrakan_Joystick. */
  private final MotorSubysytem m_MotorSubysytem;
  private final DoubleSupplier valueFunc;

  private double value;
  public StartKrakan_Joystick(MotorSubysytem motorSubysytem, DoubleSupplier valueFunc) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_MotorSubysytem = motorSubysytem;
    this.valueFunc = valueFunc;

    addRequirements(m_MotorSubysytem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    value = valueFunc.getAsDouble();
    m_MotorSubysytem.setMotor_Krakan(value);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_MotorSubysytem.setMotor_Krakan(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
