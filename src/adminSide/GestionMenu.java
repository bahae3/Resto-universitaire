package adminSide;

import database.database;
import objects.MenuObject;
import photos.ResizableImageLabel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GestionMenu extends JFrame {
    ArrayList<MenuObject> menu = database.selectMenu(0);
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(219, 219, 219));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public GestionMenu() {
        setTitle("Gestion de menu");
        setBounds(200, 60, 1200, 717);
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

        for (MenuObject m : menu) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            infoPanel.setBackground(Color.WHITE);


            ResizableImageLabel imageLabel = new ResizableImageLabel("src/photos/" + m.nomPhoto + ".jpg", 80, 80);

            infoPanel.add(imageLabel);

            JLabel itemNameLabel = new JLabel(m.nom);
            itemNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

            JLabel itemPriceLabel = new JLabel(m.prix + " MAD");
            itemPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
            labelsPanel.add(itemNameLabel);
            labelsPanel.add(itemPriceLabel);
            labelsPanel.setBackground(Color.WHITE);
            infoPanel.add(labelsPanel);

            JButton modifyButton = createButton("Modifier", new Color(0, 128, 0));
            infoPanel.add(modifyButton);
            modifyButton.addActionListener(evt -> {
                setVisible(false);
                // Modifier plat avec son id
                new ModifierPlat(m, m.idMenu);
                dispose();
            });

            JButton deleteButton = createButton("Supprimer", new Color(255, 0, 0));
            infoPanel.add(deleteButton);
            deleteButton.addActionListener(evt -> {
                if (database.deleteItem(m.idMenu)){
                    System.out.println("Item deleted");
                    setVisible(false);
                    new GestionMenu();
                    dispose();
                }
            });

            itemPanel.add(infoPanel, BorderLayout.CENTER);

            listPanel.add(itemPanel);
            listPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.WHITE);

        Border border = BorderFactory.createLineBorder(new Color(219, 219, 219), 40);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        return scrollPane;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 40));
        buttonPanel.setBackground(new Color(219, 219, 219));

        JButton addButton = createButton("Ajouter un plat", new Color(60, 160, 240));
        addButton.addActionListener(evt -> {
            setVisible(false);
            new AjouterPlat();
            dispose();
        });

        JButton deliveryButton = createButton("Livraisons", new Color(60, 160, 240));
        deliveryButton.addActionListener(evt -> {
            setVisible(false);
            new GestionDeLivraison();
            dispose();
        });

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

//    public static void main(String[] args) {
//        new GestionMenu();
//    }
}