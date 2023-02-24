package AllMouseAdapter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;

import UMLObject.BaseObject;

import main.Canvas;

public class Mode1_Selector extends MouseAdapter{
	private boolean singleSelection = false;
	private ImageIcon selectionImage = new ImageIcon(Mode1_Selector.class.getResource(""));
	
    private int originalX;
    private int originalY;

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (main.UMLwindow.canvas.getSelectedAreaLabel() != null) {
        	main.UMLwindow.canvas.clearSelectedAreaLabel();
        }

        BaseObject component = main.UMLwindow.canvas.getComponentWithin(mouseEvent.getX(), mouseEvent.getY());
        if (component != null) {
            // Single Selection

            this.originalX = (int) component.getMousePosition().getX();
            this.originalY = (int) component.getMousePosition().getY();

            ArrayList<BaseObject> selections = new ArrayList<BaseObject>();
            selections.add(component);

            main.UMLwindow.canvas.clearSelections();
            main.UMLwindow.canvas.setSelections(selections);

            this.singleSelection = true;
        } else {
            this.originalX = mouseEvent.getX();
            this.originalY = mouseEvent.getY();

            main.UMLwindow.canvas.clearSelections();

            JLabel selectedArea = new JLabel();
            selectedArea.setOpaque(true);
            selectedArea.setBackground(Color.PINK);
            
            selectedArea.setBounds(this.originalX, this.originalY, 0, 0);

            main.UMLwindow.canvas.setSelectedArea(selectedArea);
        }

        main.UMLwindow.canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (this.singleSelection) {
            // Single Selection

            main.UMLwindow.canvas.getSelections().iterator().next().moveTo(mouseEvent.getX() - originalX, mouseEvent.getY() - originalY);
         
        } else {
            int upperLeftX = Math.min(this.originalX, mouseEvent.getX());
            int upperLeftY = Math.min(this.originalY, mouseEvent.getY());
            int width = Math.abs(this.originalX - mouseEvent.getX());
            int height = Math.abs(this.originalY - mouseEvent.getY());                        
            
            ArrayList<BaseObject> selections = main.UMLwindow.canvas
                    .getAllComponentWithin(new Rectangle(upperLeftX, upperLeftY, width, height));

            main.UMLwindow.canvas.clearSelections();
            main.UMLwindow.canvas.setSelections(selections);
            main.UMLwindow.canvas.setSelectedAreaLine(upperLeftX, upperLeftY, width, height);
        }

        main.UMLwindow.canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        // System.out.println("SelectMode onReleased");

        if (main.UMLwindow.canvas.getSelectedAreaLabel() != null) {
            if (main.UMLwindow.canvas.getSelections().isEmpty()) {
                main.UMLwindow.canvas.clearSelectedAreaLabel();
            } else {
                // Resize Selection Area

                int upperLeftX = Integer.MAX_VALUE;
                int upperLeftY = Integer.MAX_VALUE;
                int width = 0;
                int height = 0;

                Iterator<BaseObject> iterator = main.UMLwindow.canvas.getSelections().iterator();
                while (iterator.hasNext()) {
                    BaseObject tmp = iterator.next();

                    upperLeftX = Math.min(upperLeftX, tmp.getX());
                    upperLeftY = Math.min(upperLeftY, tmp.getY());
                }

                iterator = main.UMLwindow.canvas.getSelections().iterator();
                while (iterator.hasNext()) {
                    BaseObject tmp = iterator.next();

                    width = Math.max(width, Math.abs((tmp.getX() + tmp.getWidth()) - upperLeftX));
                    height = Math.max(height, Math.abs((tmp.getY() + tmp.getHeight()) - upperLeftY));
                }
                
                main.UMLwindow.canvas.getSelectedAreaLabel().setBounds(upperLeftX, upperLeftY, width, height);
				selectionImage.setImage(selectionImage.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
				main.UMLwindow.canvas.getSelectedAreaLabel().setIcon(selectionImage);
				main.UMLwindow.canvas.setSelectedAreaLine(upperLeftX, upperLeftY, width, height);
				
                main.UMLwindow.canvas.repaint();
            }
        }

        this.singleSelection = false;
    }

    
}
