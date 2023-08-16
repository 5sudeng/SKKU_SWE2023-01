import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JApplet;

public class MyApplet extends JApplet {

	public void paint(Graphics page) {

		page.setColor(Color.black);
		page.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 50));
		page.drawString("SKKU", 100, 100);

		page.setColor(Color.gray);
		page.drawLine(150, 150, 550, 150);
		page.setColor(Color.DARK_GRAY);

		page.setColor(Color.red);
		page.drawRect(100, 200, 30, 80);

	} // paint()
} // MyApplet class
