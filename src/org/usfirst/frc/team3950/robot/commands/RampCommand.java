package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RampCommand extends Command {
	XboxController controller;

    public RampCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rampSubsystem);
    	controller = Robot.oi.xboxcontroller;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }


    protected void execute() {
    	/* Just saying this superior if statement does the EXACT SAME THING as the one below ;)
    	if (Robot.rampSubsystem.getRampStatus().toString().compareTo("kForward")==0) {
    		Robot.rampSubsystem.rampUp();
    	}
    	else {
    		Robot.rampSubsystem.rampDown();
    	}
    	*/
    	
    	if(controller.getBackButtonPressed()) {
    		if(RobotMap.rampSolenoid.get() == DoubleSolenoid.Value.kForward) {
		   		Robot.rampSubsystem.rampDown();
        	} else if(RobotMap.rampSolenoid.get() == DoubleSolenoid.Value.kReverse) {
        		Robot.rampSubsystem.rampUp();
        	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
