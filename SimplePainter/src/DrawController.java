import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawController extends JPanel { 
	
	private SimplePainterModel nowData;
	private ArrayList<SimplePainterModel> savedList;
	private DrawListener drawL;
	private SimplePainterView view;
	private boolean bDrag;
	
	public DrawController(SimplePainterView v) {
		view = v;
		
		setBackground(Color.white);
		
		drawL = new DrawListener();
		
		addMouseListener(drawL);
		addMouseMotionListener(drawL);
		
		nowData = new SimplePainterModel();
		savedList = new ArrayList<SimplePainterModel>();
		
		nowData.nDrawMode = Constants.NONE;
		bDrag = false;
	} // DrawController()
	
	public void undo() {
		savedList.remove(savedList.size() - 1);
		repaint();
	}
	
	public void clear() {
		savedList.clear();
		repaint();
	}
	
	public void setDrawMode(int mode) {
		nowData.nDrawMode = mode;
	}
	
	public void setSelectedColor(Color color) {
		nowData.selectedColor = color;
	}
	
	public Color getSelectedColor() { return nowData.selectedColor; }
	
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		
		Graphics2D page2 = (Graphics2D)page;
		
		if (bDrag) {
			page.setColor(nowData.selectedColor);
			page2.setStroke(new BasicStroke(nowData.nSize));
			switch(nowData.nDrawMode) {
				case Constants.LINE :
					page.drawLine(nowData.ptOne.x, nowData.ptOne.y, nowData.ptTwo.x, nowData.ptTwo.y);
					break;
				case Constants.RECT :
					draw4Rect(page, nowData.ptOne, nowData.ptTwo, nowData.bFill);
					break;
				case Constants.OVAL :
					draw4Oval(page, nowData.ptOne, nowData.ptTwo, nowData.bFill);
					break;
			} //switch
		} //if - //now data
		
		
		for (SimplePainterModel data: savedList) {
			switch(data.nDrawMode) {
				case Constants.DOT :
					page.setColor(data.selectedColor);
					page.fillOval(data.ptOne.x - data.nSize/2, data.ptOne.y - data.nSize/2, data.nSize, data.nSize);
					break;
				case Constants.LINE :
					page.setColor(data.selectedColor);
					page2.setStroke(new BasicStroke(data.nSize));
					page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
					break;
				case Constants.RECT :
					page.setColor(data.selectedColor);
					page2.setStroke(new BasicStroke(data.nSize));
					draw4Rect(page, data.ptOne, data.ptTwo, data.bFill);
					break;
				case Constants.OVAL :
					page.setColor(data.selectedColor);
					page2.setStroke(new BasicStroke(data.nSize));
					draw4Oval(page, data.ptOne, data.ptTwo, data.bFill);
					break;
			} // switch
			
		} //for 
		
	} // paintComponent()
	
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
		
		if (fill) page.fillRect(x, y, width, height); 
		else page.drawRect(x, y, width, height);
	} // draw4Rect();
	
	private void draw4Oval(Graphics page, Point pt1, Point pt2, boolean fill) {
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
		
		if (fill) page.fillOval(x, y, width, height); 
		else page.drawOval(x, y, width, height);
	} // draw4Oval();
	
	private class DrawListener implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (nowData.nDrawMode == Constants.DOT) {
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTxtSize();
				savedList.add(new SimplePainterModel(nowData));
				view.setMessage(nowData);
				repaint();
			} // if
		} // mouseClicked()
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (nowData.nDrawMode == Constants.LINE) {
				bDrag = true;
				
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTxtSize();
				view.setMessage(nowData);
			} // if
			
			if (nowData.nDrawMode == Constants.RECT || nowData.nDrawMode == Constants.OVAL) {
				bDrag = true;
				
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTxtSize();
				nowData.bFill = view.getChkFill();
				view.setMessage(nowData);
			} // if
			
			
		} // mousePressed()

		@Override
		public void mouseReleased(MouseEvent e) {
			if (nowData.nDrawMode == Constants.LINE) {
				bDrag = false;
				
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				view.setMessage(nowData);
				repaint();
			} // if
			
			if (nowData.nDrawMode == Constants.RECT || nowData.nDrawMode == Constants.OVAL) {
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				bDrag = false;
				view.setMessage(nowData);
				repaint();
			} // if
			
		} // mouseReleased()
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (nowData.nDrawMode == Constants.LINE) {
				nowData.ptTwo = e.getPoint();
				view.setMessage(nowData);
				repaint();
			} // if
			
			if (nowData.nDrawMode == Constants.RECT || nowData.nDrawMode == Constants.OVAL) {
				nowData.ptTwo = e.getPoint();
				view.setMessage(nowData);
				repaint();
			} // if
		} // mouseDragged() 
		
		
		@Override
		public void mouseMoved(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
} // DrawController class
