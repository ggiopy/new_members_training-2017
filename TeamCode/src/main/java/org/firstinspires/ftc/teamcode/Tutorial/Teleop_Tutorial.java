package org.firstinspires.ftc.teamcode.Tutorial;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Student on 8/12/2017.
 */

@TeleOp(name="Teleop_Tutorial")
@Disabled
public class Teleop_Tutorial extends OpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private Servo arm;

    @Override
    public void init() {
        //hardware map
        //syntax: variable name = hardwareMap._____.get("name_goes_here");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        arm = hardwareMap.servo.get("arm");

        //assuming that the right motor and the arm will be running in the forward direction
        //must reverse one motor, left motor is reversed
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
    }
    //make sure that all powers are set to zero before the robot starts to function
    @Override
    public void loop() {
        leftMotor.setPower(0.0);
        rightMotor.setPower(0);
        arm.setPosition(0);
    }

    @Override
    public void stop() {
        arcadeDrive(-1*gamepad1.left_stick_y, gamepad1.right_stick_x);
    }

    public void arcadeDrive(double power, double turn) {
        double leftPower = Range.clip(power + turn, -1,1);
        double rightPower = Range.clip(power - turn, -1,1);

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }
}
