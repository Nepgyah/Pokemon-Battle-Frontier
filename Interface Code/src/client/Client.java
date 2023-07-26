package client;


import gameInterface.*;
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
public class Client extends javax.swing.JFrame {

    CardLayout menuCard;
    CardLayout navCard;
    
    /*  List of all information to customize pokemon trainers
    TODO: Move this to a better spot
    */
    ImageIcon pikachu = new ImageIcon("pikachu.png");
    //ArrayList<Pokemon> pokelist;
    
    public Client() {
        initComponents();
     
        // Lable for MainMenu picture
        pictureLabel.setText("");
        pictureLabel.setIcon(pikachu);
        
        // panel settings for menuCard
        menuCard = (CardLayout) contentPanel.getLayout();
        menuCard.show(contentPanel, "grettingCard");
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
        contentPanel.add(new NewsPanel(contentPanel), "newsCard");
        contentPanel.add(new PokedexPanel(contentPanel), "pokedexCard");
        contentPanel.add(new MovedexPanel(contentPanel), "movedexCard");
        contentPanel.add(new CreateTrainerPanel(contentPanel), "createTrainerCard");
        contentPanel.add(new ViewEditTrainerPanel(contentPanel), "viewEditTrainerCard");
        contentPanel.add(new HallOfFamePanel(contentPanel), "HOFCard");

        // panel settings for navCard
   
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
        newsPanel = new javax.swing.JPanel();
        pictureLabel = new javax.swing.JLabel();
        newsLabel = new javax.swing.JLabel();
        topnavPanel = new javax.swing.JPanel();
        battleButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        sidenavPanel = new javax.swing.JPanel();
        createTrainerButton = new javax.swing.JButton();
        editTrainerButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        extrasLabel = new javax.swing.JLabel();
        movedexButton = new javax.swing.JButton();
        pokedexButton = new javax.swing.JButton();
        hallOfFameButton = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PokeClient");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        contentPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        contentPanel.setLayout(new java.awt.CardLayout());

        pictureLabel.setText("picture");

        newsLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        newsLabel.setText("Welcome to the Battle Frontier");

        javax.swing.GroupLayout newsPanelLayout = new javax.swing.GroupLayout(newsPanel);
        newsPanel.setLayout(newsPanelLayout);
        newsPanelLayout.setHorizontalGroup(
            newsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newsPanelLayout.createSequentialGroup()
                .addGroup(newsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(322, 322, 322))
        );
        newsPanelLayout.setVerticalGroup(
            newsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newsPanelLayout.createSequentialGroup()
                .addComponent(newsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(pictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addGap(108, 108, 108))
        );

        contentPanel.add(newsPanel, "menuCard");

        topnavPanel.setBackground(new java.awt.Color(153, 0, 0));

        battleButton.setText("Battle");
        battleButton.setPreferredSize(new java.awt.Dimension(135, 40));
        battleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                battleButtonActionPerformed(evt);
            }
        });

        homeButton.setText("Home");
        homeButton.setPreferredSize(new java.awt.Dimension(135, 40));
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pokemon Battle Frontier");

        javax.swing.GroupLayout topnavPanelLayout = new javax.swing.GroupLayout(topnavPanel);
        topnavPanel.setLayout(topnavPanelLayout);
        topnavPanelLayout.setHorizontalGroup(
            topnavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topnavPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(battleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topnavPanelLayout.setVerticalGroup(
            topnavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topnavPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(topnavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(battleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        sidenavPanel.setBackground(new java.awt.Color(0, 51, 153));

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
        editTrainerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTrainerButtonActionPerformed(evt);
            }
        });

        quitButton.setText("Quit");
        quitButton.setPreferredSize(new java.awt.Dimension(135, 40));
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        extrasLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        extrasLabel.setForeground(new java.awt.Color(255, 255, 255));
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

        hallOfFameButton.setText("Hall Of Fame");
        hallOfFameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallOfFameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidenavPanelLayout = new javax.swing.GroupLayout(sidenavPanel);
        sidenavPanel.setLayout(sidenavPanelLayout);
        sidenavPanelLayout.setHorizontalGroup(
            sidenavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidenavPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidenavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidenavPanelLayout.createSequentialGroup()
                        .addGroup(sidenavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editTrainerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pokedexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidenavPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(sidenavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createTrainerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(extrasLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(movedexButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hallOfFameButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        sidenavPanelLayout.setVerticalGroup(
            sidenavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidenavPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createTrainerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(editTrainerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(extrasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pokedexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(movedexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hallOfFameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sidenavPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(topnavPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topnavPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sidenavPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void battleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_battleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_battleButtonActionPerformed

    private void createTrainerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTrainerButtonActionPerformed
        // TODO add your handling code here:
        menuCard.show(contentPanel, "createTrainerCard");
    }//GEN-LAST:event_createTrainerButtonActionPerformed

    private void movedexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movedexButtonActionPerformed
        // TODO add your handling code here:
        menuCard.show(contentPanel, "movedexCard");
    }//GEN-LAST:event_movedexButtonActionPerformed

    private void pokedexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokedexButtonActionPerformed
        // TODO add your handling code here:
        menuCard.show(contentPanel, "pokedexCard");
    }//GEN-LAST:event_pokedexButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        menuCard.show(contentPanel, "newsCard");
    }//GEN-LAST:event_homeButtonActionPerformed

    private void hallOfFameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallOfFameButtonActionPerformed
        // TODO add your handling code here:
        menuCard.show(contentPanel, "HOFCard");
    }//GEN-LAST:event_hallOfFameButtonActionPerformed

    private void editTrainerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTrainerButtonActionPerformed
        // TODO add your handling code here:
        menuCard.show(contentPanel, "viewEditTrainerCard");
    }//GEN-LAST:event_editTrainerButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton battleButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton createTrainerButton;
    private javax.swing.JButton editTrainerButton;
    private javax.swing.JLabel extrasLabel;
    private javax.swing.JButton hallOfFameButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton movedexButton;
    private javax.swing.JLabel newsLabel;
    private javax.swing.JPanel newsPanel;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JButton pokedexButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JPanel sidenavPanel;
    private javax.swing.JPanel topnavPanel;
    // End of variables declaration//GEN-END:variables

}
