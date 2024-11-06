package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gripper
{
   public Servo Gripper_servo;
   private double closedPosition;
   private double openPosition;

   public void init(HardwareMap hwMap)
   {
      Gripper_servo = hwMap.get(Servo.class, "GripperServo");
      Gripper_servo.setDirection(Servo.Direction.REVERSE);

      // Start with gripper closed so element is held for auto
      this.close();
   }

   public void setClosedPosition(double position)
   {
      this.closedPosition = position;
   }

   public void setOpenPosition(double position)
   {
      this.openPosition = position;
   }

   public void close()
   {
      Gripper_servo.setPosition(closedPosition);
   }

   public void open()
   {
      Gripper_servo.setPosition(openPosition);
   }
}