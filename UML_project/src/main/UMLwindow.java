package main;

import java.awt.EventQueue;

import UMLObject.*;
import AllMouseAdapter.*;
import MenuButtonEvent.ChangeObjectName;
import MenuButtonEvent.GroupObjects;
import MenuButtonEvent.UngroupObjects;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UMLwindow {
	public static int mode;/*   select
								association
								generalization
								composition
								class
								use case       */
	private JFrame frame;
	public static Canvas canvas;
	private static ArrayList<JButton> functionButtons = new ArrayList<JButton>();
	private static ArrayList<ImageIcon> imageIconList = new ArrayList<ImageIcon>();
	
	private Mode1_Selector mode1_Selector = new Mode1_Selector();
	private Mode5_AddClass mode5_AddClass = new Mode5_AddClass();
	private Mode2_AddLines mode2_AddAssociationLine = new Mode2_AddLines("Association");
	private Mode2_AddLines mode3_GeneralizationLine = new Mode2_AddLines("Generalization");
	private Mode2_AddLines mode4_CompositionLine = new Mode2_AddLines("Composition");
	private Mode6_AddUseCase mode6_AddUseCase = new Mode6_AddUseCase();
	/**
	 * Create the application.
	 */
	public UMLwindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("my_UML_editor");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.RED);
		frame.setBounds(100, 100, 912, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		canvas = new Canvas();
		
		
		
		canvas.setBounds(100, 0, 800, 600);
		frame.getContentPane().add(canvas);
		
		setButtons();
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File\r\n");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("change object name");
		mntmNewMenuItem_4.addActionListener(new ChangeObjectName());
		
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Group");
		mntmNewMenuItem_3.addActionListener(new GroupObjects());
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("UnGroup");
		mntmNewMenuItem_5.addActionListener(new UngroupObjects());
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		frame.setVisible(true);
	}
	
	private void setButtons() {
		JButton select_button = new JButton(new ImageIcon(UMLwindow.class.getResource("/Image/mouseArrow-removebg-preview.png")));
		select_button.setBackground(Color.WHITE);
		functionButtons.add(select_button);
		select_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 1 ;
				resetButtons(0);
				canvas.setCanvasMouseBehavior(mode1_Selector);;
			}
		});		
		select_button.setBounds(0, 0, 100, 100);
		frame.getContentPane().add(select_button);
		
		
		JButton association_button = new JButton(new ImageIcon("D:\\Users\\USER\\Desktop\\eclipse-workspace\\UML_project\\src\\Image\\association-removebg-preview.png"));
		association_button.setBackground(Color.WHITE);
		functionButtons.add(association_button);
		association_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 2 ;
				resetButtons(1);
				canvas.setCanvasMouseBehavior(mode2_AddAssociationLine);
			}
		});
		association_button.setBounds(0, 100, 100, 100);
		frame.getContentPane().add(association_button);
		
		
		JButton generalization_button = new JButton(new ImageIcon(UMLwindow.class.getResource("/Image/GeneralizationLine2-removebg-preview.png")));
		generalization_button.setBackground(Color.WHITE);
		functionButtons.add(generalization_button);
		generalization_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 3 ;
				resetButtons(2);
				canvas.setCanvasMouseBehavior(mode3_GeneralizationLine);
			}
		});
		generalization_button.setBounds(0, 200, 100, 100);
		frame.getContentPane().add(generalization_button);
		
		
		JButton composition_button = new JButton(new ImageIcon(UMLwindow.class.getResource("/Image/CompositionLine2-removebg-preview.png")));
		composition_button.setBackground(Color.WHITE);
		functionButtons.add(composition_button);
		composition_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 4 ;
				resetButtons(3);
				canvas.setCanvasMouseBehavior(mode4_CompositionLine);
			}
		});
		composition_button.setBounds(0, 300, 100, 100);
		frame.getContentPane().add(composition_button);
		
		
		JButton class_button = new JButton(new ImageIcon(UMLwindow.class.getResource("/Image/AddClass-removebg-preview.png")));
		class_button.setBackground(Color.WHITE);
		functionButtons.add(class_button);
		class_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 5 ;
				resetButtons(4);
				canvas.setCanvasMouseBehavior(mode5_AddClass);
			}
		});
		class_button.setBounds(0, 400, 100, 100);
		frame.getContentPane().add(class_button);
		
		
		JButton use_class_button = new JButton(new ImageIcon(UMLwindow.class.getResource("/Image/AddUseClass-removebg-preview.png")));
		use_class_button.setBackground(Color.WHITE);
		functionButtons.add(use_class_button);
		use_class_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 6 ;
				resetButtons(5);
				canvas.setCanvasMouseBehavior(mode6_AddUseCase);
			}
		});		
		use_class_button.setBounds(0, 500, 100, 100);
		frame.getContentPane().add(use_class_button);
	}
	
	
	public static void resetButtons(int selectedIndex) {
		int i=0;
        for (JButton functionButton : functionButtons) {
            functionButton.setBackground(Color.WHITE);
            i++;
        }
        functionButtons.get(selectedIndex).setBackground(Color.DARK_GRAY);
    }
}
