import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class JavApp {

	public static void main(String[] args) {
		Random random = new Random();
		
		// heavyweight container
		JFrame frame = new JFrame("My GUI Example"); // 생성 (메모리에)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 끌 시 terminate

		// frame -> heavy weight container (운영체제가 관리하는 중량 컨테이너)
		// lightweight container (경량 컨테이너) -> 즉, panel을 만들어서 관리하는 게 편함
		JPanel primary = new JPanel();
		primary.setPreferredSize(new Dimension(420, 420));
		primary.setBackground(Color.white);
		primary.setLayout(null);
		
		int num1 = random.nextInt(6)+1;
		int num2 = random.nextInt(6)+1;
		
		Color color1 = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
		Color color2 = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
		Color color3 = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
		
		JLabel lbl1 = new JLabel(Integer.toString(num1));
		JLabel lbl2 = new JLabel(Integer.toString(num2));
		
		
		int x1 = random.nextInt(320);
		int y1 = random.nextInt(320);
		int x2 = random.nextInt(320);
		int y2 = random.nextInt(320);
		
		
		if (num1 > num2){
			lbl1.setFont(new Font("Verdana", Font.BOLD, 80));
			lbl2.setFont(new Font("Verdana", Font.BOLD, 30));
		} else if (num1 < num2){
			lbl1.setFont(new Font("Verdana", Font.BOLD, 30));
			lbl2.setFont(new Font("Verdana", Font.BOLD, 80));
		} else {
			lbl1.setFont(new Font("Verdana", Font.BOLD, 50));
			lbl2.setFont(new Font("Verdana", Font.BOLD, 50));
		}
		
		lbl1.setForeground(color2);
		lbl1.setForeground(color3);
		
	
		//lbl사이즈 지정 
		lbl1.setBounds(x1,y1, lbl1.getPreferredSize().width,lbl1.getPreferredSize().height);
		lbl2.setBounds(x2,y2, lbl2.getPreferredSize().width,lbl2.getPreferredSize().height);

		JPanel nestedPanel = new JPanel();
		nestedPanel.setPreferredSize(new Dimension(400, 400));
		nestedPanel.setBackground(color1);
		nestedPanel.setLayout(null);
		nestedPanel.setBounds(10,10,400,400);
		nestedPanel.add(lbl1);
		nestedPanel.add(lbl2);
		
		primary.add(nestedPanel);

		frame.getContentPane().add(primary); // 경량컨테이너라서 내가 관리
		frame.pack();
		frame.setVisible(true); // 생성된 객체 화면에 띄우기

	} // main()


}
