// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  // Create basic motor objects
  private final CANSparkMax bottomFlywheel = new CANSparkMax(7, MotorType.kBrushless);
  private final CANSparkMax topFlywheel = new CANSparkMax(8, MotorType.kBrushless);

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    // Reset all motor controllers to factory defaults to ensure consistent settings
    bottomFlywheel.restoreFactoryDefaults();
    topFlywheel.restoreFactoryDefaults();

    // Set current limits to prevent brownouts and to protect motors
    bottomFlywheel.setSmartCurrentLimit(60);
    topFlywheel.setSmartCurrentLimit(60);

    // Invert the flywheels
    bottomFlywheel.setInverted(true);
    topFlywheel.setInverted(true);

    // Set idle mode to coast (things that interact with game pieces normally use coast mode to prevent damage to the game pieces)
    bottomFlywheel.setIdleMode(IdleMode.kCoast);
    topFlywheel.setIdleMode(IdleMode.kBrake);

    // Burn these settings to the motor to keep them persistent (even if the code doesn't config the motors properly)
    bottomFlywheel.burnFlash();
    topFlywheel.burnFlash();
  }

  /** Set the speed of both shooter motors. */
  public void setSpeed(double shooterSpeed) {
    // Set the speed of the top and bottom flywheel motors.
    bottomFlywheel.set(shooterSpeed);
    topFlywheel.set(shooterSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
