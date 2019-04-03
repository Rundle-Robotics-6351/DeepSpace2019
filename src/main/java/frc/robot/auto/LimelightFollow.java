/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LimelightFollow extends Command {
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
  

  public LimelightFollow() {
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
      desiredTargetArea = desiredAreaCargo;
    }
    else {
      Robot.limelight.setPipeline(3);
    }
    **/
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Init
    targetX = Robot.limelight.tx();
    targetY = Robot.limelight.ty();
    targetArea = Robot.limelight.ta();
    targetsVisible = Robot.limelight.tv();

    // Debug
    SmartDashboard.putNumber("targetArea", targetArea);

    // Variables
    double zOffset = 0.7;
    double zMultiplier = -0.05296;
    double maxTargetArea = 8.5;
    double minTargetArea = 0.0003;

    //Infrared closest val
    double desiredInfrared = Robot.infraredPreferredDistance;

    // Turn speed variables
    double highSpeed = 0.35;
    double medSpeed = 0.25;
    double lowSpeed = 0.15;

    // Forward / Backward
		if ((targetArea <= maxTargetArea) && (targetArea >= minTargetArea)) {
      //yChange = (zMultiplier * targetArea) + zOffset;
      yChange = (targetArea / zMultiplier) + zOffset;
    }
    else if (desiredInfrared < Robot.sensors.infraredDistance()){
      yChange = 0;
    }
		else {
			yChange = 0;
		}
    
    // Turning
		if (targetX < -5) {
			xChange = -highSpeed;
		}
		else if (targetX > 5) {
			xChange = highSpeed;
		}
		else if ((targetX > 1) && (targetX < 5)) {
			xChange = medSpeed;
		}
		else if ((targetX > -5) && (targetX < -1)) {
			xChange = -medSpeed;
		}
		else if ((targetX > 0.5) && (targetX < 1)) {
			xChange = lowSpeed;
		}
		else if ((targetX < -0.5) && (targetX > -1)) {
			xChange = -lowSpeed;
		}
		else {
			xChange = 0;
    }
    
    // Limiting speed
    if (yChange > RobotMap.MAX_ROBOT_SPEED) { yChange = RobotMap.MAX_ROBOT_SPEED; }
		if (yChange < RobotMap.MIN_ROBOT_SPEED) { yChange = RobotMap.MIN_ROBOT_SPEED; }
		if (xChange > RobotMap.MAX_ROBOT_SPEED) { xChange = RobotMap.MAX_TURN_SPEED;  }
		if (xChange < -RobotMap.MAX_TURN_SPEED) { xChange = -RobotMap.MAX_TURN_SPEED; }

    // Drive
		Robot.driveTrain.arcadeDrive(yChange, xChange, false);
  }

  
  @Override
  protected boolean isFinished() {
    return false;
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
