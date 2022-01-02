package Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MenuCartPanel extends JPanel implements ActionListener  {
	private static final int MENU_NAME = 0;
	private static final int MENU_PRICE = 1;
	private static final int MENU_COUNT = 2;
	
	private Color isColor = Color.WHITE;	
	private DefaultTableModel model;
	private JTable CartTbl;
	private JPanel CartPnl, BtnPnl, pricePnl;
	private JButton plusBtn, minusBtn, delBtn, allDelBtn;
	private JLabel totalPriceLbl;
	private int totalPrice;
	
	public MenuCartPanel() {
		setLayout(new BorderLayout());
		this.setBackground(isColor);
		
		String header[] = {"선택한 메뉴", "주문금액", "수량"};
		String contents[][] = {};

		model = new DefaultTableModel(contents, header) {
			// JTable 의 내용을 수정못하도록 설정 // table 아이템 변경 막기
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		CartTbl = new JTable(model);
		JScrollPane sp = new JScrollPane(CartTbl, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(new Dimension(444,136));
		sp.setBackground(isColor);

		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tblAligenment = CartTbl.getColumnModel();
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i=0; i<tblAligenment.getColumnCount(); i++) 
			tblAligenment.getColumn(i).setCellRenderer(cellRenderer);
		for (int i=1; i<tblAligenment.getColumnCount(); i++) 
			tblAligenment.getColumn(i).setCellRenderer(cellRenderer);

		//CartTbl.setGridColor(Color.RED);			// 테이블 선 색깔 바꾸기
		//CartTbl.setShowVerticalLines(false);		// 세로 선 안보이게 하기
		//CartTbl.setShowHorizontalLines(false);	// 가로 선 안보이게 하기
		CartTbl.setShowGrid(false);					// 테이블 전체 선 안보이게 하기
		CartTbl.setRowHeight(22);					// Row 높이 설정
		CartTbl.setFillsViewportHeight(true);		// 테이블의 배경색 true해야 바꾸기가능
		CartTbl.setBackground(isColor);				// setBackground
		CartTbl.getTableHeader().setReorderingAllowed(false); 	// Header 이동 불가
		CartTbl.getTableHeader().setResizingAllowed(false); 	// Header 크기 조절 불가
		CartTbl.setFont(new Font("맑은 고딕", Font.BOLD, 16));	
		// 셀 간격 
		CartTbl.getColumnModel().getColumn(MENU_NAME).setPreferredWidth(230);
		CartTbl.getColumnModel().getColumn(MENU_PRICE).setPreferredWidth(140);
		CartTbl.getColumnModel().getColumn(MENU_COUNT).setPreferredWidth(74);
		
		CartPnl = new JPanel();
		CartPnl.setBackground(isColor);
		CartPnl.add(sp);

		BtnPnl = new JPanel(new GridLayout(0, 1, 0, 10));
		plusBtn = setBtnInit(plusBtn, "수량 추가");
		minusBtn = setBtnInit(minusBtn, "수량 제외");
		delBtn = setBtnInit(delBtn, "메뉴 삭제");
		allDelBtn = setBtnInit(allDelBtn, "전체 삭제");
		BtnPnl.setBackground(isColor);
		BtnPnl.add(plusBtn);
		BtnPnl.add(minusBtn);
		BtnPnl.add(delBtn);
		BtnPnl.add(allDelBtn);
		CartPnl.add(BtnPnl);
		this.add(CartPnl, "Center");
		
		pricePnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pricePnl.setBackground(isColor);	
		JLabel totalPriceStr = null;
		totalPriceStr = setPriceLbl(totalPriceStr, "총 주문금액  ", Color.BLACK);
		totalPriceLbl = setPriceLbl(totalPriceLbl, "0", Color.RED);
		JLabel won = null;
		won = setPriceLbl(won, "원", Color.RED);
		
		this.add(pricePnl, "South");
	}
		
	public JLabel setPriceLbl(JLabel lbl, String str, Color color) {
		lbl = new JLabel(str);
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		lbl.setForeground(color);
		pricePnl.add(lbl);
		return lbl;
	}

	public JButton setBtnInit(JButton btn, String str) {
		btn = new JButton(str);
		btn.setPreferredSize(new Dimension(100, 26));
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 15));		// 수정 ()
		btn.addActionListener(this);
		btn.setFocusPainted(false);
		return btn;
	}

	public void setInfo(String name, int price, int count) {
		boolean checkName = false;
		String[] info = new String[3];
		info[MENU_NAME] = name;
		info[MENU_PRICE] = String.valueOf(price);
		info[MENU_COUNT] = String.valueOf(count);	
		for(int i=0; i<CartTbl.getRowCount(); i++) {
			// 메뉴 이름 똑같으면
			if(info[MENU_NAME].equals(String.valueOf(model.getValueAt(i, MENU_NAME)))) {
				// 메뉴 수량 숫자 변경
				String emptyCount = String.valueOf(model.getValueAt(i, MENU_COUNT));
				int menuCount = Integer.parseInt(emptyCount);
				model.setValueAt(String.valueOf(menuCount+1), i, MENU_COUNT);
				totalPrice += price;	// 메뉴 추가시마다 총 금액 증가
				totalPriceLbl.setText(String.valueOf(totalPrice));
				checkName = true;
				break;
			}
		}
		if(!checkName) {
			totalPrice += price;	// 메뉴 추가시마다 총 금액 증가
			totalPriceLbl.setText(String.valueOf(totalPrice));
			model.addRow(info);		
		}
	}
	
	public int changeTotalPrice(){    // 메뉴 선택 변화 시 총 주문금액 변화
		for(int i=0; i<model.getRowCount(); i++){
			String price = String.valueOf(model.getValueAt(i, MENU_PRICE));
			totalPrice += Integer.parseInt(price);
		}
		return totalPrice;
	}
	
	public DefaultTableModel getModel() {
		return this.model;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	
	public boolean getCartCount() {
		if(CartTbl.getRowCount() > 0)
			return true;
		else
			return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == allDelBtn) {	// 테이블 전체 삭제
			model.setNumRows(0);	// 전체 초기화
			totalPrice = 0;
			totalPriceLbl.setText("0");
			return;
		}	
		int row = CartTbl.getSelectedRow();
		if(row < 0) return;
		if(e.getSource() == plusBtn) {	// 메뉴 추가 (수량 추가) 
			variation(row, 1);
		} else if(e.getSource() == minusBtn) {	// 메뉴 제외 (수량 감소) 
			int count = variation(row, -1);
			if(count == 0)
				model.removeRow(row);
		} else if(e.getSource() == delBtn) {	// 선택한 테이블 메뉴 Row 삭제
			// 선택한 Row 삭제되며 총 금액에서 Row의 합계금액 빼주기
			String emptyPrice = String.valueOf(model.getValueAt(row, MENU_PRICE));
			String emptyCount = String.valueOf(model.getValueAt(row, MENU_COUNT));
			int menuCount = Integer.parseInt(emptyCount);
			int minusPrice = menuCount * Integer.parseInt(emptyPrice);
			totalPriceLbl.setText(String.valueOf(totalPrice-=minusPrice));		
			// 선택한 Row 삭제
			model.removeRow(row);
		}	
	}
	
	public int variation(int row, int changeCount) {
		// 메뉴 수량 증감 되면 총 금액도 증감
		String emptyPrice = String.valueOf(model.getValueAt(row, MENU_PRICE));
		int menuPrice = Integer.parseInt(emptyPrice);
		totalPriceLbl.setText(String.valueOf(totalPrice+=(menuPrice*changeCount)));
		// 메뉴 수량 숫자 변경
		String emptyCount = String.valueOf(model.getValueAt(row, MENU_COUNT));
		int menuCount = Integer.parseInt(emptyCount);
		model.setValueAt(String.valueOf(menuCount+changeCount), row, MENU_COUNT);
		return menuCount-1;
	}
}
