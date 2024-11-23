package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Elevator
{
   private DcMotor elevator_Motor;
   private static final double raiseSpeed = 1.0;
   private static final double lowerSpeed = -0.7;
   private static final int wallElementPosition = 240;
   private static final int lowBucketPosition = 1600;
   private static final int lowRungPosition = 440;
   private static final int highRungPosition = 1400;

   // Motor PIDF coefficients, USE CAUTION. These values change how the motor
   // responds when commanded to an encoder position.
   public static final double NEW_P = 6.0;
   public static final double NEW_I = 0.3;
   public static final double NEW_D = 0.2;
   public static final double NEW_F = 1.0;
   PIDFCoefficients pidfNew;
   PIDFCoefficients pidCheck;

   public void init(HardwareMap hwMap)
   {
      elevator_Motor = hwMap.get(DcMotor.class, "ElevatorMotor");
      elevator_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
      elevator_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

      this.setPIDF();

      // Explicitly initialize with motor power off for safety
      this.stop();
   }

   public void setPIDF()
   {
      // Get a reference to the motor controller and cast it as an extended functionality controller.
      // We assume it's a REV Robotics Expansion Hub, which supports the extended controller functions.
      DcMotorControllerEx motorControllerEx = (DcMotorControllerEx) elevator_Motor.getController();

      // Get the port number of our configured motor.
      int motorIndex = ((DcMotorEx) elevator_Motor).getPortNumber();

      // change coefficients
      pidfNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
      motorControllerEx.setPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER, pidfNew);

      // Read coefficients from memory to check that they took.
      pidCheck = motorControllerEx.getPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);
   }

   public int getPosition()
   {
      return elevator_Motor.getCurrentPosition();
   }

   public void raiseManual()
   {
      elevator_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      elevator_Motor.setPower(raiseSpeed);
   }

   public void lowerManual()
   {
      elevator_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      elevator_Motor.setPower(lowerSpeed);
   }

   public void stop()
   {
      // This method is intended to be called when we no longer want to energize the motors.
      // In manual mode, this is anytime the driver is not pressing a raise or lower button.
      // In encoder mode, this is when the elevator is resting on at the bottom. There is no
      // reason to apply motor torque to hold position when the elevator is resting on the hard stop.
      if ((elevator_Motor.getMode() != DcMotor.RunMode.RUN_TO_POSITION) || (elevator_Motor.getTargetPosition() == 0 && elevator_Motor.getCurrentPosition() < 5)) {
         elevator_Motor.setPower(0.0);
         elevator_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         elevator_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      }
   }

   public void toWallElementPosition()
   {
      if (this.getPosition() < wallElementPosition) {
         elevator_Motor.setPower(raiseSpeed);
      }
      else {
         elevator_Motor.setPower(lowerSpeed);
      }

      elevator_Motor.setTargetPosition(wallElementPosition);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toLowBucket()
   {
      if (this.getPosition() < lowBucketPosition) {
         elevator_Motor.setPower(raiseSpeed);
      }
      else {
         elevator_Motor.setPower(lowerSpeed);
      }

      elevator_Motor.setTargetPosition(lowBucketPosition);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toLowRungPosition()
   {
      if (this.getPosition() < lowRungPosition) {
         elevator_Motor.setPower(raiseSpeed);
      }
      else {
         elevator_Motor.setPower(lowerSpeed);
      }

      elevator_Motor.setTargetPosition(lowRungPosition);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toHighRungPosition()
   {
      if (this.getPosition() < highRungPosition) {
         elevator_Motor.setPower(raiseSpeed);
      }
      else {
         elevator_Motor.setPower(lowerSpeed);
      }

      elevator_Motor.setTargetPosition(highRungPosition);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toHome()
   {
      int homePosition = 0;
      elevator_Motor.setPower(lowerSpeed);
      elevator_Motor.setTargetPosition(homePosition);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void getElevatorTelemetry(Telemetry telemetry)
   {
      telemetry.addData("Elevator Position: ", elevator_Motor.getTargetPosition());
      telemetry.addData("Elevator Motor Mode: ", elevator_Motor.getMode());
      telemetry.addData("P,I,D,F", "%.04f, %.04f, %.04f, %.04f", pidCheck.p, pidCheck.i, pidCheck.d, pidCheck.f);
   }
}

