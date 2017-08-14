package org.firstinspires.ftc.teamcode.Tutorial;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Student on 8/12/2017.
 */

@Autonomous(name="Autonomous_Tutorial")
@Disabled

public class Autonomous_Tutorial extends LinearOpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        //The robot's power is 0.5, it will go 48 inches forward, with a 3 second timeout before going to its next move
        //encoderDrive(0.5,48,48,3);

        //encoderDrive(0.7,30,0,4);

        encoderDrive(1,48,48,.9001);

        encoderDrive(1,-10,10,.9001);

        encoderDrive(1,-25,-25,.9001);

        //The robot will drive forward for 4 seconds

        /*arcadeDrive(0.5,0);
        runtime.reset();
        while(opModeIsActive() &&(runtime.seconds() < 4.0)){
            telemetry.addData("Path", "1: Drive forward for 4 seconds", runtime.seconds());
            telemetry.update();
        }

        //The robot will drive backwards for 6 seconds

        arcadeDrive(-0.5,0);
        runtime.reset();
        while(opModeIsActive() &&(runtime.seconds() < 6.0)){
            telemetry.addData("Path", "1: Drive backwards for 6 seconds", runtime.seconds());
            telemetry.update();
        }*/

        //always put this at the end
        idle();

    }
    public void arcadeDrive(double power, double turn) {
        double leftPower = Range.clip(power + turn, -1,1);
        double rightPower = Range.clip(power - turn, -1,1);

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS)throws InterruptedException{

        int newLeftTarget;
        int newRightTarget;

        if(opModeIsActive()){
            newLeftTarget = leftMotor.getCurrentPosition()*(int) (leftInches*1440);
            newRightTarget = rightMotor.getCurrentPosition()*(int) (rightInches*1440);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);

            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            leftMotor.setPower(speed);
            rightMotor.setPower(speed);

            while(opModeIsActive()&&(runtime.seconds() < timeoutS)&&(leftMotor.isBusy()&&rightMotor.isBusy())){

                idle();
            }

        }

    }

}

