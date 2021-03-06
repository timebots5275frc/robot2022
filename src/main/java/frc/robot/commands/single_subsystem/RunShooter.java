// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.single_subsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.constants.Constants;
import frc.robot.subsystems.shooter.Shooter;

public class RunShooter extends CommandBase {

    private Shooter shooter_subsystem;
    private double shoterSpeedRPM;
    private Joystick joyStick;

    /** Creates a new RunShooter. */
    public RunShooter(Shooter _shooter_subsystem, double _shoterSpeedRPM, Joystick _joyStick) {
        this.shooter_subsystem = _shooter_subsystem;
        this.shoterSpeedRPM = _shoterSpeedRPM;
        this.joyStick = _joyStick;
        addRequirements(shooter_subsystem);

        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double throttle = -joyStick.getThrottle() * (this.shoterSpeedRPM * .1); // between 0 and 1 = 0% and 100%

        shooter_subsystem.setShooterMotorSpeed(this.shoterSpeedRPM + throttle);

        SmartDashboard.putNumber("SetShooterSpeed", this.shoterSpeedRPM + throttle);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooter_subsystem.setShooterMotorSpeed(Constants.ShooterConstants.SHOOTER_DEFAULT_RPM);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
