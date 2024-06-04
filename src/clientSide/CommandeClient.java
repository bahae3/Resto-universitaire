package clientSide;

import objects.CommandeObject;
import photos.ResizableImageLabel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class CommandeClient extends JFrame {
    int idUser;
    ArrayList<CommandeObject> commande;
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(219, 219, 219));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public CommandeClient(int idUser) {
        this.idUser = idUser;
        this.commande = database.database.selectCommande(this.idUser);

        setTitle("Resto universitaire - Mes Commandes");
        setSize(1200, 717);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200, 60);

        panel.setLayout(new BorderLayout());

        JLabel titleLabel = createTitleLabel();
        JScrollPane scrollPane = createScrollPane();
        JLabel confirmationLabel = createConfirmationLabel();
        JPanel buttonPanel = createButtonPanel();

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(confirmationLabel, BorderLayout.SOUTH);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setVisible(true);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Mes Commandes", SwingConstants.CENTER);
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
            JPanel articlePanel = createArticlePanel(commande.quantite, commande.nomMenu, commande.prix);
            JPanel etatPanel = createEtatPanel(commande.etatLivraison);

            Border border = BorderFactory.createLineBorder(Color.BLACK);
            articlePanel.setBorder(border);
            etatPanel.setBorder(border);

            listPanel.add(articlePanel);
            listPanel.add(etatPanel);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.WHITE);

        Border border = BorderFactory.createLineBorder(new Color(219, 219, 219), 40);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        return scrollPane;
    }

    private JLabel createConfirmationLabel() {
        JLabel confirmationLabel = new JLabel("Avez-vous reçu votre/vos commande(s) ?", SwingConstants.CENTER);
        confirmationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        confirmationLabel.setBackground(new Color(219, 219, 219));
        confirmationLabel.setOpaque(true);
        confirmationLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        return confirmationLabel;
    }

    private JPanel createArticlePanel(int qtt, String nomMenu, double prix) {
        JPanel articlePanel = new JPanel(new GridBagLayout());
        articlePanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel quantityLabel = new JLabel(qtt + "x");
        articlePanel.add(quantityLabel, gbc);

//        JPanel squarePanel = new JPanel();
        ResizableImageLabel imageLabel = new ResizableImageLabel("src/photos/" + nomMenu + ".jpg", 80, 80);
//        infoPanel.add(imageLabel);
//        squarePanel.setPreferredSize(new Dimension(80, 80));
//        squarePanel.setBackground(Color.LIGHT_GRAY);
        gbc.gridx = 1;
        articlePanel.add(imageLabel, gbc);

        JLabel itemNameLabel = new JLabel(nomMenu);
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

    private JPanel createEtatPanel(String etatLivraison) {
        JPanel etatPanel = new JPanel(new GridBagLayout());
        etatPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel deliveryStatusLabel = new JLabel(etatLivraison);
        deliveryStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        etatPanel.add(deliveryStatusLabel, gbc);

        return etatPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 40));
        buttonPanel.setBackground(new Color(219, 219, 219));

        JButton ouiButton = createButton("Oui");
        JButton nonButton = createButton("Non");

        Dimension buttonSize = new Dimension(160, 40);
        ouiButton.setPreferredSize(buttonSize);
        nonButton.setPreferredSize(buttonSize);

        buttonPanel.add(ouiButton);
        buttonPanel.add(nonButton);

        return buttonPanel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(60, 160, 240));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

    public static void main(String[] args) {
        new CommandeClient(1);
    }
}