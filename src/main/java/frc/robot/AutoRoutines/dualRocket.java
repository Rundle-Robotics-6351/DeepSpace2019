/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class dualRocket extends CommandGroup {
  /**
   * Add your docs here.
   */
  public dualRocket(String position) {
    

    if (position.equals("L")){
      addSequential(new singleRocket("L"));
      addSequential(new rocketToLoad("L"));
    }
    else if (position.equals("R")){
      addSequential(new singleRocket("R"));
      addSequential(new rocketToLoad("R"));

    }
  }

  public boolean isFinished(){
    return true;
  }
}
