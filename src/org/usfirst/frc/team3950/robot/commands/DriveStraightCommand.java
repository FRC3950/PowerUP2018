package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.PIDSourceYaw;
import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightCommand extends Command implements PIDOutput {
	
	AHRS navx;
	PIDController pid;
	double output = .5;
	PIDSourceYaw yaw;
	PIDOutput out;
	
	double P = SmartDashboard.getNumber("P (drive straight)", .95);
	double I = SmartDashboard.getNumber("I (drive straight)", 0.128);
	double D = SmartDashboard.getNumber("D (drive straight)", 0.075);
	double F = SmartDashboard.getNumber("F (drive straight)", 0);
	
	double speed = 0.5;

    public DriveStraightCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrainSubsystem);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	navx = RobotMap.ahrs;
    	yaw = new PIDSourceYaw();
    	navx.reset();
    	navx.zeroYaw();
    	out = new PIDOutput() {
			public void pidWrite(double out) {
				output = out;
				
			}
    	};
    	pid = new PIDController(P, I, D, F, yaw, out);
    	
    	
    	pid.setOutputRange(-1.0, 1.0);
    	pid.setAbsoluteTolerance(0.2);
    	pid.setContinuous(false);
    	pid.setPID(P, I, D, F);
    	pid.setSetpoint(0);
    }

    // Called repeatedly when the command scheduled to run
    protected void execute() {
    	pid.enable();
		SmartDashboard.putNumber("YAW", yaw.pidGet());
		SmartDashboard.putBoolean("On target", pid.onTarget());
		SmartDashboard.putNumber("Output", pid.get());
		pid.setSetpoint(0);
    	Robot.drivetrainSubsystem.Drive(/*some number*/ .5, pid.get());
    	
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
		
	}
}
