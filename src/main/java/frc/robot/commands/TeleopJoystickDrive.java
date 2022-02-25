// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;

/* To do list
Be able to drive with the joystick in the teleop period
*/

public class TeleopJoystickDrive extends CommandBase {
  /** Creates a new TeleopJoystickDrive. */
  public Drivetrain drivetrain;

  public TeleopJoystickDrive(Drivetrain _subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = _subsystem;
    addRequirements(_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
