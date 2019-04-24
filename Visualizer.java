import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Visualizer extends JFrame {

    private JSplitPane canvas;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private ProjectileMotion projectile;

    private static JTextField tfVelocity;
    private static JTextField tfAngle;
    private static JTextField tfHeight;
    private static JTextField tfSteps;

    private double maxHeight;
    private double maxRange;
    private double totalTime;
    private double target;

    private static JPanel mainPanel;

   /*
    public static void main(String args[]) {
        new Visualizer();
    }
   */

    public JSplitPane getPane(){
      return canvas;
    }

    public double getMaxHeight(){
      maxHeight = this.projectile.maxHeight();
      return maxHeight;
    }

    public double getMaxRange(){
      maxRange = this.projectile.maxRange();
      return maxRange;
    }

    public double getTotalTime(){
      totalTime = this.projectile.totalTime();
      return totalTime;
    }
    
    public double getTarget() {
      return target;
    }

    public Visualizer() {

        // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height; // * {1/4, 1/2, 2/3} to scale window to fit % of screen
        int width = screenSize.width;

        this.canvas = new JSplitPane();
        this.leftPanel = new JPanel();
        this.rightPanel = new JPanel();
        this.projectile = new ProjectileMotion();

        setRightPanel();
        setLeftPanel(width, height);
        setCanvas(width, height);
    }


    public Visualizer(JPanel mainPanel, JTextField tfVelocity, JTextField tfAngle, JTextField tfHeight, JTextField tfSteps) {
        this.tfVelocity = tfVelocity;
        this.tfAngle = tfAngle;
        this.tfHeight = tfHeight;
        this.tfSteps = tfSteps;
        this.mainPanel = mainPanel;

        // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height; // * {1/4, 1/2, 2/3} to scale window to fit % of screen
        int width = screenSize.width;

        this.canvas = new JSplitPane();
        this.leftPanel = new JPanel();
        this.rightPanel = new JPanel();
        //this.projectile = new ProjectileMotion();
        this.projectile = new ProjectileMotion(Double.parseDouble(tfVelocity.getText()),
        Double.parseDouble(tfAngle.getText()), Double.parseDouble(tfHeight.getText()),
        Integer.parseInt(tfSteps.getText()));

        setRightPanel();
        setLeftPanel(width, height);
        setCanvas(width, height);
    }
    
    public Visualizer(JPanel mainPanel, JTextField tfVelocity, JTextField tfAngle, JTextField tfHeight, JTextField tfSteps, ArrayList<Integer> buildings, String difficulty) {
        this.tfVelocity = tfVelocity;
        this.tfAngle = tfAngle;
        this.tfHeight = tfHeight;
        this.tfSteps = tfSteps;
        this.mainPanel = mainPanel;

        // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height; // * {1/4, 1/2, 2/3} to scale window to fit % of screen
        int width = screenSize.width;

        this.canvas = new JSplitPane();
        this.leftPanel = new JPanel();
        this.rightPanel = new JPanel();
        //this.projectile = new ProjectileMotion();
        this.projectile = new ProjectileMotion(Double.parseDouble(tfVelocity.getText()),
        Double.parseDouble(tfAngle.getText()), Double.parseDouble(tfHeight.getText()),
        Integer.parseInt(tfSteps.getText()));


        setRightPanel();
        setLeftPanel(width, height, buildings);
        setCanvas(width, height);
    }

    public void setCanvas(int w, int h) {
        //set window width/height
        setSize(new Dimension(w, h));
        //add canvas to the window
        getContentPane().add(this.canvas);  //GridLayout will full the splitPane to the whole window
        //don't allow resizing the divider to eliminate multi-scroll bars on table panel
        this.canvas.setEnabled(false);
        this.canvas.setOrientation(JSplitPane.HORIZONTAL_SPLIT);       //split the window horizontally
        this.canvas.setDividerSize(1);      //make divier invivisible initially
        this.canvas.setDividerLocation(w);
        this.canvas.setLeftComponent(leftPanel);
        this.canvas.setRightComponent(rightPanel);

        //setVisible(true);
        //maximize window
        //setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        //mainPanel.add(canvas);

    }

    public void setLeftPanel(int w, int h) {
        Visualizer.DrawArc grid = new DrawArc(w, h);
        // Visualizer.DrawBuildings buildings = new DrawBuildings();
        this.leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // BoxLayout.Y_AXIS will arrange the content vertically
        this.leftPanel.add(grid);
        // this.leftPanel.add(buildings);

        //add button to show/hide table
        JButton b = new JButton("Show Table");
        //disable 'focus' bordering around text
        b.setFocusPainted(false);
        //add the listener
        b.addActionListener(new ActionListener() {
            boolean showTable = false;
            public void actionPerformed(ActionEvent e) {
                //show table
                if (showTable == false) {
                    Visualizer.this.canvas.setDividerLocation((int)Math.round(w*3/4));
                    Visualizer.this.canvas.setDividerSize(10);
                    b.setText("Hide Table");
                    showTable = true;
                //hide table
                } else {
                    Visualizer.this.canvas.setDividerLocation(w);
                    Visualizer.this.canvas.setDividerSize(1);
                    b.setText("Show Table");
                    showTable = false;
               }
            }
        });
		//this.leftPanel.add(b);
        this.leftPanel.setVisible(true);
    }
    
    public void setLeftPanel(int w, int h, ArrayList<Integer> buildings) {
        Visualizer.DrawGame grid = new DrawGame(w, h, buildings);
        this.target = grid.getTarget();
        this.leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // BoxLayout.Y_AXIS will arrange the content vertically
        this.leftPanel.add(grid);

        this.leftPanel.setVisible(true);
    }
    
    public void setRightPanel() {
        Visualizer.DrawTable table = new DrawTable();
        this.rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        this.rightPanel.add(table);
        this.rightPanel.setVisible(true);
    }

    //new arc definiton
    private class DrawArc extends JComponent {

        int width;
        int height;

        public DrawArc(int width, int height) {
            this.width = width;
            this.height = height;
        }

        //Graphics is the base class that allows us to draw on our components
        //paint() is abstract so we define it here
        public void paint(Graphics g) {
            //extend Graphics to draw 2D shapes
            Graphics2D arc = (Graphics2D)g;
            //cleans up edges of whatever is drawn on screen
            arc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            markers(g); //markers first so axis overlaps them in black
            grid(g);
//             target(g);
//             buildings(g);
            parabola(g);

            //automated interpolation
            //scale/zoom grid
        }

        //draw grid/axis
        public void grid(Graphics g) {
            //draw white background for grid
            g.setColor(Color.white);
            g.fillRect(60, 0, this.width, this.height-200);

            //modified online code---------------------------------
            //http://ibcomp.fis.edu/Java/parabola.html
            g.setColor(Color.gray);
            //draw horizontal lines
            for (int x = 0; x <= this.height-199; x = x + 20) {
                g.drawLine(60,x,this.width,x);
            }
            //draw vertical lines
            for (int y = 60; y <= this.width; y = y + 20) {
                g.drawLine(y,0,y,this.height-199);
            }

            g.setColor(Color.black);
            //x axis
            g.drawLine(20, this.height-200, this.width, this.height-200);
            g.drawLine(20, this.height-199, this.width, this.height-199);
            //y axis
            g.drawLine(60,0,60,this.height-160);
            g.drawLine(61,0,61,this.height-160);
            //----------------------------------------
        }

        //draw blank grid without graph
        public void blank(Graphics g) {
            //draw white background for grid
            g.setColor(Color.white);
            g.fillRect(60, 0, this.width, this.height-200);

            g.setColor(Color.black);
            //x axis
            g.drawLine(20, this.height-200, this.width, this.height-200);
            g.drawLine(20, this.height-199, this.width, this.height-199);
            //y axis
            g.drawLine(60,0,60,this.height-160);
            g.drawLine(61,0,61,this.height-160);
            //----------------------------------------
        }

        //draw dotted parabola trajectory
        public void parabola(Graphics g) {
            // 0,0 coordiante for graph
            //g.fillOval(56, this.height-200, 10, 10);

            //uget information from outer class with Outer.this.field
            List<double[]> table = Visualizer.this.projectile.table();
            double maxY = Visualizer.this.projectile.maxHeight();

            //print dots
            g.setColor(Color.blue);
            for (int i = 0; i < table.size(); i++) {
                //show start/end as green
                if (i == 0 || i == table.size()-1) {
                    g.setColor(Color.green);
                //show normal as blue
                } else {
                    g.setColor(Color.blue);
                }

                double[] moment = table.get(i);
                int xp = (int)Math.round(moment[1]) + 60;
                /*
                //scale grid to arc
                //(maxHeight - this y val) * window height / maxHeight
                int yp = (int)Math.round((maxY - moment[2]) * (this.height-200) / maxY);
                */
                //scale arc to grid
                int yp = ((int)Math.round(maxY - moment[2])) + ((this.height-200) - (int)Math.round(Visualizer.this.projectile.maxHeight()));
                if (yp == (int)Math.round(Visualizer.this.projectile.maxHeight())) {
                    g.setColor(Color.yellow);
                    g.fillOval(xp-2, yp-2, 10, 10);
                }

                //show out of bounds as black
                if (xp > this.width || yp > this.height-200) {
                    g.setColor(Color.black);
                    g.fillOval(xp-2, yp-2, 10, 10);
                    break;
                } else {
                    //g.drawString(""+x, xp, yp); //print values next to dot
                    g.fillOval(xp-2, yp-2, 5, 5);
                }
            }
        }

        //create tick marks along to axis
        public void markers(Graphics g) {
            g.setColor(Color.red);
            //x-axis tick marks
            int yout = this.height-200;
            for (int x = 80, scale = 20; x <= this.width; x += 40, scale += 40) {
                //gray-out axis
                if (scale > (int)Math.round(Visualizer.this.projectile.maxRange())) {
                    g.setColor(Color.gray);
                }
                g.drawLine(x-1, yout, x, yout+20);
                g.drawLine(x, yout, x, yout+20);
                g.drawLine(x+1, yout, x, yout+20);

                //align in center of number
                if (scale >= 1000) {
                    g.drawString(""+scale, x-15, yout+35);
                } else if (scale >= 100) {
                    g.drawString(""+scale, x-10, yout+35);
                } else if (scale >= 10) {
                    g.drawString(""+scale, x-5, yout+35);
                } else {
                    g.drawString(""+scale, x, yout+35);
                }
            }

            //reset color
            g.setColor(Color.red);

            //y-axis tick marks
            for (int y = this.height-220, scale = 20; y > 0; y -= 40, scale += 40) {
                //gray-out axis
                if (y < ((this.height-200) - (int)Math.round(Visualizer.this.projectile.maxHeight()))) {
                    g.setColor(Color.gray);
                }
                g.drawLine(40, y, 60, y-1);
                g.drawLine(40, y, 60, y);
                g.drawLine(40, y, 60, y+1);

                //align in center of number
                if (scale >= 100) {
                    g.drawString(""+scale, 15, y+5);
                } else if (scale >= 10) {
                    g.drawString(""+scale, 20, y+5);
                } else {
                    g.drawString(""+scale, 25, y+5);
                }
            }
        }
        
//         public void target(Graphics g) {
//             g.setColor(Color.red);
//             
//             g.fillOval(this.width - 170, 870, 20, 20);
//         }
//         
//         public void buildings(Graphics g) {
//     
//             int width = (int)(Math.random()*100+100); //random width of building
//             int height = (int)(Math.random()*350+100); // random height for the first building
//           
//             int yMax = 712;   
//             //loop through to make buildings
//             for (int i = 460; i < yMax + 460; i += (width + 10)){  //previously i = 0
//                g.setColor(Color.black);
//                g.fillRect(i, 880-height, width, height); //previously yMax - height for 2nd param
//                height = (int)(Math.random()*462+100); // randomize height for next building
//             }
//         }
    }

    //new table definiton
    private class DrawTable extends JComponent {

        Object[][] data;
        boolean spawn;

        public DrawTable() {
            data = listToObject(Visualizer.this.projectile.table());
            spawn = false;
        }

        public void paint(Graphics g) {
            //extend Graphics to draw 2D shapes
            Graphics2D graphic = (Graphics2D)g;
            //cleans up edges of whatever is drawn on screen
            graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            String[] columnsNames = {"Step", "X Position (m)", "Y Position (m)", "Time (s)"};
            JTable table = new JTable(data, columnsNames);

            //make table selectable but not editable
            JTextField tf = new JTextField();
            tf.setEditable(false);
            DefaultCellEditor editor = new DefaultCellEditor(tf);
            table.setDefaultEditor(Object.class, editor);

            //turn off text focsuing in cells
            table.setFocusable(false);

            JScrollPane scrollTable = new JScrollPane(table); //without disabling resize, this makes multiple scroll bars

            //add scroll-able table into panel
            Visualizer.this.rightPanel.setLayout(new BorderLayout());
            Visualizer.this.rightPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
            Visualizer.this.rightPanel.add(scrollTable, BorderLayout.CENTER);
            //spawn the table behind the scenes so we see it on first click
            if (spawn == false) {
                Visualizer.this.rightPanel.revalidate();
                Visualizer.this.rightPanel.repaint();
                //then turn off to prevent lagging when scrolling
                spawn = true;
            }
        }


        public Object[][] listToObject(List<double[]> list) {
            Object[][] object = new Object[list.size()][4];

            for (int i = 0; i < list.size(); i++) {
                String[] cast = new String[4];

                cast[0] = String.format("%.0f", (double)i);
                cast[1] = String.format("%.2f", list.get(i)[1]); //x
                cast[2] = String.format("%.2f",list.get(i)[2]); //y
                cast[3] = String.format("%.2f",list.get(i)[0]); //time

                object[i] = cast;
            }

            return object;
        }
    }
    
    //new game definiton
    private class DrawGame extends JComponent {

        int width;
        int height;
        int targetX;
        
        ArrayList<Integer> buildings;

        public DrawGame(int width, int height, ArrayList<Integer> buildings) {
            this.width = width;
            this.height = height;
            this.targetX = width - 190;   //10 to the right of where fillOval is defined
            this.buildings = buildings;
        }
         
        public double getTarget() {
            return this.targetX - 60;
        }
        
        //Graphics is the base class that allows us to draw on our components
        //paint() is abstract so we define it here
        public void paint(Graphics g) {
            //extend Graphics to draw 2D shapes
            Graphics2D arc = (Graphics2D)g;
            //cleans up edges of whatever is drawn on screen
            arc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            markers(g); //markers first so axis overlaps them in black
            blank(g);
            target(g);
            buildings(g);
            parabola(g);

            //automated interpolation
            //scale/zoom grid
        }

       

        //draw blank grid without graph
        public void blank(Graphics g) {
            //draw white background for grid
            g.setColor(Color.white);
            g.fillRect(60, 0, this.width, this.height-200);

            g.setColor(Color.black);
            //x axis
            g.drawLine(20, this.height-200, this.width, this.height-200);
            g.drawLine(20, this.height-199, this.width, this.height-199);
            //y axis
            g.drawLine(60,0,60,this.height-160);
            g.drawLine(61,0,61,this.height-160);
            //----------------------------------------
        }

        //draw dotted parabola trajectory
        public void parabola(Graphics g) {
            // 0,0 coordiante for graph
            //g.fillOval(56, this.height-200, 10, 10);

            //uget information from outer class with Outer.this.field
            List<double[]> table = Visualizer.this.projectile.table();
            double maxY = Visualizer.this.projectile.maxHeight();

            //print dots
            g.setColor(Color.blue);
            for (int i = 0; i < table.size(); i++) {
                //show start/end as green
                if (i == 0 || i == table.size()-1) {
                    g.setColor(Color.green);
                //show normal as blue
                } else {
                    g.setColor(Color.blue);
                }

                double[] moment = table.get(i);
                int xp = (int)Math.round(moment[1]) + 60;
                /*
                //scale grid to arc
                //(maxHeight - this y val) * window height / maxHeight
                int yp = (int)Math.round((maxY - moment[2]) * (this.height-200) / maxY);
                */
                //scale arc to grid
                int yp = ((int)Math.round(maxY - moment[2])) + ((this.height-200) - (int)Math.round(Visualizer.this.projectile.maxHeight()));
                if (yp == (int)Math.round(Visualizer.this.projectile.maxHeight())) {
                    g.setColor(Color.yellow);
                    g.fillOval(xp-2, yp-2, 10, 10);
                }

                //show out of bounds as black
                if (xp > this.width || yp > this.height-200) {
                    g.setColor(Color.black);
                    g.fillOval(xp-2, yp-2, 10, 10);
                    break;
                } else {
                    //g.drawString(""+x, xp, yp); //print values next to dot
                    g.fillOval(xp-2, yp-2, 5, 5);
                }
            }
        }

        //create tick marks along to axis
        public void markers(Graphics g) {
            g.setColor(Color.red);
            //x-axis tick marks
            int yout = this.height-200;
            for (int x = 80, scale = 20; x <= this.width; x += 40, scale += 40) {
                //gray-out axis
                if (scale > (int)Math.round(Visualizer.this.projectile.maxRange())) {
                    g.setColor(Color.gray);
                }
                g.drawLine(x-1, yout, x, yout+20);
                g.drawLine(x, yout, x, yout+20);
                g.drawLine(x+1, yout, x, yout+20);

                //align in center of number
                if (scale >= 1000) {
                    g.drawString(""+scale, x-15, yout+35);
                } else if (scale >= 100) {
                    g.drawString(""+scale, x-10, yout+35);
                } else if (scale >= 10) {
                    g.drawString(""+scale, x-5, yout+35);
                } else {
                    g.drawString(""+scale, x, yout+35);
                }
            }

            //reset color
            g.setColor(Color.red);

            //y-axis tick marks
            for (int y = this.height-220, scale = 20; y > 0; y -= 40, scale += 40) {
                //gray-out axis
                if (y < ((this.height-200) - (int)Math.round(Visualizer.this.projectile.maxHeight()))) {
                    g.setColor(Color.gray);
                }
                g.drawLine(40, y, 60, y-1);
                g.drawLine(40, y, 60, y);
                g.drawLine(40, y, 60, y+1);

                //align in center of number
                if (scale >= 100) {
                    g.drawString(""+scale, 15, y+5);
                } else if (scale >= 10) {
                    g.drawString(""+scale, 20, y+5);
                } else {
                    g.drawString(""+scale, 25, y+5);
                }
            }
        }
        
        public void target(Graphics g) {
            g.setColor(Color.red);
            
            g.fillOval(this.targetX - 10, 870, 20, 20);
        }
        
        public void buildings(Graphics g) {
        	int width = (int)(Math.random()*100+100); //random width of building
             int height = (int)(Math.random()*100+300); // random height for the first building
             
             int xMax = 700;
             int yMax = 600;   
             //loop through to make buildings
             for (int i = 2*width; i < xMax /*+ 460*/; i += (width + 10)){  //previously i = 0
                g.setColor(Color.black);
                g.fillRect(i, yMax-height, width, height); //previously yMax - height for 2nd param
                height = (int)(Math.random()*100+300); // randomize height for next building
             }        
        }
    }
   
}
