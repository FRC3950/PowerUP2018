package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.FieldPositionAnalysis;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScalePositionLeftAutoCommandGroup extends CommandGroup {
	
	FieldPositionAnalysis analyzer;

    public ScalePositionLeftAutoCommandGroup() {
    	
    	analyzer = new FieldPositionAnalysis();
    	
    	//Command Group for when scale is on left side
    	if (analyzer.getScalePosition().compareTo("L") == 0) {
    		
    		//write left position to left scale code in here
    		
    		//These values are complete B.S. I made them up. Please change them during calibration.
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new DriveTurnPreciseCommand(90));
    		addSequential(new EncoderNavX2AutoCommand(8));
    	}
    	
    	//Command Group for when scale is on right side
    	if (analyzer.getScalePosition().compareTo("R") == 0) {
    		
    		//write left position to right scale code in here
    		
    		//These values are complete B.S. I made them up. Please change them during calibration.
    		addSequential(new EncoderNavX2AutoCommand(8));
    		addSequential(new DriveTurnPreciseCommand(-90));
    		addSequential(new EncoderNavX2AutoCommand(8));
    	}
    	
    }
}
