package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.PIDSourceDistance;
import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderNavXAutoCommand extends Command implements PIDOutput{
	ScaleAutoCommand x = new ScaleAutoCommand();
	DriveStraightCommand y = new DriveStraightCommand();
	PIDSourceDistance source = new PIDSourceDistance();

    public EncoderNavXAutoCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	x.initialize();
    	y.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrainSubsystem.Drive(y.output, x.output);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	x.isFinished();
    	y.isFinished();
        return isCanceled();
    }

    // Called once after isFinished returns true
    protected void end() {
    	x.end();
    	y.end();
    	Robot.drivetrainSubsystem.Drive(0, 0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	x.interrupted();
    	y.interrupted();
    	Robot.drivetrainSubsystem.Drive(0, 0);
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		System.out.print(Thread.currentThread().getStackTrace()[2].getMethodName());
		
	}
}
