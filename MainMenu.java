import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainMenu extends JFrame implements ActionListener{
   private final LayoutManager layout;

   private JTextArea ta0;
   private JLabel lbl0;
   private JButton btnCalculator;
   private JButton btnGame;
   private JButton btnHighscores;


   public MainMenu(){
      super("Main Menu");
      layout = new BorderLayout();
      setLayout(layout);

      lbl0 = new JLabel("Physics Trajectory Visualizer");
      lbl0.setFont(new Font("", Font.PLAIN, 30));

      btnCalculator = new JButton("Calculator");
      btnCalculator.setFocusPainted(false);
      btnGame = new JButton("Game");
      btnGame.setFocusPainted(false);
      btnHighscores = new JButton("Highscores");
      btnHighscores.setFocusPainted(false);
      //old layout
      /*
      JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
      */

      JPanel centerPanel = new JPanel();
      //old layout: centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
      centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            JPanel subCenter1 = new JPanel();
            //old layout
            /*
            subCenter1.setLayout(new BoxLayout(subCenter1, BoxLayout.Y_AXIS));
               subCenter1.setPreferredSize(new Dimension(100, 50));
               subCenter1.add(ta0);
               */
               subCenter1.add(lbl0);
            JPanel subCenter2 = new JPanel();
            //old layout: subCenter2.setLayout(new BoxLayout(subCenter2, BoxLayout.X_AXIS));
               subCenter2.add(btnCalculator);
               subCenter2.add(btnGame);
               //old layout: subCenter2.add(btn2);
            JPanel subCenter3 = new JPanel();
               subCenter3.add(btnHighscores);

      centerPanel.add(subCenter1);
      centerPanel.add(subCenter2);
      centerPanel.add(subCenter3);
      //old layout
      /*
      mainPanel.add(centerPanel);

      add(centerPanel, BorderLayout.CENTER);
      add(mainPanel, BorderLayout.CENTER);
      */
      add(centerPanel);
      btnCalculator.addActionListener(this);
      btnGame.addActionListener(this);
      btnHighscores.addActionListener(this);
   }

   public void actionPerformed(ActionEvent event){
      //event.getActionCommand() returns the lable on the button
      //event.getSource() returns the button object
      //See sample code below
      //JOptionPane.showMessageDialog(this,"You just pressed the " + event.getActionCommand(),"Demo",JOptionPane.INFORMATION_MESSAGE);

      if(event.getSource() == btnCalculator){
         Calculator calculator = new Calculator();
         //old sizing
         /*
         calculator.setSize(700,550);
         calculator.setLocation(350,0);
         */
         calculator.setExtendedState(JFrame.MAXIMIZED_BOTH);
         calculator.setUndecorated(true);
         calculator.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
         calculator.setResizable(false);
         calculator.setVisible(true);
         MainMenu.this.dispose();
      }
      else if(event.getSource() == btnGame){
         Game game = new Game();
         //old sizing
         /*
         game.setSize(700,550);
         game.setLocation(350,0);
         */
         game.setExtendedState(JFrame.MAXIMIZED_BOTH);
         game.setUndecorated(true);
         game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
         game.setResizable(false);
         game.setVisible(true);
         MainMenu.this.dispose();
      }
   }

   public static void main(String[] args){
      MainMenu mainMenu  = new MainMenu();
      mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainMenu.setSize(500, 400);
      mainMenu.setLocation(450, 100);
      mainMenu.setVisible(true);

   }


}
