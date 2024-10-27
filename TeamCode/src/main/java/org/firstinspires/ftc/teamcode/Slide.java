package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



public class Slide {
    public DcMotor Slide_motor;

    public void init(HardwareMap hwMap) {
        Slide_motor = hwMap.get(DcMotor.class, "Slide_motor");
        Slide_motor.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void Slide_Control(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            Slide_motor.setPower(-1.0);
            Slide_motor.setPower(1.0);
        }
    }
}