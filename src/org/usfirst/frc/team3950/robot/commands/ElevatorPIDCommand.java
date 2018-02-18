package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Logger;
import org.usfirst.frc.team3950.robot.Logger.LogLevel;
import org.usfirst.frc.team3950.robot.PIDSourceElevator;
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
public class ElevatorPIDCommand extends Command implements PIDOutput{
 	double P = SmartDashboard.getNumber("P (elevator)", .0008);
	double I = SmartDashboard.getNumber("I (elevator)", 0.0001);
	double D = SmartDashboard.getNumber("D (elevator)", 0.001);
	double F = SmartDashboard.getNumber("F (elevator)", 0);
	
	PIDSourceElevator source;
	PIDController pid;
	double setpoint = 0;
	int range = 10;

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
    	pid.setInputRange(0,  setpoint);
    	pid.setOutputRange(-.5, .5);
    	pid.setPercentTolerance(5.0);
    	pid.setContinuous(false);
    	pid.setPID(P, I, D, F);
    	pid.setSetpoint(setpoint);
    	pid.enable();
    	
     }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		
    	}

    	
    
    //the speed if statement can be removed later
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	int velocity = RobotMap.elevatorMotor.getSelectedSensorVelocity(0);
    	//Logger.log(Logger.LogLevel.debug, "pid.onTarget() is " + pid.onTarget());
        //return pid.onTarget();
    	System.out.println("velocity is " + velocity);
    	System.out.println("pid on target is " + pid.onTarget());
    	return pid.onTarget() && velocity >= -range && 
    			velocity <= range;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSubsystem.elevatorControl(0);
    	pid.disable();
    	System.out.println("done end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevatorSubsystem.elevatorControl(0);;
    	pid.disable();
    	System.out.println("done intr");
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		Robot.elevatorSubsystem.elevatorControl(output);
		Logger.log(LogLevel.info, "Encoder Height " + Robot.elevatorSubsystem.getElevatorHeight());
		Logger.log(Logger.LogLevel.info, "elevator enc counts" + RobotMap.elevatorMotor.getSelectedSensorPosition(0));
		
	}
}
