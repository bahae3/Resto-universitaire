package clientSide;

import javax.swing.*;
import java.awt.*;

public class Remerciement extends JFrame {
    int idUser;
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(219, 219, 219));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public Remerciement(int idUser) {
        this.idUser = idUser;
        // style de ma fenetre
        setTitle("UnivEats - Bon Appetit");
        this.setBounds(200, 60, 1200, 717);
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(219, 219, 219));
        setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel.setLayout(null);

        panel.setBackground(Color.YELLOW);

        JLabel instruction = new JLabel("Bon Appetit :)");
        instruction.setBounds(500, 90, 620, 320);
        instruction.setFont(new Font("Verdana", Font.PLAIN, 36));
        panel.add(instruction);

        JLabel numero = new JLabel("Restaurant universitaire");
        numero.setBounds(450, 320, 360, 40);
        numero.setFont(new Font("Verdana", Font.PLAIN, 28));
        panel.add(numero);

        JButton menuButton = new JButton("Menu");
        menuButton.setBounds(465, 460, 125, 30);
        menuButton.setFont(new Font("Arial", Font.PLAIN, 18));
        menuButton.setBackground(new Color(60, 160, 240));
        menuButton.setForeground(Color.WHITE);

        JButton quitterButton = new JButton("Quitter");
        quitterButton.setBounds(630, 460, 125, 30);
        quitterButton.setFont(new Font("Arial", Font.PLAIN, 18));
        quitterButton.setBackground(new Color(60, 160, 240));
        quitterButton.setForeground(Color.WHITE);

        panel.add(menuButton);
        panel.add(quitterButton);

        setContentPane(panel);

        menuButton.addActionListener(evt -> {
            setVisible(false);
            new MenuClient(this.idUser);
            dispose();
        });

        quitterButton.addActionListener(evt -> System.exit(0));

        setVisible(true);
    }

}
