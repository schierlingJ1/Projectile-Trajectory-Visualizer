import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Game extends JFrame implements ActionListener{
   private final LayoutManager layout;

   MainMenu menu;
   
   private JTextField tfInitialVel;
   private JTextField tfInitialAng;
   private JTextField tfInitialSteps;
   private JTextField tfInitialHeight;
   private JLabel lblInitialVel;
   private JLabel lblInitialAng;
   private JLabel lblInitialSteps;
   private JLabel lblInitialHeight;
   private JTextArea taMotion;
   private JButton btnLaunch;
   private JButton btnClear;
   private JButton btnMainMenu;
   private JButton btnExit;
   
   private JPanel graphPanel;
   
   private JSplitPane splitPane;
   private Visualizer visualizer;
   
   private Thread thread;

   public Game(){
      super("Physics Game");
      layout = new BorderLayout(); 
      setLayout(layout);
      
      menu = new MainMenu();
      
      lblInitialVel = new JLabel("Initial Vel.");
      lblInitialAng = new JLabel("Initial Ang.");
      lblInitialSteps = new JLabel("# of Steps.");
      lblInitialHeight = new JLabel("Initial Height:");
      
      tfInitialVel = new JTextField("100",7);
      tfInitialAng = new JTextField("45",7);
      tfInitialSteps = new JTextField("100",7);    
      tfInitialHeight = new JTextField("0",7);
      
      taMotion = new JTextArea(25, 40);  
      taMotion.setEditable(false);
      
      btnLaunch = new JButton("Launch");
      btnClear = new JButton("Clear");
      btnMainMenu = new JButton("Main Menu");
      btnExit = new JButton("Exit");
      
      //old layout
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
      
      
      JPanel southPanel = new JPanel();
         JPanel subSouth1 = new JPanel();
         subSouth1.setLayout(new BoxLayout(subSouth1, BoxLayout.X_AXIS));
         subSouth1.add(btnCalculate);
         subSouth1.add(btnClear); 
         subSouth1.add(btnMainMenu);
         subSouth1.add(btnExit);
      southPanel.add(subSouth1);
      
      JPanel mainSouthPanel = new JPanel();
      mainSouthPanel.add(southPanel);
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
         subEast4.add(lblInitialHeight);
         subEast4.add(tfInitialHeight);
         
         JPanel subEast5 = new JPanel();
         subEast5.setLayout(new BoxLayout(subEast5, BoxLayout.Y_AXIS));
         subEast5.add(btnLaunch);
         subEast5.add(btnClear); 
         subEast5.add(btnMainMenu);
         subEast5.add(btnExit);
         
      eastPanel.add(subEast1);
      eastPanel.add(subEast2);
      eastPanel.add(subEast3);
      eastPanel.add(subEast4);
      eastPanel.add(subEast5);
      
      //old layout
      /* 
      add(westPanel, BorderLayout.WEST);
      add(mainWestPanel, BorderLayout.WEST);
      add(mainEastPanel, BorderLayout.EAST);
      add(mainSouthPanel, BorderLayout.SOUTH);
      */
      
      JPanel mainEastPanel = new JPanel();
      mainEastPanel.add(eastPanel);
      
         
      //add(taMotion);
      add(mainEastPanel, BorderLayout.WEST);
      //add(taMotion);
      //add(graphPanel);
      
      graphPanel = new JPanel();
      graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
      //graphPanel.add(splitPane);
      
      visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfInitialHeight, tfInitialSteps); 
      //add(visualizer.getRightPanel());
      //add(visualizer.getLeftPanel());   
      //graphPanel.add(visualizer.getLeftPanel());
      //graphPanel.add(visualizer.getRightPanel());
      graphPanel.add(visualizer.getPane());
      graphPanel.setVisible(false);
      add(graphPanel);
      
      btnMainMenu.addActionListener(this);
      btnLaunch.addActionListener(this); 
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
         Game.this.dispose();      
      }
      else if(event.getSource() == btnLaunch){
         //visualizer.start();
         //graphPanel.add(visualizer.getPane());
         //add(visualizer.getRightPanel());
         //add(visualizer.getLeftPanel());
         remove(graphPanel);
         graphPanel = new JPanel();
         graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
         graphPanel.setVisible(true);
         visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfInitialHeight, tfInitialSteps);
         graphPanel.add(visualizer.getPane());
         //graphPanel.setVisible(false);
         add(graphPanel);
         validate();
         //add(visualizer.getRightPanel());
         //add(visualizer.getLeftPanel());
      }
      else if(event.getSource() == btnClear){
         tfInitialVel.setText("");
         tfInitialAng.setText("");
         tfInitialSteps.setText("");
         tfInitialHeight.setText("");
         taMotion.setText("");
      }
      else if(event.getSource() == btnExit){
         System.exit(0);
      }
       
   }
   
   public static void main(String[] args){
      Game game  = new Game();
      game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //old sizing
      /*
      game.setSize(700, 550);
      game.setLocation(350, 0);
      */
      game.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      game.setUndecorated(true);
      game.setVisible(true);
   }
   

}