/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.PowerControllerRumble;

/**
 * Add your docs here.
 */
public class Sensors extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Encoder encoderLeft;
  public AHRS navX;
  public DigitalInput limitSwitch;
  public AnalogInput infrared;

  public Sensors() {
    encoderLeft = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
    navX = new AHRS(SPI.Port.kMXP);
    limitSwitch = new DigitalInput(2);
	  infrared = new AnalogInput(1);

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new PowerControllerRumble());
  }

  public double getNavAngle(){
    return navX.getRoll();
  }

  public double getNavPitch(){
    return navX.getPitch();
  }

  public double getDistance(){
    double distanceInches = (encoderLeft.get()) / (19.1667);
    return distanceInches;
  }

  public boolean switchState() {
    return limitSwitch.get();
  }

  public double infraredDistance() {
		double voltage = infrared.getVoltage();

		
		double distance = 12.415 * Math.pow(voltage, -1.017);
		
			return distance;
		
	}


}
