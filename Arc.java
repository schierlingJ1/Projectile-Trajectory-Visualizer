import java.util.List;
import javax.swing.*;
import java.awt.*;


public class Arc extends JFrame {

    private JFrame frame;
    private ProjectileMotion projectile;

    public static void main(String args[]) {
        new Arc();
    }

    public Arc() {
        // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 2/3;
        int width = screenSize.width * 2/3;

        this.frame = new JFrame("PTV");
        this.projectile = new ProjectileMotion();

        this.frame.setSize(width, height);                                //set applet frame width/height
        this.frame.setTitle("Arc");                                       //shape applet title
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        //close applet on X
        this.frame.getRootPane().setBorder(                               //border padding
            BorderFactory.createEmptyBorder(25, 25, 0, 0));
        this.frame.add(new DrawTable(width, height), BorderLayout.CENTER);  //new table component
        this.frame.add(new DrawArc(width, height), BorderLayout.CENTER);  //new arc component
        this.frame.setVisible(true);                                      //make applet visible

        //take logic out of constructor - make window, display, close, etc...
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
            parabola(g);

            //automated interpolation
            //scale/zoom grid
        }

        //draw grid/axis
        public void grid(Graphics g) {
            //modified online code---------------------------------
            //http://ibcomp.fis.edu/Java/parabola.html
            g.setColor(Color.gray);
            //draw horizontal lines
            for (int x = 0; x <= this.height-199; x = x + 20) {
                g.drawLine(60,x,this.width-106,x);
            }
            //draw vertical lines
            for (int y = 60; y <= this.width-100; y = y + 20) {
                g.drawLine(y,0,y,this.height-199);
            }

            g.setColor(Color.black);
            //x axis
            g.drawLine(20, this.height-200, this.width-105, this.height-200);
            g.drawLine(20, this.height-199, this.width-105, this.height-199);
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
            List<double[]> table = Arc.this.projectile.table();
            double maxY = Arc.this.projectile.maxHeight();

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
                int yp = ((int)Math.round(maxY - moment[2])) + ((this.height-200) - (int)Math.round(Arc.this.projectile.maxHeight()));
                if (yp == (int)Math.round(Arc.this.projectile.maxHeight())) {
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
            for (int x = 80, scale = 20; x <= this.width-100; x += 40, scale += 40) {
                //gray-out axis
                if (scale > (int)Math.round(Arc.this.projectile.maxRange())) {
                    g.setColor(Color.gray);
                }
                g.drawLine(x-1, yout, x, yout+20);
                g.drawLine(x, yout, x, yout+20);
                g.drawLine(x+1, yout, x, yout+20);

                //align in center of number
                if (scale >= 100) {
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
                if (y < ((this.height-220) - (int)Math.round(Arc.this.projectile.maxHeight()))) {
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
    }

    //new table definiton
    private class DrawTable extends JComponent {

        int width;
        int height;

        public DrawTable(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public void paint(Graphics g) {
            table(Arc.this.frame);
        }

        public void table(JFrame frame) {
            String[] columnsNames = {"step", "x", "y", "time"};

            Object[][] data = listToObject(Arc.this.projectile.table());

            JTable table = new JTable(data, columnsNames);
            Arc.this.frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
            Arc.this.frame.add(table, BorderLayout.EAST);
            Arc.this.frame.pack();
            //Arc.this.frame.setLocationRelativeTo(null);
            Arc.this.frame.setVisible(true);
        }

        public Object[][] listToObject(List<double[]> list) {
            Object[][] object = new Object[list.size()][4];

            for (int i = 0; i < list.size(); i++) {
                String[] cast = new String[4];

                cast[0] = String.format("%.2f", (double)i);
                cast[1] = String.format("%.2f", list.get(i)[1]); //x
                cast[2] = String.format("%.2f",list.get(i)[2]); //y
                cast[3] = String.format("%.2f",list.get(i)[0]); //time

                object[i] = cast;
            }

            return object;
        }

    }
}
