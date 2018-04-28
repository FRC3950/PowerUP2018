package org.usfirst.frc.team3950.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestSwitchAutoCommandGroup extends CommandGroup {

    public TestSwitchAutoCommandGroup(String switc) {
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
    	
     	if(switc.compareTo("R") == 0) {
     		addSequential(new EncoderNavX2AutoCommand(12.7));
     		addSequential(new BangBangElevatorCommand(24));//10.2 //move up to side of switch approx middle
    		addSequential(new DriveTurnPreciseCommand(-90)); //turn 90 degrees right toward switch //move elevator up 18 inches
    		//addSequential(new EncoderNavX2AutoCommand(1)); //.5 //move forward a bit more
    		addSequential(new IntakeOuttakeAutoCommand(-.5)); //outtake the cube into the switch
    }
}
}