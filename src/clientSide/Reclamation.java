package clientSide;

import javax.swing.*;
import java.awt.*;

public class Reclamation extends JFrame {
    int idUser;
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(237, 210, 133));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public Reclamation(int idUser) {
        this.idUser = idUser;
        //style de ma fenetre
        setTitle("Resto universitaire - Reclamation");
        this.setBounds(200, 60, 1200, 717);
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(255, 255, 153));
        setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel.setLayout(null);


        panel.setBackground(Color.YELLOW);


        JLabel instruction = new JLabel("Veuillez appeler ce numéro pour réclamer");
        instruction.setBounds(370, 100, 620, 320);
        instruction.setFont(new Font("Verdana", Font.PLAIN, 22));
        panel.add(instruction);


        JLabel numero = new JLabel("+212 6 00 00 00 00");
        numero.setBounds(500, 340, 250, 40);
        numero.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel.add(numero);


        JButton menuButton = new JButton("Menu");
        menuButton.setBounds(465, 460, 125, 30);
        menuButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        menuButton.setBackground(new Color(60, 160, 240));
        menuButton.setForeground(Color.WHITE);

        JButton quitterButton = new JButton("Quitter");
        quitterButton.setBounds(630, 460, 125, 30);
        quitterButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        quitterButton.setBackground(new Color(60, 160, 240));
        quitterButton.setForeground(Color.WHITE);

        panel.add(menuButton);
        panel.add(quitterButton);

        menuButton.addActionListener(evt -> {
            setVisible(false);
            new MenuClient(this.idUser);
        });

        quitterButton.addActionListener(evt -> System.exit(0));

        setContentPane(panel);


        setVisible(true);
    }

}
