import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;


public class Questions implements ActionListener{
	
	static HashMap<String, String> dic = new HashMap<String, String>();
	static Set<String> keys;
	static Frame frame;
	static Button[] buttons;
	int index = 0;
	String question = "";
	
	public static void main(String[] args) {
		Questions obj = new Questions();
	}
	
	Questions() {
		SaveQtoSet("TestQuestions.txt");
		getWindow();
	}
	
	public void getWindow() {
		
		frame = new Frame();
		frame.setBackground(Color.GREEN);
		frame.setSize(1000, 1000);
		frame.setLayout(new GridLayout(keys.size(), 2, 2, 5));
		frame.setVisible(true);
		Iterator<String> itr = keys.iterator();
		buttons = new Button[keys.size()+1];
		buttons[0] = new Button("Answers here!");
		for (int i = 1;i <= keys.size(); i++) {
			String question =  itr.next();
			buttons[i] = new Button(question);
			frame.add(buttons[i]);
			
			index = i;
			this.question = question;
			buttons[i].addActionListener(this);
		}
		
	}
	
	public void Game() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to the Quiz!\n");
		if (dic != null) {
			Set<String> keys = dic.keySet();
			
			for (String key:keys) {
				System.out.println(key);
				String answer = dic.get(key);
				String str = scan.nextLine();
				System.out.println(answer);
				System.out.println(str);
			}
			
		} else {
			System.out.println("Sorry, quiz is not ready yet!");
		}
		
		scan.close();
	}
	
	public void PrintDic() {
		
		if (dic != null) {
			// Set<String> keys = dic.keySet();
			
			for (String key:keys) {
				System.out.println(key);
				String answer = dic.get(key);
				System.out.println(answer);
			}	
		}
	}
	
	@SuppressWarnings("resource")
	public static void SaveQtoSet(String filename) {
		
		
		try {
			// Initialize the Scanner
			File file= new File("/Users/aiying/Documents/workspace/QuizJP/src/"+filename);
			file.exists();
			
			Scanner scan = new Scanner(file);
			
			
			String key = "", value = "";
			
			if (!scan.hasNextLine()) {
				System.out.println("File is empty!");
				return;
			}
			
			// Read file
			String str = scan.nextLine();
			do {
				
				if (str.startsWith("#")) {
					StringBuilder sb1 = new StringBuilder();
					
					while (!str.startsWith("Answer:")) {
						sb1.append(str);
						if (scan.hasNextLine())
							str = scan.nextLine();
						else {
							value = "This Q doesn't have answer!";
							break;
						}
					}
					
					key = sb1.toString();
				} 
				
				if (str.startsWith("Answer")) {
					StringBuilder sb2 = new StringBuilder();
					
					while (!str.startsWith("#")) {
						sb2.append(str);
						if (scan.hasNextLine());
						if (scan.hasNextLine())
							str = scan.nextLine();
						else 
							break;
					}
					value = sb2.toString();
				}
				
				dic.put(key, value);
				
			} while (scan.hasNextLine());
			
			// Save all the keys
			keys = dic.keySet();
			// Close Scanner
			scan.close();
		

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 	
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		buttons[index].setLabel(dic.get(question));
		
	}
	
}