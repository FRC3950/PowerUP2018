package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.ElevatorCommand;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
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
	DoubleSolenoid elevatorSolenoid;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand( new ElevatorCommand());

    	elevatorMotor = RobotMap.elevatorMotor;
    	elevatorMotorFollower = RobotMap.elevatorMotorFollower;
    	bottomLimitSwitch = RobotMap.bottomLimitSwitch;
    	topLimitSwitch = RobotMap.topLimitSwitch;
    	elevatorSolenoid = RobotMap.elevatorSolenoid;
 
    	}
 
    public boolean bottomGetter() {
    	return bottomLimitSwitch.get();
    }
    
    public boolean topGetter() {
    	return topLimitSwitch.get();
    }
        
    public void elevatorControl(double leftstick) {
    	elevatorMotor.set(leftstick);
    	elevatorMotorFollower.set(-leftstick);
    }
    
    public void elevatorUp(double leftstick) {
    	elevatorSolenoid.set(null);
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
    
}

 
    		
  
    	    	
    
    
    
    



