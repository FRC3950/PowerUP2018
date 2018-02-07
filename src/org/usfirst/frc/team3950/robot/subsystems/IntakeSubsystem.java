package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.*;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	WPI_VictorSPX left;
	WPI_VictorSPX right;
	DigitalInput intakeSwitch;
	DoubleSolenoid vertical;
	DoubleSolenoid horizontal;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeCommand());
    	
    	left = RobotMap.intakeLeftMotor;
    	right = RobotMap.intakeRightMotor;
    	vertical = RobotMap.intakeVertical;
    	horizontal = RobotMap.intakeHorizontal;
    	
    	
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
    public void verticalUp() {
    	vertical.set(DoubleSolenoid.Value.kReverse);
    
    }
    public void verticalDown() {
    	vertical.set(DoubleSolenoid.Value.kForward);
    
    }
    public void horizontalOut() {
    	horizontal.set(DoubleSolenoid.Value.kReverse);

    }
    public void horitontalIn() {
    	horizontal.set(DoubleSolenoid.Value.kForward);
    }  
    public Value getIntakeVerticalValue() {
    	return vertical.get();
    	
    }
    public Value getIntakeHorizontalValue() {
    	return horizontal.get();
    	
    }
    	    
    }


