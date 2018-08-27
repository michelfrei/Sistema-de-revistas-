/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.Conexao;
import DAO.RevistaDAO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Revistas;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michel
 */

    


public class DashBoard extends javax.swing.JFrame {
    
    
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public DashBoard() {
        initComponents();
        ComboBoxClassificacao();
    }
    
    private void ComboBoxClassificacao(){
        try{
            String SQL = "Select * from sys.Classificação"; 
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                String Nome = rs.getString("Nome");
                ComboBoxClassificacao.addItem(Nome);
            }
            
            
        }catch(Exception e){
            System.out.println("problema na combobox");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        SideBoard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        BotaoAdicionarClasse = new javax.swing.JLabel();
        BotaoAdicionarRevista = new javax.swing.JLabel();
        PaneMae = new javax.swing.JPanel();
        AdicionarRevista = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        campoEspecificacao = new javax.swing.JTextField();
        lblEspecificacao = new javax.swing.JLabel();
        campoTitulo = new javax.swing.JTextField();
        lblOrigem = new javax.swing.JLabel();
        lblQuantidade = new javax.swing.JLabel();
        campoQuantidade = new javax.swing.JTextField();
        lblData = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        lblOrigem1 = new javax.swing.JLabel();
        ComboBoxOrigem = new javax.swing.JComboBox<>();
        campoData = new javax.swing.JFormattedTextField();
        ComboBoxClassificacao = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        PaneClassificacao = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        SideBoard.setBackground(new java.awt.Color(37, 103, 125));
        SideBoard.setForeground(new java.awt.Color(37, 103, 125));

        jLabel1.setFont(new java.awt.Font("Utsaah", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("UEMG Revistas");

        BotaoAdicionarClasse.setBackground(new java.awt.Color(37, 103, 125));
        BotaoAdicionarClasse.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        BotaoAdicionarClasse.setForeground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarClasse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoAdicionarClasse.setText("Adicionar e consultar classificações");
        BotaoAdicionarClasse.setMaximumSize(new java.awt.Dimension(131, 25));
        BotaoAdicionarClasse.setMinimumSize(new java.awt.Dimension(131, 25));
        BotaoAdicionarClasse.setOpaque(true);
        BotaoAdicionarClasse.setPreferredSize(new java.awt.Dimension(131, 25));
        BotaoAdicionarClasse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoAdicionarClasseMouseClicked(evt);
            }
        });

        BotaoAdicionarRevista.setBackground(new java.awt.Color(37, 103, 125));
        BotaoAdicionarRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoAdicionarRevista.setForeground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarRevista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoAdicionarRevista.setText("Adicionar e consultar revistas");
        BotaoAdicionarRevista.setOpaque(true);
        BotaoAdicionarRevista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoAdicionarRevistaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout SideBoardLayout = new javax.swing.GroupLayout(SideBoard);
        SideBoard.setLayout(SideBoardLayout);
        SideBoardLayout.setHorizontalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideBoardLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(36, 36, 36))
            .addComponent(BotaoAdicionarRevista, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarClasse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SideBoardLayout.setVerticalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addGap(72, 72, 72)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAdicionarRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAdicionarClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneMae.setBackground(new java.awt.Color(255, 255, 255));
        PaneMae.setLayout(new java.awt.CardLayout());

        AdicionarRevista.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(44, 106, 129));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Adicionar e consultar revistas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(419, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        lblTitulo.setText("Titulo");

        lblEspecificacao.setText("Especificação");

        lblOrigem.setText("Origem");

        lblQuantidade.setText("Quantidade");

        campoQuantidade.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), null));

        lblData.setText("data");

        btnSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        lblOrigem1.setText("Classificação");

        ComboBoxOrigem.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        ComboBoxOrigem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doação", "Aquisição" }));
        ComboBoxOrigem.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ComboBoxOrigem.setFocusable(false);

        try {
            campoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEspecificacao)
                            .addComponent(lblOrigem)
                            .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoQuantidade)
                            .addComponent(lblQuantidade)
                            .addComponent(lblData)
                            .addComponent(campoData, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(lblOrigem1)
                            .addComponent(ComboBoxClassificacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitulo)
                            .addComponent(lblQuantidade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEspecificacao)
                            .addComponent(lblData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(lblOrigem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblOrigem1)
                        .addGap(471, 471, 471))))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 0, 0));

        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"qwe",  new Integer(1), "ad"},
                {"qwe",  new Integer(1), "asdf"},
                {"qwe",  new Integer(11), "gfd"},
                {"qwe",  new Integer(1), null}
            },
            new String [] {
                "Titulo", "Quantidade", "Origem"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 204, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout AdicionarRevistaLayout = new javax.swing.GroupLayout(AdicionarRevista);
        AdicionarRevista.setLayout(AdicionarRevistaLayout);
        AdicionarRevistaLayout.setHorizontalGroup(
            AdicionarRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(AdicionarRevistaLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AdicionarRevistaLayout.setVerticalGroup(
            AdicionarRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdicionarRevistaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(AdicionarRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PaneMae.add(AdicionarRevista, "card3");

        PaneClassificacao.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(44, 106, 129));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Adicionar e consultar classificações");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(330, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PaneClassificacaoLayout = new javax.swing.GroupLayout(PaneClassificacao);
        PaneClassificacao.setLayout(PaneClassificacaoLayout);
        PaneClassificacaoLayout.setHorizontalGroup(
            PaneClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneClassificacaoLayout.setVerticalGroup(
            PaneClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneClassificacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneMae.add(PaneClassificacao, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SideBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaneMae, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PaneMae, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoAdicionarRevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoAdicionarRevistaMouseClicked
        AdicionarRevista.setVisible(true);
        PaneClassificacao.setVisible(false);
        setLblColor(BotaoAdicionarRevista);
        ResetColor(BotaoAdicionarClasse);
    }//GEN-LAST:event_BotaoAdicionarRevistaMouseClicked

    private void BotaoAdicionarClasseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoAdicionarClasseMouseClicked
        AdicionarRevista.setVisible(false);
        PaneClassificacao.setVisible(true);
        setLblColor(BotaoAdicionarClasse);
        ResetColor(BotaoAdicionarRevista);
    }//GEN-LAST:event_BotaoAdicionarClasseMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(campoTitulo.getText().isEmpty() || campoData.getText().isEmpty() || campoEspecificacao.getText().isEmpty() /*|| campoClassificacao.getText().isEmpty()*/ || campoQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Há campos não preenchidos", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            Revistas rev = new Revistas();
            try{

                rev.getID();
                rev.setTitulo(campoTitulo.getText());
                rev.setEspecificacao(campoEspecificacao.getText());
                rev.setClassificacao((String) ComboBoxClassificacao.getSelectedItem());
                rev.setData(campoData.getText());
                rev.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                rev.setOrigem((String) ComboBoxOrigem.getSelectedItem());

                RevistaDAO revistaDAO = new RevistaDAO();
                revistaDAO.InserirRevistas(rev);

                campoTitulo.setText("");
                campoEspecificacao.setText("");
                //campoClassificacao.setText("");
                campoData.setText("");
                campoQuantidade.setText("");

            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Azedou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnSalvarActionPerformed
    
    public void setLblColor(JLabel lbl){
        lbl.setBackground(new Color(150, 150,150));
    }
    public void ResetColor(JLabel lbl){
        lbl.setBackground(new Color(37,103,125));
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdicionarRevista;
    private javax.swing.JLabel BotaoAdicionarClasse;
    private javax.swing.JLabel BotaoAdicionarRevista;
    private javax.swing.JComboBox<String> ComboBoxClassificacao;
    private javax.swing.JComboBox<String> ComboBoxOrigem;
    private javax.swing.JPanel PaneClassificacao;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel SideBoard;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField campoData;
    private javax.swing.JTextField campoEspecificacao;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblEspecificacao;
    private javax.swing.JLabel lblOrigem;
    private javax.swing.JLabel lblOrigem1;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables

}
