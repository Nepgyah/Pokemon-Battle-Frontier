package battle.gamemodes;

import java.awt.CardLayout;
import battle.gui.utilities.*;

public class SingleBattleWindow extends javax.swing.JFrame {

    CardLayout controlCard;
    boolean playerOneTurn = true;
    public SingleBattleWindow() {
        initComponents();
        
        controlCard = (CardLayout) detailedPanel.getLayout();
        
        detailedPanel.add(new bagPanel(detailedPanel, "Ash"), "leftBagPanel");
        detailedPanel.add(new bagPanel(detailedPanel, "Gary"), "rightBagPanel");
        detailedPanel.add(new pokemonPanel(detailedPanel, "Ash"), "leftPokemonPanel");
        detailedPanel.add(new pokemonPanel(detailedPanel, "Gary"), "rightPokemonPanel");
    }
    
    public void beginBattle() {
        // Initialize the handler and set the GUI fields appropriate
        eventTextArea.setText("The Battle Begins");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        battlePanel = new javax.swing.JPanel();
        leftPokemonPane = new javax.swing.JPanel();
        rightLevelLabel = new javax.swing.JLabel();
        rightNameLabel = new javax.swing.JLabel();
        rightHPBar = new javax.swing.JProgressBar();
        rightCurrentHP = new javax.swing.JLabel();
        rightMaxHP = new javax.swing.JLabel();
        rightSlash = new javax.swing.JLabel();
        leftPokemonPane1 = new javax.swing.JPanel();
        leftLevelLabel = new javax.swing.JLabel();
        leftNameLabel = new javax.swing.JLabel();
        leftHPBar = new javax.swing.JProgressBar();
        leftCurrentHP = new javax.swing.JLabel();
        leftMaxHP = new javax.swing.JLabel();
        leftSlash = new javax.swing.JLabel();
        eventPanel = new javax.swing.JScrollPane();
        eventTextArea = new javax.swing.JTextArea();
        detailedPanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        bagButton = new javax.swing.JButton();
        fightButton = new javax.swing.JButton();
        pokemonButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pokemon Battle Window");

        battlePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        leftPokemonPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        rightLevelLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rightLevelLabel.setText("Lv.");

        rightNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rightNameLabel.setText("Pokemon Name");

        rightHPBar.setForeground(new java.awt.Color(51, 204, 0));
        rightHPBar.setValue(100);

        rightCurrentHP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rightCurrentHP.setText("Current");

        rightMaxHP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rightMaxHP.setText("Max");

        rightSlash.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rightSlash.setText("/");

        javax.swing.GroupLayout leftPokemonPaneLayout = new javax.swing.GroupLayout(leftPokemonPane);
        leftPokemonPane.setLayout(leftPokemonPaneLayout);
        leftPokemonPaneLayout.setHorizontalGroup(
            leftPokemonPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPokemonPaneLayout.createSequentialGroup()
                .addGroup(leftPokemonPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(leftPokemonPaneLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightCurrentHP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightSlash)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightMaxHP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftPokemonPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(leftPokemonPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rightHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(leftPokemonPaneLayout.createSequentialGroup()
                                .addComponent(rightNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rightLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(6, 6, 6))
        );
        leftPokemonPaneLayout.setVerticalGroup(
            leftPokemonPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPokemonPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPokemonPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rightNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPokemonPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rightCurrentHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightMaxHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightSlash, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        leftPokemonPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        leftLevelLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        leftLevelLabel.setText("Lv.");

        leftNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        leftNameLabel.setText("Pokemon Name");

        leftHPBar.setForeground(new java.awt.Color(51, 204, 0));
        leftHPBar.setValue(100);

        leftCurrentHP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        leftCurrentHP.setText("Current");

        leftMaxHP.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        leftMaxHP.setText("Max");

        leftSlash.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        leftSlash.setText("/");

        javax.swing.GroupLayout leftPokemonPane1Layout = new javax.swing.GroupLayout(leftPokemonPane1);
        leftPokemonPane1.setLayout(leftPokemonPane1Layout);
        leftPokemonPane1Layout.setHorizontalGroup(
            leftPokemonPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPokemonPane1Layout.createSequentialGroup()
                .addGroup(leftPokemonPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(leftPokemonPane1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leftCurrentHP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftSlash)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftMaxHP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftPokemonPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(leftPokemonPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(leftHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(leftPokemonPane1Layout.createSequentialGroup()
                                .addComponent(leftNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leftLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(6, 6, 6))
        );
        leftPokemonPane1Layout.setVerticalGroup(
            leftPokemonPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPokemonPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPokemonPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPokemonPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftMaxHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftSlash, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftCurrentHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout battlePanelLayout = new javax.swing.GroupLayout(battlePanel);
        battlePanel.setLayout(battlePanelLayout);
        battlePanelLayout.setHorizontalGroup(
            battlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(battlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftPokemonPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(443, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, battlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(leftPokemonPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        battlePanelLayout.setVerticalGroup(
            battlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(battlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftPokemonPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(leftPokemonPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        eventPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        eventPanel.setToolTipText("");

        eventTextArea.setEditable(false);
        eventTextArea.setColumns(20);
        eventTextArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        eventTextArea.setLineWrap(true);
        eventTextArea.setRows(5);
        eventPanel.setViewportView(eventTextArea);

        detailedPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        detailedPanel.setLayout(new java.awt.CardLayout());

        controlPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bagButton.setText("Bag");
        bagButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bagButtonActionPerformed(evt);
            }
        });

        fightButton.setText("Fight");
        fightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fightButtonActionPerformed(evt);
            }
        });

        pokemonButton.setText("Pokemon");
        pokemonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokemonButtonActionPerformed(evt);
            }
        });

        quitButton.setText("Quit");

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(fightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bagButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(pokemonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bagButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(fightButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pokemonButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(quitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(eventPanel)
                    .addComponent(battlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(detailedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(battlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(detailedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eventPanel)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fightButtonActionPerformed
        // TODO add your handling code here:
        playerOneTurn = false;
    }//GEN-LAST:event_fightButtonActionPerformed

    private void bagButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bagButtonActionPerformed
        // TODO add your handling code here:
        if (playerOneTurn) {
            controlCard.show(detailedPanel, "leftBagPanel");
        } else {
            controlCard.show(detailedPanel, "rightBagPanel");
        }
    }//GEN-LAST:event_bagButtonActionPerformed

    private void pokemonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonButtonActionPerformed
        // TODO add your handling code here:
        if (playerOneTurn) {
            controlCard.show(detailedPanel, "leftPokemonPanel");
        } else {
            controlCard.show(detailedPanel, "rightPokemonPanel");
        }
    }//GEN-LAST:event_pokemonButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bagButton;
    private javax.swing.JPanel battlePanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel detailedPanel;
    private javax.swing.JScrollPane eventPanel;
    private javax.swing.JTextArea eventTextArea;
    private javax.swing.JButton fightButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel leftCurrentHP;
    private javax.swing.JProgressBar leftHPBar;
    private javax.swing.JLabel leftLevelLabel;
    private javax.swing.JLabel leftMaxHP;
    private javax.swing.JLabel leftNameLabel;
    private javax.swing.JPanel leftPokemonPane;
    private javax.swing.JPanel leftPokemonPane1;
    private javax.swing.JLabel leftSlash;
    private javax.swing.JButton pokemonButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel rightCurrentHP;
    private javax.swing.JProgressBar rightHPBar;
    private javax.swing.JLabel rightLevelLabel;
    private javax.swing.JLabel rightMaxHP;
    private javax.swing.JLabel rightNameLabel;
    private javax.swing.JLabel rightSlash;
    // End of variables declaration//GEN-END:variables
}
