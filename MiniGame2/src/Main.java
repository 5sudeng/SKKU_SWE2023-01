import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame;
        JPanel primary;
        JButton btnGame1, btnGame2, btnGame3, btnGame4;
        
        Image image1 = new ImageIcon("Archive 2/main3.png").getImage(); 
		image1 = image1.getScaledInstance(250, 150, Image.SCALE_SMOOTH); 
		ImageIcon icon1 = new ImageIcon(image1);
		
		Image image2 = new ImageIcon("Archive 2/main1.png").getImage(); 
		image2 = image2.getScaledInstance(250, 150, Image.SCALE_SMOOTH); 
		ImageIcon icon2 = new ImageIcon(image2);
		
		Image image3 = new ImageIcon("Archive 2/main2.png").getImage(); 
		image3 = image3.getScaledInstance(250, 150, Image.SCALE_SMOOTH); 
		ImageIcon icon3 = new ImageIcon(image3);
		
		Image image4 = new ImageIcon("Archive 2/main4.png").getImage(); 
		image4 = image4.getScaledInstance(250, 150, Image.SCALE_SMOOTH); 
		ImageIcon icon4 = new ImageIcon(image4);

		Image image5 = new ImageIcon("Archive 2/MiniGameTitle.png").getImage(); 
		image5 = image5.getScaledInstance(500, 80, Image.SCALE_SMOOTH); 
		ImageIcon icon5 = new ImageIcon(image5);
		
        frame = new JFrame("Mini Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        

        primary = new JPanel();
        primary.setPreferredSize(new Dimension(630, 420));
        primary.setBackground(Color.black);
        primary.setLayout(null);
        frame.getContentPane().add(primary);
            
        // icon5를 포함한 JLabel을 생성합니다.
        JLabel label = new JLabel(icon5);
        // JLabel의 너비와 높이를 가져옵니다.
        int labelWidth = label.getPreferredSize().width;
        int labelHeight = label.getPreferredSize().height;
        // JLabel을 primary 패널의 상단 가운데로 위치시킵니다.
        label.setBounds(70, 10, labelWidth, labelHeight);
        // primary 패널에 JLabel을 추가합니다.
        primary.add(label);

        btnGame1 = new JButton(icon1);
        btnGame1.setBounds(50, 100, 255, 155);
        btnGame1.setBackground(Color.black);
        btnGame1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String gamePath = "BRGame/bin/BRGame.class";
                    executeGame(gamePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        primary.add(btnGame1);

        btnGame2 = new JButton(icon2);
        btnGame2.setBounds(325, 100, 255, 155);
        btnGame2.setBackground(Color.black);
        // 미니게임 버튼에 액션 리스너 추가
        btnGame2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String gamePath = "MiniGame/bin/DudoDudoji.class";
                    executeGame(gamePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        primary.add(btnGame2);

        btnGame3 = new JButton(icon4);
        btnGame3.setBounds(50, 260, 255, 155);
        btnGame3.setBackground(Color.black);
        btnGame3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String gamePath = "MyGame/bin/GameMain.class";
                    executeGame(gamePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        primary.add(btnGame3);

        btnGame4 = new JButton(icon3);
        btnGame4.setBounds(325, 260, 255, 155);
        btnGame4.setBackground(Color.black);
        btnGame4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String gamePath = "Pong/bin/PongGame.class";
                    executeGame(gamePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }        
            }
        });
        primary.add(btnGame4);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    private static void executeGame(String gamePath) throws IOException {
        File gameFile = new File(gamePath);
        if (gameFile.exists()) {
            String absolutePath = gameFile.getAbsolutePath();
            Desktop.getDesktop().open(gameFile);
        } else {
            System.err.println("게임 파일을 찾을 수 없습니다.");
        }
    }
}
