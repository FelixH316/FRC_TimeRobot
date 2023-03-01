// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.lang.model.util.ElementScanner14;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot { 
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // INDIVIDUAL MOTOR DECLARATION
  //private CANSparkMax sparkMax = new CANSparkMax(2, MotorType.kBrushed);
  private CANSparkMax sparkMaxR1 = new CANSparkMax(3, MotorType.kBrushed);
  private CANSparkMax sparkMaxR2 = new CANSparkMax(2, MotorType.kBrushed); 
  private CANSparkMax sparkMaxL1 = new CANSparkMax(4, MotorType.kBrushed);
  private CANSparkMax sparkMaxL2 = new CANSparkMax(5, MotorType.kBrushed); 

  // CONTROLLER DECLARATION
  Joystick control = new Joystick(0);

  // MOTOR GROUP DECLARATION
  //private MotorControllerGroup leftGroup = new MotorControllerGroup(sparkMaxL1, sparkMaxL2); 
  //private MotorControllerGroup rightGroup = new MotorControllerGroup(sparkMaxR1, sparkMaxR2); 
  //private DifferentialDrive chasis = new DifferentialDrive(leftGroup, righGroup);
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    // MOTOR CONFIGURATION
    sparkMaxR1.restoreFactoryDefaults();
    sparkMaxR1.setInverted(false);
    sparkMaxR1.setIdleMode(IdleMode.kCoast);

    sparkMaxR2.restoreFactoryDefaults();
    sparkMaxR2.setInverted(false);
    sparkMaxR2.setIdleMode(IdleMode.kCoast);  // kBrake - Freno

    sparkMaxL1.restoreFactoryDefaults();
    sparkMaxL1.setInverted(false);
    sparkMaxL1.setIdleMode(IdleMode.kCoast);

    sparkMaxL2.restoreFactoryDefaults();
    sparkMaxL2.setInverted(false);
    sparkMaxL2.setIdleMode(IdleMode.kCoast);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // CHASIS CONTROL
    // FORWARD
    if (control.getRawButton(8))
    {
      sparkMaxR1.set(0.2);
      sparkMaxR2.set(0.2);
      sparkMaxL1.set(-0.2);
      sparkMaxL2.set(-0.2);
    }
    // TURN LEFT
    else if(control.getRawButton(5))
    {
      sparkMaxR1.set(0.2);
      sparkMaxR2.set(0.2);
      sparkMaxL1.set(0.2);
      sparkMaxL2.set(0.2);
    }
    // TURN RIGHT
    else if(control.getRawButton(6))
    {
      sparkMaxR1.set(-0.2);
      sparkMaxR2.set(-0.2);
      sparkMaxL1.set(-0.2);
      sparkMaxL2.set(-0.2);
    }
    // REVERSE
    else if(control.getRawButton(7))
    {
      sparkMaxR1.set(-0.2);
      sparkMaxR2.set(-0.2);
      sparkMaxL1.set(0.2);
      sparkMaxL2.set(0.2);
    }
    // STOP - NOTHING PRESS
    else 
    {
      sparkMaxR1.set(0);
      sparkMaxR2.set(0);
      sparkMaxL1.set(0);
      sparkMaxL2.set(0);
    }  
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
