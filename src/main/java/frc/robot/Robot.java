// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.AutonomousHopperLower;
import frc.robot.commands.autonomous.AutonomousHopperUpper;
import frc.robot.constants.Constants;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private ParallelCommandGroup Autonomous1_DriveAndIntakeAndHopper;
    private SequentialCommandGroup Autonomous1_DriveThenHopper;
    // private Command Autonomous1_Shooter;

    // private ParallelCommandGroup Autonomous2;
    // private ParallelCommandGroup Autonomous3;

    private RobotContainer timeBotsRobotContainer;

    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
        timeBotsRobotContainer = new RobotContainer();
        PowerDistribution pdp = new PowerDistribution();

    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and
     * test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled
        // commands, running already-scheduled commands, removing finished or
        // interrupted commands,
        // and running subsystem periodic() methods. This must be called from the
        // robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
        timeBotsRobotContainer.checkButtons();
    }

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
        timeBotsRobotContainer.climber.setLeftExtendingArmCurrent(0);
        timeBotsRobotContainer.climber.setRightExtendingArmCurrent(0);
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void disabledPeriodic() {
        timeBotsRobotContainer.climber.setLeftExtendingArmCurrent(0);
        timeBotsRobotContainer.climber.setRightExtendingArmCurrent(0);
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
        CommandScheduler.getInstance().cancelAll();

        // Autonomous1_DriveAndIntakeAndHopper = new
        // ParallelCommandGroup(timeBotsRobotContainer.getAutonomousCommand(),
        // timeBotsRobotContainer.autonomousIntakeCargo,
        // timeBotsRobotContainer.autonomousHopperLowerCargo);

        // Autonomous1_Shooter = timeBotsRobotContainer.autonomousShootCargo;

        Command autoDrive = timeBotsRobotContainer.getAutoCommand();

        // schedule the autonomous command (example)
        if (autoDrive != null) {

            Autonomous1_DriveThenHopper = new SequentialCommandGroup(
                    autoDrive,
                    new AutonomousHopperUpper(timeBotsRobotContainer.hopper,
                            Constants.HopperConstants.HOPPER_FIRE_SPEED)
                                    .alongWith(new AutonomousHopperLower(timeBotsRobotContainer.hopper,
                                            Constants.HopperConstants.HOPPER_FIRE_SPEED)));

            Autonomous1_DriveThenHopper.schedule();
            timeBotsRobotContainer.autonomousShootCargo.schedule();
            timeBotsRobotContainer.autonomousIntakeCargo.schedule();
            // timeBotsRobotContainer.autonomousHopperLowerCargo.schedule();
        }
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        // ShuffleboardTab misc = Shuffleboard.getTab("Misc");
        // PowerDistribution pdp = new PowerDistribution();

        // misc.add("PDP",
        // pdp).withWidget(BuiltInWidgets.kPowerDistribution).withPosition(0,
        // 0)
        // .withSize(6,
        // 3);

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (Autonomous1_DriveThenHopper != null) {
            CommandScheduler.getInstance().cancelAll();

            // Autonomous1_DriveThenHopper.cancel();
            // timeBotsRobotContainer.autonomousShootCargo.cancel();
            // timeBotsRobotContainer.autonomousIntakeCargo.cancel();
            // timeBotsRobotContainer.autonomousHopperLowerCargo.cancel();
        }
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }

    /** This function is called once when the robot is first started up. */
    @Override
    public void simulationInit() {
    }

    /** This function is called periodically whilst in simulation. */
    @Override
    public void simulationPeriodic() {
    }
}
