package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.PIDSourceDistance;
import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.TCS34725ColorSensor;

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
	I2C i2cBus;
	TCS34725ColorSensor colorSen = new TCS34725ColorSensor();
	PIDSourceDistance source;
	
	//constants
	double maxSpeed = 1;
	//setpoint is in feet
	double setpoint = 8f;
	
	
	double P = SmartDashboard.getNumber("P (distance)", .5);
	double I = SmartDashboard.getNumber("I (distance)", 0);
	double D = SmartDashboard.getNumber("D (distance)", 0);
	double F = SmartDashboard.getNumber("F (distance)", 0);
	
	double ret_val;
	double redVal;
	double greenVal;
	double blueVal;
	double clearVal;
	
	
    public ScaleAutoCommand() {
    	//requires(Robot.drivetrainSubsystem);
    	source = new PIDSourceDistance();
    	pid = new PIDController(P, I, D, F, source, this);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	source.reset();
    	source.setPIDSourceType(PIDSourceType.kDisplacement);
    	pid.setInputRange(0f,  setpoint*1.1);
    	pid.setOutputRange(0f, maxSpeed);
    	pid.setAbsoluteTolerance(0.1);
    	pid.setContinuous(false);
    	pid.setPID(P, I, D, F);
    	pid.setSetpoint(setpoint);
    	//Robot.robotLogger.info("This logger comes BEFORE PID Enable.");
    	pid.enable();
    	//Robot.robotLogger.info("This logger comes AFTER PID Enable.");
    	ret_val = colorSen.init();
    	
    	Robot.robotLogger.info("Initialized" + ret_val);
    	
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	ret_val = colorSen.readColors();
    	
    	Robot.robotLogger.info("Read Colors Value" + ret_val);
    	
    	SmartDashboard.putNumber("Red sensor", colorSen.getRedVal());
    	SmartDashboard.putNumber("Green sensor", colorSen.getGreenVal());
    	SmartDashboard.putNumber("Blue sensor", colorSen.getBlueVal());
    	SmartDashboard.putNumber("Clear sensor", colorSen.getClearVal()); 


    	// Robot.drivetrainSubsystem.readColor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.drivetrainSubsystem.Drive(0,0);
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.drivetrainSubsystem.Drive(0,0);
    	pid.disable();
    }
    public double output = 0;
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stud
    	//SmartDashboard.putNumber("Left Encoder Distance", Robot.drivetrainSubsystem.getLeftEncoder());
    	//SmartDashboard.putNumber("Right Encoder Distance", Robot.drivetrainSubsystem.getRightEncoder());
    	//SmartDashboard.putNumber("Total Distance Travelled", Robot.drivetrainSubsystem.getCountDistanceFeet());
		SmartDashboard.putNumber("Output (Distance)", output);
		System.out.println("ScaleAuto.output = " + output);
    	//Robot.drivetrainSubsystem.Drive(-output, 0);
		this.output = output;

	}
}
