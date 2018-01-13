package org.usfirst.frc.team3950.robot.subsystems;

import org.usfirst.frc.team3950.robot.RobotMap;
import org.usfirst.frc.team3950.robot.commands.DriveCommand;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands
	
	double output = 0;
	
	TalonSRX frontLeft;
	TalonSRX backLeft;
	TalonSRX frontRight;
	TalonSRX backRight;
	AHRS navx;
	
	DifferentialDrive drivetrain;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	frontLeft = RobotMap.frontLeft;
    	backLeft = RobotMap.backLeft;
    	frontRight = RobotMap.frontRight;
    	backRight = RobotMap.backRight;
    	
    	navx = RobotMap.ahrs;
    	
    	setDefaultCommand(new DriveCommand());
    }
    
    public void Drive(double y, double twist){
    	drivetrain.arcadeDrive(-y, -twist);
    }
    
    public void driveStraight(double speed) {
    	Drive(speed, output);
    }
    
}

