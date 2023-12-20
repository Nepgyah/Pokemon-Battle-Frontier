package battle.gui.utils;

import battle.modes.singlebattle.SingleBattleController;
import item.Item;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import trainer.Trainer;

public class bagPanel extends javax.swing.JPanel {

    JPanel contentPanel;
    Trainer trainer;
    SingleBattleController controller;
    CardLayout card;
    ArrayList<Item> bag;
    
    public bagPanel(JPanel panel, Trainer trainer, SingleBattleController controller) {
        initComponents();
        this.contentPanel = panel;
        this.trainer = trainer;
        this.controller = controller;
        this.bag = trainer.getBag();
        this.card = (CardLayout) panel.getLayout();
        
        titleLabel.setText("Bag inventory for " + trainer.getName());
       
        for (Item item : bag) {
            bagList.add(item.getName());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        bagList = new java.awt.List();
        selectionLabel = new javax.swing.JLabel();
        itemNameLabel = new javax.swing.JLabel();
        useButton = new javax.swing.JButton();

        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titleLabel.setText("Bag");

        bagList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bagListMouseClicked(evt);
            }
        });

        selectionLabel.setText("Item Selected:");

        useButton.setText("Use");
        useButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bagList, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectionLabel)
                                    .addComponent(useButton))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(useButton))
                    .addComponent(bagList, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bagListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bagListMouseClicked
        // TODO add your handling code here:
           itemNameLabel.setText(bagList.getSelectedItem());
    }//GEN-LAST:event_bagListMouseClicked

    private void useButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useButtonActionPerformed
        // TODO add your handling code here:
        controller.setItemUse(bagList.getSelectedIndex());
    }//GEN-LAST:event_useButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.List bagList;
    private javax.swing.JLabel itemNameLabel;
    private javax.swing.JLabel selectionLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton useButton;
    // End of variables declaration//GEN-END:variables
}
