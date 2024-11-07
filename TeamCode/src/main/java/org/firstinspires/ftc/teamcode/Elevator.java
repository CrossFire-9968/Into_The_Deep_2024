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
   private double elevatorRaiseSpeed;
   private double elevatorLowerSpeed;
   private int topBucketPosition;
   private int bottomBucketPosition;

   public static final double NEW_P = 6.0;
   public static final double NEW_I = 0.3;
   public static final double NEW_D = 0.2;
   public static final double NEW_F = 1.0;
   PIDFCoefficients pidfOrig;
   PIDFCoefficients pidfNew;
   PIDFCoefficients pidfModified;

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

      // Get the PIDF coefficients for the RUN_USING_ENCODER RunMode.
      pidfOrig = motorControllerEx.getPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);

      // change coefficients
      pidfNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
      motorControllerEx.setPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER, pidfNew);

      // Re-read coefficients and verify change.
      pidfModified = motorControllerEx.getPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);

      // Display info to user.
//      telemetry.addData("P,I,D,F (orig)", "%.04f, %.04f, %.04f, %.04f", pidfOrig.p, pidfOrig.i, pidfOrig.d, pidfOrig.f);
   }

   public void setRaiseSpeed(double speed)
   {
      this.elevatorRaiseSpeed = speed;
   }

   public void setLowerSpeed(double speed)
   {
      this.elevatorLowerSpeed = speed;
   }

   public void setTopBucketPosition(int position)
   {
      this.topBucketPosition = position;
   }

   public void setBottomBucketPosition(int position)
   {
      this.bottomBucketPosition = position;
   }

   public int getPosition()
   {
      return elevator_Motor.getCurrentPosition();
   }

   public void raiseManual()
   {
      elevator_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      elevator_Motor.setPower(elevatorRaiseSpeed);
   }

   public void lowerManual()
   {
      elevator_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      elevator_Motor.setPower(elevatorLowerSpeed);
   }

   public void stop()
   {
      // Only do this if we aren't using encoders and not near the home position.
      // When near home, go ahead and turn off run by encoders as the motor is not needed to hold
      // the position since the elevator is collapsed.
      if ((elevator_Motor.getMode() != DcMotor.RunMode.RUN_TO_POSITION) || (elevator_Motor.getTargetPosition() == 0 && elevator_Motor.getCurrentPosition() < 5)) {
         elevator_Motor.setPower(0.0);
         elevator_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         elevator_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      }
   }

   public void toBottomBucket()
   {
      if (this.getPosition() < this.bottomBucketPosition) {
         elevator_Motor.setPower(this.elevatorRaiseSpeed);
      }
      else {
         elevator_Motor.setPower(this.elevatorLowerSpeed);
      }

      elevator_Motor.setTargetPosition(this.bottomBucketPosition);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toTopBucket()
   {
      if (this.getPosition() < this.topBucketPosition) {
         elevator_Motor.setPower(this.elevatorRaiseSpeed);
      }
      else {
         elevator_Motor.setPower(this.elevatorLowerSpeed);
      }

      elevator_Motor.setTargetPosition(this.topBucketPosition);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void toHome()
   {
      int homePosition = 0;
      elevator_Motor.setPower(this.elevatorLowerSpeed);
      elevator_Motor.setTargetPosition(homePosition);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }

   public void getElevatorTelemetry(Telemetry telemetry)
   {
      telemetry.addData("Elevator Position: ", elevator_Motor.getTargetPosition());
      telemetry.addData("Elevator Motor Mode: ", elevator_Motor.getMode());
      telemetry.addData("P,I,D,F (orig)", "%.04f, %.04f, %.04f, %.04f", pidfOrig.p, pidfOrig.i, pidfOrig.d, pidfOrig.f);
      telemetry.addData("P,I,D,F (modified)", "%.04f, %.04f, %.04f, %.04f", pidfModified.p, pidfModified.i, pidfModified.d, pidfModified.f);
   }
}

