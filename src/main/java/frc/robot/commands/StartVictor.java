// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MotorSubysytem;

public class StartVictor extends Command {
  /** Creates a new StartVictor. */
  private final MotorSubysytem m_MotorSubysytem;
  public StartVictor(MotorSubysytem motorSubysytem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_MotorSubysytem = motorSubysytem;

    addRequirements(m_MotorSubysytem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_MotorSubysytem.setMotor_Victor(0.5);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_MotorSubysytem.setMotor_Victor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
