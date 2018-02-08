package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.FieldPositionAnalysis;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchPositionMiddleAutoCommandGroup extends CommandGroup {
    	
    FieldPositionAnalysis analyzer;

    public SwitchPositionMiddleAutoCommandGroup() {
    	
    	analyzer = new FieldPositionAnalysis();
    	
    	//Command Group for when switch is on left side
    	if (analyzer.getSwitchClosePosition().compareTo("L") == 0) {
    		
    		//write right position to left switch code in here
    		
    		//These values are complete B.S. I made them up. Please change them during calibration.
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new DriveTurnPreciseCommand(90));
    		addSequential(new EncoderNavX2AutoCommand(8));
    	}
    	
    	//Command Group for when switch is on right side
    	if (analyzer.getSwitchClosePosition().compareTo("R") == 0) {
    		
    		//write right position to right switch code in here
    		
    		//These values are complete B.S. I made them up. Please change them during calibration.
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new DriveTurnPreciseCommand(-90));
    		addSequential(new EncoderNavX2AutoCommand(8));
    	}
    	
    }
	
}
