package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.*;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	WPI_TalonSRX left;
	WPI_TalonSRX right;
	DigitalInput intakeSwitch;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeCommand());
    	
    	left = RobotMap.intakeLeftMotor;
    	right = RobotMap.intakeRightMotor;
    	
    	
    }
    
    	
    public boolean currentOverload() {
    	return left.getOutputCurrent() >= 40 || right.getOutputCurrent() >= 40;
    
    }
    
    public boolean boxIn() {
    	return intakeSwitch.get();
    }
    
    public void Intake(double trigger) {
    	left.set(trigger);
    	right.set(-trigger);
    	
    	
    }
}


