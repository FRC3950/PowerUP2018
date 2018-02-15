package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Logger;
import org.usfirst.frc.team3950.robot.Logger.LogLevel;
import org.usfirst.frc.team3950.robot.PIDSourceElevator;
import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorPIDCommand extends Command implements PIDOutput{
 	double P = SmartDashboard.getNumber("P (elevator)", .45);
	double I = SmartDashboard.getNumber("I (elevator)", 0.128);
	double D = SmartDashboard.getNumber("D (elevator)", 0.075);
	double F = SmartDashboard.getNumber("F (elevator)", 0);
	
	PIDSourceElevator source;
	PIDController pid;
	double setpoint;

    public ElevatorPIDCommand(double input) {
        // eg. requires(chassis);
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.elevatorSubsystem);
    	source = new PIDSourceElevator();
    	pid = new PIDController(P, I, D, F, source, this);
    	setpoint = input;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevatorSubsystem.resetEncoder();
    	source.setPIDSourceType(PIDSourceType.kDisplacement);
    	pid.setInputRange(0,  79);
    	pid.setOutputRange(-1, 1);
    	pid.setAbsoluteTolerance(0.3);
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
    	Robot.elevatorSubsystem.elevatorControl(0);
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevatorSubsystem.elevatorControl(0);
    	pid.disable();
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		Robot.elevatorSubsystem.elevatorControl(output);
		Logger.log(LogLevel.info, "Encoder Height " + Robot.elevatorSubsystem.getElevatorHeight());
	}
}
