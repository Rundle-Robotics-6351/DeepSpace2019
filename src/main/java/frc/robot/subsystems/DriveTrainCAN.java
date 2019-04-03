/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.TalonDrive;

/**
 * Add your docs here.
 */
public class DriveTrainCAN extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static WPI_TalonSRX leftLead = new WPI_TalonSRX(5);
  public static WPI_TalonSRX rightLead = new WPI_TalonSRX(6);
  public static WPI_VictorSPX leftFollow = new WPI_VictorSPX(8);
  public static WPI_VictorSPX rightFollow = new WPI_VictorSPX(9);

  public DifferentialDrive m_myRobot;

  public DriveTrainCAN() {
    //IF MOTORS ARE NOT FOLLOWING
    //POWER CYCLE BOT
    leftFollow.follow(leftLead, FollowerType.PercentOutput);
    rightFollow.follow(rightLead, FollowerType.PercentOutput);

    leftFollow.setInverted(InvertType.FollowMaster);
    rightFollow.setInverted(InvertType.FollowMaster);

    leftLead.configFactoryDefault();
    leftFollow.configFactoryDefault();
    rightLead.configFactoryDefault();
    rightFollow.configFactoryDefault();

    //leftLead.configOpenloopRamp(0.5);
    //rightLead.configOpenloopRamp(0.5);

    m_myRobot = new DifferentialDrive(leftLead, rightLead);

    m_myRobot.setSafetyEnabled(false);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TalonDrive());
  }

  public void arcadeDrive(double speed, double rotation, boolean squared) {
    m_myRobot.arcadeDrive(speed, rotation, squared);
  }

  public void arcadeDrive(double speed, double rotation) {
    this.arcadeDrive(speed, rotation, false);
  }

  public void setRotation(double value) {
    leftLead.set(ControlMode.PercentOutput, value);
    rightLead.set(ControlMode.PercentOutput, value);
  }

  public void setLeft(double value){
    leftLead.set(ControlMode.PercentOutput, value);
  }
  public void setRight(double value){
    rightLead.set(ControlMode.PercentOutput, value);
  }
}
