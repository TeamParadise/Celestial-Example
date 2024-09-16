// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.IntakeNote;
import frc.robot.commands.ShootNote;
import frc.robot.commands.drive.ArcadeDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Create the instances of all of our subsystems to be used in our robot code.
  // When we call our subsystems, they initialize and configure our motors.
  private final DriveSubsystem drive = new DriveSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();

  // Create our Xbox controller object for our controllers. Always use CommandXboxController for command-based projects.
  private final CommandXboxController driverController = new CommandXboxController(0);
  private final CommandXboxController coDriverController = new CommandXboxController(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the bindings of our driver and co-driver controller.
    configureBindings();
    // Configure the default commands of our subsystems.
    configureDefaultCommands();
  }

  /** We configure our controller bindings here. Controller bindings means we map a command or commands to a button or action on the controller. */
  private void configureBindings() {
    // This command will run the "ShootNote" command once the A button on the driver controller (main controller) is pressed.
    // "onTrue()" means the command will run once the button is pressed, and will continue running even if the button is let go of.
    driverController.a().onTrue(new ShootNote(intake, shooter)); // Since this uses "onTrue()", this command will run until the end condition is reached, which in this case, is a timeout.

    // This command will run the "IntakeNote" command as the A button on the co-driver controller (secondary controller) is held down.
    // "whileTrue()" means the command will run once the button is pressed, but the command will end if the button is let go of.
    coDriverController.b().whileTrue(new IntakeNote(intake, shooter)); // Since this uses "whileTrue()", this command will stop running once the B button on the co-driver controller is let go of.
  
  }

  /** This method will setup any default commands for our subsystems. Default commands are always running, unless another command interupts them. */
  private void configureDefaultCommands() {
    // Configure our default drive command, which is an ArcadeDrive command using the inputs from our main Xbox controller (known as the Driver Controller)
    drive.setDefaultCommand(new ArcadeDrive(
      drive,
      () -> driverController.getLeftY(), // Notice the "() ->"? This makes this a DoubleSupplier, compared to just a normal double.
      () -> driverController.getRightX() // Notice the "() ->"? This makes this a DoubleSupplier, compared to just a normal double.
    ));
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
