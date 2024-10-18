package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;


@TeleOp(name = "Robot Manual")
public class RobotManual extends OpMode {
    public Mecanum mecanum =  new Mecanum();
    public Blinkin blinkin = new Blinkin();

    public void init() {
        mecanum.init(hardwareMap);
        blinkin.init(hardwareMap);
    }

    @Override
    public void loop() {
        mecanum.manualDrive(gamepad1, telemetry);
        if (gamepad1.square == true) {
            blinkin.setColor(RevBlinkinLedDriver.BlinkinPattern.GREEN);

        telemetry.update();}
    }
}