import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Memray for Homework of INFSCI 2470
 */
public class RuiMengApplet extends JApplet {

    private String name = "";
    private String shape = "";
    private String color = "";
    private String bgColor = "";
    private int x = 300;
    private int y = 100;
    private String ftStyle = "";
    private int ftSize = 30;

    private String x1 = "";
    private String y1 = "";
    private String ftSize1 = "";

    /**
     * Initialization
     */
    public void init() {
        this.name = this.getParameter("NAME");
        this.shape = this.getParameter("SHAPE");
        this.color = this.getParameter("COLOR");
        this.bgColor = this.getParameter("BGCOLOR");
        this.x1 = this.getParameter("X");
        this.y1 = this.getParameter("Y");
        this.ftSize1 = this.getParameter("FTSIZE");
        this.ftStyle = this.getParameter("FTSTYLE");
        ;

        //set background color
        if (bgColor == null || bgColor.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Background color should not be empty");
            this.getContentPane().setBackground(Color.white);
        } else {
            Color bgc = getColor(bgColor);
            this.getContentPane().setBackground(bgc);
        }


    }

    // check nullpointer exception
    public boolean isNull() {

        if (name != null && shape != null && color != null && bgColor != null && (x1 != null && x1.matches("[-+]?\\d*\\.?\\d+")) && (y1 != null && y1.matches("[-+]?\\d*\\.?\\d+")) && ftStyle != null && (ftSize1 != null && ftSize1.matches("[-+]?\\d*\\.?\\d+"))) {
            this.x = (new Integer(x1)).intValue();
            this.y = (new Integer(y1)).intValue();
            this.ftSize = (new Integer(ftSize1)).intValue();
            return false;

        } else {

            this.name = "Parameters are null";
            this.shape = "ROUNDRECT";
            this.color = "black";
            this.bgColor = "white";
            this.x = 300;
            this.y = 80;
            this.ftStyle = "BOLD";
            this.ftSize = 25;


            return true;
        }


    }

    public void paint(Graphics g) {
        super.paint(g);

        if (isNull())

            JOptionPane.showMessageDialog(null, "Parameter should not be empty");

//        else
//        {

        //set forground color
        Color c = getColor(color);
        g.setColor(c);

        // set shape position, set default x,y for shape
        Dimension d = getSize();
        if (x > d.width || y > d.height) {
            x = d.width;
            y = d.height;
        }


        int shapeX = (d.width - x) / 2;
        int shapeY = (d.height - y) / 2;

        // draw shape, SHAPE are OVAL, RECT and ROUNDRECT
        if (shape.equals("OVAL")) {

            g.drawOval(shapeX, shapeY, x, y);

        } else if (shape.equals("RECT")) {

            g.drawRect(shapeX, shapeY, x, y);
        } else if (shape.equals("ROUNDRECT")) {
            g.drawRoundRect(shapeX, shapeY, x, y, 20, 20);
        } else
            JOptionPane.showMessageDialog(null, "Wrong shape type");

        // set font and center text in the shape

        Font font = getFont(ftStyle);
        FontMetrics fm = g.getFontMetrics(font);
        g.setFont(font);
        int nameXPosi = fm.getHeight();
        int totalWidth = (fm.stringWidth(name)) + 4;
        int textX = (x - totalWidth) / 2 + shapeX;
        int textY = (y - fm.getHeight()) / 2 + shapeY;
        if (totalWidth >= x || nameXPosi >= totalWidth) {
            JOptionPane.showMessageDialog(null, "Your text is too long or too large");

        } else
            g.drawString(name, textX, textY + fm.getAscent());


        // }

    }

    // Writh colors into a haspmap for searching
    public Color getColor(String colorName) {
        //black, blue, cyan, darkGray, gray, green, lightGray, magenta, orange, pink, red, white, yellow

        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("black", Color.black);
        colorMap.put("blue", Color.blue);
        colorMap.put("darkGray", Color.darkGray);
        colorMap.put("gray", Color.gray);
        colorMap.put("green", Color.green);
        colorMap.put("lightGray", Color.lightGray);
        colorMap.put("magenta", Color.magenta);
        colorMap.put("orange", Color.orange);
        colorMap.put("pink", Color.pink);
        colorMap.put("red", Color.red);
        colorMap.put("white", Color.white);
        colorMap.put("yellow", Color.yellow);

        if (!colorMap.containsKey(colorName)) {
            JOptionPane.showMessageDialog(null, "Wrong color");
            // set default color
            Color c = Color.black;
            return c;
        } else {
            Color c = colorMap.get(colorName);
            return c;

        }

        //return c;

    }

    // set font style
    public Font getFont(String ftStyle) {

        //REGULAR, BOLD, ITALIC, and BOLD ITALIC
        //set default font
        Font font = new Font("serif", Font.BOLD, ftSize);

        if (ftStyle.equals("BOLD"))
            font = new Font("serif", Font.BOLD, ftSize);
        else if (ftStyle.equals("REGULAR"))
            font = new Font("serif", Font.PLAIN, ftSize);
        else if (ftStyle.equals("ITALIC"))
            font = new Font("serif", Font.ITALIC, ftSize);
        else if (ftStyle.equals("BOLD ITALIC"))
            font = new Font("serif", Font.ITALIC + Font.BOLD, ftSize);
        else {
            JOptionPane.showMessageDialog(null, "Wrong font style");
            font = new Font("serif", Font.BOLD, ftSize);
            return font;
        }

        return font;

    }


}
