package UMLObject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JLabel;

import main.UMLwindow;

public class GroupObject extends BaseObject {
	private ArrayList<BaseObject> groupMembers = new ArrayList<BaseObject>();
    public GroupObject(ArrayList<BaseObject> selections , int relativeX ,int relativeY){
    	super();
    	this.isPortVisible = false;
    	this.connectable = false;
    	this.nameChangable = false;
    	for (BaseObject component : selections) {
    		this.groupMembers.add(component);
        }
    	
    	this.width = UMLwindow.canvas.getSelectedAreaLabel().getWidth();
        this.height = UMLwindow.canvas.getSelectedAreaLabel().getHeight();
        
        
    	for (BaseObject component : this.groupMembers) {
    		component.setParant(this);
    		UMLwindow.canvas.removeBaseUMLObject(component);
    		component.setBounds( 
    				component.getLocation().x - relativeX,
    				component.getLocation().y - relativeY,
    				component.getWidth(),
    				component.getHeight()	);
    		this.add(component);
        }
    }
    @Override
    public void ungroupUMLGroup( int relativeX ,int relativeY){
    	System.out.println("group¤j¤p" + this.groupMembers.size() );
    	for (BaseObject component : this.groupMembers) {
    		this.remove(component);
    		component.setParant(null);
    		System.out.println("²¾¥Xgroup" +component.getLocation().x +" "+ component.getLocation().y );
    		UMLwindow.canvas.addBaseUMLObject(relativeX + component.getLocation().x ,
    				relativeY + component.getLocation().y  ,
    				component , component.getZAxisHeight());   		
        }
    	UMLwindow.canvas.removeBaseUMLObject(this);
    	UMLwindow.canvas.getSelections().remove(this);
    	UMLwindow.canvas.repaint();
    }
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        float []f={10f,30f};
        BasicStroke bs=new BasicStroke(6,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0F,f,0f);
        graphics2D.setStroke(bs);
        graphics2D.setColor(Color.lightGray);
        graphics2D.drawRect(0, 0, this.width, this.height);

        super.paintComponent(graphics);
    }
}
