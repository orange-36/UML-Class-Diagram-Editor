package MenuButtonEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.UMLwindow;

public class ChangeObjectName implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(UMLwindow.canvas.getSelections().size() == 1) {
			JFrame jFrame = new JFrame();
			String oldName = UMLwindow.canvas.getSelections().get(0).getLabel().getText();
	        String getMessage = JOptionPane.showInputDialog(jFrame, "Enter new object name");
			System.out.println( ":" + getMessage);
			if (getMessage == null) {
				
			}
			else {
				UMLwindow.canvas.getSelections().get(0).getLabel().setText(getMessage);
			}
			
		}
	}
}
