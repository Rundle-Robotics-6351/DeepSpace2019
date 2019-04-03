package frc.robot.auto;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;


public class TurnPID extends PIDCommand {
	
	double m_angle;
	double m_currentAngle;
	static double kP = 0.04;
	static double kI = 0;
	static double kD = 0.09;
	int threshold;
	int counter = 0;
	
	boolean isFinished = false;
	
	public TurnPID(double angle, int thresh) {
		super(kP, kI, kD);
		requires(Robot.driveTrain);
		requires(Robot.sensors);
		m_angle = angle;
		threshold = thresh;
		
	}
	
	
	protected void initialize() {
		if (Robot.testBoolean != null){
			this.getPIDController().setPID(Robot.TurnP, Robot.TurnI, Robot.TurnD);
		}
		getPIDController().setPID(kP, kI, kD);

		Robot.sensors.encoderLeft.reset();
	
		m_currentAngle = Robot.sensors.getNavAngle();
		setInputRange(-720, 720);
		getPIDController().setContinuous(true);
		setSetpoint(m_currentAngle + m_angle);
		getPIDController().setAbsoluteTolerance(1);
		getPIDController().setOutputRange(-0.4, 0.4);
		
		getPIDController().enable();
		
	}
	
	public void execute() {
		boolean onTarget = getPIDController().onTarget();
		
		if (onTarget == true) {
			counter = counter+1;
			
			//changing this value will affect how long the system takes to adjust
			isFinished = counter >= threshold;
		}
		else {
			counter = 0;
		}
	}

	@Override
	protected double returnPIDInput() {
		return Robot.sensors.getNavAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
    Robot.driveTrain.setRotation(output);
	}

	@Override
	protected boolean isFinished() {
		return isFinished;
  }
  
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		getPIDController().disable();

	}

}
