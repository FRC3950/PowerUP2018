package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Logger;
import org.usfirst.frc.team3950.robot.PIDSourceYaw;
import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTurnPreciseCommand extends Command implements PIDOutput  {
	
	PIDController pid;
	double output = 1;
	PIDSourceYaw yaw;
	
	//setpoint in degrees
	double setpoint = 0;
	
//	double P = SmartDashboard.getNumber("P (turn)", .025);
//	double I = SmartDashboard.getNumber("I (turn)", 0.0);
//	double D = SmartDashboard.getNumber("D (turn)", 0);
//	double F = SmartDashboard.getNumber("F (turn)", 0);
	
	double P = .0105;
	double I = 0.0005;
	double D = 0.15;
	double F = 0;

    public DriveTurnPreciseCommand(double input) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrainSubsystem);
    	//yaw = new PIDSourceYaw();
    	setpoint = input;
    	//pid = new PIDController(P, I, D, F, yaw, this);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Logger.log(Logger.LogLevel.debug, "Drive Turn Precise init");
    	yaw = new PIDSourceYaw();
    	RobotMap.ahrs.reset();
    	yaw.setPIDSourceType(PIDSourceType.kDisplacement);
    	pid = new PIDController(P, I, D, F, yaw, this);
//    	SmartDashboard.putNumber("Setpoint End", 45);
//    	SmartDashboard.putNumber("Setpoint Begin", 0);
    	pid.setInputRange(setpoint>0?0:setpoint*1.1, setpoint<0?0:setpoint*1.1);
		//pid.setInputRange(SmartDashboard.getNumber("Setpoint Begin", 0), (SmartDashboard.getNumber("Setpoint End", 45))*1.1);
    	pid.setOutputRange(-.75,.75);
    	pid.setPercentTolerance(4);
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
    //	System.out.println("pid.onTarget() = " + pid.onTarget());
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
		SmartDashboard.putNumber("YAW", RobotMap.ahrs.getYaw());
		System.out.println("yaw " + yaw.pidGet());
		SmartDashboard.putNumber("Output", pid.get());
    	Robot.drivetrainSubsystem.Drive(0, output);
		
	}
}
