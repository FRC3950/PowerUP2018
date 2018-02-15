package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeVerticalCommand extends Command {

	double speed = .5;
	boolean start; //true if starting from top, false if starting from bottom
	boolean finish = false;
	
    public IntakeVerticalCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.intakeSubsystem.atTop()) {
    		Robot.intakeSubsystem.intakeVertical(speed);
    		start = true;
    	} else if (Robot.intakeSubsystem.atBottom()) {
    		Robot.intakeSubsystem.intakeVertical(-speed);
    		start = false;
    	} else {
    		Robot.intakeSubsystem.intakeVertical(speed);
    		start = true;
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	if (start) {
    		if (Robot.intakeSubsystem.atBottom()) {
    			finish = true;
    		}
    	} else {
    		if (Robot.intakeSubsystem.atTop()) {
    			finish = true; 
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (finish);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSubsystem.intakeVertical(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSubsystem.intakeVertical(0);
    }
}
