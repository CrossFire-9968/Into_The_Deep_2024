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
        int driveToBucket = 36;
        int driveToDump = 6;

        mecanumAuto.drive(-autoDrivePower, driveToBucket * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHighBucket();
        waitForElevatorToStop();
        sleep(300);

        mecanumAuto.drive(-autoDrivePower, driveToDump * countsToDriveOneInch);
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