package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Robot Manual")
public class RobotManual extends OpMode
{
   public Blinkin blinkin = new Blinkin();
   public Mecanum mecanum = new Mecanum();
   public Elevator elevator = new Elevator();
   public Gripper gripper = new Gripper();

   public void init()
   {
      blinkin.init(hardwareMap);
      mecanum.init(hardwareMap);
      elevator.init(hardwareMap);
      gripper.init(hardwareMap);
   }

   @Override
   public void loop()
   {
      mecanum.manualDrive(gamepad1, telemetry);

      // --- Manual elevator control ------------------
      if (gamepad2.dpad_up) {
         elevator.raiseManual();
      }
      else if (gamepad2.dpad_down) {
         elevator.lowerManual();
      }
      else {
         elevator.stop();
      }

      // --- Encoder elevator control ------------------
      if (gamepad2.triangle) {
         elevator.toLowRungPosition();
      }
      else if (gamepad2.circle) {
         elevator.toWallElementPosition();
      }
      else if (gamepad2.square) {
         elevator.toHighRungPosition();
      }
      else if (gamepad2.cross) {
         elevator.toHome();
      }

      // --- Gripper control ------------------
      if (gamepad2.left_bumper) {
         gripper.close();
         gamepad2.rumble(100);
         blinkin.green();
      }
      else if (gamepad2.right_bumper) {
         gripper.open();
         blinkin.white();
      }

      elevator.getElevatorTelemetry(telemetry);
      telemetry.addData("Gripper State: ", gripper.getState());
      mecanum.getMotorTelemetry(telemetry);
      telemetry.update();
   }
}