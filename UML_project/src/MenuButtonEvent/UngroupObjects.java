package MenuButtonEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UMLObject.BaseObject;
import UMLObject.GroupObject;
import main.UMLwindow;

public class UngroupObjects implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( UMLwindow.canvas.getSelections().size() == 1 && !UMLwindow.canvas.getSelections().get(0).isConnectable() && !UMLwindow.canvas.getSelections().get(0).isNameChangable()) {
			//UMLwindow.canvas.getSelectedArea().getLocale();
			BaseObject thisUMLGroup = UMLwindow.canvas.getSelections().get(0);
			thisUMLGroup.ungroupUMLGroup(thisUMLGroup.getLocation().x ,thisUMLGroup.getLocation().y);
			System.out.println( "ungroup " + thisUMLGroup.getLocation().x + " " + thisUMLGroup.getLocation().y);
		}
	}
}
