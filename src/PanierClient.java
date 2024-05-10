import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

import objects.*;
import photos.ResizableImageLabel;

public class PanierClient extends JFrame {
    JPanel listPanel;
    ArrayList<PanierObject> paniers = new ArrayList<>();
    double totalAmount;
    JLabel totalLabel;
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(237, 210, 133));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public PanierClient() {
        totalAmount = 0;
        setTitle("Resto universitaire - Mon panier");
        setSize(1200, 717);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLocation(200, 60);

        panel.setLayout(new BorderLayout());

        JLabel titleLabel = createTitleLabel();
        JScrollPane scrollPane = createScrollPane();
        JPanel buttonPanel = createButtonPanel();
        JLabel totalLabel = createTotalLabel();


        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(totalLabel, BorderLayout.SOUTH);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setVisible(true);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Mon panier", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        return titleLabel;
    }

    private JScrollPane createScrollPane() {
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        ArrayList<MenuObject> items = new ArrayList<>();
        items.add(new MenuObject(1, "Pizza", "fiha lfrite", "src/photos/burger.jpg", 40.4));
        items.add(new MenuObject(1, "Pizza", "fiha lfrite", "src/photos/burger.jpg", 40.4));
        items.add(new MenuObject(2, "Tacos", "fiha lfrite", "src/photos/burger.jpg", 35));
        items.add(new MenuObject(1, "Pizza", "fiha lfrite", "src/photos/burger.jpg", 40.4));
        items.add(new MenuObject(2, "Tacos", "fiha lfrite", "src/photos/burger.jpg", 35));
        items.add(new MenuObject(3, "Panini", "fiha lfrite", "src/photos/burger.jpg", 12.5));
        items.add(new MenuObject(4, "Plat nugget", "fiha lfrite", "src/photos/burger.jpg", 30));
        items.add(new MenuObject(5, "Bocadillos", "fiha lfrite", "src/photos/burger.jpg", 7));


        // Hna kan3amer array fiha elements dyal panier mn menu

        for (MenuObject menu : items) {
            boolean found = false;
            for (PanierObject p : paniers) {
                if (p.idMenu == menu.idMenu) {
                    p.quantite++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                paniers.add(new PanierObject(menu.idMenu, 1, menu.nomPhoto, menu.nom, menu.prix));
            }
        }


        for (PanierObject panier : paniers) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));

            JLabel quantityLabel = new JLabel(panier.quantite + "x ");
            quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
            itemPanel.add(quantityLabel);

            ResizableImageLabel imageLabel = new ResizableImageLabel(panier.nomPhoto, 80, 80);
            itemPanel.add(imageLabel);

            JLabel itemNameLabel = new JLabel(panier.nomMenu);
            itemNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
            itemNameLabel.setBackground(Color.WHITE);
            itemNameLabel.setOpaque(true);
            itemNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

            JLabel itemPriceLabel = new JLabel(String.valueOf(panier.prix));
            itemPriceLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
            itemPriceLabel.setBackground(Color.WHITE);
            itemPriceLabel.setOpaque(true);
            itemPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
            labelsPanel.add(itemNameLabel);
            labelsPanel.add(itemPriceLabel);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setBackground(Color.WHITE);

            JButton plusButton = new JButton("+");
            plusButton.setBackground(Color.WHITE);
            plusButton.setForeground(Color.BLACK);
            plusButton.addActionListener(evt -> {
                panier.quantite++;
                totalAmount += panier.prix;
                quantityLabel.setText(panier.quantite + "x ");
                totalLabel.setText("Total à payer est : " + Math.round(totalAmount * 100.0) / 100.0 + " MAD");
            });

            JButton minusButton = new JButton("-");
            minusButton.setBackground(Color.WHITE);
            minusButton.setForeground(Color.BLACK);
            minusButton.addActionListener(evt -> {
                if (panier.quantite > 0) {
                    panier.quantite--;
                    quantityLabel.setText(panier.quantite + "x ");
                    totalAmount -= panier.prix;
                    totalLabel.setText("Total à payer est : " + Math.round(totalAmount * 100.0) / 100.0 + " MAD");
                }
            });

            buttonPanel.add(plusButton);
            buttonPanel.add(minusButton);

            itemPanel.add(labelsPanel);
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

    private JLabel createTotalLabel() {
        for (PanierObject p : paniers) {
            if (p.quantite > 1) {
                totalAmount += p.quantite * p.prix;
            } else {
                totalAmount += p.prix;
            }
        }

        totalLabel = new JLabel("Total à payer est : " + Math.round(totalAmount * 100.0) / 100.0 + " MAD", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        totalLabel.setBackground(new Color(237, 210, 133));
        totalLabel.setOpaque(true);

        return totalLabel;
    }


    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 40));
        buttonPanel.setBackground(new Color(237, 210, 133));

        JButton confirmerButton = createButton("Confirmer");
        JButton menuButton = createButton("Menu");
        JButton effacerButton = createButton("Effacer");


        Dimension buttonSize = new Dimension(160, 40);
        confirmerButton.setPreferredSize(buttonSize);
        menuButton.setPreferredSize(buttonSize);
        effacerButton.setPreferredSize(buttonSize);

        buttonPanel.add(confirmerButton);
        buttonPanel.add(menuButton);
        buttonPanel.add(effacerButton);

        confirmerButton.addActionListener(evt -> {

        });

        menuButton.addActionListener(evt -> {

        });

        effacerButton.addActionListener(evt -> {
            // The ArrayList panier will be cleared (empty)
            paniers.clear();

            // The list will be empty here and has the message in the variable emptyLabel
            // imad, sayeb position f center
            listPanel.removeAll();
            JLabel emptyLabel = new JLabel("Votre panier est vide. Cliquez sur Menu pour le remplir");
            listPanel.add(emptyLabel);
            listPanel.revalidate();
            listPanel.repaint();
        });

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
        new PanierClient();
    }
}
