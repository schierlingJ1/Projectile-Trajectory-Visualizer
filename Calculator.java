import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Calculator extends JFrame implements ActionListener{
   private final LayoutManager layout;

   MainMenu menu;
   
   private JTextField tfInitialVel;
   private JTextField tfInitialAng;
   private JTextField tfInitialSteps;
   private JLabel lblInitialVel;
   private JLabel lblInitialAng;
   private JLabel lblInitialSteps;
   private JTextArea taMotion;
   private JButton btnCalculate;
   private JButton btnClear;
   private JButton btnMainMenu;
   private JButton btnExit;

   public Calculator(){
      super("Physics Calculator");
      layout = new BorderLayout(); 
      setLayout(layout);
      
      menu = new MainMenu();
      
      lblInitialVel = new JLabel("Initial Velocity:");
      lblInitialAng = new JLabel("Initial Angle:");
      lblInitialSteps = new JLabel("# of Steps:");
      
      tfInitialVel = new JTextField(7);
      tfInitialAng = new JTextField(7);
      tfInitialSteps = new JTextField(7);    
      
      taMotion = new JTextArea(25, 40);  
      taMotion.setEditable(false);
      
      btnCalculate = new JButton("Calculate");
      btnClear = new JButton("Clear");
      btnMainMenu = new JButton("Main Menu");
      btnExit = new JButton("Exit");
      
      //bunch of old layouts
      /*
      JPanel westPanel = new JPanel();
      //westPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
         JPanel subWest1 = new JPanel();
            subWest1.setLayout(new FlowLayout(FlowLayout.LEFT));
            subWest1.add(lblInitialVel);
            subWest1.add(tfInitialVel);
      westPanel.add(subWest1);
         JPanel subWest2 = new JPanel();
            subWest2.setLayout(new FlowLayout(FlowLayout.LEFT));
            subWest2.add(lblInitialAng);
            subWest2.add(tfInitialAng);
      westPanel.add(subWest2);
         JPanel subWest3 = new JPanel();
            subWest3.setLayout(new FlowLayout(FlowLayout.LEFT));
            subWest3.add(lblInitialSteps);
            subWest3.add(tfInitialSteps);
      westPanel.add(subWest3);
      
      JPanel mainWestPanel = new JPanel();
      mainWestPanel.add(westPanel);
      
      
      JPanel eastPanel = new JPanel();
         eastPanel.add(taMotion);
           
      JPanel mainEastPanel = new JPanel();
      mainEastPanel.add(eastPanel);
      */
      
      /*
      JPanel southPanel = new JPanel();
      southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
         JPanel subSouth1 = new JPanel();
         subSouth1.setLayout(new BoxLayout(subSouth1, BoxLayout.X_AXIS));
         subSouth1.add(lblInitialVel);
         subSouth1.add(tfInitialVel);
         
         JPanel subSouth2 = new JPanel();
         subSouth2.setLayout(new BoxLayout(subSouth2, BoxLayout.X_AXIS));
         subSouth1.add(lblInitialAng);
         subSouth1.add(tfInitialAng);
         
         JPanel subSouth3 = new JPanel();
         subSouth3.setLayout(new BoxLayout(subSouth3, BoxLayout.X_AXIS));
         subSouth3.add(lblInitialSteps);
         subSouth3.add(tfInitialSteps);
         
         
         JPanel subSouth4 = new JPanel();
         subSouth4.setLayout(new BoxLayout(subSouth4, BoxLayout.X_AXIS));
         subSouth4.add(btnCalculate);
         subSouth4.add(btnClear); 
         subSouth4.add(btnMainMenu);
         
      southPanel.add(subSouth1);
      southPanel.add(subSouth2);
      southPanel.add(subSouth3);
      southPanel.add(subSouth4);
      */
      
      JPanel eastPanel = new JPanel();
      eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
         JPanel subEast1 = new JPanel();
         subEast1.setLayout(new BoxLayout(subEast1, BoxLayout.Y_AXIS));
         subEast1.add(lblInitialVel);
         subEast1.add(tfInitialVel);
         
         JPanel subEast2 = new JPanel();
         subEast2.setLayout(new BoxLayout(subEast2, BoxLayout.Y_AXIS));
         subEast2.add(lblInitialAng);
         subEast2.add(tfInitialAng);
         
         JPanel subEast3 = new JPanel();
         subEast3.setLayout(new BoxLayout(subEast3, BoxLayout.Y_AXIS));
         subEast3.add(lblInitialSteps);
         subEast3.add(tfInitialSteps);
         
         
         JPanel subEast4 = new JPanel();
         subEast4.setLayout(new BoxLayout(subEast4, BoxLayout.Y_AXIS));
         subEast4.add(btnCalculate);
         subEast4.add(btnClear); 
         subEast4.add(btnMainMenu);
         subEast4.add(btnExit);
         
      eastPanel.add(subEast1);
      eastPanel.add(subEast2);
      eastPanel.add(subEast3);
      eastPanel.add(subEast4);
      //old layout
      /*
      JPanel mainSouthPanel = new JPanel();
      mainSouthPanel.add(southPanel);
      */
      JPanel mainEastPanel = new JPanel();
      mainEastPanel.add(eastPanel);
      //old layout
      /*   
      add(westPanel, BorderLayout.WEST);
      add(mainWestPanel, BorderLayout.WEST);
      add(mainEastPanel, BorderLayout.EAST);
      */
      add(taMotion);
      add(mainEastPanel, BorderLayout.EAST);
      //old layout
      /*
      add(mainSouthPanel, BorderLayout.EAST);
      add(southPanel, BorderLayout.SOUTH);
      */   
      btnMainMenu.addActionListener(this);
      btnCalculate.addActionListener(this); 
      btnClear.addActionListener(this);
      btnExit.addActionListener(this);
   }
   
    public void actionPerformed(ActionEvent event){
      //event.getActionCommand() returns the lable on the button
      //event.getSource() returns the button object
      //See sample code below
      //JOptionPane.showMessageDialog(this,"You just pressed the " + event.getActionCommand(),"Demo",JOptionPane.INFORMATION_MESSAGE);
      if(event.getSource() == btnMainMenu){
         MainMenu menu2 = new MainMenu();
            menu2.setSize(500, 400);
            menu2.setLocation(450, 100);
            menu2.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            menu2.setResizable(false);
            menu2.setVisible(true);
            Calculator.this.dispose();      
      }
      else if(event.getSource() == btnCalculate){
      
      }
      else if(event.getSource() == btnClear){
            tfInitialVel.setText("");
            tfInitialAng.setText("");
            tfInitialSteps.setText("");
            taMotion.setText("");
      }  
      else if(event.getSource() == btnExit){
         System.exit(0);
      }  
   }
   
   public static void main(String[] args){
      Calculator calculator  = new Calculator();
      calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //old sizing
      /*
      calculator.setSize(700, 550);
      calculator.setLocation(350, 0);
      */
      calculator.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      calculator.setUndecorated(true);
      calculator.setVisible(true);
   }
   

}