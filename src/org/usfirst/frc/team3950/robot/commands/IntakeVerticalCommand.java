package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeVerticalCommand extends Command {

	XboxController controller = new XboxController(1);
	double speed = 0;
	boolean start; //true if starting from top, false if starting from bottom
	boolean finish = false;
	double tolerance = 0.07;
	
    public IntakeVerticalCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeVerticalSubsystem);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//System.out.print("In intake vertical init");
    	/*
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
    	*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = -controller.getY(Hand.kRight);
    	
    	if(speed <= tolerance && speed >= -tolerance)
    		speed = 0;
    	/*
    	if (Robot.intakeSubsystem.atTop()) {
    		Robot.intakeSubsystem.intakeVertical(controller.getY(Hand.kRight));
    		start = true;
    	} else if (Robot.intakeSubsystem.atBottom()) {
    		Robot.intakeSubsystem.intakeVertical(-controller.getY(Hand.kRight));
    		start = false;
    	} else {
    		Robot.intakeSubsystem.intakeVertical(controller.getY(Hand.kRight));
    		start = true;
    	}

    	if (start) {
    		if (Robot.intakeSubsystem.atBottom()) {
    			finish = true;
    		}
    	} else {
    		if (Robot.intakeSubsystem.atTop()) {
    			finish = true; 
    		}
    	}
    	*/
    	
    	//System.out.println("Intake Vertical Speed is " + speed);
    	
    	
    	if((Robot.intakeVerticalSubsystem.atBottom()) && (speed < 0)) {
    		Robot.intakeVerticalSubsystem.intakeVertical(0);
    	} else if (Robot.intakeVerticalSubsystem.atTop() && speed > 0) {
    		Robot.intakeVerticalSubsystem.intakeVertical(0.1);
    	} else {
    		Robot.intakeVerticalSubsystem.intakeVertical(-controller.getY(Hand.kRight));
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeVerticalSubsystem.intakeVertical(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeVerticalSubsystem.intakeVertical(0);
    }
}
