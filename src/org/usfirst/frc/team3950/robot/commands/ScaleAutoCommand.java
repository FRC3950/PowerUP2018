package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Logger;
import org.usfirst.frc.team3950.robot.PIDSourceDistance;
import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ScaleAutoCommand extends Command implements PIDOutput{
	
	PIDController pid;
	PIDSourceDistance source;
	
	//constants
	double maxSpeed = 0.75;
	//setpoint is in feet
	double setpoint = 6f;
	
	
//	double P = SmartDashboard.getNumber("P (distance)", 0.17);
//	double I = SmartDashboard.getNumber("I (distance)", 0.001);
//	double D = SmartDashboard.getNumber("D (distance)", 0.1);
//	double F = SmartDashboard.getNumber("F (distance)", 0);
	
	double P = .272;
	double I = 0.0001;
	double D = 0;
	double F = 0;
	
	
    public ScaleAutoCommand() {
    	requires(Robot.drivetrainSubsystem);
    	source = new PIDSourceDistance();
    	pid = new PIDController(P, I, D, F, source, this);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	source.reset();
    	source.setPIDSourceType(PIDSourceType.kDisplacement);
    	pid.setInputRange(0f,  setpoint*1.1);
    	pid.setOutputRange(0f, maxSpeed);
    	//pid.setAbsoluteTolerance(0.1);
    	pid.setPercentTolerance(2.0);
    	pid.setContinuous(false);
    	pid.setPID(P, I, D, F);
    	pid.setSetpoint(setpoint);
    	//Robot.robotLogger.info("This logger comes BEFORE PID Enable.");
    	pid.enable();
    	//Robot.robotLogger.info("This logger comes AFTER PID Enable.");

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Noelle waz here
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrainSubsystem.Drive(0,0);
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrainSubsystem.Drive(0,0);
    	pid.disable();
    }
    public double output = 0;
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stud
    	SmartDashboard.putNumber("Left Encoder Distance", Robot.drivetrainSubsystem.getLeftEncoder());
    	SmartDashboard.putNumber("Right Encoder Distance", Robot.drivetrainSubsystem.getRightEncoder());
    	SmartDashboard.putNumber("Total Distance Travelled", Robot.drivetrainSubsystem.getCountDistanceFeet());
    	Logger.log(Logger.LogLevel.info, "Error is " + (setpoint - Robot.drivetrainSubsystem.getCountDistanceFeet()));
    	SmartDashboard.putNumber("Average Encoder = ", Robot.drivetrainSubsystem.getAverageEncoder());
		SmartDashboard.putNumber("Output (Distance)", output);
		Logger.log(Logger.LogLevel.info, "Left Encoder is " + Robot.drivetrainSubsystem.getLeftEncoder());
		Logger.log(Logger.LogLevel.info, "Right Encoder is " + Robot.drivetrainSubsystem.getRightEncoder());
		Logger.log(Logger.LogLevel.info, "ScaleAuto.output = " + output);
    	Robot.drivetrainSubsystem.Drive(-output, 0);
		//this.output = output;

	}
}
