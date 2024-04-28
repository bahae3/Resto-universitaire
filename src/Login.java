import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    // Hadi dima diroha mnin tbghiw tssaybo chi frame jdida
    // Hadi hia li kat3ti dak couleur sfer
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(255, 255, 153));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public Login() {
        this.setTitle("Resto universitaire - Se connecter");
        this.setBounds(200, 60, 1200, 717);
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
        Font logoFontConn = new Font("Cambria", Font.BOLD, 38);
        seConnecter.setFont(logoFontConn);
        seConnecter.setForeground(Color.RED); // Set foreground color to red

        JLabel login = new JLabel("E-mail");
        login.setBounds(800, 220, 130, 25);

        JTextField loginTf = new JTextField();
        loginTf.setBounds(800, 250, 200, 25);

        JLabel password = new JLabel("Mot de passe");
        password.setBounds(800, 300, 130, 25);

        JPasswordField passwordTf = new JPasswordField();
        passwordTf.setBounds(800, 330, 200, 25);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setBounds(828, 380, 150, 30);
        loginButton.setBackground(new Color(60, 160, 240));
        loginButton.setForeground(Color.WHITE);

        JButton creerCompte = new JButton("S'inscrire");
        creerCompte.setBounds(828, 430, 150, 30);
        creerCompte.setBackground(new Color(60, 160, 240));
        creerCompte.setForeground(Color.WHITE);

        // Hna ghir 9adit l fonts dyal dok labels a buttons
        login.setFont(new Font("Verdana", Font.PLAIN, 19));
        password.setFont(new Font("Verdana", Font.PLAIN, 19));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        creerCompte.setFont(new Font("Arial", Font.PLAIN, 18));

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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Retrieving username and password
                String username = loginTf.getText();
                String pwd = new String(passwordTf.getPassword());
            }
        });

        creerCompte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // interface dyal login ghatmshi w ghatla3 dyal sign up
                new Signup().setVisible(true);
                setVisible(false);
            }
        });

        this.setVisible(true);
    }
}
