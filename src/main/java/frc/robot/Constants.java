package frc.robot;

public final class Constants {
  
  public static final class DriveConstants {

    // Motor controller IDs for drivetrain motors

    public static final int LEFT_LEADER_ID = 1;
    public static final int LEFT_FOLLOWER_ID = 3;
    public static final int RIGHT_LEADER_ID = 4;
    public static final int RIGHT_FOLLOWER_ID = 2;
    
  }

  public static final class FuelConstants {

    // CAN IDs  
    public static final int FEEDER_ROLLER_ID = 0; // shoot
    public static final int MAIN_ROLLER_ID = 6; // feeder + outtake - VictorSP

    // Voltages (VELOCIDADE)
    public static final double INTAKE_MAIN_VOLTAGE  = 6.0;
    public static final double INTAKE_FEEDER_VOLTAGE = -8.0;

    public static final double LAUNCH_MAIN_VOLTAGE = 10.6;
    public static final double LAUNCH_FEEDER_VOLTAGE = 9.0;

    public static final double OUTTAKE_MAIN_VOLTAGE = -6.0;
    public static final double OUTTAKE_FEEDER_VOLTAGE = 6.0;
    
   }

  public static final class OperatorConstants {
   
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;

    public static final double DRIVE_SCALING = .7;
    public static final double ROTATION_SCALING = .7;
  }

  public static final class ShooterConstants {

    // teste
    public static double kForward = 5.0;
    public static double kReverse = -5.0;

  }

}
