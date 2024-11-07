package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "PID Robot")
public class PIDrobot extends OpMode
{
   public PIDtest pidTest = new PIDtest();

   public void init()
   {
      pidTest.init(hardwareMap);
   }

   @Override
   public void loop()
   {
      pidTest.pid(telemetry);

      telemetry.update();
   }
}