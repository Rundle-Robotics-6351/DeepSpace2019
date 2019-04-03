/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ShiftControlMode extends Command {

  private Boolean completed;
  private String direction;
  private Command transition = new ControlModeTransition();

  public ShiftControlMode(String dir) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    direction = dir;
  }

  
  @Override protected void initialize() {}
  @Override
  protected void execute() {
    switch(direction){
      case "up":
        Robot.controlModeIndex++;
        break;
      case "down":
        Robot.controlModeIndex--;
        break;
    }
    if (Robot.controlModeIndex > (Robot.controlModeList.length -1)){
      //If greater than highest index value, set to zero
      Robot.controlModeIndex = 0;
    }
    else if (Robot.controlModeIndex < 0){
      //If lower than lowest possible value, set to highest index in list
      Robot.controlModeIndex = Robot.controlModeList.length - 1;
    }
    
    Robot.controlMode = Robot.controlModeList[Robot.controlModeIndex];
    
    completed = true;
   }

  
  @Override
  protected boolean isFinished() {
    return completed;
  }

  @Override protected void end() {
    transition.start();
  }
  @Override protected void interrupted() {}
  
}
