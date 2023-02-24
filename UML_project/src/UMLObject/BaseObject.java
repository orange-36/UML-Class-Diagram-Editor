package UMLObject;

import java.awt.*;
import java.util.ArrayList;
import main.*;
import javax.swing.JComponent;
import javax.swing.JLabel;


public abstract class BaseObject extends JComponent {
    private static final long serialVersionUID = 2259886535991683392L;

    protected static int itemCounter = 0;
    protected int zAxisHeight = 0;

    protected Point centerLocation = new Point() ;
    protected BaseObject parentObject = null;
    protected int width;
    protected int height;
    protected int offsetX = 5;
    protected int offsetY = 5;

    protected boolean isPortVisible = false;
    protected boolean connectable = true;
    protected boolean nameChangable = true;
    
    public Port ports = null;

    protected JLabel label = new JLabel();

    public BaseObject() {
        zAxisHeight = itemCounter++;

    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (this.isPortVisible && this.ports != null) {
            for (Point point : this.ports.getPorts()) {
                graphics2D.setColor(Color.BLACK);
                graphics2D.fillOval((int) point.getX() -5, (int) point.getY()-5 , 10, 10);
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setPortVisible(boolean isVisible) {
        this.isPortVisible = isVisible;
        this.repaint();
    }

    public int getZAxisHeight() {
        return this.zAxisHeight;
    }

    public String getPortAlias(Point destination) {
        String alias = null;

        double angle = (double) Math.toDegrees(Math.atan2(destination.getX() - this.getCenterLocation().getX(),
                destination.getY() - this.getCenterLocation().getY())) - 90;

        if (angle < 0) {
            angle += 360;
        }

        if (135 > angle && angle >= 45) {
            alias = "north";
        } else if (45 > angle || angle >= 315) {
            alias = "east";
        } else if (315 > angle && angle >= 225) {
            alias = "south";
        } else {
            alias = "west";
        }

        return alias;
    }

    public Point getPortByAlias(String alias) {
        Point result = null;

        if (alias == "north") {
            result = this.ports.getNorthPort();
        } else if (alias == "east") {
            result = this.ports.getEastPort();
        } else if (alias == "south") {
            result = this.ports.getSouthPort();
        } else if (alias == "west") {
            result = this.ports.getWestPort();
        }
        /*if(this.parentObject != null) {
        	return new Point((int) ( this.getLocation().x + result.getX()) + this.parentObject.getLocation().x,
                    (int) (	this.getLocation().y + result.getY() + this.parentObject.getLocation().y ));
        }*/
        return new Point((int) ( this.getRelateLocation().x + result.getX()),
                (int) (	this.getRelateLocation().y + result.getY()));
    }
    
    public Point getRelateLocation() {
    	if(this.parentObject != null) {
        	return new Point((int) ( this.getLocation().x ) + this.parentObject.getRelateLocation().x,
                    (int) (	this.getLocation().y  + this.parentObject.getRelateLocation().y ));
        }
        return new Point((int) ( this.getLocation().x ),
                (int) (	this.getLocation().y ) );
    }
    
    
    public void setCenterLocation(int X,int Y) {
        this.centerLocation.setLocation(X, Y);
    }
    
    public void setParant(BaseObject parentObject) {
        this.parentObject = parentObject;
    }
    
    public Point getCenterLocation() {
    	this.centerLocation.setLocation(this.getLocation().x + this.getWidth()/2, this.getLocation().y + this.getHeight()/2 );
        return this.centerLocation;
    }

    public void moveTo(int x, int y) {
        this.setLocation(x, y);
        
    }

    public JLabel getLabel() {
        return this.label;
    }

    // Percolating up due to Composite Pattern.
    public ArrayList<BaseObject> getUMLComponents() {
        return null;
    }

    public boolean isConnectable() {
        return this.connectable;
    }

    public boolean isNameChangable() {
        return this.nameChangable;
    }
    
    public void ungroupUMLGroup(int x,int y) {} ;
    
}
