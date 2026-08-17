
package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Auto2;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

public class RobotContainer {

  // The robot's subsystems
  private final CANDriveSubsystem driveSubsystem = new CANDriveSubsystem();
  private final CANFuelSubsystem ballSubsystem = new CANFuelSubsystem();

  // The driver's controller
  private final CommandXboxController driverController = new CommandXboxController(
      Constants.OperatorConstants.DRIVER_CONTROLLER_PORT);

  // The operator's controller
  private final CommandXboxController operatorController = new CommandXboxController(
      Constants.OperatorConstants.OPERATOR_CONTROLLER_PORT);

  // The autonomous chooser
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {

    configureBindings();

    ballSubsystem.periodic();

    autoChooser.setDefaultOption(
        "Auto - Andar",
        new Auto2(driveSubsystem, ballSubsystem));
    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  private void configureBindings() {

    // Driver Controller
    driveSubsystem.setDefaultCommand(
        driveSubsystem.driveArcade(
            () -> driverController.getLeftY() * Constants.OperatorConstants.ROTATION_SCALING,
            () -> -driverController.getRightX() * Constants.OperatorConstants.DRIVE_SCALING));

    // Operator Controller        

    // RightBumper gira apenas o Shooter
    driverController.rightBumper()
        .whileTrue(ballSubsystem.shoot(() -> 1))
        .whileFalse(ballSubsystem.run(ballSubsystem::stop));

  }

  public Command getAutonomousCommand() {

    return autoChooser.getSelected();

  }

}
