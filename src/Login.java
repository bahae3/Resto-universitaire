import javax.swing.*;
import java.awt.*;
import database.*;

public class Login extends JFrame {
    // Hadi dima diroha mnin tbghiw tssaybo chi frame jdida
    // Hadi hia li kat3ti dak couleur sfer
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(237, 210, 133));
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
        seConnecter.setForeground(Color.RED);

        JLabel email = new JLabel("E-mail:");
        email.setBounds(800, 220, 130, 25);

        JTextField emailData = new JTextField();
        emailData.setBounds(800, 250, 200, 25);


        JLabel mdp = new JLabel("Mot de passe:");
        mdp.setBounds(800, 300, 135, 25);

        JPasswordField mdpData = new JPasswordField();
        mdpData.setBounds(800, 330, 200, 25);

        JLabel loginError = new JLabel("");
        loginError.setForeground(Color.RED);
        loginError.setFont(new Font("Verdana", Font.ITALIC, 12));
        loginError.setBounds(801, 360, 200, 25);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setBounds(828, 390, 150, 30);
        loginButton.setBackground(new Color(60, 160, 240));
        loginButton.setForeground(Color.WHITE);

        JButton creerCompte = new JButton("S'inscrire");
        creerCompte.setBounds(828, 440, 150, 30);
        creerCompte.setBackground(new Color(60, 160, 240));
        creerCompte.setForeground(Color.WHITE);

        // Hna ghir 9adit l fonts dyal dok labels a buttons
        email.setFont(new Font("Verdana", Font.PLAIN, 19));
        mdp.setFont(new Font("Verdana", Font.PLAIN, 19));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        creerCompte.setFont(new Font("Arial", Font.PLAIN, 18));

        this.panel.add(bienvenue);
        this.panel.add(resto);
        this.panel.add(seConnecter);
        this.panel.add(email);
        this.panel.add(emailData);
        this.panel.add(mdp);
        this.panel.add(mdpData);
        this.panel.add(loginError);
        this.panel.add(loginButton);
        this.panel.add(creerCompte);

        this.setContentPane(this.panel);

        loginButton.addActionListener(evt -> {
            // Retrieving username and mdp
            String emailUser = emailData.getText();
            String mdpUser = new String(mdpData.getPassword());

            if (database.userLogin(emailUser, mdpUser)){
                setVisible(false);
                new Menu().setVisible(true);
            } else {
                loginError.setText("Email ou mot de passe incorrect.");
                emailData.setText("");
                mdpData.setText("");
            }
        });

        creerCompte.addActionListener(evt -> {
            // interface dyal email ghatmshi w ghatla3 dyal sign up
            setVisible(false);
            new Signup().setVisible(true);
        });

        this.setVisible(true);
    }
}
