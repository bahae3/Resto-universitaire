import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class commandeP extends JFrame implements ActionListener {
    private JLabel titre;
    private JButton confirmerB, menuB, effacerB, totalB;
    private JPanel platSel; // plats li ghadi y'tséléctionnaw
    private double totalCost = 0;

    public commandeP() {
        setTitle("Votre Restaurant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400); // Ajustement de la taille pour le nouveau layout
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Titre 
        titre = new JLabel("Mon Panier", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        add(titre, BorderLayout.NORTH);

        // scroll fach kankhtaro bzaf d les plats non testé
        platSel = new JPanel();
        platSel.setLayout(new BoxLayout(platSel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(platSel);
        add(scrollPane, BorderLayout.CENTER);

        // bouttons w spaces li binhoum hadik menusoit n7youha soit ndiro chi méthode kat afficher lmenu f chi boite de dialogue z3ma ila nsa ma khtarch chi plat wla ndir chi bouton smitou prcédent
        JPanel bouttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        confirmerB = createButton("Confirmer");
        menuB = createButton("Menu");
        effacerB = createButton("Effacer");
        totalB = createButton("Total");

        bouttons.add(totalB); // Ajout du bouton Total
        bouttons.add(confirmerB);
        bouttons.add(menuB);
        bouttons.add(effacerB);

        add(bouttons, BorderLayout.SOUTH);
    }
//METHODE POUR creer l buttons bla man3awdo kola w7da bo7dha
    private JButton createButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBackground(new Color(75, 0, 130)); // 
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        return button;
    }

    // atomic integer houma l incremented counters ghadi ndirouha 3la wed les quantités 
    public void ajouterPlat(String nomPlat, double prixPlat, AtomicInteger quantite) {
        JPanel platInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nomLabel = new JLabel(nomPlat);
        JLabel prixLabel = new JLabel("- " + prixPlat + "€");
        JLabel quantiteLabel = new JLabel("Quantité: " + quantite);
        JButton plusB = new JButton("+");
        JButton moinsB = new JButton("-");

        
        plusB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantite.incrementAndGet();
                quantiteLabel.setText("Quantité: " + quantite);
                totalCost += prixPlat; // listeners z3ma la zdna chi quantité mn lplat katzad f total li ghadi yt3tahflkher
            }
        });

        moinsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantite.get() > 0) {
                    quantite.decrementAndGet();
                    quantiteLabel.setText("Quantité: " + quantite);
                    totalCost -= prixPlat; // same thing ms l3ks
                }
            }
        });

        platInfoPanel.add(nomLabel);
        platInfoPanel.add(prixLabel);
        platInfoPanel.add(quantiteLabel);
        platInfoPanel.add(plusB);
        platInfoPanel.add(moinsB);

        platSel.add(platInfoPanel);
        platSel.add(Box.createRigidArea(new Dimension(0, 10))); 
        platSel.revalidate();
        platSel.repaint(); 
    }

    @Override
    //chno kiw9e3 z3ma fach kit'clicka 3la chaque bouton
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmerB) {
            JOptionPane.showMessageDialog(this, "Confirmation effectuée !");
        } else if (e.getSource() == menuB) {
            JOptionPane.showMessageDialog(this, "Affichage du menu...");
        } else if (e.getSource() == effacerB) {
            int choice = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment effacer ?");
            if (choice == JOptionPane.YES_OPTION) {
                
                this.dispose();
                // n7lo lmenu ila ms7na 7it maghadi yb9a walo 
                MenuPage menuPage = new MenuPage();
                menuPage.setVisible(true);
            }
        } else if (e.getSource() == totalB) {
            JOptionPane.showMessageDialog(this, "Total: " + totalCost + "€");
        }
    }

    public void ajouterPlats(String platsSelectionnes, Map<String, Double> platPrixMap) {
        String[] plats = platsSelectionnes.split("\n");
        for (String plat : plats) {
            ajouterPlat(plat, platPrixMap.get(plat.split(" - ")[0]), new AtomicInteger(0));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            commandeP app = new commandeP();
            app.setVisible(true);
        });
    }
}
