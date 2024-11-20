package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Color_Sensor {


    // This class is a library of functions that supports the robot color sensors


    public static double hue = 0.0;
    public static double saturation = 0.0;
    public static double luminosity = 0.0;
    private static ColorSensor sensor;

    public void init(HardwareMap hwMap) {
        sensor = hwMap.get(ColorSensor.class, "ColorSensor");
    }


    // Calculate HSL values from sensor RGB sensor readings
    public void calculateHSL() {
        // Initialize variables
        int red = sensor.red();
        int green = sensor.green();
        int blue = sensor.blue();

        // Get minimum and maximum RGB values
        // Calculate difference between max and min
        double cmax = Math.max(red, Math.max(green, blue));   // maximum of r, g, b
        double cmin = Math.min(red, Math.min(green, blue));   // minimum of r, g, b
        double diff = cmax - cmin;                            // diff of cmax and cmin.

        // if cmax and cmax are equal then hue = 0
        if (cmax == cmin) {
            hue = 0;
        }

        // if cmax equals red value
        else if (cmax == red) {
            hue = (60 * ((green - blue) / diff) + 360) % 360;
        }

        // if cmax equal green value
        else if (cmax == green) {
            hue = (60 * ((blue - red) / diff) + 120) % 360;
        }

        // if cmax equal blue value
        else if (cmax == blue) {
            hue = (60 * ((red - green) / diff) + 240) % 360;
        }




        // Calculate saturation value
        if (cmax == 0) {
            saturation = 0;
        } else {
            saturation = (diff / cmax) * 100;
        }

        // compute luminosity
        luminosity = cmax * 100;
    }

    public double getHue() {
       calculateHSL();
        return hue;
    }
}

