// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDrive extends Command {
  // Create basic subsystem objects to be called
  private final DriveSubsystem drive;
  
  // Create the speed and rotation providers that will be changed when we create this command
  private final DoubleSupplier speed;
  private final DoubleSupplier rotation;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(DriveSubsystem drive, DoubleSupplier speed, DoubleSupplier rotation) {
    // Set the "drive" of this command equal to the drive argument that was passed in. This makes the variable global (inside of this command).
    this.drive = drive;

    // Set the speed of this command equal to the speed that was passed in, making it global (in this command).
    this.speed = speed;
    // Set the rotation of this command equal to the rotation that was passed in, making it global (in this command).
    this.rotation = rotation;

    // Add the drive subsystem as a requirement of this command, prevents from running any other commands that require the drivetrain at the same time as this one.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Call the drive subsystem and set the speed and rotation
    drive.arcadeDrive(speed.getAsDouble(), rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Once this command ends, set the drive speed and rotation to zero.
    drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
