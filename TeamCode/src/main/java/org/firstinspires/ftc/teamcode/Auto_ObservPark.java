package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="ObservPark")
public class Auto_ObservPark extends LinearOpMode
{
    public Blinkin blinkin = new Blinkin();
    public MecanumAuto mecanumAuto = new MecanumAuto();
    private ElapsedTime cameraTimer = new ElapsedTime();
    public Gripper gripper = new Gripper();
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
        gripper.init(hardwareMap);
        boolean isAutoComplete = false;

        // Init robot
        mecanumAuto.setAllMecanumPowers(0.0);
        blinkin.setColor(RevBlinkinLedDriver.BlinkinPattern.VIOLET);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Run this code while Autonomous has not timed out
        while (opModeIsActive() && !isAutoComplete) {
            strafeToPark();

           isAutoComplete = true;
        }
    }

    // Sequence of events for dropping the pixel on the center tape and then parking
    public void strafeToPark() {
        int countsToDriveOneInch = -33;         // Approximate encoder counts to drive 1 inch
        int strafeToObserv = 48                     ;

        // Drive to tape
//        mecanumAuto.drive(-autoDrivePower, driveDistanceToPixel * countsToDriveOneInch);
//        waitForMotionToComplete();
//        sleep(autoStateDelay);

        // Strafe to go around pixel
        mecanumAuto.strafe(-autoDrivePower, strafeToObserv * countsToDriveOneInch);
        waitForMotionToComplete();
        sleep(autoStateDelay);

        // corrective rotation
//        mecanumAuto.rotate(autoDrivePower,rotateCorrect);
//        waitForMotionToComplete();
//        sleep(autoStateDelay);


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
}