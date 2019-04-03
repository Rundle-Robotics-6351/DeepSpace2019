/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class FrontStep extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static DoubleSolenoid frontPistons = new DoubleSolenoid(1, 4, 5);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public boolean getDoubleSolenoidState() {
    boolean currentState = false;
    switch (frontPistons.get()) {
      case kForward: currentState = true;
        break;
      case kReverse: currentState = false;
        break;
      case kOff: 
        break;
    }
    return currentState;
    }

    public void raiseFrontPiston() {
      frontPistons.set(Value.kReverse);
    }

    public void lowerFrontPiston() {
      frontPistons.set(Value.kForward);
    }

    public boolean frontStepIsExtended(){
      if (getDoubleSolenoidState() == Robot.frontStepExtended){
        return true;
      }
      else {
        return false;
      }
    }
}
