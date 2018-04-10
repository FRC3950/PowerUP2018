package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.DriveCommand;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

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
	double wheelDiameter;
	double wheelCircumference;
	
	WPI_VictorSPX backLeft;
	WPI_TalonSRX frontLeft;
	WPI_VictorSPX backRight;
	WPI_TalonSRX frontRight;
	AHRS navx;
	
	DifferentialDrive drivetrain;
	
	//private static Logger logger = LoggerFactory.getLogger(DrivetrainSubsystem.class);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	frontLeft = RobotMap.frontLeft;
    	backLeft = RobotMap.backLeft;
    	frontRight = RobotMap.frontRight;
    	backRight = RobotMap.backRight;
    	
    	frontLeft.setNeutralMode(NeutralMode.Brake);
    	backLeft.setNeutralMode(NeutralMode.Brake);
    	frontRight.setNeutralMode(NeutralMode.Brake);
    	backRight.setNeutralMode(NeutralMode.Brake);
    	
    	SpeedControllerGroup left = new SpeedControllerGroup(frontLeft,backLeft);
    	SpeedControllerGroup right = new SpeedControllerGroup(frontRight,backRight);
    	
    	frontLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.Analog, 0, 0);
    	frontLeft.setSensorPhase(false);
    	frontRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.Analog, 0, 0);
    	frontRight.setSensorPhase(false);
    	
    	drivetrain = new DifferentialDrive(left, right);
    	navx = RobotMap.ahrs;
    	
    	
    	//diameter in feet
    	//wheelDiameter = (1.0/3.0);
    	
    	
    wheelCircumference =  (19.0 + (11.0/16))/12.0; //(18.75)/12; //.5*Math.PI;
    	
    	
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
    
    
    /*
     * Hey Bryce! If you're reading this, it's probably because you're wondering
     * why your encoders aren't working the way they should. Have you tried
     * negating them? I negated the left one. Maybe it shouldn't be.
     * Hope this was helpful. See ya! 
     * 
     * This is really strange, just saying. like wtf dude 
     * 
     */
    
    public int getLeftEncoder() {
    	return -(frontLeft.getSelectedSensorPosition(0));
    }
    public int getRightEncoder() {
    	return frontRight.getSelectedSensorPosition(0);
    }
    public double getAverageEncoder() {
     return (-(frontLeft.getSelectedSensorPosition(0)) + frontRight.getSelectedSensorPosition(0))/2;
    }
 
    public void resetEncoders() {
    	frontLeft.setSelectedSensorPosition(0, 0, 0);
    	frontRight.setSelectedSensorPosition(0, 0, 0);
    }
    
    public double getCountDistanceFeet() {
    	//return (getAverageEncoder()/1024)*wheelDiameter*Math.PI;
    	return (getAverageEncoder()/1024)*wheelCircumference;
    }
    
}
