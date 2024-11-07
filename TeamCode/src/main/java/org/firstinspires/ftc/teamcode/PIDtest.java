package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Tom on 9/26/17.  Updated 9/24/2021 for PIDF.
 * This assumes that you are using a REV Robotics Expansion Hub
 * as your DC motor controller.  This OpMode uses the extended/enhanced
 * PIDF-related functions of the DcMotorControllerEx class.
 * The REV Robotics Expansion Hub supports the extended motor controller
 * functions, but other controllers (such as the deprecated Modern Robotics
 * and Hitechnic DC Motor Controllers) do not.
 */
public class PIDtest
{
   // our DC motor
   DcMotor elevator_Motor;

   public static final double NEW_P = 2.5;
   public static final double NEW_I = 0.1;
   public static final double NEW_D = 0.2;
   public static final double NEW_F = 0.5;
   // These values are for illustration only; they must be set
   // and adjusted for each motor based on its planned usage.

   public void init(HardwareMap hwMap)
   {
      elevator_Motor = hwMap.get(DcMotor.class, "ElevatorMotor");
      elevator_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
      elevator_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      elevator_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
   }

   public void pid(Telemetry telemetry)
   {
      // Get a reference to the motor controller and cast it as an extended functionality controller.
      // We assume it's a REV Robotics Expansion Hub, which supports the extended controller functions.
      DcMotorControllerEx motorControllerEx = (DcMotorControllerEx)elevator_Motor.getController();

      // Get the port number of our configured motor.
      int motorIndex = ((DcMotorEx)elevator_Motor).getPortNumber();

      // Get the PIDF coefficients for the RUN_USING_ENCODER RunMode.
      PIDFCoefficients pidfOrig = motorControllerEx.getPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);

      // change coefficients
      PIDFCoefficients pidfNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
      motorControllerEx.setPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER, pidfNew);

      // Re-read coefficients and verify change.
      PIDFCoefficients pidfModified = motorControllerEx.getPIDFCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);

      // Display info to user.
      telemetry.addData("P,I,D,F (orig)", "%.04f, %.04f, %.04f, %.04f", pidfOrig.p, pidfOrig.i, pidfOrig.d, pidfOrig.f);
      telemetry.addData("P,I,D,F (modified)", "%.04f, %.04f, %.04f, %.04f", pidfModified.p, pidfModified.i, pidfModified.d, pidfModified.f);
   }
}
