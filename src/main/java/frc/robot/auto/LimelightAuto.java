/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LimelightAuto extends Command {
  double targetX;
	double targetY;
	double targetArea;
	double targetsVisible;
	double leftMotorVal;
	double rightMotorVal;
	
	double xChange;
  double yChange;

  double targetAreaHatch;
  double targetAreaCargo;
  
  double desiredTargetArea;
  double desiredInfrared = Robot.infraredPreferredDistance;
  

  public LimelightAuto() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.limelight);
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    targetX = Robot.limelight.tx();
    targetY = Robot.limelight.ty();
    targetArea = Robot.limelight.ta();
    targetsVisible = Robot.limelight.tv();

    Robot.limelight.setCamMode(0);
    Robot.limelight.setLightMode(0);
    /** 
    if (Robot.controlMode.equals("Hatch")){
      Robot.limelight.setPipeline(1);
      desiredTargetArea = targetAreaHatch;
    }
    else if (Robot.controlMode.equals("Cargo")){
      Robot.limelight.setPipeline(2);
      desiredTargetArea = targetAreaCargo;
    }
    else {
      Robot.limelight.setPipeline(3);
    }
    **/
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    targetX = Robot.limelight.tx();
    targetY = Robot.limelight.ty();
    targetArea = Robot.limelight.ta();
    targetsVisible = Robot.limelight.tv();

    double zOffset = 0.5;
    double zMultiplier = 0.2;
    
    if ((targetArea > 10)) {
			// Too close, object is x% of frame. back up
			yChange = -0.35;
			xChange = 0;
    }
    //else if ((targetArea <= desiredTargetArea && (targetArea >= 0.0003))){
		else if ((targetArea <= 5) && (targetArea >= 0.0003)) {
			// Too far, object is x% of frame, get closer
			yChange = (-zMultiplier * targetArea) + zOffset;
    }
    else if (desiredInfrared < Robot.sensors.infraredDistance()){
      yChange = 0;
    }
		else {
			yChange = 0;
		}
		
		if (targetX < -5) {
			xChange = -0.35;
		}
		else if (targetX > 5) {
			xChange = 0.35;
		}
		else if ((targetX > 1) && (targetX < 5)) {
			xChange = 0.25;
		}
		else if ((targetX > -5) && (targetX < -1)) {
			xChange = -0.25;
		}
		else if ((targetX > 0.5) && (targetX < 1)) {
			xChange = 0.15;
		}
		else if ((targetX < -0.5) && (targetX > -1)) {
			xChange = -0.15;
		}
		else {
			xChange = 0;
    }
    
    if (yChange > RobotMap.MAX_ROBOT_SPEED) { yChange = RobotMap.MAX_ROBOT_SPEED; }
		if (yChange < RobotMap.MIN_ROBOT_SPEED) { yChange = RobotMap.MIN_ROBOT_SPEED; }
		if (xChange > RobotMap.MAX_ROBOT_SPEED) { xChange = RobotMap.MAX_TURN_SPEED;  }
		if (xChange < -RobotMap.MAX_TURN_SPEED) { xChange = -RobotMap.MAX_TURN_SPEED; }

		Robot.driveTrain.arcadeDrive(-yChange, xChange, false);
  }

  
  @Override
  protected boolean isFinished() {
    boolean complete;
    //if (Math.abs(targetX) < 0.5 && targetArea >=desiredTargetArea){

    if (Math.abs(targetX) < 0.5 && targetArea > 2.5  && Robot.sensors.infraredDistance() < desiredInfrared){
      complete = true;
    }
    else{
      complete = false;
    }
    return complete;
  }

  
  @Override
  protected void end() {
    Robot.limelight.setCamMode(1);
    Robot.limelight.setLightMode(1);
  }

 
  @Override
  protected void interrupted() {
    end();
  }
}
