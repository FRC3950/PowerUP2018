 package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.RobotMap;
 //import org.usfirst.frc.team3950.robot.commands.RampStopCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RampSubsystem extends Subsystem {
	DoubleSolenoid rampSolenoid;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand())
    	rampSolenoid = RobotMap.rampSolenoid;
    }
    
    public void rampUp() {
    	rampSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void rampDown() {
    	rampSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public Value getRampStatus() {
    	return rampSolenoid.get();
    }
    
    	
    }
    
    


