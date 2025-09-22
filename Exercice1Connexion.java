import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Exercice1Connexion{
    public static void main (String[] args)
    {
        // création de la fenetre principal
        JFrame frame = new JFrame("MasterMind Connexion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,200);
        frame.setLayout(new GridLayout(3,2,10,10));

        // création des composants
        JLabel labelUser = new JLabel("Utilisateur:");
        JTextField fieldUser = new JTextField();

        JLabel labelPassword = new JLabel("Mot de passe:");
        JPasswordField fieldPassword = new JPasswordField();

        JButton buttonLogin = new JButton("Connexion");
        JLabel labelMessage = new JLabel("");

        //ajout des composants a la fenetre
        frame.add(labelUser);
        frame.add(fieldUser);
        frame.add(labelPassword);
        frame.add(fieldPassword);
        frame.add(buttonLogin);
        frame.add(labelMessage);

        // Gestionnaire d'évenement du bouton
        buttonLogin.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String username = fieldUser.getText();
                String password = new String(fieldPassword.getPassword());

                if(username.equals("admin") && password.equals("1234"))
                {
                    labelMessage.setText("connexion réussie!");
                    labelMessage.setForeground(Color.GREEN);

                }else{
                    labelMessage.setText("Echec de connexion");
                    labelMessage.setForeground(Color.RED);
                }
            } 
        });

        // Centrer la fenetre et la rendre Visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}