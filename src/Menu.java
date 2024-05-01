import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(237, 210, 133));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public Menu(){
        this.setTitle("Resto universitaire - Menu");
        this.setBounds(200, 60, 1200, 717);
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(255, 255, 153));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel.setLayout(null);
        this.setVisible(true);
    }
}
