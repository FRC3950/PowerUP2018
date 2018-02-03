package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.EncPIDController;
import org.usfirst.frc.team3950.robot.PIDOutputDistance;
import org.usfirst.frc.team3950.robot.PIDOutputYaw;
import org.usfirst.frc.team3950.robot.PIDSourceDistance;
import org.usfirst.frc.team3950.robot.PIDSourceYaw;
import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.YawPIDController;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StraightScaleAutoCommand extends Command{
	
	//from scaleAuto
	EncPIDController pidEnc;
	PIDController pidDistance;
	PIDSourceDistance source;
	PIDOutputDistance outputDistance;
	
	//from driveStright
	PIDController pidStraight;
	double output = 1;
	PIDSourceYaw yaw;
	PIDOutputYaw outputYaw;
	
	//constants for scaleAuto, setpoint in feet
	double maxSpeed = 1;
	double setpoint = 27f;
	
	//from scaleAuto
	double PEnc = SmartDashboard.getNumber("P (distance)", 0.5);
	double IEnc = SmartDashboard.getNumber("I (distance)", 0);
	double DEnc = SmartDashboard.getNumber("D (distance)", 0);
	double FEnc = SmartDashboard.getNumber("F (distance)", 0);
	
	//from driveStraight
	double P = SmartDashboard.getNumber("P (drive straight)", .45);
	double I = SmartDashboard.getNumber("I (drive straight)", 0.128);
	double D = SmartDashboard.getNumber("D (drive straight)", 0.075);
	double F = SmartDashboard.getNumber("F (drive straight)", 0);
	
	//get outputs
	public double encOut;
	public double navOut;
	
    public StraightScaleAutoCommand(double input) {
    	requires(Robot.drivetrainSubsystem);
    	source = new PIDSourceDistance();
    	setpoint = input;
    	outputDistance = new PIDOutputDistance();
    	//pidEnc = new EncPIDController(PEnc, IEnc, DEnc, FEnc, source, this);
    	pidDistance = new PIDController(PEnc, IEnc, DEnc, FEnc, source, outputDistance);
    	
    	yaw = new PIDSourceYaw();
    	outputYaw = new PIDOutputYaw();
    	pidStraight = new PIDController(P, I, D, F, yaw, outputYaw);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//from scaleAuto
    	source.reset();
    	source.setPIDSourceType(PIDSourceType.kDisplacement);
    	pidDistance.setInputRange(0f,  setpoint*1.1);
    	pidDistance.setOutputRange(0f, maxSpeed);
    	pidDistance.setAbsoluteTolerance(0.1);
    	pidDistance.setContinuous(false);
    	pidDistance.setPID(PEnc, IEnc, DEnc, FEnc);
    	pidDistance.setSetpoint(setpoint);
    	pidDistance.enable();
    	
    	//from driveStraight
    	yaw.reset();
    	yaw.setPIDSourceType(PIDSourceType.kDisplacement);
    	pidStraight.setInputRange(-5.0f, 5.0f);
    	pidStraight.setOutputRange(-0.5, 0.5);
    	pidStraight.setAbsoluteTolerance(0.1);
    	pidStraight.setContinuous(false);
    	pidStraight.setPID(P, I, D, F);
    	pidStraight.setSetpoint(0);
    	pidStraight.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 

    	SmartDashboard.putNumber("Left Encoder Distance", Robot.drivetrainSubsystem.getLeftEncoder());
    	SmartDashboard.putNumber("Right Encoder Distance", Robot.drivetrainSubsystem.getRightEncoder());
    	SmartDashboard.putNumber("Total Distance Travelled", Robot.drivetrainSubsystem.getCountDistanceFeet());
		SmartDashboard.putNumber("Output (Distance)", output);
    	
    	Robot.drivetrainSubsystem.Drive(-outputDistance.pidGet(), outputYaw.pidGet());
    	// Robot.drivetrainSubsystem.readColor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pidDistance.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrainSubsystem.Drive(0,0);
    	pidDistance.disable();
    	pidStraight.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrainSubsystem.Drive(0,0);
    	pidDistance.disable();
    	pidStraight.disable();
    }

//	@Override
//	public synchronized void pidWrite(double output) {
		// TODO Auto-generated method stud
//		for(StackTraceElement stElement : Thread.currentThread().getStackTrace()) {
//			System.out.println(stElement.getClassName());
//		}
//		String method = Thread.currentThread().getStackTrace()[3].getClassName();
//		System.out.println(method + " from pidWrite");
//		if(method.compareTo("ScaleAutoCommand") == 0) {
//			System.out.println("in the if statement scale auto true");
//			encOut = output;
//		}else if(method.compareTo("DriveStraightCommand") == 0) {
//			navOut = output;
//			System.out.println("in the if statement scale straight true");
//		}
//		Robot.drivetrainSubsystem.Drive(-pidStraight.get(), pidEnc.get());
//	}
	
}