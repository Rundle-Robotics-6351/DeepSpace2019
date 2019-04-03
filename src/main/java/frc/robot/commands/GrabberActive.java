/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class GrabberActive extends Command {

  double leftJoystick;
  double rightJoystick;
  public GrabberActive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.grabber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //if (Robot.controlMode.equals("Cargo")){
    leftJoystick = Robot.m_oi.secondaryControllerAxisValue(RobotMap.Xbox_Left_Y_Axis);
    rightJoystick = Robot.m_oi.secondaryControllerAxisValue(RobotMap.Xbox_Right_Y_Axis);

    if (leftJoystick > 0.30) {
      Robot.grabber.setSpeed(leftJoystick);
    }
    else if (leftJoystick < -0.30) {
      Robot.grabber.setSpeed(leftJoystick);
    }
    else{
      Robot.grabber.setSpeed(0);
    }

    if (rightJoystick < -0.30) {
      Robot.grabber.openGrabber();
    }
    else if (rightJoystick > 0.30){
      Robot.grabber.closeGrabber();
    }
  }
  //}

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
    Robot.grabber.setSpeed(0);
  }
}
