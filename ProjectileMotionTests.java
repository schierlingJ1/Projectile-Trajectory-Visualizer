import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ProjectileMotionTests{
  
  double delta = 0.01;
  
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("ProjectileMotionTests");
  }
  
  @Test public void constructor1(){
    ProjectileMotion a = new ProjectileMotion(45.0, 45.0, 0.0, 10);
    assertEquals(45.0, a.getVelocity(), delta);
    assertEquals(Math.toRadians(45.0), a.getAngle(), delta);
    assertEquals(0.0, a.getHeight(), delta);
  }
  
  @Test public void constructor2(){
    ProjectileMotion a = new ProjectileMotion(45.0, 45.0, 200.0, 10);
    assertEquals(45.0, a.getVelocity(), delta);
    assertEquals(Math.toRadians(45.0), a.getAngle(), delta);
    assertEquals(200.0, a.getHeight(), delta);
  }
  
  @Test public void constructor3(){
    ProjectileMotion a = new ProjectileMotion(100.0, 89.9, 0.0, 10);
    assertEquals(100.0, a.getVelocity(), delta);
    assertEquals(Math.toRadians(89.9), a.getAngle(), delta);
    assertEquals(0.0, a.getHeight(), delta);
  }
  
  @Test public void constructor4(){
    ProjectileMotion a = new ProjectileMotion(100.0, 89.9, 100.0, 10);
    assertEquals(100.0, a.getVelocity(), delta);
    assertEquals(Math.toRadians(89.9), a.getAngle(), delta);
    assertEquals(100.0, a.getHeight(), delta);
  }
  
  @Test public void constructor5(){
    ProjectileMotion a = new ProjectileMotion(100.0, 5.5, 0.0, 10);
    assertEquals(100.0, a.getVelocity(), delta);
    assertEquals(Math.toRadians(5.5), a.getAngle(), delta);
    assertEquals(0.0, a.getHeight(), delta);
  }
  
  @Test public void constructor6(){
    ProjectileMotion a = new ProjectileMotion(100.0, 5.5, 300.0, 10);
    assertEquals(100.0, a.getVelocity(), delta);
    assertEquals(Math.toRadians(5.5), a.getAngle(), delta);
    assertEquals(300.0, a.getHeight(), delta);
  }
  
  @Test public void constructor7(){
    ProjectileMotion a = new ProjectileMotion(4.4, 75.7, 0.0, 10);
    assertEquals(4.4, a.getVelocity(), delta);
    assertEquals(Math.toRadians(75.7), a.getAngle(), delta);
    assertEquals(0.0, a.getHeight(), delta);
  }
  
  @Test public void constructor8(){
    ProjectileMotion a = new ProjectileMotion(4.4, 75.7, 500.0, 10);
    assertEquals(4.4, a.getVelocity(), delta);
    assertEquals(Math.toRadians(75.7), a.getAngle(), delta);
    assertEquals(500.0, a.getHeight(), delta);
  }
  
  @Test public void calculate1(){
    ProjectileMotion a = new ProjectileMotion(45.0, 45.0, 0.0, 10);
    assertEquals(31.81, a.getxVelocity(), delta);
    assertEquals(31.81, a.getyVelocity(), delta);
    assertEquals(206.42, a.maxRange(), delta);
    assertEquals(51.60, a.maxHeight(), delta);
    assertEquals(6.48, a.totalTime(), delta);
  }
  
  @Test public void calculate2(){
    ProjectileMotion a = new ProjectileMotion(45.0, 45.0, 200.0, 10);
    assertEquals(31.81, a.getxVelocity(), delta);
    assertEquals(31.81, a.getyVelocity(), delta);
    assertEquals(331.10, a.maxRange(), delta);
    assertEquals(251.60, a.maxHeight(), delta);
    assertEquals(10.40, a.totalTime(), delta);
  }
  
  @Test public void calculate3(){
    ProjectileMotion a = new ProjectileMotion(100.0, 89.9, 0.0, 10);
    assertEquals(0.17, a.getxVelocity(), delta);
    assertEquals(99.99, a.getyVelocity(), delta);
    assertEquals(3.55, a.maxRange(), delta);
    assertEquals(509.68, a.maxHeight(), delta);
    assertEquals(20.38, a.totalTime(), delta);
  }
  
  @Test public void calculate4(){
    ProjectileMotion a = new ProjectileMotion(100.0, 89.9, 100.0, 10);
    assertEquals(0.17, a.getxVelocity(), delta);
    assertEquals(99.99, a.getyVelocity(), delta);
    assertEquals(3.72, a.maxRange(), delta);
    assertEquals(609.68, a.maxHeight(), delta);
    assertEquals(21.34, a.totalTime(), delta);
  }
  
  @Test public void calculate5(){
    ProjectileMotion a = new ProjectileMotion(100.0, 5.5, 0.0, 10);
    assertEquals(99.53, a.getxVelocity(), delta);
    assertEquals(9.58, a.getyVelocity(), delta);
    assertEquals(194.50, a.maxRange(), delta);
    assertEquals(4.68, a.maxHeight(), delta);
    assertEquals(1.95, a.totalTime(), delta);
  }
  
  @Test public void calculate6(){
    ProjectileMotion a = new ProjectileMotion(100.0, 5.5, 300.0, 10);
    assertEquals(99.53, a.getxVelocity(), delta);
    assertEquals(9.58, a.getyVelocity(), delta);
    assertEquals(881.76, a.maxRange(), delta);
    assertEquals(304.68, a.maxHeight(), delta);
    assertEquals(8.85, a.totalTime(), delta);
  }
  
  @Test public void calculate7(){
    ProjectileMotion a = new ProjectileMotion(4.4, 75.7, 0.0, 10);
    assertEquals(1.08, a.getxVelocity(), delta);
    assertEquals(4.26, a.getyVelocity(), delta);
    assertEquals(0.94, a.maxRange(), delta);
    assertEquals(0.92, a.maxHeight(), delta);
    assertEquals(0.86, a.totalTime(), delta);
  }
  
  @Test public void calculate8(){
    ProjectileMotion a = new ProjectileMotion(4.4, 75.7, 500.0, 10);
    assertEquals(1.08, a.getxVelocity(), delta);
    assertEquals(4.26, a.getyVelocity(), delta);
    assertEquals(11.45, a.maxRange(), delta);
    assertEquals(500.92, a.maxHeight(), delta);
    assertEquals(10.54, a.totalTime(), delta);
  }
  
}