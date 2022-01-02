package Menu;

import Main.*;
import etc.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MenuOrderDialog extends JDialog implements ActionListener {
	private static final int MENU_NAME = 0;
	private static final int MENU_PRICE = 1;
	private static final int MENU_COUNT = 2;

	private MainFrame mainFrame;
	private MenuMainPanel menuMainPanel;
	private MenuCartPanel menuCartPanel;
	private DefaultTableModel cartModel;
	private DefaultTableModel thisModel;
	private JPanel pnlS, totalPricePnl, btnPnl;
	private Color isColor = Color.WHITE;	
	private JButton cancelBtn, okBtn;
	private JTable orderCheckTbl;

	public MenuOrderDialog(MainFrame mainFrame, MenuMainPanel menuMainPanel, MenuCartPanel menuCartPanel) {
		super(mainFrame, "", true);	// true => 다이얼로그창이 켜지면 프레임 클릭 안됨

		this.mainFrame = mainFrame;
		this.menuMainPanel = menuMainPanel;
		this.menuCartPanel = menuCartPanel;
		this.setLayout(new BorderLayout());
		this.setSize(550, 690);

		cartModel = this.menuCartPanel.getModel();
		
		JPanel menuCheckPnl = new JPanel();
		menuCheckPnl.setBackground(isColor);
		JLabel menuCheck = new JLabel("메뉴 주문확인");
		menuCheck.setBackground(isColor);
		menuCheck.setFont(new Font("맑은 고딕", Font.BOLD, 40));	
		menuCheckPnl.add(menuCheck);
		this.add(menuCheckPnl, "North");

		String[] header = new String[] { "메뉴", "수량", "결제금액"};
		Object[][] contents = new Object[][] { };

		// table 아이템 변경 막기
		thisModel = new DefaultTableModel(contents,  header) {
			@Override
			public boolean isCellEditable(int row, int  column) {
				return false;
			}
		};

		// MenuOrderDialog 의 테이블 Init
		for(int i =0; i< cartModel.getRowCount();i++ ){
			this.setModelInfo(String.valueOf(cartModel.getValueAt(i, MENU_NAME)),
					String.valueOf(cartModel.getValueAt(i, MENU_PRICE)),
					String.valueOf(cartModel.getValueAt(i, MENU_COUNT)));
		}

		// table에 이미지를 출력하기 위한 오버라이드
		orderCheckTbl = new JTable(thisModel) {
			@Override
			public Class<?> getColumnClass(int column)  {
				// row - JTable에 입력된 2차원 배열의  행에 속한다면 모든 컬럼을 입력된 형으로  반환한다.
				// 다시말해, 어떤 행이든 간에 입력된  column의 class를 반환하도록 한 것
				return getValueAt(0,  column).getClass();
			}
		};

		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tblAligenment = orderCheckTbl.getColumnModel();
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i=1; i<tblAligenment.getColumnCount(); i++) 
			tblAligenment.getColumn(i).setCellRenderer(cellRenderer);
		
		orderCheckTbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));	

		orderCheckTbl.setShowVerticalLines(false);
		//orderCheckTbl.setShowGrid(false);	
		orderCheckTbl.setRowHeight(140); // 행 높이 조절
		orderCheckTbl.getColumnModel().getColumn(0).setPreferredWidth(250); // 컬럼(열)너비 조절
		orderCheckTbl.getColumnModel().getColumn(1).setPreferredWidth(100); // 컬럼(열)너비 조절
		orderCheckTbl.getColumnModel().getColumn(2).setPreferredWidth(200); // 컬럼(열)너비 조절
		orderCheckTbl.setFillsViewportHeight(true);	// 테이블의 배경색 true해야 바꾸기가능
		orderCheckTbl.setBackground(isColor);		// setBackground
		orderCheckTbl.setEnabled(false);			// cell 클릭 안되게 하기

		JScrollPane sp = new JScrollPane(orderCheckTbl, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBorder(BorderFactory.createEmptyBorder());	// 스크롤 보더 경계선 없애기
		sp.setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.GRAY)); // 위쪽에 1pix 선 그리기
		this.add(sp, "Center");

		pnlS = new JPanel(new BorderLayout());
		pnlS.setBackground(isColor);

		btnPnl = new JPanel();
		btnPnl.setBackground(isColor);
		cancelBtn = setBtnInit(cancelBtn, "주문취소");
		okBtn = setBtnInit(okBtn, "결제하기");
		pnlS.add(btnPnl, "South");

		totalPricePnl = new JPanel();
		totalPricePnl.setBackground(isColor);
		JLabel totalPriceStr = null;
		totalPriceStr = setPriceLbl(totalPriceStr, "총 주문금액  ", Color.BLACK);
		JLabel totalPriceLbl = null;
		totalPriceLbl = setPriceLbl(totalPriceLbl, String.valueOf(this.menuCartPanel.getTotalPrice()), Color.RED);
		JLabel won = null;
		won = setPriceLbl(won, "원", Color.RED);
		pnlS.add(totalPricePnl, "North");

		this.add(pnlS, "South");

		this.setUndecorated(true); 	// 타이틀바 삭제
		this.setBackground(isColor);
		this.setLocationRelativeTo(this.mainFrame);	// 중앙 배치. size를 설정한후에 설정해야한다.
		this.setVisible(true);	
	}

	public void setModelInfo(String menuName, String menuPrice, String menuCount) {			
		Object[] info = new Object[3];	// image, count
		info[0] = this.setImageData(menuName);
		info[1] = menuCount;
		info[2] = String.valueOf((Integer.parseInt(menuPrice)*Integer.parseInt(menuCount))) + " 원";

		thisModel.addRow(info);
	}

	public JLabel setPriceLbl(JLabel lbl, String str, Color color) {
		lbl = new JLabel(str);
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		lbl.setForeground(color);
		totalPricePnl.add(lbl);
		return lbl;
	}

	public JButton setBtnInit(JButton btn, String str) {
		btn = new JButton(str);
		btn.setPreferredSize(new Dimension(200,50));
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 20));		// 수정 ()
		btn.addActionListener(this);
		btn.setFocusPainted(false);
		btnPnl.add(btn);
		return btn;
	}

	public ImageIcon setImageData(String menuName) {
		ImageIcon icon = new ImageIcon("ImageFile/MENU/"+menuName+".png");
		return icon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		if(e.getSource() == okBtn) {
			this.mainFrame.setTotalPrice(this.menuCartPanel.getTotalPrice());
			this.menuMainPanel.getPayPanel();
		}
	}
}
