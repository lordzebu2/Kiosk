package etc;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class RoundedButton extends JButton {
	public RoundedButton() { super(); decorate(); } 
	public RoundedButton(String text) { super(text); decorate(); } 
	public RoundedButton(Action action) { super(action); decorate(); } 
	public RoundedButton(Icon icon) { super(icon); decorate(); } 
	public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
	protected void decorate() { 
		setBorderPainted(false); 
		setOpaque(false); }
	@Override 
	protected void paintComponent(Graphics g) {
		Color c = new Color(250,100,100); //배경색 결정
		Color o = new Color(0,0,0); //글자색 결정
		int width = getWidth(); 
		int height = getHeight(); 
		Graphics2D graphics = (Graphics2D) g; 
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		if (getModel().isArmed()) { graphics.setColor(c.darker());} 
		else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
		else { graphics.setColor(c); } 

		graphics.fillRoundRect(0, 0, width, height, 30, 30); 
		FontMetrics fontMetrics = graphics.getFontMetrics(); 
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
		float textX = (width - stringBounds.width) / 2.5f; 	// 수정 int, /2
		float textY = (height - stringBounds.height) / 1.5f + fontMetrics.getAscent(); // 수정 int, /2
		graphics.setColor(o); 
		graphics.setFont(new Font("맑은 고딕", Font.BOLD, 20));		// 수정 getFont()
		graphics.drawString(getText(), textX, textY); 
		graphics.dispose(); 
		super.paintComponent(g); 
	}
}


