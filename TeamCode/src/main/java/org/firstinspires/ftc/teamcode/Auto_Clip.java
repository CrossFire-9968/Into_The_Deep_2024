package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoClip")
public class Auto_Clip extends LinearOpMode
{
    public Blinkin blinkin = new Blinkin();
    public Elevator elevator = new Elevator();
    public Gripper gripper = new Gripper();
    public Pivot pivot = new Pivot();
    public MecanumAuto mecanumAuto = new MecanumAuto();
    private ElapsedTime cameraTimer = new ElapsedTime();
    private long autoStateDelay = 300;
    private double autoDrivePower = 0.3;

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
        int strafeToCenter = -14;
        int driveToRung = 28;
        int driveToClip = 8;
        int backToWall = -12;
        int oneSecondDelay = 1000;

        mecanumAuto.drive(-autoDrivePower, driveToRung * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(oneSecondDelay);

        mecanumAuto.strafe(autoDrivePower,strafeToCenter * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(oneSecondDelay);

        elevator.toHighRungPosition();
        waitForElevatorToStop();
        sleep(oneSecondDelay);

        mecanumAuto.drive(-autoDrivePower, driveToClip * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(oneSecondDelay);

        elevator.toHighRungHookPosition();
        waitForElevatorToStop();
        sleep(300);

        gripper.open();
        sleep(oneSecondDelay);

        mecanumAuto.drive(-autoDrivePower, backToWall * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(oneSecondDelay);

        elevator.toHome();
        waitForElevatorToStop();
        sleep(oneSecondDelay);
    }

    public void parkInAscent() {
        int countsToDriveOneInch = -33;         // Approximate encoder counts to drive 1 inch
        int strafeToAscent = 12;
        int driveDistanceToAscent = 78;
        int rotateToAscent = -950;
        int driveToBar = 4;

        //Drive to ascent
        mecanumAuto.drive(-autoDrivePower, driveDistanceToAscent * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoStateDelay);

        // Strafe to go to ascent
        mecanumAuto.strafe(-autoDrivePower, strafeToAscent * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoStateDelay);

        // Raise arm to touch lower rung
        elevator.toHighRungPosition();

        //rotate to ascent
        mecanumAuto.rotate(autoDrivePower,rotateToAscent);
        waitForMotionToComplete();
        sleep(autoStateDelay);

        //Drive to ascent
        mecanumAuto.drive(-autoDrivePower, driveToBar * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoStateDelay);

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
        while(elevator.elevator_Motor.isBusy()){
            this.idle();
        }
    }
}