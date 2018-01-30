package org.usfirst.frc.team3950.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class RGBSensorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	I2C i2cBus;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	/*for(int i = 0; i < 128; i++) {
    		i2cBus = new I2C(I2C.Port.kOnboard, i);
    		if (!i2cBus.addressOnly()) {
    			System.out.println("The address of the RGB sensor is " + i);
    			break;
    		}
    		else {
    			System.out.println("Sensor not found at " + i);
    		}
    	}
    */
    	i2cBus = new I2C(I2C.Port.kOnboard, 0x12);
    	if(i2cBus.addressOnly()) {
    		System.out.println("Transaction aborted");
    	}
    	else {
    		System.out.println("Transaction successful");
    	}
    }
    
    public void readColor(byte[] buffer) {
    	if(i2cBus.write(0x00, 0x13)) {
    		System.out.println("Transaction aborted");	
    	} else {
    		System.out.println("Transaction Successful");
    		i2cBus.read(0x14, 6, buffer);
    		for(int i =0; i<buffer.length;i++) 
    			System.out.println(buffer[i]);
    	} 
    }
}

