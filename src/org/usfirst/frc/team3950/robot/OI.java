/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Fedex packages
package org.usfirst.frc.team3950.robot;
import org.usfirst.frc.team3950.robot.commands.*;

//Control packages 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//Control packages end

//Package end

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{ // OI master class begin
	
		public Joystick driveStick = new Joystick(0);
		public XboxController xboxcontroller = new XboxController(1);
		//Begin import of 2017 steamwork 2017 input code
	    public Button driveStick3Button = new JoystickButton(driveStick, 3);
	    public Button driveStick4Button = new JoystickButton(driveStick, 4);
	    public Button driveStick1Button = new JoystickButton(driveStick, 1);
	    public Button driveStick2Button = new JoystickButton(driveStick, 2);
	    public Button driveStick5Button = new JoystickButton(driveStick, 5);
	    public Button driveStick6Button = new JoystickButton(driveStick, 6);
	    public Button driveStick7Button = new JoystickButton(driveStick, 7);
	    public Button driveStick8Button = new JoystickButton(driveStick, 8);    
	    public Button driveStick9Button = new JoystickButton(driveStick, 9);
	    public Button driveStick10Button = new JoystickButton(driveStick, 10);
	    public Button driveStick11Button = new JoystickButton(driveStick, 11);
	    public Button driveStick12Button = new JoystickButton(driveStick, 12);	
	    // Xbox controls
	    public Button xboxControllerAButton = new JoystickButton(xboxcontroller, 1);
	    public Button xboxControllerBButton = new JoystickButton(xboxcontroller, 2);
	    public Button xboxControllerXButton = new JoystickButton(xboxcontroller, 3);
	    public Button xboxControllerYButton = new JoystickButton(xboxcontroller, 4);
	    public Button xboxControllerLBButton = new JoystickButton(xboxcontroller, 5);
	    public Button xboxControllerRBButton = new JoystickButton(xboxcontroller, 6);
	    public Button xboxControllerLeftStickButton = new JoystickButton(xboxcontroller, 7);
	    
	//End button assignment import
	   // public Button xboxControllerLTButton = new JoystickButton(xboxcontroller, 7);
	   // public Button xboxControllerRTButton = new JoystickButton(xboxcontroller, 8);
	    
	    
	    //Begin 2017 steamwork control import, removed xbox controller input
	    public OI() 
	    {
	    	System.out.println("Button assignment initialized.");
	    	
	    	//Joystick assignment
	    	//driveStick3Button.whenPressed(new DriveStraightCommand());
//	    	driveStick4Button.whenPressed(new   );
//	    	driveStick1Button.whenPressed(new   );
//	    	driveStick2Button.whenPressed(new   );
//	    	driveStick5Button.whenPressed(new   );
//	    	driveStick6Button.whenPressed(new   );
//	    	driveStick11Button.whenPressed(new   );
//	    	driveStick12Button.whenPressed(new   );
	    	//Joystick assignment end
	    	
	    	//Xbox assignment start
	    	//  xboxControllerBButton.whenPressed(new BallIntakeCommand());
	        //	xboxControllerBButton.whenPressed(new BallOuttakeCommand());
	        //	xboxControllerYButton.whenPressed(new ShooterIndexerCommand());
	        //xboxControllerRBButton.whileHeld(new RampUpCommand());
	        //	xboxControllerXButton.whenPressed(new GearDistanceCommand());
	        xboxControllerLBButton.whenPressed(new RampCommand());
	        // 	xboxControllerYButton.whenPressed(new GearIntakeStartCommand());
	        xboxControllerXButton.whenPressed(new ElevatorPIDCommand(1));
	        //	xboxControllerAButton.whenPressed(new GearIntakeFloorCommand());
	        //  Xbox end
	        xboxControllerLeftStickButton.whenPressed(new ElevatorShiftCommand());
	    }
	    //End 2017 control import
} // End OI master class


	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
