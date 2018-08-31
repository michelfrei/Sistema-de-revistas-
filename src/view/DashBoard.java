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
import javax.swing.UnsupportedLookAndFeelException;

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
        ComboBoxEspecificacao();
        
        
    }
    
    private void limpaCampos(){
                campoTitulo.setText("");
                campoData.setText("");
                campoQuantidade.setText("");
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
    private void ComboBoxEspecificacao(){
        try{
            String SQL = "Select * from sys.Especificação "; /*order by Nome*/
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                String Nome = rs.getString("Nome");
                ComboBoxEspecificacao.addItem(Nome);
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
        BotaoAdicionarRevista1 = new javax.swing.JLabel();
        BotaoAdicionarRevista2 = new javax.swing.JLabel();
        PaneMae = new javax.swing.JPanel();
        AdicionarRevista = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        ComboBoxOrigem = new javax.swing.JComboBox<>();
        campoData = new javax.swing.JFormattedTextField();
        lblData = new javax.swing.JLabel();
        lblOrigem = new javax.swing.JLabel();
        BotaoRemoverRevistas = new javax.swing.JButton();
        BotaoBuscaRevistas = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblOrigem1 = new javax.swing.JLabel();
        ComboBoxClassificacao = new javax.swing.JComboBox<>();
        BotaoSalvaRevistas = new javax.swing.JButton();
        campoTitulo = new javax.swing.JTextField();
        BotaoAlterarRevistas = new javax.swing.JButton();
        BotaoTeste = new javax.swing.JTextField();
        campoQuantidade = new javax.swing.JTextField();
        ComboBoxEspecificacao = new javax.swing.JComboBox<>();
        BotaoSubtrairRevistas = new javax.swing.JButton();
        BotaoNovaRevista = new javax.swing.JButton();
        lblEspecificacao = new javax.swing.JLabel();
        lblQuantidade = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        campoTitulo1 = new javax.swing.JTextField();
        ComboBoxEspecificacao1 = new javax.swing.JComboBox<>();
        ComboBoxClassificacao1 = new javax.swing.JComboBox<>();
        lblOrigem2 = new javax.swing.JLabel();
        lblEspecificacao1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        PaneClassificacao = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

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
        BotaoAdicionarRevista.setIcon(new javax.swing.ImageIcon("C:\\Users\\Michel\\Desktop\\Magazine_30px-teste7.png")); // NOI18N
        BotaoAdicionarRevista.setText("Adicionar nova revista");
        BotaoAdicionarRevista.setOpaque(true);
        BotaoAdicionarRevista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoAdicionarRevistaMouseClicked(evt);
            }
        });

        BotaoAdicionarRevista1.setBackground(new java.awt.Color(37, 103, 125));
        BotaoAdicionarRevista1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoAdicionarRevista1.setForeground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarRevista1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoAdicionarRevista1.setText("Adicionar revistas");
        BotaoAdicionarRevista1.setOpaque(true);

        BotaoAdicionarRevista2.setBackground(new java.awt.Color(37, 103, 125));
        BotaoAdicionarRevista2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoAdicionarRevista2.setForeground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarRevista2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoAdicionarRevista2.setText("Adicionar revistas");
        BotaoAdicionarRevista2.setOpaque(true);

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
            .addComponent(BotaoAdicionarRevista, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarRevista2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarRevista1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(BotaoAdicionarRevista2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAdicionarRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAdicionarClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(373, Short.MAX_VALUE))
        );

        PaneMae.setBackground(new java.awt.Color(255, 255, 255));
        PaneMae.setLayout(new java.awt.CardLayout());

        AdicionarRevista.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setDoubleBuffered(false);
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(44, 106, 129));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Adicionar nova revista");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        ComboBoxOrigem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxOrigem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doação", "Aquisição" }));
        ComboBoxOrigem.setBorder(null);
        ComboBoxOrigem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboBoxOrigem.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxOrigem.setEditor(null);
        ComboBoxOrigem.setFocusCycleRoot(true);
        ComboBoxOrigem.setFocusable(false);
        ComboBoxOrigem.setLightWeightPopupEnabled(false);
        ComboBoxOrigem.setMinimumSize(new java.awt.Dimension(25, 25));

        try {
            campoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData.setText("Data");

        lblOrigem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem.setText("Origem");

        BotaoRemoverRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoRemoverRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoRemoverRevistas.setText("Remover revista");
        BotaoRemoverRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        BotaoBuscaRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaRevistas.setText("Busca revista");
        BotaoBuscaRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("Titulo da revista");

        lblOrigem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem1.setText("Área");

        ComboBoxClassificacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxClassificacao.setToolTipText("Selecione");
        ComboBoxClassificacao.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxClassificacao.setDoubleBuffered(true);
        ComboBoxClassificacao.setEditor(null);
        ComboBoxClassificacao.setFocusable(false);
        ComboBoxClassificacao.setMinimumSize(new java.awt.Dimension(25, 25));
        ComboBoxClassificacao.setName("Selecione"); // NOI18N

        BotaoSalvaRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoSalvaRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoSalvaRevistas.setText("Adicionar revista");
        BotaoSalvaRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvaRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvaRevistasActionPerformed(evt);
            }
        });

        campoTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTitulo.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTitulo.setMinimumSize(new java.awt.Dimension(25, 25));

        BotaoAlterarRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAlterarRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoAlterarRevistas.setText("Alterar revista");
        BotaoAlterarRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        campoQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoQuantidade.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), null));
        campoQuantidade.setMaximumSize(new java.awt.Dimension(25, 25));
        campoQuantidade.setMinimumSize(new java.awt.Dimension(25, 25));
        campoQuantidade.setPreferredSize(new java.awt.Dimension(25, 25));

        ComboBoxEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEspecificacao.setToolTipText("Selecione");
        ComboBoxEspecificacao.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxEspecificacao.setFocusable(false);
        ComboBoxEspecificacao.setMinimumSize(new java.awt.Dimension(25, 25));

        BotaoSubtrairRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoSubtrairRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoSubtrairRevistas.setText("Subtrair revista");
        BotaoSubtrairRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        BotaoNovaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoNovaRevista.setIcon(new javax.swing.ImageIcon("C:\\Users\\Michel\\Desktop\\Save Close_30px-btsalvar.png")); // NOI18N
        BotaoNovaRevista.setText("Nova Revista");
        BotaoNovaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaRevistaActionPerformed(evt);
            }
        });

        lblEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao.setText("Especificação");

        lblQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblQuantidade.setText("Quantidade");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(ComboBoxClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(ComboBoxEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTitulo)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblOrigem)
                                        .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(198, 198, 198)
                                            .addComponent(lblOrigem1)))
                                    .addGap(227, 227, 227)
                                    .addComponent(lblEspecificacao)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(BotaoRemoverRevistas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(BotaoBuscaRevistas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(117, 117, 117)
                                .addComponent(BotaoSalvaRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(BotaoAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoSubtrairRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoTeste, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(399, 399, 399)
                        .addComponent(BotaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData)
                    .addComponent(lblOrigem1)
                    .addComponent(lblEspecificacao)
                    .addComponent(lblQuantidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxEspecificacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBoxClassificacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoData)))
                .addGap(17, 17, 17)
                .addComponent(lblOrigem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(58, 58, 58)
                .addComponent(BotaoNovaRevista)
                .addGap(103, 103, 103)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoSubtrairRevistas)
                    .addComponent(BotaoTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAlterarRevistas)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(BotaoRemoverRevistas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoBuscaRevistas))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(BotaoSalvaRevistas)))
                .addGap(442, 442, 442))
        );

        jTabbedPane1.addTab("Nova revista", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo1.setText("Titulo da revista");

        campoTitulo1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTitulo1.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTitulo1.setMinimumSize(new java.awt.Dimension(25, 25));

        ComboBoxEspecificacao1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEspecificacao1.setToolTipText("Selecione");
        ComboBoxEspecificacao1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxEspecificacao1.setFocusable(false);
        ComboBoxEspecificacao1.setMinimumSize(new java.awt.Dimension(25, 25));

        ComboBoxClassificacao1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxClassificacao1.setToolTipText("Selecione");
        ComboBoxClassificacao1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxClassificacao1.setDoubleBuffered(true);
        ComboBoxClassificacao1.setEditor(null);
        ComboBoxClassificacao1.setFocusable(false);
        ComboBoxClassificacao1.setMinimumSize(new java.awt.Dimension(25, 25));
        ComboBoxClassificacao1.setName("Selecione"); // NOI18N

        lblOrigem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem2.setText("Área");

        lblEspecificacao1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao1.setText("Especificação");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel4.setText("Faça as alterações na revista");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel5.setText("Selecione a revista");

        jSeparator2.setForeground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTitulo1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(campoTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(ComboBoxClassificacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(93, 93, 93)
                            .addComponent(ComboBoxEspecificacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(lblOrigem2)
                            .addGap(227, 227, 227)
                            .addComponent(lblEspecificacao1))))
                .addGap(66, 66, 66))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEspecificacao1)
                    .addComponent(lblOrigem2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxEspecificacao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(ComboBoxClassificacao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(699, 699, 699))
        );

        jTabbedPane1.addTab("Alterar revistas", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab4", jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 962, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab5", jPanel9);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout AdicionarRevistaLayout = new javax.swing.GroupLayout(AdicionarRevista);
        AdicionarRevista.setLayout(AdicionarRevistaLayout);
        AdicionarRevistaLayout.setHorizontalGroup(
            AdicionarRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AdicionarRevistaLayout.setVerticalGroup(
            AdicionarRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdicionarRevistaLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PaneMae.add(AdicionarRevista, "card3");

        PaneClassificacao.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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
                .addContainerGap(389, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 660, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneClassificacaoLayout = new javax.swing.GroupLayout(PaneClassificacao);
        PaneClassificacao.setLayout(PaneClassificacaoLayout);
        PaneClassificacaoLayout.setHorizontalGroup(
            PaneClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneClassificacaoLayout.setVerticalGroup(
            PaneClassificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneClassificacaoLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        PaneMae.add(PaneClassificacao, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SideBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PaneMae, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaneMae, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(SideBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
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
    
    //Aqui cria-se uma nova revista
    private void BotaoNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaRevistaActionPerformed
         if(campoTitulo.getText().isEmpty() || campoData.getText().isEmpty() /*|| campoEspecificacao.getText().isEmpty() || campoClassificacao.getText().isEmpty()*/ || campoQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Há campos não preenchidos", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            Revistas rev = new Revistas();
            try{

                rev.getID();
                rev.setTitulo(campoTitulo.getText());
                rev.setEspecificacao((String)ComboBoxEspecificacao.getSelectedItem());
                rev.setClassificacao((String) ComboBoxClassificacao.getSelectedItem());
                rev.setData(campoData.getText());
                rev.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                rev.setOrigem((String) ComboBoxOrigem.getSelectedItem());

                RevistaDAO revistaDAO = new RevistaDAO();
                revistaDAO.InserirNovaRevistas(rev);
                JOptionPane.showMessageDialog(null, "Revista criada com sucesso", "Sistema", JOptionPane.INFORMATION_MESSAGE);

                limpaCampos();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Azedou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_BotaoNovaRevistaActionPerformed

    private void BotaoSalvaRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvaRevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoSalvaRevistasActionPerformed
    
    public void setLblColor(JLabel lbl){
        lbl.setBackground(new Color(150, 150,150));
    }
    public void ResetColor(JLabel lbl){
        lbl.setBackground(new Color(37,103,125));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdicionarRevista;
    private javax.swing.JLabel BotaoAdicionarClasse;
    private javax.swing.JLabel BotaoAdicionarRevista;
    private javax.swing.JLabel BotaoAdicionarRevista1;
    private javax.swing.JLabel BotaoAdicionarRevista2;
    private javax.swing.JButton BotaoAlterarRevistas;
    private javax.swing.JButton BotaoBuscaRevistas;
    private javax.swing.JButton BotaoNovaRevista;
    private javax.swing.JButton BotaoRemoverRevistas;
    private javax.swing.JButton BotaoSalvaRevistas;
    private javax.swing.JButton BotaoSubtrairRevistas;
    private javax.swing.JTextField BotaoTeste;
    private javax.swing.JComboBox<String> ComboBoxClassificacao;
    private javax.swing.JComboBox<String> ComboBoxClassificacao1;
    private javax.swing.JComboBox<String> ComboBoxEspecificacao;
    private javax.swing.JComboBox<String> ComboBoxEspecificacao1;
    private javax.swing.JComboBox<String> ComboBoxOrigem;
    private javax.swing.JPanel PaneClassificacao;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel SideBoard;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField campoData;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoTitulo;
    private javax.swing.JTextField campoTitulo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblEspecificacao;
    private javax.swing.JLabel lblEspecificacao1;
    private javax.swing.JLabel lblOrigem;
    private javax.swing.JLabel lblOrigem1;
    private javax.swing.JLabel lblOrigem2;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    // End of variables declaration//GEN-END:variables

}
