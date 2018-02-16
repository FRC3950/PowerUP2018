/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3950.robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
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
	
	public static String switchClosePosition = "";
	public static String scalePosition = "";
	public static String switchFarPosition = "";
	
	public static String teamScale = "";
	public static String teamSwitchRight = "";
	public static String teamSwitchLeft = "";
	
	public static String ourFieldPosition = "";

	//public static Logger robotLogger = LoggerFactory.getLogger(Robot.class);
	
	public static FieldPositionAnalysis side = new FieldPositionAnalysis();
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	SendableChooser<Logger.LogLevel> logChooser = new SendableChooser<>();
	
	public static DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
	public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
	public static RampSubsystem rampSubsystem = new RampSubsystem();


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		
		m_chooser = new SendableChooser<Command>();
		logChooser = new SendableChooser<Logger.LogLevel>();
		
		logChooser.addObject("Info", Logger.LogLevel.info);
		logChooser.addObject("Debug", Logger.LogLevel.debug);
		logChooser.addObject("Trace", Logger.LogLevel.trace);
		
		m_chooser.addDefault("Turn Precise", new DriveTurnPreciseCommand(90));
		m_chooser.addObject("Scale Auto", new ScaleAutoCommand());
		m_chooser.addObject("Drive Straight", new DriveStraightCommand());
		//m_chooser.addObject("Straight + Scale Auto", new StraightScaleAutoCommand(27));
		m_chooser.addObject("EncoderNavx Drive", new EncoderNavX2AutoCommand(6));
		m_chooser.addObject("Command Group Auto Test", new TestAutoCommandGroup());
		m_chooser.addObject("Elevator Auto", new ElevatorPIDCommand(38));
		m_chooser.addObject("No Auto", null);
		
		SmartDashboard.putData("Auto mode", m_chooser);
		
		Robot.teamScale = SmartDashboard.getString("Can team do Scale Auto (Y/N)", null);
		Robot.teamSwitchRight = SmartDashboard.getString("Can team do Switch Right (Y/N)", null);
		
		Robot.ourFieldPosition = SmartDashboard.getString("Which position are we at (L/R/C)", null);
		
//		SmartDashboard.putNumber("P (drive straight)", 1);
//		SmartDashboard.putNumber("I (drive straight)", 0);
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
	
	/*

	private static void fieldPositionAnalysis() {
		String str = DriverStation.getInstance().getGameSpecificMessage();
		switchClosePosition = str.substring(0,1);
		scalePosition = str.substring(1,2);
		switchFarPosition = str.substring(2,3);
	}
	
	public static Command chooseAutoMode(String leftRightCenter, String rightSwitchAbility, String scaleAbility) {
		if(leftRightCenter.compareTo("C") == 0) {
			if(rightSwitchAbility.compareTo("N") == 0) {
				return new SwitchPositionAutoCommandGroup(Robot.switchClosePosition); //switch right auto
			} else {
				return new BaselineAutoCommandGroup();
			}
		} else {
			if (leftRightCenter.compareTo(Robot.scalePosition) == 0 || scaleAbility.compareTo("N") == 0) {
				return new ScalePositionAutoCommandGroup(leftRightCenter, Robot.scalePosition); //make one over-arching and pass in robotPOs and scalePos
			} else {
				if(leftRightCenter.compareTo(Robot.switchClosePosition) == 0) {
					return new SwitchPositionAutoCommandGroup(Robot.switchClosePosition); //pass in switch side AND robotPos
				} else {
					return new BaselineAutoCommandGroup();
				}		 
			}
		} 
	}
	*/
	
	
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
		//fieldPositionAnalysis();
		m_autonomousCommand = m_chooser.getSelected();//new ScaleAutoCommand();//m_chooser.getSelected();
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
		
		//m_autonomousCommand = Robot.chooseAutoMode(Robot.ourFieldPosition, Robot.teamSwitchRight, Robot.teamScale);
		
		//m_autonomousCommand = new EncoderNavX2AutoCommand();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
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
		Scheduler.getInstance().run();
		
		Logger.log(Logger.LogLevel.info, "Limit switch pressed is " + RobotMap.elevatorBottomLimitSwitch.get());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
