package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gripper
{
   public Servo gripper_servo;
   private String state;
   private final double openPosition = 0.3;
   private final double closedPosition = 0.7;

   public void init(HardwareMap hwMap, Blinkin blinkin)
   {
      gripper_servo = hwMap.get(Servo.class, "GripperServo");
      gripper_servo.setDirection(Servo.Direction.REVERSE);

      // Start with gripper closed so element is held for autonomous
      this.close();
   }

   public void close()
   {
      gripper_servo.setPosition(closedPosition);
      state = "Closed";
   }

   public void open()
   {
      gripper_servo.setPosition(openPosition);
      state = "Open";
   }

   public String getState()
   {
      return state;
   }
}