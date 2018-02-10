package org.usfirst.frc.team3950.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class YawPIDController extends PIDController{

	public YawPIDController(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output) {
		super(Kp, Ki, Kd, Kf, source, output);
		// TODO Auto-generated constructor stub
	}

}
