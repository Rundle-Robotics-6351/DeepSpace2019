/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.control;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.ElevatorMotionMagic;

public class NewOIHold extends Command {
  private Command ControlCommand;
  private String button;
  private String controlMode;
  public NewOIHold(String button) {
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
      case "A":
        ControlCommand = new ElevatorMotionMagic(1000);
        break;
      case "B":
        if (controlMode == "Cargo"){
        ControlCommand = new ElevatorMotionMagic(Robot.cargoMiddle);
        }
        else if (controlMode == "Hatch"){
          ControlCommand = new ElevatorMotionMagic(Robot.hatchMiddle);
        }
        break;
        case "Y":
        if (controlMode == "Cargo"){
        ControlCommand = new ElevatorMotionMagic(Robot.cargoHigh);
        }
        else if (controlMode == "Hatch"){
          ControlCommand = new ElevatorMotionMagic(Robot.hatchHigh);
        }
        break;
    }


    if (ControlCommand != null){
      ControlCommand.start();
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
      ControlCommand.cancel();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
