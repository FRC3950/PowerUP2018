package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;
import org.usfirst.frc.team3950.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorUndoBrakeCommand extends Command {

    public ElevatorUndoBrakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (RobotMap.elevatorBrakeSolenoid.get() == DoubleSolenoid.Value.kForward) {
    		Robot.elevatorSubsystem.undoBrake();
    	} else if(RobotMap.elevatorBrakeSolenoid.get() == DoubleSolenoid.Value.kReverse){
    		Robot.elevatorSubsystem.elevatorBrake();
    	} else {
    		Robot.elevatorSubsystem.undoBrake();
    	}
    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
