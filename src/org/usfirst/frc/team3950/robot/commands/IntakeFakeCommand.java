package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeFakeCommand extends Command {
	Joystick controller = Robot.oi.driveStick;
	boolean boxIn = false;
	double trigger;
	

	public IntakeFakeCommand(double input) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intakeSubsystem);
		trigger = input;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//trigger = -controller.getTriggerAxis(Hand.kRight) + controller.getTriggerAxis(Hand.kLeft);
		/*
		if (!boxIn) {
			Robot.intakeSubsystem.Intake(-controller.getTriggerAxis(Hand.kRight) + controller.getTriggerAxis(Hand.kLeft));
			boxIn = Robot.intakeSubsystem.boxIn();
		} else { 
			Robot.intakeSubsystem.Intake(controller.getTriggerAxis(Hand.kLeft));
		}
		if (controller.getTriggerAxis(Hand.kLeft) >= .5) {
			boxIn = false;
		}
		*/
		//create limit switch if statement for intake after learning function and heat of volcano
		
		if(!boxIn && Robot.intakeSubsystem.boxIn()) {
			Robot.intakeSubsystem.Intake(0);
		} else if (Robot.intakeSubsystem.boxIn() && trigger >= 0) {
			return;
		} else {
			Robot.intakeSubsystem.Intake(trigger);
		}
	

		boxIn = Robot.intakeSubsystem.boxIn();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
	}
	 
