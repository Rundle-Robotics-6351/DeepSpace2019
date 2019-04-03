/*--  --------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ElevatorManual extends Command {

  double rightTrigger;
  double leftTrigger;
  
  public ElevatorManual() {// Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.zeroEncoder();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {



    leftTrigger = Robot.m_oi.secondaryControllerAxisValue(RobotMap.Xbox_Left_Trigger);
    rightTrigger = Robot.m_oi.secondaryControllerAxisValue(RobotMap.Xbox_Right_Trigger);

    double downspeed;
    double upspeed;
   if (Robot.switchBypass == false){
      if (Robot.sensors.switchState() == false){
        downspeed = 0;
        upspeed = -1 * rightTrigger;
      }
      else {
        upspeed = -1 * rightTrigger;
        downspeed = leftTrigger;
      }
  } else {
    upspeed = -1 * rightTrigger;
    downspeed = leftTrigger;
  }
    
    if (rightTrigger == 0 && leftTrigger == 0){
      Robot.elevator.setSpeed(0);
    }
    else if (rightTrigger > 0){
      Robot.elevator.setSpeed(upspeed);
    }
    else if (leftTrigger > 0){
      Robot.elevator.setSpeed(downspeed);
    }

    
    
    
    double encoderval = Robot.elevator.elevatorEncoderPosition();
    SmartDashboard.putNumber("ELEVATOR ENCODER", encoderval);
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
