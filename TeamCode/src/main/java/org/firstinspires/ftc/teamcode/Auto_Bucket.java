package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoBucket")
public class Auto_Bucket extends LinearOpMode {
    public Blinkin blinkin = new Blinkin();
    public Elevator elevator = new Elevator();
    public Gripper gripper = new Gripper();
    public Pivot pivot = new Pivot();
    public MecanumAuto mecanumAuto = new MecanumAuto();
    private ElapsedTime cameraTimer = new ElapsedTime();
    private long autoStateDelay = 300;
    private double autoDrivePower = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        //-----------------------------------------------------------------------
        // INITIALIZATIONS
        // Our initialization code should go here before calling "WaitForStart()"
        //-----------------------------------------------------------------------
        blinkin.init(hardwareMap);
        mecanumAuto.init(hardwareMap);
        elevator.init(hardwareMap);
        gripper.init(hardwareMap);
        pivot.init(hardwareMap);
        boolean isAutoComplete = false;

        // Init robot
        mecanumAuto.setAllMecanumPowers(0.0);
        gripper.init(hardwareMap);
        pivot.init(hardwareMap);
        elevator.init(hardwareMap);

        //blinkin.setColor(RevBlinkinLedDriver.BlinkinPattern.VIOLET)

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Run this code while Autonomous has not timed out
        while (opModeIsActive() && !isAutoComplete) {
            bucketClip();

            isAutoComplete = true;
        }
    }
    public void bucketClip(){
        int countsToDriveOneInch = -33; // Approximate encoder counts to drive 1 inch
        int autoDelay = 250;
        int driveToRung = 26;
        int driveToClip = 8;
        int backUp = -12;
        int strafeToSamples = -38;
        int driveToClipOne = 40;
        int strafeToClipOne = -18;
        int pushClipOne = -59;
        int driveToClipTwo = 60;
        int strafeToClipTwo = -16;
        int pushClipTwo = -50;
        int driveToPark = 50;
        int rotateToSub = 30;
        int driveToSub = 32;
        int driveToBar = 6;

        mecanumAuto.drive(-autoDrivePower, driveToRung * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHighRungPosition();
        waitForElevatorToStop();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToClip * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHighRungHookPosition();
        waitForElevatorToStop();
        sleep(300);

        gripper.open();
        sleep(autoDelay);

        elevator.toHome();
        waitForElevatorToStop();
        sleep(300);

        //robot back up to before strafe over to samples
        mecanumAuto.drive(-autoDrivePower, backUp * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.strafe(-autoDrivePower, strafeToSamples * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToClipOne * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.strafe(-autoDrivePower, strafeToClipOne * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, pushClipOne * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToClipTwo * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.strafe(-autoDrivePower, strafeToClipTwo * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, pushClipTwo * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToPark * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.rotate(-autoDrivePower, rotateToSub * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToSub * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHighRungPosition();
        waitForElevatorToStop();
        sleep(500);

        mecanumAuto.drive(-autoDrivePower, driveToBar * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        pivot.pivot_Down();
        waitForMotionToComplete();
        sleep(autoDelay);

    }

    public void waitForMotionToComplete() {

        // Only wait until one of the wheels reaches position. At that point, stop all the motors
        while (mecanumAuto.motor_LF.isBusy() && mecanumAuto.motor_RF.isBusy() && mecanumAuto.motor_RR.isBusy() && mecanumAuto.motor_LR.isBusy()) {
            this.idle();
        }

        mecanumAuto.stopAndResetEncoders();
    }

    public void waitForElevatorToStop() {
        while (elevator.elevator_Motor.isBusy()) {
            this.idle();
        }
    }
}
