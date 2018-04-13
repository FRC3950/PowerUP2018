package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BangBangElevatorCommand extends Command {

	double elHeight = 0;
	double offset = 2.5;
	boolean finished = false;
	
    public BangBangElevatorCommand(double height) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    	elHeight = height;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("in init");
    	finished = false;
    	Robot.elevatorSubsystem.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.elevatorSubsystem.bottomGetter()) {
    		Robot.elevatorSubsystem.resetEncoder();
    	}
    	  
    	if(Robot.elevatorSubsystem.topGetter()) {
    		Robot.elevatorSubsystem.elevatorControl(0);
    	}
    	
    	if(Robot.elevatorSubsystem.getElevatorHeight() < (elHeight - offset)) {
    		System.out.println("it is below");
    		Robot.elevatorSubsystem.undoBrake();
    		Robot.elevatorSubsystem.elevatorControl(0.5);
    	} else if (Robot.elevatorSubsystem.getElevatorHeight() > (elHeight + offset)) {
    		System.out.println("it is above");
    		Robot.elevatorSubsystem.undoBrake();
    		Robot.elevatorSubsystem.elevatorControl(-.5);
    	} else {
    		System.out.println("it is at height");
    		Robot.elevatorSubsystem.elevatorControl(0);
    		Robot.elevatorSubsystem.elevatorBrake();
    		finished = true;
    	}
    	System.out.println("Encoder elevator counts: " + Robot.elevatorSubsystem.getEncoder());
    	System.out.println("Encoder elevator height: " + Robot.elevatorSubsystem.getElevatorHeight());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
