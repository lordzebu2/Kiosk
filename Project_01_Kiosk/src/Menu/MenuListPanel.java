package Menu;

import Main.*;
import etc.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

class MenuListPanel extends JPanel implements MouseListener {
	private static final int LBL_WIDTH = 120;
	private static final int LBL_HEIGHT = 140;
	
	private static final String PREMIUM_FILE = "DataFile/premium.txt";
	private static final String WHOPPER_FILE = "DataFile/whopper.txt";
	private static final String JUNIOR_FILE = "DataFile/junior.txt";
	private static final String CHICKEN_FILE = "DataFile/chicken.txt";
	private static final String SIDE_FILE = "DataFile/side.txt";
	private static final String DRINK_FILE = "DataFile/drink.txt";
	
	private static final int MENU_INFO_NAME = 0;
	private static final int MENU_INFO_PRICE = 1;
	
	private static final int MENU_TITLE_COUNT = 6;
	private static final int PREMIUM_COUNT = 0;
	private static final int WHOPPER_COUNT = 1;
	private static final int JUNIOR_COUNT = 2;
	private static final int CHICKEN_COUNT = 3;
	private static final int SIDE_COUNT = 4;
	private static final int DRINK_COUNT = 5;
	
	private MenuLabelManager[] menuPremiumLabel;
	private MenuLabelManager[] menuWhopperLabel;
	private MenuLabelManager[] menuJuniorLabel;
	private MenuLabelManager[] menuChickenLabel;
	private MenuLabelManager[] menuSideLabel;
	private MenuLabelManager[] menuDrinkLabel;
	
	private MenuCartPanel menuCartPanel;
	
	private MainFrame mainFrame;
	private MenuFileManager fileManager;
	private int[] menuArrayCount;
	private boolean selectPutMenu;

	public MenuListPanel(MainFrame mainFrame, MenuCartPanel menuCartPanel) {		
		this.setLayout(new GridLayout(0, 4, 14, 10));
		this.setBackground(Color.WHITE);
		
		this.mainFrame = mainFrame;
		this.menuCartPanel = menuCartPanel;
		
		fileManager = new MenuFileManager();
		
		menuArrayCount = new int[MENU_TITLE_COUNT];
		setArrayCount();

		// 생성 - 초기화
		menuPremiumLabel = setLabelInfoInit(menuPremiumLabel, PREMIUM_FILE, PREMIUM_COUNT);
		menuWhopperLabel = setLabelInfoInit(menuWhopperLabel, WHOPPER_FILE, WHOPPER_COUNT);
		menuJuniorLabel = setLabelInfoInit(menuJuniorLabel, JUNIOR_FILE, JUNIOR_COUNT);
		menuChickenLabel = setLabelInfoInit(menuChickenLabel, CHICKEN_FILE, CHICKEN_COUNT);
		menuSideLabel = setLabelInfoInit(menuSideLabel, SIDE_FILE, SIDE_COUNT);
		menuDrinkLabel = setLabelInfoInit(menuDrinkLabel, DRINK_FILE, DRINK_COUNT);
		
		drawLabelImage(menuPremiumLabel, PREMIUM_COUNT);	// 첫 시작화면 프리미엄
	}	
	
	public void setArrayCount() {
		menuArrayCount[PREMIUM_COUNT] = getMenuCount(PREMIUM_FILE);
		menuArrayCount[WHOPPER_COUNT] = getMenuCount(WHOPPER_FILE);
		menuArrayCount[JUNIOR_COUNT] = getMenuCount(JUNIOR_FILE);
		menuArrayCount[CHICKEN_COUNT] = getMenuCount(CHICKEN_FILE);
		menuArrayCount[SIDE_COUNT] = getMenuCount(SIDE_FILE);
		menuArrayCount[DRINK_COUNT] = getMenuCount(DRINK_FILE);
	}
		
	public MenuLabelManager[] setLabelInfoInit(MenuLabelManager[] lbl, String fileName, int arrayCount) {
		String info = null;
		lbl = new MenuLabelManager[menuArrayCount[arrayCount]];
		for(int i=0; i<menuArrayCount[arrayCount]; i++) {
			lbl[i] = new MenuLabelManager();
			lbl[i].setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
			lbl[i].addMouseListener(this);
			info = fileManager.getSelectedFileInfo(fileName, i);
			lbl[i].setMenuName(info.split("/")[MENU_INFO_NAME]);
			lbl[i].setMenuPrice(Integer.parseInt(info.split("/")[MENU_INFO_PRICE]));
			lbl[i].setMenuIcon();
			
		//	lbl[i].setBorder(new LineBorder(Color.RED));	// test
		}
		return lbl;
	}	
	
	// 파일의 가장 마지막 품목 번호를 받아온다. 품목 갯수 확인
	public int getMenuCount(String fileName) {
		return fileManager.getFileCountInfo(fileName);
	}	
	
	public void drawLabelImage(MenuLabelManager[] lbl, int arrayCount) {
		this.removeAll();
		for(int i=0; i<menuArrayCount[arrayCount]; i++) {
			this.add(lbl[i]);
		}
		this.revalidate();
		this.repaint();
	}
	
	public void changeMenu(String menuTitle) {
		if (menuTitle.equals("프리미엄")) 
			drawLabelImage(menuPremiumLabel, PREMIUM_COUNT);
		else if(menuTitle.equals("와퍼")) 
			drawLabelImage(menuWhopperLabel, WHOPPER_COUNT);
		else if (menuTitle.equals("주니어&버거")) 
			drawLabelImage(menuJuniorLabel, JUNIOR_COUNT);
		else if (menuTitle.equals("치킨&치킨버거")) 
			drawLabelImage(menuChickenLabel, CHICKEN_COUNT);
		else if (menuTitle.equals("사이드")) 
			drawLabelImage(menuSideLabel, SIDE_COUNT);
		else if (menuTitle.equals("음료&디저트")) 
			drawLabelImage(menuDrinkLabel, DRINK_COUNT);
	}
	
	public void selectPut(boolean bool) {
		selectPutMenu = bool;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		MenuLabelManager lbl = (MenuLabelManager)e.getSource();
		TranslucenceDialog transDialog = new TranslucenceDialog(mainFrame);
		new MenuAddDialog(this.mainFrame, transDialog, lbl, this);

		// 테스트용 코드
		//this.menuCartPanel.setInfo(lbl.getMenuName(), lbl.getMenuPrice(), lbl.getMenuCount());
	
		if(selectPutMenu)	// 담기를 누르면
			this.menuCartPanel.setInfo(lbl.getMenuName(), lbl.getMenuPrice(), lbl.getMenuCount());
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}
