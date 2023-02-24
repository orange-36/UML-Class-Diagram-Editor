package UMLLine;

import java.awt.*;

import UMLObject.*;

public class GeneralizationLine extends BaseLine {
    public GeneralizationLine(BaseObject source, Point originalPoint, Point currentPoint) {
        super(source, originalPoint, currentPoint);
    }

    public GeneralizationLine(BaseObject source, Point originalPoint, BaseObject destination,
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

        int dx = destinationX - sourceX;
        int dy = destinationY - sourceY;

        double D = Math.sqrt(dx * dx + dy * dy);
        double unitVectorX = dx/D;
        double unitVectorY = dy/D;
        double unitVectorVerticalX = -1*dy/D ;
        double unitVectorVerticalY = dx/D ;
        
        double diagonal = 30;
        double diagonalVertical = 20;
        
        double rearX = destinationX - unitVectorX*diagonal;
        double rearY = destinationY - unitVectorY*diagonal;
        
        double rightPiontX = destinationX - unitVectorX*diagonal/2 + unitVectorVerticalX*diagonalVertical/2;
        double rightPiontY = destinationY - unitVectorY*diagonal/2 + unitVectorVerticalY*diagonalVertical/2;
        
        double leftPiontX = destinationX - unitVectorX*diagonal/2 - unitVectorVerticalX*diagonalVertical/2;
        double leftPiontY = destinationY - unitVectorY*diagonal/2 - unitVectorVerticalY*diagonalVertical/2;
        
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(6));
               
        int[] xpoints = { destinationX, (int) rightPiontX, (int) leftPiontX , (int) rearX };
        int[] ypoints = { destinationY, (int) rightPiontY, (int) leftPiontY , (int) rearY };

        graphics2D.setColor(Color.BLACK);
        graphics2D.drawPolygon(xpoints, ypoints, 3); // 4:draw lightning 3:draw triangle
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillPolygon(xpoints, ypoints, 3); //
    }
}
