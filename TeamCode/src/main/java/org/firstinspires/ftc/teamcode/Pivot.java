
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Pivot {

    public Servo Pivot_servo;

    public void init(HardwareMap hwMap) {

        Pivot_servo = hwMap.get(Servo.class, "Pivot_servo");
        Pivot_servo.setDirection(Servo.Direction.FORWARD);

        pivot_Up();
    }


    public void pivot_Down() {
        Pivot_servo.setPosition(0.1);
    }


    public void pivot_Up() {
        Pivot_servo.setPosition(0.5);
    }
}
