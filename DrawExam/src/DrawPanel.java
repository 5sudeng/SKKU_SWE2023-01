import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
	private ArrayList<DrawData> _ptList;
	//private Point _ptDot;
	private int 		_dotSize; 
	private Color 		_selectedColor;
	private JTextField  _txtDotSize;
	private JButton	    _btnColor;
	
	public DrawPanel() {
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.white);
		addMouseListener(new DrawListener());
		
		_txtDotSize = new JTextField(10);
		_txtDotSize.setText("10");
		add(_txtDotSize);
		
		_btnColor = new JButton("Color Chooser");
		_btnColor.addActionListener(new ButtonListener());
		add(_btnColor);
		
		_selectedColor = Color.black; // 초기값 black -> new Color(0,0,0);(동일) 
		
		_dotSize = 100;
		//_ptDot = new Point();
		_ptList = new ArrayList<DrawData>();
		
	} // DrawPanel()
	
	public void setSize(int size) {_dotSize = size;}
	public int geDottSize() {return _dotSize;}
	
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		
		//page.fillOval(_ptDot.x - _dotSize/2, _ptDot.y - _dotSize/2, _dotSize, _dotSize);
		for (DrawData data: _ptList) {
			//page.fillOval(pt.x - _dotSize/2, pt.y - _dotSize/2, _dotSize, _dotSize);
			page.setColor(data.color);
			page.fillOval(data.ptDot.x - data.dotSize/2, data.ptDot.y - data.dotSize/2, data.dotSize, data.dotSize);
		}
	}// paintComponent()
	
	//1. listener class
	private class DrawListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			//_ptDot = e.getPoint();  //_ptDot.x = e.getX(); 이벤트 발생 좌표 
			//_ptList.add(e.getPoint());
			_ptList.add(new DrawData(e.getPoint(), _selectedColor, Integer.parseInt(_txtDotSize.getText()))); //클릭한 곳의 좌표값, 컬러값, 텍스트 필드의 값 어레이리스트에 저장 
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	} // DrawListener class
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == _btnColor) {
				_selectedColor = JColorChooser.showDialog(_btnColor, "Color Chooser", _selectedColor);
			} // if
			
		} // actionPerformed
		
	} // ButtonListener class
	
} // DrawPanel class
