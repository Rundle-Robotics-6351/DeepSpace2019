/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class ControlModeTransition extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ControlModeTransition() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    requires(Robot.hatch);
    requires(Robot.grabber);
   
    if (Robot.controlMode.equals("Defence/Endgame")) {
      //Tuck in both elements
      if (Robot.hatch.getDoubleSolenoidState() == Robot.hatchDeployExtended) {
        addParallel(new HatchDeploy());
      }
      if (Robot.grabber.getSolenoidState() == Robot.grabberExtended){
        addParallel(new GrabberSolenoids());
      }
      if (Robot.grabber.getDoubleSolenoidState() == Robot.grabberDeployExtended) {
        addSequential(new GrabberDeploy());
      }
     
    }
    else if (Robot.controlMode.equals("Hatch")) {
      //Bring in grabber, deploy hatch
      if (Robot.grabber.getSolenoidState() == Robot.grabberExtended) {
        addParallel(new GrabberSolenoids());
      }
      if (Robot.hatch.getDoubleSolenoidState() != Robot.hatchDeployExtended) {
        addParallel(new HatchDeploy());
      }
      if (Robot.grabber.getDoubleSolenoidState() == Robot.grabberDeployExtended) {
        addSequential(new GrabberDeploy());
      }
    }
    else if (Robot.controlMode.equals("Cargo")) {
      //Bring in hatch, deploy grabber
      if (Robot.hatch.getDoubleSolenoidState() == Robot.hatchDeployExtended) {
        addParallel(new HatchDeploy());
    }
      if (Robot.grabber.getDoubleSolenoidState() != Robot.grabberDeployExtended) {
        addSequential(new GrabberDeploy());
    }
  }
   
   
   
   
   
   
   
    /**
    if (Robot.controlMode.equals("Defence/Endgame")) {
      //Tuck in both elements
      if (Robot.hatch.getDoubleSolenoidState() == false) {
        addParallel(new HatchDeploy());
      }
      if (Robot.grabber.getSolenoidState() == false){
        addParallel(new GrabberSolenoids());
      }
      if (Robot.grabber.getDoubleSolenoidState() == true) {
        addSequential(new GrabberDeploy());
      }
     
    }
    else if (Robot.controlMode.equals("Hatch")) {
      //Bring in grabber, deploy hatch
      if (Robot.grabber.getSolenoidState() == true) {
        addParallel(new GrabberSolenoids());
      }
      if (Robot.hatch.getDoubleSolenoidState() == false) {
        addParallel(new HatchDeploy());
      }
      if (Robot.grabber.getDoubleSolenoidState() == true) {
        addSequential(new GrabberDeploy());
      }
    }
    else if (Robot.controlMode.equals("Cargo")) {
      //Bring in hatch, deploy grabber
      if (Robot.hatch.getDoubleSolenoidState() == false) {
        addParallel(new HatchDeploy());
    }
      if (Robot.grabber.getDoubleSolenoidState() == true) {
        addSequential(new GrabberDeploy());
    }
  }
  **/
}
  
}
