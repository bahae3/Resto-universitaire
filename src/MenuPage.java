package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MenuPage extends JFrame implements ActionListener {
    private JLabel titre;
    private JButton confirmerB;
    private JCheckBox[] platCheckBoxes;
    private Map<String, Double> platPrixMap;

    public MenuPage() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       
        titre = new JLabel("Menu", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        add(titre, BorderLayout.NORTH);

        
        JPanel platPanel = new JPanel();
        platPanel.setLayout(new BoxLayout(platPanel, BoxLayout.Y_AXIS));

        //ex w safi 7ta nbdlohom
        platPrixMap = new HashMap<>();
        platPrixMap.put("Plat 1", 10.0);
        platPrixMap.put("Plat 2", 12.0);
        platPrixMap.put("Plat 3", 15.0);

        platCheckBoxes = new JCheckBox[platPrixMap.size()];
        int index = 0;
        for (String plat : platPrixMap.keySet()) {
            platCheckBoxes[index] = new JCheckBox(plat + " - " + platPrixMap.get(plat) + "€");
            platPanel.add(platCheckBoxes[index]);
            index++;
        }

        JScrollPane scrollPane = new JScrollPane(platPanel);
        add(scrollPane, BorderLayout.CENTER);

        // confirmer chno khtar
        confirmerB = new JButton("Confirmer");
        confirmerB.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(confirmerB);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmerB) {
            // kan recuperew chno khtar
            StringBuilder selectedPlats = new StringBuilder();
            for (JCheckBox platCheckBox : platCheckBoxes) {
                if (platCheckBox.isSelected()) {
                    selectedPlats.append(platCheckBox.getText()).append("\n");
                }
            }

            // ndozo lpage lkhra 
            commandeP commandePage = new commandeP();
            commandePage.ajouterPlats(selectedPlats.toString(), platPrixMap);
            commandePage.setVisible(true);
            dispose(); 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPage menuPage = new MenuPage();
            menuPage.setVisible(true);
        });
    }
}
