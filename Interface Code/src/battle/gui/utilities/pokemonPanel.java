package battle.gui.utilities;

import battle.gamemodes.SingleBattleController;
import java.awt.CardLayout;
import javax.swing.JPanel;
import trainer.Trainer;

public class pokemonPanel extends javax.swing.JPanel {

    JPanel contentPanel;
    CardLayout card;
    Trainer trainer;
    SingleBattleController controller;
    
    public pokemonPanel(JPanel panel, Trainer trainer, SingleBattleController controller) {
        initComponents();
        this.contentPanel = panel;
        this.trainer = trainer;
        this.controller = controller;
        card = (CardLayout) panel.getLayout();
        
        initializeParty();
        titleLabel.setText("Party Pokemon for " + trainer.getName());
        
    }

    private void initializeParty() {
        pokemonOne.setText(trainer.getParty().get(0).displayButtonInfo());
        if (trainer.getParty().size() > 1) {
            pokemonTwo.setText(trainer.getParty().get(1).displayButtonInfo());
        } else {
            pokemonTwo.setVisible(false);
        }
        if (trainer.getParty().size() > 2) {
            pokemonThree.setText(trainer.getParty().get(2).displayButtonInfo());
        } else {
            pokemonThree.setVisible(false);
        }
        if (trainer.getParty().size() > 3) {
            pokemonFour.setText(trainer.getParty().get(3).displayButtonInfo());
        } else {
            pokemonFour.setVisible(false);
        }
        if (trainer.getParty().size() > 4) {
            pokemonFive.setText(trainer.getParty().get(4).displayButtonInfo());
        } else {
            pokemonFive.setVisible(false);
        }
        if (trainer.getParty().size() > 5) {
            pokemonSix.setText(trainer.getParty().get(5).displayButtonInfo());
        } else {
            pokemonSix.setVisible(false);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        pokemonOne = new javax.swing.JButton();
        pokemonTwo = new javax.swing.JButton();
        pokemonThree = new javax.swing.JButton();
        pokemonFour = new javax.swing.JButton();
        pokemonFive = new javax.swing.JButton();
        pokemonSix = new javax.swing.JButton();

        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        pokemonOne.setText("Pokemon One");
        pokemonOne.setEnabled(false);
        pokemonOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokemonOneActionPerformed(evt);
            }
        });

        pokemonTwo.setText("Pokemon Two");
        pokemonTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokemonTwoActionPerformed(evt);
            }
        });

        pokemonThree.setText("Pokemon Three");
        pokemonThree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokemonThreeActionPerformed(evt);
            }
        });

        pokemonFour.setText("Pokemon Four");
        pokemonFour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokemonFourActionPerformed(evt);
            }
        });

        pokemonFive.setText("Pokemon Five");
        pokemonFive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokemonFiveActionPerformed(evt);
            }
        });

        pokemonSix.setText("Pokemon Six");
        pokemonSix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokemonSixActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pokemonOne, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pokemonTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pokemonThree, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pokemonFour, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pokemonFive, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pokemonSix, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pokemonOne, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pokemonTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pokemonThree, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pokemonFour, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pokemonFive, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pokemonSix, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pokemonOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonOneActionPerformed
        controller.setPokemonSwap(0);
        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_pokemonOneActionPerformed

    private void pokemonTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonTwoActionPerformed
        controller.setPokemonSwap(1);
        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_pokemonTwoActionPerformed

    private void pokemonThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonThreeActionPerformed
        controller.setPokemonSwap(2);
        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_pokemonThreeActionPerformed

    private void pokemonFourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonFourActionPerformed
        controller.setPokemonSwap(3);
        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_pokemonFourActionPerformed

    private void pokemonFiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonFiveActionPerformed
        controller.setPokemonSwap(4);
        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_pokemonFiveActionPerformed

    private void pokemonSixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonSixActionPerformed
        controller.setPokemonSwap(5);
        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_pokemonSixActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton pokemonFive;
    private javax.swing.JButton pokemonFour;
    private javax.swing.JButton pokemonOne;
    private javax.swing.JButton pokemonSix;
    private javax.swing.JButton pokemonThree;
    private javax.swing.JButton pokemonTwo;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
