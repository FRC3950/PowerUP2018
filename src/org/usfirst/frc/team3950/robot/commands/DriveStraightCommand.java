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
public class DriveStraightCommand extends Command {
	
	AHRS navx;
	PIDController pid;
	double output = 0;
	PIDSourceYaw yaw;
	
	double P = SmartDashboard.getNumber("P (drive straight)", 1);
	double I = SmartDashboard.getNumber("I (drive straight)", 0);
	double D = SmartDashboard.getNumber("D (drive straight)", 0);
	double F = SmartDashboard.getNumber("F (drive straight)", 0);

    public DriveStraightCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrainSubsystem);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	navx = RobotMap.ahrs;
    	yaw = new PIDSourceYaw();
    	navx.zeroYaw();
    	
    	pid = new PIDController(P, I, D, F, yaw, new PIDOutput() {
			@Override
			public void pidWrite(double out) {
				output = out;	
			}
    	});
    	
    	pid.enable();
    	pid.setOutputRange(-1, 1);
    	pid.setSetpoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		SmartDashboard.putNumber("Angle", navx.getYaw());
    	Robot.drivetrainSubsystem.Drive(/*some number*/ .5, /*output*/ 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	pid.disable();
    }
}
