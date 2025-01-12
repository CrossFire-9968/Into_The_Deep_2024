package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoDoubleClip")
public class Auto_DoubleClip extends LinearOpMode {
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
            clipSpecimen();

            isAutoComplete = true;
        }
    }

    public void clipSpecimen() {
        int countsToDriveOneInch = -33; // Approximate encoder counts to drive 1 inch
        int driveToRung = 25;
        int driveToClip = 8;
        int strafeNextSpecimen = 48;
        int rotateToSample = 1900;
        int driveToSpecimen = 14;
        int driveToGrab = 5;
        int driveToNextClip = 22;
        int strafe2ndClip = 55;
        int rotateToSub = 1900;
        int strafeToPark = 45;
        int backToPark = 24;
        int autoDelay = 250;

        mecanumAuto.drive(-autoDrivePower, driveToRung * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

//        mecanumAuto.strafe(autoDrivePower, strafeToCenter * countsToDriveOneInch);
//        waitForMotionToComplete();
//           sleep(autoDelay);

        elevator.toHighRungPosition();
        waitForElevatorToStop();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToClip * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHighRungHookPosition();
        waitForElevatorToStop();
        //sleep(200);

        gripper.open();
        sleep(autoDelay);

        elevator.toHome();
        waitForElevatorToStop();
        //sleep(autoDelay);

        mecanumAuto.drive(autoDrivePower, -10 * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.strafe(-autoDrivePower, strafeNextSpecimen * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.rotate(-autoDrivePower, rotateToSample);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToSpecimen * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(1000);

        mecanumAuto.drive(-autoDrivePower, driveToGrab * countsToDriveOneInch);
        waitForMotionToComplete();
//        sleep(autoDelay);

        gripper.close();
        sleep(1000);

        elevator.toLowRungPosition();
        waitForElevatorToStop();
        //sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, -driveToNextClip * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.strafe(-autoDrivePower, strafe2ndClip * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

       mecanumAuto.rotate(-autoDrivePower, -rotateToSub);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHighRungPosition();
        waitForElevatorToStop();
        //sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, 11 * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHighRungHookPosition();
        waitForElevatorToStop();
        //sleep(200);

        gripper.open();
//        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, -10 * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        elevator.toHome();
        waitForElevatorToStop();

        mecanumAuto.strafe(-autoDrivePower, strafeToPark * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, -backToPark * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoDelay);
    }

    // Drive until one of the 4 wheels has reached it's target position. We only wait for one
    // because it is not guaranteed all 4 wheels will reach their target at the same time due
    // to inconsistencies in alignment and resistance in the drivetrain. If we wait for all 4 wheels
    // They will start fighting one another in a tug-of-war type effect and we may not transition
    // to the next stage as we expect.
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