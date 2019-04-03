/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Solenoid hatchSolenoid = new Solenoid(0, 1);
  private static DoubleSolenoid hatchExtension = new DoubleSolenoid(0, 2, 3);

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public boolean getSolenoidState() {
    boolean currentState = false;
    currentState = hatchSolenoid.get();
    return currentState;
  }

  public boolean getDoubleSolenoidState() {
    boolean currentState = false;
    switch (hatchExtension.get()) {
      case kForward: currentState = true;
        break;
      case kReverse: currentState = false;
        break;
      case kOff: 
        break;
    }
    return currentState;
    }

    public void openHatch() {
      hatchSolenoid.set(true);
    }

    public void closeHatch() {
      hatchSolenoid.set(false);
    }

    public void hatchUp() {
      hatchExtension.set(Value.kForward);
    }

    public void hatchDown() {
      hatchExtension.set(Value.kReverse);
    }

    public boolean hatchIsExtended(){
      if (getSolenoidState() == Robot.hatchExtended){
        return true;
      }
      else {
        return false;
      }
    }

    public boolean hatchDeployIsExtended(){
      if (getDoubleSolenoidState() == Robot.hatchDeployExtended){
        return true;
      }
      else {
        return false;
      }
    }

}
