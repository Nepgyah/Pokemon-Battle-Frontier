package client;

import java.awt.CardLayout;
import javax.swing.ImageIcon;

public class Client extends javax.swing.JFrame {

    CardLayout menuCard;
    CardLayout navCard;
    
    /*  List of all information to customize pokemon trainers
    TODO: Move this to a better spot
    */
    ImageIcon pikachu = new ImageIcon("pikachu.png");
    //ArrayList<Pokemon> pokelist;
    
    public Client() {
        initComponents();
     
        // Lable for MainMenu picture
        pictureLabel.setText("");
        pictureLabel.setIcon(pikachu);
        
        // panel settings for menuCard
        menuCard = (CardLayout) contentPanel.getLayout();
        menuCard.show(contentPanel, "grettingCard");
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
        contentPanel.add(new NewsPanel(contentPanel), "newsCard");
        contentPanel.add(new PokedexPanel(contentPanel), "pokedexCard");
        contentPanel.add(new MovedexPanel(contentPanel), "movedexCard");
        contentPanel.add(new CreateTrainerPanel(contentPanel), "createTrainerCard");
        contentPanel.add(new ViewEditTrainerPanel(contentPanel), "viewEditTrainerCard");
        contentPanel.add(new HallOfFamePanel(contentPanel), "HOFCard");

        // panel settings for navCard
        navCard = (CardLayout) navPanel.getLayout();
        
        navPanel.add(new SideNavPanel(contentPanel), "testCard");
        navPanel.add(new InBattlePanel(navPanel), "altCard");
        navCard.show(navPanel, "testCard");
    }   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        newsPanel = new javax.swing.JPanel();
        pictureLabel = new javax.swing.JLabel();
        newsLabel = new javax.swing.JLabel();
        topnavPanel = new javax.swing.JPanel();
        battleButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        navPanel = new javax.swing.JPanel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PokeClient");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        contentPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        contentPanel.setLayout(new java.awt.CardLayout());

        pictureLabel.setText("picture");

        newsLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        newsLabel.setText("Welcome to the Battle Frontier");

        javax.swing.GroupLayout newsPanelLayout = new javax.swing.GroupLayout(newsPanel);
        newsPanel.setLayout(newsPanelLayout);
        newsPanelLayout.setHorizontalGroup(
            newsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newsPanelLayout.createSequentialGroup()
                .addGroup(newsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(322, 322, 322))
        );
        newsPanelLayout.setVerticalGroup(
            newsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newsPanelLayout.createSequentialGroup()
                .addComponent(newsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(pictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addGap(108, 108, 108))
        );

        contentPanel.add(newsPanel, "menuCard");

        topnavPanel.setBackground(new java.awt.Color(153, 0, 0));

        battleButton.setText("Battle");
        battleButton.setPreferredSize(new java.awt.Dimension(135, 40));
        battleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                battleButtonActionPerformed(evt);
            }
        });

        homeButton.setText("Home");
        homeButton.setPreferredSize(new java.awt.Dimension(135, 40));
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pokemon Battle Frontier");

        javax.swing.GroupLayout topnavPanelLayout = new javax.swing.GroupLayout(topnavPanel);
        topnavPanel.setLayout(topnavPanelLayout);
        topnavPanelLayout.setHorizontalGroup(
            topnavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topnavPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(battleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topnavPanelLayout.setVerticalGroup(
            topnavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topnavPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(topnavPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(battleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        navPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        navPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(navPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(topnavPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topnavPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(navPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void battleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_battleButtonActionPerformed
        // TODO add your handling code here:
        navCard.show(navPanel, "altCard");
    }//GEN-LAST:event_battleButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        menuCard.show(contentPanel, "newsCard");
        navCard.show(navPanel, "testCard");
    }//GEN-LAST:event_homeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton battleButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel navPanel;
    private javax.swing.JLabel newsLabel;
    private javax.swing.JPanel newsPanel;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JPanel topnavPanel;
    // End of variables declaration//GEN-END:variables

}
