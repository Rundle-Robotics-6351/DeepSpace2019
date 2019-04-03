/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.GrabberActive;

/**
 * Add your docs here.
 */
public class Grabber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static VictorSP leftMotor = new VictorSP(0);
  public static VictorSP rightMotor = new VictorSP(1);

  public static Solenoid grabberSolenoid = new Solenoid(0, 0);
  public static DoubleSolenoid grabberDeploy = new DoubleSolenoid(0, 6, 7);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new GrabberActive());
  }

  public void setSpeed(double speed) {
    leftMotor.set(speed);
    rightMotor.set(-speed);
  }

  public boolean getSolenoidState() {
    boolean currentState = false;
    currentState = grabberSolenoid.get();
    return currentState;
  }

    public boolean getDoubleSolenoidState() {
    	boolean currentState = false;
    	switch (grabberDeploy.get()) {
    		case kForward: currentState = true;
    			break;
    		case kReverse: currentState = false;
    			break;
    		case kOff: 
    			break;
    	}
    	return currentState;
      }
      
    public void closeGrabber(){
      grabberSolenoid.set(false);
    }
    public void openGrabber(){
      grabberSolenoid.set(true);
    }
    public void grabberDown(){
      grabberDeploy.set(Value.kForward);
    }
    public void grabberUp(){
      grabberDeploy.set(Value.kReverse);
    }

    public boolean grabberIsExtended(){
      if (getSolenoidState() == Robot.grabberExtended){
        return true;
      }
      else {
        return false;
      }
    }

    public boolean grabberDeployIsExtended(){
      if (getDoubleSolenoidState() == Robot.grabberDeployExtended){
        return true;
      }
      else {
        return false;
      }
    }
    
  }
