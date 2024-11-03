package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


public class ElevatorArm {
    public DcMotor elevator_Motor;

    public Servo Gripper_servo;

    public Servo Pivot_servo;


    public void init(HardwareMap hwMap) {
        elevator_Motor = hwMap.get(DcMotor.class, "ElevatorMotor");
        elevator_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        //elevator_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevator_Motor.setPower(0.0);

        Gripper_servo = hwMap.get(Servo.class, "Gripper_servo");
        Gripper_servo.setDirection(Servo.Direction.FORWARD);

        Pivot_servo = hwMap.get(Servo.class, "Pivot_servo");
        Pivot_servo.setDirection(Servo.Direction.FORWARD);


    }

    public void liftRaise() {
        elevator_Motor.setPower(0.6);
    }

    public void liftLower() {
        elevator_Motor.setPower(-0.6);
    }

    public void liftPowerOff() {
        elevator_Motor.setPower(0.0);
    }

    public void Gripper_servo(Gamepad gpad) {
        if (gpad.left_bumper) {
            Gripper_servo.setPosition(30);
        }

        if (gpad.right_bumper) {
            Gripper_servo.setPosition(120);
        }
    }

    public void Pivot_servo(Gamepad gpad) {
        if (gpad.dpad_left) {
            Pivot_servo.setPosition(30);
        }

        if (gpad.dpad_right) {
            Pivot_servo.setPosition(120);
        }
    }
}

