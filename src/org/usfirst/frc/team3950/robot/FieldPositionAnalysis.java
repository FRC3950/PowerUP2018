package org.usfirst.frc.team3950.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class FieldPositionAnalysis {
	
	String str;
	DriverStation ds;
	
	FieldPositionAnalysis(){
		DriverStation.getInstance();
		str = ds.getGameSpecificMessage();
		
	}
	
	public String getSwitchAPosition() {
		String side;
		if(str.substring(0, 1).compareTo("L")==0) {
			side = "Left";
		} else {
			side = "Right"; 
		}
		return side;
	}
	
	public String getScalePosition() {
		String side;
		if(str.substring(1,2).compareTo("L")==0) {
			side = "Left";
		} else {
			side = "Right";
		}
		return side;
	}
	
	public String getSwitchBPosition() {
		String side;
		if(str.substring(2,3).compareTo("L")==0) {
			side = "Left";
		} else {
			side = "Right";
		}
		return side;
	}
}
