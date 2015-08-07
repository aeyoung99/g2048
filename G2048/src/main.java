import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class main extends JFrame implements ActionListener{
	
	JButton[] buttons = new JButton[20];
	JLabel[] labels = new JLabel[20];

	public static void main(String[] args) {
		new main().setVisible(true);;
	}
	
	private main() {
		super("2048");
		setSize(150, 100); // 1024*765, 800*600
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton button = new JButton("New game");
		button.setActionCommand("click");
		button.addActionListener(this);
		
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("Menu");
		JMenuItem newItem = new JMenu("New Game");
		JMenuItem stopItem = new JMenuItem("Stop Game");
		JMenuItem calendar = new JMenuItem("Calendar");
		calendar.setActionCommand("calendar");
		calendar.addActionListener(this);
		JMenuItem closeItem = new JMenuItem("Exit");
		JMenuItem eraser = new JMenuItem("Eraser");
		eraser.setActionCommand("eraser");
		eraser.addActionListener(this);
		
		JMenuItem h2048 = new JMenuItem("2048");
		h2048.setActionCommand("2048");
		h2048.addActionListener(this);
		JMenuItem h4096 = new JMenuItem("Painter");
		h4096.setActionCommand("painter");
		h4096.addActionListener(this);
		
		newItem.add(h2048);
		newItem.add(h4096);
		newItem.add(eraser);
		
		file.add(newItem);
		file.add(stopItem);
		file.add(calendar);
		file.addSeparator();
		file.add(closeItem);
		
		bar.add(file);
		
		closeItem.addActionListener(this);
		
		add(button, BorderLayout.WEST);
		setJMenuBar(bar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		
		if (name.equalsIgnoreCase("click")) {
			System.out.println("clicked");
			new g2048().setVisible(true);
		} else if (name.equalsIgnoreCase("exit")) {
			System.out.println("closed");
			System.exit(0);
		} else if (name.equals("2048")) {
			System.out.println("2048");
			new g2048().setVisible(true);
		} else if (name.equals("painter")) {
			new Square().setVisible(true);
		} else if (name.equals("eraser")) {
			new eraser().setVisible(true);
		} else if (name.equals("calendar")) {
			new myCalendar().setVisible(true);
		}
			
	}
	
}
