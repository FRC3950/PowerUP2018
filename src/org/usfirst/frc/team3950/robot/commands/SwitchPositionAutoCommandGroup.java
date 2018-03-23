package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchPositionAutoCommandGroup extends CommandGroup {

    public SwitchPositionAutoCommandGroup(int fieldPosition, String switchSide) {
    	
    	System.out.println("In switch position auto command noelle suks ");
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	if(fieldPosition == 1) {
    		
    		if (switchSide.compareTo("L") == 0) {
        		
        		//write left position to left scale code in here
    			System.out.println("left switch");
        		//These values are complete B.S. I made them up. Please change them during calibration.
        		addSequential(new EncoderNavX2AutoCommand(27));
        		addSequential(new DriveTurnPreciseCommand(90));
        		addSequential(new EncoderNavX2AutoCommand(8));
        		addSequential(new ElevatorPIDCommand(79));
        		addSequential(new IntakeOuttakeAutoCommand(-.5));
        	}
        	
        	//Command Group for when scale is on right side
        	else if (switchSide.compareTo("R") == 0) {
        		
        		System.out.println("right switch");
        		//write left position to right scale code in here
        		
        		//These values are complete B.S. I made them up. Please change them during calibration.
        		addSequential(new EncoderNavX2AutoCommand(27));
        		addSequential(new DriveTurnPreciseCommand(-90));
        		addSequential(new EncoderNavX2AutoCommand(8));
        		addSequential(new ElevatorPIDCommand(79));
        		//addSequential(new IntakeOuttakeAutoCommand(-.5));
        	} else {
        		Logger.log(Logger.LogLevel.info, "SwitchPositionLeftAutoCommand BIG ERROR - string passed in is equal to:" + switchSide);
        	}
        
    	} else if (fieldPosition == 3) {
    		
    			if (switchSide.compareTo("L") == 0) {
	    		
    				//write right position to left scale code in here
	    		
    				//These values are complete B.S. I made them up. Please change them during calibration.
    				addSequential(new EncoderNavX2AutoCommand(27));
    				addSequential(new DriveTurnPreciseCommand(90));
    				addSequential(new EncoderNavX2AutoCommand(8));
    				addSequential(new ElevatorPIDCommand(79));
            		addSequential(new IntakeOuttakeAutoCommand(-.5));
    			}
	    	
    			//Command Group for when scale is on right side
    			if (switchSide.compareTo("R") == 0) {
	    		
    				//write right position to right scale code in here
	    		
    				//These values are complete B.S. I made them up. Please change them during calibration.
    				addSequential(new EncoderNavX2AutoCommand(27));
    				addSequential(new DriveTurnPreciseCommand(-90));
    				addSequential(new EncoderNavX2AutoCommand(8));
    				addSequential(new ElevatorPIDCommand(79));
            		addSequential(new IntakeOuttakeAutoCommand(-.5));
    			}
    		} else if(fieldPosition == 2) {
    			if (switchSide.compareTo("L") == 0) {
    	    		
    				//write right position to left scale code in here
	    		
    				//These values are complete B.S. I made them up. Please change them during calibration.
    				addSequential(new EncoderNavX2AutoCommand(27));
    				addSequential(new DriveTurnPreciseCommand(90));
    				addSequential(new EncoderNavX2AutoCommand(8));
    				addSequential(new ElevatorPIDCommand(79));
            		addSequential(new IntakeOuttakeAutoCommand(-.5));
    			}
	    	
    			//Command Group for when scale is on right side
    			if (switchSide.compareTo("R") == 0) {
	    		
    				//write right position to right scale code in here
	    		
    				//These values are complete B.S. I made them up. Please change them during calibration.
    				addSequential(new EncoderNavX2AutoCommand(27));
    				addSequential(new DriveTurnPreciseCommand(-90));
    				addSequential(new EncoderNavX2AutoCommand(8));
    				addSequential(new ElevatorPIDCommand(79));
            		addSequential(new IntakeOuttakeAutoCommand(-.5));
    			}

    		}
    	}
	}
