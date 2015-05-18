package helpers;

public class ExtMath {

    public static double addToAngle(double angle, double add) {
        double r = angle + add;
        while (r < 0) r += 360;
        while (r >= 360) r -= 360;

        return r;
    }

}
