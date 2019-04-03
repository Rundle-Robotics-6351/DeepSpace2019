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

public class ElevatorMotionMagic extends Command {
  double position;
  public ElevatorMotionMagic(double position) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
    this.position = position;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    if (Robot.testBoolean != null){
			Robot.elevator.motionMagicPrep(Robot.ElevatorF, Robot.ElevatorP, Robot.ElevatorI, Robot.ElevatorD, 4096, 4096);
    }
    else{
    Robot.elevator.motionMagicPrep(0.2, 0.5, 0, 0, 4096, 4096);
    }
   
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if (Robot.sensors.switchState() == false){
      Robot.elevator.zeroEncoder(); 
    }

    Robot.elevator.motionMagic(position);
    SmartDashboard.putNumber("MotionMagicEncoder", Robot.elevator.elevatorEncoderPosition());
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
