package etc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Team7 extends JFrame{
	StartCart sCart;

	public Team7() {
		sCart = new StartCart();

		setSize(500,900);
		setDefaultCloseOperation(3);
		setTitle("카트");

		this.add(sCart);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new Team7();
	}
}

class StartCart extends JPanel{
	CartPanel cPanel;
	ButtonPanel bPanel;

	public StartCart(){
		this.setLayout(new BorderLayout());
		cPanel = new CartPanel();
		this.add(cPanel,"Center");
		bPanel = new ButtonPanel(cPanel);
		this.add(bPanel,"South");
	}
}

class CartPanel extends JPanel{
	DefaultTableModel model;
	JTable tbl;
	JPanel pnl1,labPnl,imgPnl,menePnl,pricePnl,totalPricePln,orderPnl;
	JLabel lab, priceLab;
	String price = "0";
	int totalP = 0;

	public CartPanel() {

		setLayout(new GridLayout(0,1));
		String header[]={"상품이름","주문금액"};
		String contents[][] = {

				{"몬스터 와퍼","8600"},
				{"몬스터 와퍼주니어","5600"},
				{"트러플머쉬룸 와퍼","6700"},
				{"트러플머쉬룸 와퍼주니어","5300"}

		};

		model = new DefaultTableModel(contents,header); //데이터 받아와서 세팅
		tbl = new JTable(model);
		JScrollPane sp = new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnl1 = new JPanel(new BorderLayout());
		pnl1.add(sp);
		this.add(pnl1);

		String price="총 금액 : ";
		lab = new JLabel(price);
		tPrice();

		labPnl = new JPanel();

		JLabel tplbl = new JLabel((String.valueOf(totalP+"원")));
		labPnl.add(lab);
		labPnl.add(tplbl);
		this.add(labPnl);

	}
	//===================================================================

	public void getMenuImg(String mene){ //선택한 메뉴 사진 받아오는 메서드

		ImageIcon icon = new ImageIcon(mene+".png");
		Image iconChage = icon.getImage();  //ImageIcon을 Image 추출
		Image imgChage= iconChage.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); 
		//추출한 Image 크기 조절
		ImageIcon img = new ImageIcon(imgChage);
		//새로운 Image로 새로운 ImageIcon객체를 생성
		JLabel labImg = new JLabel(); 
		labImg.setIcon(img);
		orderPnl.add(labImg);
		this.add(orderPnl);
	}

	void getMenuName(String mene){   //선택한 메뉴이름 가져오는 메서드
		JLabel labName = new JLabel(mene);
		orderPnl.add(labName);
		this.add(orderPnl);
	}

	void getMenePrice(String price){ //선택한 메뉴 가격 가져오는 메서드
		JLabel labPrice = new JLabel(price+"원");
		orderPnl.add(labPrice);
		this.add(orderPnl);
	}

	void setPrice(){                     //토탈 결제금액 출력

		String totalPrice = "총 결제금액 :";

		priceLab = new JLabel(totalPrice);
		JLabel totalLab = new JLabel((String.valueOf(totalP+"원")));
		totalPricePln.add(priceLab);
		totalPricePln.add(totalLab);
		this.add(totalPricePln);
	}

	void tPrice(){    //결제금액 구하기
		for(int i=0;i<model.getRowCount();i++){
			price= (String.valueOf(model.getValueAt(i, 1)));
			totalP+=Integer.parseInt(price);
		}

	}

	int getotalP(){  //총 결제금액
		return totalP;
	}

	/*
      void menuName(){  //주문 메뉴 이름 넘겨주기
         return model.getValueAt(i,0);
      }*/

	void setInit(){

		orderPnl = new JPanel();  //이미지,메뉴명,가격
		orderPnl.setLayout(new GridLayout(0,3,20,0));

		totalPricePln = new JPanel(); //총 결제금액

	}

	void rmPnl(){
		this.remove(pnl1);
		this.remove(labPnl);

	}
	void rmPnl2(){
		this.remove(orderPnl);
		this.remove(totalPricePln);
	}
	void addPnl(){
		this.add(pnl1);
		this.add(labPnl);
	}
}
//========================================================================================================
class ButtonPanel extends JPanel implements ActionListener{
	CartPanel cart;

	JPanel btnPnl,  newBtnPnl;
	JButton btn1,btn2,newBtn1,newBtn2;
	public ButtonPanel(CartPanel cPnl){

		this.cart = cPnl;

		btn1 = new JButton("취소하기"); //첫 화면으로 돌아가
		btn2 = new JButton("주문하기");
		btn1.addActionListener(this);
		btn2.addActionListener(this);

		btn1.setBackground(Color.RED);
		btn2.setBackground(Color.GREEN);
		btn1.setForeground(Color.white);
		btn2.setForeground(Color.white);

		btnPnl = new JPanel();
		btnPnl.add(btn1);
		btnPnl.add(btn2);

		this.add(btnPnl);
	}


	void newBtn(){                     //새로운 버튼
		newBtn1 = new JButton("주문취소");
		newBtn2 = new JButton("결제하기");
		newBtn1.setBackground(Color.RED);
		newBtn2.setBackground(Color.GREEN);
		newBtn1.setForeground(Color.white);
		newBtn2.setForeground(Color.white);

		newBtn1.addActionListener(this);
		newBtn2.addActionListener(this);

		newBtnPnl = new JPanel();
		newBtnPnl.add(newBtn1);
		newBtnPnl.add(newBtn2);

		this.add(newBtnPnl);
	}


	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btn2){

			cart.setInit();
			cart.rmPnl();
			this.remove(btnPnl);

			for(int i =0; i< cart.model.getRowCount();i++ ){

				cart.getMenuImg(String.valueOf(cart.model.getValueAt(i,0)));
				cart.getMenuName(String.valueOf(cart.model.getValueAt(i,0)));
				cart.getMenePrice(String.valueOf(cart.model.getValueAt(i,1)));   
			}

			cart.setPrice();
			newBtn();

			repaint();      //새로그림
			revalidate();   //새로고침

		}else if(e.getSource()==btn1){ //취소하기 첫화면으로 가기

		}
		else if(e.getSource()==newBtn1){ //주문내역에서 카트로 돌아오기

			cart.rmPnl2();
			cart.addPnl();
			this.remove(newBtnPnl);
			this.add(btnPnl);

			repaint();      //새로그림
			revalidate();   //새로고침
		}else if(e.getSource()==newBtn2){ //결제하기

			cart.rmPnl2();
			this.remove(newBtnPnl);
			// cart.getotalP(); //총결제금액 리턴하는 메소드

			repaint();      //새로그림
			revalidate();   //새로고침

		}

	}

}
