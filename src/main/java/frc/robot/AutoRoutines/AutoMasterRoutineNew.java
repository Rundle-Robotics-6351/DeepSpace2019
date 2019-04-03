/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoRoutines;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoMasterRoutineNew extends Command {

  String routine;
  String position;
  Command autoCommand;

  public AutoMasterRoutineNew(String routine, String position) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    this.routine = routine;
    this.position = position;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (Robot.hatch.getSolenoidState() != Robot.hatchExtended){
      //Open hatch sets cylinder to true, which is the extended state, which is when the hatch panel is locked in
      Robot.hatch.openHatch();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (routine.equals("sideCargo")){
      autoCommand = new cargoToLoad(position);
    }
    else if (routine.equals("singleCargo")){
      autoCommand = new singleCargo(position);
    }
    else if (routine.equals("singleRocket")){
      autoCommand = new singleRocket(position);
    }
    else if (routine.equals("dualRocket")){
      autoCommand = new dualRocket(position);
    }
    else if (routine.equals("dualCargo")){
      autoCommand = new dualCargo(position);
    }
    else{
      autoCommand = null;
    }
  
  if (autoCommand != null){
   autoCommand.start();
   SmartDashboard.putString("Auto Message", "Auto In Progress" + routine);
  }
  else {
    SmartDashboard.putString("Auto Message", "Auto Null");
  }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Robot.m_oi.driverControllerButtonPressed(RobotMap.Xbox_X_Button)){
      return true;

    }
    else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    autoCommand.cancel();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    autoCommand.cancel();
  }
}
