/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.Conexao;
import DAO.RevistaDAO;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Revistas;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class DashBoard extends javax.swing.JFrame {

    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Revistas> ListaRevista;

    List<Revistas> ListaBuscaRevista;

    public DashBoard() {
        initComponents();
        setLblColor(BotaoAdicionarRevista);
        ComboBoxAreaNovaRevista();
        ComboBoxEspecificacaoNovaRevista();
        BuscaAreaMenuRevistas();
        BuscaEspecificacaoMenuRevistas();
        atualizarTabelaRevista();
        AlteraAreaMenuRevistas();
        AlteraEspecificacaoMenuRevistas();
        ResetaCamposAlterarRevistas();
        TravaCamposDoNovaRevista();
        limpaCamposNovaRevista();
        TravaCamposDoPesquisarRevistasParaAlteração();
    }

    private void limpaCamposNovaRevista() {
        campoTitulo.setText("");
        campoData.setText("");
        campoQuantidade.setText("");
        ComboBoxAreaNovaRevista.setSelectedItem(null);
        ComboBoxEspecificacaoNovaRevista.setSelectedItem(null);
    }

    private void TravaCamposDoNovaRevista() {
        campoTitulo.setEnabled(false);
        campoData.setEnabled(false);
        campoQuantidade.setEnabled(false);
        ComboBoxOrigem.setEnabled(false);
        ComboBoxAreaNovaRevista.setEnabled(false);
        ComboBoxEspecificacaoNovaRevista.setEnabled(false);
    }

    private void DestravaCamposDoNovaRevista() {
        campoTitulo.setEnabled(true);
        campoData.setEnabled(true);
        campoQuantidade.setEnabled(true);
        ComboBoxOrigem.setEnabled(true);
        ComboBoxAreaNovaRevista.setEnabled(true);
        ComboBoxEspecificacaoNovaRevista.setEnabled(true);
    }
    
    private void TravaCamposDoPesquisarRevistasParaAlteração(){
        BuscaTituloMenuRevistas.setEnabled(false);
        BuscaAreaMenuRevistas.setEnabled(false);
        BuscaEspecificacaoMenuRevistas.setEnabled(false);
    }
    private void DestravaCamposDoPesquisarRevistasParaAlteração(){
        BuscaTituloMenuRevistas.setEnabled(true);
        BuscaAreaMenuRevistas.setEnabled(true);
        BuscaEspecificacaoMenuRevistas.setEnabled(true);
    }

    private void ResetaCamposAlterarRevistas() {
        BuscaTituloMenuRevistas.setText("");
        BuscaEspecificacaoMenuRevistas.setSelectedItem(null);
        BuscaAreaMenuRevistas.setSelectedItem(null);
    }

    //ComboBox do jTabbedPane, referente a aba PaneGuiaNovaRevista
    private void ComboBoxAreaNovaRevista() {
        try {
            String SQL = "Select * from sys.Area ";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                ComboBoxAreaNovaRevista.addItem(Nome);
            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }

    private void ComboBoxEspecificacaoNovaRevista() {
        try {
            String SQL = "Select * from sys.Especificação";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                ComboBoxEspecificacaoNovaRevista.addItem(Nome);
            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }

    //ComboBox do jTabbedPane(referente a aba PaneGuiaAlteraRevista) que BUSCA especificacao.
    private void BuscaEspecificacaoMenuRevistas() {
        try {
            String SQL = "Select * from sys.Especificação ";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                BuscaEspecificacaoMenuRevistas.addItem(Nome);
            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }

    //ComboBox do jTabbedPane(referente a aba PaneGuiaAlteraRevista) que BUSCA area.
    private void BuscaAreaMenuRevistas() {
        try {
            String SQL = "Select * from sys.Area";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                BuscaAreaMenuRevistas.addItem(Nome);
            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }

    //ComboBox do jTabbedPane(referente a aba PaneGuiaAlteraRevista) que ALTERA a area.
    private void AlteraAreaMenuRevistas() {
        try {
            String SQL = "Select * from sys.Area ";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                AlteraAreaMenuRevistas.addItem(Nome);
            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }

    //ComboBox do jTabbedPane(referente a aba PaneGuiaAlteraRevista) que ALTERA a especificacao.
    private void AlteraEspecificacaoMenuRevistas() {
        try {
            String SQL = "Select * from sys.Especificação";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                AlteraEspecificacaoMenuRevistas.addItem(Nome);
            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }

    public void atualizarTabelaRevista() {

        Revistas rev = new Revistas();
        RevistaDAO revistasDAO = new RevistaDAO();
        try {
            ListaRevista = revistasDAO.ListaRevista();
        } catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaRevista.size()][6];
        int i = 0;
        for (Revistas revistas : ListaRevista) {
            dados[i][0] = String.valueOf(revistas.getID());
            dados[i][1] = revistas.getTitulo();
            dados[i][2] = revistas.getEspecificacao();
            dados[i][3] = revistas.getOrigem();
            dados[i][4] = revistas.getData();
            dados[i][5] = revistas.getArea();
            i++;
        }
        String tituloColuna[] = {"id", "Titulo", "Especificação", "Origem", "Data", "qweqweqw"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverRevista.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false,};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(1).setPreferredWidth(200);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(2).setPreferredWidth(100);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(3).setPreferredWidth(75);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(4).setPreferredWidth(30);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(5).setPreferredWidth(100);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.setRowHeight(25);
        TabelaAlterarOuRemoverRevista.updateUI();

    }

    public void BuscaRevistaComFiltro() {
        Revistas revistas = new Revistas();

        String dados[][] = new String[ListaBuscaRevista.size()][6];
        int i = 0;
        for (Revistas rev : ListaBuscaRevista) {
            dados[i][0] = String.valueOf(rev.getID());
            dados[i][1] = rev.getTitulo();
            dados[i][2] = rev.getEspecificacao();
            dados[i][3] = rev.getOrigem();
            dados[i][4] = rev.getData();
            dados[i][5] = rev.getArea();
            i++;
        }
        String tituloColuna[] = {"id", "Titulo", "Especificação", "Origem", "Data", "Area"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverRevista.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false,};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(1).setPreferredWidth(200);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(2).setPreferredWidth(100);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(3).setPreferredWidth(75);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(4).setPreferredWidth(30);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(5).setPreferredWidth(100);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.setRowHeight(25);
        TabelaAlterarOuRemoverRevista.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        BotaoNovaRevista1 = new javax.swing.JButton();
        PaneGuiaAlteraRevista = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        AlteraAnoMenuRevistas = new javax.swing.JFormattedTextField();
        lblData1 = new javax.swing.JLabel();
        BuscaTituloMenuRevistas = new javax.swing.JTextField();
        lblTitulo2 = new javax.swing.JLabel();
        BuscaEspecificacaoMenuRevistas = new javax.swing.JComboBox<>();
        lblOrigem4 = new javax.swing.JLabel();
        BuscaAreaMenuRevistas = new javax.swing.JComboBox<>();
        lblEspecificacao2 = new javax.swing.JLabel();
        lblQuantidade1 = new javax.swing.JLabel();
        IDTravadoAlteraRevista = new javax.swing.JTextField();
        BotaoBuscaAlterarOuRemoverRevista = new javax.swing.JButton();
        BotaoAlterarRevistas = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaAlterarOuRemoverRevista = new javax.swing.JTable();
        lblTitulo3 = new javax.swing.JLabel();
        AlteraTituloMenuRevistas = new javax.swing.JTextField();
        lblOrigem5 = new javax.swing.JLabel();
        AlteraAreaMenuRevistas = new javax.swing.JComboBox<>();
        AlteraEspecificacaoMenuRevistas = new javax.swing.JComboBox<>();
        lblEspecificacao3 = new javax.swing.JLabel();
        BotaoRemoverRevistas1 = new javax.swing.JButton();
        lblOrigem2 = new javax.swing.JLabel();
        AlteraOrigemMenuRevistas = new javax.swing.JComboBox<>();
        BotaoResetaPesquisaAlterarOuRemoverRevista = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        SideBoard.setBackground(new java.awt.Color(37, 103, 125));
        SideBoard.setForeground(new java.awt.Color(37, 103, 125));

        jLabel1.setFont(new java.awt.Font("Utsaah", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
            .addComponent(BotaoAdicionarRevista, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarRevista2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarRevista1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarClasse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        SideBoardLayout.setVerticalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addGap(69, 69, 69)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotaoAdicionarRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAdicionarRevista2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAdicionarRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAdicionarClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(392, Short.MAX_VALUE))
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
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

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
            campoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData.setText("Ano");

        lblOrigem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem.setText("Origem");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("Titulo da revista");

        lblOrigem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem1.setText("Especificação");

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
        BotaoNovaRevista.setText("Salvar");
        BotaoNovaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaRevistaActionPerformed(evt);
            }
        });

        lblEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao.setText("Área");

        lblQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblQuantidade.setText("Quantidade");

        BotaoNovaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoNovaRevista1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoNovaRevista1.setText("Nova revista");
        BotaoNovaRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaRevista1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaNovaRevistaLayout = new javax.swing.GroupLayout(PaneGuiaNovaRevista);
        PaneGuiaNovaRevista.setLayout(PaneGuiaNovaRevistaLayout);
        PaneGuiaNovaRevistaLayout.setHorizontalGroup(
            PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblData)
                    .addComponent(lblTitulo)
                    .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                            .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblOrigem)
                                .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                                    .addGap(198, 198, 198)
                                    .addComponent(lblOrigem1))
                                .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(93, 93, 93)
                                    .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(93, 93, 93)
                            .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblEspecificacao)
                                .addComponent(ComboBoxAreaNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                            .addComponent(BotaoNovaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(BotaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        PaneGuiaNovaRevistaLayout.setVerticalGroup(
            PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                .addGap(70, 70, 70)
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
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBoxAreaNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(lblOrigem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoNovaRevista)
                    .addComponent(BotaoNovaRevista1))
                .addGap(691, 691, 691))
        );

        jTabbedPane1.addTab("Nova revista", PaneGuiaNovaRevista);

        PaneGuiaAlteraRevista.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel4.setText("Faça as alterações na revista:");
        jLabel4.setPreferredSize(new java.awt.Dimension(407, 41));

        try {
            AlteraAnoMenuRevistas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        AlteraAnoMenuRevistas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AlteraAnoMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblData1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData1.setText("Ano");

        BuscaTituloMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaTituloMenuRevistas.setMaximumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo2.setText("Titulo da revista");

        BuscaEspecificacaoMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaEspecificacaoMenuRevistas.setToolTipText("Selecione");
        BuscaEspecificacaoMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        BuscaEspecificacaoMenuRevistas.setDoubleBuffered(true);
        BuscaEspecificacaoMenuRevistas.setEditor(null);
        BuscaEspecificacaoMenuRevistas.setFocusable(false);
        BuscaEspecificacaoMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaEspecificacaoMenuRevistas.setName("Selecione"); // NOI18N
        BuscaEspecificacaoMenuRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscaEspecificacaoMenuRevistasActionPerformed(evt);
            }
        });

        lblOrigem4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem4.setText("Área");

        BuscaAreaMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaAreaMenuRevistas.setToolTipText("Selecione");
        BuscaAreaMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        BuscaAreaMenuRevistas.setFocusable(false);
        BuscaAreaMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaAreaMenuRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscaAreaMenuRevistasActionPerformed(evt);
            }
        });

        lblEspecificacao2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao2.setText("Especificação");

        lblQuantidade1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblQuantidade1.setText("ID:");

        IDTravadoAlteraRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        IDTravadoAlteraRevista.setEnabled(false);

        BotaoBuscaAlterarOuRemoverRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaAlterarOuRemoverRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaAlterarOuRemoverRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_24px.png"))); // NOI18N
        BotaoBuscaAlterarOuRemoverRevista.setText("Busca revista");
        BotaoBuscaAlterarOuRemoverRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaAlterarOuRemoverRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaAlterarOuRemoverRevistaActionPerformed(evt);
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

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel5.setText("Escolha uma revista para alterar:");

        jSeparator2.setForeground(new java.awt.Color(37, 103, 125));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        TabelaAlterarOuRemoverRevista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelaAlterarOuRemoverRevista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaAlterarOuRemoverRevistaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaAlterarOuRemoverRevista);

        lblTitulo3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo3.setText("Titulo da revista");

        AlteraTituloMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraTituloMenuRevistas.setMaximumSize(new java.awt.Dimension(25, 25));
        AlteraTituloMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));

        lblOrigem5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem5.setText("Especificação");

        AlteraAreaMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraAreaMenuRevistas.setToolTipText("Selecione");
        AlteraAreaMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        AlteraAreaMenuRevistas.setDoubleBuffered(true);
        AlteraAreaMenuRevistas.setEditor(null);
        AlteraAreaMenuRevistas.setFocusable(false);
        AlteraAreaMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        AlteraAreaMenuRevistas.setName("Selecione"); // NOI18N
        AlteraAreaMenuRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlteraAreaMenuRevistasActionPerformed(evt);
            }
        });

        AlteraEspecificacaoMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraEspecificacaoMenuRevistas.setToolTipText("Selecione");
        AlteraEspecificacaoMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        AlteraEspecificacaoMenuRevistas.setFocusable(false);
        AlteraEspecificacaoMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        AlteraEspecificacaoMenuRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlteraEspecificacaoMenuRevistasActionPerformed(evt);
            }
        });

        lblEspecificacao3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao3.setText("Área");

        BotaoRemoverRevistas1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoRemoverRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoRemoverRevistas1.setText("Remover revista");
        BotaoRemoverRevistas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoRemoverRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverRevistas1ActionPerformed(evt);
            }
        });

        lblOrigem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem2.setText("Origem");

        AlteraOrigemMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraOrigemMenuRevistas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doação", "Aquisição" }));
        AlteraOrigemMenuRevistas.setBorder(null);
        AlteraOrigemMenuRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AlteraOrigemMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        AlteraOrigemMenuRevistas.setEditor(null);
        AlteraOrigemMenuRevistas.setFocusCycleRoot(true);
        AlteraOrigemMenuRevistas.setFocusable(false);
        AlteraOrigemMenuRevistas.setLightWeightPopupEnabled(false);
        AlteraOrigemMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));

        BotaoResetaPesquisaAlterarOuRemoverRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoResetaPesquisaAlterarOuRemoverRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Zoom In_24px.png"))); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverRevista.setText("Nova pesquisa");
        BotaoResetaPesquisaAlterarOuRemoverRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoResetaPesquisaAlterarOuRemoverRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaAlteraRevistaLayout = new javax.swing.GroupLayout(PaneGuiaAlteraRevista);
        PaneGuiaAlteraRevista.setLayout(PaneGuiaAlteraRevistaLayout);
        PaneGuiaAlteraRevistaLayout.setHorizontalGroup(
            PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblTitulo2))
                            .addComponent(jLabel5)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(BuscaTituloMenuRevistas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                            .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                    .addComponent(BotaoBuscaAlterarOuRemoverRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BotaoResetaPesquisaAlterarOuRemoverRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                    .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblOrigem4)
                                        .addComponent(BuscaAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(BuscaEspecificacaoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblEspecificacao2))))
                            .addGap(66, 66, 66))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AlteraTituloMenuRevistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addComponent(lblQuantidade1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IDTravadoAlteraRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitulo3)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrigem5)
                                    .addComponent(lblEspecificacao3)
                                    .addComponent(AlteraAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AlteraEspecificacaoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(112, 112, 112)
                                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblData1)
                                    .addComponent(AlteraAnoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AlteraOrigemMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOrigem2))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(BotaoAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(BotaoRemoverRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addContainerGap())
        );
        PaneGuiaAlteraRevistaLayout.setVerticalGroup(
            PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(5, 5, 5)
                        .addComponent(lblTitulo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscaTituloMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addComponent(lblOrigem4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BuscaAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblEspecificacao2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BuscaEspecificacaoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoBuscaAlterarOuRemoverRevista)
                            .addComponent(BotaoResetaPesquisaAlterarOuRemoverRevista))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(IDTravadoAlteraRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblQuantidade1))
                                .addGap(18, 18, 18)
                                .addComponent(lblTitulo3))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(AlteraTituloMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addComponent(lblData1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(AlteraAnoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AlteraEspecificacaoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(lblOrigem5)))
                        .addGap(21, 21, 21)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEspecificacao3)
                            .addComponent(lblOrigem2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AlteraOrigemMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlteraAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoAlterarRevistas)
                            .addComponent(BotaoRemoverRevistas1))))
                .addGap(0, 181, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Alterar ou remover", PaneGuiaAlteraRevista);

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
                .addContainerGap(515, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(BotaoBuscaRevistas)
                .addContainerGap(672, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Entrada ou saida de revista", jPanel7);

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
                .addContainerGap(532, Short.MAX_VALUE))
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
                .addContainerGap(511, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1084, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 895, Short.MAX_VALUE)
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, Short.MAX_VALUE))
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
                .addContainerGap(511, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PaneMae.add(PaneClassificacao, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaneMae, javax.swing.GroupLayout.PREFERRED_SIZE, 1089, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaneMae, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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

    private void BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed
        DestravaCamposDoPesquisarRevistasParaAlteração();
        ResetaCamposAlterarRevistas();
        atualizarTabelaRevista();
    }//GEN-LAST:event_BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed

    private void BotaoRemoverRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverRevistas1ActionPerformed

        if (IDTravadoAlteraRevista.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma revista para exclui-la", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Revistas rev = new Revistas();
            RevistaDAO revistaDAO = new RevistaDAO();

            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir essa revista?", "pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                try {
                    rev.setID(Integer.parseInt(IDTravadoAlteraRevista.getText()));
                    revistaDAO.RemoverRevista(rev);
                    JOptionPane.showMessageDialog(null, "Revista Removida com sucesso", "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    limpaCamposNovaRevista();
                    atualizarTabelaRevista();
                    //atualizarTabelaCliente();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Problema no BotaoRemoverRevistas1ActionPerformed, remoção de revista falhou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_BotaoRemoverRevistas1ActionPerformed

    private void AlteraEspecificacaoMenuRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlteraEspecificacaoMenuRevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlteraEspecificacaoMenuRevistasActionPerformed

    private void AlteraAreaMenuRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlteraAreaMenuRevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlteraAreaMenuRevistasActionPerformed

    private void TabelaAlterarOuRemoverRevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaAlterarOuRemoverRevistaMouseClicked

        TabelaAlterarOuRemoverRevista.getTableHeader().setReorderingAllowed(false);
        IDTravadoAlteraRevista.setText(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 0).toString());
        AlteraTituloMenuRevistas.setText(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 1).toString());
        AlteraEspecificacaoMenuRevistas.getModel().setSelectedItem(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 2).toString());
        AlteraOrigemMenuRevistas.getModel().setSelectedItem(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 3).toString());
        AlteraAnoMenuRevistas.setText(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 4).toString());
        AlteraAreaMenuRevistas.getModel().setSelectedItem(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 5).toString());

    }//GEN-LAST:event_TabelaAlterarOuRemoverRevistaMouseClicked

    private void BotaoAlterarRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarRevistasActionPerformed
        Revistas rev = new Revistas();
        RevistaDAO revistaDAO = new RevistaDAO();
        if (IDTravadoAlteraRevista.getText().isEmpty() || AlteraTituloMenuRevistas.getText().isEmpty() || AlteraAnoMenuRevistas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Há campos vazios, preencha esses campos, para proceder com a alteração", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {

            try {

                rev.setID(Integer.parseInt(IDTravadoAlteraRevista.getText()));
                rev.setTitulo(AlteraTituloMenuRevistas.getText());
                rev.setEspecificacao((String) AlteraEspecificacaoMenuRevistas.getSelectedItem());
                rev.setArea((String) AlteraAreaMenuRevistas.getSelectedItem());
                rev.setData(AlteraAnoMenuRevistas.getText());
                rev.setOrigem((String) AlteraOrigemMenuRevistas.getSelectedItem());

                if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir essa revista?", "pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    revistaDAO.AlterarRevista(rev);
                    JOptionPane.showMessageDialog(null, "Revista alterada com sucesso", "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    limpaCamposNovaRevista();
                    atualizarTabelaRevista();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problema no BotaoAlterarRevistasActionPerformed, alteração da revista falhou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_BotaoAlterarRevistasActionPerformed

    private void BotaoBuscaAlterarOuRemoverRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscaAlterarOuRemoverRevistaActionPerformed
        ListaBuscaRevista = null;
        Revistas rev = new Revistas();
        RevistaDAO revistaDAO = new RevistaDAO();

        try {

            if (!BuscaTituloMenuRevistas.getText().isEmpty()) {
                rev.setTitulo(BuscaTituloMenuRevistas.getText());
            }

            if (BuscaEspecificacaoMenuRevistas.getSelectedItem() != null) {
                rev.setEspecificacao((String) BuscaEspecificacaoMenuRevistas.getSelectedItem());
            }

            if (BuscaAreaMenuRevistas.getSelectedItem() != null) {
                rev.setArea((String) BuscaAreaMenuRevistas.getSelectedItem());
            }

            //System.out.println(BuscaAreaMenuRevistas.getSelectedItem());
            ListaBuscaRevista = revistaDAO.ListaBuscaRevista(rev);

            BuscaRevistaComFiltro();

        } catch (Exception E) {
            System.out.println(E.getMessage());
            JOptionPane.showMessageDialog(null, "Problema no BotaoBuscaAlterarOuRemoverRevista do DashBoard, busca de revista falhou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_BotaoBuscaAlterarOuRemoverRevistaActionPerformed

    private void BuscaAreaMenuRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscaAreaMenuRevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscaAreaMenuRevistasActionPerformed

    private void BuscaEspecificacaoMenuRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscaEspecificacaoMenuRevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscaEspecificacaoMenuRevistasActionPerformed

    //Aqui cria-se uma nova revista
    private void BotaoNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaRevistaActionPerformed
        if (campoTitulo.getText().isEmpty() || campoData.getText().isEmpty() || campoQuantidade.getText().isEmpty() || ComboBoxEspecificacaoNovaRevista.getSelectedItem() == null || ComboBoxAreaNovaRevista.getSelectedItem()== null) {
            JOptionPane.showMessageDialog(null, "Há campos não preenchidos", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Revistas rev = new Revistas();
            try {

                rev.getID();
                rev.setTitulo(campoTitulo.getText());
                rev.setEspecificacao((String) ComboBoxEspecificacaoNovaRevista.getSelectedItem());
                rev.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                rev.setOrigem((String) ComboBoxOrigem.getSelectedItem());
                rev.setData(campoData.getText());
                rev.setArea((String) ComboBoxAreaNovaRevista.getSelectedItem());

                RevistaDAO revistaDAO = new RevistaDAO();
                revistaDAO.InserirNovaRevistas(rev);
                JOptionPane.showMessageDialog(null, "Revista criada com sucesso", "Sistema", JOptionPane.INFORMATION_MESSAGE);

                limpaCamposNovaRevista();
                TravaCamposDoNovaRevista();
                atualizarTabelaRevista();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problema no BotaoNovaRevistaActionPerformed do DashBoard, criação de revista falhou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_BotaoNovaRevistaActionPerformed

    private void ComboBoxAreaNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAreaNovaRevistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAreaNovaRevistaActionPerformed

    private void BotaoNovaRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaRevista1ActionPerformed
        DestravaCamposDoNovaRevista();
    }//GEN-LAST:event_BotaoNovaRevista1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    public void setLblColor(JLabel lbl) {
        lbl.setBackground(new Color(150, 150, 150));
    }

    public void ResetColor(JLabel lbl) {
        lbl.setBackground(new Color(37, 103, 125));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdicionarRevista;
    private javax.swing.JFormattedTextField AlteraAnoMenuRevistas;
    private javax.swing.JComboBox<String> AlteraAreaMenuRevistas;
    private javax.swing.JComboBox<String> AlteraEspecificacaoMenuRevistas;
    private javax.swing.JComboBox<String> AlteraOrigemMenuRevistas;
    private javax.swing.JTextField AlteraTituloMenuRevistas;
    private javax.swing.JLabel BotaoAdicionarClasse;
    private javax.swing.JLabel BotaoAdicionarRevista;
    private javax.swing.JLabel BotaoAdicionarRevista1;
    private javax.swing.JLabel BotaoAdicionarRevista2;
    private javax.swing.JButton BotaoAlterarRevistas;
    private javax.swing.JButton BotaoBuscaAlterarOuRemoverRevista;
    private javax.swing.JButton BotaoBuscaRevistas;
    private javax.swing.JButton BotaoNovaRevista;
    private javax.swing.JButton BotaoNovaRevista1;
    private javax.swing.JButton BotaoRemoverRevistas;
    private javax.swing.JButton BotaoRemoverRevistas1;
    private javax.swing.JButton BotaoResetaPesquisaAlterarOuRemoverRevista;
    private javax.swing.JButton BotaoSalvaRevistas;
    private javax.swing.JButton BotaoSubtrairRevistas;
    private javax.swing.JComboBox<String> BuscaAreaMenuRevistas;
    private javax.swing.JComboBox<String> BuscaEspecificacaoMenuRevistas;
    private javax.swing.JTextField BuscaTituloMenuRevistas;
    private javax.swing.JComboBox<String> ComboBoxAreaNovaRevista;
    private javax.swing.JComboBox<String> ComboBoxEspecificacaoNovaRevista;
    private javax.swing.JComboBox<String> ComboBoxOrigem;
    private javax.swing.JTextField IDTravadoAlteraRevista;
    private javax.swing.JPanel PaneClassificacao;
    private javax.swing.JPanel PaneGuiaAlteraRevista;
    private javax.swing.JPanel PaneGuiaNovaRevista;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel SideBoard;
    private javax.swing.JTable TabelaAlterarOuRemoverRevista;
    private javax.swing.JFormattedTextField campoData;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblData1;
    private javax.swing.JLabel lblEspecificacao;
    private javax.swing.JLabel lblEspecificacao2;
    private javax.swing.JLabel lblEspecificacao3;
    private javax.swing.JLabel lblOrigem;
    private javax.swing.JLabel lblOrigem1;
    private javax.swing.JLabel lblOrigem2;
    private javax.swing.JLabel lblOrigem4;
    private javax.swing.JLabel lblOrigem5;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblQuantidade1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    // End of variables declaration//GEN-END:variables

}
