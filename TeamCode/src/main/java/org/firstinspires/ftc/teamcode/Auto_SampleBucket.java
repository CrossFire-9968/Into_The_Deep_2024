package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoSampleBucket")
public class Auto_SampleBucket extends LinearOpMode {
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
            bucketSample();

            isAutoComplete = true;
        }
    }

    public void bucketSample(){
        int countsToDriveOneInch = -33; // Approximate encoder counts to drive 1 inch
        int autoDelay = 250;
        int driveToBucket = 16;
        int driveToDump = 6;
        int strafeToGrab = 37;
        int rotateToSample = 28;
        int rotateToSampleTwo = 45;
        int rotateToBucket = 70;
        int driveNextSample = 13;
        int backUp = -8;
        int strafeToBucket = 30;
        int backUpBucket = -5;

        //Dump the first sample
        mecanumAuto.drive(-autoDrivePower, driveToBucket * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toLowBucket();
        waitForElevatorToStop();
        sleep(300);

        mecanumAuto.drive(-autoDrivePower, driveToDump * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        gripper.open();
        sleep(autoDelay);

        //Get 2nd sample
        mecanumAuto.drive(-autoDrivePower, backUp * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHome();
        waitForElevatorToStop();
        sleep(300);

        mecanumAuto.strafe(-autoDrivePower, strafeToGrab * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.rotate(-autoDrivePower, rotateToSample * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        pivot.pivot_Down();
        sleep(autoDelay);

        gripper.close();
        sleep(autoDelay);

        pivot.pivot_Up();
        sleep(autoDelay);

        //Dump 2nd sample
        mecanumAuto.rotate(-autoDrivePower, rotateToBucket * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToBucket * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.strafe(-autoDrivePower, -4 *countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toLowBucket();
        waitForElevatorToStop();
        sleep(300);

        mecanumAuto.drive(-autoDrivePower, driveToDump *countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        gripper.open();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, backUpBucket * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHome();

        // snatch 3rd sample
        mecanumAuto.rotate(-autoDrivePower, rotateToSampleTwo * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

//        mecanumAuto.strafe(-autoDrivePower, 7 * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveNextSample * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        pivot.pivot_Down();
        sleep(autoDelay);

        gripper.close();
        sleep(autoDelay);

        pivot.pivot_Up();
        sleep(autoDelay);

        // dump 3rd sample
        mecanumAuto.rotate(-autoDrivePower, rotateToBucket * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.strafe(-autoDrivePower, -16 * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, 10 * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toLowBucket();
        waitForElevatorToStop();
        sleep(300);

        mecanumAuto.drive(-autoDrivePower, driveToDump *countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        gripper.open();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, backUp *countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHome();

        mecanumAuto.rotate(-autoDrivePower, 75 * countsToDriveOneInch);
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
        while (elevator.ElevatorMotor.isBusy()) {
            this.idle();
        }
    }
}