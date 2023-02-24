package AllMouseAdapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import UMLObject.ClassObject;
import UMLObject.UseCaseObject;

public class Mode6_AddUseCase extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("³Q©I¥s" +e.getX() +" "+ e.getY());
		UseCaseObject classObject = new UseCaseObject();
		main.UMLwindow.canvas.addBaseUMLObject(e.getX(), e.getY(),classObject,classObject.getZAxisHeight());		
		main.UMLwindow.canvas.repaint();
	}
}
