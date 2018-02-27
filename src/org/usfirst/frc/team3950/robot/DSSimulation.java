package org.usfirst.frc.team3950.robot;

import java.util.Random;

public class DSSimulation {

	public static String getSide(String[] side) {
		int sideNum = 0;
		Random random = new Random();
		sideNum = random.nextInt(1);
		
		return side[sideNum];
		
	}
}
