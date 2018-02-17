package org.usfirst.frc.team3950.robot.commands;

//import org.usfirst.frc.team3950.robot.FieldPositionAnalysis;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchPositionAutoCommandGroup extends CommandGroup {
    	
    
    public void setLocation(String location) {
    	//Command Group for when switch is on left side
    	if (location.compareTo("L") == 0) {
    		
    		//write right position to left switch code in here
    		
    		//These values are complete B.S. I made them up. Please change them during calibration.
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new DriveTurnPreciseCommand(90));
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new ElevatorPIDCommand(29));
    		addSequential(new IntakeOuttakeAutoCommand(-.5));
    	}
    	
    	//Command Group for when switch is on right side
    	if (location.compareTo("R") == 0) {
    		
    		//write right position to right switch code in here
    		
    		//These values are complete B.S. I made them up. Please change them during calibration.
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new DriveTurnPreciseCommand(-90));
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new ElevatorPIDCommand(29));
    		addSequential(new IntakeOuttakeAutoCommand(-.5));
    	}
    }
    
    public SwitchPositionAutoCommandGroup(String switchPos) {
    	
    	if(switchPos.compareTo("L") == 0) {
    		
    		//write right position to left switch code in here
    		
    		//These values are complete B.S. I made them up. Please change them during calibration.
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new DriveTurnPreciseCommand(90));
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new ElevatorPIDCommand(29));
    		addSequential(new IntakeOuttakeAutoCommand(-.5));
    	} else {
    		
    		//write right position to right switch code in here
    		
    		//These values are complete B.S. I made them up. Please change them during calibration.
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new DriveTurnPreciseCommand(-90));
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new ElevatorPIDCommand(29));
    		addSequential(new IntakeOuttakeAutoCommand(-.5));
    		
    	}
    	
    }
	
}
