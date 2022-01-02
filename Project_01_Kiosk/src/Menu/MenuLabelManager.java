package Menu;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MenuLabelManager extends JLabel {
	private ImageIcon icon;
	private String menuName;
	private int menuPrice;
	private int menuCount = 1;
	
	public MenuLabelManager() {
		this.setBackground(Color.WHITE);
	}
	public MenuLabelManager(String str) {
		this.setText(str);
	}

	public void setMenuName(String menuName)	{	this.menuName = menuName;	}
	public void setMenuPrice(int menuPrice)		{	this.menuPrice = menuPrice;	}

	public String getMenuName()	{	return this.menuName;	}
	public int getMenuPrice()	{	return this.menuPrice;	}
	public int getMenuCount()	{	return this.menuCount;	}
		
	public void setInit() {
		this.menuName = "";
		this.menuPrice = 0;		
	}
	
	public void setMenuIcon() {
		icon = new ImageIcon("ImageFile/MENU/"+this.getMenuName()+".png");
		this.setIcon(icon);
	}
	
	public ImageIcon getMenuIcon() {
		return this.icon;
	}
}
