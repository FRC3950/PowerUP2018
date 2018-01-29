package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorCommand extends Command {
	XboxController controller = Robot.oi.xboxcontroller;
	boolean bottom = false;
	boolean top = false;

    public ElevatorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (bottom) {
    		if (controller.getY(Hand.kLeft) >= 0) {
    			Robot.elevatorSubsystem.elevatorControl(controller.getY(Hand.kLeft));
    		}
    	}
    	else if (top) {
    		if (controller.getY(Hand.kLeft) <= 0) {
    			Robot.elevatorSubsystem.elevatorControl(controller.getY(Hand.kLeft));
    		}
    	} else {
    		Robot.elevatorSubsystem.elevatorControl(controller.getY(Hand.kLeft));
    	}
    	
    		
    		// if (y<0) {
    		// elevatorMotor.set(0);
        // if (bottom) {
        	// if (y>0) {
        		// elevatorMotor.set(1);
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
