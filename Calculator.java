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

   public Calculator(){
      super("Physics Calculator");
      layout = new BorderLayout(); 
      setLayout(layout);
      
      menu = new MainMenu();
      
      lblInitialVel = new JLabel("Initial Vel.");
      lblInitialAng = new JLabel("Initial Ang.");
      lblInitialSteps = new JLabel("# of Steps.");
      
      tfInitialVel = new JTextField(7);
      tfInitialAng = new JTextField(7);
      tfInitialSteps = new JTextField(7);    
      
      taMotion = new JTextArea(25, 40);  
      taMotion.setEditable(false);
      
      btnCalculate = new JButton("Calculate");
      btnClear = new JButton("Clear");
      btnMainMenu = new JButton("Main Menu");
      
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
      
      
      JPanel southPanel = new JPanel();
         JPanel subSouth1 = new JPanel();
         subSouth1.setLayout(new BoxLayout(subSouth1, BoxLayout.X_AXIS));
         subSouth1.add(btnCalculate);
         subSouth1.add(btnClear); 
         subSouth1.add(btnMainMenu);
      southPanel.add(subSouth1);
      
      JPanel mainSouthPanel = new JPanel();
      mainSouthPanel.add(southPanel);
         
      //add(westPanel, BorderLayout.WEST);
      add(mainWestPanel, BorderLayout.WEST);
      add(mainEastPanel, BorderLayout.EAST);
      add(mainSouthPanel, BorderLayout.SOUTH);
         
      btnMainMenu.addActionListener(this);
      btnCalculate.addActionListener(this); 
      btnClear.addActionListener(this);
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
   }
   
   public static void main(String[] args){
      Calculator calculator  = new Calculator();
      calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      calculator.setSize(700, 550);
      calculator.setLocation(350, 0);
      calculator.setVisible(true);
   }
   

}