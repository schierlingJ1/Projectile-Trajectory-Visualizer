import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Game extends JFrame{
   private final LayoutManager layout;

   MainMenu menu;
   
   private JTextField tfInitialVel;
   private JTextField tfInitialAng;
   private JTextField tfInitialSteps;
   private JTextField txtFMajor;
   private JTextField txtFGPA;
   private JTextField txtFSID;
   private JLabel lblInitialVel;
   private JLabel lblInitialAng;
   private JLabel lblInitialSteps;
   private JLabel lblMajor;
   private JLabel lblGPA;
   private JLabel lblSID;
   private JLabel lblNumOfRecords;
   private JTextArea taMotion;
   private JButton btnCalculate;
   private JButton btnClear;
   private JButton btnMainMenu;
   
   private JFrame newFrame;

   public Game(){
      super("Physics Game");
      layout = new BorderLayout(); 
      setLayout(layout);
      
      newFrame = new JFrame();
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
         
      btnClear.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent event){
            tfInitialVel.setText("");
            tfInitialAng.setText("");
            tfInitialSteps.setText("");
            taMotion.setText("");
         }
      });   
         
      btnMainMenu.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent event){
            /*
            Menu menu2 = new Menu(); 
            menu2.setSize(250,300);
            menu2.setLocation(100,15);
            menu2.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
            menu2.setResizable(false);
            menu2.setVisible(true);
            */
            MainMenu menu2 = new MainMenu();
            menu2.setSize(500, 400);
            menu2.setLocation(350, 0);
            menu2.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            menu2.setResizable(false);
            menu2.setVisible(true);
            Game.this.dispose();
         }
      });   
   }
   
   
   public void actionPerformed(ActionEvent event){
      //event.getActionCommand() returns the lable on the button
      //event.getSource() returns the button object
      //See sample code below
      //JOptionPane.showMessageDialog(this,"You just pressed the " + event.getActionCommand(),"Demo",JOptionPane.INFORMATION_MESSAGE);
      if(event.getSource() == btnClear){
         /*
         Menu menu2 = new Menu(); 
         menu2.setSize(700,700);
         menu2.setLocation(100,15);
         menu2.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
         menu2.setResizable(false);
         menu2.setVisible(true);
         */
         JFrame newFrame = new JFrame();
         newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         newFrame.setSize(600, 500);
         newFrame.setVisible(true);       
      }
       
   }
   
   public static void main(String[] args){
      Game game  = new Game();
      game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      game.setSize(700, 550);
      game.setLocation(350, 0);
      game.setVisible(true);
   }
   

}