package battle.gui.utilities;

import javax.swing.JPanel;
import trainer.Trainer;

public class pokemonPanel extends javax.swing.JPanel {

    JPanel contentPanel;
    Trainer trainer;
    
    public pokemonPanel(JPanel panel, Trainer trainer) {
        initComponents();
        this.contentPanel = panel;
        this.trainer = trainer;
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

        pokemonTwo.setText("Pokemon Two");

        pokemonThree.setText("Pokemon Three");

        pokemonFour.setText("Pokemon Four");

        pokemonFive.setText("Pokemon Five");

        pokemonSix.setText("Pokemon Six");

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
