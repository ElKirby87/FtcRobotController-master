package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class MecanumDrive extends SubsystemBase {
    private DcMotorEx leftFront = null;
    private DcMotorEx rightFront = null;
    private DcMotorEx leftBack = null;
    private DcMotorEx rightBack = null;

    public void DriveInit(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void Drive(double drive, double strafe, double twist) {

        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(twist), 1);
        double frontLeftPower = (drive + strafe + twist) / denominator;
        double backLeftPower = (drive - strafe + twist) / denominator;
        double frontRightPower = (drive - strafe - twist) / denominator;
        double backRightPower = (drive + strafe - twist) / denominator;

        leftFront.setPower(frontLeftPower * 1.25);
        rightFront.setPower(frontRightPower * 0.75);
        leftBack.setPower(backLeftPower * 1.25);
        rightBack.setPower(backRightPower * 1);
    }


}
