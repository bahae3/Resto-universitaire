package monpanier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GestionMenu extends JFrame {
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(237, 210, 133));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public GestionMenu() {
        setTitle("Gestion de menu");
        setSize(700, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(200, 60);

        panel.setLayout(new BorderLayout());

        JLabel titleLabel = createTitleLabel();
        JScrollPane scrollPane = createScrollPane();
        JPanel buttonPanel = createButtonPanel();

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setVisible(true);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Gestion de menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        return titleLabel;
    }

    private JScrollPane createScrollPane() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        for (int i = 0; i < 3; i++) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            infoPanel.setBackground(Color.WHITE);

            JPanel squarePanel = new JPanel();
            squarePanel.setPreferredSize(new Dimension(80, 80));
            squarePanel.setBackground(Color.LIGHT_GRAY);
            infoPanel.add(squarePanel);

            JLabel itemNameLabel = new JLabel("Nom plat ");
            itemNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JLabel itemPriceLabel = new JLabel("$prix");
            itemPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
            labelsPanel.add(itemNameLabel);
            labelsPanel.add(itemPriceLabel);
            labelsPanel.setBackground(Color.WHITE);
            infoPanel.add(labelsPanel);

            JButton modifyButton = createButton("Modifier", new Color(0, 0, 255));
            JButton deleteButton = createButton("Supprimer", new Color(255, 0, 0));
            infoPanel.add(modifyButton);
            infoPanel.add(deleteButton);

            itemPanel.add(infoPanel, BorderLayout.CENTER);

            listPanel.add(itemPanel);
            listPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.WHITE);

        Border border = BorderFactory.createLineBorder(new Color(237, 210, 133), 40);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        return scrollPane;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 40));
        buttonPanel.setBackground(new Color(237, 210, 133));

        JButton addButton = createButton("Ajouter un plat", new Color(75, 0, 130));
        JButton deliveryButton = createButton("Livraisons", new Color(75, 0, 130));

        Dimension buttonSize = new Dimension(160, 40);
        addButton.setPreferredSize(buttonSize);
        deliveryButton.setPreferredSize(buttonSize);

        buttonPanel.add(addButton);
        buttonPanel.add(deliveryButton);

        return buttonPanel;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

    public static void main(String[] args) {
        new GestionMenu();
    }
}
