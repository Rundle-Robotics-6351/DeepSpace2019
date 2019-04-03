/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.BackStepDrive;

/**
 * Add your docs here.
 */
public class BackStep extends Subsystem {
  
  public static VictorSP backMotor = new VictorSP(2);

  public static DoubleSolenoid backPiston = new DoubleSolenoid(1, 6, 7);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new BackStepDrive());
  }

  public boolean getDoubleSolenoidState() {
    boolean currentState = false;
    switch (backPiston.get()) {
      case kForward: currentState = true;
        break;
      case kReverse: currentState = false;
        break;
      case kOff: 
        break;
    }
    return currentState;
    }

  public void lowerBackStep() {
    backPiston.set(Value.kForward);
  }

  public void raiseBackStep() {
    backPiston.set(Value.kReverse);
  }

  public void setStepSpeed(double value) {
    backMotor.set(value);
  }

  public boolean backStepIsExtended(){
    if (getDoubleSolenoidState() == Robot.backStepExtended){
      return true;
    }
    else {
      return false;
    }
  }

 
  
}
