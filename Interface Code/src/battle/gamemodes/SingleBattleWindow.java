package battle.gamemodes;

import battle.gui.utilities.bagPanel;
import java.awt.CardLayout;
import battle.gui.utilities.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import pokemon.Pokemon;
import trainer.Trainer;
import javax.swing.SwingUtilities;

public class SingleBattleWindow extends javax.swing.JFrame {

    CardLayout controlCard;
    SingleBattleController battleController;
    
    public SingleBattleWindow(Trainer leftTrainer, Trainer rightTrainer) {
        initComponents();
        
        JLabel [] leftLabels = new JLabel[]{
            leftNameLabel, 
            leftLevelValue,
            leftCurrentHP,
            leftMaxHP,
            leftIcon
        };
        JLabel [] rightLabels = new JLabel[]{
            rightNameLabel,
            rightLevelValue,
            rightCurrentHP,
            rightMaxHP,
            rightIcon
        };
        battleController = new SingleBattleController(
                leftTrainer, rightTrainer, 
                true, 
                eventTextArea,
                leftLabels, rightLabels,
                leftHPBar,rightHPBar,
                fightButton,
                bagButton,
                pokemonButton
        );
        
        controlCard = (CardLayout) detailedPanel.getLayout();
        
        detailedPanel.add(new waitingPanel(detailedPanel), "waitingPanel");
        
        detailedPanel.add(new bagPanel(detailedPanel, leftTrainer.getName()), "leftBagPanel");
        detailedPanel.add(new bagPanel(detailedPanel, rightTrainer.getName()), "rightBagPanel");
        
        detailedPanel.add(new pokemonPanel(detailedPanel, leftTrainer, battleController), "leftPokemonPanel");
        detailedPanel.add(new pokemonPanel(detailedPanel, rightTrainer, battleController), "rightPokemonPanel");
        
        detailedPanel.add(new movePanel(detailedPanel, leftTrainer.getName(), leftTrainer.getParty().get(0).getMoveset(), battleController ), "leftMovePanel");
        detailedPanel.add(new movePanel(detailedPanel, rightTrainer.getName(), rightTrainer.getParty().get(0).getMoveset(), battleController), "rightMovePanel");
        
//        setLeftPokemonLabels(leftTrainer.getParty().get(0));
//        setRightPokemonLabels(rightTrainer.getParty().get(0));
        
        System.out.println("WINDOW CONSOLE: Initializing battle between " + leftTrainer.getName() + " vs " + rightTrainer.getName());
    }

    private void setRightPokemonLabels(Pokemon pokemon) {
        rightNameLabel.setText(pokemon.getName());
        rightLevelValue.setText(Integer.toString(pokemon.getLevel()));
        rightCurrentHP.setText(Integer.toString(pokemon.getCurrent_hp()));
        rightMaxHP.setText(Integer.toString(pokemon.getCurrent_max_hp()));
        rightHPBar.setMaximum(pokemon.getCurrent_max_hp());
        rightHPBar.setValue(pokemon.getCurrent_hp());
        rightIcon.setIcon(pokemon.getIcon());
    }
    
    private void setLeftPokemonLabels(Pokemon pokemon) {
        leftNameLabel.setText(pokemon.getName());
        leftLevelValue.setText(Integer.toString(pokemon.getLevel()));
        leftCurrentHP.setText(Integer.toString(pokemon.getCurrent_hp()));
        leftMaxHP.setText(Integer.toString(pokemon.getCurrent_max_hp()));
        leftHPBar.setMaximum(pokemon.getCurrent_max_hp());
        leftHPBar.setValue(pokemon.getCurrent_hp());
        leftIcon.setIcon(new ImageIcon(new ImageIcon(pokemon.getIconPath()).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
    }
    
    private void updateLeftHP() {
        leftLevelValue.setText(Integer.toString(battleController.getLeftPokemon().getCurrent_hp()));
    }
    
    private void updateRightHP() {
        rightLevelValue.setText(Integer.toString(battleController.getRightPokemon().getCurrent_hp()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        battlePanel = new javax.swing.JPanel();
        rightPokemonPanel = new javax.swing.JPanel();
        rightLevelLabel = new javax.swing.JLabel();
        rightNameLabel = new javax.swing.JLabel();
        rightHPBar = new javax.swing.JProgressBar();
        rightCurrentHP = new javax.swing.JLabel();
        rightMaxHP = new javax.swing.JLabel();
        rightSlash = new javax.swing.JLabel();
        rightLevelValue = new javax.swing.JLabel();
        leftPokemonPanel = new javax.swing.JPanel();
        leftLevelLabel = new javax.swing.JLabel();
        leftNameLabel = new javax.swing.JLabel();
        leftHPBar = new javax.swing.JProgressBar();
        leftCurrentHP = new javax.swing.JLabel();
        leftMaxHP = new javax.swing.JLabel();
        leftSlash = new javax.swing.JLabel();
        leftLevelValue = new javax.swing.JLabel();
        leftIcon = new javax.swing.JLabel();
        rightIcon = new javax.swing.JLabel();
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

        rightPokemonPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

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

        rightLevelValue.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout rightPokemonPanelLayout = new javax.swing.GroupLayout(rightPokemonPanel);
        rightPokemonPanel.setLayout(rightPokemonPanelLayout);
        rightPokemonPanelLayout.setHorizontalGroup(
            rightPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPokemonPanelLayout.createSequentialGroup()
                .addGroup(rightPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rightPokemonPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightCurrentHP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightSlash)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightMaxHP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rightPokemonPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(rightPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rightHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(rightPokemonPanelLayout.createSequentialGroup()
                                .addComponent(rightNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rightLevelLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rightLevelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(6, 6, 6))
        );
        rightPokemonPanelLayout.setVerticalGroup(
            rightPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPokemonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rightNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightLevelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rightCurrentHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightMaxHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightSlash, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        leftPokemonPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

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

        leftLevelValue.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout leftPokemonPanelLayout = new javax.swing.GroupLayout(leftPokemonPanel);
        leftPokemonPanel.setLayout(leftPokemonPanelLayout);
        leftPokemonPanelLayout.setHorizontalGroup(
            leftPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPokemonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPokemonPanelLayout.createSequentialGroup()
                        .addComponent(leftNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftLevelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftLevelValue, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPokemonPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(leftPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPokemonPanelLayout.createSequentialGroup()
                                .addComponent(leftCurrentHP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leftSlash)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leftMaxHP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(leftHPBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6))
        );
        leftPokemonPanelLayout.setVerticalGroup(
            leftPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPokemonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftLevelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPokemonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addGroup(battlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(battlePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rightPokemonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(battlePanelLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(leftIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(battlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftPokemonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, battlePanelLayout.createSequentialGroup()
                        .addComponent(rightIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)))
                .addContainerGap())
        );
        battlePanelLayout.setVerticalGroup(
            battlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(battlePanelLayout.createSequentialGroup()
                .addGroup(battlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(battlePanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightPokemonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(leftIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(battlePanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(rightIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leftPokemonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        eventPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        eventPanel.setToolTipText("");

        eventTextArea.setEditable(false);
        eventTextArea.setColumns(20);
        eventTextArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        eventTextArea.setLineWrap(true);
        eventTextArea.setRows(5);
        eventTextArea.setFocusable(false);
        eventPanel.setViewportView(eventTextArea);

        detailedPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        detailedPanel.setLayout(new java.awt.CardLayout());

        controlPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bagButton.setBackground(new java.awt.Color(255, 102, 0));
        bagButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bagButton.setForeground(new java.awt.Color(255, 255, 255));
        bagButton.setText("Bag");
        bagButton.setFocusable(false);
        bagButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bagButtonActionPerformed(evt);
            }
        });

        fightButton.setBackground(new java.awt.Color(204, 0, 51));
        fightButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        fightButton.setForeground(new java.awt.Color(255, 255, 255));
        fightButton.setText("Fight");
        fightButton.setFocusable(false);
        fightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fightButtonActionPerformed(evt);
            }
        });

        pokemonButton.setBackground(new java.awt.Color(51, 153, 0));
        pokemonButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        pokemonButton.setForeground(new java.awt.Color(255, 255, 255));
        pokemonButton.setText("Pokemon");
        pokemonButton.setFocusable(false);
        pokemonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokemonButtonActionPerformed(evt);
            }
        });

        quitButton.setText("Quit");
        quitButton.setFocusable(false);

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

        if (battleController.getLeftTrainerTurn()) {
            controlCard.show(detailedPanel, "leftMovePanel");
        } else {
            controlCard.show(detailedPanel, "rightMovePanel");
        }
    }//GEN-LAST:event_fightButtonActionPerformed

    private void bagButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bagButtonActionPerformed
        // TODO add your handling code here:
        if (battleController.getLeftTrainerTurn()) {
            controlCard.show(detailedPanel, "leftBagPanel");
        } else {
            controlCard.show(detailedPanel, "rightBagPanel");
        }
    }//GEN-LAST:event_bagButtonActionPerformed

    private void pokemonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonButtonActionPerformed
        // TODO add your handling code here:
        if (battleController.getLeftTrainerTurn()) {
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
    public javax.swing.JTextArea eventTextArea;
    private javax.swing.JButton fightButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel leftCurrentHP;
    private javax.swing.JProgressBar leftHPBar;
    private javax.swing.JLabel leftIcon;
    private javax.swing.JLabel leftLevelLabel;
    private javax.swing.JLabel leftLevelValue;
    private javax.swing.JLabel leftMaxHP;
    private javax.swing.JLabel leftNameLabel;
    private javax.swing.JPanel leftPokemonPanel;
    private javax.swing.JLabel leftSlash;
    private javax.swing.JButton pokemonButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel rightCurrentHP;
    private javax.swing.JProgressBar rightHPBar;
    private javax.swing.JLabel rightIcon;
    private javax.swing.JLabel rightLevelLabel;
    private javax.swing.JLabel rightLevelValue;
    private javax.swing.JLabel rightMaxHP;
    private javax.swing.JLabel rightNameLabel;
    private javax.swing.JPanel rightPokemonPanel;
    private javax.swing.JLabel rightSlash;
    // End of variables declaration//GEN-END:variables
}
