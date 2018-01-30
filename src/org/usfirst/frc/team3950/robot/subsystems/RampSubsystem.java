 package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.RampStopCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RampSubsystem extends Subsystem {
	WPI_TalonSRX leftMotor;
	WPI_TalonSRX rightMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new RampStopCommand());
    	leftMotor = RobotMap.rampLeftMotor;
    	rightMotor = RobotMap.rampRightMotor;
    }
    
    public void RampIn() {
    	leftMotor.set(1);
    	rightMotor.set(1);
    }
    
    public void RampStop() {
    	leftMotor.set(0);
    	rightMotor.set(0);
    }
    public void RampDown() {
    	leftMotor.set(-1);
    	rightMotor.set(-1);
    }
    
    	
    }
    
    


