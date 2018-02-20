/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3950.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

//import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	

	public static WPI_TalonSRX frontLeft = new WPI_TalonSRX(15);
	public static WPI_TalonSRX frontRight = new WPI_TalonSRX(0);
	public static WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(2);
	
	public static WPI_VictorSPX backLeft = new WPI_VictorSPX(14); 
	public static WPI_VictorSPX backRight = new WPI_VictorSPX(1);
	public static WPI_VictorSPX elevatorMotorFollower = new WPI_VictorSPX (3);
	public static WPI_VictorSPX intakeLeftMotor = new WPI_VictorSPX(13);
	public static WPI_VictorSPX intakeRightMotor = new WPI_VictorSPX(10);
	public static WPI_VictorSPX intakeVerticalMotor = new WPI_VictorSPX(12);
	
	public static DigitalInput elevatorBottomLimitSwitch = new DigitalInput(0); 
	public static DigitalInput elevatorTopLimitSwitch = new DigitalInput(1); 
	public static DigitalInput intakeCubeOneOptical = new DigitalInput(2); 
	public static DigitalInput intakeBottomLimitSwitch = new DigitalInput(3); 
	public static DigitalInput intakeTopLimitSwitch = new DigitalInput(4);
	//public static DigitalInput intakeCubeTwoOptical = new DigitalInput(5);
	
	public static DoubleSolenoid elevatorBrakeSolenoid = new DoubleSolenoid(1, 0);
	public static DoubleSolenoid intakeHorizontalLeft = new DoubleSolenoid (2, 3);
	public static DoubleSolenoid intakeHorizontalRight = new DoubleSolenoid (4, 5);
	public static DoubleSolenoid rampSolenoid = new DoubleSolenoid(6, 7);
	public static DoubleSolenoid elevatorShiftSolenoid = new DoubleSolenoid(1, 0, 1);
	

	
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP);
	
	public static SpeedControllerGroup left = new SpeedControllerGroup(frontLeft,backLeft);
	public static SpeedControllerGroup right = new SpeedControllerGroup(frontRight,backRight);
	
	
}
