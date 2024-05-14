package clientSide;

import objects.PanierObject;
import photos.ResizableImageLabel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class CommandeClient extends JFrame {
    static ArrayList<PanierObject> paniers;
    static double totalAmount;
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(237, 210, 133));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public CommandeClient(ArrayList<PanierObject> paniers, double totalAmount) {
        CommandeClient.totalAmount = totalAmount;
        System.out.println("Prix total: " + totalAmount);
        CommandeClient.paniers = paniers;
        System.out.println("Size is: " + paniers.size());
        for(PanierObject p : paniers){
            System.out.println(p.nomMenu);
        }

        setTitle("Resto universitaire - Mes Commandes");
        setSize(1200, 717);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200, 60);

        panel.setLayout(new BorderLayout());

        JLabel titleLabel = createTitleLabel();
        JScrollPane scrollPane = createScrollPane();
        JPanel buttonPanel = createButtonPanel();
        JLabel confirmationLabel = createConfirmationLabel();

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
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0,0));
        return titleLabel;
    }

    private JScrollPane createScrollPane() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        headerPanel.setBackground(Color.WHITE);
        JLabel articlesLabel = new JLabel("Articles");
        articlesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        articlesLabel.setBorder(BorderFactory.createEmptyBorder(0, 80,0, 0));
        JLabel etatLabel = new JLabel("Etat");
        etatLabel.setFont(new Font("Arial", Font.BOLD, 18));
        etatLabel.setBorder(BorderFactory.createEmptyBorder(0, 250, 0, 0)); 
        headerPanel.add(articlesLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(35, 0))); 
        headerPanel.add(etatLabel);
        listPanel.add(headerPanel);

        
        listPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        for (PanierObject panier : paniers) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEADING)); 
            infoPanel.setBackground(Color.WHITE);

            JLabel quantityLabel = new JLabel(panier.quantite + "x ");
            quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
            infoPanel.add(quantityLabel);

            ResizableImageLabel imageLabel = new ResizableImageLabel("src/photos/" + panier.nomPhoto + ".jpg", 80, 80);
            infoPanel.add(imageLabel);

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
            infoPanel.add(labelsPanel); 

            JPanel LivraisonStatusPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING)); 
            LivraisonStatusPanel.setBackground(Color.WHITE);
            LivraisonStatusPanel.setPreferredSize(new Dimension(200, 20));

            JLabel deliveryStatusLabel = new JLabel("Livré ou non Livré");

            deliveryStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
            LivraisonStatusPanel.add(deliveryStatusLabel); 
            infoPanel.add(Box.createRigidArea(new Dimension(120,0)));
            infoPanel.add(LivraisonStatusPanel);

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

    private JLabel createConfirmationLabel() {
        JLabel confirmationLabel = new JLabel("Avez-vous reçu votre commande ?", SwingConstants.CENTER);
        confirmationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        confirmationLabel.setBackground(new Color(237, 210, 133));
        confirmationLabel.setOpaque(true);
        return confirmationLabel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 40));
        buttonPanel.setBackground(new Color(237, 210, 133));

        JButton OuiButton = createButton("Oui");
        JButton NonButton = createButton("Non");

        Dimension buttonSize = new Dimension(160, 40);
        OuiButton.setPreferredSize(buttonSize);
        NonButton.setPreferredSize(buttonSize);

        buttonPanel.add(OuiButton);
        buttonPanel.add(NonButton);

        OuiButton.addActionListener(evt -> {
            this.setVisible(false);
            new Remerciement().setVisible(true);
        });

        NonButton.addActionListener(evt -> {
            this.setVisible(false);
            new Reclamation().setVisible(true);
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


}
