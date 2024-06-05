import javax.swing.*;
import java.awt.*;

import adminSide.GestionMenu;
import clientSide.MenuClient;
import database.*;
import photos.ResizableImageLabel;

public class Login extends JFrame {
    // Hadi dima diroha mnin tbghiw tssaybo chi frame jdida
    // Hadi hia li kat3ti dak couleur gris
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(219, 219, 219));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public Login() {
        this.setTitle("UnivEats - Se connecter");
        this.setBounds(200, 60, 1200, 717);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel.setLayout(null);

        // Partie a gauche
        JLabel bienvenue = new JLabel("UnivEats");
        bienvenue.setBounds(180, 130, 400, 40);
        Font logoFontBvn = new Font("Lucida Handwriting", Font.BOLD, 42);
        bienvenue.setFont(logoFontBvn);
        bienvenue.setForeground(new Color(100, 100, 255));

        JLabel resto = new JLabel("Bienvenue . . .");
        resto.setBounds(180, 185, 400, 40);
        Font logoFontResto = new Font("Segoe Script", Font.PLAIN, 38);
        resto.setFont(logoFontResto);
        resto.setForeground(new Color(100, 100, 255));

        // Adding an image
        ResizableImageLabel imageLabel = new ResizableImageLabel("src/logo.jpg", 285, 285);
        imageLabel.setBounds(180, 250, 285, 285);
        this.panel.add(imageLabel);

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
                // 1 is true and 0 is false
                if(database.isAdmin(emailUser, mdpUser) == 1){
                    System.out.println("Admin logged in.");
                    setVisible(false);
                    new GestionMenu();
                    dispose();
                } else {
                    setVisible(false);
                    new MenuClient(database.getUserId(emailUser, mdpUser));
                    dispose();
                }
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
            dispose();
        });

        this.setVisible(true);
    }
}
