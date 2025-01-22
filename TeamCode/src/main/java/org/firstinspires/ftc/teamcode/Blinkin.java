

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Blinkin {
   public RevBlinkinLedDriver blinkin;

   public void init(HardwareMap hwMap){
      blinkin = hwMap.get(RevBlinkinLedDriver.class, "blinkin");
      blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
   }

   public void setColor(RevBlinkinLedDriver.BlinkinPattern color){
      blinkin.setPattern(color);
   }



}

//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//public class Blinkin
//{
//   public RevBlinkinLedDriver blinkin;
//
//   public void init(HardwareMap hwMap)
//   {
//      blinkin = hwMap.get(RevBlinkinLedDriver.class, "blinkin");
//
//      // Color to start with after hitting init button
//      blue();
//   }
//
//   public void setColor(RevBlinkinLedDriver.BlinkinPattern color)
//   {
//      blinkin.setPattern(color);
//   }
//
//   public void blue()
//   {
//      blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
//   }
//
//   public void green()
//   {
//      blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
//   }
//   public void red()
//   {
//      blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
//   }
//
//   public void white()
//   {
//      blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
//   }
//}