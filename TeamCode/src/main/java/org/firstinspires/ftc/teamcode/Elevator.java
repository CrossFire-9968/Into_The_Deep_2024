package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator
{
   public DcMotor elevator_Motor;
   private double elevatorRaiseSpeed;
   private double elevatorLowerSpeed;


   public void init(HardwareMap hwMap)
   {
      elevator_Motor = hwMap.get(DcMotor.class, "ElevatorMotor");
      elevator_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
      //elevator_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      // Explicitly initialize with motor power off for safety
      this.stop();
   }

   public void setRaiseSpeed(double speed)
   {
      this.elevatorRaiseSpeed = speed;
   }

   public void setLowerSpeed(double speed)
   {
      this.elevatorLowerSpeed = speed;
   }

   public void raise()
   {
      elevator_Motor.setPower(elevatorRaiseSpeed);
   }

   public void lower()
   {
      elevator_Motor.setPower(elevatorLowerSpeed);
   }

   public void stop()
   {
      elevator_Motor.setPower(0.0);
   }
}

