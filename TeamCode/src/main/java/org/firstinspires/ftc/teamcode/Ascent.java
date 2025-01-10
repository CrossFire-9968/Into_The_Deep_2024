package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.json.JSONArray;

public class Ascent {
    public CRServo Ascent_Servo;

    public DcMotor Pulley_Motor_L;

    public DcMotor Pulley_Motor_R;


    public void init(HardwareMap hwMap) {

        Ascent_Servo = hwMap.get(CRServo.class, "AscentServo");
        Ascent_Servo.setDirection(CRServo.Direction.FORWARD);


        Pulley_Motor_L = hwMap.get(DcMotor.class, "PulleyMotorL");
        Pulley_Motor_L.setDirection(DcMotor.Direction.FORWARD);


        Pulley_Motor_R = hwMap.get(DcMotor.class, "PulleyMotorR");
        Pulley_Motor_R.setDirection(DcMotor.Direction.FORWARD);

    }


    public void ascentPull() {
        Pulley_Motor_L.setPower(1);
        Pulley_Motor_R.setPower(1);
    }

    public void ascentRaise() {
        Ascent_Servo.setPower(1);
    }

    public void ascentLower() {
        Ascent_Servo.setPower(-1);
    }

    public void ascentIdle() {
        Ascent_Servo.setPower(0);
        Pulley_Motor_L.setPower(0);
        Pulley_Motor_R.setPower(0);
    }

    public void ascentUnwind() {
        Pulley_Motor_L.setPower(-1);
        Pulley_Motor_R.setPower(-1);
    }
}




