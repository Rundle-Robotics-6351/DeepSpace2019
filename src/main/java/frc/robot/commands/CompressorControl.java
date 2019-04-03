/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CompressorControl extends Command {
  boolean enabled;
  //PowerDistributionPanel pdp = new PowerDistributionPanel(); pdp.getVoltage()

  
  public CompressorControl() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.pneumatics);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    enabled = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (DriverStation.getInstance().isAutonomous() ){
      enabled = false;
    }
    else{
    if (RobotController.getBatteryVoltage() <= 10.0){
      enabled = false;
    }
    else if (RobotController.getBatteryVoltage() >= 11.0){
      enabled = true;
    }

    if (enabled == false){
      Robot.pneumatics.stopCompressor();
    }
    else if (enabled == true){
      Robot.pneumatics.startCompressor();
    }
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
*/ 