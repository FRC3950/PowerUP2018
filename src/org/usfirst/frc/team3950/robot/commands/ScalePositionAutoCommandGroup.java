package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScalePositionAutoCommandGroup extends CommandGroup {

    public ScalePositionAutoCommandGroup(int fieldPosition, String scaleSide) {
    	
    	System.out.println("In scale position auto command noelle suks ");
    	
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
    		
    		if (scaleSide.compareTo("L") == 0) {
        		
        		//write left position to left scale code in here
    			System.out.println("left scale, left field");
        		//These values are complete B.S. I made them up. Please change them during calibration.
    			addSequential(new EncoderNavX2AutoCommand(24.5));
    			addSequential(new BangBangElevatorCommand(75));
        		addSequential(new DriveTurnPreciseCommand(90));
        		//addSequential(new EncoderNavX2AutoCommand(0.78));
        		addSequential(new IntakeOuttakeAutoCommand(-.5));
        	}
        	
        	//Command Group for when scale is on right side
        	else if (scaleSide.compareTo("R") == 0) {
        		
        		System.out.println("right scale, left field");
        		//write left position to right scale code in here
        		
        		//These values are complete B.S. I made them up. Please change them during calibration.
        		addSequential(new EncoderNavX2AutoCommand(17.04));
        		addSequential(new DriveTurnPreciseCommand(90));
        		addSequential(new EncoderNavX2AutoCommand(17.5));
        		addSequential(new DriveTurnPreciseCommand(-90));
        		addSequential(new EncoderNavX2AutoCommand(5.45));
        		addSequential(new BangBangElevatorCommand(79));
        		addSequential(new DriveTurnPreciseCommand(-90));
        		addSequential(new IntakeOuttakeAutoCommand(-.5));
        		//addSequential(new IntakeOuttakeAutoCommand(-.5));
        	} else {
        		Logger.log(Logger.LogLevel.info, "ScalePositionLeftAutoCommand BIG ERROR - string passed in is equal to:" + scaleSide);
        	}
        
    	} else if (fieldPosition == 3) {
    		
    			if (scaleSide.compareTo("L") == 0) {
	    		
    				System.out.println("left scale, right field");
    				//write right position to left scale code in here
	    		
    				//These values are complete B.S. I made them up. Please change them during calibration.
    				addSequential(new EncoderNavX2AutoCommand(17.04));
            		addSequential(new DriveTurnPreciseCommand(-90));
            		addSequential(new EncoderNavX2AutoCommand(17.5));
            		addSequential(new DriveTurnPreciseCommand(90));
            		addSequential(new EncoderNavX2AutoCommand(5.45));
            		addSequential(new BangBangElevatorCommand(75));
            		addSequential(new DriveTurnPreciseCommand(90));
            		addSequential(new IntakeOuttakeAutoCommand(-.5));
    			}
	    	
    			//Command Group for when scale is on right side
    			if (scaleSide.compareTo("R") == 0) {
    				
    				System.out.println("right scale, right field");
	    		
    				//write right position to right scale code in here
	    		
    				//These values are complete B.S. I made them up. Please change them during calibration.
    				addSequential(new EncoderNavX2AutoCommand  (24.5));
        			addSequential(new BangBangElevatorCommand(75));
            		addSequential(new DriveTurnPreciseCommand(-90));
            		//addSequential(new EncoderNavX2AutoCommand(0.78));
            		addSequential(new IntakeOuttakeAutoCommand(-.5));
    			}
    		} 
    	}
	}
