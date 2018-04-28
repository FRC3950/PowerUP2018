/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3950.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.usfirst.frc.team3950.robot.commands.ExampleCommand;
//import org.usfirst.frc.team3950.robot.commands.IntakeCommand;
import org.usfirst.frc.team3950.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team3950.robot.subsystems.ElevatorSubsystem;
//import org.usfirst.frc.team3950.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3950.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team3950.robot.subsystems.IntakeVerticalSubsystem;
import org.usfirst.frc.team3950.robot.subsystems.RampSubsystem;
import org.usfirst.frc.team3950.robot.Logger.LogLevel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.usfirst.frc.team3950.robot.commands.*;
//import org.usfirst.frc.team3950.robot.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.  
 */
public class Robot extends TimedRobot {

	public static OI oi;
	
	//XboxController controller;
	
	public static String switchClosePosition = "";
	public static String scalePosition = "";
	public static String switchFarPosition = "";
	public static String fieldPos = "";
	
	public static Object teamScale = false;
	public static Object teamSwitchRight = false;
	public static String teamSwitchLeft = "";
	public static Object teamSwitch = false;
	
	public static int ourFieldPosition = 0;
	
	//public static String intakeIn = "Cube is not Intook";
	
	//public static Logger robotLogger = LoggerFactory.getLogger(Robot.class);
	
	public static FieldPositionAnalysis side = new FieldPositionAnalysis();
	
	Command m_autonomousCommand;
	//SendableChooser<Command> autoChooser = new SendableChooser<>();
	SendableChooser<Logger.LogLevel> logChooser = new SendableChooser<>();
	SendableChooser<String> typeChooser = new SendableChooser<>();
	SendableChooser<String> fieldPosition = new SendableChooser<>();
	SendableChooser teamScaleAuto = null;
	SendableChooser teamSwitchRightAuto = null;
	SendableChooser teamSwitchAuto = null;
	
	
	public static DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
	public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static IntakeVerticalSubsystem intakeVerticalSubsystem = new IntakeVerticalSubsystem();
	public static ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
	public static RampSubsystem rampSubsystem = new RampSubsystem();
	
	public static String[] sides = {"L", "R"};


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		//Robot.elevatorSubsystem.undoBrake();
		

		/*
		teamScaleAuto = new SendableChooser();
		teamScaleAuto.addDefault("Yes", true);
		teamScaleAuto.addObject("No", false);
		teamScaleAuto.setName("teamScaleAuto");
		SmartDashboard.putData("teamScaleAuto", teamScaleAuto);
		teamScale = teamScaleAuto.getSelected();
		System.out.println(teamScale);
		//SmartDashboard.putString("Can team do scale auto?", "");
		
		teamSwitchRightAuto = new SendableChooser();
		teamSwitchRightAuto.addDefault("Yes", true);
		teamSwitchRightAuto.addObject("No", false);
		teamSwitchRightAuto.setName("teamSwitchRightAuto");
		SmartDashboard.putData("teamSwitchRightAuto", teamSwitchRightAuto);
		teamSwitchRight = teamSwitchRightAuto.getSelected();
		System.out.println(teamSwitchRight);
		//SmartDashboard.putString("Can team do switch right auto?", "");
		
		teamSwitchAuto = new SendableChooser();
		teamSwitchAuto.addDefault("Yes", true);
		teamSwitchAuto.addObject("No", false);
		teamSwitchAuto.setName("teamSwitchAuto");
		SmartDashboard.putData("teamSwitchAuto", teamSwitchAuto);
		teamSwitch = teamSwitchAuto.getSelected();
		*/
		
		typeChooser = new SendableChooser<String>();
		typeChooser.addDefault("Switch", new String("Switch"));
		typeChooser.addObject("Scale", new String("Scale"));
		typeChooser.addObject("Baseline", new String("Baseline"));
		typeChooser.addObject("No Auto", new String("No Auto"));
		typeChooser.addObject("Fun Test Center Auto", ("Fun Test Center Auto"));
		SmartDashboard.putData("Auto Mode", typeChooser);
		
		
		fieldPosition = new SendableChooser<String>();
		fieldPosition.addDefault("Left", new String("Left"));
		fieldPosition.addObject("Right", new String("Right"));
		fieldPosition.addObject("Center", new String("Center"));
		//fieldPosition.setName("fieldPosition");
		SmartDashboard.putData("fieldPosition", fieldPosition);
		
		//ourFieldPosition = (String) fieldPosition.getSelected();
		//SmartDashboard.putString("What is our field position?", "");
		
		//
		
		//autoChooser = new SendableChooser<Command>();
		
		
		/*
		autoChooser.addDefault("Turn Precise", new DriveTurnPreciseCommand(90));
		autoChooser.addObject("Scale Auto", new ScaleAutoCommand());
		autoChooser.addObject("Drive Straight", new DriveStraightCommand());
		//m_chooser.addObject("Straight + Scale Auto", new StraightScaleAutoCommand(27));
		autoChooser.addObject("EncoderNavx Drive", new EncoderNavX2AutoCommand(6));
		autoChooser.addObject("Command Group Auto Test", new TestAutoCommandGroup());
		autoChooser.addObject("Elevator Auto", new ElevatorPIDCommand(38));
		autoChooser.addObject("Outtake Auto Test", new IntakeOuttakeAutoCommand(-.5));
		autoChooser.addObject("Intake Auto Test", new IntakeOuttakeAutoCommand(.5));
		autoChooser.addObject("No Auto", null);
		SmartDashboard.putData("Auto mode", autoChooser);
		*/
		
		SmartDashboard.putNumber("P (drive straight)", 0.032);
		SmartDashboard.putNumber("I (drive straight)", 0);
		
		SmartDashboard.putNumber("P (distance)", 0.278);
		SmartDashboard.putNumber("I (distance)", 0.0001);
		
		
		SmartDashboard.putNumber("P (elevator)", 0.008);
		SmartDashboard.putNumber("I (elevator)", 0.00001);
		SmartDashboard.putNumber("D (elevator)", 0.0001);
		
		RobotMap.elevatorBrakeSolenoid.set(DoubleSolenoid.Value.kOff);
		RobotMap.intakeHorizontalLeft.set(DoubleSolenoid.Value.kOff);
		RobotMap.intakeHorizontalRight.set(DoubleSolenoid.Value.kOff);
		RobotMap.rampSolenoid.set(DoubleSolenoid.Value.kOff);
		RobotMap.elevatorShiftSolenoid.set(DoubleSolenoid.Value.kOff);
		RobotMap.elevatorSecondaryBrakeSolenoid.set(DoubleSolenoid.Value.kForward);
		
//		SmartDashboard.putNumber("D (drive straight)", 0);
//		SmartDashboard.putNumber("F (drive straight)", 0);
//		SmartDashboard.putNumber("Speed", -0.75);
		
		//robotLogger.info("Robot properly initialized.");
		
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	
	
	//for elimination / final matches only
	
	/*
	public static Command chooseAutoModeElim(int leftRightCenter, Object rightSwitchAbility, Object scaleAbility) {
		System.out.println("in the method elim");
		if(leftRightCenter == 2) {
			if(rightSwitchAbility.equals(false)) {
				return new SwitchPositionAutoCommandGroup(leftRightCenter, Robot.switchClosePosition); //switch right auto
			} else {
				return new BaselineAutoCommandGroup();
			}
		} else {
			if ((leftRightCenter == 1 && Robot.scalePosition.compareTo("L") == 0 || leftRightCenter == 3 && Robot.scalePosition.compareTo("R") == 0) || (scaleAbility.equals(false))) {
				return new ScalePositionAutoCommandGroup(leftRightCenter, Robot.scalePosition); //make one over-arching and pass in robotPOs and scalePos
			} else {
				if((leftRightCenter == 1 && Robot.switchClosePosition.compareTo("L") == 0) || 
						(leftRightCenter == 3 && Robot.switchClosePosition.compareTo("R") == 0)) {
					return new SwitchPositionAutoCommandGroup(leftRightCenter, Robot.switchClosePosition); //pass in switch side AND robotPos
				} else {
					return new BaselineAutoCommandGroup();
				}		 
			}
		} 
	}
	
	//for qualification matches only
	
	public static Command chooseAutoModeQual(int leftRightCenter, Object rightSwitchAbility, Object scaleAbility, Object switchAbility) {
		System.out.println("in the method qual");
		if(leftRightCenter == 2) {
			if(rightSwitchAbility.equals(false)) {
				return new SwitchPositionAutoCommandGroup(leftRightCenter, Robot.switchClosePosition); //switch right auto
			} else {
				return new BaselineAutoCommandGroup();
			}
		} else {
			if ((leftRightCenter == 1 && Robot.switchClosePosition.compareTo("L") == 0 || leftRightCenter == 3 && Robot.switchClosePosition.compareTo("R") == 0) || (switchAbility.equals(false))) {
				return new SwitchPositionAutoCommandGroup(leftRightCenter, Robot.switchClosePosition); //make one over-arching and pass in robotPOs and scalePos
			} else {
				if((leftRightCenter == 1 && Robot.scalePosition.compareTo("L") == 0) || 
						(leftRightCenter == 3 && Robot.scalePosition.compareTo("R") == 0)) {
					return new ScalePositionAutoCommandGroup(leftRightCenter, Robot.scalePosition); //pass in switch side AND robotPos
				} else {
					return new BaselineAutoCommandGroup();
				}		 
			}
		} 
	}
	
	

	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		//Robot.elevatorSubsystem.undoSecondaryBrake();
		RobotMap.elevatorSecondaryBrakeSolenoid.set(DoubleSolenoid.Value.kForward);

		//fieldPositionAnalysis();
		switchClosePosition = DriverStation.getInstance().getGameSpecificMessage().substring(0, 1);//DSSimulation.getSide(sides);//str.substring(0,1);
		System.out.println(switchClosePosition);
		scalePosition = DriverStation.getInstance().getGameSpecificMessage().substring(1, 2);//str.substring(1,2);
		System.out.println(scalePosition);
		switchFarPosition = DriverStation.getInstance().getGameSpecificMessage().substring(2, 3);//str.substring(2,3);
		System.out.println(switchFarPosition);
		
		
		if(fieldPosition.getSelected().compareTo("Left") == 0) {
			Robot.ourFieldPosition = 1;
		} else if(fieldPosition.getSelected().compareTo("Center") == 0) {
			Robot.ourFieldPosition = 2;
		} else {
			Robot.ourFieldPosition = 3;
		}
		
		
		
		if(typeChooser.getSelected().compareTo("Switch") == 0) {
			m_autonomousCommand = new SwitchPositionAutoCommandGroup(Robot.ourFieldPosition, Robot.switchClosePosition);
		} else if(typeChooser.getSelected().compareTo("Scale") == 0) {
			m_autonomousCommand = new ScalePositionAutoCommandGroup(Robot.ourFieldPosition, Robot.scalePosition);
		} else if(typeChooser.getSelected().compareTo("Baseline") == 0) {
			m_autonomousCommand = new BaselineAutoCommandGroup();
		} else if(typeChooser.getSelected().compareTo("No Auto") == 0) {
			m_autonomousCommand = null;
		} else if(typeChooser.getSelected().compareTo("Fun Test Center Auto") == 0) {
			m_autonomousCommand = new TestSwitchAutoCommandGroup(Robot.switchClosePosition);
		}
		
		
		//m_autonomousCommand = new TestSwitchAutoCommandGroup("R");
		//m_autonomousCommand = new DriveTurnPreciseCommand(-90);
		//m_autonomousCommand = new BaselineAutoCommandGroup();
		//m_autonomousCommand = typeChooser.getSelected();//new ScaleAutoCommand();//m_chooser.getSelected();
		//m_autonomousCommand = new SwitchPositionAutoCommandGroup(3, Robot.switchClosePosition);
		
		//System.out.println(teamScale);
		//System.out.println(teamSwitchRight);
		//System.out.println(fieldPosition);
		
		/*
		try {
			if (m_autonomousCommand.getClass() == Class.forName("org.usfirst.frc.team3950.robot.commands.ScalePositionLeftAutoCommandGroup")) {
				((ScalePositionLeftAutoCommandGroup)m_autonomousCommand).setLocation(Robot.scalePosition);
			} 
			else if (m_autonomousCommand.getClass() == Class.forName("org.usfirst.frc.team3950.robot.commands.ScalePositionRightAutoCommandGroup")) {
				((ScalePositionRightAutoCommandGroup)m_autonomousCommand).setLocation(Robot.scalePosition);
			}
			else if(m_autonomousCommand.getClass() == Class.forName("org.usfirst.frc.team3950.robot.commands.SwitchPositionMiddleAutoCommandGroup")) {
				((SwitchPositionAutoCommandGroup)m_autonomousCommand).setLocation(Robot.switchClosePosition);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//m_autonomousCommand = chooseAutoModeElim(Robot.ourFieldPosition, Robot.teamSwitchRight, Robot.teamScale);
		//m_autonomousCommand = chooseAutoModeQual(Robot.ourFieldPosition, Robot.teamSwitchRight, Robot.teamScale, Robot.teamSwitch);
		
		//m_autonomousCommand = new EncoderNavX2AutoCommand();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule
		//the autonomous command (example)
		//robotLogger.info("I am in autoInit yay");
		
		/*
		
		SmartDashboard.putString("Switch A side is ", side.getSwitchClosePosition());
		SmartDashboard.putString("Scale side is ", side.getScalePosition());
		SmartDashboard.putString("Switch B side is ", side.getSwitchFarPosition());
		*/
		
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
//		robotLogger.info("I am in autoPeriodic yay");
	}

	@Override
	public void teleopInit() {
		//RobotMap.ahrs.reset();
		
		RobotMap.elevatorSecondaryBrakeSolenoid.set(DoubleSolenoid.Value.kForward);

		//Robot.elevatorSubsystem.undoSecondaryBrake();
		
		Logger.loggerLogLevel = logChooser.getSelected();
		if(Logger.loggerLogLevel == null) {
			Logger.loggerLogLevel = Logger.LogLevel.info;
		}
		
		Logger.log(Logger.LogLevel.info, "Hello World info");
		Logger.log(Logger.LogLevel.debug, "Hello World debug");
		Logger.log(Logger.LogLevel.trace, "Hello World trace");
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		//robotLogger.info("I am in teleopInit (be careful this is an iStripper  virus)");
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		new DriveCommand().start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
	//	robotLogger.info("I am in teleopPeriodic");
		
		//System.out.println("field chooser = " + fieldPosition.getSelected());
		Scheduler.getInstance().run();
		
		
		SmartDashboard.putBoolean("Is Cube Intooken", Robot.intakeSubsystem.boxIn()); 
		
		SmartDashboard.putBoolean("Is Elevator at Top", Robot.elevatorSubsystem.topGetter());
		SmartDashboard.putBoolean("Is Elevator at Bottom", Robot.elevatorSubsystem.bottomGetter());
		
		//System.out.println("yaw " + RobotMap.ahrs.getYaw());
		
		
//		System.out.println("limit switch bottom elevator " + RobotMap.elevatorBottomLimitSwitch.get());
//		System.out.println("limit switch top elevator " + RobotMap.elevatorTopLimitSwitch.get());
//		System.out.println("limit switch bottom intake " + RobotMap.intakeBottomLimitSwitch.get()); 
//		System.out.println("limit switch top intake " + RobotMap.intakeTopLimitSwitch.get());
		
		
		//System.out.println("digital io two is " + RobotMap.intakeCubeTwoOptical.get());
		
//		controller = new XboxController(1);
//		System.out.println("controller value is " + controller.getTriggerAxis(Hand.kRight));
		
		//Logger.log(Logger.LogLevel.info, "Limit switch pressed is " + RobotMap.elevatorBottomLimitSwitch.get());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	
	@Override
	public void testPeriodic() {
	}
}