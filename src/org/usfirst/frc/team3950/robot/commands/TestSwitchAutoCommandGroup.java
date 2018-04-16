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
     		addSequential(new BangBangElevatorCommand(20));
    		addSequential(new EncoderNavX2AutoCommand(1));
    		addSequential(new IntakeOuttakeAutoCommand(-1));
    	} else {
    		addSequential(new EncoderNavX2AutoCommand(8.35));
    	}
    }
}
