import javax.swing.*;
import java.awt.*;

public class Login extends JFrame{
    JPanel panel = new JPanel();

    public Login(){
        this.setTitle("Resto universitaire - Se connecter");
        this.setBounds(200, 60, 1200, 717);
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(255, 255, 153));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel.setLayout(null);

        // Partie a gauche
        JLabel bienvenue = new JLabel("Bienvenue . . .");
        bienvenue.setBounds(120, 250, 400, 40);
        Font logoFontBvn = new Font("Lucida Handwriting", Font.BOLD, 42);
        bienvenue.setFont(logoFontBvn);
        bienvenue.setForeground(new Color(100, 100, 255));

        JLabel resto = new JLabel("Resto universitaire");
        resto.setBounds(120, 305, 400, 40);
        Font logoFontResto = new Font("Segoe Script", Font.PLAIN, 38);
        resto.setFont(logoFontResto);
        resto.setForeground(new Color(100, 100, 255));

        // Partie a droite
        JLabel seConnecter = new JLabel("Veuillez s'identifier");
        seConnecter.setBounds(725, 160, 400, 30);
        Font logoFontConn = new Font("Times New Roman", Font.BOLD, 38);
        seConnecter.setFont(logoFontConn);
        seConnecter.setForeground(Color.red);

        JLabel login = new JLabel("E-mail");
        login.setBounds(800, 220, 130, 25);

        JTextField loginTf = new JTextField();
        loginTf.setBounds(800, 250, 175, 25);

        JLabel password = new JLabel("Mot de passe");
        password.setBounds(800, 300, 130, 25);

        JPasswordField passwordTf = new JPasswordField();
        passwordTf.setBounds(800, 330, 175, 25);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setBounds(812, 380, 150, 30);
        loginButton.setBackground(new Color(60, 160, 240));
        loginButton.setForeground(Color.WHITE);

        JButton creerCompte = new JButton("S'inscrire");
        creerCompte.setBounds(812, 430, 150, 30);
        creerCompte.setBackground(new Color(60, 160, 240));
        creerCompte.setForeground(Color.WHITE);

        Font labelFont = login.getFont();
        login.setFont(new Font(labelFont.getName(), Font.PLAIN, 21));
        password.setFont(new Font(labelFont.getName(), Font.PLAIN, 21));
        loginButton.setFont(new Font(labelFont.getName(), Font.PLAIN, 18));
        creerCompte.setFont(new Font(labelFont.getName(), Font.PLAIN, 18));

        this.panel.add(bienvenue);
        this.panel.add(resto);
        this.panel.add(seConnecter);
        this.panel.add(login);
        this.panel.add(loginTf);
        this.panel.add(password);
        this.panel.add(passwordTf);
        this.panel.add(loginButton);
        this.panel.add(creerCompte);

        this.setContentPane(this.panel);

        this.setVisible(true);
    }
}
