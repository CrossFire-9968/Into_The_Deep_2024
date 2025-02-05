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
   public DcMotor ElevatorMotor;
   private static final double raiseSpeed = 1.0;
   private static final double lowerSpeed = -0.8;
   private static final int wallElementPosition = 240;
   private static final int lowBucketPosition = 1600;
   private static final int highBucketPosition = 3000;
   private static final int lowRungPosition =440;
   private static final int highRungPosition = 1325;
   private static final int highRungHookPosition = 975;
   private static  int homePosition = 0;

   // Motor PIDF coefficients, USE CAUTION. These values change how the motor
   // responds when commanded to an encoder position.
   public static final double NEW_P = 10.0;
   public static final double NEW_I = 0.75;
   public static final double NEW_D = 0.1;
   public static final double NEW_F = 1.0;
   PIDFCoefficients pidfNew;
   PIDFCoefficients pidCheck;

   public void init(HardwareMap hwMap)
   {
      ElevatorMotor = hwMap.get(DcMotor.class, "ElevatorMotor");
      ElevatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);
      ElevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

      this.setPIDF();

      // Explicitly initialize with motor power off for safety
      this.stop();
   }

   public void setPIDF()
   {
      // Get a reference to the motor controller and cast it as an extended functionality controller.
      // We assume it's a REV Robotics Expansion Hub, which supports the extended controller functions.
      DcMotorControllerEx motorControllerEx = (DcMotorControllerEx) ElevatorMotor.getController();

      // Get the port number of our configured motor.
      int motorIndex = ((DcMotorEx) ElevatorMotor).getPortNumber();

      // change coefficients
      pidfNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
      motorControllerEx.setPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER, pidfNew);

      // Read coefficients from memory to check that they took.
      pidCheck = motorControllerEx.getPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);
   }

   public int getPosition()
   {
      return ElevatorMotor.getCurrentPosition();
   }

   public void raiseManual()
   {
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      ElevatorMotor.setPower(raiseSpeed);
   }

   public void lowerManual()
   {
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      ElevatorMotor.setPower(lowerSpeed);
   }

   public void stop()
   {
      // This method is intended to be called when we no longer want to energize the motors.
      // In manual mode, this is anytime the driver is not pressing a raise or lower button.
      // In encoder mode, this is when the elevator is resting on at the bottom. There is no
      // reason to apply motor torque to hold position when the elevator is resting on the hard stop.
      if ((ElevatorMotor.getMode() != DcMotor.RunMode.RUN_TO_POSITION) || (ElevatorMotor.getTargetPosition() == 0 && ElevatorMotor.getCurrentPosition() < 5)) {
         ElevatorMotor.setPower(0.0);
         ElevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         ElevatorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      }
   }

   public void toWallElementPosition()
   {
      if (this.getPosition() < wallElementPosition) {
         ElevatorMotor.setPower(raiseSpeed);
      }
      else {
         ElevatorMotor.setPower(lowerSpeed);
      }

      ElevatorMotor.setTargetPosition(wallElementPosition);
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toLowBucket()
   {
      if (this.getPosition() < lowBucketPosition) {
         ElevatorMotor.setPower(raiseSpeed);
      }
      else {
         ElevatorMotor.setPower(lowerSpeed);
      }

      ElevatorMotor.setTargetPosition(lowBucketPosition);
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toHighBucket()
   {
      if (this.getPosition() < highBucketPosition) {
         ElevatorMotor.setPower(raiseSpeed);
      }
      else {
         ElevatorMotor.setPower(lowerSpeed);
      }

      ElevatorMotor.setTargetPosition(highBucketPosition);
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toLowRungPosition()
   {
      if (this.getPosition() < lowRungPosition) {
         ElevatorMotor.setPower(raiseSpeed);
      }
      else {
         ElevatorMotor.setPower(lowerSpeed);
      }

      ElevatorMotor.setTargetPosition(lowRungPosition);
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void
   toHighRungPosition()
   {
      if (this.getPosition() < highRungPosition) {
         ElevatorMotor.setPower(raiseSpeed);
      }
      else {
         ElevatorMotor.setPower(lowerSpeed);
      }

      ElevatorMotor.setTargetPosition(highRungPosition);
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toHome()
   {
      ElevatorMotor.setPower(lowerSpeed);
      ElevatorMotor.setTargetPosition(homePosition);
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toHighRungHookPosition(){
      ElevatorMotor.setPower(lowerSpeed);
      ElevatorMotor.setTargetPosition(highRungHookPosition);
      ElevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }


   public void getElevatorTelemetry(Telemetry telemetry)
   {
      telemetry.addData("Elevator Position: ", ElevatorMotor.getTargetPosition());
      telemetry.addData("Elevator Motor Mode: ", ElevatorMotor.getMode());
      telemetry.addData("P,I,D,F", "%.04f, %.04f, %.04f, %.04f", pidCheck.p, pidCheck.i, pidCheck.d, pidCheck.f);
   }
}

