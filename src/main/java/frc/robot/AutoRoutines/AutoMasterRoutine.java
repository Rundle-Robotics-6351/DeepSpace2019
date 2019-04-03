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

public class AutoMasterRoutine extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoMasterRoutine(String position, String routine) {
   
    

      if (routine.equals("dualRocket")){
        addParallel(new dualRocket(position));
        
      }

      if (routine.equals("dualCargo")){
        addParallel(new dualCargo(position));
       
      }
      
      if (routine.equals("singleRocket")){
        addParallel(new singleRocket(position));
        
      }

      if (routine.equals("singleCargo")){
        addParallel(new singleCargo(position));
      }
     
  }

  public boolean isFinished(){
    //X BUTTON OVERRIDES ROUTINE
    if (Robot.m_oi.driverControllerButtonPressed(RobotMap.Xbox_X_Button)){
      return true;
    }
    else {
      return false;
    }
  } 
}
