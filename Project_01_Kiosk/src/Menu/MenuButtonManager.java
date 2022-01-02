package Menu;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

class MenuButtonManager extends JButton {
	private ImageIcon icon;
	private int menuNumber;
	private String menuName;
	private int menuPrice;
	
	public MenuButtonManager() {
		this.setBackground(Color.WHITE);
	}
	public MenuButtonManager(String str) {
		this.setText(str);
	}
	
	public void setMenuNumber(int menuNumber)	{	this.menuNumber = menuNumber;}
	public void setMenuName(String menuName)	{	this.menuName = menuName;	}
	public void setMenuPrice(int menuPrice)	{	this.menuPrice = menuPrice;	}
	
	public int getMenuNumber()		{	return menuNumber;	}
	public String getMenuName()		{	return menuName;	}
	public int getMenuPrice()	{	return menuPrice;	}
		
	public void setInit() {
		menuNumber = 0;
		menuName = "";
		menuPrice = 0;		
	}
	
	public void setCleanButton() {
		this.setBorderPainted(false);		// 버튼의 경계 감춤
		this.setFocusPainted(false);		// 버튼 선택했을때 생기는 테두리 감춤
		this.setContentAreaFilled(false);	// 버튼의 기본배경 채울것인가
	}
	
	public void drawImage() {
		icon = new ImageIcon("ImageFile/"+this.getMenuName()+".png");
		this.setIcon(icon);
	}
}
