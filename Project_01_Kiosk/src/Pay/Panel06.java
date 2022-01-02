package Pay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import etc.Payment;

class Panel06 extends JPanel implements ActionListener, KeyListener { // 멤버십 적립
	// - 회원
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5;
	JButton btn1, btn2, btn3; // 이전, 다음
	JPanel pnl1, pnl2, pnl4, pnl6, pnl7;
	Payment pm;
	JTextField tf;
	int a, b; // 적립 내역의 합, 사용 내역의 합
	int point = 0;
	JTable tbl1, tbl2;
	DefaultTableModel model, model2;
	ImageIcon btn1Icon, btn2Icon;

	PayMainPanel payMainPanel;
	
	int x = 550;
	int y = 80;

	public Panel06(PayMainPanel payMainPanel) {
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(new Color(240, 235, 229));
		this.payMainPanel = payMainPanel;

		pnl1 = new JPanel(null);
		pnl1.setBackground(new Color(240, 235, 229));
		pnl1.setSize(600, 400);
		lbl3 = new JLabel(""); // 이번에 적립될 내용
		lbl3.setBounds(185, 60, 300, 100);
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lbl5 = new JLabel(""); // 사용 가능한 적립금의 총합
		lbl5.setBounds(200, 90, 300, 100);
		lbl5.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lbl4 = new JLabel("사용할 적립금을 입력해주세요");
		lbl4.setBounds(190, 150, 200, 100);
		lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		tf = new JTextField(10);
		tf.setBounds(155, 220, 130, 30);
		tf.addKeyListener(this);
		tf.addActionListener(this);
		btn3 = new JButton("전액사용");
		btn3.setBounds(305, 220, 90, 30);
		btn3.addActionListener(this);

		pnl1.add(lbl3);
		pnl1.add(lbl5);
		pnl1.add(lbl4);
		pnl1.add(tf);
		pnl1.add(btn3);

		pnl6 = new JPanel(null);
		pnl6.setBackground(new Color(240, 235, 229));
		pnl6.setSize(600, 300);
		String header[] = { "적립 내역" };
		String contents[][] = {};
		model = new DefaultTableModel(contents, header);
		tbl1 = new JTable(model);
		JScrollPane sp = new JScrollPane(tbl1);
		sp.getViewport().setBackground(Color.WHITE);
		sp.setBounds(35, 50, 250, 200);

		String header1[] = { "사용 내역" };
		String contents1[][] = {};
		model2 = new DefaultTableModel(contents1, header1);
		tbl2 = new JTable(model2);
		JScrollPane sp1 = new JScrollPane(tbl2);
		sp1.getViewport().setBackground(Color.WHITE);
		sp1.setBounds(300, 50, 250, 200);
		pnl6.add(sp);
		pnl6.add(sp1);

		pnl4 = new JPanel(null);
		pnl4.setBackground(new Color(240, 235, 229));
		pnl4.setSize(600, 80);
		// =============================================
		btn1 = new JButton("취소");
		btn1.setBounds(55, 80, 210, 80);
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(x, y));
		btn1Icon = new ImageIcon("ImageFile/PAY/취소.png");
		Image img = btn1Icon.getImage();
		Image ch = img.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		btn1.setIcon(chgIcon);

		btn2 = new JButton("확인");
		btn2.setBounds(320, 80, 210, 80);
		btn2.addActionListener(this);
		btn2.setPreferredSize(new Dimension(x, y));
		btn2Icon = new ImageIcon("ImageFile/PAY/확인.png");
		Image img2 = btn2Icon.getImage();
		Image ch2 = img2.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon2 = new ImageIcon(ch2);
		btn2.setIcon(chgIcon2);
		// =============================================
		pnl4.add(btn1);
		pnl4.add(btn2);

		this.add(pnl1);
		this.add(pnl6);
		this.add(pnl4);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) { // 취소
			model.setNumRows(0);
			model2.setNumRows(0);
			this.payMainPanel.changePanel(2);
		} else if (e.getSource() == btn2) { // 확인
			this.payMainPanel.changePanel(2);
			if (!tf.getText().equals("")) {
				this.payMainPanel.point = Integer.parseInt(tf.getText());
				this.payMainPanel.updateTotal();
				String[] str = { "0", "0" };
				str[0] = tf.getText();
				model2.addRow(str);
			}
			File f = new File("FileDataEx3.txt");
			PrintWriter pw = null;
			try {
				FileWriter fw = new FileWriter(f);
				pw = new PrintWriter(fw);
				for (int i = 0; i < tbl1.getRowCount(); i++) {
					pw.print(tbl1.getValueAt(i, 0) + "/");
					pw.println("t");
				}
				for (int i = 0; i < tbl2.getRowCount(); i++) {
					pw.print(tbl2.getValueAt(i, 0) + "/");
					pw.println("f");
				}
				model.setNumRows(0);
				model2.setNumRows(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				model.setNumRows(0);
				model2.setNumRows(0);
				if (pw != null) {
					pw.close();
				}
			}
		} else if (e.getSource() == btn3) { // 전액사용
			tf.setText(Integer.toString(a - b));
		}
	}

	public void Point() {
		point = (int) (this.payMainPanel.totalPrice * 0.01);
		lbl3.setText(point + "점이 적립될 예정입니다");
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int p = Integer.parseInt(tf.getText());
		if (p > a - b) {
			tf.setText(Integer.toString(a - b));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (!Character.isDigit(c)) {
			e.consume();
			return;
		}
	}
}