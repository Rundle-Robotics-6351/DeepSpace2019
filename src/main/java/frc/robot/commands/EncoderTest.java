/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class EncoderTest extends Command {

  double units;
  boolean isFinished = false;
  public EncoderTest(double units) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.units = units;
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("encoderGet()", Robot.sensors.encoderLeft.get());
    SmartDashboard.putNumber("encoderGetDistance()", Robot.sensors.encoderLeft.getDistance());
    SmartDashboard.putNumber("encoderDistancePerPulse()", Robot.sensors.encoderLeft.getDistancePerPulse());
    SmartDashboard.putNumber("encoderGetRate()", Robot.sensors.encoderLeft.getRate());

    double currentUnits = Robot.sensors.encoderLeft.get();


    if (currentUnits < units){
      Robot.driveTrain.arcadeDrive(0.3, 0);
    }
    else {
     Robot.driveTrain.setLeft(0);
     Robot.driveTrain.setRight(0);
     isFinished = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
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
