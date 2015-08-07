import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class imageAutoScale {
	JFrame frame;
	JPanel panel;
	JButton searchButton;
	ImageIcon searchIcon;
	BufferedImage master;
	
	imageAutoScale() {
		
		EventQueue.invokeLater(new Runnable() {
			// /Users/aiying/Desktop/resources/search256.png
			@Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                try {
                    master = ImageIO.read(new File("/Users/aiying/Desktop/resources/search256.png"));

                    JButton btn = new JButton() {

                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension(90, 50);
                        }

                    };
                    btn.addComponentListener(new ComponentAdapter() {

                        @Override
                        public void componentResized(ComponentEvent e) {
                            JButton btn = (JButton) e.getComponent();
                            Dimension size = btn.getSize();
                            Insets insets = btn.getInsets();
                            size.width -= insets.left + insets.right;
                            size.height -= insets.top + insets.bottom;
                            if (size.width > size.height) {
                                size.width = -1;
                            } else {
                                size.height = -1;
                            }
                            Image scaled = master.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
                            btn.setIcon(new ImageIcon(scaled));
                        }

                    });

                    JFrame frame = new JFrame("Testing");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(btn);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (IOException exp) {
                    exp.printStackTrace();
                }
            }
        });
		
		
	}

	public static void main(String[] args) {
		new imageAutoScale();

	}

}
