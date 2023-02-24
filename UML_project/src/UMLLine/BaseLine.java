package UMLLine;

import java.awt.*;

import UMLObject.BaseObject;

public abstract class BaseLine {
    protected BaseObject source = null;
    protected BaseObject destination = null;

    protected String sourcePortAlias = null;
    protected String destinationPortAlias = null;

    protected Point currentPoint = null;

    public BaseLine(BaseObject source, Point originalPoint, Point currentPoint) {
        this.currentPoint = currentPoint;
        this.source = source;
        this.sourcePortAlias = source.getPortAlias(originalPoint);
    }

    public BaseLine(BaseObject source, Point originalPoint, BaseObject destination,
            Point currentPoint) {
        this.source = source;
        this.destination = destination;
        this.sourcePortAlias = source.getPortAlias(originalPoint);
        this.destinationPortAlias = destination.getPortAlias(currentPoint);
    }

    public void drawBodyOfLine(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        int sourceX;
        int sourceY;
        int destinationX;
        int destinationY;

        if (destination != null) {
            sourceX = (int) this.source.getPortByAlias(this.sourcePortAlias).getX();
            sourceY = (int) this.source.getPortByAlias(this.sourcePortAlias).getY();
            destinationX = (int) this.destination.getPortByAlias(this.destinationPortAlias).getX();
            destinationY = (int) this.destination.getPortByAlias(this.destinationPortAlias).getY();
        } else {
            sourceX = (int) this.source.getPortByAlias(this.sourcePortAlias).getX();
            sourceY = (int) this.source.getPortByAlias(this.sourcePortAlias).getY();

            destinationX = (int) currentPoint.getX();
            destinationY = (int) currentPoint.getY();
        }
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(sourceX, sourceY, destinationX, destinationY);
    }

    public abstract void drawArrowOfLine(Graphics graphics);
}
