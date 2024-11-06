package org.firstinspires.ftc.teamcode;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;


@TeleOp(name = "Robot Manual")
public class RobotManual extends OpMode
{
   public Mecanum mecanum = new Mecanum();
   public Blinkin blinkin = new Blinkin();
   public Elevator elevator = new Elevator();
   public Gripper gripper = new Gripper();
   public Slide slide = new Slide();

   public RobotManual()
   {
      elevator.setRaiseSpeed(0.6);
      elevator.setLowerSpeed(-0.6);
      gripper.setOpenPosition(1.0);
      gripper.setClosedPosition(0.9);
   }

   public void init()
   {
      mecanum.init(hardwareMap);
      blinkin.init(hardwareMap);
      elevator.init(hardwareMap);
      gripper.init(hardwareMap);
   }

   @Override
   public void loop()
   {
      mecanum.manualDrive(gamepad1, telemetry);

      // Control elevator
      if (gamepad2.dpad_up) {
         elevator.raise();
      }
      else if (gamepad2.dpad_down) {
         elevator.lower();
      }
      else {
         elevator.stop();
      }

      // Control gripper
      if (gamepad2.left_bumper) {
         gripper.close();
      }
      else if (gamepad2.right_bumper) {
         gripper.open();
      }

      // Make the thingy blingy
      if (gamepad1.square) {
         blinkin.setColor(RevBlinkinLedDriver.BlinkinPattern.GREEN);
      }

        telemetry.update();}
}