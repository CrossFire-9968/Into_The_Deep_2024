//package org.firstinspires.ftc.teamcode;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//public class Auto_RedBucket extends LinearOpMode{
//    public MecanumAuto mecanumAuto = new MecanumAuto();
//    double driveSpeed = 0.5;
//    int rotateToBucket = 6;
//
//    public void runOpMode()throws InterruptedException{
//        mecanumAuto.init(hardwareMap);
//        mecanumAuto.setAllMecanumPowers(0.0);
//    }
//    public void driveToAprilTag(){
//        int strafeToAprilTag = 12;
//        int driveToAprilTag = 8;
//
//        mecanumAuto.strafe(driveSpeed,strafeToAprilTag);
//
//    public void aprilTagToBucket(){
//        //rotateBucket not exact
//        mecanumAuto.rotate(driveSpeed,rotateToBucket);
//    }
//}
