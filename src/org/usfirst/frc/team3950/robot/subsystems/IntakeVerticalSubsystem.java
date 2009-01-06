package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.IntakeVerticalCommand;
import org.usfirst.frc.team3950.robot.commands.NoLimitSwIntakeVerticalCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeVerticalSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_VictorSPX vertical;
	DigitalInput bottomSwitch;
	DigitalInput topSwitch;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	bottomSwitch = RobotMap.intakeBottomLimitSwitch;
    	topSwitch = RobotMap.intakeTopLimitSwitch;
    	vertical = RobotMap.intakeVerticalMotor;
    	
    	setDefaultCommand(new NoLimitSwIntakeVerticalCommand());
    }
    
    public boolean atBottom() {
    	return bottomSwitch.get();
    }
    
    public boolean atTop() {
    	return topSwitch.get();
    }
    
    public void intakeVertical(double speed) {
    	vertical.set(speed);
    }
}

