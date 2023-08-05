package battle.gamemodes;

import javax.swing.JPanel;
import move.Move;

public class movePanel extends javax.swing.JPanel {

    JPanel contentPanel;
    
    public movePanel(JPanel panel, String trainer, Move[] moveList) {
        initComponents();
        this.contentPanel = panel;
        titleLabel.setText("Move inventory for " + trainer);
        setMoveButtons(moveList);
    }

    public void setMoveButtons(Move[] moveList) {
        if (moveList[0] != null) {
            moveOneButton.setText(moveList[0].getName());
        } else {
            moveOneButton.setEnabled(false);
        }
        if (moveList[1] != null) {
            moveTwoButton.setText(moveList[1].getName());
        } else {
            moveTwoButton.setEnabled(false);
        }
        if (moveList[2] != null) {
            moveThreeButton.setText(moveList[2].getName());
        } else {
            moveThreeButton.setEnabled(false);
        }
        if (moveList[3] != null) {
            moveFourButton.setText(moveList[3].getName());
        } else {
            moveFourButton.setEnabled(false);
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
        // TODO add your handling code here:
    }//GEN-LAST:event_moveOneButtonActionPerformed

    private void moveTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveTwoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moveTwoButtonActionPerformed

    private void moveThreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveThreeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moveThreeButtonActionPerformed

    private void moveFourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveFourButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moveFourButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton moveFourButton;
    private javax.swing.JButton moveOneButton;
    private javax.swing.JButton moveThreeButton;
    private javax.swing.JButton moveTwoButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
