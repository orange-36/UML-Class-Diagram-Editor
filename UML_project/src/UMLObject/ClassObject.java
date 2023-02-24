package UMLObject;

import java.awt.*;

import javax.swing.JLabel;

import main.*;

public class ClassObject extends BaseObject {
    private static final long serialVersionUID = 8488292894462555825L;

    public ClassObject() {
        super();

        this.width = 130;
        this.height = 150;

        this.label = new JLabel("New Class");
        this.label.setBackground(Color.WHITE);
        this.label.setOpaque(true);
        this.label.setBounds( this.offsetX + 5, this.offsetY + 5, 110, 40);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label);
        this.ports = new Port(	new Point(this.offsetX + 60, this.offsetY + 0),
				                new Point(this.offsetX + 120, this.offsetY + 70), 
				                new Point(this.offsetX + 60, this.offsetY + 140),
				                new Point(this.offsetX + 0, this.offsetY + 70)		);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.lightGray);
        graphics2D.fillRect(this.offsetX, this.offsetY, 120, 140);
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillRect(this.offsetX + 5, this.offsetY + 5, 110, 40);
        graphics2D.fillRect(this.offsetX + 5, this.offsetY + 50, 110, 40);
        graphics2D.fillRect(this.offsetX + 5, this.offsetY + 95, 110, 40);

        // Render the text.
        super.paintComponent(graphics);
    }
}
