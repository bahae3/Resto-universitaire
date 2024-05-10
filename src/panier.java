package monpanier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class panier2 extends JFrame {
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(237, 210, 133));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    public panier2() {
        setTitle("Panier");
        setSize(700, 630); 
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
        return titleLabel;
    }

    private JScrollPane createScrollPane() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE); 

        for (int i = 0; i < 3; i++) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEADING)); 
            itemPanel.setBackground(Color.WHITE); 
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0)); 

           
            JLabel quantityLabel = new JLabel(1 + "x ");
            quantityLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
            itemPanel.add(quantityLabel);

            
            JPanel squarePanel = new JPanel();
            squarePanel.setPreferredSize(new Dimension(80, 80)); 
            squarePanel.setBackground(Color.LIGHT_GRAY);
            itemPanel.add(squarePanel); 

            JLabel itemNameLabel = new JLabel("Nom Plat ");
            itemNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
            itemNameLabel.setBackground(Color.WHITE); 
            itemNameLabel.setOpaque(true); 
            itemNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JLabel itemPriceLabel = new JLabel("$prix"); 
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
            JButton minusButton = new JButton("-");
            minusButton.setBackground(Color.WHITE); 
            minusButton.setForeground(Color.BLACK); 

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
        
        String totalAmount = "****"; 

        
        JLabel totalLabel = new JLabel("Total à payer est : " + totalAmount + " MAD", SwingConstants.CENTER);
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
        new panier2();
    }
}
