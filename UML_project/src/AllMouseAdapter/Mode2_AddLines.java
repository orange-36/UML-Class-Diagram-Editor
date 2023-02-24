package AllMouseAdapter;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import UMLLine.*;
import UMLObject.BaseObject;

import main.*;

public class Mode2_AddLines extends MouseAdapter  {
	private String type;

    private BaseObject source = null;
    private BaseObject destination = null;

    private Point originalPoint = null;

    public Mode2_AddLines(String type) {
        this.type = type;
    }

    private BaseLine createUMLLine(BaseObject source, Point originalPoint, Point currentPoint) {
        switch (this.type) {
            case "Association":
                return new AssociationLine(source, originalPoint, currentPoint);
            case "Generalization":
                return new GeneralizationLine(source, originalPoint, currentPoint);                
            case "Composition":
                return new CompositionLine(source, originalPoint,  currentPoint);
            default:
                return null;
        }
    }

    private BaseLine createUMLLine(BaseObject source, Point originalPoint,
            BaseObject destination, Point currentPoint) {
        switch (this.type) {
            case "Association":
                return new AssociationLine(source, originalPoint, destination, currentPoint);
            case "Generalization":
                return new GeneralizationLine(source, originalPoint, destination, currentPoint);                
            case "Composition":
                return new CompositionLine(source, originalPoint, destination, currentPoint);
            default:
                return null;
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        BaseObject component;

        component = main.UMLwindow.canvas.getComponentWithin(mouseEvent.getX(), mouseEvent.getY());

        if (component != null && component.isConnectable()) {
            this.source = component;
            this.originalPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
        }

        main.UMLwindow.canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (this.source != null) {
            main.UMLwindow.canvas.setDrawingLine(this.createUMLLine(this.source, this.originalPoint,
                    new Point(mouseEvent.getX(), mouseEvent.getY())));
        }

        main.UMLwindow.canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        BaseObject component;

        component = main.UMLwindow.canvas.getComponentWithin(mouseEvent.getX(), mouseEvent.getY());

        if (component != null && component.isConnectable()) {
            this.destination = component;
        }

        if (this.source != null && this.destination != null) {
            main.UMLwindow.canvas.setDrawingLine(null);

            if (this.source != this.destination) {
                main.UMLwindow.canvas.addUMLConntectionLine(this.createUMLLine(this.source, this.originalPoint,
                        this.destination, new Point(mouseEvent.getX(), mouseEvent.getY())));
            }
        } else if (this.source != null && this.destination == null) {
            main.UMLwindow.canvas.setDrawingLine(null);
        }

        this.source = null;
        this.destination = null;

        main.UMLwindow.canvas.repaint();
    }
}
