package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;


public class ElevatorArm {
    public DcMotor elevator_Motor;
    public void init(HardwareMap hwMap){
        elevator_Motor = hwMap.get(DcMotor.class, "ElevatorMotor");
        elevator_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        //elevator_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        elevator_Motor.setPower(0.0);

    }

    public void elevator_control(Gamepad gpad) {
        if (gpad.dpad_up) {
            elevator_Motor.setPower(0.6);
        } else if (gpad.dpad_down) {
            elevator_Motor.setPower(-0.6);
        } else {
            elevator_Motor.setPower(0.0);
        }
    }
}
