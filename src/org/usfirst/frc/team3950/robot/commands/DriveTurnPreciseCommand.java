package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.PIDSourceYaw;
import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTurnPreciseCommand extends Command implements PIDOutput {
	
	PIDController pid;
	double output = 1;
	PIDSourceYaw yaw;
	
	//setpoint in degrees
	double setpoint = 90;
	
	double P = SmartDashboard.getNumber("P (turn)", .35);
	double I = SmartDashboard.getNumber("I (turn)", 0.128);
	double D = SmartDashboard.getNumber("D (turn)", 0.075);
	double F = SmartDashboard.getNumber("F (turn)", 0);

    public DriveTurnPreciseCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrainSubsystem);
    	yaw = new PIDSourceYaw();
    	pid = new PIDController(P, I, D, F, yaw, this);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	yaw.reset();
    	yaw.setPIDSourceType(PIDSourceType.kDisplacement);
    	pid.setInputRange(0, setpoint*1.1);
    	pid.setOutputRange(-.75,.75);
    	pid.setAbsoluteTolerance(0.2);
    	pid.setContinuous(false);
    	pid.setPID(P, I, D, F);
    	pid.setSetpoint(setpoint);
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		SmartDashboard.putNumber("YAW", yaw.pidGet());
		SmartDashboard.putNumber("Output", pid.get());
    	Robot.drivetrainSubsystem.Drive(0, output);
		
	}
}
