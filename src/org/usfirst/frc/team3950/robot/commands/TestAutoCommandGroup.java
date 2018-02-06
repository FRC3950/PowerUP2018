package org.usfirst.frc.team3950.robot.commands;

import org.usfirst.frc.team3950.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAutoCommandGroup extends CommandGroup {

    public TestAutoCommandGroup() {
    	
    	requires(Robot.drivetrainSubsystem);
    	
    	addSequential(new EncoderNavX2AutoCommand(8));
    	addSequential(new DriveTurnPreciseCommand(180));
    	//addSequential(new DriveTurnPreciseCommand(90));
    	addSequential(new EncoderNavX2AutoCommand(8));
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
    }
}
