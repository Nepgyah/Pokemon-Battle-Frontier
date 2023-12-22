package battle.gui.utils;

import battle.modes.singlebattle.SingleBattleController;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import trainer.Trainer;
import utilities.PokeColors;

public class pokemonPanel extends javax.swing.JPanel {

    JPanel contentPanel;
    CardLayout card;
    Trainer trainer;
    SingleBattleController controller;
    JTextArea textArea;
    int pokemonSelected = 0;
    public pokemonPanel(JPanel panel, Trainer trainer, SingleBattleController controller, JTextArea textArea) {
        initComponents();
        this.contentPanel = panel;
        this.trainer = trainer;
        this.controller = controller;
        this.textArea = textArea;
        card = (CardLayout) panel.getLayout();
        shiftButton.setBackground(PokeColors.quitButton);
        summaryButton.setBackground(PokeColors.quitButton);
        cancelButton.setBackground(PokeColors.quitButton);
        setPokemonButtons();
        titleLabel.setText("Party Pokemon for " + trainer.getName());
        
    }
    
    public JButton[] getPokemonButtons() {
        JButton [] buttons = new JButton[] {
            this.pokemonOne, 
            this.pokemonTwo, 
            this.pokemonThree, 
            this.pokemonFour, 
            this.pokemonFive, 
            this.pokemonSix
        };
        return buttons;
    }
    
    public void setPokemonButtons() {
        this.pokemonOne.setText(trainer.getParty().get(0).displayButtonInfo());
        if (trainer.getParty().size() > 1) {
            this.pokemonTwo.setText(trainer.getParty().get(1).displayButtonInfo());
        } else {
            this.pokemonTwo.setVisible(false);
        }
        if (trainer.getParty().size() > 2) {
            this.pokemonThree.setText(trainer.getParty().get(2).displayButtonInfo());
        } else {
            this.pokemonThree.setVisible(false);
        }
        if (trainer.getParty().size() > 3) {
            this.pokemonFour.setText(trainer.getParty().get(3).displayButtonInfo());
        } else {
            this.pokemonFour.setVisible(false);
        }
        if (trainer.getParty().size() > 4) {
            this.pokemonFive.setText(trainer.getParty().get(4).displayButtonInfo());
        } else {
            this.pokemonFive.setVisible(false);
        }
        if (trainer.getParty().size() > 5) {
            this.pokemonSix.setText(trainer.getParty().get(5).displayButtonInfo());
        } else {
            this.pokemonSix.setVisible(false);
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
        shiftButton = new javax.swing.JButton();
        summaryButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

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

        shiftButton.setText("Shift");
        shiftButton.setBorderPainted(false);
        shiftButton.setOpaque(true);
        shiftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftButtonActionPerformed(evt);
            }
        });

        summaryButton.setText("Summary");
        summaryButton.setBorderPainted(false);
        summaryButton.setOpaque(true);
        summaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                summaryButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.setBorderPainted(false);
        cancelButton.setOpaque(true);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
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
                        .addComponent(pokemonSix, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(shiftButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(summaryButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shiftButton)
                    .addComponent(summaryButton)
                    .addComponent(cancelButton))
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pokemonOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonOneActionPerformed
        this.pokemonSelected = 0;
        textArea.setText("What would you like to do with " + pokemonOne.getName());
    }//GEN-LAST:event_pokemonOneActionPerformed

    private void pokemonTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonTwoActionPerformed
        this.pokemonSelected = 1;
        textArea.setText("What would you like to do with " + pokemonTwo.getName());
    }//GEN-LAST:event_pokemonTwoActionPerformed

    private void pokemonThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonThreeActionPerformed
        this.pokemonSelected = 2;
        textArea.setText("What would you like to do with " + pokemonThree.getName());
    }//GEN-LAST:event_pokemonThreeActionPerformed

    private void pokemonFourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonFourActionPerformed
        this.pokemonSelected = 3;
        textArea.setText("What would you like to do with " + pokemonFour.getName());
    }//GEN-LAST:event_pokemonFourActionPerformed

    private void pokemonFiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonFiveActionPerformed
        this.pokemonSelected = 4;
        textArea.setText("What would you like to do with " + pokemonFive.getName());
    }//GEN-LAST:event_pokemonFiveActionPerformed

    private void pokemonSixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonSixActionPerformed
        this.pokemonSelected = 5;
        textArea.setText("What would you like to do with " + pokemonSix.getName());
    }//GEN-LAST:event_pokemonSixActionPerformed

    private void shiftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftButtonActionPerformed
        controller.setPokemonSwap(pokemonSelected);
        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_shiftButtonActionPerformed

    private void summaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_summaryButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_summaryButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton pokemonFive;
    private javax.swing.JButton pokemonFour;
    private javax.swing.JButton pokemonOne;
    private javax.swing.JButton pokemonSix;
    private javax.swing.JButton pokemonThree;
    private javax.swing.JButton pokemonTwo;
    private javax.swing.JButton shiftButton;
    private javax.swing.JButton summaryButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
