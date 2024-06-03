package adminSide;

import objects.CommandeObject;
import photos.ResizableImageLabel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GestionDeLivraison extends JFrame {
    ArrayList<CommandeObject> commande;
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(219, 219, 219));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public GestionDeLivraison() {
        this.commande = database.database.selectCommande(0);

        System.out.println("Size is: " + commande.size());
        for (CommandeObject p : commande) {
            System.out.println(p.nomMenu);
        }

        setTitle("Gestion de livraisons");
        setSize(1200, 717);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200, 60);

        panel.setLayout(new BorderLayout());

        JLabel titleLabel = createTitleLabel();
        JScrollPane scrollPane = createScrollPane();
        JPanel buttonPanel = createButtonPanel();

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setVisible(true);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Gestion de commandes - livraisons", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        return titleLabel;
    }

    private JScrollPane createScrollPane() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel articlesLabel = new JLabel("Articles", SwingConstants.CENTER);
        articlesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        listPanel.add(articlesLabel);

        JLabel etatLabel = new JLabel("Etat", SwingConstants.CENTER);
        etatLabel.setFont(new Font("Arial", Font.BOLD, 18));
        listPanel.add(etatLabel);

        for (CommandeObject commande : commande) {
            System.out.println("Etat est : " + commande.etatLivraison);
            JPanel articlePanel = createArticlePanel(commande.quantite, commande.nomMenu, commande.prix);
            JPanel etatPanel = createEtatPanel(commande.idCommande, commande.numCommande, commande.etatLivraison);

            Border border = BorderFactory.createLineBorder(Color.BLACK);
            articlePanel.setBorder(border);
            etatPanel.setBorder(border);

            listPanel.add(articlePanel);
            listPanel.add(etatPanel);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.WHITE);

        Border border = BorderFactory.createLineBorder((new Color(219, 219, 219)), 40);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        return scrollPane;
    }

    private JPanel createArticlePanel(int qtt, String nom, double prix) {
        JPanel articlePanel = new JPanel(new GridBagLayout());
        articlePanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel quantityLabel = new JLabel(qtt + "x");
        articlePanel.add(quantityLabel, gbc);

        JPanel squarePanel = new JPanel();
        squarePanel.setBackground(Color.WHITE);
        ResizableImageLabel imageLabel = new ResizableImageLabel("src/photos/" + nom + ".jpg", 80, 80);
        squarePanel.add(imageLabel);
        gbc.gridx = 1;
        articlePanel.add(squarePanel, gbc);

        JLabel itemNameLabel = new JLabel(nom);
        itemNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
        itemNameLabel.setBackground(Color.WHITE);
        itemNameLabel.setOpaque(true);
        itemNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 2;
        articlePanel.add(itemNameLabel, gbc);

        JLabel itemPriceLabel = new JLabel(prix + " MAD");
        itemPriceLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
        itemPriceLabel.setBackground(Color.WHITE);
        itemPriceLabel.setOpaque(true);
        itemPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 3;
        articlePanel.add(itemPriceLabel, gbc);

        return articlePanel;
    }

    private JPanel createEtatPanel(int idCommande, int numCommande, String etatLivraison) {
        JPanel etatPanel = new JPanel(new GridBagLayout());
        etatPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        String livreOuNon = "Commande n° " + numCommande + ": ";
//        String nonLivre = "Commande n° " + numCommande + ": ";

        JLabel deliveryStatusLabel = new JLabel(livreOuNon + etatLivraison);
        deliveryStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        etatPanel.add(deliveryStatusLabel, gbc);

        JButton changeStatusButton = new JButton("Changer l'état");
        changeStatusButton.setFont(new Font("Arial", Font.BOLD, 14));
        changeStatusButton.setBackground(new Color(60, 160, 240));
        changeStatusButton.setForeground(Color.WHITE);

        // Hna dir disable button ila kant commande livre
        if (etatLivraison.equals("Livré")) {
            changeStatusButton.setEnabled(false);
        } else {

            changeStatusButton.addActionListener(evt -> {
                if (deliveryStatusLabel.getText().equals(livreOuNon + "Non Livré")) {
                    if (database.database.modifierEtatCommande(idCommande)) {
                        setVisible(false);
                        new GestionDeLivraison();
                        dispose();
                        deliveryStatusLabel.setText(livreOuNon + "Livré");
                        changeStatusButton.setEnabled(false);
                    }
                } else {
                    deliveryStatusLabel.setText(livreOuNon);
                }

            });
        }

        gbc.gridy = 1;
        etatPanel.add(changeStatusButton, gbc);

        JButton deleteCommande = new JButton("Supprimer");
        deleteCommande.setFont(new Font("Arial", Font.BOLD, 14));
        deleteCommande.setBackground(Color.red);
        deleteCommande.setForeground(Color.WHITE);
        System.out.println(deliveryStatusLabel.getText());
        if (!deliveryStatusLabel.getText().equals(livreOuNon + "Livré")) {
            deleteCommande.setEnabled(false);
        } else {
            deleteCommande.addActionListener(evt -> {
                // Delet the commande here
                if (database.database.deleteCommande(idCommande)) {
                    System.out.println("Commande supprimeee");
                    setVisible(false);
                    new GestionDeLivraison();
                    dispose();
                }
            });
        }

        gbc.gridy = 2;
        etatPanel.add(deleteCommande, gbc);

        return etatPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 40));
        buttonPanel.setBackground(new Color(219, 219, 219));

        JButton returnToMenuButton = createButton();

        Dimension buttonSize = new Dimension(160, 40);
        returnToMenuButton.setPreferredSize(buttonSize);

        returnToMenuButton.addActionListener(evt -> {
            this.setVisible(false);
            new GestionMenu();
            dispose();
        });

        buttonPanel.add(returnToMenuButton);

        return buttonPanel;
    }

    private JButton createButton() {
        JButton button = new JButton("Retour au menu");
        button.setBackground(new Color(60, 160, 240));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

//    public static void main(String[] args) {
//        new GestionDeLivraison();
//    }
}