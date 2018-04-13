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
        		
        		//left field left switch
    			System.out.println("left switch");
        		addSequential(new ScaleAutoCommand(11.79)); //10.2 //move up to side of switch approx middle
        		addSequential(new DriveTurnPreciseCommand(90)); //turn 90 degrees right toward switch
        		addSequential(new BangBangElevatorCommand(24)); //move elevator up 18 inches
        		//addSequential(new EncoderNavX2AutoCommand(1)); //.5 //move forward a bit more
        		addSequential(new IntakeOuttakeAutoCommand(-.5)); //outtake the cube into the switch
        	}
        	
        	//Command Group for when scale is on right side
        	else if (switchSide.compareTo("R") == 0) {
        		
        		System.out.println("right switch");
        		//write left position to right scale code in here
        		
        		//These values are complete B.S. I made them up. Please change them during calibration.
        		addSequential(new ScaleAutoCommand(18.6));
        		addSequential(new DriveTurnPreciseCommand(90));
        		addSequential(new ScaleAutoCommand(11.59)); //13.83
        		addSequential(new DriveTurnPreciseCommand(90));
        		addSequential(new BangBangElevatorCommand(24));
        		addSequential(new IntakeOuttakeAutoCommand(-.5));
        		//addSequential(new IntakeOuttakeAutoCommand(-.5));

        	} else {
        		Logger.log(Logger.LogLevel.info, "SwitchPositionLeftAutoCommand BIG ERROR - string passed in is equal to:" + switchSide);
        	}
        
    	} else if (fieldPosition == 3) {
    		
    			if (switchSide.compareTo("L") == 0) {
	    		
    				//write right position to left scale code in here
	    		
    				//These values are complete B.S. I made them up. Please change them during calibration.
    				addSequential(new ScaleAutoCommand(18.6));
            		addSequential(new DriveTurnPreciseCommand(-90));
            		addSequential(new ScaleAutoCommand(11.59)); //13.83
            		addSequential(new DriveTurnPreciseCommand(-90));
            		addSequential(new BangBangElevatorCommand(24));
            		addSequential(new IntakeOuttakeAutoCommand(-.5));
    			}
	    	
    			//Command Group for when scale is on right side
    			if (switchSide.compareTo("R") == 0) {
	    		
    				//write right position to right scale code in here
	    		
    				//These values are complete B.S. I made them up. Please change them during calibration.
    				addSequential(new ScaleAutoCommand(11.79)); 
            		addSequential(new DriveTurnPreciseCommand(-90)); 
            		addSequential(new BangBangElevatorCommand(24));
            		//addSequential(new EncoderNavX2AutoCommand(1)); //.5 //move forward a bit more
            		addSequential(new IntakeOuttakeAutoCommand(-.5));  
    			}
    		} 
    	}
	}
