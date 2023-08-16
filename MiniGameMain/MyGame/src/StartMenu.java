import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartMenu extends JPanel{
	
	private ImageIcon icTitle, icStory, icTuto;
	private JLabel title, story, tutorial;

	public StartMenu() {
		// 초기화면 패널 꾸미기
		setBackground(Color.white);
		setPreferredSize(new Dimension(630,420));
		setLayout(null);
		
		icTitle = new ImageIcon("title.png");
		icStory = new ImageIcon("story.png");
		icTuto = new ImageIcon("tutorial.png");
		
		
		title = new JLabel(); // 제목
		title.setIcon(icTitle);
		title.setBounds(155,20,320,54);
		title.setHorizontalAlignment(JLabel.CENTER);
		add(title);
		
		Color theme = new Color(251,209,251); // 연핑크
		
		story = new JLabel();
		story.setIcon(icStory); // 게임 스토리 설명 (왼쪽 박스)
		story.setBounds(13,110,288,214);
		story.setHorizontalAlignment(JLabel.CENTER);
		story.setBorder(BorderFactory.createLineBorder(theme, 1)); // 테두리 설정
		add(story);
		
		tutorial = new JLabel(); // 게임 방법 설명 (오른쪽 박스)
		tutorial.setIcon(icTuto);
		tutorial.setBounds(329,110,288,214);
		tutorial.setHorizontalAlignment(JLabel.CENTER);
		tutorial.setBorder(BorderFactory.createLineBorder(theme, 1)); // 테두리 설정
		add(tutorial);
		
	}
}
