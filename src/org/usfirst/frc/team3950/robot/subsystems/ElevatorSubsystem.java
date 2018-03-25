 package org.usfirst.frc.team3950.robot.subsystems;

//import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.ElevatorCommand;


//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {
	// XboxController controller = Robot.oi.xboxcontroller;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	WPI_TalonSRX elevatorMotor;
	WPI_VictorSPX elevatorMotorFollower;
	DigitalInput bottomLimitSwitch;
	DigitalInput topLimitSwitch;
	DoubleSolenoid brakeSolenoid;
	DoubleSolenoid shiftSolenoid;
	
	double distancePerRotation;
	
	double switchHeight = 24;
	double scaleLowHeight = 58;
	double scaleMidHeight = 61;
	double scaleTopHeight = 79;
	
	public double getSwitchHeight() {
		return switchHeight;
	}
	public double getScaleLowHeight() {
		return scaleLowHeight;
	}
	public double getScaleMidHeight() {
		return scaleMidHeight;
	}
	public double getScaleTopHeight() {
		return scaleTopHeight;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand( new ElevatorCommand());

    	elevatorMotor = RobotMap.elevatorMotor;
    	elevatorMotorFollower = RobotMap.elevatorMotorFollower;
    	bottomLimitSwitch = RobotMap.elevatorBottomLimitSwitch;
    	topLimitSwitch = RobotMap.elevatorTopLimitSwitch;
    	brakeSolenoid = RobotMap.elevatorBrakeSolenoid;
    	shiftSolenoid = RobotMap.elevatorShiftSolenoid;
    	
    	elevatorMotorFollower.follow(elevatorMotor);
    	
    	elevatorMotor.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
    	elevatorMotor.setSensorPhase(false);
 
    	//distance per rotation in inches
    	distancePerRotation = (19 + (11/16));
    	
    	}
 
    public boolean bottomGetter() {
    	return bottomLimitSwitch.get();
    }
    
    public boolean topGetter() {
    	return topLimitSwitch.get();
    }
        
    public void elevatorControl(double leftstick) {
    	elevatorMotor.set(leftstick);
    	elevatorMotorFollower.set(leftstick);
    }
    
    public void MotionMagic() {
		elevatorMotor.configNominalOutputForward(0, 0);
		elevatorMotor.configNominalOutputReverse(0, 0);
		elevatorMotor.configPeakOutputForward(1, 0);
		elevatorMotor.configPeakOutputReverse(-1, 0);
    	
		elevatorMotor.selectProfileSlot(0, 0);
		elevatorMotor.config_kF(0, 0.2, 0);
		elevatorMotor.config_kP(0, 0.2, 0);
		elevatorMotor.config_kI(0, 0, 0);
		elevatorMotor.config_kD(0, 0, 0);
		

    	elevatorMotor.configMotionCruiseVelocity(1, 0);
    	elevatorMotor.configMotionAcceleration(1, 0);
    	
    	elevatorMotor.setSelectedSensorPosition(0, 0,0);
    }
    
    public int getEncoder(){
    	return elevatorMotor.getSelectedSensorPosition(0);
    }
    
    public void resetEncoder() {
    	elevatorMotor.setSelectedSensorPosition(0, 0, 0);
    }
    
    public double getElevatorHeight() {
    	return (getEncoder()/4096)*distancePerRotation;
    }
    public void elevatorBrake() {
    	brakeSolenoid.set(DoubleSolenoid.Value.kForward);
    	elevatorMotor.set(0);
    	elevatorMotorFollower.set(0);
    }
    
    public double getMotorValue() {
    	return elevatorMotor.get();
    }
    
    public void shiftGear() {
    	if (shiftSolenoid.get() == DoubleSolenoid.Value.kForward) {
    		shiftSolenoid.set(DoubleSolenoid.Value.kReverse);
    	} else {
    		shiftSolenoid.set(DoubleSolenoid.Value.kForward);
    	}
    }
    
    public void undoBrake(){
    	brakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
} 

    
    

 
    		
  
    	    	
    
    
    
    



