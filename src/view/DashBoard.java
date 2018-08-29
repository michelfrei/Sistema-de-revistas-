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
        ComboBoxEspecificacao();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblEspecificacao = new javax.swing.JLabel();
        campoTitulo = new javax.swing.JTextField();
        lblOrigem = new javax.swing.JLabel();
        lblQuantidade = new javax.swing.JLabel();
        campoQuantidade = new javax.swing.JTextField();
        lblData = new javax.swing.JLabel();
        BotaoNovaRevista = new javax.swing.JButton();
        lblOrigem1 = new javax.swing.JLabel();
        ComboBoxOrigem = new javax.swing.JComboBox<>();
        campoData = new javax.swing.JFormattedTextField();
        ComboBoxClassificacao = new javax.swing.JComboBox<>();
        ComboBoxEspecificacao = new javax.swing.JComboBox<>();
        BotaoSalvaRevistas = new javax.swing.JButton();
        BotaoSubtrairRevistas = new javax.swing.JButton();
        BotaoRemoverRevistas = new javax.swing.JButton();
        BotaoAlterarRevistas = new javax.swing.JButton();
        BotaoTeste = new javax.swing.JTextField();
        BotaoBuscaRevistas = new javax.swing.JButton();
        PaneClassificacao = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneMae.setBackground(new java.awt.Color(255, 255, 255));
        PaneMae.setLayout(new java.awt.CardLayout());

        AdicionarRevista.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setDoubleBuffered(false);
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTitulo.setText("Titulo da revista");

        lblEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEspecificacao.setText("Especificação");

        campoTitulo.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTitulo.setMinimumSize(new java.awt.Dimension(25, 25));

        lblOrigem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblOrigem.setText("Origem");

        lblQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQuantidade.setText("Quantidade");

        campoQuantidade.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), null));
        campoQuantidade.setMaximumSize(new java.awt.Dimension(25, 25));
        campoQuantidade.setMinimumSize(new java.awt.Dimension(25, 25));
        campoQuantidade.setPreferredSize(new java.awt.Dimension(25, 25));

        lblData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblData.setText("Data");

        BotaoNovaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoNovaRevista.setText("Nova Revista");
        BotaoNovaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaRevistaActionPerformed(evt);
            }
        });

        lblOrigem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblOrigem1.setText("Área");

        ComboBoxOrigem.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        ComboBoxOrigem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doação", "Aquisição" }));
        ComboBoxOrigem.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ComboBoxOrigem.setFocusable(false);
        ComboBoxOrigem.setMinimumSize(new java.awt.Dimension(25, 25));

        try {
            campoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ComboBoxClassificacao.setToolTipText("Selecione");
        ComboBoxClassificacao.setMinimumSize(new java.awt.Dimension(25, 25));

        ComboBoxEspecificacao.setToolTipText("Selecione");
        ComboBoxEspecificacao.setMinimumSize(new java.awt.Dimension(25, 25));
        ComboBoxEspecificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEspecificacaoActionPerformed(evt);
            }
        });

        BotaoSalvaRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoSalvaRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoSalvaRevistas.setText("Adicionar revista");
        BotaoSalvaRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvaRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvaRevistasActionPerformed(evt);
            }
        });

        BotaoSubtrairRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoSubtrairRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoSubtrairRevistas.setText("Subtrair revista");
        BotaoSubtrairRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSubtrairRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSubtrairRevistasActionPerformed(evt);
            }
        });

        BotaoRemoverRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoRemoverRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoRemoverRevistas.setText("Remover revista");
        BotaoRemoverRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoRemoverRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverRevistasActionPerformed(evt);
            }
        });

        BotaoAlterarRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAlterarRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoAlterarRevistas.setText("Alterar revista");
        BotaoAlterarRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarRevistasActionPerformed(evt);
            }
        });

        BotaoBuscaRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaRevistas.setText("Busca revista");
        BotaoBuscaRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaRevistasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTitulo)
                            .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboBoxClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOrigem1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOrigem))
                                .addGap(98, 98, 98)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblData))
                                .addGap(19, 19, 19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQuantidade)
                            .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEspecificacao)
                            .addComponent(ComboBoxEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(BotaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BotaoSalvaRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BotaoSubtrairRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BotaoBuscaRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BotaoTeste)
                            .addComponent(BotaoRemoverRevistas, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(BotaoAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(lblQuantidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblOrigem1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoSalvaRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoSubtrairRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoRemoverRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEspecificacao)
                            .addComponent(lblData)
                            .addComponent(lblOrigem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboBoxEspecificacao, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(campoData)
                            .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addGap(102, 102, 102)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoBuscaRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(472, 472, 472))
        );

        javax.swing.GroupLayout AdicionarRevistaLayout = new javax.swing.GroupLayout(AdicionarRevista);
        AdicionarRevista.setLayout(AdicionarRevistaLayout);
        AdicionarRevistaLayout.setHorizontalGroup(
            AdicionarRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AdicionarRevistaLayout.setVerticalGroup(
            AdicionarRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdicionarRevistaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(335, Short.MAX_VALUE))
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
            .addGap(0, 702, Short.MAX_VALUE)
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
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(PaneMae, javax.swing.GroupLayout.PREFERRED_SIZE, 849, Short.MAX_VALUE)
            .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void BotaoNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaRevistaActionPerformed
        if(campoTitulo.getText().isEmpty() || campoData.getText().isEmpty() /*|| campoEspecificacao.getText().isEmpty() || campoClassificacao.getText().isEmpty()*/ || campoQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Há campos não preenchidos", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            Revistas rev = new Revistas();
            try{

                rev.getID();
                rev.setTitulo(campoTitulo.getText());
                rev.setEspecificacao((String )ComboBoxEspecificacao.getSelectedItem());
                rev.setClassificacao((String) ComboBoxClassificacao.getSelectedItem());
                rev.setData(campoData.getText());
                rev.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                rev.setOrigem((String) ComboBoxOrigem.getSelectedItem());

                RevistaDAO revistaDAO = new RevistaDAO();
                revistaDAO.InserirNovaRevistas(rev);
                
                campoTitulo.setText("");
                campoData.setText("");
                campoQuantidade.setText("");

            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Azedou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_BotaoNovaRevistaActionPerformed

    private void ComboBoxEspecificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEspecificacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEspecificacaoActionPerformed

    private void BotaoSalvaRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvaRevistasActionPerformed
        Revistas rev = new Revistas();
        
        try{
        rev.setTitulo(campoTitulo.getText());
        rev.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
        
        RevistaDAO revistaDAO = new RevistaDAO();
        if(!revistaDAO.InserirRevista(rev)){
            JOptionPane.showMessageDialog(null, "Não foi possivel inserir as Revistas, caiu no catch do BotaoSalvaRevistasActionPerformed", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        
        campoTitulo.setText("");
        campoQuantidade.setText("");
        
        } catch(Exception e){
            System.out.println("Não foi possivel inserir as Revistas, caiu no catch do BotaoSalvaRevistasActionPerformed");
        }
    }//GEN-LAST:event_BotaoSalvaRevistasActionPerformed

    private void BotaoSubtrairRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSubtrairRevistasActionPerformed
        Revistas rev = new Revistas();
        
        try{
        rev.setTitulo(campoTitulo.getText());
        rev.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
        
        
        RevistaDAO revistaDAO = new RevistaDAO();
        if(!revistaDAO.SubtrairRevista(rev)){
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir as Revistas, caiu no catch do BotaoSalvaRevistasActionPerformed", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        
        campoTitulo.setText("");
        campoQuantidade.setText("");
        
        } catch(Exception e){
            System.out.println("Não foi possivel inserir as Revistas, caiu no catch do BotaoSalvaRevistasActionPerformed");
        }
    }//GEN-LAST:event_BotaoSubtrairRevistasActionPerformed

    private void BotaoRemoverRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverRevistasActionPerformed
        Revistas rev = new Revistas();
        
        try{
        rev.setTitulo(campoTitulo.getText());
        
        RevistaDAO revistaDAO = new RevistaDAO();
        if(!revistaDAO.RemoverRevista(rev)){
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir as Revistas, caiu no catch do BotaoSalvaRevistasActionPerformed", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        
        campoTitulo.setText("");
        campoQuantidade.setText("");
        
        } catch(Exception e){
            System.out.println("Não foi possivel inserir as Revistas, caiu no catch do BotaoSalvaRevistasActionPerformed");
        }
    }//GEN-LAST:event_BotaoRemoverRevistasActionPerformed

    private void BotaoAlterarRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarRevistasActionPerformed
        Revistas rev = new Revistas();
        
        try{
        rev.setTitulo(campoTitulo.getText());
        
        RevistaDAO revistaDAO = new RevistaDAO();
        revistaDAO.BuscarRevista(rev);
        
        //if(!revistaDAO.BuscarRevista(rev)){
           // JOptionPane.showMessageDialog(null, "Não foi possivel excluir as Revistas, caiu no catch do BotaoSalvaRevistasActionPerformed", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        //}
        /*
        campoTitulo.setText("");
        campoQuantidade.setText("");*/
        
        } catch(Exception e){
            System.out.println("Não foi possivel inserir as Revistas, caiu no catch do BotaoSalvaRevistasActionPerformed");
        }
    }//GEN-LAST:event_BotaoAlterarRevistasActionPerformed

    private void BotaoBuscaRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscaRevistasActionPerformed
        Revistas rev = new Revistas();
        
        
        System.out.println("Entrou no buscar revista");
        rev.setTitulo(campoTitulo.getText());
        RevistaDAO revistaDAO = new RevistaDAO();

        try {
            revistaDAO.BuscarRevista(rev);
            
            campoTitulo.setText(rev.getTitulo());
            campoData.setText(rev.getData());
            
            
            
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }//GEN-LAST:event_BotaoBuscaRevistasActionPerformed
    
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
    private javax.swing.JComboBox<String> ComboBoxEspecificacao;
    private javax.swing.JComboBox<String> ComboBoxOrigem;
    private javax.swing.JPanel PaneClassificacao;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel SideBoard;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField campoData;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblEspecificacao;
    private javax.swing.JLabel lblOrigem;
    private javax.swing.JLabel lblOrigem1;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables

}
