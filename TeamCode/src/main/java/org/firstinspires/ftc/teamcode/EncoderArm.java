package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class EncoderArm {
    public Servo gripper;
    public DcMotor armMotor;
    double armSpeedUp = 0.4;
    double armSpeedDown = 0.2;
//    int armMotorPosition = 500;
//    int armMotorHomePosition = 0;
//    int motorHangPosition = 700;

    public void init (HardwareMap hwMap){
        armMotor = hwMap.get(DcMotor.class, "armMotor");
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armMotor.setPower(0.0);
    }

    public int armControl(Gamepad gamepad) {
        if (gamepad.a) {
            armMotor.setPower(armSpeedDown);
            armMotor.setTargetPosition(gripperArmHomePosition);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad.y) {
            armMotor.setPower(armSpeedUp);
            armMotor.setTargetPosition(gripperArmPixelPosition);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (gamepad.x) {
            armMotor.setPower(armSpeedUp);
            armMotor.setTargetPosition(gripperArmHangPosition);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        return armMotor.getCurrentPosition();
    }

}
