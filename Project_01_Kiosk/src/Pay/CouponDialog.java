package Pay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Main.MainFrame;

public class CouponDialog extends JDialog implements ActionListener {
	Color isColor = Color.WHITE;
	
	private MainFrame mainFrame;
	ServiceOptionPanel serviceOptionPanel;
	DefaultTableModel model;
	JTable tbl;
	JPanel btnPnl, pricePnl;
	JLabel inputCodeLbl, totalPriceLbl;
	JButton cancelBtn, okBtn, inputCodeBtn;
	JPanel pnlS, tablePnl, inputPnl;
	JTextField tf;
	
	int isTotalPrice;
	
	public CouponDialog(MainFrame mainFrame, ServiceOptionPanel serviceOptionPanel) {
		super(mainFrame, "Coupon", true);
		
		this.mainFrame = mainFrame;
		this.serviceOptionPanel = serviceOptionPanel;
		
		this.setLayout(new BorderLayout());
		this.setSize(550, 350);
		
		inputPnl = new JPanel();
		inputPnl.setBackground(isColor);
		inputCodeLbl = new JLabel("쿠폰 번호 입력");
		inputCodeLbl.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		tf = new JTextField(15);
		inputCodeBtn = new JButton("쿠폰 등록");
		inputCodeBtn.setFocusPainted(false);
		inputCodeBtn.addActionListener(this);
		inputPnl.add(inputCodeLbl);
		inputPnl.add(tf);
		inputPnl.add(inputCodeBtn);
		this.add(inputPnl, "North");
		
		String header[] = { "쿠폰 이름", "할인 가격" };
		String contents[][] = {};

		tablePnl = new JPanel();
		tablePnl.setBackground(isColor);
		model = new DefaultTableModel(contents,  header) {
			@Override
			public boolean isCellEditable(int row, int  column) {
				return false;
			}
		};
		tbl = new JTable(model);
		JScrollPane sp = new JScrollPane(tbl);
		sp.getViewport().setBackground(Color.WHITE);
		sp.setPreferredSize(new Dimension(444,150));
		
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tblAligenment = tbl.getColumnModel();
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i=0; i<tblAligenment.getColumnCount(); i++) 
			tblAligenment.getColumn(i).setCellRenderer(cellRenderer);
		for (int i=1; i<tblAligenment.getColumnCount(); i++) 
			tblAligenment.getColumn(i).setCellRenderer(cellRenderer);
		
		tbl.setFillsViewportHeight(true);	// 테이블의 배경색 true해야 바꾸기가능
		tbl.setBackground(isColor);		// setBackground
		tbl.setEnabled(false);			// cell 클릭 안되게 하기
		tablePnl.add(sp);
		this.add(tablePnl, "Center");
		
		pnlS = new JPanel(new BorderLayout());
		pnlS.setBackground(isColor);
		
		pricePnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pricePnl.setBackground(isColor);	
		JLabel totalPriceStr = null;
		totalPriceStr = setPriceLbl(totalPriceStr, "총 결제금액  ", Color.BLACK);
		totalPriceLbl = setPriceLbl(totalPriceLbl, String.valueOf(this.serviceOptionPanel.totalPrice), Color.RED);
		isTotalPrice = this.serviceOptionPanel.totalPrice;
		JLabel won = null;
		won = setPriceLbl(won, "원", Color.RED);
		pnlS.add(pricePnl, "North");

		btnPnl = new JPanel();
		btnPnl.setBackground(isColor);
		cancelBtn = setBtnInit(cancelBtn, "취소");
		okBtn = setBtnInit(okBtn, "사용");
		pnlS.add(btnPnl, "South");
		this.add(pnlS, "South");
		
		//this.setUndecorated(true); 	// 타이틀바 삭제
		this.setBackground(isColor);
		this.setLocationRelativeTo(this.mainFrame);	// 중앙 배치. size를 설정한후에 설정해야한다.
		this.setVisible(true);	
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
		btn.setPreferredSize(new Dimension(200,50));
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 20));		// 수정 ()
		btn.addActionListener(this);
		btn.setFocusPainted(false);
		btnPnl.add(btn);
		return btn;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okBtn) {
			this.serviceOptionPanel.setTotalPrice(isTotalPrice);
			this.dispose();
		} else if(e.getSource() == cancelBtn) {
			this.dispose();
		} else if(e.getSource() == inputCodeBtn) {
			File f = new File("DataFile/coupon.txt");
			FileReader fr = null;
			BufferedReader br = null;
			JLabel label = new JLabel();
			label.setFont(new Font("맑은 고딕", Font.BOLD, 18));

			String str[] = new String[2];
			if(tf.getText().equals(("")))  {
				label.setText("쿠폰 번호를 입력해주세요.");
				JOptionPane.showMessageDialog(this, label);
			} else {
				try {
					fr = new FileReader(f);
					br = new BufferedReader(fr);

					String l = null;
					while ((l = br.readLine()) != null) {
						if (l.split("/")[0].equals(tf.getText())) {
							str[0] = l.split("/")[1];	// 쿠폰 이름
							str[1] = l.split("/")[2];	// 할인가격
							model.addRow(str);

							isTotalPrice -= Integer.parseInt(str[1]);
							if(isTotalPrice < 0)
								isTotalPrice = 0;
							totalPriceLbl.setText(String.valueOf(isTotalPrice));	
							tf.setText("");
							return;
						} 
					}
					label.setText("쿠폰 번호가 맞지 않습니다.");
					JOptionPane.showMessageDialog(this, label);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (br != null) {
							br.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}	
			}
			tf.setText("");
		}	
	}
}
