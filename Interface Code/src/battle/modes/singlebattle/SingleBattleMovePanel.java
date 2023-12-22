package battle.modes.singlebattle;

import javax.swing.JPanel;
import move.Move;
import battle.modes.singlebattle.SingleBattleController;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import types.*;
import utilities.PokeColors;

/**
 * JPanel for move selection during a single battle.
 */
public class SingleBattleMovePanel extends javax.swing.JPanel {

    JPanel contentPanel;
    CardLayout card;
    SingleBattleController controller;
    JLabel hpLabel;
    
    /**
     * Constructor for move panel
     * @param panel link back to battle panel
     * @param trainer trainer who is currently making a turn
     * @param moveList list of pokemon's current moves
     * @param controller single battle controller
     */
    public SingleBattleMovePanel(JPanel panel, String trainer, Move[] moveList, SingleBattleController controller) {
        initComponents();
        this.contentPanel = panel;
        this.controller = controller;
        this.hpLabel = hpLabel;
        
        this.card = (CardLayout) panel.getLayout();
        titleLabel.setText("Moveset for " + trainer);
        setMoveButtons(moveList);
    }

    /**
     * Displays the current available moves of a pokemon.
     * @param moveList current list of moves the pokemon can use.
     */
    public void setMoveButtons(Move[] moveList) {
        if (moveList[0] != null) {
            moveOneButton.setEnabled(true);
            moveOneButton.setText(moveList[0].getName());
            moveOneButton.setBackground(setButtonColorByType(moveList[0]));
        } else {
            moveOneButton.setEnabled(false);
            moveOneButton.setText("Move 1");
        }
        if (moveList[1] != null) {
            moveTwoButton.setEnabled(true);
            moveTwoButton.setText(moveList[1].getName());
            moveTwoButton.setBackground(setButtonColorByType(moveList[1]));
        } else {
            moveTwoButton.setEnabled(false);
            moveTwoButton.setText("Move 2");
        }
        if (moveList[2] != null) {
            moveThreeButton.setEnabled(true);
            moveThreeButton.setText(moveList[2].getName());
            moveThreeButton.setBackground(setButtonColorByType(moveList[2]));
        } else {
            moveThreeButton.setEnabled(false);
            moveThreeButton.setText("Move 3");
        }
        if (moveList[3] != null) {
            moveFourButton.setEnabled(true);
            moveFourButton.setText(moveList[3].getName());
            moveFourButton.setBackground(setButtonColorByType(moveList[3]));
        } else {
            moveFourButton.setEnabled(false);
            moveFourButton.setText("Move 4");
        }
    }
    
    private Color setButtonColorByType(Move move) {
        if(move instanceof Bug) return PokeColors.bug;
        if(move instanceof Dark) return PokeColors.dark;
        if(move instanceof Dragon) return PokeColors.dragon;
        if(move instanceof Electric) return PokeColors.electric;
        if(move instanceof Fighting) return PokeColors.fighting;
        if(move instanceof Fire) return PokeColors.fire;
        if(move instanceof Flying) return PokeColors.flying;
        if(move instanceof Ghost) return PokeColors.ghost;
        if(move instanceof Grass) return PokeColors.grass;
        if(move instanceof Ground) return PokeColors.ground;
        if(move instanceof Ice) return PokeColors.ice;
        if(move instanceof Normal) return PokeColors.normal;
        if(move instanceof Poison) return PokeColors.poison;
        if(move instanceof Psychic) return PokeColors.psychic;
        if(move instanceof Rock) return PokeColors.rock;
        if(move instanceof Steel) return PokeColors.steel;
        if(move instanceof Water) return PokeColors.water;
        else return null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        moveOneButton = new javax.swing.JButton();
        moveTwoButton = new javax.swing.JButton();
        moveThreeButton = new javax.swing.JButton();
        moveFourButton = new javax.swing.JButton();

        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titleLabel.setText("Bag");

        moveOneButton.setBackground(new java.awt.Color(153, 0, 51));
        moveOneButton.setForeground(new java.awt.Color(255, 255, 255));
        moveOneButton.setText("Move 1");
        moveOneButton.setBorderPainted(false);
        moveOneButton.setOpaque(true);
        moveOneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneButtonActionPerformed(evt);
            }
        });

        moveTwoButton.setBackground(new java.awt.Color(153, 0, 51));
        moveTwoButton.setForeground(new java.awt.Color(255, 255, 255));
        moveTwoButton.setText("Move 2");
        moveTwoButton.setBorderPainted(false);
        moveTwoButton.setOpaque(true);
        moveTwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveTwoButtonActionPerformed(evt);
            }
        });

        moveThreeButton.setBackground(new java.awt.Color(153, 0, 51));
        moveThreeButton.setForeground(new java.awt.Color(255, 255, 255));
        moveThreeButton.setText("Move 3");
        moveThreeButton.setBorderPainted(false);
        moveThreeButton.setOpaque(true);
        moveThreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveThreeButtonActionPerformed(evt);
            }
        });

        moveFourButton.setBackground(new java.awt.Color(153, 0, 51));
        moveFourButton.setForeground(new java.awt.Color(255, 255, 255));
        moveFourButton.setText("Move 4");
        moveFourButton.setBorderPainted(false);
        moveFourButton.setOpaque(true);
        moveFourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveFourButtonActionPerformed(evt);
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
                        .addComponent(moveOneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(moveTwoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(moveThreeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(moveFourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveOneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveTwoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveThreeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveFourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void moveOneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveOneButtonActionPerformed
        controller.setMoveChoice(0);
//        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_moveOneButtonActionPerformed

    private void moveTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveTwoButtonActionPerformed
        controller.setMoveChoice(1);
//        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_moveTwoButtonActionPerformed

    private void moveThreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveThreeButtonActionPerformed
        controller.setMoveChoice(2);
//        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_moveThreeButtonActionPerformed

    private void moveFourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveFourButtonActionPerformed
        controller.setMoveChoice(3);
//        card.show(contentPanel, "waitingPanel");
    }//GEN-LAST:event_moveFourButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton moveFourButton;
    private javax.swing.JButton moveOneButton;
    private javax.swing.JButton moveThreeButton;
    private javax.swing.JButton moveTwoButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
