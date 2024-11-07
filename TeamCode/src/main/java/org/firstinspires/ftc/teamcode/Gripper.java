package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gripper
{
   public Servo gripper_servo;
   private double closedPosition;
   private double openPosition;
   private String state;

   public void init(HardwareMap hwMap, Blinkin blinkin)
   {
      gripper_servo = hwMap.get(Servo.class, "GripperServo");
      gripper_servo.setDirection(Servo.Direction.REVERSE);

      // Start with gripper closed so element is held for autonomous
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