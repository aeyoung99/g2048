import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class g2048 extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel[][] labels = new JLabel[5][5];
	Random rg = new Random();
	JPanel panel;
	GridBagConstraints c;
	int[][] val;
	int x1, y1, x2, y2;
	
	g2048() {
		
		val = new int[5][5];
		initLabels();
		
		// Initialize frame
		setSize(500,500);
		setLayout(new BorderLayout());
		setResizable(false);
		getContentPane().setBackground(Color.white);
		
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.YELLOW);
		panel.setSize(600,600);
		
		// Use constraints to set label locations
		c = new GridBagConstraints();
		c.insets = new Insets(30,30,30,30);
		
		for (int i = 0 ; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				c.gridx = i;
				c.gridy = j;
				panel.add(labels[j][i], c);
			}
		}
		
		add(panel, BorderLayout.CENTER);
		// pack();
		
		// Add MouseMotionListener
		this.addMouseListener(new myMouseListener());
		this.addMouseMotionListener(new myMouseListener());
		this.addComponentListener(new myWindowListener()); 
	}
	
	@Override
	public void paint(Graphics g) {
		
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				labels[row][col].setText(val[row][col]+"");
				labels[row][col].setSize(50, 50);
				labels[row][col].setFont(new Font("Arial", Font.BOLD, 30));
			}
		}
	}
	
	private class myWindowListener implements ComponentListener {
		@Override
		public void componentResized(ComponentEvent e) {
			repaint();
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private void initLabels() {
		
		// initialize label value
		for (int i = 0 ; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				val[i][j] = 0;
			}
		}
		int row1 = rg.nextInt(5); // Exclude 4
		int row2 = rg.nextInt(5);
		int col1 = rg.nextInt(5);
		int col2 = rg.nextInt(5);
		val[row1][col1] = 2;
		val[row2][col2] = 2;
		
		// set label value
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				int red = rg.nextInt(256);
				int green = rg.nextInt(256);
				int blue = rg.nextInt(256);
				
				// Set random RGB color to the TextField
				Color randomColor = new Color(red, green, blue);
				labels[row][col] = new JLabel(val[row][col]+"");
				labels[row][col].setSize(50, 50);
				labels[row][col].setFont(new Font("Arial", Font.BOLD, 30));
				labels[row][col].setForeground(randomColor);
				// labels[row][col].setText(val[row][col]+"");
				
			}
		}
		
	}
	
	private void randomLabel() {
		
		int row; 
		int col;
		
		do {
			if (gameOver()) return;
			row = rg.nextInt(5);
			col = rg.nextInt(5);
		} while (!labels[row][col].getText().equals("0"));
		
		val[row][col] = 2;
		
	}
	
	
	private boolean gameOver() {
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (labels[i][j].getText().equals("0"))
					return false;
			}
		}
		return true;
	}
	
	private boolean up() {
		
		boolean flag = false;
		
		for (int col = 0; col < 5; col++) {
			
			// Store continuous non-zero values to the array
			int valIndex = 0;
			for (int row = 0; row < 5; row++) {
				String value = labels[row][col].getText();
				if (!value.equals("0")) {
					val[valIndex][col] = Integer.parseInt(value);
					valIndex++;
				}
			}
			
			while (valIndex < 5) {
				val[valIndex][col] = 0;
				valIndex++;
			}
			
			// check if there are same val next to each other
			for (int m = 3; m >= 0; m--) {
				if (val[m][col] != 0 && val[m][col] == val[m+1][col]) {
					val[m][col] *= 2;
					for (int n = m+1; n < 4; n++) {
						val[n][col] = val[n+1][col];
					}
					val[4][col] = 0;
					flag = true; // has same, game not over yet!
					break;
				}	
			}
			
		}
		repaint();
		randomLabel();
		return flag; // false doesn't mean game over!
	}
	
	private boolean down() {
		boolean flag = false;
		
		for (int col = 0; col < 5; col++) {
			
			// Store continuous non-zero values to the array
			int valIndex = 4;
			for (int row = 4; row >= 0; row--) {
				String value = labels[row][col].getText();
				if (!value.equals("0")) {
					val[valIndex][col] = Integer.parseInt(value);
					valIndex--;
				}
			}
			
			while (valIndex >= 0) {
				val[valIndex][col] = 0;
				valIndex--;
			}
			
			// check if there are same val next to each other
			for (int m = 1; m <= 4; m++) {
				if (val[m][col] != 0 && val[m][col] == val[m-1][col]) {
					val[m][col] *= 2;
					for (int n = m-1; n > 0; n--) {
						val[n][col] = val[n-1][col];
					}
					val[0][col] = 0;
					
					flag = true; // has same, game not over yet!
					break;
				}	
			}
			
		}
		repaint();
		randomLabel();
		return flag; // false doesn't mean game over!
	}
	
	private boolean left() {
		boolean flag = false;
		
		for (int row = 0; row < 5; row++) {
			
			// Store continuous non-zero values to the array
			int valIndex = 0;
			for (int col = 0; col < 5; col++) {
				String value = labels[row][col].getText();
				if (!value.equals("0")) {
					val[row][valIndex] = Integer.parseInt(value);
					valIndex++;
				}
			}
			
			while (valIndex < 5) {
				val[row][valIndex] = 0;
				valIndex++;
			}
			
			// check if there are same val next to each other
			for (int m = 3; m >= 0; m--) {
				if (val[row][m] != 0 && val[row][m] == val[row][m+1]) {
					val[row][m] *= 2;
					for (int n = m+1; n < 4; n++) {
						val[row][n] = val[row][n+1];
					}
					val[row][4] = 0;
					flag = true; // has same, game not over yet!
					break;
				}	
			}
			
		}
		repaint();
		randomLabel();
		return flag; // false doesn't mean game over!
	}
	
	private boolean right() {
		boolean flag = false;
		
		for (int row = 0; row < 5; row++) {
			
			// Store continuous non-zero values to the array
			int valIndex = 4;
			for (int col = 4; col >= 0; col--) {
				String value = labels[row][col].getText();
				if (!value.equals("0")) {
					val[row][valIndex] = Integer.parseInt(value);
					valIndex--;
				}
			}
			
			while (valIndex >= 0) {
				val[row][valIndex] = 0;
				valIndex--;
			}
			
			// check if there are same val next to each other
			for (int m = 1; m <= 4; m++) {
				if (val[row][m] != 0 && val[row][m] == val[row][m-1]) {
					val[row][m] *= 2;
					for (int n = m-1; n > 0; n--) {
						val[row][n] = val[row][n-1];
					}
					val[row][0] = 0;
					
					flag = true; // has same, game not over yet!
					break;
				}	
			}
		}
		repaint();
		randomLabel();
		return flag; // false doesn't mean game over!
	}
	
	class myMouseListener implements MouseMotionListener, MouseListener {
		@Override
		public void mouseDragged(MouseEvent e) {
			
			
			x2 = e.getX();
			y2 = e.getY();
			
			int diffX = x2 - x1;
			int diffY = y2 - y1;
			
			if (diffX == 0) {
				if (diffY > 0) up();
				else down();
			} else if (diffY == 0) {
				if (diffX > 0) right();
				else left();
			} else {
				if (diffX > diffY) {
					if (diffX > 0) right();
					else left();
				} else {
					if (diffY > 0) up();
					else down();
				}
			}
		
			Random rg = new Random();
			int row = rg.nextInt(3);
			int col = rg.nextInt(3);
			labels[row][col].setText("2");
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
}
