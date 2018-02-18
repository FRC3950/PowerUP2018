package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Logger;
import org.usfirst.frc.team3950.robot.Logger.LogLevel;
import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorCommand extends Command {
	XboxController controller = Robot.oi.xboxcontroller;
	boolean bottom = false;
	boolean top = false;
	
	double tolerance  = 0.1;
	double getY = 0;
	
	
	double P = SmartDashboard.getNumber("P (elevator)", .002);
	double I = SmartDashboard.getNumber("I (elevator)", 0.001);
	double D = SmartDashboard.getNumber("D (elevator)", 0.0);
	double F = SmartDashboard.getNumber("F (elevator)", 0);

    public ElevatorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    //change code button to go to each specific height //bottom //switch //scale //high scale //or not
    protected void execute() {
    	//System.out.println("I am in execute of el com");
    	getY = controller.getY(Hand.kLeft);
    	
    	bottom = RobotMap.elevatorBottomLimitSwitch.get();
    	top = RobotMap.elevatorTopLimitSwitch.get();
    	
    	//Logger.log(LogLevel.info, "y axis is " + getY);
    	
    	
    	
    	if(getY >= -tolerance && getY <= tolerance) {
    		getY = 0;
    	}

    	if (bottom) {
    		Robot.elevatorSubsystem.resetEncoder();
    		getY = 0;
    		if (getY >= 0) {
    			Robot.elevatorSubsystem.elevatorControl(getY);
    		}
    	}
    	else if (top) {
    		getY = 0;
    		if (getY <= 0) {
    			Robot.elevatorSubsystem.elevatorControl(getY);
    		}
    	} else {
    		Robot.elevatorSubsystem.elevatorControl(getY);
    	}
    	
    	if (Robot.elevatorSubsystem.getMotorValue() == 0 /*|| RobotMap.elevatorBottomLimitSwitch.get()
    			|| RobotMap.elevatorTopLimitSwitch.get()*/) {
    		Robot.elevatorSubsystem.elevatorBrake();
    		}
    	else {
    		Robot.elevatorSubsystem.undoBrake();
    	}
    	
    }
    		
    		// if (y<0) {
    		// elevatorMotor.set(0);
        // if (bottom) {
        	// if (y>0) {
        		// elevatorMotor.set(1);
        	
    		

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {    
    }

	public void pidWrite(double output) {
		SmartDashboard.putNumber("Elevator output", output);
		RobotMap.elevatorMotor.set(output);
		RobotMap.elevatorMotorFollower.set(output);
		//Robot.elevatorSubsystem.MotionMagic();
	
		
	}

}
	