package org.usfirst.frc.team3950.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class FieldPositionAnalysis {
	
	String str;
	DriverStation ds;
	
	FieldPositionAnalysis(){
		DriverStation.getInstance();
		str = ds.getGameSpecificMessage();
		
	}
	
	public String getSwitchBposition() {
		String side;
		if(str.substring(0, 1).compareTo("L")==0) {
			side = "The side is " + str.substring(0,1);
		}else {
			side = "The side is Right"; 
		}
		return side;
	}
}
