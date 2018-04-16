package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Logger;
import org.usfirst.frc.team3950.robot.PIDOutputDistance;
import org.usfirst.frc.team3950.robot.PIDOutputYaw;
import org.usfirst.frc.team3950.robot.PIDSourceDistance;
import org.usfirst.frc.team3950.robot.PIDSourceYaw;
import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * PID command to drive the left and right side of a drive train a specific
 * distance using a PIDController.
 */
public class EncoderNavX2AutoCommand extends Command { 
  // You will need to adjust your PID constants
	//This is not a joke this is a last cry for help as everyday I code I come closer and closer to ending it all.
	//Every command prompt, every semi-colon, every click of the keyboard is one step closer to me leaving
	//The next person that tells me to code is coming with me
	//From Bryce
	//encoder values
	double encP = SmartDashboard.getNumber("P (distance)", .278);
	double encI = SmartDashboard.getNumber("I (distance)", 0.0001);
	double encD = SmartDashboard.getNumber("D (distance)", 0);
	double encF = SmartDashboard.getNumber("F (distance)", 0);
	
	//straight drive values
	double navxP = SmartDashboard.getNumber("P (drive straight)", .01);
	double navxI = SmartDashboard.getNumber("I (drive straight)", 0.0005);
	double navxD = SmartDashboard.getNumber("D (drive straight)", 0.15);
	double navxF = SmartDashboard.getNumber("F (drive straight)", 0);

  // Set to false once you are done tuning the PID
  //private static final boolean DEBUG = true;

  // PID controllers for each side of drive train
  private PIDController encPID;

  private PIDController navXPID;
  
  double encSumError = 0.0;
  double navXSumError = 0.0;

  // Drive subsystem that will expose motor control and distrance traveled

  // Used for diagnostic output to see how far each side was driven
  private double encOutput = Double.MAX_VALUE;
  private double navxOutput = Double.MAX_VALUE;
  
  PIDSourceDistance encSource;
  PIDSourceYaw navxSource;
  
	double maxSpeed = .75;
	double setpoint = 0;
  /**
   * Command to use PID control to drive a fixed distance.
   *
   * @param drive
   *          Drive subsystem to use.
   * @param leftM
   *          How far the left side of the drive should travel (meters).
   * @param rightM
   *          How far the right side of the drive should travel (meters).
   */
  public EncoderNavX2AutoCommand(double input) {
    //
    // Define PIDSource based on distance to travel
    //
	  setpoint = input;
	  
	  encSource = new PIDSourceDistance() {
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
      }
      
      @Override
      public PIDSourceType getPIDSourceType() {
        // Distance type PID
        return PIDSourceType.kDisplacement;
      }

      @Override
      public double pidGet() {
    	 Logger.log(Logger.LogLevel.info, "Count distance feet = " + Robot.drivetrainSubsystem.getCountDistanceFeet());
        return Robot.drivetrainSubsystem.getCountDistanceFeet();
      }
       	
    };

     navxSource = new PIDSourceYaw() {
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
      }

      @Override
      public PIDSourceType getPIDSourceType() {
        // Distance type PID
        return PIDSourceType.kDisplacement;
      }

      @Override
      public double pidGet() {
    	  System.out.println(RobotMap.ahrs.getYaw());
    	  return RobotMap.ahrs.getYaw();
      }
      
    };

    //
    // Define PID outputs to set drive power
    //
    PIDOutputDistance encOut = new PIDOutputDistance() {
      @Override
      public void pidWrite(double output) {
    	  encOutput = output;

  		  Logger.log(Logger.LogLevel.info, "Output Enc = " + output);
    	  encSumError = encSumError + encPID.getError();
      }
    };

    PIDOutputYaw navxOut = new PIDOutputYaw() {
      @Override
      public void pidWrite(double output) {
    	  navxOutput = output;
    	  Logger.log(Logger.LogLevel.info, "Output NavX = " + output);
      }
    };

    // Initialize PID controllers
    encPID = new PIDController(encP, encI, encD, encF, encSource, encOut);
    navXPID = new PIDController(navxP, navxI, navxD, navxF, navxSource, navxOut);
    
    // If debugging PID, then pollute dash board with some tuning values
//    if (DEBUG) {
//      SmartDashboard.putData("Left PID", encPID);
//      SmartDashboard.putData("Right PID", navXPID);
//    }
  }

  @Override
  protected void initialize() {
	  
	  encSumError = 0;
	  navXSumError = 0;
    // Save distance at start (I don't like zeroing encoder counts - but this is
    // an option as well)
	 Logger.log(Logger.LogLevel.info, "I am in EncNavX2 Init");
	 RobotMap.ahrs.reset();
	 Robot.drivetrainSubsystem.resetEncoders();
	  
	//from scaleAuto
	encSource.setPIDSourceType(PIDSourceType.kDisplacement);
	encPID.setInputRange(0f,  setpoint*1.1);
  	encPID.setOutputRange(0f, maxSpeed);
  	encPID.setPercentTolerance(5);
  	encPID.setContinuous(false);
  	encPID.setPID(encP, encI, encD, encF);
  	encPID.setSetpoint(setpoint);
  	encPID.enable();
  	
  	//from driveStraight
  	navxSource.setPIDSourceType(PIDSourceType.kDisplacement);
	RobotMap.ahrs.zeroYaw();
  	navXPID.setInputRange(-5.0f, 5.0f);
  	navXPID.setOutputRange(-0.5, 0.5);
  	navXPID.setPercentTolerance(5);
  	navXPID.setContinuous(false);
  	navXPID.setPID(navxP, navxI, navxD, navxF);
  	navXPID.setSetpoint(0);
  	navXPID.enable();
	  

  }

  @Override
  protected void execute() {
	  
	  //Logger.log(Logger.LogLevel.debug, "I am in EncNavX2 Execute");
	  
	  Logger.log(Logger.LogLevel.info, "nAVx Error is " + navXPID.getError());
	  Logger.log(Logger.LogLevel.info, "Enc Error is " + encPID.getError());
	  
	  // do something with the encOuptut and navxOutput 
	  if(encOutput != Double.MAX_VALUE && navxOutput != Double.MAX_VALUE) {
		  Robot.drivetrainSubsystem.Drive(-encOutput, navxOutput);
	  }  
  }

  @Override
  protected boolean isFinished() {
	  return encPID.onTarget();
	 //return isCanceled();
  }
  
  

  /** Shutdown PIDs and stop motors when ended/interrupted. */
  @Override
  protected void end() {
	Robot.drivetrainSubsystem.Drive(0, 0);
    encPID.disable();
    navXPID.disable();
  }

  /** Shutdown PIDs and stop motors when ended/interrupted. */
  @Override
  protected void interrupted() {
	  Robot.drivetrainSubsystem.Drive(0, 0);
	  encPID.disable();
	  navXPID.disable();
  }
}