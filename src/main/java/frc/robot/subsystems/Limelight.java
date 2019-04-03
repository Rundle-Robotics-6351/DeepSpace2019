/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static NetworkTableInstance networktables = NetworkTableInstance.getDefault();
  public static NetworkTable limelight = networktables.getTable("limelight");

  public static NetworkTableEntry light = limelight.getEntry("ledMode");
	public static NetworkTableEntry camera = limelight.getEntry("camMode");
	public static NetworkTableEntry targetX = limelight.getEntry("tx");
  public static NetworkTableEntry targetY = limelight.getEntry("ty");
  public static NetworkTableEntry targetArea = limelight.getEntry("ta");
  public static NetworkTableEntry targetsVisible = limelight.getEntry("tv");
  public static NetworkTableEntry pipeline = limelight.getEntry("pipeline");

  //to reference the above values, type in "entry".getDouble(0)
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  //one is off, 0 is on
  public void setCamMode(double mode){
    camera.forceSetNumber(mode);
  }
  public void setLightMode(double mode){
    light.forceSetNumber(mode);
  }

  public void setPipeline(double pipe){
    pipeline.forceSetNumber(pipe);
  }

  public double tx() {
    return targetX.getDouble(0);
  }

  public double ty() {
    return targetY.getDouble(0);
  }

  public double ta() {
    return targetArea.getDouble(0);
  }
  public double tv() {
    return targetsVisible.getDouble(0);
  }
}
