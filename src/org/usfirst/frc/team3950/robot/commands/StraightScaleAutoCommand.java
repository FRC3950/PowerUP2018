package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.PIDSourceDistance;
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
public class StraightScaleAutoCommand extends Command implements PIDOutput{
	
	//from scaleAuto
	PIDController pidEnc;
	PIDSourceDistance source;
	
	//from driveStright
	PIDController pidStraight;
	double output = 1;
	PIDSourceYaw yaw;
	
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
	
    public StraightScaleAutoCommand() {
    	requires(Robot.drivetrainSubsystem);
    	source = new PIDSourceDistance();
    	pidEnc = new PIDController(PEnc, IEnc, DEnc, FEnc, source, this);
    	
    	yaw = new PIDSourceYaw();
    	pidStraight = new PIDController(P, I, D, F, yaw, this);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//from scaleAuto
    	source.reset();
    	source.setPIDSourceType(PIDSourceType.kDisplacement);
    	pidEnc.setInputRange(0f,  setpoint*1.1);
    	pidEnc.setOutputRange(0f, maxSpeed);
    	pidEnc.setAbsoluteTolerance(0.1);
    	pidEnc.setContinuous(false);
    	pidEnc.setPID(PEnc, IEnc, DEnc, FEnc);
    	pidEnc.setSetpoint(setpoint);
    	pidEnc.enable();
    	
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
    	
    	SmartDashboard.putNumber("P (distance)", 1.0);
    	SmartDashboard.putNumber("I (distance)", 0.0);
    	SmartDashboard.putNumber("D (distance)", 0.0);
    	SmartDashboard.putNumber("F (distance)", 0.0);

    	// Robot.drivetrainSubsystem.readColor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isCanceled();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrainSubsystem.Drive(0,0);
    	pidEnc.disable();
    	pidStraight.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrainSubsystem.Drive(0,0);
    	pidEnc.disable();
    	pidStraight.disable();
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stud
		String method = Thread.currentThread().getStackTrace()[2].getClassName();
		System.out.println(method + " from pidWrite");
		if(method.compareTo("ScaleAutoCommand") == 0) {
			System.out.println("in the friccin if statement scale auto true");
			encOut = output;
		}else {
			navOut = output;
			System.out.println("in the friccin if statement scale straight true");
		}
		Robot.drivetrainSubsystem.Drive(navOut, encOut);
	}
	
	
}
