package MenuButtonEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import UMLObject.GroupObject;
import main.UMLwindow;

public class GroupObjects implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(UMLwindow.canvas.getSelections().size() > 1) {
			//UMLwindow.canvas.getSelectedArea().getLocale();
			GroupObject newgroup = new GroupObject(UMLwindow.canvas.getSelections(),UMLwindow.canvas.getSelectedAreaLabel().getLocation().x,UMLwindow.canvas.getSelectedAreaLabel().getLocation().y);
			UMLwindow.canvas.addBaseUMLObject(UMLwindow.canvas.getSelectedAreaLabel().getLocation().x ,
					UMLwindow.canvas.getSelectedAreaLabel().getLocation().y ,
					newgroup, newgroup.getZAxisHeight());
			
		}
		main.UMLwindow.canvas.clearSelections();
		main.UMLwindow.canvas.clearSelectedAreaLabel();
	}
}
