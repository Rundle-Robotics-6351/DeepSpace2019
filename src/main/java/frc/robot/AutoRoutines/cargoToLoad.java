/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoRoutines;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.auto.DrivePID;
import frc.robot.auto.LimelightAuto;
import frc.robot.auto.TurnPID;

public class cargoToLoad extends CommandGroup {
  /**
   * Add your docs here.
   */
  public cargoToLoad(String position) {
    

    if (position.equals("L")){

      addSequential(new DrivePID(-36, 0));
      addSequential(new TurnPID(-125, 0));
      addSequential(new DrivePID(153, 0));
      addSequential(new TurnPID(-35, 0));
      addSequential(new LimelightAuto());
      Timer.delay(0.5);

    }
    else if (position.equals("R")){
      addSequential(new DrivePID(-36, 0));
      addSequential(new TurnPID(125, 0));
      addSequential(new DrivePID(153, 0));
      addSequential(new TurnPID(35, 0));
      addSequential(new LimelightAuto());
      Timer.delay(0.5);
    }
  }

  public boolean isFinished(){
    return true;
  }
}