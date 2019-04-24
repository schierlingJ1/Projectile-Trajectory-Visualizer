import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Calculator extends JFrame implements ActionListener{
   private final LayoutManager layout;
   
   private boolean showTable;

   MainMenu menu;
   
   private JTextField tfInitialVel;
   private JTextField tfInitialAng;
   private JTextField tfInitialSteps;
   private JTextField tfHeight;
   private JLabel lblInitialVel;
   private JLabel lblInitialAng;
   private JLabel lblInitialSteps;
   private JLabel lblHeight;
   private JLabel lblSummary;
   private String maxHeight;
   private String maxRange;
   private String maxTime;
   private JTextArea taInfo;
   private JButton btnCalculate;
   private JButton btnClear;
   private JButton btnMainMenu;
   private JButton btnExit;
   private JButton btnTable;
  
   
   private JPanel graphPanel;
   
   private JSplitPane splitPane;
   private Visualizer visualizer;

   public Calculator(){
      super("Physics Calculator");
      layout = new BorderLayout(); 
      setLayout(layout);
      
      menu = new MainMenu();
      
      lblInitialVel = new JLabel("Initial Velocity:");
      lblInitialAng = new JLabel("Initial Angle:");
      lblInitialSteps = new JLabel("# of Steps:");
      lblHeight = new JLabel("Initial Height:");
      lblSummary = new JLabel("Summary:");
      
      tfInitialVel = new JTextField("0",7);
      tfInitialAng = new JTextField("0",7);
      tfInitialSteps = new JTextField("100",7);    
      tfHeight = new JTextField("0",7);
      
      taInfo = new JTextArea(10, 15);  
      taInfo.setEditable(false);
      
      btnCalculate = new JButton("Calculate");
      btnCalculate.setFocusPainted(false);
      btnClear = new JButton("Clear");
      btnClear.setFocusPainted(false);
      btnMainMenu = new JButton("Main Menu");
      btnMainMenu.setFocusPainted(false);
      btnExit = new JButton("Exit");
      btnExit.setFocusPainted(false);
      btnTable = new JButton("Show Table");
      btnTable.setFocusPainted(false);
      
      showTable = false;
      
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
         subEast4.add(lblHeight);
         subEast4.add(tfHeight);
         
         JPanel subEast5 = new JPanel();
         subEast5.add(btnCalculate);
         
         JPanel subEast6 = new JPanel();
         subEast6.add(btnClear);
         
         JPanel subEast7 = new JPanel();
         subEast7.add(btnTable);
         
         JPanel subEast8 = new JPanel();
         subEast8.add(btnMainMenu);
         
         JPanel subEast9 = new JPanel();
         subEast9.add(btnExit);
         
         JPanel subEast10 = new JPanel();
         subEast10.add(lblSummary);
         
         JPanel subEast11 = new JPanel();
         subEast11.add(taInfo);
         
      eastPanel.add(subEast1);
      eastPanel.add(subEast2);
      eastPanel.add(subEast3);
      eastPanel.add(subEast4);
      eastPanel.add(subEast5);
      eastPanel.add(subEast6);
      eastPanel.add(subEast7);
      eastPanel.add(subEast7);
      eastPanel.add(subEast8);
      eastPanel.add(subEast9);
      eastPanel.add(subEast10);
      eastPanel.add(subEast11);
      
      JPanel mainEastPanel = new JPanel();
      mainEastPanel.add(eastPanel);
      
      add(mainEastPanel, BorderLayout.WEST);  
      
      graphPanel = new JPanel();
      graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
      
      visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfHeight, tfInitialSteps); 
 
      splitPane = visualizer.getPane();
      graphPanel.add(splitPane);
      
      add(graphPanel);  
      
      btnMainMenu.addActionListener(this);
      btnCalculate.addActionListener(this); 
      btnClear.addActionListener(this);
      btnExit.addActionListener(this);
      btnTable.addActionListener(this);
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
         double velocity = Double.parseDouble(tfInitialVel.getText());
         double angle = Double.parseDouble(tfInitialAng.getText());
         int steps = Integer.parseInt(tfInitialSteps.getText());
         double height = Double.parseDouble(tfHeight.getText());
         
         
         if(velocity < 0){
            taInfo.setText("Velocity must be positive\n");
            Font font = new Font("", Font.BOLD, 12);
            taInfo.setFont(font);
            taInfo.setForeground(Color.RED);
            
            tfInitialVel.setText("0");
            tfInitialAng.setText("0");
            tfInitialSteps.setText("0");
            tfHeight.setText("0");
            
            remove(graphPanel);
            graphPanel = new JPanel();
            graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
            graphPanel.setVisible(true);
            visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfHeight, tfInitialSteps);
            splitPane = visualizer.getPane();
            //graphPanel.add(visualizer.getPane());
            graphPanel.add(splitPane);
            //graphPanel.setVisible(false);
            add(graphPanel);
            validate();       
         }
         else if(angle < 0 || angle > 90){
            taInfo.setText("Angle must be between 0-90");
            Font font = new Font("", Font.BOLD, 12);
            taInfo.setFont(font);
            taInfo.setForeground(Color.RED);
            
            tfInitialVel.setText("0");
            tfInitialAng.setText("0");
            tfInitialSteps.setText("0");
            tfHeight.setText("0");
            
            remove(graphPanel);
            graphPanel = new JPanel();
            graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
            graphPanel.setVisible(true);
            visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfHeight, tfInitialSteps);
            graphPanel.add(visualizer.getPane());
            //graphPanel.setVisible(false);
            add(graphPanel);
            validate(); 
         }
         else if(steps < 10){
            taInfo.setText("Steps must be greater than 10");
            Font font = new Font("", Font.BOLD, 12);
            taInfo.setFont(font);
            taInfo.setForeground(Color.RED);
            
            tfInitialVel.setText("0");
            tfInitialAng.setText("0");
            tfInitialSteps.setText("0");
            tfHeight.setText("0");
            
            remove(graphPanel);
            graphPanel = new JPanel();
            graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
            graphPanel.setVisible(true);
            visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfHeight, tfInitialSteps);
            graphPanel.add(visualizer.getPane());
            //graphPanel.setVisible(false);
            add(graphPanel);
            validate(); 
         }
         else if(height < 0){
            taInfo.setText("Height must be positive");
            Font font = new Font("", Font.BOLD, 12);
            taInfo.setFont(font);
            taInfo.setForeground(Color.RED);
            
            tfInitialVel.setText("0");
            tfInitialAng.setText("0");
            tfInitialSteps.setText("0");
            tfHeight.setText("0");
            
            remove(graphPanel);
            graphPanel = new JPanel();
            graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
            graphPanel.setVisible(true);
            visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfHeight, tfInitialSteps);
            graphPanel.add(visualizer.getPane());
            //graphPanel.setVisible(false);
            add(graphPanel);
            validate(); 
         }
         else{
            Font font =  new Font("", Font.PLAIN, 12);
            taInfo.setFont(font);
            taInfo.setForeground(Color.BLACK);
            
            remove(graphPanel);
            graphPanel = new JPanel();
            graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
            graphPanel.setVisible(true);
            visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfHeight, tfInitialSteps);
            splitPane = visualizer.getPane();
            graphPanel.add(splitPane);
            //graphPanel.setVisible(false);
            add(graphPanel);
            validate();
        
            maxHeight = String.format("%.2f", visualizer.getMaxHeight()); 
            taInfo.setText("Max Height:\n");
            taInfo.append(maxHeight + " (m)\n\n");
            
            maxRange = Double.toString(visualizer.getMaxRange());
            maxRange = String.format("%.2f", visualizer.getMaxRange());
            taInfo.append("Max Range:\n");
            taInfo.append(maxRange + " (m)\n\n");
   
            maxTime = Double.toString(visualizer.getTotalTime());
            maxTime = String.format("%.2f", visualizer.getTotalTime());
            taInfo.append("Max Time:\n");
            taInfo.append(maxTime + " (s)\n\n");
          }
            
      }
      else if(event.getSource() == btnClear){
            tfInitialVel.setText("0");
            tfInitialAng.setText("0");
            tfInitialSteps.setText("0");
            tfHeight.setText("0");
            taInfo.setText("");
            
            remove(graphPanel);
            graphPanel = new JPanel();
            graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
            graphPanel.setVisible(true);
            visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfHeight, tfInitialSteps);
            graphPanel.add(visualizer.getPane());
            //graphPanel.setVisible(false);
            add(graphPanel);
            validate();
      }  
      else if(event.getSource() == btnExit){
         System.exit(0);
      }  
      else if(event.getSource() == btnTable){
         int w = visualizer.getWidth();
         //show table
         if (showTable == false) {
            splitPane.setDividerLocation((int)Math.round(w*2/3));
            splitPane.setDividerSize(10);
            btnTable.setText("Hide Table");
            showTable = true;
         //hide table
         } else {
            splitPane.setDividerLocation(w);
            splitPane.setDividerSize(1);
            btnTable.setText("Show Table");
            showTable = false;
         }
      }
   }
   
   public static void main(String[] args){
      Calculator calculator  = new Calculator();
      calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      calculator.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      calculator.setUndecorated(true);
      calculator.setVisible(true);
   }
   

}