package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Robot Manual")
public class RobotManual extends OpMode
{
   public Blinkin blinkin = new Blinkin();
   public Mecanum mecanum = new Mecanum();
   public Elevator elevator = new Elevator();
   public Gripper gripper = new Gripper();
   public Color_Sensor colorSensor = new Color_Sensor();

   @Override
   public void init() {

      blinkin.init(hardwareMap);
      mecanum.init(hardwareMap);
      elevator.init(hardwareMap);
      gripper.init(hardwareMap);
      colorSensor.init(hardwareMap);
   }





   @Override
   public void loop()
   {
      mecanum.manualDrive(gamepad1, telemetry);
      double hue = colorSensor.getHue();
      if ((hue >= 70) && (hue <= 90)){
         telemetry.addData("Yellow", hue);
         blinkin.setColor(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
      }
      else if ((hue >= 10) && (hue <= 30)){
         telemetry.addData("Red", hue);
         blinkin.setColor(RevBlinkinLedDriver.BlinkinPattern.RED);
   }
      else if ((hue >= 210) && (hue <= 230)){
         telemetry.addData("Blue", hue);
         blinkin.setColor(RevBlinkinLedDriver.BlinkinPattern.BLUE);
      }
      else {
         telemetry.addData("Unknown", hue);
         blinkin.setColor(RevBlinkinLedDriver.BlinkinPattern.GREEN);
      }

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
//         blinkin.green();
      }
      else if (gamepad2.right_bumper) {
         gripper.open();
//         blinkin.white();
      }
      elevator.getElevatorTelemetry(telemetry);
      telemetry.addData("Gripper State: ", gripper.getState());
      telemetry.addData("Color Hue: ", colorSensor.getHue());
      mecanum.getMotorTelemetry(telemetry);
      telemetry.update();
   }
}