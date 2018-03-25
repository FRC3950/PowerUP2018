package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NoLimitSwIntakeCommand extends Command {
	
	XboxController controller = new XboxController(1);
	double speed = 0;
	boolean start; //true if starting from top, false if starting from bottom
	boolean finish = false;
	double tolerance = 0.07;

    public NoLimitSwIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeVerticalSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = controller.getY(Hand.kRight);
    	
    	if(speed <= tolerance && speed >= -tolerance)
    		speed = 0;
    	
    	Robot.intakeVerticalSubsystem.intakeVertical(speed);
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
