package frc.robot.subsystems;

import static frc.robot.Constants.FuelConstants.FEEDER_ROLLER_ID;
import static frc.robot.Constants.FuelConstants.INTAKE_FEEDER_VOLTAGE;
import static frc.robot.Constants.FuelConstants.INTAKE_MAIN_VOLTAGE;
import static frc.robot.Constants.FuelConstants.LAUNCH_FEEDER_VOLTAGE;
import static frc.robot.Constants.FuelConstants.LAUNCH_MAIN_VOLTAGE;
import static frc.robot.Constants.FuelConstants.MAIN_ROLLER_ID;
import static frc.robot.Constants.FuelConstants.OUTTAKE_FEEDER_VOLTAGE;
import static frc.robot.Constants.FuelConstants.OUTTAKE_MAIN_VOLTAGE;

import java.util.function.DoubleSupplier;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANFuelSubsystem extends SubsystemBase {

  private final SparkMax mainRoller;
  private final VictorSP feederRoller;

  public CANFuelSubsystem() {

    var config = new SparkMaxConfig();
    config.inverted(true);

    mainRoller = new SparkMax(MAIN_ROLLER_ID, MotorType.kBrushless);
    mainRoller.configure(config, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    feederRoller = new VictorSP(FEEDER_ROLLER_ID);
    
    feederRoller.setInverted(true);
  }

  // Métodos

  public void intake() {
    mainRoller.setVoltage(INTAKE_MAIN_VOLTAGE);
    feederRoller.setVoltage(INTAKE_FEEDER_VOLTAGE);
  }

  public void buffer() {
    feederRoller.setVoltage(LAUNCH_FEEDER_VOLTAGE);
  }

  public void launch() {
    mainRoller.setVoltage(LAUNCH_MAIN_VOLTAGE);
    feederRoller.setVoltage(LAUNCH_FEEDER_VOLTAGE);
  }

  public void outtake() {
    mainRoller.setVoltage(OUTTAKE_MAIN_VOLTAGE);
    feederRoller.setVoltage(OUTTAKE_FEEDER_VOLTAGE);
  }

  public void stop() {
    mainRoller.stopMotor();
    feederRoller.stopMotor();
  }

  public void shoot() {
    mainRoller.setVoltage(LAUNCH_MAIN_VOLTAGE);
  }

   public void periodic() {

  }

  public Command launchCommand(DoubleSupplier xSpeed) {
    return this.run(() -> launch());
  }

  public void feederRollback(){
    feederRoller.set(-1);

  }

  public void feederSlowRollback(){
    feederRoller.set(-0.7);
  }

  public Command shoot(DoubleSupplier xSpeed) {
    SmartDashboard.putNumber("Velocity", xSpeed.getAsDouble());
    return this.run(
        () -> mainRoller.set(xSpeed.getAsDouble())); 

  
  }
}


