package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.json.JSONArray;

public class Ascent
{
   public CRServo Ascent_Servo;

   public DcMotor Pulley_Motor_L;

    public DcMotor Pulley_Motor_R;
   private String state;
   private static final double openPosition = 0.4;
   private static final double closedPosition = 0.80;

   public void init(HardwareMap hwMap) {
       {
           Ascent_Servo = hwMap.get(CRServo.class, "AscentServo");
           Ascent_Servo.setDirection(CRServo.Direction.FORWARD);
       }
       {

           Pulley_Motor_L = hwMap.get(DcMotor.class, "PulleyMotorL");
           Pulley_Motor_L.setDirection(DcMotor.Direction.FORWARD);
       }

       {
           Pulley_Motor_R = hwMap.get(DcMotor.class, "PulleyMotorL");
           Pulley_Motor_R.setDirection(DcMotor.Direction.FORWARD);
       }
   }


   public void ascentPull(Gamepad gamepad)
   {Pulley_Motor_L.setPower(1);
    Pulley_Motor_R.setPower(1);}

    public void ascentRaise(Gamepad gamepad)
    {Ascent_Servo.setPower(1);}

    public void ascentLower(Gamepad gamepad)
    {Ascent_Servo.setPower(-1);}

    public void ascentIdle(Gamepad gamepad)
    {Ascent_Servo.setPower(0);}

    public void ascentUnwind(Gamepad gamepad)
    {Pulley_Motor_L.setPower(-1);
    Pulley_Motor_R.setPower(-1);}

    public void ascentStop(Gamepad gamepad)
    {Pulley_Motor_L.setPower(0);
    Pulley_Motor_R.setPower(0);}}


