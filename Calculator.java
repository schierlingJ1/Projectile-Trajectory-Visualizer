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
   private String maxHeight;
   private String maxRange;
   private String maxTime;
   private JTextArea taInfo;
   private JButton btnCalculate;
   private JButton btnClear;
   private JButton btnTable;
   private JButton btnMainMenu;
   private JButton btnExit;


   private JPanel graphPanel;

   private JSplitPane splitPane;
   private Visualizer visualizer;

   public Calculator(){
      super("Physics Calculator");
      layout = new BorderLayout();
      setLayout(layout);

      menu = new MainMenu();
      showTable = false;

      lblInitialVel = new JLabel("Initial Velocity:");
      lblInitialAng = new JLabel("Initial Angle:");
      lblInitialSteps = new JLabel("# of Steps:");
      lblHeight = new JLabel("Initial Height:");

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
      //add button to show/hide table
      btnTable = new JButton("Show Table");
      btnTable.setFocusPainted(false);

      btnMainMenu = new JButton("Main Menu");
      btnMainMenu.setFocusPainted(false);
      btnExit = new JButton("Exit");
      btnExit.setFocusPainted(false);

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
         subEast4.add(lblHeight);
         subEast4.add(tfHeight);

         JPanel subEast5 = new JPanel();
         subEast5.setLayout(new BoxLayout(subEast5, BoxLayout.Y_AXIS));
         subEast5.add(btnCalculate);
         subEast5.add(btnClear);
         subEast5.add(btnTable);
         subEast5.add(btnMainMenu);
         subEast5.add(btnExit);


         JPanel subEast6 = new JPanel();
         //subEast5.setLayout(new BoxLayout(subEast5, BoxLayout.Y_AXIS));
         subEast6.add(taInfo);

      eastPanel.add(subEast1);
      eastPanel.add(subEast2);
      eastPanel.add(subEast3);
      eastPanel.add(subEast4);
      eastPanel.add(subEast5);
      eastPanel.add(subEast6);
      //eastPanel.add(taInfo);

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
      //add(taMotion);
      add(mainEastPanel, BorderLayout.WEST);

      //add(taMotion);
      //old layout
      /*
      add(mainSouthPanel, BorderLayout.EAST);
      add(southPanel, BorderLayout.SOUTH);
      */

      graphPanel = new JPanel();
      graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
      //graphPanel.add(splitPane);

      visualizer = new Visualizer(graphPanel, tfInitialVel, tfInitialAng, tfHeight, tfInitialSteps);
      //add(visualizer.getRightPanel());
      //add(visualizer.getLeftPanel());
      //graphPanel.add(visualizer.getLeftPanel());
      //graphPanel.add(visualizer.getRightPanel());
      graphPanel.add(visualizer.getPane());
      //graphPanel.setVisible(false);
      add(graphPanel);

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
         //visualizer.start();
         //graphPanel.add(visualizer.getPane());
         //add(visualizer.getRightPanel());
         //add(visualizer.getLeftPanel());

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
            graphPanel.add(visualizer.getPane());
            //graphPanel.setVisible(false);
            add(graphPanel);
            validate();
         }
         else if(angle < 0 || angle > 90){
            taInfo.setText("Angle must be between 0-90 degrees");
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
            graphPanel.add(visualizer.getPane());
            //graphPanel.setVisible(false);
            add(graphPanel);
            validate();

            maxHeight = String.format("%.2f", visualizer.getMaxHeight());
            taInfo.setText("Max Height:\n");
            taInfo.append(maxHeight + "m\n\n");

            maxRange = Double.toString(visualizer.getMaxRange());
            maxRange = String.format("%.2f", visualizer.getMaxRange());
            taInfo.append("Max Range:\n");
            taInfo.append(maxRange + "m\n\n");

            maxTime = Double.toString(visualizer.getTotalTime());
            maxTime = String.format("%.2f", visualizer.getTotalTime());
            taInfo.append("Total Time:\n");
            taInfo.append(maxTime + "s\n\n");
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
      else if(event.getSource() == btnTable) {
        int w = visualizer.getWidth();
        System.out.println(w);
        //show table
        if (showTable == false) {
            visualizer.getPane().setDividerLocation((int)Math.round(w*2/3));
            visualizer.getPane().setDividerSize(10);
            btnTable.setText("Hide Table");
            showTable = true;
        //hide table
        } else {
            visualizer.getPane().setDividerLocation(w);
            visualizer.getPane().setDividerSize(1);
            btnTable.setText("Show Table");
            showTable = false;
        }
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
