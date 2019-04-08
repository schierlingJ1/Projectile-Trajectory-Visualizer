// computes the trajectory of a projectile.

import java.util.*;  // for Scanner
import java.util.List;

public class ProjectileMotion{

    private double velocity;
    private double angle;
    private int steps;

    private double xVelocity;
    private double yVelocity;
    private double timeIncrement;

    // constant for Earth acceleration in meters/second^2
    public static final double GRAVITY = -9.81;

    public static void main(String[] args) {
        ProjectileMotion pm = new ProjectileMotion();
        pm.table();
    }

    /*
     * constructor to calculate PM data with given inputs
    */
    public ProjectileMotion() {
        //scanner to get user input
        Scanner sc = new Scanner(System.in);
        // read in initial velocity
        System.out.print("Enter initial velocity:\n> ");
        double velocity = sc.nextDouble();
        this.velocity = velocity;
        // read in initial angle
        System.out.print("\nEnter initial angle:\n> ");
        double angle = Math.toRadians(sc.nextDouble());
        this.angle = angle;
        // read in how many steps/points on the curve = total time/steps
        System.out.print("\nEnter number of steps to display:\n> ");
        int steps = sc.nextInt();
        this.steps = steps;

        System.out.println();

        // x-velocity = v0 cos theta
        this.xVelocity = velocity * Math.cos(angle);
        // y-velocity = v0 sin theta
        this.yVelocity = velocity * Math.sin(angle);
        // t = -2(y-v0)/g
        this.timeIncrement = (-2.0 * this.yVelocity / GRAVITY) / steps;
        this.steps = steps;
    }

    /*
     * create 2D arraylist to store PM data
    */
    public List<double[]> table() {
        List<double[]> projectile = new ArrayList<double[]>();
        System.out.println("step\tx\ty\ttime");

        //calculate over the range of steps
        for (int i = 0; i <= this.steps; i++) {

            double time = i * timeIncrement;
            double xDisplacement = this.xVelocity * time;
            if (i == steps) {
                this.maxDistance = xDisplacement;
            }
            double yDisplacement = displacement(this.yVelocity, GRAVITY, time);

            System.out.printf("%d\t%.2f\t%.2f\t%.2f\n", i, xDisplacement, yDisplacement, time);

            //put them into 2D array
            double[] moment = {time, xDisplacement, yDisplacement};
            projectile.add(moment);
        }
        return projectile;
    }

    /*
     * helper to compute the change in position of a projectile
    */
    public double displacement(double velocity, double acceleration, double time) {
        return (velocity * time + 0.5 * acceleration * Math.pow(time, 2));
    }

    /*
     * helper to compute the max height of a projectile
    */
    public double maxHeight() {
        return (Math.pow(this.velocity, 2) * Math.pow(Math.sin(this.angle), 2)) / (2 * GRAVITY * -1);
    }

}
