import java.awt.*;
import java.awt.event.*;

public class Square extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TextField tf;
	int x1, y1;
	int x2, y2;
	int[] arrX;
	int[] arrY;
	Graphics g;
	
	Square() {

		setBackground(Color.cyan);
		setLayout(new FlowLayout());
		setVisible(true);
		
		tf = new TextField(20);
		tf.setFont(new Font("Arial", Font.BOLD, 18));
		setSize(600, 600);
		add(tf);

		this.addMouseListener(new A());
		this.addMouseMotionListener(new A());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		
		int[] arrX = {x1, x2, x2, x1};
		int[] arrY = {y1, y2, y1, y2};
		g = getGraphics();
		g.setColor(Color.pink);
		g.drawPolygon(arrX, arrY, 4);  // Rectangle
		
	}
	
	class A implements MouseListener, MouseMotionListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}


		@Override
		public void mouseMoved(MouseEvent e) {
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			repaint();
		}
		
	}
	
}
