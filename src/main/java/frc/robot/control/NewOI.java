/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.control;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.GrabberDeploy;
import frc.robot.commands.GrabberSolenoids;
import frc.robot.commands.HatchDeploy;
import frc.robot.commands.HatchSolenoids;

public class NewOI extends Command {
  private Command ControlCommand;
  private String button;
  private String controlMode;
  public NewOI(String button) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.button = button;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    controlMode = Robot.controlMode;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    switch(button){
      case "X": 
        if (controlMode == "Cargo"){
          ControlCommand = new GrabberSolenoids();
        }
        else if (controlMode == "Hatch"){
          ControlCommand = new HatchSolenoids();
        }
        break;
      case "B":
        if (controlMode == "Cargo"){
          ControlCommand = new GrabberDeploy();
        }
        else if (controlMode == "Hatch"){
          ControlCommand = new HatchDeploy();
        }
        break;
        
     
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if (ControlCommand != null){
      ControlCommand.start();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
