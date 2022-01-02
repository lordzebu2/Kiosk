package etc;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

class Panel01 extends JPanel implements ActionListener {
	JLabel lbl1, lbl2, lbl3, lbl4;
	JButton btn1, btn2; // 이전, 다음
	JPanel pnl1, pnl2, pnl3, pnl4, pnl5;
	private String[][] contents;
	DefaultTableModel model;
	JTable tbl;
	int sum = 0; // 선택한 품목의 합
	Payment pm;
	ImageIcon btn1Icon, btn2Icon;

	int x = 550;
	int y = 80;

	public Panel01(Payment pm) { // 결제창 1번
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(Color.WHITE);
		this.pm = pm;

		pnl1 = new JPanel(null);
		pnl1.setSize(600, 200);
		lbl2 = new JLabel("결제 방법 선택");
		lbl2.setBounds(242, 94, 100, 100);
		pnl1.add(lbl2);

		pnl5 = new JPanel(null);
		pnl5.setBackground(Color.WHITE);
		String header[] = { "품목", "수량", "가격" };
		String contents[][] = {};
		model = new DefaultTableModel(contents, header);
		tbl = new JTable(model);
		JScrollPane sp = new JScrollPane(tbl);
		sp.setBounds(68, 30, 450, 150);
		sp.getViewport().setBackground(Color.WHITE);
		pnl5.add(sp);

		pnl3 = new JPanel(null);
		pnl3.setBackground(Color.WHITE);
		pnl3.setSize(600, 100);
		lbl3 = new JLabel("최종 결제 금액      ");
		lbl3.setBounds(220, 40, 130, 50);
		lbl4 = new JLabel(Integer.toString(pm.total));
		lbl4.setBounds(320, 40, 130, 50);
		pnl3.add(lbl3);
		pnl3.add(lbl4);

		pnl4 = new JPanel(null);
		pnl4.setBackground(Color.WHITE);
		pnl4.setSize(600, 100);

		// =============================================
		btn1 = new JButton("이전");
		btn1.setBounds(55, 0, 210, 80);
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(x, y));
		btn1Icon = new ImageIcon("ImageFile/PAY/이전.png");
		Image img = btn1Icon.getImage();
		Image ch = img.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		btn1.setIcon(chgIcon);

		btn2 = new JButton("다음");
		btn2.setBounds(320, 0, 210, 80);
		btn2.addActionListener(this);
		btn2.setPreferredSize(new Dimension(x, y));
		btn2Icon = new ImageIcon("ImageFile/PAY/다음.png");
		Image img2 = btn2Icon.getImage();
		Image ch2 = img2.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon2 = new ImageIcon(ch2);
		btn2.setIcon(chgIcon2);

		// =============================================
		pnl4.add(btn1);
		pnl4.add(btn2);

		this.add(pnl1);
		this.add(pnl5);
		this.add(pnl3);
		this.add(pnl4);

		this.setVisible(true);
		this.Order();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) { // 이전

		} else if (e.getSource() == btn2) { // 다음
			pm.changePanel(2);
			pm.updateTotal();
		}
	}

	public void Order() {
		File f = new File("FileDataEx1.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String l = null;
			while ((l = br.readLine()) != null) { // 선택한 품목에 대한 총합
				String[] str = l.split("/");
				sum = sum + Integer.parseInt(str[1]);
				model.addRow(str);
			}
			pm.total = sum;
			lbl4.setText(Integer.toString(sum));
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
}

class Panel02 extends JPanel implements ActionListener { // 결제창 2번
	JLabel lbl1, lbl2, lbl3, lbl4;
	JButton btn1, btn2, btn3, btn4; // 이전, 다음
	JPanel pnl1, pnl2, pnl3, pnl4, pnl5;
	Payment pm;
	int t; // 로그인 시 t=1 //비회원일 시 t=2
	Panel08 pnl08;
	ImageIcon btn1Icon, btn2Icon, btn3Icon, btn4Icon;

	int x = 550;
	int y = 80;

	public Panel02(Payment pm, Panel08 pnl08) {
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(Color.WHITE);
		this.pm = pm;
		this.pnl08 = pnl08;

		pnl1 = new JPanel(null);
		pnl1.setBackground(new Color(240, 235, 229));
		pnl1.setSize(600, 200);
		lbl2 = new JLabel("멤버십 및 쿠폰");
		lbl2.setBounds(225, 94, 150, 100);
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		pnl1.add(lbl2);

		pnl4 = new JPanel(null);
		pnl4.setBackground(new Color(240, 235, 229));
		pnl4.setSize(600, 400);
		btn3 = new JButton("멤버십 적립");
		btn3Icon = new ImageIcon("ImageFile/PAY/멤버십.png");
		Image img3 = btn3Icon.getImage();
		btn3.setIcon(btn3Icon);
		btn3.setBounds(128, 12, 145, 200);
		btn3.setBackground(Color.WHITE);
		btn3.addActionListener(this);
		btn4 = new JButton("쿠폰 사용");
		btn4.setFocusPainted(false);
		btn4Icon = new ImageIcon("ImageFile/PAY/쿠폰.png");
		Image img4 = btn4Icon.getImage();
		btn4.setIcon(btn4Icon);
		btn4.setBounds(300, 12, 150, 200);
		btn4.setBackground(Color.WHITE);
		btn4.addActionListener(this);
		pnl4.add(btn3);
		pnl4.add(btn4);

		pnl3 = new JPanel(null);
		pnl3.setBackground(new Color(240, 235, 229));
		pnl3.setSize(600, 100);
		lbl3 = new JLabel("최종 결제 금액      ");
		lbl3.setBounds(150, 60, 180, 50);
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbl4 = new JLabel(Integer.toString(pm.total));
		lbl4.setBounds(300, 55, 130, 50);
		lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		pnl3.add(lbl3);
		pnl3.add(lbl4);

		pnl5 = new JPanel(null);
		pnl5.setBackground(new Color(240, 235, 229));
		pnl5.setSize(600, 100);

		// =============================================
		btn1 = new JButton("이전");
		btn1.setBounds(55, 0, 210, 80);
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(x, y));
		btn1Icon = new ImageIcon("ImageFile/PAY/이전.png");
		Image img = btn1Icon.getImage();
		Image ch = img.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		btn1.setIcon(chgIcon);

		btn2 = new JButton("다음");
		btn2.setBounds(320, 0, 210, 80);
		btn2.addActionListener(this);
		btn2.setPreferredSize(new Dimension(x, y));
		btn2Icon = new ImageIcon("ImageFile/PAY/다음.png");
		Image img2 = btn2Icon.getImage();
		Image ch2 = img2.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon2 = new ImageIcon(ch2);
		btn2.setIcon(chgIcon2);
		// =============================================
		pnl5.add(btn1);
		pnl5.add(btn2);

		this.add(pnl1);
		this.add(pnl4);
		this.add(pnl3);
		this.add(pnl5);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn3) { // 멤버십 적립
			if (true) { // 로그인이 되었을 때는 멤버십 적립 창으로 이동
				pm.changePanel(6);
				File f = new File("FileDataEx3.txt");
				FileReader fr = null;
				BufferedReader br = null;
				try {
					fr = new FileReader(f);
					br = new BufferedReader(fr);
					String l = null;
					pm.pnl06.a = 0;
					pm.pnl06.b = 0;
					while ((l = br.readLine()) != null) { // 파일에 저장된 포인트를 테이블에
						// 추가
						String[] str = l.split("/");
						if (str[1].equals("t")) { // 적립내역
							pm.pnl06.model.addRow(str);
							pm.pnl06.a = pm.pnl06.a + Integer.parseInt(str[0]);
						} else { // 사용내역
							pm.pnl06.model2.addRow(str);
							pm.pnl06.b = pm.pnl06.b + Integer.parseInt(str[0]);
						}
						pm.pnl06.lbl5.setText(Integer.toString(pm.pnl06.a - pm.pnl06.b) + "점 사용 가능합니다");
					}
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
				pm.pnl06.Point();
			} else {
				JOptionPane.showConfirmDialog(this, "비회원입니다. 회원가입을 하겠습니까?", "비회원", JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE);
			}
		} else if (e.getSource() == btn4) { // 쿠폰 사용
			pm.changePanel(8); // 패널8로
			pnl08.setData();
		} else if (e.getSource() == btn1) { // 이전
			pm.changePanel(1); // 패널1로
		} else if (e.getSource() == btn2) { // 다음
			pm.changePanel(3); // 패널3으로
			pm.updateTotal();
		}
	}
}

class Panel03 extends JPanel implements ActionListener, MouseListener { // 결제창
	// 3번
	JLabel lbl1, lbl2, lbl3, lbl4;
	JButton btn1, btn2, btn3, btn4; // 이전, 다음
	JPanel pnl1, pnl2, pnl3, pnl4, pnl5;
	Payment pm;
	int select;
	ImageIcon btn1Icon, btn2Icon, btn3Icon, btn4Icon, btn5Icon, btn6Icon;

	int x = 550;
	int y = 80;

	public Panel03(Payment pm) {
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(Color.WHITE);
		this.pm = pm;

		pnl1 = new JPanel(null);
		pnl1.setBackground(new Color(240, 235, 229));
		pnl1.setSize(600, 200);
		lbl2 = new JLabel("결제 방법 선택");
		lbl2.setBounds(225, 94, 150, 100);
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		pnl1.add(lbl2);

		pnl4 = new JPanel(null);
		pnl4.setBackground(new Color(240, 235, 229));
		pnl4.setSize(600, 400);
		btn3 = new JButton("현금");
		btn3Icon = new ImageIcon("ImageFile/PAY/현금.png");
		Image img1 = btn3Icon.getImage();
		btn3.setIcon(btn3Icon);
		btn3.setBounds(128, 12, 150, 200);
		btn3.setBackground(Color.WHITE);
		btn3.addMouseListener(this);
		btn4 = new JButton("카드");
		btn4Icon = new ImageIcon("ImageFile/PAY/카드.png");
		Image img3 = btn4Icon.getImage();
		btn4.setIcon(btn4Icon);
		btn4.setBounds(300, 12, 150, 200);
		btn4.setBackground(Color.WHITE);
		btn4.addMouseListener(this);
		pnl4.add(btn3);
		pnl4.add(btn4);

		pnl3 = new JPanel(null);
		pnl3.setBackground(new Color(240, 235, 229));
		pnl3.setSize(600, 100);
		lbl3 = new JLabel("최종 결제 금액      ");
		lbl3.setBounds(150, 60, 180, 50);
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbl4 = new JLabel(Integer.toString(pm.total));
		lbl4.setBounds(300, 55, 130, 50);
		lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		pnl3.add(lbl3);
		pnl3.add(lbl4);

		pnl5 = new JPanel(null);
		pnl5.setBackground(new Color(240, 235, 229));
		pnl5.setSize(600, 100);

		// =============================================
		btn1 = new JButton("이전");
		btn1.setBounds(55, 0, 210, 80);
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(x, y));
		btn1Icon = new ImageIcon("ImageFile/PAY/이전.png");
		Image img = btn1Icon.getImage();
		Image ch = img.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		btn1.setIcon(chgIcon);

		btn2 = new JButton("다음");
		btn2.setBounds(320, 0, 210, 80);
		btn2.addActionListener(this);
		btn2.setPreferredSize(new Dimension(x, y));
		btn2Icon = new ImageIcon("ImageFile/PAY/다음.png");
		Image img2 = btn2Icon.getImage();
		Image ch2 = img2.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon2 = new ImageIcon(ch2);
		btn2.setIcon(chgIcon2);
		// =============================================

		pnl5.add(btn1);
		pnl5.add(btn2);

		this.add(pnl1);
		this.add(pnl4);
		this.add(pnl3);
		this.add(pnl5);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			pm.changePanel(2);
		} else if (e.getSource() == btn2) {
			if (select == 1) { // 현금
				pm.changePanel(4);
			} else if (select == 2) { // 카드
				pm.changePanel(5);
				Thread t = new Thread(pm.pnl05, "5");
				pm.pnl05.thread_start = true;
				t.start();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btn3) {
			btn5Icon = new ImageIcon("ImageFile/PAY/현금체크.png");
			Image img5 = btn5Icon.getImage();
			btn3.setIcon(btn5Icon);
			btn4.setIcon(btn4Icon);
			select = 1;
		} else if (e.getSource() == btn4) {
			btn6Icon = new ImageIcon("ImageFile/PAY/카드체크.png");
			Image img6 = btn6Icon.getImage();
			btn4.setIcon(btn6Icon);
			btn3.setIcon(btn3Icon);
			select = 2;
		}
		this.revalidate();
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}

class Panel04 extends JPanel implements ActionListener, KeyListener { // 결제창 4번
	// - 현금
	JLabel lbl1, lbl2, lbl3, lbl4;
	JButton btn1, btn2; // 이전, 다음
	JPanel pnl1, pnl2, pnl3, pnl4, pnl5;
	Payment pm;
	JTextField tf;
	ImageIcon btn1Icon, btn2Icon;

	int x = 550;
	int y = 80;

	public Panel04(Payment pm) {
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(Color.WHITE);
		this.pm = pm;

		pnl4 = new JPanel(null);
		pnl4.setBackground(new Color(240, 235, 229));
		pnl4.setSize(600, 250);
		lbl2 = new JLabel("결제할 금액을 입력해주세요");
		lbl2.setBounds(160, 210, 300, 30);
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		tf = new JTextField(30);
		tf.setBounds(233, 260, 100, 30);
		tf.addKeyListener(this);
		pnl4.add(lbl2);
		pnl4.add(tf);

		pnl3 = new JPanel(null);
		pnl3.setBackground(new Color(240, 235, 229));
		pnl3.setSize(600, 100);
		lbl3 = new JLabel("최종 결제 금액      ");
		lbl3.setBounds(150, 126, 180, 50);
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbl4 = new JLabel(Integer.toString(pm.total));
		lbl4.setBounds(300, 120, 130, 50);
		lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		pnl3.add(lbl3);
		pnl3.add(lbl4);

		pnl5 = new JPanel(null);
		pnl5.setBackground(new Color(240, 235, 229));
		pnl5.setSize(600, 100);

		// =============================================
		btn1 = new JButton("이전");
		btn1.setBounds(55, 80, 210, 80);
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(x, y));
		btn1Icon = new ImageIcon("ImageFile/PAY/이전.png");
		Image img = btn1Icon.getImage();
		Image ch = img.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		btn1.setIcon(chgIcon);

		btn2 = new JButton("다음");
		btn2.setBounds(320, 80, 210, 80);
		btn2.addActionListener(this);
		btn2.setPreferredSize(new Dimension(x, y));
		btn2Icon = new ImageIcon("ImageFile/PAY/다음.png");
		Image img2 = btn2Icon.getImage();
		Image ch2 = img2.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon2 = new ImageIcon(ch2);
		btn2.setIcon(chgIcon2);
		// =============================================

		pnl5.add(btn1);
		pnl5.add(btn2);

		this.add(pnl4);
		this.add(pnl3);
		this.add(pnl5);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			pm.changePanel(3);
		} else if (e.getSource() == btn2) { // 입력받은 tf와 결제 금액이 같을 때 아래의 메시지
			if (Integer.parseInt(tf.getText()) == pm.realtotal) {
				JOptionPane.showMessageDialog(this, "결제가 완료되었습니다", "결제", JOptionPane.PLAIN_MESSAGE);
				if (pm.pnl06.point != 0) {
					File f = new File("FileDataEx3.txt");
					PrintWriter pw = null;
					try {
						FileWriter fw = new FileWriter(f, true);
						pw = new PrintWriter(fw);
						pw.println(pm.pnl06.point + "/t");
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						if (pw != null) {
							pw.close();
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "결제 금액이 올바르지 않습니다.");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
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

class Panel05 extends JPanel implements ActionListener, Runnable { // 결제창 4번 -
	// 카드
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
	JButton btn1; // 이전, 다음
	JPanel pnl1, pnl2, pnl3, pnl4, pnl5;
	Payment pm;
	Boolean thread_start = false;
	Font font = new Font("Serif", Font.BOLD, 50);
	ImageIcon btn1Icon;

	int x = 550;
	int y = 80;

	public Panel05(Payment pm) {
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(new Color(240, 235, 229));
		this.pm = pm;

		pnl4 = new JPanel(null);
		pnl4.setBackground(new Color(240, 235, 229));
		lbl5 = new JLabel("카드를 투입해주세요");
		lbl5.setBounds(200, 190, 200, 30);
		lbl5.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbl6 = new JLabel("");
		lbl6.setBounds(275, 230, 100, 120);
		lbl6.setFont(font); // 폰트 변경
		pnl4.add(lbl5);
		pnl4.add(lbl6);

		pnl3 = new JPanel(null);
		pnl3.setBackground(new Color(240, 235, 229));
		pnl3.setSize(600, 100);
		lbl3 = new JLabel("최종 결제 금액      ");
		lbl3.setBounds(150, 126, 180, 50);
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbl4 = new JLabel(Integer.toString(pm.total));
		lbl4.setBounds(300, 120, 130, 50);
		lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		pnl3.add(lbl3);
		pnl3.add(lbl4);

		pnl5 = new JPanel(null);
		pnl5.setBackground(new Color(240, 235, 229));
		pnl5.setSize(600, 100);

		// =============================================
		btn1 = new JButton("이전");
		btn1.setBounds(190, 80, 210, 80);
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(x, y));
		btn1Icon = new ImageIcon("ImageFile/PAY/이전.png");
		Image img = btn1Icon.getImage();
		Image ch = img.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		btn1.setIcon(chgIcon);
		// ========================================================

		pnl5.add(btn1);

		this.add(pnl4);
		this.add(pnl3);
		this.add(pnl5);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			pm.changePanel(3);
			thread_start = false;
		}
	}

	@Override
	public void run() {
		try {
			for (int i = Integer.parseInt(Thread.currentThread().getName()); i >= 0; i--) {
				lbl6.setText(Integer.toString(i));
				for (int j = 0; j < 10; j++) {
					if (thread_start == false) {
						return;
					}
					Thread.sleep(100);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "결제가 완료되었습니다", "결제", JOptionPane.PLAIN_MESSAGE);
		if (pm.pnl06.point != 0) {
			File f = new File("FileDataEx3.txt");
			PrintWriter pw = null;
			try {
				FileWriter fw = new FileWriter(f, true);
				pw = new PrintWriter(fw);
				pw.println(pm.pnl06.point + "/t");
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}
	}
}

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

	int x = 550;
	int y = 80;

	public Panel06(Payment pm) {
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(new Color(240, 235, 229));
		this.pm = pm;

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
			pm.changePanel(2);
		} else if (e.getSource() == btn2) { // 확인
			pm.changePanel(2);
			if (!tf.getText().equals("")) {
				pm.point = Integer.parseInt(tf.getText());
				pm.updateTotal();
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
		point = (int) (pm.total * 0.01);
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

class Panel08 extends JPanel implements ActionListener { // 쿠폰
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7;
	JButton btn1, btn2, btn3, btn4, btn5; // 이전, 다음
	JPanel pnl1, pnl2, pnl3, pnl4, pnl5, pnl6, pnl7;
	JTextField tf;
	Payment pm;
	private String[][] contents;
	DefaultTableModel model, model2;
	JTable tbl, tbl2;
	int sum = 0; // 선택한 품목에 할인쿠폰을 적용해서 그만큼 차감한 것의 총액
	ImageIcon btn1Icon;

	int x = 550;
	int y = 80;

	public Panel08(Payment pm) {
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(new Color(240, 235, 229));
		this.pm = pm;

		pnl6 = new JPanel(null);
		pnl6.setBackground(new Color(240, 235, 229));
		lbl7 = new JLabel("쿠폰 번호를 입력해주세요");
		lbl7.setBounds(190, 90, 200, 20);
		lbl7.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		tf = new JTextField(10);
		tf.setBounds(170, 130, 130, 30);
		btn4 = new JButton("쿠폰 등록");
		btn4.setBounds(320, 130, 90, 30);
		btn4.addActionListener(this);
		pnl6.add(lbl7);
		pnl6.add(tf);
		pnl6.add(btn4);

		pnl5 = new JPanel(null);
		pnl5.setBackground(new Color(240, 235, 229));
		lbl3 = new JLabel("등록된 쿠폰");
		lbl3.setBounds(165, 0, 100, 20);
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		String header[] = { "쿠폰 번호", "할인 가격" };
		String contents[][] = {};
		model = new DefaultTableModel(contents, header);
		tbl = new JTable(model) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);
				if (!isRowSelected(row)) {
					component.setBackground(Color.WHITE);
					return component;
				} else {
					component.setBackground(Color.ORANGE); // 셀 선택했을 때 색 변경
					return component;
				}
			}
		};
		JScrollPane sp = new JScrollPane(tbl);
		sp.setBounds(45, 40, 320, 150);
		sp.getViewport().setBackground(Color.WHITE);
		btn3 = new JButton("쿠폰 사용");
		btn3.setBounds(395, 90, 120, 50);
		btn3.addActionListener(this);
		pnl5.add(lbl3);
		pnl5.add(sp);
		pnl5.add(btn3);

		pnl7 = new JPanel(null);
		pnl7.setBackground(new Color(240, 235, 229));
		lbl6 = new JLabel("사용된 쿠폰");
		lbl6.setBounds(165, 0, 100, 20);
		lbl6.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		String header1[] = { "쿠폰 번호", "할인 가격" };
		String contents1[][] = {};
		model2 = new DefaultTableModel(contents1, header1);
		tbl2 = new JTable(model2) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);
				if (!isRowSelected(row)) {
					component.setBackground(Color.WHITE);
					return component;
				} else {
					component.setBackground(Color.ORANGE); // 셀 선택했을 때 색 변경
					return component;
				}
			}
		};
		JScrollPane sp1 = new JScrollPane(tbl2);
		sp1.setBounds(45, 40, 320, 150);
		sp1.getViewport().setBackground(Color.WHITE);
		btn5 = new JButton("쿠폰 사용 취소");
		btn5.setBounds(395, 90, 120, 50);
		btn5.addActionListener(this);
		pnl7.add(lbl6);
		pnl7.add(sp1);
		pnl7.add(btn5);

		pnl4 = new JPanel(null);
		pnl4.setBackground(new Color(240, 235, 229));
		pnl4.setSize(600, 80);
		// =============================================
		btn1 = new JButton("취소");
		btn1.setBounds(55, 0, 210, 80);
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(x, y));
		btn1Icon = new ImageIcon("ImageFile/PAY/취소.png");
		Image img = btn1Icon.getImage();
		Image ch = img.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(ch);
		btn1.setIcon(chgIcon);

		btn2 = new JButton("확인");
		btn2.setBounds(320, 0, 210, 80);
		btn2.addActionListener(this);
		btn2.setPreferredSize(new Dimension(x, y));
		ImageIcon btn2Icon = new ImageIcon("ImageFile/PAY/확인.png");
		Image img2 = btn2Icon.getImage();
		Image ch2 = img2.getScaledInstance(x + 10, y, Image.SCALE_SMOOTH);
		ImageIcon chgIcon2 = new ImageIcon(ch2);
		btn2.setIcon(chgIcon2);
		// =============================================
		pnl4.add(btn1);
		pnl4.add(btn2);

		this.add(pnl6);
		this.add(pnl5);
		this.add(pnl7);
		this.add(pnl4);

		this.setVisible(true);
	}

	public void setData() {
		// 파일 읽어오기
		File f = new File("FileDataEx2.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			String l = null;
			while ((l = br.readLine()) != null) {
				if (l.split("/")[2].equals("t")) {
					model.addRow(l.split("/"));
				} else {
					model2.addRow(l.split("/"));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) { // 취소
			pm.changePanel(2);
			model.setNumRows(0);
			model2.setNumRows(0);
		} else if (e.getSource() == btn2) { // 확인
			pm.changePanel(2);
			pm.discount = sum;
			pm.updateTotal();
			File f = new File("FileDataEx2.txt");
			PrintWriter pw = null;
			try {
				FileWriter fw = new FileWriter(f);
				pw = new PrintWriter(fw);

				for (int i = 0; i < tbl.getRowCount(); i++) {
					pw.print(tbl.getValueAt(i, 0) + "/");
					pw.print(tbl.getValueAt(i, 1) + "/");
					pw.println("t");
				}
				for (int i = 0; i < tbl2.getRowCount(); i++) {
					pw.print(tbl.getValueAt(i, 0) + "/");
					pw.print(tbl.getValueAt(i, 1) + "/");
					pw.println("f");
				}
				model.setNumRows(0); // 테이블 안의 값 초기화
				model2.setNumRows(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		} else if (e.getSource() == btn3) { // 쿠폰 사용 버튼
			if (tbl.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "쿠폰이 선택되지 않았습니다");
			} else {
				sum = sum + Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 1) + "");
				pm.discount = sum;
				model2.addRow(new Object[] { tbl.getValueAt(tbl.getSelectedRow(), 0),
						tbl.getValueAt(tbl.getSelectedRow(), 1) });
				model.removeRow(tbl.getSelectedRow());
			}
		} else if (e.getSource() == btn4) { // 쿠폰 등록 버튼
			if (!tf.getText().equals("1234")) {
				return;
			}
			String tcoupon[] = { tf.getText(), "5000" };
			model.addRow(tcoupon);
			tf.setText("");
			File f = new File("FileDataEx2.txt");
			FileWriter fw = null;
			PrintWriter pw = null;
			try {
				fw = new FileWriter(f);
				pw = new PrintWriter(fw);

				for (int i = 0; i < tbl.getRowCount(); i++) { // 행
					pw.print(tbl.getValueAt(i, 0) + "/");
					pw.print(tbl.getValueAt(i, 1) + "/");
					pw.println("t");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		} else if (e.getSource() == btn5) { // 쿠폰 사용 취소 버튼
			if (tbl2.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "쿠폰이 선택되지 않았습니다");
			} else {
				sum = sum + Integer.parseInt(tbl2.getValueAt(tbl2.getSelectedRow(), 1) + "");
				pm.discount = sum;
				model.addRow(new Object[] { tbl2.getValueAt(tbl2.getSelectedRow(), 0),
						tbl2.getValueAt(tbl2.getSelectedRow(), 1) });
				model2.removeRow(tbl2.getSelectedRow());
			}
		}
	}
}

public class Payment extends JFrame {
	Panel01 pnl01; // 결제창 1번
	Panel02 pnl02; // 결제창 2번
	Panel03 pnl03; // 결제창 3번
	Panel04 pnl04; // 결제창 4번 - 현금
	Panel05 pnl05; // 결제창 4번 - 카드
	Panel06 pnl06; // 멤버십 적립 - 회원
	Panel08 pnl08; // 쿠폰
	int total; // 선택한 품목의 총합
	int discount; // 적용한 할인쿠폰 만큼 차감
	int point; // 사용한 적립금만큼 차감
	int realtotal; // 모든 차감 이후 최종 결제할 금액

	public Payment() {
		this.setDefaultCloseOperation(3);
		this.setBackground(Color.WHITE);
		pnl01 = new Panel01(this);
		this.add(pnl01);

		pnl03 = new Panel03(this);
		pnl04 = new Panel04(this);
		pnl05 = new Panel05(this);
		pnl06 = new Panel06(this);
		pnl08 = new Panel08(this);
		pnl02 = new Panel02(this, pnl08);

		this.setSize(600, 1000);

		this.setVisible(true);
	}

	public void changePanel(int a) {
		getContentPane().removeAll();
		if (a == 1) {
			getContentPane().add(pnl01);
		} else if (a == 2) {
			getContentPane().add(pnl02);
		} else if (a == 3) {
			getContentPane().add(pnl03);
		} else if (a == 4) {
			getContentPane().add(pnl04);
		} else if (a == 5) {
			getContentPane().add(pnl05);
		} else if (a == 6) {
			getContentPane().add(pnl06);
		} else if (a == 8) {
			getContentPane().add(pnl08);
		}
		this.revalidate();
		this.repaint();
	}

	public void updateTotal() {
		realtotal = total - discount - point;
		pnl01.lbl4.setText(Integer.toString(realtotal));
		pnl02.lbl4.setText(Integer.toString(realtotal));
		pnl03.lbl4.setText(Integer.toString(realtotal));
		pnl04.lbl4.setText(Integer.toString(realtotal));
		pnl05.lbl4.setText(Integer.toString(realtotal));
	}

	public static void main(String[] args) {
		new Payment();
	}
}