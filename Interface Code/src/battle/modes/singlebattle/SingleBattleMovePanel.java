package battle.modes.singlebattle;

import javax.swing.JPanel;
import move.Move;
import battle.modes.singlebattle.SingleBattleController;
import java.awt.CardLayout;
import javax.swing.JLabel;

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
        
        card = (CardLayout) panel.getLayout();
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
        } else {
            moveOneButton.setEnabled(false);
            moveOneButton.setText("Move 1");
        }
        if (moveList[1] != null) {
            moveTwoButton.setEnabled(true);
            moveTwoButton.setText(moveList[1].getName());
        } else {
            moveTwoButton.setEnabled(false);
            moveTwoButton.setText("Move 2");
        }
        if (moveList[2] != null) {
            moveThreeButton.setEnabled(true);
            moveThreeButton.setText(moveList[2].getName());
        } else {
            moveThreeButton.setEnabled(false);
            moveThreeButton.setText("Move 3");
        }
        if (moveList[3] != null) {
            moveFourButton.setEnabled(true);
            moveFourButton.setText(moveList[3].getName());
        } else {
            moveFourButton.setEnabled(false);
            moveFourButton.setText("Move 4");
        }
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

        moveOneButton.setText("Move 1");
        moveOneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveOneButtonActionPerformed(evt);
            }
        });

        moveTwoButton.setText("Move 2");
        moveTwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveTwoButtonActionPerformed(evt);
            }
        });

        moveThreeButton.setText("Move 3");
        moveThreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveThreeButtonActionPerformed(evt);
            }
        });

        moveFourButton.setText("Move 4");
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
