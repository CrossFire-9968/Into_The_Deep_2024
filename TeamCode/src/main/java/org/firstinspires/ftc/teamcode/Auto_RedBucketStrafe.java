package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Auto_RedBucketStrafe extends LinearOpMode {
    public MecanumAuto mecanumAuto = new MecanumAuto();
    double driveSpeed = 0.5;

    private double autoDrivePower = 0.3;


    @Override
    public void runOpMode(){
        //Init
        mecanumAuto.init(hardwareMap);
        mecanumAuto.setAllMecanumPowers(0.0);
    }
    public void Park() {
        int countsToDriveOneInch = -33;
        int strafeToPark = -65;

        mecanumAuto.strafe(autoDrivePower, strafeToPark * countsToDriveOneInch);
    }

}
