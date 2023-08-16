import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LinePanel extends JPanel {
	
	private ArrayList<LineData>  savedList; // 저장된 데이터 
	private LineData			 nowData; // 현재거 그림 
	private LineListener 		 lineL;
	private boolean			     bDrag; // 현재 drag모드인지 아닌지 
	private JTextField			 txtSize;
	private JButton				 btnColor;
	
	public LinePanel() {
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.white);
		
		lineL = new LineListener();
		addMouseListener(lineL);
		addMouseMotionListener(lineL);
		
		nowData = new LineData();
		savedList = new ArrayList<LineData>();
		
	    bDrag = false;
	    
	    txtSize = new JTextField(10);
	    txtSize.setText("1");
	    add(txtSize);
	    
	    btnColor = new JButton("Colot Chooser");
	    btnColor.addActionListener(new ButtonListener());
	    add(btnColor);
	}
	
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		Graphics2D page2 = (Graphics2D)page; // Graphics에는 두께 변경 기능이 없음 
		
		// now mode
		if (bDrag) {
			page2.setStroke(new BasicStroke(nowData.width));
			page.setColor(nowData.selectedColor);
			page.drawLine(nowData.ptOne.x, nowData.ptOne.y, nowData.ptTwo.x, nowData.ptTwo.y);
		} // if
		
		for (LineData data: savedList) {
			page2.setStroke(new BasicStroke(data.width));
			page.setColor(data.selectedColor);
			page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
		} // for
		
		
	}// paintComponent()
	
	private class LineListener implements MouseListener, MouseMotionListener {
		
		@Override
		public void mousePressed(MouseEvent e) {
			nowData.ptOne = e.getPoint();
			nowData.width = Integer.parseInt(txtSize.getText());
			bDrag = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			nowData.ptTwo = e.getPoint();
			savedList.add(new LineData(nowData));
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
		
	} // LineListener class
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			nowData.selectedColor = JColorChooser.showDialog(btnColor, "COLOR CHOOSER", nowData.selectedColor);
			
		}
		
	} // ButtonListener class
	
} // LinePanel class
