package org.usfirst.frc.team3950.robot.subsystems;

//import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.*;

//import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
//import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands
	
	WPI_VictorSPX left;
	WPI_VictorSPX right;
	WPI_VictorSPX vertical;
	DigitalInput cubeSwitch;
	DigitalInput bottomSwitch;
	DigitalInput topSwitch;
	DoubleSolenoid horizontalLeft;
	DoubleSolenoid horizontalRight;
	
	double speed = 0.75;
	
	public double getIntakeSpeed() {
		return speed;
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeCommand());
    	//setDefaultCommand(new IntakeFakeStopCommand());
    	
    	left = RobotMap.intakeLeftMotor;
    	right = RobotMap.intakeRightMotor;
    	vertical = RobotMap.intakeVerticalMotor;
    	horizontalLeft = RobotMap.intakeHorizontalLeft;
    	horizontalRight = RobotMap.intakeHorizontalRight;
    	cubeSwitch = RobotMap.intakeCubeOneOptical;
    	bottomSwitch = RobotMap.intakeBottomLimitSwitch;
    	topSwitch = RobotMap.intakeTopLimitSwitch;
    }
    
    	
    public boolean currentOverload() {
    	return left.getOutputCurrent() >= 40 || right.getOutputCurrent() >= 40;
    
    }
    
    public boolean boxIn() {
    	return !cubeSwitch.get();
    }
    
    public boolean atBottom() {
    	return bottomSwitch.get();
    }
    
    public boolean atTop() {
    	return topSwitch.get();
    }
    
    public void Intake(double trigger) {
    	left.set(-trigger);
    	right.set(trigger);    	
    }
    
    public void intakeVertical(double speed) {
    	vertical.set(speed);
    }

    public void horizontalOut() {
    	horizontalLeft.set(DoubleSolenoid.Value.kReverse);
    	horizontalRight.set(DoubleSolenoid.Value.kReverse);

    }
    public void horitontalIn() {
    	horizontalLeft.set(DoubleSolenoid.Value.kForward);
    	horizontalRight.set(DoubleSolenoid.Value.kForward);
    }  

    public Value getIntakeHorizontalValue() {
    	return horizontalRight.get();
    	
    }
    	    
    }


