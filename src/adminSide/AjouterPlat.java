package adminSide;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import static database.database.insertMenuToDb;

public class AjouterPlat extends JFrame {
    JPanel mainPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(219, 219, 219));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    public AjouterPlat() {
        setTitle("Ajout d'un plat"); // Définit le titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Définit l'action de fermeture
        setBounds(200, 60, 1000, 600); // Définit la taille de la fenêtre
        setLocationRelativeTo(null); // Centre la fenêtre à l'écran
        mainPanel.setLayout(new BorderLayout()); // Utilise BorderLayout pour l'agencement

        // Crée le panneau du titre
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // Rend le fond transparent
        JLabel titleLabel = new JLabel("Ajout d'un plat");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Définit la police du titre
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centre le titre
        titlePanel.add(titleLabel);

        // Crée le panneau du formulaire
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Rend le fond transparent
        formPanel.setLayout(new GridBagLayout()); // Utilise GridBagLayout pour l'agencement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Définit les marges
        gbc.anchor = GridBagConstraints.WEST; // Aligne les composants à gauche

        Font labelFont = new Font("Arial", Font.PLAIN, 16); // Police pour les étiquettes
        Font textFieldFont = new Font("Arial", Font.PLAIN, 16); // Police pour les champs de texte

        gbc.gridwidth = 1;
        gbc.gridy = 0;

        // Ajoute l'étiquette et le champ de texte pour le nom du plat
        JLabel nameLabel = new JLabel("Nom du plat:");
        nameLabel.setFont(labelFont);
        gbc.gridx = 0;
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        nameField.setFont(textFieldFont);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Ajoute l'étiquette et le menu déroulant pour la catégorie
        gbc.gridy++;
        JLabel categoryLabel = new JLabel("Categorie:");
        categoryLabel.setFont(labelFont);
        gbc.gridx = 0;
        formPanel.add(categoryLabel, gbc);

        JComboBox<String> categoryComboBox = new JComboBox<>(new String[] {"Boissons", "Entrées", "Plats principaux", "Desserts", "Menus spéciaux"});
        categoryComboBox.setFont(textFieldFont);
        categoryComboBox.setPreferredSize(nameField.getPreferredSize()); // Assure la même taille que les autres champs
        categoryComboBox.setBackground(Color.WHITE); // Définit la couleur de fond en blanc
        gbc.gridx = 1;
        formPanel.add(categoryComboBox, gbc);

        // Ajoute l'étiquette et le champ de texte pour la description
        gbc.gridy++;
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(labelFont);
        gbc.gridx = 0;
        formPanel.add(descriptionLabel, gbc);

        JTextField descriptionField = new JTextField(15);
        descriptionField.setFont(textFieldFont);
        gbc.gridx = 1;
        formPanel.add(descriptionField, gbc);

        // Ajoute l'étiquette et le champ de texte pour le jour du plat
        gbc.gridy++;
        JLabel dayLabel = new JLabel("Jour du plat:");
        dayLabel.setFont(labelFont);
        gbc.gridx = 0;
        formPanel.add(dayLabel, gbc);

        JComboBox<String> dayComboBox = new JComboBox<>(new String[] {"Tous les jours", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"});
        dayComboBox.setFont(textFieldFont);
        dayComboBox.setPreferredSize(nameField.getPreferredSize()); // Assure la même taille que les autres champs
        dayComboBox.setBackground(Color.WHITE); // Définit la couleur de fond en blanc
        gbc.gridx = 1;
        formPanel.add(dayComboBox, gbc);

        // Ajoute l'étiquette et le champ de texte pour le prix
        gbc.gridy++;
        JLabel priceLabel = new JLabel("Prix:");
        priceLabel.setFont(labelFont);
        gbc.gridx = 0;
        formPanel.add(priceLabel, gbc);

        JTextField priceField = new JTextField(15);
        priceField.setFont(textFieldFont);
        gbc.gridx = 1;
        formPanel.add(priceField, gbc);

        // Crée le panneau des boutons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false); // Rend le fond transparent
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Centre les boutons
        Dimension buttonSize = new Dimension(160, 40); // Dimensions for the buttons

        JButton addButton = new JButton("Ajouter au menu");
        addButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addButton.setBackground(new Color(60, 160, 240));
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(buttonSize);

        addButton.addActionListener(evt -> {
            String nomPlat = nameField.getText();
            String categoriePlat = (String) categoryComboBox.getSelectedItem();
            String descriptionPlat = descriptionField.getText();
            String jourPlat = (String) dayComboBox.getSelectedItem();
            double prixPlat = Double.parseDouble(priceField.getText());

            int idCategorie = switch (Objects.requireNonNull(categoriePlat)) {
                case "Boissons" -> 1;
                case "Entrées" -> 2;
                case "Plats principaux" -> 3;
                case "Desserts" -> 4;
                case "Menus spéciaux" -> 5;
                default -> 0;
            };

            assert jourPlat != null;
            boolean insertion;
            if (jourPlat.equals("Tous les jours")){
                insertion = insertMenuToDb(nomPlat, idCategorie, descriptionPlat, null, prixPlat);
            } else {
                insertion = insertMenuToDb(nomPlat, idCategorie, descriptionPlat, jourPlat, prixPlat);
            }

            System.out.println("Nom du plat: " + nomPlat);
            System.out.println("Catégorie: " + categoriePlat);
            System.out.println("Description: " + descriptionPlat);
            System.out.println("Jour: " + jourPlat);
            System.out.println("Prix: " + prixPlat);

            if (insertion){
                System.out.println("Plat ajoute");
                setVisible(false);
                new GestionMenu();
            }
        });
        buttonsPanel.add(addButton);

        JButton returnButton = new JButton("Revenir au menu");
        returnButton.setFont(new Font("Arial", Font.PLAIN, 16));
        returnButton.setBackground(new Color(60, 160, 240));
        returnButton.setForeground(Color.WHITE);
        returnButton.setPreferredSize(buttonSize);

        returnButton.addActionListener(evt -> {
            setVisible(false);
            new GestionMenu();
        });
        buttonsPanel.add(returnButton);

        // Ajoute les composants au panneau principal
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true); // Rendre la fenêtre visible
    }

//    public static void main(String[] args) {
//        new AjoutPlat(); // Lance l'application
//    }
}
