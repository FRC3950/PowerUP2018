package org.usfirst.frc.team3950.robot.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.DriveCommand;

import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands
	
	
	double output;
	
	WPI_TalonSRX frontLeft;
	WPI_TalonSRX backLeft;
	WPI_TalonSRX frontRight;
	WPI_TalonSRX backRight;
	AHRS navx;
	
	DifferentialDrive drivetrain;
	
	private static Logger logger = LoggerFactory.getLogger(DrivetrainSubsystem.class);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	frontLeft = RobotMap.frontLeft;
    	backLeft = RobotMap.backLeft;
    	frontRight = RobotMap.frontRight;
    	backRight = RobotMap.backRight;
    	
    	SpeedControllerGroup left = new SpeedControllerGroup(frontLeft,backLeft);
    	SpeedControllerGroup right = new SpeedControllerGroup(frontRight,backRight);
    	
    	backLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	backLeft.setSensorPhase(false);
    	backRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
    	backRight.setSensorPhase(false);
    	
    	drivetrain = new DifferentialDrive(left, right);
    	navx = RobotMap.ahrs;
    	
    	byte[] buffer = new byte[6];
    	
    	
    	System.out.println("I am in drivetrainSubsystem initDefaultCommand");

    	
    	frontLeft.setSafetyEnabled(false);
    	backLeft.setSafetyEnabled(false);
    	frontRight.setSafetyEnabled(false);
    	backRight.setSafetyEnabled(false);
    	drivetrain.setSafetyEnabled(false);
    	
    	setDefaultCommand(new DriveCommand());
    }
    
    public void Drive(double y, double twist){
    	drivetrain.arcadeDrive(-y, twist);
    	//logger.info("Twist value is" + Double.toString(-twist));
    }
    
    public int getLeftEncoder() {
    	return backLeft.getSelectedSensorPosition(0);
    }
    public int getRightEncoder() {
    	return backRight.getSelectedSensorPosition(0);
    }
    public int getAverageEncoder() {
     return (int)(backLeft.getSelectedSensorPosition(0) + backRight.getSelectedSensorPosition(0))/2;
    }
 
    public void resetEncoders() {
    	backLeft.setSelectedSensorPosition(0, 0, 0);
    	backRight.setSelectedSensorPosition(0, 0, 0);
    }
    
}
