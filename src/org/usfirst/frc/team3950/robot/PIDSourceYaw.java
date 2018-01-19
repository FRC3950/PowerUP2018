package org.usfirst.frc.team3950.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDSourceYaw implements PIDSource {
	PIDSourceType type;
	AHRS navx;
	public PIDSourceYaw() {
		navx = RobotMap.ahrs;
	}
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		type = pidSource;
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public double pidGet() {
		return navx.getAngle();
	}
	
}
