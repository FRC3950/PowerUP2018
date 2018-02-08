package org.usfirst.frc.team3950.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDSourceDistance implements PIDSource {
	PIDSourceType type;

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		type = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return Robot.drivetrainSubsystem.getCountDistanceFeet();
	}
	
	public void reset() {
		Robot.drivetrainSubsystem.resetEncoders();
	}

}
