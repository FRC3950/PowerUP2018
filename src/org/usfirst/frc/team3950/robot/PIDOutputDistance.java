package org.usfirst.frc.team3950.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDOutputDistance implements PIDOutput {

	double outsource = 0;
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		outsource = output;
	}
	
	public double pidGet() {
		return outsource;
	}
}
