import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;

public class myCalendar extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	JPanel panel;
	JButton[] buttons;
	JLabel[] labels;
	JTextArea firstText;
	Random rd;
	Border border;
	Calendar cal;
	String currentDate;
	int[] monthDate = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	int[] result = new int[2]; // result[0]=firstDayOfTheMonth, result[1]=month
	
	myCalendar() {
		rd = new Random();
		buttons = new JButton[42];
		labels = new JLabel[7];
		border = BorderFactory.createLineBorder(Color.white, 1);
		cal = Calendar.getInstance(); // Month starts from zero
		
		init();
	}
	
	private void init() {
		
		// JFrame initialization
		this.setSize(500, 350);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.pack();
		
		panel = new JPanel(); // JPanel does not have a layout
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.PINK);
		panel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(50,50,50,50);
		
		// First Line label
		Date now = cal.getTime();
		JLabel firstLabel = new JLabel(now.toString(), SwingConstants.CENTER);
		firstLabel.setPreferredSize(new Dimension(250, 30));
		firstLabel.setFont(new Font("Arial", Font.ITALIC, 15));
		firstLabel.setForeground(Color.BLACK);
		panel.add(firstLabel);
		
		// FirstLine TextArea
		firstText = new JTextArea(" 2015,01,01");
		firstText.setPreferredSize(new Dimension(110, 30));
		firstText.setFont(new Font("Arial", Font.BOLD, 20));
		firstText.setForeground(Color.BLACK);
		panel.add(firstText);
		
		// FirstLine Button to search Calendar
		JButton firstButton = new JButton("Click");
		firstButton.setPreferredSize(new Dimension(80, 30));
		firstButton.setActionCommand("click");
		firstButton.addActionListener(new buttonActionListener());
		panel.add(firstButton);
		
		// Display Sunday to Saturday
		for (int i = 0; i < 7; i++) {
			c.gridx = i;
			c.gridy = 1;
			labels[i] = new JLabel(days[i], SwingConstants.CENTER);
			labels[i].setPreferredSize(new Dimension(60, 30));
			//labels[i].setBorder(border);
			labels[i].setFont(new Font("Arial", Font.ITALIC, 20));
	
			panel.add(labels[i], c);
		}
		
		// Buttons to display dates
		for (int i = 0; i < 42; i++) {
			int r = rd.nextInt(256);
			int g = rd.nextInt(256);
			int b = rd.nextInt(256);
			buttons[i] = new JButton();
			buttons[i].setBackground(new Color(r,g,b));
			buttons[i].setPreferredSize(new Dimension(60, 30));
			buttons[i].setText("");
			panel.add(buttons[i]);
		}
		add(panel);
		
		// PaintCalendar depends on the currentDate
		paintCalendar(getCalendar(this.cal));
	}

	// 
	private void paintCalendar(int[] getCal) {
		int date = 1;
		int max = monthDate[getCal[1]];
		for (int i = 0; i < getCal[0]-1; i++) {
			buttons[i].setText("");
		}
		for (int i = getCal[0]-1; i < getCal[0]-1 + max; i++) {
			buttons[i].setText(date+"");
			date++;
		}
		for (int i = getCal[0]-1 + max; i < buttons.length; i++) {
			buttons[i].setText("");
		}
	}
	
	// getFirstday's day and the month number
	private int[] getCalendar(Calendar cal) {
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		
		int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
		int dayofWeek = cal.get(Calendar.DAY_OF_WEEK); // index starts from 1
		int dayofYear = cal.get(Calendar.DAY_OF_YEAR);
		
		int weekofYear = cal.get(Calendar.WEEK_OF_YEAR);
		int weekofMonth = cal.get(Calendar.WEEK_OF_MONTH);
		
		int hour       = cal.get(Calendar.HOUR);        // 12 hour clock
		int hourOfDay  = cal.get(Calendar.HOUR_OF_DAY); // 24 hour clock
		int minute     = cal.get(Calendar.MINUTE);
		int second     = cal.get(Calendar.SECOND);
		int millisecond= cal.get(Calendar.MILLISECOND);
		
		//date %= 7;
		int firstDate = 8; // date 1 and 8's day are same
		int firstdaysDay = (dayofWeek + (firstDate - date))%7;
		if (firstdaysDay <= 0) result[0] = firstdaysDay+7;
		else
			result[0] = firstdaysDay;
		result[1] = month;
		
		System.out.println(dayofWeek);
		return result;
	}
	
	private class buttonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String time = firstText.getText();
			time = time.trim();
			String[] t = time.split(",");
			paintCalendar(getCalendar(new GregorianCalendar(Integer.parseInt(t[0]),Integer.parseInt(t[1])-1,Integer.parseInt(t[2]),13,24,56)));
		}
		
	}
	
	
	public static void main(String[] args) {
		System.out.println((-5+7)%7);
		new myCalendar().setVisible(true);
	}

}
