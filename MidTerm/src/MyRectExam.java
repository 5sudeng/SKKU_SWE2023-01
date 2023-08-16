import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class MyRectExam {

	public static void main(String[] args) {
		// random 객체 생성 -> color 지정위해서
		Random random = new Random();
		
		// 사각형 겹치는 경우를 찾기 위한 bool 변수 생성 
		boolean overlap;
		
		// 랜덤 컬러 만들어두기
		Color color1 = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
		Color color2 = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
		
		// rect 객체 두개 생성 
		MyRect rect1, rect2;
		rect1 = new MyRect();
		rect2 = new MyRect();
		
		// heavyweight container
		// frame -> heavy weight container (운영체제가 관리하는 중량 컨테이너)
		JFrame frame = new JFrame("MIDTERM EXAM"); // 생성 (메모리에) + 창 이름 지정 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 끌 시 terminate
		
		// lightweight container (경량 컨테이너) -> 즉, panel을 만들어서 관리하는 게 편함
		JPanel primary = new JPanel();
		primary.setPreferredSize(new Dimension(400, 400)); // 프레임의 크기 400x400
		primary.setBackground(Color.white); // 프레임 배경색 -> 흰
		primary.setLayout(null); // 배치관리자 disable

		JPanel rectPan1 = new JPanel(); // rectPanel1 생성 
		rectPan1.setPreferredSize(new Dimension(rect1.getWidth(),rect1.getHeight())); // rect1의 width와 height로 size 설정 
		rectPan1.setBackground(color1); // 랜덤 설정해둔 color로 색 설정 
		rectPan1.setBounds(rect1.getX(), rect1.getY(), rectPan1.getPreferredSize().width, rectPan1.getPreferredSize().height);
		// rect1의 좌표와 사이즈로 bounds설정 
		
		JPanel rectPan2 = new JPanel(); // rectPanel1과 동일하게 진행
		rectPan2.setPreferredSize(new Dimension(rect2.getWidth(),rect2.getHeight()));
		rectPan2.setBackground(color2);
		rectPan2.setBounds(rect2.getX(), rect2.getY(), rectPan2.getPreferredSize().width, rectPan2.getPreferredSize().height);
		
		// 사각형이 겹치는 경우 overlap -> true
		if ( rect1.getX() > rect2.getX()+rect2.getWidth() )
			overlap = false;
		else if ( rect1.getX()+rect1.getWidth() < rect2.getX())
			overlap = false;
		else if ( rect1.getY() > rect2.getY()+rect2.getHeight())
			overlap = false;
		else if ( rect1.getY() + rect1.getHeight() < rect2.getY())
			overlap = false;
		else
			overlap = true;
		
		//Cond1 - RectA.Left > RectB.Right
		//Cond2 - RectA.Right < RectB.Left
		//Cond3 - RectA.Top > RectB.Bottom
		//Cond4 - RectA.Bottom < RectB.Top
		//하나라도 트루이면 안겹침  
		
		if (overlap == true) { // 사각형이 겹치는 경우에만 실행 
			int condition = rect1.compareRectArea(rect2); // rect1과 rect2의 넓이를 비교하여 condition에 반환
			JLabel label = new JLabel(); // 라벨 생성 
			label.setFont(new Font("Verdana", Font.BOLD, 10)); // 폰트 지정 
			if (condition == 1) { // rect1의 넓이가 큰경우 -> 라벨의 text 설정 , 라벨의 위치는 큰 사각형보다 10 아래, 왼쪽부터 10으로 설정
				label.setText("Rect1 > Rect2");
				label.setBounds(rect1.getX() + 10,rect1.getY() + rect1.getHeight() + 10, label.getPreferredSize().width,label.getPreferredSize().height);
			} else if (condition == -1) { // rect2의 넓이가 큰 경우 
				label.setText("Rect1 < Rect2");
				label.setBounds(rect2.getX() + 10,rect2.getY() + rect2.getHeight() + 10, label.getPreferredSize().width,label.getPreferredSize().height);
			} else {
				label.setText("Rect1 = Rect2"); // 넓이가 같은 경우 
				label.setBounds(rect1.getX() + 10,rect1.getY() + rect1.getHeight() + 10, label.getPreferredSize().width,label.getPreferredSize().height);
			}
			
			primary.add(label);
		} // if overlap
		
		primary.add(rectPan2);
		primary.add(rectPan1);
		
		frame.getContentPane().add(primary); // 경량컨테이너라서 내가 관리
		frame.pack();
		frame.setVisible(true); // 생성된 객체 화면에 띄우기
		
	} // main()
} // MyRectExam class
