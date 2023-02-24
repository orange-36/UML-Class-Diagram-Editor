package UMLLine;

import java.awt.*;

import UMLObject.*;

public class CompositionLine extends BaseLine {
    public CompositionLine(BaseObject source, Point originalPoint, Point currentPoint) {
        super(source, originalPoint, currentPoint);
    }

    public CompositionLine(BaseObject source, Point originalPoint, BaseObject destination,
            Point currentPoint) {
        super(source, originalPoint, destination, currentPoint);
    }

    @Override
    public void drawArrowOfLine(Graphics graphics) {
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

        double dx = destinationX - sourceX;
        double dy = destinationY - sourceY;

        double D = Math.sqrt(dx * dx + dy * dy);
        double unitVectorX = dx/D;
        double unitVectorY = dy/D;
        double unitVectorVerticalX = -1*dy/D ;
        double unitVectorVerticalY = dx/D ;
        
        double diagonal = 24;
        double diagonalVertical = 18;
        
        double rearX = destinationX - unitVectorX*diagonal;
        double rearY = destinationY - unitVectorY*diagonal;
        
        double leftPiontX = destinationX  - unitVectorX*diagonal/2 + unitVectorVerticalX*diagonalVertical/2;
        double leftPiontY = destinationY  - unitVectorY*diagonal/2 + unitVectorVerticalY*diagonalVertical/2;
        
        double rightPiontX = destinationX - unitVectorX*diagonal/2 - unitVectorVerticalX*diagonalVertical/2;
        double rightPiontY = destinationY - unitVectorY*diagonal/2 - unitVectorVerticalY*diagonalVertical/2;
        
        graphics2D.setStroke(new BasicStroke(6));
        
        /*graphics2D.setColor(Color.BLACK);        
        graphics2D.drawOval( (int)rearX -15,(int)rearY -15, 30, 30);
        graphics2D.drawOval( (int)leftPiontX -5,(int)leftPiontY -5, 10, 10);
        graphics2D.drawOval( (int)rightPiontX -5,(int)rightPiontY -5, 10, 10);        
        graphics2D.setColor(Color.white);
        graphics2D.fillOval( (int)rearX -15,(int)rearY -15, 30, 30);
        graphics2D.fillOval( (int)leftPiontX -5,(int)leftPiontY -5, 10, 10);
        graphics2D.fillOval( (int)rightPiontX -5,(int)rightPiontY -5, 10, 10);*/
               
        int[] xpoints = { destinationX, (int) rightPiontX , (int)rearX  , (int) leftPiontX };
        int[] ypoints = { destinationY, (int) rightPiontY , (int)rearY  , (int) leftPiontY };

        graphics2D.setColor(Color.BLACK);
        graphics2D.drawPolygon(xpoints, ypoints, 4);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillPolygon(xpoints, ypoints, 4);
    }
}
