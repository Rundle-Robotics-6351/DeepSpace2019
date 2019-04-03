/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoRoutines;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.auto.DrivePID;
import frc.robot.auto.LimelightAuto;
import frc.robot.commands.HatchDeploy;
import frc.robot.commands.HatchSolenoids;

public class singleCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  
  public singleCargo(String position) {
    
    requires(Robot.hatch);
    requires(Robot.limelight);
    requires(Robot.driveTrain);

    if (position.equals("L")){

      //Distance in inches to drive forward, time spent on accuracy after sequence, milliseconds
      addSequential(new HatchDeploy());
      addSequential(new DrivePID(60, 0));
      addSequential(new LimelightAuto());
      Timer.delay(2);
      addSequential(new HatchSolenoids());
      Timer.delay(0.5);


    }
    else if (position.equals("R")){
      addSequential(new HatchDeploy());
      addSequential(new DrivePID(60, 0));
      addSequential(new LimelightAuto());
      Timer.delay(2);
      addSequential(new HatchSolenoids());
      Timer.delay(0.5);

    }
  }

  public boolean isFinished(){
    return true;
  }
}