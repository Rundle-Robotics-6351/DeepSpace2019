/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.auto.DrivePID;
import frc.robot.auto.LimelightAuto;
import frc.robot.auto.TurnPID;

public class rocketToLoad extends CommandGroup {
  /**
   * Add your docs here.
   */
  public rocketToLoad(String position) {
    requires(Robot.driveTrain);
    requires(Robot.hatch);
    requires(Robot.limelight);

    if (position.equals("L")){

      addSequential(new DrivePID(-36, 0));
      addSequential(new TurnPID(-146, 25));
      addSequential(new LimelightAuto());

    }
    else if (position.equals("R")){
      addSequential(new DrivePID(-36, 0));
      addSequential(new TurnPID(146, 25));
      addSequential(new LimelightAuto());

    }
  }

  public boolean isFinished(){
    return true;
  }
}
