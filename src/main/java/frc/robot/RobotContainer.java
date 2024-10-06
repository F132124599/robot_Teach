// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.StartKrakan;
import frc.robot.commands.StartKrakan_Joystick;
import frc.robot.commands.StartSparkMax;
import frc.robot.commands.StartVictor;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.subsystems.CancoderSubsystem;
import frc.robot.subsystems.DetectorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.subsystems.MotorSubysytem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final MotorSubysytem m_MotorSubysytem = new MotorSubysytem();
  private final CancoderSubsystem m_CancoderSubsystem = new CancoderSubsystem();
  private final BaseSubsystem m_BaseSubsystem = new BaseSubsystem();
  private final DetectorSubsystem m_DetectorSubsystem = new DetectorSubsystem();
  private final GyroSubsystem m_GyroSubsystem = new GyroSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController joystick = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    DoubleSupplier motorValue = ()-> joystick.getRawAxis(0);

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    joystick.a().whileTrue(new StartKrakan(m_MotorSubysytem));
    joystick.b().whileTrue(new StartSparkMax(m_MotorSubysytem));
    joystick.x().onTrue(new StartVictor(m_MotorSubysytem));

    joystick.y().onTrue(new StartKrakan_Joystick(m_MotorSubysytem, motorValue));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
