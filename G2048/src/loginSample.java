import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class loginSample implements ActionListener {

	private JFrame loginFrame = new JFrame("Encrypted Chat - Login");
    private JPanel pnl = new JPanel();
    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField userField;
    private JTextField passField;
    private JButton login;
    private JButton closeLogin;
    private JButton help;

    public loginSample() {
        userLabel = new JLabel("Username: ");
        passLabel = new JLabel("Password: ");
        userField = new JTextField(16);
        passField = new JTextField(16);
        login = new JButton("Login");
        login.setActionCommand("login");
        login.setMnemonic(KeyEvent.VK_L);
        closeLogin = new JButton("Close");
        closeLogin.setActionCommand("closeLogin");
        closeLogin.setMnemonic(KeyEvent.VK_E);
        help = new JButton("Help");
        help.setActionCommand("helpLogin");
        help.setMnemonic(KeyEvent.VK_H);
        login.addActionListener(this);
        closeLogin.addActionListener(this);
        help.addActionListener(this);
        pnl.add(userLabel);
        pnl.add(userField);
        pnl.add(passLabel);
        pnl.add(passField);
        pnl.add(login);
        pnl.add(help);
        pnl.add(closeLogin);
        loginFrame.add(pnl);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 125);
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                loginSample loginSample = new loginSample();
            }
        });
    }
}