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
        setLblColor(BotaoAdicionarRevista);
        ComboBoxAreaNovaRevista();
        ComboBoxEspecificacaoNovaRevista();
        ComboBoxAreaAlterarRevistas();
        ComboBoxEspecificacaoAlterarRevistas();
        
        
    }
    
    private void limpaCampos(){
                campoTitulo.setText("");
                campoData.setText("");
                campoQuantidade.setText("");
        }
    
    //Pane Guia Nova revista
    private void ComboBoxAreaNovaRevista(){
        try{
            String SQL = "Select * from sys.Especificação "; /*order by Nome*/
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                String Nome = rs.getString("Nome");
                ComboBoxEspecificacaoNovaRevista.addItem(Nome);
            }
            
            
        }catch(Exception e){
            System.out.println("problema na combobox");
        }
    }
    
    private void ComboBoxEspecificacaoNovaRevista(){
         try{
            String SQL = "Select * from sys.Classificação"; 
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                String Nome = rs.getString("Nome");
                ComboBoxAreaNovaRevista.addItem(Nome);
            }
            
            
        }catch(Exception e){
            System.out.println("problema na combobox");
        }
    }
    
    
    //Pane Guia Altera Revista
    private void ComboBoxAreaAlterarRevistas(){
         try{
            String SQL = "Select * from sys.Especificação "; /*order by Nome*/
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                String Nome = rs.getString("Nome");
                ComboBoxAreaAlterarRevistas.addItem(Nome);
            }
            
            
        }catch(Exception e){
            System.out.println("problema na combobox");
        }
    }
    
    private void ComboBoxEspecificacaoAlterarRevistas(){
        try{
            String SQL = "Select * from sys.Classificação"; 
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                String Nome = rs.getString("Nome");
                ComboBoxEspecificacaoAlterarRevistas.addItem(Nome);
            }
            
            
        }catch(Exception e){
            System.out.println("problema na combobox");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        DialogGuiaAlterRevista = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
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
        PaneGuiaNovaRevista = new javax.swing.JPanel();
        ComboBoxOrigem = new javax.swing.JComboBox<>();
        campoData = new javax.swing.JFormattedTextField();
        lblData = new javax.swing.JLabel();
        lblOrigem = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblOrigem1 = new javax.swing.JLabel();
        ComboBoxAreaNovaRevista = new javax.swing.JComboBox<>();
        campoTitulo = new javax.swing.JTextField();
        campoQuantidade = new javax.swing.JTextField();
        ComboBoxEspecificacaoNovaRevista = new javax.swing.JComboBox<>();
        BotaoNovaRevista = new javax.swing.JButton();
        lblEspecificacao = new javax.swing.JLabel();
        lblQuantidade = new javax.swing.JLabel();
        PaneGuiaAlteraRevista = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        ComboBoxOrigem1 = new javax.swing.JComboBox<>();
        lblOrigem3 = new javax.swing.JLabel();
        campoData1 = new javax.swing.JFormattedTextField();
        lblData1 = new javax.swing.JLabel();
        campoTitulo2 = new javax.swing.JTextField();
        lblTitulo2 = new javax.swing.JLabel();
        ComboBoxAreaAlterarRevistas = new javax.swing.JComboBox<>();
        lblOrigem4 = new javax.swing.JLabel();
        ComboBoxEspecificacaoAlterarRevistas = new javax.swing.JComboBox<>();
        lblEspecificacao2 = new javax.swing.JLabel();
        lblQuantidade1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        BotaoBuscaRevistas1 = new javax.swing.JButton();
        BotaoAlterarRevistas = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        BotaoBuscaRevistas = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        BotaoSalvaRevistas = new javax.swing.JButton();
        BotaoRemoverRevistas = new javax.swing.JButton();
        BotaoSubtrairRevistas = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        PaneClassificacao = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DialogGuiaAlterRevistaLayout = new javax.swing.GroupLayout(DialogGuiaAlterRevista.getContentPane());
        DialogGuiaAlterRevista.getContentPane().setLayout(DialogGuiaAlterRevistaLayout);
        DialogGuiaAlterRevistaLayout.setHorizontalGroup(
            DialogGuiaAlterRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogGuiaAlterRevistaLayout.setVerticalGroup(
            DialogGuiaAlterRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
            .addComponent(BotaoAdicionarRevista, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarRevista2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarRevista1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarClasse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        SideBoardLayout.setVerticalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
        jLabel2.setText("Menu revistas");

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
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);

        PaneGuiaNovaRevista.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaNovaRevista.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

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

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("Titulo da revista");

        lblOrigem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem1.setText("Área");

        ComboBoxAreaNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxAreaNovaRevista.setToolTipText("Selecione");
        ComboBoxAreaNovaRevista.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxAreaNovaRevista.setDoubleBuffered(true);
        ComboBoxAreaNovaRevista.setEditor(null);
        ComboBoxAreaNovaRevista.setFocusable(false);
        ComboBoxAreaNovaRevista.setMinimumSize(new java.awt.Dimension(25, 25));
        ComboBoxAreaNovaRevista.setName("Selecione"); // NOI18N
        ComboBoxAreaNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxAreaNovaRevistaActionPerformed(evt);
            }
        });

        campoTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTitulo.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTitulo.setMinimumSize(new java.awt.Dimension(25, 25));

        campoQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoQuantidade.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), null));
        campoQuantidade.setMaximumSize(new java.awt.Dimension(25, 25));
        campoQuantidade.setMinimumSize(new java.awt.Dimension(25, 25));
        campoQuantidade.setPreferredSize(new java.awt.Dimension(25, 25));

        ComboBoxEspecificacaoNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEspecificacaoNovaRevista.setToolTipText("Selecione");
        ComboBoxEspecificacaoNovaRevista.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxEspecificacaoNovaRevista.setFocusable(false);
        ComboBoxEspecificacaoNovaRevista.setMinimumSize(new java.awt.Dimension(25, 25));

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

        javax.swing.GroupLayout PaneGuiaNovaRevistaLayout = new javax.swing.GroupLayout(PaneGuiaNovaRevista);
        PaneGuiaNovaRevista.setLayout(PaneGuiaNovaRevistaLayout);
        PaneGuiaNovaRevistaLayout.setHorizontalGroup(
            PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaNovaRevistaLayout.createSequentialGroup()
                        .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData)
                            .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                                .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(ComboBoxAreaNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTitulo)
                            .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                                    .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblOrigem)
                                        .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                                            .addGap(198, 198, 198)
                                            .addComponent(lblOrigem1)))
                                    .addGap(227, 227, 227)
                                    .addComponent(lblEspecificacao)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaNovaRevistaLayout.createSequentialGroup()
                        .addComponent(BotaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(353, 353, 353))))
        );
        PaneGuiaNovaRevistaLayout.setVerticalGroup(
            PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData)
                    .addComponent(lblOrigem1)
                    .addComponent(lblEspecificacao)
                    .addComponent(lblQuantidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ComboBoxAreaNovaRevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addComponent(lblOrigem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotaoNovaRevista)
                .addGap(732, 732, 732))
        );

        jTabbedPane1.addTab("Nova revista", PaneGuiaNovaRevista);

        PaneGuiaAlteraRevista.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 44)); // NOI18N
        jLabel4.setText("Faça as alterações na revista");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 44)); // NOI18N
        jLabel5.setText("Escolha uma revista para alterar:");

        jSeparator2.setForeground(new java.awt.Color(0, 102, 102));

        ComboBoxOrigem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxOrigem1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doação", "Aquisição" }));
        ComboBoxOrigem1.setBorder(null);
        ComboBoxOrigem1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboBoxOrigem1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxOrigem1.setEditor(null);
        ComboBoxOrigem1.setFocusCycleRoot(true);
        ComboBoxOrigem1.setFocusable(false);
        ComboBoxOrigem1.setLightWeightPopupEnabled(false);
        ComboBoxOrigem1.setMinimumSize(new java.awt.Dimension(25, 25));

        lblOrigem3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem3.setText("Origem");

        try {
            campoData1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoData1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoData1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblData1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData1.setText("Data");

        campoTitulo2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTitulo2.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTitulo2.setMinimumSize(new java.awt.Dimension(25, 25));

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo2.setText("Titulo da revista");

        ComboBoxAreaAlterarRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxAreaAlterarRevistas.setToolTipText("Selecione");
        ComboBoxAreaAlterarRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxAreaAlterarRevistas.setDoubleBuffered(true);
        ComboBoxAreaAlterarRevistas.setEditor(null);
        ComboBoxAreaAlterarRevistas.setFocusable(false);
        ComboBoxAreaAlterarRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        ComboBoxAreaAlterarRevistas.setName("Selecione"); // NOI18N
        ComboBoxAreaAlterarRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxAreaAlterarRevistasActionPerformed(evt);
            }
        });

        lblOrigem4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem4.setText("Área");

        ComboBoxEspecificacaoAlterarRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEspecificacaoAlterarRevistas.setToolTipText("Selecione");
        ComboBoxEspecificacaoAlterarRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxEspecificacaoAlterarRevistas.setFocusable(false);
        ComboBoxEspecificacaoAlterarRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        ComboBoxEspecificacaoAlterarRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEspecificacaoAlterarRevistasActionPerformed(evt);
            }
        });

        lblEspecificacao2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao2.setText("Especificação");

        lblQuantidade1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblQuantidade1.setText("ID");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setEnabled(false);

        BotaoBuscaRevistas1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaRevistas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_30px.png"))); // NOI18N
        BotaoBuscaRevistas1.setText("Busca revista");
        BotaoBuscaRevistas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaRevistas1ActionPerformed(evt);
            }
        });

        BotaoAlterarRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAlterarRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoAlterarRevistas.setText("Alterar revista");
        BotaoAlterarRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout PaneGuiaAlteraRevistaLayout = new javax.swing.GroupLayout(PaneGuiaAlteraRevista);
        PaneGuiaAlteraRevista.setLayout(PaneGuiaAlteraRevistaLayout);
        PaneGuiaAlteraRevistaLayout.setHorizontalGroup(
            PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BotaoAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(388, 388, 388))
            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoBuscaRevistas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo2)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(campoTitulo2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                    .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblData1)
                                        .addComponent(campoData1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(70, 70, 70)
                                    .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ComboBoxAreaAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblOrigem4))
                                    .addGap(71, 71, 71)
                                    .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblEspecificacao2)
                                        .addComponent(ComboBoxEspecificacaoAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblOrigem3)
                                        .addComponent(ComboBoxOrigem1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addComponent(lblQuantidade1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(82, 82, 82))
        );
        PaneGuiaAlteraRevistaLayout.setVerticalGroup(
            PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(BotaoBuscaRevistas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidade1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblTitulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData1)
                    .addComponent(lblOrigem4)
                    .addComponent(lblEspecificacao2)
                    .addComponent(lblOrigem3))
                .addGap(18, 18, 18)
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxAreaAlterarRevistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBoxEspecificacaoAlterarRevistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBoxOrigem1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(BotaoAlterarRevistas)
                .addGap(578, 578, 578))
        );

        jTabbedPane1.addTab("Alterar revistas", PaneGuiaAlteraRevista);

        BotaoBuscaRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaRevistas.setText("Busca revista");
        BotaoBuscaRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(BotaoBuscaRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(420, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(BotaoBuscaRevistas)
                .addContainerGap(827, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel7);

        BotaoSalvaRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoSalvaRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoSalvaRevistas.setText("Adicionar revista");
        BotaoSalvaRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        BotaoRemoverRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoRemoverRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoRemoverRevistas.setText("Remover revista");
        BotaoRemoverRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        BotaoSubtrairRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoSubtrairRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoSubtrairRevistas.setText("Subtrair revista");
        BotaoSubtrairRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(419, 419, 419)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotaoRemoverRevistas)
                    .addComponent(BotaoSalvaRevistas)
                    .addComponent(BotaoSubtrairRevistas))
                .addContainerGap(437, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(BotaoSalvaRevistas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoSubtrairRevistas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoRemoverRevistas)
                .addContainerGap(666, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 989, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
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
                .addContainerGap(416, Short.MAX_VALUE))
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
            .addComponent(PaneMae, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
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
                rev.setEspecificacao((String)ComboBoxEspecificacaoNovaRevista.getSelectedItem());
                rev.setClassificacao((String) ComboBoxAreaNovaRevista.getSelectedItem());
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

    private void BotaoBuscaRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscaRevistas1ActionPerformed
        DialogGuiaAlterRevista.setVisible(true);
    }//GEN-LAST:event_BotaoBuscaRevistas1ActionPerformed

    private void ComboBoxAreaNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAreaNovaRevistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAreaNovaRevistaActionPerformed

    private void ComboBoxAreaAlterarRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAreaAlterarRevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAreaAlterarRevistasActionPerformed

    private void ComboBoxEspecificacaoAlterarRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEspecificacaoAlterarRevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEspecificacaoAlterarRevistasActionPerformed
    
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
    private javax.swing.JButton BotaoBuscaRevistas1;
    private javax.swing.JButton BotaoNovaRevista;
    private javax.swing.JButton BotaoRemoverRevistas;
    private javax.swing.JButton BotaoSalvaRevistas;
    private javax.swing.JButton BotaoSubtrairRevistas;
    private javax.swing.JComboBox<String> ComboBoxAreaAlterarRevistas;
    private javax.swing.JComboBox<String> ComboBoxAreaNovaRevista;
    private javax.swing.JComboBox<String> ComboBoxEspecificacaoAlterarRevistas;
    private javax.swing.JComboBox<String> ComboBoxEspecificacaoNovaRevista;
    private javax.swing.JComboBox<String> ComboBoxOrigem;
    private javax.swing.JComboBox<String> ComboBoxOrigem1;
    private javax.swing.JDialog DialogGuiaAlterRevista;
    private javax.swing.JPanel PaneClassificacao;
    private javax.swing.JPanel PaneGuiaAlteraRevista;
    private javax.swing.JPanel PaneGuiaNovaRevista;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel SideBoard;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField campoData;
    private javax.swing.JFormattedTextField campoData1;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoTitulo;
    private javax.swing.JTextField campoTitulo2;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblData1;
    private javax.swing.JLabel lblEspecificacao;
    private javax.swing.JLabel lblEspecificacao2;
    private javax.swing.JLabel lblOrigem;
    private javax.swing.JLabel lblOrigem1;
    private javax.swing.JLabel lblOrigem3;
    private javax.swing.JLabel lblOrigem4;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblQuantidade1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo2;
    // End of variables declaration//GEN-END:variables

}
