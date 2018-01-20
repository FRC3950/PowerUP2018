package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.PIDSourceYaw;
import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;
import org.slf4j.Logger;


import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightCommand extends Command implements PIDOutput {
	
	PIDController pid;
	double output = .5;
	PIDSourceYaw yaw;
	
	double P = SmartDashboard.getNumber("P (drive straight)", .95);
	double I = SmartDashboard.getNumber("I (drive straight)", 0.128);
	double D = SmartDashboard.getNumber("D (drive straight)", 0.075);
	double F = SmartDashboard.getNumber("F (drive straight)", 0);
	
	double speed = 0.5;

    public DriveStraightCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrainSubsystem);
        yaw = new PIDSourceYaw();
    	pid = new PIDController(P, I, D, yaw, this);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	yaw.reset();
    	yaw.setPIDSourceType(PIDSourceType.kDisplacement);
    	pid.setInputRange(-20.0f,  20.0f);
    	pid.setOutputRange(.5, -.5);
    	pid.setAbsoluteTolerance(0.2);
    	pid.setContinuous(false);
    	pid.setPID(P, I, D, F);
    	pid.setSetpoint(0);
    	Robot.robotLogger.info("This logger comes BEFORE PID Enable.");
    	pid.enable();
    	Robot.robotLogger.info("This logger comes AFTER PID Enable.");
    }

    // Called repeatedly when the command scheduled to run
    protected void execute() {
		SmartDashboard.putNumber("YAW", yaw.pidGet());
		SmartDashboard.putBoolean("On target", pid.onTarget());
		SmartDashboard.putNumber("Output", pid.get());
		pid.setSetpoint(0);
    	Robot.robotLogger.info("I am in DriveStraightCommand Execute!");
//    	if(!pid.onTarget()) {
//    		RobotMap.right.set(speed + 0.000001);
//    	}
//    
		
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		Robot.robotLogger.debug("Output = " + output);
    	Robot.drivetrainSubsystem.Drive(.5, output);
	}
}
