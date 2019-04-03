/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PowerControllerRumble extends Command {

  boolean enabled;
  public PowerControllerRumble() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    enabled = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if (RobotController.getBatteryVoltage() <= 10.5){
      enabled = true;
    }
    else if (RobotController.getBatteryVoltage() > 10.5){
      enabled = false;
    }

    if (enabled == true){
      Robot.m_oi.setSecondaryRumbleL(1);
      Robot.m_oi.setSecondaryRumbleR(1);

      Robot.m_oi.setRumbleL(0.5);
      Robot.m_oi.setRumbleR(0.5);
    }
    else if (enabled == false){
      Robot.m_oi.setSecondaryRumbleL(0);
      Robot.m_oi.setSecondaryRumbleR(0);

      Robot.m_oi.setRumbleL(0);
      Robot.m_oi.setRumbleR(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
