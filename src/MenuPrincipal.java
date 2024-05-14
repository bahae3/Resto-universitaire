package monpanier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(237, 210, 133));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public MenuPrincipal() {
        setTitle("Panier");
        setSize(700, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(200, 60);

        panel.setLayout(new BorderLayout());

        JLabel titleLabel = createTitleLabel();
        JPanel navBarPanel = createNavBar();
        JScrollPane scrollPane = createScrollPane();
        JPanel buttonPanel = createButtonPanel();

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(navBarPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setVisible(true);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Menu Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        return titleLabel;
    }

    private JPanel createNavBar() {
        JPanel navBarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        String[] buttonLabels = {"Plat du jour", "Entrées", "Boissons", "Desserts", "Plats Principaux", "Menus spéciaux"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(150, 40));
            button.setBackground(new Color(237, 210, 133));
            navBarPanel.add(button);
        }

        return navBarPanel;
    }

    private JScrollPane createScrollPane() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        for (int i = 0; i < 3; i++) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));

            JPanel squarePanel = new JPanel();
            squarePanel.setPreferredSize(new Dimension(80, 80));
            squarePanel.setBackground(Color.LIGHT_GRAY);
            itemPanel.add(squarePanel);

            JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
            labelsPanel.setBackground(Color.WHITE);
            labelsPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
            itemPanel.add(labelsPanel);

            JLabel itemNameLabel = new JLabel("Nom Plat ");
            itemNameLabel.setOpaque(true);
            itemNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            itemNameLabel.setBackground(Color.WHITE);
            labelsPanel.add(itemNameLabel);

            JLabel itemPriceLabel = new JLabel("$prix");
            itemPriceLabel.setOpaque(true);
            itemPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            itemPriceLabel.setBackground(Color.WHITE);
            labelsPanel.add(itemPriceLabel);

            JPanel buttonPanel = new JPanel(new BorderLayout());
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));

            JButton plusButton = new JButton("+");
            plusButton.setPreferredSize(new Dimension(40, 40));
            plusButton.setBackground(Color.WHITE);
            plusButton.setForeground(Color.BLACK);
            plusButton.setFont(new Font("Arial", Font.BOLD, 16));
            plusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            buttonPanel.add(plusButton, BorderLayout.WEST);

            JButton minusButton = new JButton("-");
            minusButton.setPreferredSize(new Dimension(40, 40));
            minusButton.setBackground(Color.WHITE);
            minusButton.setForeground(Color.BLACK);
            minusButton.setFont(new Font("Arial", Font.BOLD, 16));
            minusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            buttonPanel.add(minusButton, BorderLayout.CENTER);

            JLabel quantityLabel = new JLabel(1 + "x ");
            quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
            buttonPanel.add(quantityLabel, BorderLayout.EAST);

            itemPanel.add(buttonPanel);

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

        JButton PanierButton = createButton("Panier");

        Dimension buttonSize = new Dimension(160, 40);
        PanierButton.setPreferredSize(buttonSize);

        buttonPanel.add(PanierButton);

        return buttonPanel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(75, 0, 130));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
