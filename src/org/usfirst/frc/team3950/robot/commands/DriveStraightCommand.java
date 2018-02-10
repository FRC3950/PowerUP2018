package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.PIDSourceYaw;
import org.usfirst.frc.team3950.robot.Robot;
//import org.slf4j.Logger;


import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightCommand extends Command implements PIDOutput {
	
	
	PIDController pid;
	//double output = 1;
	PIDSourceYaw yaw;
	Timer timer = new Timer();
	

			
	double P = SmartDashboard.getNumber("P (drive straight)", 2.9);
	double I = SmartDashboard.getNumber("I (drive straight)", 0.0);
	double D = SmartDashboard.getNumber("D (drive straight)", 0.01);
	double F = SmartDashboard.getNumber("F (drive straight)", 0);


    public DriveStraightCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrainSubsystem);
        yaw = new PIDSourceYaw();
    	pid = new PIDController(P, I, D, F, yaw, this);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	Robot.robotLogger.info("DriveStraightCommand.initialize");

    	double P = SmartDashboard.getNumber("P (drive straight)", .95);
    	double I = SmartDashboard.getNumber("I (drive straight)", 0.128);
    	double D = SmartDashboard.getNumber("D (drive straight)", 0.075);
    	double F = SmartDashboard.getNumber("F (drive straight)", 0);


    	yaw.reset();
    	yaw.setPIDSourceType(PIDSourceType.kDisplacement);
    	pid.setInputRange(-5.0f,  5.0f);
    	pid.setOutputRange(-.5, 0.5);
    	pid.setAbsoluteTolerance(0.05);
    	pid.setContinuous(false);
    	pid.setPID(P, I, D, F);
    	pid.setSetpoint(0);
    	pid.enable();
    }

    // Called repeatedly when the command scheduled to run
    protected void execute() {
		SmartDashboard.putNumber("YAW", yaw.pidGet());
		SmartDashboard.putBoolean("On target", pid.onTarget());
		SmartDashboard.putNumber("Output", pid.get());
//		pid.setSetpoint(0);
		
//		colorSen.readColors();
//    	SmartDashboard.putNumber("Red sensor", colorSen.getRedVal());
//    	SmartDashboard.putNumber("Green sensor", colorSen.getGreenVal());
//    	SmartDashboard.putNumber("Blue sensor", colorSen.getBlueVal());
//    	SmartDashboard.putNumber("Clear sensor", colorSen.getClearVal());
    	
		
//		if (timer.get() == 2) {
//			timer.stop();
//			pid.setSetpoint(90);
//		}    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isCanceled();
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

	byte[] i2cBuffer = new byte[6];
	
	public double output = 0;
	
	@Override
	public void pidWrite(double output) {
		SmartDashboard.putNumber("YAW", yaw.pidGet());
		SmartDashboard.putNumber("Output", pid.get());
		//Robot.robotLogger.debug("Output = " + output);
    	Robot.drivetrainSubsystem.Drive(SmartDashboard.getNumber("Speed", -0.5), output);
		// TODO Auto-generated method stub
		Robot.robotLogger.debug("DriveStraightCommand.output = " + output);
    	Robot.drivetrainSubsystem.Drive(SmartDashboard.getNumber("Speed", -1), output);
		this.output = output;

	}
}
