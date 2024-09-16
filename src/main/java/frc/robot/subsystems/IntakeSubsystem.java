// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  // Create basic motor objects
  private final CANSparkMax bottomIntake = new CANSparkMax(5, MotorType.kBrushless);
  private final CANSparkMax topIntake = new CANSparkMax(6, MotorType.kBrushless);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    // Reset all motor controllers to factory defaults to ensure consistent settings
    bottomIntake.restoreFactoryDefaults();
    topIntake.restoreFactoryDefaults();

    // Set current limits to prevent brownouts and to protect motors
    bottomIntake.setSmartCurrentLimit(60);
    topIntake.setSmartCurrentLimit(60);

    // Set idle mode to coast (things that interact with game pieces normally use coast mode to prevent damage to the game pieces)
    bottomIntake.setIdleMode(IdleMode.kCoast);
    topIntake.setIdleMode(IdleMode.kBrake);

    // Tell the top intake motor to follow the bottom intake motor
    topIntake.follow(bottomIntake);

    // Burn these settings to the motor to keep them persistent (even if the code doesn't config the motors properly)
    bottomIntake.burnFlash();
    topIntake.burnFlash();
  }

  /** Set the speed of both intake motors. */
  public void setSpeed(double intakeSpeed) {
    // Sets the speed of the bottom motor (since the top motor follows, we don't need to set the top motor)
    bottomIntake.set(intakeSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
