package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {
	XboxController controller = Robot.oi.xboxcontroller;
	boolean boxIn = false;
	

	public IntakeCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intakeSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!boxIn) {
			Robot.intakeSubsystem.Intake(-controller.getTriggerAxis(Hand.kRight) + controller.getTriggerAxis(Hand.kLeft));
			boxIn = Robot.intakeSubsystem.boxIn();
		} else {
			Robot.intakeSubsystem.Intake(controller.getTriggerAxis(Hand.kLeft));
		}
		if (controller.getTriggerAxis(Hand.kLeft) >= .5) {
			boxIn = false;
		}
	 	
    	if (Robot.intakeSubsystem.getSolenoidValue() == 0) {
    		Robot.intakeSubsystem.elevatorBrake();
    		}
    	else {
    		Robot.intakeSubsystem.undoBrake();
    	}
    }
    		
		
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
	 
