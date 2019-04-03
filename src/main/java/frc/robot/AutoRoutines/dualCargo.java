/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class dualCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public dualCargo(String position) {
    

    if (position.equals("L")){

      addSequential(new singleCargo("L"));
      addSequential(new cargoToLoad("L"));

    }
    else if (position.equals("R")){

      addSequential(new singleCargo("L"));
      addSequential(new cargoToLoad("R"));


    }
  }

  public boolean isFinished(){
    return true;
  }
}

