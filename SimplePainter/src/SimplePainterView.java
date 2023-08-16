import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimplePainterView extends JPanel { // 메뉴 설정 
	
	private DrawController drawController;
	
	private JLabel modelbl, sizelbl, pointlbl;
	private JPanel colorpan;
	
	private JPanel		menuPanel, optionPanel, messagePanel;
	private JButton[] 	btnMenuArray;
	private JTextField 	txtSize;
	private JButton 	btnColorChooser;
	private JCheckBox 	chkFill;
	
	public SimplePainterView() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(700, 780));
		setLayout(null);
		
		drawController = new DrawController(this);
		drawController.setBounds(10, 10, 680, 550);
		drawController.setBorder(BorderFactory.createTitledBorder("DRAWING"));
		add(drawController);
		
		menuPanel = new JPanel();
		menuPanel.setBounds(10, 570, 250, 200);
		menuPanel.setBackground(Color.white);
		menuPanel.setBorder(BorderFactory.createTitledBorder("MENU"));
		menuPanel.setLayout(new GridLayout(2,3)); //2행 3열 
		add(menuPanel);
		
		optionPanel = new JPanel();
		optionPanel.setBackground(Color.white);
		optionPanel.setBounds(270, 570, 160, 200);
		optionPanel.setBorder(BorderFactory.createTitledBorder("OPTION"));
		optionPanel.setLayout(new GridLayout(3,1));
		add(optionPanel);
		
		messagePanel = new JPanel();
		messagePanel.setLayout(new GridLayout(4,1));
		messagePanel.setBackground(Color.white);
		messagePanel.setBounds(440, 570, 250, 200);
		messagePanel.setBorder(BorderFactory.createTitledBorder("MESSAGE"));
		modelbl = new JLabel();
		colorpan = new JPanel();
		colorpan.setBackground(Color.white);
		sizelbl = new JLabel();
		pointlbl = new JLabel();
		messagePanel.add(modelbl);
		messagePanel.add(sizelbl);
		messagePanel.add(pointlbl);
		messagePanel.add(colorpan);
		add(messagePanel);
		
		btnMenuArray = new JButton[6];
		for (int i=0; i<6; i++) {
			btnMenuArray[i] = new JButton(Constants.MENU[i]);
			btnMenuArray[i].setBackground(Constants.HOVERING[0]);
			btnMenuArray[i].setForeground(Constants.HOVERING[1]);
			btnMenuArray[i].addMouseListener(new HoveringListener());
			btnMenuArray[i].addActionListener(new MenuListener());
			menuPanel.add(btnMenuArray[i]);
		} // for
		
		
		btnColorChooser = new JButton("Color Chooser");
		btnColorChooser.setBackground(Constants.HOVERING[0]);
		btnColorChooser.setForeground(Constants.HOVERING[1]);
		btnColorChooser.addMouseListener(new HoveringListener());
		btnColorChooser.addActionListener(new MenuListener());
		optionPanel.add(btnColorChooser);
		
		txtSize = new JTextField(10);
		txtSize.setText("10");
		//txtSize.setFont(new Font("Monlo", Font.BOLD, 16)); //안한게 더 예쁘다 
		txtSize.setVisible(false);
		txtSize.setHorizontalAlignment(0);
		optionPanel.add(txtSize);
		
		
		chkFill = new JCheckBox("FILL");
		chkFill.setVisible(false);
		chkFill.setBackground(Color.white);
		chkFill.setHorizontalAlignment(0);
		optionPanel.add(chkFill);
		
	} // SimplePainterView()
	
	public void setTxtSize(int size) { txtSize.setText(Integer.toString(size)); }
	public int getTxtSize() { return Integer.parseInt(txtSize.getText()); }
	
	public boolean getChkFill() { return chkFill.isSelected(); }
	
	public void setMessage(SimplePainterModel m) {
	    String drawMode = getDrawModeString(m.nDrawMode);
	    modelbl.setText("Mode : "+drawMode);
	    colorpan.setBackground(m.selectedColor);
	    sizelbl.setText("Size : "+m.nSize);
	    String points;
	    if (m.nDrawMode == 0)
	    	points = "Point1 (" + m.ptOne.x + ", " + m.ptOne.y + ")";
	    else
	    	points = "Point1 (" + m.ptOne.x + ", " + m.ptOne.y + "),   Point2 (" + m.ptTwo.x + ", " + m.ptTwo.y + ")";
	    pointlbl.setText(points);
	} // setMessage

	private String getDrawModeString(int drawMode) {
	    switch (drawMode) {
	        case Constants.DOT:
	            return "Dot";
	        case Constants.LINE:
	            return "Line";
	        case Constants.RECT:
	            return "Rect";
	        case Constants.OVAL:
	            return "Oval";
	        default:
	            return "None";
	    }
	} // getDrawModeString()
	
	public void setNewMessage(int mode) {
	    String drawMode = getDrawModeString(mode);
	    modelbl.setText("Mode : "+drawMode);
	    pointlbl.setText("Point : -");
	    if (mode == 4 || mode == 5)
	    	sizelbl.setText("Size : -");
	} // setNewMessage -> 버튼 클릭시 변한 모드만 보여줌 
	
	public void setColorMessage(Color c) {
	    colorpan.setBackground(c);
	    pointlbl.setText("Point : -");
	} // setColorMessage

	private class HoveringListener implements MouseListener {

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton btnMenu = (JButton)e.getSource();
			btnMenu.setBackground(Constants.HOVERING[2]);
			btnMenu.setForeground(Constants.HOVERING[3]);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton btnMenu = (JButton)e.getSource();
			btnMenu.setBackground(Constants.HOVERING[0]);
			btnMenu.setForeground(Constants.HOVERING[1]);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		
	} // HoveringListener class
	
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if (obj == btnColorChooser) {
				Color c = JColorChooser.showDialog(btnColorChooser, "COLOR CHOOSER", drawController.getSelectedColor());
				drawController.setSelectedColor(c);
				setColorMessage(c);
				return;
			}
			
			txtSize.setVisible(true);
			chkFill.setVisible(false);
			
			for (int i=0; i<6; i++) {
				if (obj == btnMenuArray[i]) {
					drawController.setDrawMode(i); // drawmode를 바꿈 
					setNewMessage(i);
					if (i == Constants.RECT || i == Constants.OVAL) {
						chkFill.setVisible(true);
						break;
					} else if (i == Constants.DOT || i == Constants.LINE) {
						break;
					} else { 
						txtSize.setVisible(false); // UNDO 또는 CLEAR를 선택했을 때, txtSize 안보이게 설정
						if (i == Constants.UNDO) {
							drawController.undo();
						} else if ( i == Constants.CLEAR) {
							drawController.clear();
						}
						break;
					} //if..else if
				}// if..else if	
			} // for
			
		} // actionPerformed()
		
	} // MenuListener
	
} // SimplePainterView class
