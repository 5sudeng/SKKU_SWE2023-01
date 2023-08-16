import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
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

        frame = new JFrame("Mini Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        primary = new JPanel();
        primary.setPreferredSize(new Dimension(630, 420));
        primary.setBackground(Color.white);
        primary.setLayout(null);
        frame.getContentPane().add(primary);

        btnGame1 = new JButton();
        btnGame1.setBounds(50, 100, 255, 155);
        btnGame1.setBackground(Color.green);
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

        btnGame2 = new JButton();
        btnGame2.setBounds(325, 100, 255, 155);
        btnGame2.setBackground(Color.green);
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

        btnGame3 = new JButton();
        btnGame3.setBounds(50, 260, 255, 155);
        btnGame3.setBackground(Color.green);
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

        btnGame4 = new JButton();
        btnGame4.setBounds(325, 260, 255, 155);
        btnGame4.setBackground(Color.green);
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
