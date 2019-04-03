/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TeleopMasterRoutine extends CommandGroup {


  public TeleopMasterRoutine(String position, String routine) {

    if (Robot.m_oi.driverControllerButtonPressed(RobotMap.Xbox_Y_Button) == true){

    if (routine.equals("rocketToLoad")){
      addParallel(new rocketToLoad(position));
      
    }

    if (routine.equals("cargoToLoad")){
      addParallel(new cargoToLoad(position));
     
    }
  }
    
  }

  public boolean isFinished(){
    //X BUTTON OVERRIDES ROUTINE
    if (Robot.m_oi.driverControllerButtonPressed(RobotMap.Xbox_X_Button)==true){
      return true;
    }
    else {
      return false;
    }
  } 
}
