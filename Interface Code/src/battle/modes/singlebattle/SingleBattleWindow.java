package battle.modes.singlebattle;

import battle.gui.utils.waitingPanel;
import battle.gui.utils.pokemonPanel;
import battle.gui.utils.bagPanel;
import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import trainer.Trainer;
import utilities.PokeColors;

public class SingleBattleWindow extends javax.swing.JFrame {

    JFrame clientFrame;
    JPanel navPanel;
    CardLayout controlCard;
    SingleBattleController battleController;
    pokemonPanel leftPokemonPanel, rightPokemonPanel;
    SingleBattleMovePanel leftMovePanel;
    SingleBattleMovePanel rightMovePanel;
    
    public SingleBattleWindow(JFrame clientFrame, JPanel navPanel, Trainer leftTrainer, Trainer rightTrainer) {
        initComponents();
        this.clientFrame = clientFrame;
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        JLabel [] leftLabels = new JLabel[]{
            leftName, 
            leftLevelValue,
            leftCurrentHP,
            leftMaxHP,
            leftStatus,
            leftIcon
        };
        JLabel [] rightLabels = new JLabel[]{
            rightName,
            rightLevelValue,
            rightCurrentHP,
            rightMaxHP,
            rightStatus,
            rightIcon
        };
        JButton [] controlButtons = new JButton[] {
            fightButton,
            pokemonButton,
            bagButton
        };
        battleController = new SingleBattleController(
                this,
                clientFrame,
                navPanel,
                leftTrainer, rightTrainer,
                true,
                eventTextArea,
                leftLabels, rightLabels,
                leftHPBar, rightHPBar,
                controlButtons,
                detailPanel
        );
        
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(1,1, 928,392);
        backgroundLabel.setOpaque(true);
        backgroundLabel.setIcon(new ImageIcon(new ImageIcon("resources/battleScenes/grassfield.png").getImage().getScaledInstance(928, 392, Image.SCALE_DEFAULT)));
        layeredPane.add(backgroundLabel);
        
        leftPokemonPanel = new pokemonPanel(detailPanel, leftTrainer, battleController);
        rightPokemonPanel = new pokemonPanel(detailPanel, rightTrainer, battleController);
        
        leftMovePanel = new SingleBattleMovePanel(detailPanel, leftTrainer.getName(), leftTrainer.getParty().get(0).getMoveset(), battleController );
        rightMovePanel = new SingleBattleMovePanel(detailPanel, rightTrainer.getName(), rightTrainer.getParty().get(0).getMoveset(), battleController);
        
        battleController.setLeftPokePanel(leftPokemonPanel);
        battleController.setRightPokePanel(rightPokemonPanel);
        battleController.setLeftMovePanel(leftMovePanel);
        battleController.setRightMovePanel(rightMovePanel);
        
        controlCard = (CardLayout) detailPanel.getLayout();
       
        detailPanel.add(new waitingPanel(detailPanel), "waitingPanel");
        
        detailPanel.add(new bagPanel(detailPanel, leftTrainer.getName()), "leftBagPanel");
        detailPanel.add(new bagPanel(detailPanel, rightTrainer.getName()), "rightBagPanel");
        
        detailPanel.add(leftPokemonPanel, "leftPokemonPanel");
        detailPanel.add(rightPokemonPanel, "rightPokemonPanel");
        
        detailPanel.add(leftMovePanel, "leftMovePanel");
        detailPanel.add(rightMovePanel, "rightMovePanel");
        
        eventTextArea.setText("What will " + leftTrainer.getParty().get(0).getName() + " do?");
        System.out.println("WINDOW CONSOLE: Initializing battle between " + leftTrainer.getName() + " vs " + rightTrainer.getName());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        eventPanel = new javax.swing.JScrollPane();
        eventTextArea = new javax.swing.JTextArea();
        detailPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        bagButton = new javax.swing.JButton();
        fightButton = new javax.swing.JButton();
        pokemonButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        layeredPane = new javax.swing.JLayeredPane();
        leftPokemonLabels = new javax.swing.JPanel();
        leftLevelLabel = new javax.swing.JLabel();
        leftName = new javax.swing.JLabel();
        leftHPBar = new javax.swing.JProgressBar();
        leftCurrentHP = new javax.swing.JLabel();
        leftMaxHP = new javax.swing.JLabel();
        leftSlash = new javax.swing.JLabel();
        leftLevelValue = new javax.swing.JLabel();
        leftStatus = new javax.swing.JLabel();
        leftIcon = new javax.swing.JLabel();
        rightIcon = new javax.swing.JLabel();
        rightPokemonLabels = new javax.swing.JPanel();
        rightLevelLabel = new javax.swing.JLabel();
        rightName = new javax.swing.JLabel();
        rightHPBar = new javax.swing.JProgressBar();
        rightCurrentHP = new javax.swing.JLabel();
        rightMaxHP = new javax.swing.JLabel();
        rightSlash = new javax.swing.JLabel();
        rightLevelValue = new javax.swing.JLabel();
        rightStatus = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pokemon Battle Window");
        setBackground(new java.awt.Color(51, 51, 51));

        eventPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        eventPanel.setToolTipText("");

        eventTextArea.setEditable(false);
        eventTextArea.setColumns(20);
        eventTextArea.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        eventTextArea.setLineWrap(true);
        eventTextArea.setRows(3);
        eventTextArea.setFocusable(false);
        eventPanel.setViewportView(eventTextArea);

        detailPanel.setBackground(new java.awt.Color(51, 51, 51));
        detailPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        detailPanel.setEnabled(false);
        detailPanel.setFocusable(false);
        detailPanel.setLayout(new java.awt.CardLayout());

        buttonPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonPanel.setMaximumSize(new java.awt.Dimension(344, 140));

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

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(fightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bagButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(pokemonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bagButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(fightButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pokemonButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(quitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layeredPane.setBackground(new java.awt.Color(102, 102, 0));
        layeredPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        layeredPane.setForeground(new java.awt.Color(153, 102, 0));
        layeredPane.setEnabled(false);
        layeredPane.setFocusable(false);
        layeredPane.setMaximumSize(new java.awt.Dimension(930, 394));
        layeredPane.setMinimumSize(new java.awt.Dimension(930, 394));
        layeredPane.setRequestFocusEnabled(false);
        layeredPane.setVerifyInputWhenFocusTarget(false);

        leftPokemonLabels.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        leftPokemonLabels.setPreferredSize(new java.awt.Dimension(280, 100));

        leftLevelLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        leftLevelLabel.setText("Lv.");

        leftName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        leftName.setText("Pokemon Name");

        leftHPBar.setForeground(new java.awt.Color(51, 204, 0));
        leftHPBar.setValue(100);

        leftCurrentHP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        leftCurrentHP.setText("Current");

        leftMaxHP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        leftMaxHP.setText("Max");

        leftSlash.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        leftSlash.setText("/");

        leftLevelValue.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        leftLevelValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftLevelValue.setText("100");

        leftStatus.setBackground(new java.awt.Color(0, 0, 0));
        leftStatus.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout leftPokemonLabelsLayout = new javax.swing.GroupLayout(leftPokemonLabels);
        leftPokemonLabels.setLayout(leftPokemonLabelsLayout);
        leftPokemonLabelsLayout.setHorizontalGroup(
            leftPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPokemonLabelsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(leftPokemonLabelsLayout.createSequentialGroup()
                        .addComponent(leftName, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(leftLevelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftLevelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftPokemonLabelsLayout.createSequentialGroup()
                        .addComponent(leftStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(leftPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leftHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPokemonLabelsLayout.createSequentialGroup()
                                .addComponent(leftCurrentHP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leftSlash)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leftMaxHP)
                                .addGap(25, 25, 25)))))
                .addGap(6, 6, 6))
        );
        leftPokemonLabelsLayout.setVerticalGroup(
            leftPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPokemonLabelsLayout.createSequentialGroup()
                .addGroup(leftPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(leftPokemonLabelsLayout.createSequentialGroup()
                        .addGroup(leftPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leftName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(leftPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(leftLevelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(leftLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(leftStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(leftPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftMaxHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftSlash, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftCurrentHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        leftIcon.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        rightIcon.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        rightPokemonLabels.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        rightPokemonLabels.setPreferredSize(new java.awt.Dimension(280, 100));

        rightLevelLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rightLevelLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rightLevelLabel.setText("Lv.");

        rightName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rightName.setText("Pokemon Name");

        rightHPBar.setForeground(new java.awt.Color(51, 204, 0));
        rightHPBar.setValue(100);

        rightCurrentHP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rightCurrentHP.setText("Current");

        rightMaxHP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rightMaxHP.setText("Max");

        rightSlash.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rightSlash.setText("/");

        rightLevelValue.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rightLevelValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        rightStatus.setBackground(new java.awt.Color(0, 0, 0));
        rightStatus.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout rightPokemonLabelsLayout = new javax.swing.GroupLayout(rightPokemonLabels);
        rightPokemonLabels.setLayout(rightPokemonLabelsLayout);
        rightPokemonLabelsLayout.setHorizontalGroup(
            rightPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPokemonLabelsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rightPokemonLabelsLayout.createSequentialGroup()
                        .addComponent(rightStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(rightPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPokemonLabelsLayout.createSequentialGroup()
                                .addComponent(rightCurrentHP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rightSlash, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rightMaxHP)
                                .addGap(25, 25, 25))
                            .addComponent(rightHPBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(rightPokemonLabelsLayout.createSequentialGroup()
                        .addComponent(rightName, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightLevelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightLevelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        rightPokemonLabelsLayout.setVerticalGroup(
            rightPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPokemonLabelsLayout.createSequentialGroup()
                .addGroup(rightPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rightPokemonLabelsLayout.createSequentialGroup()
                        .addGroup(rightPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rightPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rightName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rightLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rightLevelValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightHPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rightStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(rightPokemonLabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rightMaxHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightSlash, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightCurrentHP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        layeredPane.setLayer(leftPokemonLabels, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(leftIcon, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(rightIcon, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(rightPokemonLabels, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layeredPaneLayout = new javax.swing.GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rightPokemonLabels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                        .addComponent(leftPokemonLabels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                        .addComponent(rightIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136))))
        );
        layeredPaneLayout.setVerticalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(rightIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftPokemonLabels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rightPokemonLabels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(leftIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(eventPanel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(layeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(layeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eventPanel)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fightButtonActionPerformed
        if (battleController.getLeftTrainerTurn()) {
            controlCard.show(detailPanel, "leftMovePanel");
        } else {
            controlCard.show(detailPanel, "rightMovePanel");
        }
    }//GEN-LAST:event_fightButtonActionPerformed

    private void bagButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bagButtonActionPerformed
        if (battleController.getLeftTrainerTurn()) {
            controlCard.show(detailPanel, "leftBagPanel");
        } else {
            controlCard.show(detailPanel, "rightBagPanel");
        }
    }//GEN-LAST:event_bagButtonActionPerformed

    private void pokemonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokemonButtonActionPerformed
        if (battleController.getLeftTrainerTurn()) {
            controlCard.show(detailPanel, "leftPokemonPanel");
        } else {
            controlCard.show(detailPanel, "rightPokemonPanel");
        }
    }//GEN-LAST:event_pokemonButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bagButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel detailPanel;
    private javax.swing.JScrollPane eventPanel;
    public javax.swing.JTextArea eventTextArea;
    private javax.swing.JButton fightButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JLabel leftCurrentHP;
    private javax.swing.JProgressBar leftHPBar;
    private javax.swing.JLabel leftIcon;
    private javax.swing.JLabel leftLevelLabel;
    private javax.swing.JLabel leftLevelValue;
    private javax.swing.JLabel leftMaxHP;
    private javax.swing.JLabel leftName;
    private javax.swing.JPanel leftPokemonLabels;
    private javax.swing.JLabel leftSlash;
    private javax.swing.JLabel leftStatus;
    private javax.swing.JButton pokemonButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel rightCurrentHP;
    private javax.swing.JProgressBar rightHPBar;
    private javax.swing.JLabel rightIcon;
    private javax.swing.JLabel rightLevelLabel;
    private javax.swing.JLabel rightLevelValue;
    private javax.swing.JLabel rightMaxHP;
    private javax.swing.JLabel rightName;
    private javax.swing.JPanel rightPokemonLabels;
    private javax.swing.JLabel rightSlash;
    private javax.swing.JLabel rightStatus;
    // End of variables declaration//GEN-END:variables
}
