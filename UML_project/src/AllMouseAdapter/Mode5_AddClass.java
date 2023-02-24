package AllMouseAdapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import UMLObject.ClassObject;
import main.*;


public class Mode5_AddClass extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("³Q©I¥s" +e.getX() +" "+ e.getY());
		
		ClassObject classObject = new ClassObject();
		//System.out.println("" +classObject.ports.getEastPort().x +" "+ classObject.ports.getEastPort().y );
		//System.out.println("" +classObject.ports.getNorthPort().x +" "+ classObject.ports.getNorthPort().y );
		
		main.UMLwindow.canvas.addBaseUMLObject(e.getX(), e.getY(),classObject,classObject.getZAxisHeight());		
		main.UMLwindow.canvas.repaint();
	}
}
