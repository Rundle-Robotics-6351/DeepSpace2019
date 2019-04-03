package frc.robot.auto;

import edu.wpi.first.wpilibj.command.PIDCommand;

import frc.robot.Robot;

public class DrivePID extends PIDCommand {
	
	static double kP = 0.1;
	static double kI = 0;
	static double kD = 0.0; //super small increase
	static double ekP = 0.05;
	public double m_currentAngle;
	double dst, rotation;
	boolean isFinished = false;
	int threshold;
	int counter = 0;
	boolean onTarget = false;
	
	
	
	
	public DrivePID(double distance, int thresh) {
		
		super(kP, kI, kD);
		dst = distance;
		threshold = thresh;
		
		requires(Robot.driveTrain);
		requires(Robot.sensors);
		
		//this.setName("DriveTrain", "DrivePID");
		
	}
	
	public void initialize() {

		if (Robot.testBoolean != null){
			this.getPIDController().setPID(Robot.DriveP, Robot.DriveI, Robot.DriveD);
		}

		setInputRange(-400, 400);
		Robot.sensors.encoderLeft.reset();
		m_currentAngle = Robot.sensors.getNavAngle();
		
		double currentDst = Robot.sensors.getDistance();
		setSetpoint(currentDst + dst);
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(2);
		getPIDController().setOutputRange(-0.5, 0.5);
		
		getPIDController().enable();
		
	}
	
	public void execute() {
		onTarget = getPIDController().onTarget();
		
		
		double error = m_currentAngle-Robot.sensors.getNavAngle();

		
		rotation = error*ekP;
		
		
		if (onTarget == true) {
			counter = counter +1;
			
			//changing this value will affect how long the system takes to adjust
			isFinished = counter >= threshold;
			
		} else {
			counter = 0;
		}

	
		
	}

	@Override
	protected double returnPIDInput() {
		return Robot.sensors.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		
		Robot.driveTrain.arcadeDrive(-1*output, rotation);

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
