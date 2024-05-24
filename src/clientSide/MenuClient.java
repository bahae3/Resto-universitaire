package clientSide;

import database.database;
import objects.MenuObject;
import photos.ResizableImageLabel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MenuClient extends JFrame {
    ArrayList<MenuObject> menu = database.selectMenu(1);
    ArrayList<MenuObject> panier = new ArrayList<>();

    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(237, 210, 133));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public MenuClient() {
        this.setTitle("Resto universitaire - Menu");
        this.setBounds(200, 60, 1200, 717);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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


        JButton button1 = new JButton("Plat du jour");
        button1.setPreferredSize(new Dimension(150, 40));
        button1.setBackground(new Color(237, 210, 133));
        navBarPanel.add(button1);

        button1.addActionListener(evt -> {
//            menu = database.selectMenu(2);
        });

        JButton button2 = new JButton("Entrées");
        button2.setPreferredSize(new Dimension(150, 40));
        button2.setBackground(new Color(237, 210, 133));
        navBarPanel.add(button2);
        button2.addActionListener(evt -> {
            menu = database.selectMenu(2);
        });

        JButton button3 = new JButton("Boissons");
        button3.setPreferredSize(new Dimension(150, 40));
        button3.setBackground(new Color(237, 210, 133));
        navBarPanel.add(button3);
        button3.addActionListener(evt -> {
            menu = database.selectMenu(1);
        });

        JButton button4 = new JButton("Desserts");
        button4.setPreferredSize(new Dimension(150, 40));
        button4.setBackground(new Color(237, 210, 133));
        navBarPanel.add(button4);
        button4.addActionListener(evt -> {
            menu = database.selectMenu(4);
        });

        JButton button5 = new JButton("Plats Principaux");
        button5.setPreferredSize(new Dimension(150, 40));
        button5.setBackground(new Color(237, 210, 133));
        navBarPanel.add(button5);
        button5.addActionListener(evt -> {
            menu = database.selectMenu(3);
        });

        JButton button6 = new JButton("Menus spéciaux");
        button6.setPreferredSize(new Dimension(150, 40));
        button6.setBackground(new Color(237, 210, 133));
        navBarPanel.add(button6);
        button6.addActionListener(evt -> {
            createScrollPane();
            menu = database.selectMenu(5);
        });

        return navBarPanel;
    }

    private JScrollPane createScrollPane() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        for (MenuObject m : menu) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));

            JLabel quantityLabel = new JLabel(m.quantite + "x");
            quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
            itemPanel.add(quantityLabel, BorderLayout.WEST);

            ResizableImageLabel imageLabel = new ResizableImageLabel("src/photos/" + m.nomPhoto + ".jpg", 80, 80);
            itemPanel.add(imageLabel);

            JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
            labelsPanel.setBackground(Color.WHITE);
            labelsPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
            itemPanel.add(labelsPanel);

            JLabel itemNameLabel = new JLabel(m.nom);
            itemNameLabel.setOpaque(true);
            itemNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            itemNameLabel.setBackground(Color.WHITE);
            labelsPanel.add(itemNameLabel);

            JLabel itemPriceLabel = new JLabel(m.prix + " MAD");
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
            plusButton.addActionListener(evt -> {
                m.quantite++;
                quantityLabel.setText(m.quantite + "x");
                panier.add(m);
            });

            JButton minusButton = new JButton("-");
            minusButton.setPreferredSize(new Dimension(40, 40));
            minusButton.setBackground(Color.WHITE);
            minusButton.setForeground(Color.BLACK);
            minusButton.setFont(new Font("Arial", Font.BOLD, 16));
            minusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            buttonPanel.add(minusButton, BorderLayout.CENTER);
            minusButton.addActionListener(evt -> {
                m.quantite--;
                quantityLabel.setText(m.quantite + "x");
                panier.remove(m);
            });



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

        JButton panierButton = createButton("Panier");

        Dimension buttonSize = new Dimension(160, 40);
        panierButton.setPreferredSize(buttonSize);

        buttonPanel.add(panierButton);

        panierButton.addActionListener(evt -> {
            setVisible(false);
            new PanierClient(panier);
        });

        return buttonPanel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(60, 160, 240));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

}
