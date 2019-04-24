// import for j swing
import java.awt.*; 
import javax.swing.*;

public class Randomize extends JPanel{
  
  //int xMax = 800;
  int yMax = 712;
  
  public Randomize() {
  
  }
  
  @Override public void paint(Graphics g){
    Graphics2D thingy = (Graphics2D) g;
    
    int width = (int)(Math.random()*100+100); //random width of building
    int height = (int)(Math.random()*350+100); // random height for the first building
    
    //loop through to make buildings
    for (int i = 0; i < yMax; i += (width + 10)){
      thingy.setColor(Color.BLACK);
      thingy.drawRect(i, yMax-height, width, height);
      height = (int)(Math.random()*462+100); // randomize height for next building
    }
  }
  
//   public static void main(String[] args){ 
//     JFrame frame = new JFrame("Frame"); 
//     frame.add(new Randomize());
//     frame.setBackground(Color.WHITE);
//     frame.setSize(800, 750); 
//     frame.setVisible(true);
//   }
}