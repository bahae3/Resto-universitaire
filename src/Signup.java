import javax.swing.*;
import java.awt.*;
import database.*;


public class Signup extends JFrame {
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(219, 219, 219));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public Signup() {
        this.setTitle("Resto universitaire - S'inscrire");
        this.setBounds(200, 60, 1200, 717);
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(219, 219, 219));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel.setLayout(null);

        JLabel prenom = new JLabel("Prénom:");
        prenom.setBounds(430, 90, 100, 25);

        JTextField prenomData = new JTextField();
        prenomData.setBounds(430, 120, 160, 25);

        JLabel nom = new JLabel("Nom:");
        nom.setBounds(610, 90, 100, 25);

        JTextField nomData = new JTextField();
        nomData.setBounds(610, 120, 160, 25);

        JLabel email = new JLabel("Email:");
        email.setBounds(430, 165, 100, 25);

        JTextField emailData = new JTextField();
        emailData.setBounds(430, 195, 340, 25);

        JLabel mdp = new JLabel("Mot de passe:");
        mdp.setBounds(430, 245, 140, 25);

        JPasswordField mdpData = new JPasswordField();
        mdpData.setBounds(430, 275, 340, 25);

        JLabel code = new JLabel("Code étudiant:");
        code.setBounds(430, 325, 140, 25);

        JTextField codeData = new JTextField();
        codeData.setBounds(430, 355, 340, 25);

        JButton signup = new JButton("S'inscrire");
        signup.setBounds(610, 405, 140, 30);
        signup.setBackground(new Color(60, 160, 240));
        signup.setForeground(Color.WHITE);

        JButton retour = new JButton("Retour");
        retour.setBounds(450, 405, 140, 30);
        retour.setBackground(new Color(255, 0, 0));
        retour.setForeground(Color.WHITE);

        JLabel errorMessage = new JLabel("");
        errorMessage.setBounds(390,455 ,500, 25);
        errorMessage.setForeground(Color.RED);

        prenom.setFont(new Font("Verdana", Font.PLAIN, 18));
        nom.setFont(new Font("Verdana", Font.PLAIN, 18));
        email.setFont(new Font("Verdana", Font.PLAIN, 18));
        mdp.setFont(new Font("Verdana", Font.PLAIN, 18));
        code.setFont(new Font("Verdana", Font.PLAIN, 18));
        signup.setFont(new Font("Arial", Font.PLAIN, 18));
        retour.setFont(new Font("Arial", Font.PLAIN, 18));
        errorMessage.setFont(new Font("Arial", Font.PLAIN, 18));

        this.panel.add(prenom);
        this.panel.add(prenomData);
        this.panel.add(nom);
        this.panel.add(nomData);
        this.panel.add(email);
        this.panel.add(emailData);
        this.panel.add(mdp);
        this.panel.add(mdpData);
        this.panel.add(code);
        this.panel.add(codeData);
        this.panel.add(signup);
        this.panel.add(retour);
        this.panel.add(errorMessage);

        this.setContentPane(this.panel);

        signup.addActionListener(evt -> {
            // Retrieving username and password
            String prenomUser = prenomData.getText();
            String nomUser = nomData.getText();
            String emailUser = emailData.getText();
            String mdpUser = new String(mdpData.getPassword());
            Integer codeUser = Integer.valueOf(codeData.getText());
            if(database.userSignup(prenomUser, nomUser, emailUser, mdpUser, codeUser)){
                System.out.println("Data bien stockees.");
                setVisible(false);
                new Login().setVisible(true);
                dispose();
            } else {
                errorMessage.setText("Erreur. Peut-être cet email est déjà utilisé. Veuillez réessayer.");
            }

        });

        retour.addActionListener(evt -> {
            // interface dyal sign up ghatmshi w ghatla3 dyal login
            setVisible(false);
            new Login().setVisible(true);
            dispose();
        });

        this.setVisible(true);

    }
}
