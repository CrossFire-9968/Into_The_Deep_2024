package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoTripleClip")
public class Auto_TripleClip extends LinearOpMode
{
    public Blinkin blinkin = new Blinkin();
    public Elevator elevator = new Elevator();
    public Gripper gripper = new Gripper();
    public Pivot pivot = new Pivot();
    public MecanumAuto mecanumAuto = new MecanumAuto();
    private ElapsedTime cameraTimer = new ElapsedTime();
    private long autoStateDelay = 300;
    private double autoDrivePower = 0.6;

    @Override
    public void runOpMode() throws InterruptedException
    {
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
        int driveToRung = 28;
        int driveToClip = 8;
        int backToWall = -12;
        int strafeToObserv = 40;
        int driveToSample = 45;
        int strafeToSample = 14;
        int backSample = -63;
        int autoDelay = 500;
        int rotateToGrab = 58;
        int driveToGrabSampleTwo = 9;
        int backUp = -12;
        int rotateToSub = -58;
        int strafeToSub = 40;
        int driveToClipAgain = 20;
        int driveToClipTwo = 5;
        int strafeToThree  = 36;
        int dontTripOnSample = 6;

        //first clip
        elevator.toHighRungPosition();
        telemetry.addLine("At high rung position");
        telemetry.update();

        mecanumAuto.drive(-autoDrivePower, driveToRung * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("drive to rung");
        telemetry.update();
        sleep(autoDelay);
//
//        mecanumAuto.strafe(autoDrivePower,strafeToCenter * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToClip * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("drive to clip");
        telemetry.update();
        sleep(autoDelay);

        elevator.toHighRungHookPosition();
        waitForElevatorToStop();
        telemetry.addLine("At high rung hook position");
        telemetry.update();
        sleep(300);

        gripper.open();
        telemetry.addLine("open gripper");
        telemetry.update();
        sleep(autoDelay);

        //second clip
        telemetry.addLine("elevator to home");
        telemetry.update();
        elevator.toHome();
        telemetry.addLine("elevator at home");
        telemetry.update();

        mecanumAuto.drive(-autoDrivePower, backToWall * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("back up to strafe");
        telemetry.update();

        // Strafe to grab sample
        mecanumAuto.strafe(-autoDrivePower, strafeToObserv * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("strafe from sub");
        telemetry.update();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToSample * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("drive up to sample");
        telemetry.update();
        sleep(autoDelay);

        mecanumAuto.strafe(-autoDrivePower, strafeToSample * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("strafe to sample");
        telemetry.update();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, backSample * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("push sample back to obs");
        telemetry.update();
        sleep(autoDelay);

        mecanumAuto.drive( -autoDrivePower, dontTripOnSample * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("drive forward so we don't get caught on sample");
        telemetry.update();
        sleep(autoDelay);

        mecanumAuto.rotate(-autoDrivePower, rotateToGrab * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("rotate to grab specimen on wall");
        telemetry.update();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, driveToGrabSampleTwo * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("drive forward to get sample two");
        telemetry.update();
        sleep(autoDelay);

        gripper.close();
        telemetry.addLine("close gripper to grab specimen");
        telemetry.update();
        sleep(autoDelay);

        mecanumAuto.drive(-autoDrivePower, backUp * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("back up");
        telemetry.update();
        sleep(autoDelay);

        mecanumAuto.rotate(-autoDrivePower, rotateToSub * countsToDriveOneInch);
        waitForMotionToComplete();
        telemetry.addLine("rotate to sub");
        telemetry.update();
        sleep(autoDelay);
//
//        mecanumAuto.strafe(-autoDrivePower, strafeToSub * countsToDriveOneInch);
//        waitForMotionToComplete();
//        telemetry.addLine("strafe to sub");
//        telemetry.update();
//        sleep(autoDelay);
//
//        mecanumAuto.drive(-autoDrivePower, driveToClipAgain * countsToDriveOneInch);
//        waitForMotionToComplete();
//        telemetry.addLine("drive to clip specimen 2");
//        telemetry.update();
//        sleep(autoDelay);
//
//        //clip second clip
//        elevator.toHighRungPosition();
//        waitForElevatorToStop();
//        telemetry.addLine("raise elevator to high rung position");
//        telemetry.update();
//        sleep(autoDelay);
//
//        mecanumAuto.drive(-autoDrivePower, driveToClipTwo * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        elevator.toHighRungHookPosition();
//        waitForElevatorToStop();
//        sleep(autoDelay);
//
//        gripper.open();
//        sleep(autoDelay);
//
//        //third specimen
//
//        mecanumAuto.drive(-autoDrivePower, backToWall * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        elevator.toHome();
//        waitForElevatorToStop();
//        sleep(autoDelay);
//
//        // Strafe to grab sample
//        mecanumAuto.strafe(-autoDrivePower, strafeToObserv * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        mecanumAuto.drive(-autoDrivePower, driveToSample * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        mecanumAuto.strafe(-autoDrivePower, strafeToThree * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        mecanumAuto.drive(-autoDrivePower, backSample * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        mecanumAuto.rotate(-autoDrivePower, rotateToGrab * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        mecanumAuto.drive(-autoDrivePower, driveToGrabSampleTwo * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        gripper.close();
//        sleep(autoDelay);
//
//        mecanumAuto.drive(-autoDrivePower, backUp * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        mecanumAuto.rotate(-autoDrivePower, rotateToSub * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        mecanumAuto.strafe(-autoDrivePower, strafeToSub * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        mecanumAuto.drive(-autoDrivePower, driveToClipAgain * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        elevator.toHighRungPosition();
//        waitForElevatorToStop();
//        sleep(autoDelay);
//
//        mecanumAuto.drive(-autoDrivePower, driveToClipTwo * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoDelay);
//
//        elevator.toHighRungHookPosition();
//        waitForElevatorToStop();
//        sleep(autoDelay);
//
//        gripper.open();
//        sleep(autoDelay);

        telemetry.addLine("End of Auto");
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

    public void waitForElevatorToStop(){
        while(elevator.ElevatorMotor.isBusy()){
            this.idle();
        }
    }
}
