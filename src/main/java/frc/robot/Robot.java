/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrainCAN;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.FrontStep;
import frc.robot.subsystems.Grabber;
import frc.robot.AutoRoutines.AutoMasterRoutineNew;
import frc.robot.subsystems.BackStep;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Sensors;



public class Robot extends TimedRobot {

  public static final DriveTrainCAN driveTrain = new DriveTrainCAN();
  public static final Sensors sensors = new Sensors();
  public static final Elevator elevator = new Elevator();
  public static final Grabber grabber = new Grabber();
  public static final Hatch hatch = new Hatch();
  public static final Limelight limelight = new Limelight();
  public static final Pneumatics pneumatics = new Pneumatics();
  public static final BackStep backStep = new BackStep();
  public static final FrontStep frontStep = new FrontStep();



  public static String[] controlModeList = {"Defence/Endgame", "Cargo", "Hatch"};
  public static int controlModeIndex = 0;
  public static String controlMode = controlModeList[controlModeIndex];

  public static OI m_oi;
  public double matchTime;

  //Value of solenoid when extended
  //For double solenoid, true is kForward and false is kReverse
  public static boolean hatchExtended = true;
  public static boolean hatchDeployExtended = false;
  public static boolean grabberExtended = true;
  public static boolean grabberDeployExtended = false;
  public static boolean frontStepExtended = true;
  public static boolean backStepExtended = true;

  //Encoder Value of Rocket Heights. Add Negative in front because sensor is not in phase
  public static double hatchLow;//Equals zero
  public static double hatchMiddle;
  public static double hatchHigh;
  public static double cargoLow;//Not gonna bother
  public static double cargoMiddle;
  public static double cargoHigh;
  public static double cargoShip;

  //Initialize PID Tuning values
  public static Boolean testBoolean;
  public static double DriveP;
  public static double DriveI;
  public static double DriveD;
  public static double TurnP;
  public static double TurnI;
  public static double TurnD;
  public static double ElevatorF;
  public static double ElevatorP;
  public static double ElevatorI;
  public static double ElevatorD;

  //Bypass
  public static boolean switchBypass = false;

  //Infrared
  public static double infraredPreferredDistance;
  Preferences preferences;

  Command m_autonomousCommand;
  SendableChooser<String> m_startingMode = new SendableChooser<>();
  SendableChooser<String> m_autoPosition = new SendableChooser<>();
  SendableChooser<String> m_autoRoutine = new SendableChooser<>();

 
  @Override
  public void robotInit() {
    m_oi = new OI();
    preferences = Preferences.getInstance();

   
    m_startingMode.setDefaultOption("Starting in Teleop", "teleop");
    m_startingMode.addOption("Starting in Autonomous", "auto");

    SmartDashboard.putData("Auto Starting Mode", m_startingMode);

    m_autoRoutine.setDefaultOption("Single Cargo Ship ", "singleCargo");
    m_autoRoutine.addOption("Side Cargo Ship", "sideCargo");
    m_autoRoutine.addOption("Single Rocket", "singleRocket");
    m_autoRoutine.addOption("Dual Rocket", "dualRocket");
    m_autoRoutine.addOption("Dual Cargo Ship", "dualCargo");
 

    SmartDashboard.putData("Auto Routine", m_autoRoutine);


    m_autoPosition.setDefaultOption("Left Position", "L");
    m_autoPosition.addOption("Right Position", "R");

    SmartDashboard.putData("Auto Starting Position", m_autoPosition);

    Robot.limelight.setCamMode(1);

    CameraServer.getInstance().startAutomaticCapture();


  /** 
  testBoolean = preferences.getBoolean("test", false);
  DriveP = preferences.getDouble("DriveP", 0);
  DriveI = preferences.getDouble("Drivei", 0);
  DriveD = preferences.getDouble("DriveD", 0);
  TurnP = preferences.getDouble("TurnP", 0);
  TurnI = preferences.getDouble("TurnD", 0);
  TurnD = preferences.getDouble("TurnI", 0);
  ElevatorF = preferences.getDouble("ElevatorF", 0);
  ElevatorP = preferences.getDouble("ElevatorP", 0);
  ElevatorI = preferences.getDouble("ElevaotrI", 0);
  ElevatorD = preferences.getDouble("ElevatorD", 0);
  **/
   
   
  }

 
  @Override
  public void robotPeriodic() {
  }

 
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Infrared Value",Robot.sensors.infraredDistance());
  }

  
  @Override
  public void autonomousInit() {
    String mode = m_startingMode.getSelected();
    String routine = m_autoRoutine.getSelected();
    String position = m_autoPosition.getSelected();

    if (mode.equals("auto")){
      m_autonomousCommand = new AutoMasterRoutineNew(position, routine);
    }
    else if (mode.equals("teleop")){
      m_autonomousCommand = null;
    }
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

  }

 
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    matchTime = DriverStation.getInstance().getMatchTime();
    Robot.m_oi.setSecondaryRumbleL(0.5);
    Robot.m_oi.setSecondaryRumbleR(0.5);

    SmartDashboard.putBoolean("Switch", Robot.sensors.switchState());
    SmartDashboard.putBoolean("HatchSolenoids", Robot.hatch.getSolenoidState());

    
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    Robot.m_oi.setSecondaryRumbleL(0);
    Robot.m_oi.setSecondaryRumbleR(0);

  }

 
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    
    SmartDashboard.putBoolean("HatchDeploy", Robot.hatch.getDoubleSolenoidState());
    SmartDashboard.putBoolean("GrabberSolenoids", Robot.grabber.getSolenoidState());
    SmartDashboard.putBoolean("GrabberDeploy", Robot.grabber.getDoubleSolenoidState());
    SmartDashboard.putNumber("Infrared Value",Robot.sensors.infraredDistance());
    SmartDashboard.putNumber("NavX", Robot.sensors.getNavAngle());
    SmartDashboard.putBoolean("Bypassed Limit Switch", switchBypass);
    

    matchTime = DriverStation.getInstance().getMatchTime();

  }


  
  @Override
  public void testPeriodic() {
  }
}
