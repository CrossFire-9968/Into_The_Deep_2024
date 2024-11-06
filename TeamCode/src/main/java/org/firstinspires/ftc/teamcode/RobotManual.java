package org.firstinspires.ftc.teamcode;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;


@TeleOp(name = "Robot Manual")
public class RobotManual extends OpMode {
    public Mecanum mecanum =  new Mecanum();
    public Blinkin blinkin = new Blinkin();
    public ElevatorArm elevator = new ElevatorArm();

    public Slide slide = new Slide();

    public void init() {
        mecanum.init(hardwareMap);
        blinkin.init(hardwareMap);
        elevator.init(hardwareMap);
    }

    @Override
    public void loop() {
        mecanum.manualDrive(gamepad1, telemetry);

        if (gamepad1.square == true) {
            blinkin.setColor(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        }

        if(gamepad2.dpad_up) {
            elevator.liftRaise();
            telemetry.addLine("liftRaise");
        }
        else if(gamepad2.dpad_down) {
            elevator.liftLower();
            telemetry.addLine("liftLower");
        }
        else{
            elevator.liftPowerOff();
            telemetry.addLine("liftPowerOff");
        }

        elevator.Gripper_servo(gamepad2);
//        elevator.Pivot_servo(gamepad2);
        telemetry.update();
    }
}