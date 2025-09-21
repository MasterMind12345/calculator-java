import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatriceSimple extends JFrame implements ActionListener {
    private JTextField display;
    private double premierNombre, secondNombre;
    private String operation;
    private boolean nouvelleOperation, premiereOperation;

    public CalculatriceSimple() {
        setTitle("Calculatrice MasterMind"); 
        setSize(300, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); 
        
        setLayout(new BorderLayout()); 
        
        display = new JTextField(); 
        display.setEditable(false); 
        display.setFont(new Font("Arial", Font.BOLD, 24)); 
        add(display, BorderLayout.NORTH);
        
        // Panel des boutons
        JPanel panelBoutons = new JPanel(); 
        panelBoutons.setLayout(new GridLayout(4, 4, 5, 5)); 
        
        // Définition des boutons
        String[] boutons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+"  
        };
        
        for (String texte : boutons) {
            JButton bouton = new JButton(texte);
            bouton.setFont(new Font("Arial", Font.BOLD, 18));
            bouton.addActionListener(this);
            panelBoutons.add(bouton);
        }
        
        add(panelBoutons, BorderLayout.CENTER); 
        
        premiereOperation = true;
        nouvelleOperation = false;
        
        setVisible(true); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String commande = e.getActionCommand();
        
        try {
           
            if ((commande.charAt(0) >= '0' && commande.charAt(0) <= '9') || commande.equals(".")) {
                if (nouvelleOperation) {
                    display.setText(""); 
                    nouvelleOperation = false;
                }
                // Vérifie qu'il n'y a pas déjà un point décimal
                if (commande.equals(".") && display.getText().contains(".")) {
                    return; // Ne fait rien si on essaye d'ajouter un deuxième point
                }
                display.setText(display.getText() + commande); // Ajoute le caractère à l'affichage
            } 
            // Si c'est le bouton égal
            else if (commande.charAt(0) == '=') {
                if (operation != null && !operation.isEmpty()) {
                    secondNombre = Double.parseDouble(display.getText()); // Convertit l'affichage en nombre
                    calculer(); // Effectue le calcul
                    display.setText(String.valueOf(premierNombre)); // Affiche le résultat
                    operation = ""; // Réinitialise l'opération
                    nouvelleOperation = true; // Prépare pour une nouvelle opération
                }
            } 
            // Si c'est une opération (+, -, *, /)
            else {
                if (operation != null && !operation.isEmpty()) {
                    // Si une opération est déjà en cours, on calcule d'abord le résultat
                    secondNombre = Double.parseDouble(display.getText());
                    calculer();
                    display.setText(String.valueOf(premierNombre));
                } else {
                    premierNombre = Double.parseDouble(display.getText()); // Stocke le premier nombre
                }
                operation = commande; // Stocke l'opération
                nouvelleOperation = true; // Prépare pour la saisie du deuxième nombre
            }
        } catch (NumberFormatException ex) {
            display.setText("Erreur"); // En cas d'erreur de format
        }
    }
    
    // Méthode pour effectuer les calculs
    private void calculer() {
        switch (operation) {
            case "+":
                premierNombre = premierNombre + secondNombre; // Addition
                break;
            case "-":
                premierNombre = premierNombre - secondNombre; // Soustraction
                break;
            case "*":
                premierNombre = premierNombre * secondNombre; // Multiplication
                break;
            case "/":
                if (secondNombre != 0) {
                    premierNombre = premierNombre / secondNombre; // Division si le diviseur n'est pas zéro
                } else {
                    display.setText("Erreur"); // Gestion de la division par zéro
                    premierNombre = 0; // Réinitialise le résultat
                }
                break;
        }
    }
    
    // Méthode principale - point d'entrée de l'application
    public static void main(String[] args) {
        // Swing doit être exécuté dans un thread spécifique (EDT - Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> new CalculatriceSimple());
    }
}