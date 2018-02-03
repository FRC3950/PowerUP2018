 package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.RobotMap;
 //import org.usfirst.frc.team3950.robot.commands.RampStopCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RampSubsystem extends Subsystem {
	Solenoid rampSolenoid;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand())
    	rampSolenoid = RobotMap.rampSolenoid;
    }
    
    public void RampIn() {
    	rampSolenoid.set(false);
    }
    
    public void RampDown() {
    	rampSolenoid.set(true);
    }
    
    	
    }
    
    


