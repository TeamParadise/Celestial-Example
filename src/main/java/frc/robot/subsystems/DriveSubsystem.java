// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  // Create basic motor objects
  private final CANSparkMax leftLeader = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax leftFollower = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax rightLeader = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax rightFollower = new CANSparkMax(4, MotorType.kBrushless);

  // Create a DifferentialDrive object that we will call to actually control the drivetrain, but don't actually create a new object yet (we want to configure our motors first!)
  private final DifferentialDrive drivetrain;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    // Reset all motor controllers to factory defaults, ensures only settings here are modified
    leftLeader.restoreFactoryDefaults();
    leftFollower.restoreFactoryDefaults();
    rightLeader.restoreFactoryDefaults();
    rightFollower.restoreFactoryDefaults();

    // Set current limits to prevent brownouts and to protect motors
    leftLeader.setSmartCurrentLimit(60);
    leftFollower.setSmartCurrentLimit(60);
    rightLeader.setSmartCurrentLimit(60);
    rightFollower.setSmartCurrentLimit(60);

    // Set idle mode to brake (drivetrain should nearly always be set to brake mode, in order to prevent rolling when driving)
    leftLeader.setIdleMode(IdleMode.kBrake);
    leftFollower.setIdleMode(IdleMode.kBrake);
    rightLeader.setIdleMode(IdleMode.kBrake);
    rightFollower.setIdleMode(IdleMode.kBrake);

    // Invert right side of the drivetrain
    rightLeader.setInverted(true);
    rightFollower.setInverted(true);

    // Set the follower motors to follow their respective leader motors
    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    // Burn these settings to the motor to keep them persistent (even if the code doesn't config the motors properly)
    leftLeader.burnFlash();
    leftFollower.burnFlash();
    rightLeader.burnFlash();
    rightFollower.burnFlash();

    // Now that our motors are configured, we can initialize our DifferentialDrive object
    drivetrain = new DifferentialDrive(leftLeader, rightLeader);
  }

  /** Runs the drivetrain in tank drive mode (controlling the left speed and the right speed). */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    // Pass in the values into the DifferentialDrive's tankDrive method.
    drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  /** Runs the drivetrain in arcade drive mdoe (controlling the forwards and backwards speed and rotation, typically used for game controllers). */
  public void arcadeDrive(double speed, double rotation) {
    // Pass in the values into the DifferentialDrive's arcadeDrive method.
    drivetrain.arcadeDrive(speed, rotation);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
