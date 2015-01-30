package org.usfirst.frc.team2928.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive class.
 */
public class Robot extends SampleRobot {
	
    RobotDrive robotDrive;
    Joystick stick;

    // Channels for the wheels
   
 
    
    
    
    final Talon frontLeftChannel = new Talon(2);
    final Talon rearLeftChannel	= new Talon (3);
    final Talon frontRightChannel = new Talon (1);
    final Talon rearRightChannel = new Talon (4);

    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;
    final int otherJoystickChannel = 1;
    double  frontLeftVoltage;
    double rearLeftVoltage;
    double frontRightVoltage;
    double rearRightVoltage;
    public Robot() {
    Button halfSpeed = new Button(7);
    
    	
    robotDrive = new RobotDrive(frontLeftChannel, rearLeftChannel, frontRightChannel, rearRightChannel);
    	// you may need to change or remove this to match your robot

        stick = new Joystick(joystickChannel);
      
        

    }
        

    /**
     * Runs the motors with Mecanum drive.
     */
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	   double robotSpeedFL = stick.getY();
        	    double robotAngleFL = stick.getZ();
        	    double robotStrafeFL = stick.getX();
        	    
        	    frontLeftVoltage =robotSpeedFL*Math.sin(robotAngleFL+(Math.PI/4)+robotStrafeFL);
                rearLeftVoltage = robotSpeedFL*Math.cos(robotAngleFL+(Math.PI/4)+robotStrafeFL);
                frontRightVoltage = robotSpeedFL*Math.cos(robotAngleFL+(Math.PI/4)-robotStrafeFL);
                rearRightVoltage = robotSpeedFL*Math.sin(robotAngleFL+(Math.PI/4)-robotStrafeFL);
            	
        	    
            
        	// Use the joystick X axis for lateral movement, Y axis for forward movement, and Z axis for rotation.
        	// This sample does not use field-oriented drive, so the gyro input is set to zero.
            //Z and X are inverted because the joystick is stupid.
            if((stick.getX() > .2 || stick.getX()< -.2) || (stick.getY()>.2 || stick.getY <-.2) || (stick.getZ()>.2 || stick.getZ() <-.2))
            {
            	frontLeftChannel.set(frontLeftVoltage);
            	rearLeftChannel.set(rearLeftVoltage);
            	frontRightChannel.set(frontRightVoltage);
            	rearLeftChannel.set(rearLeftVoltage);
            	if(stick.GetRawButton(halfSpeed) == true;)
            	{
            	robotDrive.mecanumDrive_Cartesian(stick.getX()/2, stick.getY()/2, stick.getZ()/2, 0);
            		
            	}
            	else
            	{
            		robotDrive.mechanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getZ(), 0);
            	}
            }
            else
            {
            	
            }
        	
            
            Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
        }
    }
    
}