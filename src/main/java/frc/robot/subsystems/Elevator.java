/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ElevatorManual;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  
  public static WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(11);

  private int
   timeoutMs = 25;

  public Elevator() {
    elevatorMotor.configFactoryDefault();
    elevatorMotor.setSensorPhase(false);
    elevatorMotor.setInverted(false);

    elevatorMotor.configNominalOutputForward(0);
    elevatorMotor.configNominalOutputReverse(0);
    elevatorMotor.configPeakOutputForward(1);
    elevatorMotor.configPeakOutputReverse(-1);

    elevatorMotor.configVoltageCompSaturation(11, timeoutMs);
    elevatorMotor.enableVoltageCompensation(false);
    elevatorMotor.configVoltageMeasurementFilter(32, timeoutMs);

    elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeoutMs);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ElevatorManual());
  }

  public void setSpeed(double speed) {
    elevatorMotor.set(ControlMode.PercentOutput, speed);
  }

  public double elevatorEncoderPosition() {
    return elevatorMotor.getSelectedSensorPosition();
  }

  public void motionMagic(double position){
    elevatorMotor.set(ControlMode.MotionMagic, position);
  }
  
  public void motionMagicPrep(double kF, double kP, double kI, double kD, int velocity, int acceleration){
    elevatorMotor.selectProfileSlot(0, 0);
    elevatorMotor.config_kF(0, kF, timeoutMs);
    elevatorMotor.config_kP(0, kP, timeoutMs);
    elevatorMotor.config_kI(0, kI, timeoutMs);
    elevatorMotor.config_kD(0, kD, timeoutMs);

    elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeoutMs);

    elevatorMotor.configMotionCruiseVelocity(velocity, timeoutMs);
    elevatorMotor.configMotionAcceleration(acceleration, timeoutMs);

    elevatorMotor.setSelectedSensorPosition(0, 0, timeoutMs);
  }

  public void zeroEncoder(){
    elevatorMotor.setSelectedSensorPosition(0, 0, timeoutMs);
  }
}
