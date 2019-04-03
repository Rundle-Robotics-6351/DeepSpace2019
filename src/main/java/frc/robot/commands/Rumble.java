package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Rumble extends Command {
	
	String side;
	double value;
	
	public Rumble(String type, double val) {
	
		side = type;
		value = val;
	}
	
	public void execute() {
		switch(side) {
		case "left" : Robot.m_oi.setRumbleL(value);
			break;
		case "right": Robot.m_oi.setRumbleR(value);
			break;
		case "both": Robot.m_oi.setRumbleL(value);
		Robot.m_oi.setRumbleR(value);
		break;
		}
	}

	@Override
	protected boolean isFinished() {
	

		return true;
		
		
	}
	
	@Override
	protected void end() {
	}
	
	protected void interrupted() {
		end();
	}

}