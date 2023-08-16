import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RectPanel extends JPanel {
	
	private ArrayList<RectData>  savedList; // 저장된 데이터 
	private RectData			 nowData; // 현재거 그림 
	private RectListener 		 rectL;
	private boolean			     bDrag; // 현재 drag모드인지 아닌지 
	private JTextField			 txtSize;
	private JButton				 btnColor;
	private JCheckBox			 chkFill;
	
	public RectPanel() {
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.white);
		
		rectL = new RectListener();
		addMouseListener(rectL);
		addMouseMotionListener(rectL);
		
		nowData = new RectData();
		savedList = new ArrayList<RectData>();
		
	    bDrag = false;
	    
	    txtSize = new JTextField(10);
	    txtSize.setText("1");
	    add(txtSize);
	    
	    btnColor = new JButton("Colot Chooser");
	    btnColor.addActionListener(new ButtonListener());
	    add(btnColor);
	    
	    chkFill = new JCheckBox("FILL");
	    chkFill.setBackground(Color.white);
	    add(chkFill);
	}
	
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		Graphics2D page2 = (Graphics2D)page; // Graphics에는 두께 변경 기능이 없음 
		
		// now mode
		if (bDrag) {
			page2.setStroke(new BasicStroke(nowData.width));
			page.setColor(nowData.selectedColor);
			draw4Rect(page, nowData.ptOne, nowData.ptTwo, nowData.bFill);
		} // if
		
		for (RectData data: savedList) {
			page2.setStroke(new BasicStroke(data.width));
			page.setColor(data.selectedColor);
			draw4Rect(page, data.ptOne, data.ptTwo, data.bFill);
		} // for
		
		
	}// paintComponent()
	
	private void draw4Rect(Graphics page, Point pt1, Point pt2, boolean fill) {
		int x, y, width, height;
		x = y = width = height = 0;
		
		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			x = pt1.x;
			y = pt1.y;
			width = pt2.x - pt1.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			x = pt1.x;
			y = pt2.y;
			width = pt2.x - pt1.x;
			height = pt1.y - pt2.y;
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			x = pt2.x;
			y = pt1.y;
			width = pt1.x - pt2.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			x = pt2.x;
			y = pt2.y;
			width = pt1.x - pt2.x;
			height = pt1.y - pt2.y;
		} // if.. else if
		
		if (fill) page.fillRect(x, y, width, height); // Oval로 하면 원 그릴 수 있음 
		else page.drawRect(x, y, width, height);
	} // draw4Rect();
	
	private class RectListener implements MouseListener, MouseMotionListener {
		
		@Override
		public void mousePressed(MouseEvent e) {
			nowData.ptOne = e.getPoint();
			nowData.width = Integer.parseInt(txtSize.getText());
			nowData.bFill = chkFill.isSelected();
			bDrag = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			nowData.ptTwo = e.getPoint();
			savedList.add(new RectData(nowData));
			bDrag = false;
			repaint();
		}	
		
		@Override
		public void mouseDragged(MouseEvent e) {
			nowData.ptTwo = e.getPoint();
			repaint();
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
		@Override
		public void mouseMoved(MouseEvent e) {}
		
	} // RectListener class
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			nowData.selectedColor = JColorChooser.showDialog(btnColor, "COLOR CHOOSER", nowData.selectedColor);
			
		}
		
	} // ButtonListener class
	
} // LinePanel class
