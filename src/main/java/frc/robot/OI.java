/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.auto.DrivePID;
import frc.robot.auto.LimelightFollow;
import frc.robot.auto.TurnPID;
import frc.robot.commands.ElevatorMotionMagic;
import frc.robot.commands.EncoderTest;
//import frc.robot.commands.BackStepDeploy;
//import frc.robot.commands.CombinedDeploy;
//import frc.robot.commands.FrontStepDeploy;
import frc.robot.commands.GrabberDeploy;
import frc.robot.commands.GrabberSolenoids;
import frc.robot.commands.HatchDeploy;
import frc.robot.commands.HatchSolenoids;
//import frc.robot.commands.GrabberDeploy;
//import frc.robot.commands.GrabberSolenoids;
//import frc.robot.commands.HatchDeploy;
//import frc.robot.commands.HatchSolenoids;
//import frc.robot.commands.ShiftControlMode;
//import frc.robot.commands.StabalizeClimb;
//import frc.robot.control.NewOI;
//import frc.robot.control.NewOIHold;
import frc.robot.commands.SwitchBypassCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  //BLACK XBOX DRIVER CONTROLLER
  Joystick driverController = new Joystick(1);
  //CLEAR XBOX SECONDARY CONTROLLER
  Joystick secondaryController = new Joystick(0);

  public Button driverA = new JoystickButton(driverController, RobotMap.Xbox_A_Button);
  public Button driverB = new JoystickButton(driverController, RobotMap.Xbox_B_Button);
  public Button driverX = new JoystickButton(driverController, RobotMap.Xbox_X_Button);
  public Button driverY = new JoystickButton(driverController, RobotMap.Xbox_Y_Button);
  public Button driverLeftBumper = new JoystickButton(driverController, RobotMap.Xbox_Left_Bumper);
  public Button driverRightBumper = new JoystickButton(driverController, RobotMap.Xbox_Right_Bumper);
  public Button driverBack = new JoystickButton(driverController, RobotMap.Xbox_Back_Button);
  public Button driverStart = new JoystickButton(driverController, RobotMap.Xbox_Start_Button);

  public Button secondaryA = new JoystickButton(secondaryController, RobotMap.Xbox_A_Button);
  public Button secondaryB = new JoystickButton(secondaryController, RobotMap.Xbox_B_Button);
  public Button secondaryX = new JoystickButton(secondaryController, RobotMap.Xbox_X_Button);
  public Button secondaryY = new JoystickButton(secondaryController, RobotMap.Xbox_Y_Button);
  public Button secondaryLeftBumper = new JoystickButton(secondaryController, RobotMap.Xbox_Left_Bumper);
  public Button secondaryRightBumper = new JoystickButton(secondaryController, RobotMap.Xbox_Right_Bumper);
  public Button secondaryBack = new JoystickButton(secondaryController, RobotMap.Xbox_Back_Button);
  public Button secondaryStart = new JoystickButton(secondaryController, RobotMap.Xbox_Start_Button);

  public OI() {

    driverA.whileHeld(new LimelightFollow());
    secondaryStart.whenPressed(new EncoderTest(10));
    
    secondaryLeftBumper.whenPressed(new SwitchBypassCommand());
    secondaryRightBumper.whileHeld(new ElevatorMotionMagic(Robot.cargoShip));

    secondaryB.whenPressed(new HatchDeploy());
    secondaryY.whenPressed(new HatchSolenoids());
    secondaryA.whenPressed(new GrabberDeploy());
    secondaryX.whenPressed(new GrabberSolenoids());

    //FOR DEBUGGING

    driverStart.whenPressed(new DrivePID(60, 25));
    driverBack.whenPressed(new TurnPID(90, 25));

    
    /** 
   // driverLeftBumper.whenPressed(new FrontStepDeploy());
   // driverRightBumper.whenPressed(new BackStepDeploy());

    //driverStart.whenPressed(new FrontStepDeploy());
    //driverBack.whenPressed(new BackStepDeploy());

    
     
    //secondaryA.whenPressed(new NewOIHold("A"));
    secondaryA.whenPressed(new NewOI("A"));
    secondaryB.whenPressed(new NewOI("B"));
    //secondaryB.whenPressed(new NewOIHold("B"));
    secondaryX.whenPressed(new NewOI("X"));
    secondaryY.whenPressed(new NewOI("Y"));

    //driverB.whileHeld(new StabalizeClimb());
    driverX.whenPressed(new CombinedDeploy());
    **/
    
    
    
    

    //secondaryRightBumper.whenPressed(new ShiftControlMode("up"));
    //secondaryLeftBumper.whenPressed(new ShiftControlMode("down"));
  }

  public double driverControllerAxisValue(int axis) {
    return driverController.getRawAxis(axis);
  }

  public double secondaryControllerAxisValue(int axis) {
    return secondaryController.getRawAxis(axis);
  }

  public boolean driverControllerButtonPressed(int button){
    return driverController.getRawButtonPressed(button);
  }

  public boolean secondaryControllerButtonPressed(int button){
    return secondaryController.getRawButtonPressed(button);
  }

  public void setRumbleL(double value) {
		driverController.setRumble(RumbleType.kLeftRumble, value);
	}
	public void setRumbleR(double value) {
		driverController.setRumble(RumbleType.kRightRumble, value);
  }

  public void setSecondaryRumbleL(double value) {
		secondaryController.setRumble(RumbleType.kLeftRumble, value);
	}
	public void setSecondaryRumbleR(double value) {
		secondaryController.setRumble(RumbleType.kRightRumble, value);
  }
  
  
}
