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
import frc.robot.RobotMap;

public class TalonDrive extends Command {
 
  double rightTrigger = 0;
	double leftTrigger = 0;
	double leftJoystickXAxis = 0;
  boolean compensate;
  double initialAngle;

  private double rawTurn;
	private double speed = 0;
	private double rotation = 0;
  
  public TalonDrive() {
    requires(Robot.driveTrain); 
  }
  @Override protected void initialize() {}

  @Override
  protected void execute() {

     rightTrigger = Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Right_Trigger);
		 leftTrigger = Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Left_Trigger);
     leftJoystickXAxis = Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Left_X_Axis);
     
     speed = (rightTrigger - leftTrigger);
     rawTurn = leftJoystickXAxis;

    //Parabolic Function with a 0.1 Deadband
///** 
     if (Math.abs(rawTurn) <= 0.10) {
      rotation = 0;
    }
    else if (rawTurn < -0.10) {
      rotation = (-0.555*(Math.pow(-1*(rawTurn+0.1), 2)))-0.30;
    }
    else if (rawTurn > 0.10) {
      rotation = (0.555*(Math.pow(rawTurn-0.1, 2)))+0.30;
    }
//**/
    SmartDashboard.putNumber("Rotation", rotation);

    speed = speed*RobotMap.Drive_Scaling_Teleop_Straight;
    rotation = rotation*RobotMap.Drive_Scaling_Teleop_Turn;

    //
    //
    //

    double minSpeed = 0.1;
    double StephenYeung = 0.2;
    double speedScaling = 0.888;

    if (Math.abs(speed) < minSpeed) {
      speed = 0;
    } else if (speed > minSpeed) {
      speed = speed * speedScaling + StephenYeung;
    } else if (speed < -minSpeed) {
      speed = speed * speedScaling - StephenYeung;
    } else {
      System.out.println("Error at Driver 1 exponential speed, Damian messed up.");
    }

    //
    //
    //

    //Preventing Virtual Glitch
    Robot.driveTrain.m_myRobot.setDeadband(0.02);

    //Drive Splitting
    if (speed != 0 && Math.abs(rotation) != 0) {
      compensate = true;
      double scaleSpeed = speed*0.7;
			Robot.driveTrain.arcadeDrive(scaleSpeed, rotation);
		}
		else if (speed !=0 || Math.abs(rotation) !=0) {
			if (speed != 0 && rotation == 0) {
				if (compensate == true) {
          //Called once to get the desired heading to drive straigt in
					initialAngle = Robot.sensors.getNavAngle();
					compensate = false;
        }
        //Compensation for turning
        double eKp = 0.0;
				double currentAngle = Robot.sensors.getNavAngle();
				double error = initialAngle - currentAngle;
        double turnPower = error*eKp; //takes it to clear the deadband
        Robot.driveTrain.arcadeDrive(speed, turnPower);
        /** 
        double leftCompensate = 1;
        Robot.driveTrain.setLeft(-1 * speed * leftCompensate);
        Robot.driveTrain.setRight(speed);
        **/
      }
      else if (speed == 0 && Math.abs(rotation) !=0) {
				compensate = true;
        //Robot.driveTrain.arcadeDrive(0, rotation);
        Robot.driveTrain.setRotation(rotation);
        
      }
    } else {
			compensate = true;
			Robot.driveTrain.arcadeDrive(0, 0);
		}
		
		
  }

  
  @Override 
  protected boolean isFinished() {
    return false;
  }

  
  @Override protected void end() {}
  @Override protected void interrupted() {}
}
