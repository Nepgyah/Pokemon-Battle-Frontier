package gameInterface.redo;


import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import pokemon.Pokemon;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Auston
 */
public class MainMenu extends javax.swing.JFrame {

    CardLayout card;
    
    /*  List of all information to customize pokemon trainers
    TODO: Move this to a better spot
    */
    ArrayList<String> titles = new ArrayList<String>(Arrays.asList("Ace Trainer", "Fisherman", "Actor", "Actress", "Biker", "Office Worker"));
    ArrayList<String> regions = new ArrayList<String>(Arrays.asList("Kanto", "Jhoto", "Hoenn", "Sinnoh", "Unova", "Kalos", "Aloha", "Galar"));
    ImageIcon pikachu = new ImageIcon("pikachu.png");
    //ArrayList<Pokemon> pokelist;
    
    /*
     * Creates new form MainMenu
     */
    public MainMenu(ArrayList<Pokemon> pokedex) {
        initComponents();
     
        // Lable for MainMenu picture
        pictureLabel.setText("");
        pictureLabel.setIcon(pikachu);
        
        // Set beginning panel to mainMenu
        card = (CardLayout) contentPanel.getLayout();
        card.show(contentPanel, "menuCard");
        this.setLocationRelativeTo(null);
        
        // Panels for navigating main menu
        //contentPanel.add(new battlePanel(one, two), "battleCard");
        //contentPanel.add(new createTrainerPane(contentPanel, titles, regions, pokelist), "createTrainerCard");
        
        contentPanel.add(new pokedexPanel(contentPanel, pokedex), "pokedexCard");
        contentPanel.add(new movedexPanel(contentPanel), "movedexCard");
        // Populate allPokemonList
        /*
       
        
        // Populate trainerTitlesList
        for (String title : titles)
        {
            trainerTitleChoice.add(title);
        }
        
        // Populate trainerHometownList
        for (String region : regions)
        {
            trainerRegionChoice.add(region);
        }
        */
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        menuPane = new javax.swing.JPanel();
        battleButton = new javax.swing.JButton();
        menuLabel = new javax.swing.JLabel();
        createTrainerButton = new javax.swing.JButton();
        editTrainerButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pictureLabel = new javax.swing.JLabel();
        extrasLabel = new javax.swing.JLabel();
        movedexButton = new javax.swing.JButton();
        pokedexButton = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pokemon Battle Frontier");
        setResizable(false);

        contentPanel.setLayout(new java.awt.CardLayout());

        battleButton.setText("Battle");
        battleButton.setPreferredSize(new java.awt.Dimension(135, 40));
        battleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                battleButtonActionPerformed(evt);
            }
        });

        menuLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        menuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuLabel.setText("Main Menu");

        createTrainerButton.setText("Create A Trainer");
        createTrainerButton.setMaximumSize(new java.awt.Dimension(135, 40));
        createTrainerButton.setMinimumSize(new java.awt.Dimension(135, 40));
        createTrainerButton.setPreferredSize(new java.awt.Dimension(135, 40));
        createTrainerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTrainerButtonActionPerformed(evt);
            }
        });

        editTrainerButton.setText("View/Edit Trainers");
        editTrainerButton.setMaximumSize(new java.awt.Dimension(135, 40));
        editTrainerButton.setMinimumSize(new java.awt.Dimension(135, 40));
        editTrainerButton.setPreferredSize(new java.awt.Dimension(135, 40));

        quitButton.setText("Quit");
        quitButton.setPreferredSize(new java.awt.Dimension(135, 40));
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pokemon Battle Frontier");

        pictureLabel.setText("picture");

        extrasLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        extrasLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        extrasLabel.setText("Extras");

        movedexButton.setText("View Movedex");
        movedexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movedexButtonActionPerformed(evt);
            }
        });

        pokedexButton.setText("View Pokedex");
        pokedexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokedexButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPaneLayout = new javax.swing.GroupLayout(menuPane);
        menuPane.setLayout(menuPaneLayout);
        menuPaneLayout.setHorizontalGroup(
            menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPaneLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(pictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(quitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addComponent(editTrainerButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(createTrainerButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(battleButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(extrasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(menuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pokedexButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(movedexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuPaneLayout.setVerticalGroup(
            menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuPaneLayout.createSequentialGroup()
                        .addComponent(menuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(battleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createTrainerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTrainerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(extrasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pokedexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movedexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addComponent(pictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        contentPanel.add(menuPane, "menuCard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void battleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_battleButtonActionPerformed
        // TODO add your handling code here:
        card.show(contentPanel, "battleCard");
    }//GEN-LAST:event_battleButtonActionPerformed

    private void createTrainerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTrainerButtonActionPerformed
        // TODO add your handling code here:
        card.show(contentPanel, "createTrainerCard");
    }//GEN-LAST:event_createTrainerButtonActionPerformed

    private void movedexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movedexButtonActionPerformed
        // TODO add your handling code here:
        card.show(contentPanel, "movedexCard");
    }//GEN-LAST:event_movedexButtonActionPerformed

    private void pokedexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokedexButtonActionPerformed
        // TODO add your handling code here:
        card.show(contentPanel, "pokedexCard");
    }//GEN-LAST:event_pokedexButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton battleButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton createTrainerButton;
    private javax.swing.JButton editTrainerButton;
    private javax.swing.JLabel extrasLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel menuLabel;
    private javax.swing.JPanel menuPane;
    private javax.swing.JButton movedexButton;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JButton pokedexButton;
    private javax.swing.JButton quitButton;
    // End of variables declaration//GEN-END:variables

}
