package main;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;

import UMLLine.*;
import UMLObject.*;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Canvas extends JLayeredPane {
    private ArrayList<BaseObject> selectedUMLObjects = new ArrayList<BaseObject>();
    private ArrayList<BaseObject> allUMLObjectOfCanvas = new ArrayList<BaseObject>();
    private JLabel selectedAreaLabel;
    private int selectedAreaIndexX = 0 ,  selectedAreaIndexY =0,  selectedAreaIndexWidth=0 ,  selectedAreaIndexheight=0;
    
    private BaseLine drawing = null;
    private ArrayList<BaseLine> connections = new ArrayList<BaseLine>();
    private MouseAdapter canvas_Mouse_Behavior;
    
	
	public void paint(Graphics g)
	{

		super.paint(g);		
		
		if (this.drawing != null) {
            drawing.drawBodyOfLine(g);
            drawing.drawArrowOfLine(g);
        }
        for (BaseLine connection : connections) {
            connection.drawBodyOfLine(g);
        }

        for (BaseLine connection : connections) {
            connection.drawArrowOfLine(g);
        }
        drawSelectedAreaLine(g,selectedAreaIndexX,selectedAreaIndexY,selectedAreaIndexWidth,selectedAreaIndexheight);
	}
	
	//設置當前mode和對應滑鼠動作
	public void setCanvasMouseBehavior(MouseAdapter canvasBehavior) {
        clearSelections();
        this.clearSelectedAreaLabel();

        this.removeMouseListener(this.canvas_Mouse_Behavior);
        this.removeMouseMotionListener(this.canvas_Mouse_Behavior);

        this.canvas_Mouse_Behavior = canvasBehavior;
        
        this.addMouseListener(this.canvas_Mouse_Behavior);
        this.addMouseMotionListener(this.canvas_Mouse_Behavior);
    }
	
	//在畫布上放入物件
	public void addBaseUMLObject(int x,int y,BaseObject component, int zAxisHeight) {
		component.setBounds(x , y  , component.getWidth(), component.getHeight());
		component.setCenterLocation(x, y);
		System.out.println("加入canvas" +component.getLocation().x +" "+ component.getLocation().y );
		this.add(component);
        this.setLayer(component, zAxisHeight);
        this.allUMLObjectOfCanvas.add(component);
        UMLwindow.canvas.repaint();
    }
	
	//在畫布上移除物件
	public void removeBaseUMLObject(BaseObject component) {
        this.remove(component);
        this.allUMLObjectOfCanvas.remove(component);
    }
	
	public void addUMLConntectionLine(BaseLine newConnection) {
        connections.add(newConnection);
        this.repaint();
    }
	
    public void setDrawingLine(BaseLine drawing) {
        this.drawing = drawing;
        this.repaint();
    }
	
	//取得該位置最上方的物件
	public BaseObject getComponentWithin(int x, int y) {
        BaseObject result = null;
        for (BaseObject component : this.allUMLObjectOfCanvas) {
            if (component.getBounds().contains(x, y)) {
                if (result == null || component.getZAxisHeight() > result.getZAxisHeight()) {
                    result = component;
                }
            }
        }

        return result;
    }
	
	//取得矩形內的所有物件
	public ArrayList<BaseObject> getAllComponentWithin(Rectangle rectangle) {
        ArrayList<BaseObject> results = new ArrayList<BaseObject>();

        for (BaseObject component : this.allUMLObjectOfCanvas) {
            if (rectangle.contains(component.getBounds())) {
                results.add(component);
            }
        }

        return results;
    }
	
	//將物件們處於被選取狀態
	public void setSelections(ArrayList<BaseObject> selections) {
        for (BaseObject selection : selections) {
            selection.setPortVisible(true);
        }

        this.selectedUMLObjects = selections;
        this.repaint();
    }
	
	//解除所有物件的被選取狀態
	public void clearSelections() {
        for (BaseObject selection : this.selectedUMLObjects) {
            selection.setPortVisible(false);
        }

        this.selectedUMLObjects.clear();
    }

	//取得被選取的所有物件
	public ArrayList<BaseObject> getSelections() {
        return selectedUMLObjects;
    }
	
	//設置選取框
	public void setSelectedArea(JLabel selectedArea) {
        this.selectedAreaLabel = selectedArea;
        this.add(this.selectedAreaLabel);
        this.setLayer(this.selectedAreaLabel, -1);
    }
	
	//取得選取框
    public JLabel getSelectedAreaLabel() {
        return this.selectedAreaLabel;
    }
    
    //清除選取框
    public void clearSelectedAreaLabel() {
        if (this.selectedAreaLabel != null) {
            this.remove(this.selectedAreaLabel);
            this.repaint();

            this.selectedAreaLabel = null;
        }
        setSelectedAreaLine( 0, 0, 0, 0);
    }
	
    public void drawSelectedAreaLine(Graphics graphics , int upperLeftX, int upperLeftY, int width, int height) {
    	Graphics2D graphics2D = (Graphics2D) graphics;
        float []f={10f,30f};
        BasicStroke bs=new BasicStroke(6,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,10.0F,f,0f);
        graphics2D.setStroke(bs);
        graphics2D.setColor(Color.lightGray);
        graphics2D.drawRect( upperLeftX ,  upperLeftY , width , height);
  	
    }
    public void setSelectedAreaLine( int upperLeftX, int upperLeftY, int width, int height) {
    	this.selectedAreaIndexX = upperLeftX ; 
    	this.selectedAreaIndexY = upperLeftY ;
    	this.selectedAreaIndexWidth= width ;
    	this.selectedAreaIndexheight= height ;
    }
}
