// computes the trajectory of a projectile.

import java.util.*;  // for Scanner
import java.util.List;
import javax.swing.*;

public class ProjectileMotion{

    private static double velocity;
    private static double angle;
    private static double height;
    private static int steps;

    private double xVelocity;
    private double yVelocity;
    private double totalTime;   
    private double timeIncrement;
    // constant for Earth acceleration in meters/second^2
    public static final double GRAVITY = 9.81;
   
   /*
    public static void main(String[] args) {
        //ProjectileMotion pm = new ProjectileMotion();
        ProjectileMotion pm = new ProjectileMotion(velocity, angle, height, steps);
        pm.table();
    }
   */
    /*
     * constructor to calculate PM data with given inputs
    */
    
    public ProjectileMotion() {
        //scanner to get user input
        Scanner sc = new Scanner(System.in);
        // read in initial velocity
        System.out.print("Enter initial velocity:\n> ");
        this.velocity = sc.nextDouble();
        // read in initial angle
        System.out.print("\nEnter initial angle:\n> ");
        this.angle = Math.toRadians(sc.nextDouble());
        // read in initial height
        System.out.print("\nEnter initial height:\n> ");
        this.height = Math.toRadians(sc.nextDouble());
        // read in how many steps/points on the curve = total time/steps
        System.out.print("\nEnter number of steps to display:\n> ");
        this.steps = sc.nextInt();

        System.out.println();

        // x-velocity = v0 cos theta
        this.xVelocity = velocity * Math.cos(angle);
        // y-velocity = v0 sin theta
        this.yVelocity = velocity * Math.sin(angle);
        // t = -2(y-v0)/g
        this.timeIncrement = (-2.0 * this.yVelocity / GRAVITY) / steps;
        this.steps = steps;
    }
    
   public ProjectileMotion(double v, double a, double h, int s){
    this.velocity = v;
    this.angle = Math.toRadians(a);
    this.height = h;
    this.steps = s;

    // x-velocity = v0 cos theta - remains constant
    this.xVelocity = velocity * Math.cos(angle);
    // y-velocity = v0 sin theta - changes over time
    this.yVelocity = velocity * Math.sin(angle);

    // total time = 1/g
    double totalTime = (1 / GRAVITY) * (yVelocity + Math.sqrt(Math.pow(yVelocity,2) + 2 * GRAVITY * height));
    this.totalTime = totalTime;
    // time increment = total time/steps == t = -2(y-v0)/g
    this.timeIncrement = totalTime / steps;
  }
    
     /*
   * creates a two-dimensional arraylist to store projectile date
   */
  public List<double[]> table(){
    List<double[]> projectile = new ArrayList<double[]>();

    // calculate position over the number of points
    for (int i = 0; i <= steps; i++){
      double time = i * timeIncrement;
      double xPosition = xVelocity * time;
      double yPosition = displacement(height, yVelocity, GRAVITY, time);

      // store each points x,y position and time in the array
      double[] moment = {time, xPosition, yPosition};
      projectile.add(moment);
    }

    return projectile;
  }

  /*
   * helper method used to calculate the y displacement over time
   */
  public double displacement(double height, double velocity, double g, double time){
    return (height + velocity * time - (GRAVITY * Math.pow(time,2))/2);
  }

  /*
   * helper method used to determine the max height / when Vy = 0
   */
  public double maxHeight() {
    return (height + (Math.pow(yVelocity, 2) / (2 * GRAVITY)));
  }

  /*
   * helper method used to determine the max range of a projectile
   */
  public double maxRange(){
    return (xVelocity * (steps * timeIncrement));
  }

  /*
   * helper method used to determine the total time in air of projectile
   */
   public double totalTime() {
       return this.totalTime;
   }


}
