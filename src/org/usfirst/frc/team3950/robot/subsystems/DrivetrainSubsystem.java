package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.DriveCommand;

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
	
	double output = 0;
	
	WPI_TalonSRX frontLeft;
	WPI_VictorSPX backLeft;
	WPI_TalonSRX frontRight;
	WPI_VictorSPX backRight;
	AHRS navx;
	
	DifferentialDrive drivetrain;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	frontLeft = RobotMap.frontLeft;
    	backLeft = RobotMap.backLeft;
    	frontRight = RobotMap.frontRight;
    	backRight = RobotMap.backRight;
    	SpeedControllerGroup left = new SpeedControllerGroup(frontLeft,backLeft);
    	SpeedControllerGroup right = new SpeedControllerGroup(frontRight,backRight);
    	drivetrain = new DifferentialDrive(left, right);
    	navx = RobotMap.ahrs;
    	
    	setDefaultCommand(new DriveCommand());
    }
    
    public void Drive(double y, double twist){
    	drivetrain.arcadeDrive(-y, -twist);
    }
    
//    public void driveStraight(double speed) {
//    	Drive(speed, output);
//    }
    
}

