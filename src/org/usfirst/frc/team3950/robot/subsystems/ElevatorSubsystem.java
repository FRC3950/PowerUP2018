package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	WPI_TalonSRX elevatorMotor;
	WPI_VictorSPX elevatorMotorFollower;
	DigitalInput limitSwitch;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	elevatorMotor = RobotMap.elevatorMotor;
    	elevatorMotorFollower = RobotMap.elevatorMotorFollower;
    	limitSwitch = RobotMap.limitSwitch;
    	
 
    	}
    
    public void limitSwitch() {
    	   if (limitSwitch.get()) {
      	     elevatorMotor.set(0);
    	   }else if (!limitSwitch.get()) {
    	    	elevatorMotor.set(1);
    	     
    	   }
    	   }
    	    	
    }
    
    
    



