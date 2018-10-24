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
        inicioRevista();
    }
    
    
    private void inicioRevista() {
        PaneRevista.setVisible(true);
        PaneArea.setVisible(false);
        PaneEspecificacao.setVisible(false);
        setLblColor(BotaoAdicionarRevista);
        ResetColor(BotaoAdicionarArea);
        ResetColor(BotaoAdicionarEspecificacao);
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
        TravaCamposDoPesquisarRevistasParaAlteracao();
        IDTravadoAlteraRevista.setVisible(false);
        lblIDAlteraRevista.setVisible(false);
        LimpaCamposAlteraRevista();
        TravaCamposAlteraRevista();
        TravaBotoeAlteraRevista();
        atualizarConsultaRevista();
        ConsultaEspecificacaoRevistas();
        ConsultaAreaMenuRevistas();
        LimpaCamposConsultaRevista();
        TravaCamposConsultaRevista();
        TravaBotoesCadRevista();
    }

    private void TravaBotoesCadRevista() {
        BotaoNovaRevista.setEnabled(false);
        BotaoNovaRevista2.setEnabled(false);
    }

    private void DestravaBotoesCadRevista() {
        BotaoNovaRevista.setEnabled(true);
        BotaoNovaRevista2.setEnabled(true);
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
    //--------------------------------------------------------------------

    private void TravaCamposDoPesquisarRevistasParaAlteracao() {
        BuscaTituloMenuRevistas.setEnabled(false);
        BuscaAreaMenuRevistas.setEnabled(false);
        BuscaEspecificacaoMenuRevistas.setEnabled(false);
    }

    private void DestravaCamposDoPesquisarRevistasParaAlteracao() {
        BuscaTituloMenuRevistas.setEnabled(true);
        BuscaAreaMenuRevistas.setEnabled(true);
        BuscaEspecificacaoMenuRevistas.setEnabled(true);
    }

    private void ResetaCamposAlterarRevistas() {
        BuscaTituloMenuRevistas.setText("");
        BuscaEspecificacaoMenuRevistas.setSelectedItem(null);
        BuscaAreaMenuRevistas.setSelectedItem(null);
    }

    private void TravaCamposAlteraRevista() {
        AlteraTituloMenuRevistas.setEnabled(false);
        AlteraAnoMenuRevistas.setEnabled(false);
        AlteraAreaMenuRevistas.setEnabled(false);
        AlteraEspecificacaoMenuRevistas.setEnabled(false);
        AlteraOrigemMenuRevistas.setEnabled(false);
        AlteraQuantidadeMenuRevistas.setEnabled(false);
    }

    private void DesravaCamposAlteraRevista() {
        AlteraTituloMenuRevistas.setEnabled(true);
        AlteraAnoMenuRevistas.setEnabled(true);
        AlteraAreaMenuRevistas.setEnabled(true);
        AlteraEspecificacaoMenuRevistas.setEnabled(true);
        AlteraOrigemMenuRevistas.setEnabled(true);
        AlteraQuantidadeMenuRevistas.setEnabled(true);

    }

    private void LimpaCamposAlteraRevista() {
        IDTravadoAlteraRevista.setText("");
        AlteraTituloMenuRevistas.setText("");
        AlteraAnoMenuRevistas.setText("");
        AlteraAreaMenuRevistas.setSelectedItem(null);
        AlteraEspecificacaoMenuRevistas.setSelectedItem(null);
        AlteraQuantidadeMenuRevistas.setText("");
    }

    private void TravaBotoeAlteraRevista() {
        BotaoRemoverRevistas.setEnabled(false);
        BotaoAlterarRevistas.setEnabled(false);
        BotaoLimpaCamposAlterarRevistas.setEnabled(false);
    }

    private void DestravaBotoeAlteraRevista() {
        BotaoRemoverRevistas.setEnabled(true);
        BotaoAlterarRevistas.setEnabled(true);
        BotaoLimpaCamposAlterarRevistas.setEnabled(true);
    }

    //---------------------------------------------------------------------
    private void TravaCamposConsultaRevista() {
        ConsultaTituloMenuRevistas.setEnabled(false);
        ConsultaAreaMenuRevistas.setEnabled(false);
        ConsultaEspecificacaoMenuRevistas.setEnabled(false);
    }

    private void LimpaCamposConsultaRevista() {
        ConsultaTituloMenuRevistas.setText("");
        ConsultaAreaMenuRevistas.setSelectedItem(null);
        ConsultaEspecificacaoMenuRevistas.setSelectedItem(null);
    }

    private void DestravaCamposConsultaRevista() {
        ConsultaTituloMenuRevistas.setEnabled(true);
        ConsultaAreaMenuRevistas.setEnabled(true);
        ConsultaEspecificacaoMenuRevistas.setEnabled(true);
    }
    //---------------------------------------------------------------------

    //ComboBox do PaneRevista, referente a aba PaneGuiaNovaRevista
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

    //ComboBox do PaneRevista, referente a aba PaneGuiaNovaRevista
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

    //ComboBox do PaneRevista(referente a aba PaneGuiaAlteraRevista).
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
            System.out.println(e.getMessage());
            System.out.println("problema na combobox");
        }
    }

    //ComboBox do PaneRevista(referente a aba PaneGuiaAlteraRevista).
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

    //ComboBox do PaneRevista(referente a aba PaneGuiaAlteraRevista).
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

    //ComboBox do PaneRevista(referente a aba PaneGuiaAlteraRevista).
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
    //ComboBox do PaneRevista(referente a aba PaneGuiaConsulta).
    private void ConsultaAreaMenuRevistas() {
        try {
            String SQL = "Select * from sys.Area ";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                ConsultaAreaMenuRevistas.addItem(Nome);
            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }
    //ComboBox do PaneRevista(referente a aba PaneGuiaConsulta).
    private void ConsultaEspecificacaoRevistas() {
        try {
            String SQL = "Select * from sys.Especificação";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                ConsultaEspecificacaoMenuRevistas.addItem(Nome);
            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }

    //Tava do PaneRevista, referente a aba PaneGuiaAlteraRevista
    public void atualizarTabelaRevista() {
        Revistas rev = new Revistas();
        RevistaDAO revistasDAO = new RevistaDAO();
        try {
            ListaRevista = revistasDAO.ListaRevista();
        } catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaRevista.size()][7];
        int i = 0;
        for (Revistas revistas : ListaRevista) {
            dados[i][0] = String.valueOf(revistas.getID());
            dados[i][1] = revistas.getTitulo();
            dados[i][2] = revistas.getArea();
            dados[i][3] = revistas.getEspecificacao();
            dados[i][4] = String.valueOf(revistas.getData());
            dados[i][5] = String.valueOf(revistas.getQuantidade());
            dados[i][6] = revistas.getOrigem();
            i++;
        }
        String tituloColuna[] = {"id", "Titulo", "Área", "Especificação", "Data", "Quantidade", "Origem"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverRevista.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(1).setPreferredWidth(180);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(2).setPreferredWidth(100);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(3).setPreferredWidth(100);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(4).setPreferredWidth(30);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(5).setPreferredWidth(50);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(6).setPreferredWidth(60);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(6).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.setRowHeight(25);
        TabelaAlterarOuRemoverRevista.updateUI();

    }
    //Tava do PaneRevista, referente a aba PaneGuiaAlteraRevista
    public void BuscaRevistaComFiltro() {

        Revistas revistas = new Revistas();
        String dados[][] = new String[ListaBuscaRevista.size()][7];
        int i = 0;
        for (Revistas rev : ListaBuscaRevista) {
            dados[i][0] = String.valueOf(rev.getID());
            dados[i][1] = rev.getTitulo();
            dados[i][2] = rev.getArea();
            dados[i][3] = rev.getEspecificacao();
            dados[i][4] = String.valueOf(rev.getData());
            dados[i][5] = String.valueOf(rev.getQuantidade());
            dados[i][6] = rev.getOrigem();
            i++;
        }
        String tituloColuna[] = {"id", "Titulo", "Área", "Especificação", "Data", "Quantidade", "Origem"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverRevista.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false,};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(1).setPreferredWidth(180);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(2).setPreferredWidth(100);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(3).setPreferredWidth(100);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(4).setPreferredWidth(30);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(5).setPreferredWidth(50);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(6).setPreferredWidth(60);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.getColumnModel().getColumn(6).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverRevista.setRowHeight(25);
        TabelaAlterarOuRemoverRevista.updateUI();
    }
    //Tava do PaneRevista, referente a aba PaneGuiaConsulta
    public void atualizarConsultaRevista() {

        Revistas rev = new Revistas();
        RevistaDAO revistasDAO = new RevistaDAO();
        try {
            ListaRevista = revistasDAO.ListaRevista();
        } catch (SQLException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaRevista.size()][7];
        int i = 0;
        for (Revistas revistas : ListaRevista) {
            dados[i][0] = String.valueOf(revistas.getID());
            dados[i][1] = revistas.getTitulo();
            dados[i][2] = revistas.getArea();
            dados[i][3] = revistas.getEspecificacao();
            dados[i][4] = String.valueOf(revistas.getData());
            dados[i][5] = String.valueOf(revistas.getQuantidade());
            dados[i][6] = revistas.getOrigem();
            i++;
        }
        String tituloColuna[] = {"id", "Titulo", "Área", "Especificação", "Data", "Quantidade", "Origem"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaConsultaRevista.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaConsultaRevista.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaConsultaRevista.getColumnModel().getColumn(1).setPreferredWidth(180);
        TabelaConsultaRevista.getColumnModel().getColumn(2).setPreferredWidth(100);
        TabelaConsultaRevista.getColumnModel().getColumn(3).setPreferredWidth(100);
        TabelaConsultaRevista.getColumnModel().getColumn(4).setPreferredWidth(30);
        TabelaConsultaRevista.getColumnModel().getColumn(5).setPreferredWidth(50);
        TabelaConsultaRevista.getColumnModel().getColumn(6).setPreferredWidth(60);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaConsultaRevista.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(6).setCellRenderer(centralizado);
        TabelaConsultaRevista.setRowHeight(25);
        TabelaConsultaRevista.updateUI();
    }
    //Tava do PaneRevista, referente a aba PaneGuiaConsulta
    public void ConsultaRevistaComFiltro() {

        Revistas revistas = new Revistas();
        String dados[][] = new String[ListaBuscaRevista.size()][7];
        int i = 0;
        for (Revistas rev : ListaBuscaRevista) {
            dados[i][0] = String.valueOf(rev.getID());
            dados[i][1] = rev.getTitulo();
            dados[i][2] = rev.getArea();
            dados[i][3] = rev.getEspecificacao();
            dados[i][4] = String.valueOf(rev.getData());
            dados[i][5] = String.valueOf(rev.getQuantidade());
            dados[i][6] = rev.getOrigem();
            i++;
        }
        String tituloColuna[] = {"id", "Titulo", "Área", "Especificação", "Data", "Quantidade", "Origem"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaConsultaRevista.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false,};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaConsultaRevista.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaConsultaRevista.getColumnModel().getColumn(1).setPreferredWidth(180);
        TabelaConsultaRevista.getColumnModel().getColumn(2).setPreferredWidth(100);
        TabelaConsultaRevista.getColumnModel().getColumn(3).setPreferredWidth(100);
        TabelaConsultaRevista.getColumnModel().getColumn(4).setPreferredWidth(30);
        TabelaConsultaRevista.getColumnModel().getColumn(5).setPreferredWidth(50);
        TabelaConsultaRevista.getColumnModel().getColumn(6).setPreferredWidth(60);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaConsultaRevista.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(5).setCellRenderer(centralizado);
        TabelaConsultaRevista.getColumnModel().getColumn(6).setCellRenderer(centralizado);
        TabelaConsultaRevista.setRowHeight(25);
        TabelaConsultaRevista.updateUI();
    }
    //------------------------------------ fim das operacoes referente a revista
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SideBoard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        BotaoAdicionarArea = new javax.swing.JLabel();
        BotaoAdicionarRevista = new javax.swing.JLabel();
        BotaoAdicionarEspecificacao = new javax.swing.JLabel();
        PaneMae = new javax.swing.JPanel();
        PaneEspecificacao = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        PaneRevista = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PaneGuiaNovaRevista = new javax.swing.JPanel();
        ComboBoxOrigem = new javax.swing.JComboBox<>();
        lblData = new javax.swing.JLabel();
        lblOrigem = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblOrigem1 = new javax.swing.JLabel();
        ComboBoxAreaNovaRevista = new javax.swing.JComboBox<>();
        campoTitulo = new javax.swing.JTextField();
        ComboBoxEspecificacaoNovaRevista = new javax.swing.JComboBox<>();
        BotaoNovaRevista = new javax.swing.JButton();
        lblEspecificacao = new javax.swing.JLabel();
        BotaoNovaRevista1 = new javax.swing.JButton();
        lblData2 = new javax.swing.JLabel();
        campoQuantidade = new javax.swing.JTextField();
        campoData = new javax.swing.JTextField();
        BotaoNovaRevista2 = new javax.swing.JButton();
        PaneGuiaConsulta = new javax.swing.JPanel();
        ConsultaTituloMenuRevistas = new javax.swing.JTextField();
        lblTitulo5 = new javax.swing.JLabel();
        lblOrigem6 = new javax.swing.JLabel();
        ConsultaAreaMenuRevistas = new javax.swing.JComboBox<>();
        ConsultaEspecificacaoMenuRevistas = new javax.swing.JComboBox<>();
        lblEspecificacao4 = new javax.swing.JLabel();
        BotaoBuscaConsultaRevista = new javax.swing.JButton();
        BotaoNovaBuscaConsultaRevista = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaConsultaRevista = new javax.swing.JTable();
        BotaoCancelaConsultaRevista = new javax.swing.JButton();
        PaneGuiaAlteraRevista = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblData1 = new javax.swing.JLabel();
        BuscaTituloMenuRevistas = new javax.swing.JTextField();
        lblTitulo2 = new javax.swing.JLabel();
        BuscaEspecificacaoMenuRevistas = new javax.swing.JComboBox<>();
        lblOrigem4 = new javax.swing.JLabel();
        BuscaAreaMenuRevistas = new javax.swing.JComboBox<>();
        lblEspecificacao2 = new javax.swing.JLabel();
        lblIDAlteraRevista = new javax.swing.JLabel();
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
        BotaoRemoverRevistas = new javax.swing.JButton();
        lblOrigem2 = new javax.swing.JLabel();
        AlteraOrigemMenuRevistas = new javax.swing.JComboBox<>();
        BotaoResetaPesquisaAlterarOuRemoverRevista = new javax.swing.JButton();
        lblEspecificacao5 = new javax.swing.JLabel();
        AlteraQuantidadeMenuRevistas = new javax.swing.JTextField();
        BotaoLimpaCamposAlterarRevistas = new javax.swing.JButton();
        AlteraAnoMenuRevistas = new javax.swing.JTextField();
        BotaoCancelaConsultaRevista1 = new javax.swing.JButton();
        BotaoLimpaCamposAlterarRevistas1 = new javax.swing.JButton();
        PaneArea = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        PaneGuiaConsulta1 = new javax.swing.JPanel();
        ConsultaTituloMenuRevistas1 = new javax.swing.JTextField();
        lblTitulo6 = new javax.swing.JLabel();
        lblOrigem8 = new javax.swing.JLabel();
        ConsultaAreaMenuRevistas1 = new javax.swing.JComboBox<>();
        ConsultaEspecificacaoMenuRevistas1 = new javax.swing.JComboBox<>();
        lblEspecificacao6 = new javax.swing.JLabel();
        BotaoBuscaConsultaRevista1 = new javax.swing.JButton();
        BotaoNovaBuscaConsultaRevista1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelaConsultaRevista1 = new javax.swing.JTable();
        BotaoCancelaConsultaRevista2 = new javax.swing.JButton();
        PaneGuiaAlteraRevista1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblData5 = new javax.swing.JLabel();
        BuscaTituloMenuRevistas1 = new javax.swing.JTextField();
        lblTitulo4 = new javax.swing.JLabel();
        BuscaEspecificacaoMenuRevistas1 = new javax.swing.JComboBox<>();
        lblOrigem9 = new javax.swing.JLabel();
        BuscaAreaMenuRevistas1 = new javax.swing.JComboBox<>();
        lblEspecificacao7 = new javax.swing.JLabel();
        lblIDAlteraRevista1 = new javax.swing.JLabel();
        IDTravadoAlteraRevista1 = new javax.swing.JTextField();
        BotaoBuscaAlterarOuRemoverRevista1 = new javax.swing.JButton();
        BotaoAlterarRevistas1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        TabelaAlterarOuRemoverRevista1 = new javax.swing.JTable();
        lblTitulo7 = new javax.swing.JLabel();
        AlteraTituloMenuRevistas1 = new javax.swing.JTextField();
        lblOrigem10 = new javax.swing.JLabel();
        AlteraAreaMenuRevistas1 = new javax.swing.JComboBox<>();
        AlteraEspecificacaoMenuRevistas1 = new javax.swing.JComboBox<>();
        lblEspecificacao8 = new javax.swing.JLabel();
        BotaoRemoverRevistas1 = new javax.swing.JButton();
        lblOrigem11 = new javax.swing.JLabel();
        AlteraOrigemMenuRevistas1 = new javax.swing.JComboBox<>();
        BotaoResetaPesquisaAlterarOuRemoverRevista1 = new javax.swing.JButton();
        lblEspecificacao9 = new javax.swing.JLabel();
        AlteraQuantidadeMenuRevistas1 = new javax.swing.JTextField();
        BotaoLimpaCamposAlterarRevistas2 = new javax.swing.JButton();
        AlteraAnoMenuRevistas1 = new javax.swing.JTextField();
        BotaoCancelaConsultaRevista3 = new javax.swing.JButton();
        BotaoLimpaCamposAlterarRevistas3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        SideBoard.setBackground(new java.awt.Color(37, 103, 125));
        SideBoard.setForeground(new java.awt.Color(37, 103, 125));
        SideBoard.setMaximumSize(new java.awt.Dimension(300, 400));

        jLabel1.setFont(new java.awt.Font("Utsaah", 0, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("UEMG Revistas");

        BotaoAdicionarArea.setBackground(new java.awt.Color(37, 103, 125));
        BotaoAdicionarArea.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoAdicionarArea.setForeground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoAdicionarArea.setText("Áreas");
        BotaoAdicionarArea.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoAdicionarArea.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoAdicionarArea.setOpaque(true);
        BotaoAdicionarArea.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoAdicionarArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoAdicionarAreaMouseClicked(evt);
            }
        });

        BotaoAdicionarRevista.setBackground(new java.awt.Color(37, 103, 125));
        BotaoAdicionarRevista.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoAdicionarRevista.setForeground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarRevista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoAdicionarRevista.setText("Revistas");
        BotaoAdicionarRevista.setOpaque(true);
        BotaoAdicionarRevista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoAdicionarRevistaMouseClicked(evt);
            }
        });

        BotaoAdicionarEspecificacao.setBackground(new java.awt.Color(37, 103, 125));
        BotaoAdicionarEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoAdicionarEspecificacao.setForeground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarEspecificacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoAdicionarEspecificacao.setText("Especificação");
        BotaoAdicionarEspecificacao.setOpaque(true);
        BotaoAdicionarEspecificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoAdicionarEspecificacaoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout SideBoardLayout = new javax.swing.GroupLayout(SideBoard);
        SideBoard.setLayout(SideBoardLayout);
        SideBoardLayout.setHorizontalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BotaoAdicionarRevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarEspecificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoAdicionarArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SideBoardLayout.setVerticalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotaoAdicionarRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAdicionarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoAdicionarEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneMae.setBackground(new java.awt.Color(255, 255, 255));
        PaneMae.setLayout(new java.awt.CardLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(44, 106, 129));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 729, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneEspecificacaoLayout = new javax.swing.GroupLayout(PaneEspecificacao);
        PaneEspecificacao.setLayout(PaneEspecificacaoLayout);
        PaneEspecificacaoLayout.setHorizontalGroup(
            PaneEspecificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneEspecificacaoLayout.setVerticalGroup(
            PaneEspecificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PaneMae.add(PaneEspecificacao, "card4");

        PaneRevista.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setDoubleBuffered(false);
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(44, 106, 129));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Revista acadêmicos:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane1.setDoubleBuffered(true);
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
        ComboBoxAreaNovaRevista.setMaximumSize(new java.awt.Dimension(35, 26));
        ComboBoxAreaNovaRevista.setName("Selecione"); // NOI18N
        ComboBoxAreaNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxAreaNovaRevistaActionPerformed(evt);
            }
        });

        campoTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTitulo.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTitulo.setMinimumSize(new java.awt.Dimension(25, 25));

        ComboBoxEspecificacaoNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEspecificacaoNovaRevista.setToolTipText("Selecione");
        ComboBoxEspecificacaoNovaRevista.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ComboBoxEspecificacaoNovaRevista.setFocusable(false);
        ComboBoxEspecificacaoNovaRevista.setMaximumSize(new java.awt.Dimension(35, 26));

        BotaoNovaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoNovaRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Save Close_24px.png"))); // NOI18N
        BotaoNovaRevista.setText("Salvar");
        BotaoNovaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaRevistaActionPerformed(evt);
            }
        });

        lblEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao.setText("Área");

        BotaoNovaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoNovaRevista1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoNovaRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Add Book_24px.png"))); // NOI18N
        BotaoNovaRevista1.setText("Nova revista");
        BotaoNovaRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaRevista1ActionPerformed(evt);
            }
        });

        lblData2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData2.setText("Quantidade inicial");

        campoQuantidade.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        campoQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoQuantidadeActionPerformed(evt);
            }
        });

        campoData.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        campoData.setPreferredSize(new java.awt.Dimension(35, 26));
        campoData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataActionPerformed(evt);
            }
        });

        BotaoNovaRevista2.setBackground(new java.awt.Color(255, 255, 255));
        BotaoNovaRevista2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoNovaRevista2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Remove Book_24px.png"))); // NOI18N
        BotaoNovaRevista2.setText("Cancelar");
        BotaoNovaRevista2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaRevista2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaRevista2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaNovaRevistaLayout = new javax.swing.GroupLayout(PaneGuiaNovaRevista);
        PaneGuiaNovaRevista.setLayout(PaneGuiaNovaRevistaLayout);
        PaneGuiaNovaRevistaLayout.setHorizontalGroup(
            PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                        .addComponent(BotaoNovaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoNovaRevista2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(campoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTitulo))
                    .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                        .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblData)
                                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrigem1)
                                    .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78)
                                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEspecificacao)
                                    .addComponent(ComboBoxAreaNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PaneGuiaNovaRevistaLayout.createSequentialGroup()
                                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrigem)
                                    .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData2)
                            .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(127, Short.MAX_VALUE))
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
                    .addComponent(lblData2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxAreaNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblOrigem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoNovaRevista)
                    .addComponent(BotaoNovaRevista1)
                    .addComponent(BotaoNovaRevista2))
                .addGap(806, 806, 806))
        );

        jTabbedPane1.addTab("Cadastrar", PaneGuiaNovaRevista);

        PaneGuiaConsulta.setBackground(new java.awt.Color(255, 255, 255));

        ConsultaTituloMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaTituloMenuRevistas.setMaximumSize(new java.awt.Dimension(25, 25));
        ConsultaTituloMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));

        lblTitulo5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo5.setText("Titulo da revista");

        lblOrigem6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem6.setText("Área");

        ConsultaAreaMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaAreaMenuRevistas.setToolTipText("Selecione");
        ConsultaAreaMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ConsultaAreaMenuRevistas.setFocusable(false);
        ConsultaAreaMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaAreaMenuRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaAreaMenuRevistasActionPerformed(evt);
            }
        });

        ConsultaEspecificacaoMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaEspecificacaoMenuRevistas.setToolTipText("Selecione");
        ConsultaEspecificacaoMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ConsultaEspecificacaoMenuRevistas.setDoubleBuffered(true);
        ConsultaEspecificacaoMenuRevistas.setEditor(null);
        ConsultaEspecificacaoMenuRevistas.setFocusable(false);
        ConsultaEspecificacaoMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaEspecificacaoMenuRevistas.setName("Selecione"); // NOI18N
        ConsultaEspecificacaoMenuRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaEspecificacaoMenuRevistasActionPerformed(evt);
            }
        });

        lblEspecificacao4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao4.setText("Especificação");

        BotaoBuscaConsultaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaConsultaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaConsultaRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_24px.png"))); // NOI18N
        BotaoBuscaConsultaRevista.setText("Busca revista");
        BotaoBuscaConsultaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaConsultaRevista.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaConsultaRevista.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaConsultaRevista.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoBuscaConsultaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaConsultaRevistaActionPerformed(evt);
            }
        });

        BotaoNovaBuscaConsultaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoNovaBuscaConsultaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoNovaBuscaConsultaRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Zoom In_24px.png"))); // NOI18N
        BotaoNovaBuscaConsultaRevista.setText("Nova pesquisa");
        BotaoNovaBuscaConsultaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaBuscaConsultaRevista.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoNovaBuscaConsultaRevista.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoNovaBuscaConsultaRevista.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoNovaBuscaConsultaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaBuscaConsultaRevistaActionPerformed(evt);
            }
        });

        TabelaConsultaRevista.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelaConsultaRevista.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabelaConsultaRevista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaConsultaRevistaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelaConsultaRevista);

        BotaoCancelaConsultaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoCancelaConsultaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoCancelaConsultaRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoCancelaConsultaRevista.setText("Limpa busca");
        BotaoCancelaConsultaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelaConsultaRevista.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelaConsultaRevistaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaConsultaLayout = new javax.swing.GroupLayout(PaneGuiaConsulta);
        PaneGuiaConsulta.setLayout(PaneGuiaConsultaLayout);
        PaneGuiaConsultaLayout.setHorizontalGroup(
            PaneGuiaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaConsultaLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(PaneGuiaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PaneGuiaConsultaLayout.createSequentialGroup()
                        .addComponent(BotaoNovaBuscaConsultaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoBuscaConsultaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoCancelaConsultaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PaneGuiaConsultaLayout.createSequentialGroup()
                            .addGroup(PaneGuiaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitulo5)
                                .addComponent(ConsultaTituloMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(PaneGuiaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ConsultaEspecificacaoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEspecificacao4))
                            .addGap(18, 18, 18)
                            .addGroup(PaneGuiaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblOrigem6)
                                .addComponent(ConsultaAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        PaneGuiaConsultaLayout.setVerticalGroup(
            PaneGuiaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaConsultaLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(PaneGuiaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PaneGuiaConsultaLayout.createSequentialGroup()
                        .addComponent(lblTitulo5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConsultaTituloMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaConsultaLayout.createSequentialGroup()
                        .addComponent(lblEspecificacao4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConsultaEspecificacaoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaConsultaLayout.createSequentialGroup()
                        .addComponent(lblOrigem6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConsultaAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(PaneGuiaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoBuscaConsultaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoCancelaConsultaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoNovaBuscaConsultaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(487, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar", PaneGuiaConsulta);

        PaneGuiaAlteraRevista.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaAlteraRevista.setToolTipText("Limpa os campos acima");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel4.setText("Faça as alterações na revista:");
        jLabel4.setPreferredSize(new java.awt.Dimension(407, 41));

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

        lblIDAlteraRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIDAlteraRevista.setText("ID:");

        IDTravadoAlteraRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        IDTravadoAlteraRevista.setEnabled(false);

        BotaoBuscaAlterarOuRemoverRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaAlterarOuRemoverRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaAlterarOuRemoverRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_24px.png"))); // NOI18N
        BotaoBuscaAlterarOuRemoverRevista.setText("Busca revista");
        BotaoBuscaAlterarOuRemoverRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaAlterarOuRemoverRevista.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverRevista.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverRevista.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaAlterarOuRemoverRevistaActionPerformed(evt);
            }
        });

        BotaoAlterarRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAlterarRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoAlterarRevistas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Save as_24px.png"))); // NOI18N
        BotaoAlterarRevistas.setText("Alterar");
        BotaoAlterarRevistas.setToolTipText("Altera a revista selecionada");
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
        TabelaAlterarOuRemoverRevista.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
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

        BotaoRemoverRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoRemoverRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoRemoverRevistas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Trash_24px.png"))); // NOI18N
        BotaoRemoverRevistas.setText("Remover");
        BotaoRemoverRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoRemoverRevistas.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverRevistas.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverRevistas.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoRemoverRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverRevistasActionPerformed(evt);
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
        BotaoResetaPesquisaAlterarOuRemoverRevista.setText("Nova Busca");
        BotaoResetaPesquisaAlterarOuRemoverRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoResetaPesquisaAlterarOuRemoverRevista.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverRevista.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverRevista.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed(evt);
            }
        });

        lblEspecificacao5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao5.setText("Quantidade");

        AlteraQuantidadeMenuRevistas.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        BotaoLimpaCamposAlterarRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaCamposAlterarRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaCamposAlterarRevistas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Broom_24px.png"))); // NOI18N
        BotaoLimpaCamposAlterarRevistas.setText("Limpar");
        BotaoLimpaCamposAlterarRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaCamposAlterarRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaCamposAlterarRevistasActionPerformed(evt);
            }
        });

        AlteraAnoMenuRevistas.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        BotaoCancelaConsultaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoCancelaConsultaRevista1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoCancelaConsultaRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoCancelaConsultaRevista1.setText("Limpa busca");
        BotaoCancelaConsultaRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelaConsultaRevista1.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista1.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista1.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelaConsultaRevista1ActionPerformed(evt);
            }
        });

        BotaoLimpaCamposAlterarRevistas1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaCamposAlterarRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaCamposAlterarRevistas1.setText("Liberar ações");
        BotaoLimpaCamposAlterarRevistas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaCamposAlterarRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaCamposAlterarRevistas1ActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addComponent(BotaoResetaPesquisaAlterarOuRemoverRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotaoBuscaAlterarOuRemoverRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoCancelaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblOrigem4)
                                            .addComponent(BuscaAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BuscaEspecificacaoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEspecificacao2))
                                        .addGap(66, 66, 66))
                                    .addComponent(BuscaTituloMenuRevistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(lblTitulo2))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(14, 14, 14)))))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEspecificacao5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo3)
                    .addComponent(AlteraTituloMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlteraQuantidadeMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                            .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblOrigem5)
                                .addComponent(lblEspecificacao3)
                                .addComponent(AlteraAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AlteraEspecificacaoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(112, 112, 112)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblData1)
                                .addComponent(AlteraOrigemMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblOrigem2)
                                .addComponent(AlteraAnoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                            .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(BotaoLimpaCamposAlterarRevistas1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                    .addComponent(lblIDAlteraRevista)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IDTravadoAlteraRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(BotaoLimpaCamposAlterarRevistas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BotaoRemoverRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BotaoAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))))
                .addGap(17, 17, 17))
        );
        PaneGuiaAlteraRevistaLayout.setVerticalGroup(
            PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(BotaoBuscaAlterarOuRemoverRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoResetaPesquisaAlterarOuRemoverRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoCancelaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo3)
                        .addGap(4, 4, 4)
                        .addComponent(AlteraTituloMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addComponent(lblOrigem5)
                                .addGap(3, 3, 3)
                                .addComponent(AlteraEspecificacaoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addComponent(lblData1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlteraAnoMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEspecificacao3)
                            .addComponent(lblOrigem2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AlteraAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlteraOrigemMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblEspecificacao5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlteraQuantidadeMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(BotaoLimpaCamposAlterarRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoLimpaCamposAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoRemoverRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoAlterarRevistas))
                        .addGap(46, 46, 46)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IDTravadoAlteraRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDAlteraRevista))))
                .addGap(0, 451, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gerenciar", PaneGuiaAlteraRevista);

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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout PaneRevistaLayout = new javax.swing.GroupLayout(PaneRevista);
        PaneRevista.setLayout(PaneRevistaLayout);
        PaneRevistaLayout.setHorizontalGroup(
            PaneRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneRevistaLayout.setVerticalGroup(
            PaneRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneRevistaLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PaneMae.add(PaneRevista, "card3");

        PaneArea.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(44, 106, 129));
        jPanel2.setPreferredSize(new java.awt.Dimension(627, 134));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Menu Área:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane2.setAutoscrolls(true);
        jTabbedPane2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane2.setDoubleBuffered(true);
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1082, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Cadastrar", jPanel7);

        PaneGuiaConsulta1.setBackground(new java.awt.Color(255, 255, 255));

        ConsultaTituloMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaTituloMenuRevistas1.setMaximumSize(new java.awt.Dimension(25, 25));
        ConsultaTituloMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));

        lblTitulo6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo6.setText("Titulo da revista");

        lblOrigem8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem8.setText("Área");

        ConsultaAreaMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaAreaMenuRevistas1.setToolTipText("Selecione");
        ConsultaAreaMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ConsultaAreaMenuRevistas1.setFocusable(false);
        ConsultaAreaMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaAreaMenuRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaAreaMenuRevistas1ActionPerformed(evt);
            }
        });

        ConsultaEspecificacaoMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaEspecificacaoMenuRevistas1.setToolTipText("Selecione");
        ConsultaEspecificacaoMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        ConsultaEspecificacaoMenuRevistas1.setDoubleBuffered(true);
        ConsultaEspecificacaoMenuRevistas1.setEditor(null);
        ConsultaEspecificacaoMenuRevistas1.setFocusable(false);
        ConsultaEspecificacaoMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaEspecificacaoMenuRevistas1.setName("Selecione"); // NOI18N
        ConsultaEspecificacaoMenuRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaEspecificacaoMenuRevistas1ActionPerformed(evt);
            }
        });

        lblEspecificacao6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao6.setText("Especificação");

        BotaoBuscaConsultaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaConsultaRevista1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaConsultaRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_24px.png"))); // NOI18N
        BotaoBuscaConsultaRevista1.setText("Busca revista");
        BotaoBuscaConsultaRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaConsultaRevista1.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaConsultaRevista1.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaConsultaRevista1.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoBuscaConsultaRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaConsultaRevista1ActionPerformed(evt);
            }
        });

        BotaoNovaBuscaConsultaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoNovaBuscaConsultaRevista1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoNovaBuscaConsultaRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Zoom In_24px.png"))); // NOI18N
        BotaoNovaBuscaConsultaRevista1.setText("Nova pesquisa");
        BotaoNovaBuscaConsultaRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaBuscaConsultaRevista1.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoNovaBuscaConsultaRevista1.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoNovaBuscaConsultaRevista1.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoNovaBuscaConsultaRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaBuscaConsultaRevista1ActionPerformed(evt);
            }
        });

        TabelaConsultaRevista1.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelaConsultaRevista1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabelaConsultaRevista1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaConsultaRevista1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TabelaConsultaRevista1);

        BotaoCancelaConsultaRevista2.setBackground(new java.awt.Color(255, 255, 255));
        BotaoCancelaConsultaRevista2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoCancelaConsultaRevista2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoCancelaConsultaRevista2.setText("Limpa busca");
        BotaoCancelaConsultaRevista2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelaConsultaRevista2.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista2.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista2.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelaConsultaRevista2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaConsulta1Layout = new javax.swing.GroupLayout(PaneGuiaConsulta1);
        PaneGuiaConsulta1.setLayout(PaneGuiaConsulta1Layout);
        PaneGuiaConsulta1Layout.setHorizontalGroup(
            PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                        .addComponent(BotaoNovaBuscaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoBuscaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoCancelaConsultaRevista2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                            .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitulo6)
                                .addComponent(ConsultaTituloMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ConsultaEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEspecificacao6))
                            .addGap(18, 18, 18)
                            .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblOrigem8)
                                .addComponent(ConsultaAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        PaneGuiaConsulta1Layout.setVerticalGroup(
            PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                                .addComponent(lblEspecificacao6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ConsultaEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                                .addComponent(lblOrigem8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ConsultaAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaConsulta1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(lblTitulo6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConsultaTituloMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoBuscaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoCancelaConsultaRevista2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoNovaBuscaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Consultar", PaneGuiaConsulta1);

        PaneGuiaAlteraRevista1.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaAlteraRevista1.setToolTipText("Limpa os campos acima");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel7.setText("Faça as alterações na revista:");
        jLabel7.setPreferredSize(new java.awt.Dimension(407, 41));

        lblData5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData5.setText("Ano");

        BuscaTituloMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaTituloMenuRevistas1.setMaximumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));

        lblTitulo4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo4.setText("Titulo da revista");

        BuscaEspecificacaoMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaEspecificacaoMenuRevistas1.setToolTipText("Selecione");
        BuscaEspecificacaoMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        BuscaEspecificacaoMenuRevistas1.setDoubleBuffered(true);
        BuscaEspecificacaoMenuRevistas1.setEditor(null);
        BuscaEspecificacaoMenuRevistas1.setFocusable(false);
        BuscaEspecificacaoMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaEspecificacaoMenuRevistas1.setName("Selecione"); // NOI18N
        BuscaEspecificacaoMenuRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscaEspecificacaoMenuRevistas1ActionPerformed(evt);
            }
        });

        lblOrigem9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem9.setText("Área");

        BuscaAreaMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaAreaMenuRevistas1.setToolTipText("Selecione");
        BuscaAreaMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        BuscaAreaMenuRevistas1.setFocusable(false);
        BuscaAreaMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaAreaMenuRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscaAreaMenuRevistas1ActionPerformed(evt);
            }
        });

        lblEspecificacao7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao7.setText("Especificação");

        lblIDAlteraRevista1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIDAlteraRevista1.setText("ID:");

        IDTravadoAlteraRevista1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        IDTravadoAlteraRevista1.setEnabled(false);

        BotaoBuscaAlterarOuRemoverRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaAlterarOuRemoverRevista1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaAlterarOuRemoverRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_24px.png"))); // NOI18N
        BotaoBuscaAlterarOuRemoverRevista1.setText("Busca revista");
        BotaoBuscaAlterarOuRemoverRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaAlterarOuRemoverRevista1.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverRevista1.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverRevista1.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaAlterarOuRemoverRevista1ActionPerformed(evt);
            }
        });

        BotaoAlterarRevistas1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAlterarRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoAlterarRevistas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Save as_24px.png"))); // NOI18N
        BotaoAlterarRevistas1.setText("Alterar");
        BotaoAlterarRevistas1.setToolTipText("Altera a revista selecionada");
        BotaoAlterarRevistas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarRevistas1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel8.setText("Escolha uma revista para alterar:");

        jSeparator3.setForeground(new java.awt.Color(37, 103, 125));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        TabelaAlterarOuRemoverRevista1.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelaAlterarOuRemoverRevista1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabelaAlterarOuRemoverRevista1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaAlterarOuRemoverRevista1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TabelaAlterarOuRemoverRevista1);

        lblTitulo7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo7.setText("Titulo da revista");

        AlteraTituloMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraTituloMenuRevistas1.setMaximumSize(new java.awt.Dimension(25, 25));
        AlteraTituloMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));

        lblOrigem10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem10.setText("Especificação");

        AlteraAreaMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraAreaMenuRevistas1.setToolTipText("Selecione");
        AlteraAreaMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        AlteraAreaMenuRevistas1.setDoubleBuffered(true);
        AlteraAreaMenuRevistas1.setEditor(null);
        AlteraAreaMenuRevistas1.setFocusable(false);
        AlteraAreaMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        AlteraAreaMenuRevistas1.setName("Selecione"); // NOI18N
        AlteraAreaMenuRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlteraAreaMenuRevistas1ActionPerformed(evt);
            }
        });

        AlteraEspecificacaoMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraEspecificacaoMenuRevistas1.setToolTipText("Selecione");
        AlteraEspecificacaoMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        AlteraEspecificacaoMenuRevistas1.setFocusable(false);
        AlteraEspecificacaoMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        AlteraEspecificacaoMenuRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlteraEspecificacaoMenuRevistas1ActionPerformed(evt);
            }
        });

        lblEspecificacao8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao8.setText("Área");

        BotaoRemoverRevistas1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoRemoverRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoRemoverRevistas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Trash_24px.png"))); // NOI18N
        BotaoRemoverRevistas1.setText("Remover");
        BotaoRemoverRevistas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoRemoverRevistas1.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverRevistas1.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverRevistas1.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoRemoverRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverRevistas1ActionPerformed(evt);
            }
        });

        lblOrigem11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem11.setText("Origem");

        AlteraOrigemMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraOrigemMenuRevistas1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doação", "Aquisição" }));
        AlteraOrigemMenuRevistas1.setBorder(null);
        AlteraOrigemMenuRevistas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AlteraOrigemMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        AlteraOrigemMenuRevistas1.setEditor(null);
        AlteraOrigemMenuRevistas1.setFocusCycleRoot(true);
        AlteraOrigemMenuRevistas1.setFocusable(false);
        AlteraOrigemMenuRevistas1.setLightWeightPopupEnabled(false);
        AlteraOrigemMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));

        BotaoResetaPesquisaAlterarOuRemoverRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoResetaPesquisaAlterarOuRemoverRevista1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Zoom In_24px.png"))); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverRevista1.setText("Nova Busca");
        BotaoResetaPesquisaAlterarOuRemoverRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoResetaPesquisaAlterarOuRemoverRevista1.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverRevista1.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverRevista1.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoResetaPesquisaAlterarOuRemoverRevista1ActionPerformed(evt);
            }
        });

        lblEspecificacao9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao9.setText("Quantidade");

        AlteraQuantidadeMenuRevistas1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        BotaoLimpaCamposAlterarRevistas2.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaCamposAlterarRevistas2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaCamposAlterarRevistas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Broom_24px.png"))); // NOI18N
        BotaoLimpaCamposAlterarRevistas2.setText("Limpar");
        BotaoLimpaCamposAlterarRevistas2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaCamposAlterarRevistas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaCamposAlterarRevistas2ActionPerformed(evt);
            }
        });

        AlteraAnoMenuRevistas1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N

        BotaoCancelaConsultaRevista3.setBackground(new java.awt.Color(255, 255, 255));
        BotaoCancelaConsultaRevista3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoCancelaConsultaRevista3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoCancelaConsultaRevista3.setText("Limpa busca");
        BotaoCancelaConsultaRevista3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelaConsultaRevista3.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista3.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista3.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoCancelaConsultaRevista3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelaConsultaRevista3ActionPerformed(evt);
            }
        });

        BotaoLimpaCamposAlterarRevistas3.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaCamposAlterarRevistas3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaCamposAlterarRevistas3.setText("Liberar ações");
        BotaoLimpaCamposAlterarRevistas3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaCamposAlterarRevistas3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaCamposAlterarRevistas3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaAlteraRevista1Layout = new javax.swing.GroupLayout(PaneGuiaAlteraRevista1);
        PaneGuiaAlteraRevista1.setLayout(PaneGuiaAlteraRevista1Layout);
        PaneGuiaAlteraRevista1Layout.setHorizontalGroup(
            PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                .addComponent(BotaoResetaPesquisaAlterarOuRemoverRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotaoBuscaAlterarOuRemoverRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoCancelaConsultaRevista3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblOrigem9)
                                            .addComponent(BuscaAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BuscaEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEspecificacao7))
                                        .addGap(66, 66, 66))
                                    .addComponent(BuscaTituloMenuRevistas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(lblTitulo4))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(14, 14, 14)))))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEspecificacao9)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo7)
                    .addComponent(AlteraTituloMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlteraQuantidadeMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                            .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblOrigem10)
                                .addComponent(lblEspecificacao8)
                                .addComponent(AlteraAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AlteraEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(112, 112, 112)
                            .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblData5)
                                .addComponent(AlteraOrigemMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblOrigem11)
                                .addComponent(AlteraAnoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                            .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(BotaoLimpaCamposAlterarRevistas3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                    .addComponent(lblIDAlteraRevista1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(IDTravadoAlteraRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(BotaoLimpaCamposAlterarRevistas2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BotaoRemoverRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BotaoAlterarRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))))
                .addGap(17, 17, 17))
        );
        PaneGuiaAlteraRevista1Layout.setVerticalGroup(
            PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscaTituloMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                .addComponent(lblOrigem9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BuscaAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblEspecificacao7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BuscaEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoBuscaAlterarOuRemoverRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoResetaPesquisaAlterarOuRemoverRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoCancelaConsultaRevista3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo7)
                        .addGap(4, 4, 4)
                        .addComponent(AlteraTituloMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                .addComponent(lblOrigem10)
                                .addGap(3, 3, 3)
                                .addComponent(AlteraEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                .addComponent(lblData5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlteraAnoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEspecificacao8)
                            .addComponent(lblOrigem11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AlteraAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlteraOrigemMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblEspecificacao9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlteraQuantidadeMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(BotaoLimpaCamposAlterarRevistas3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoLimpaCamposAlterarRevistas2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoRemoverRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoAlterarRevistas1))
                        .addGap(46, 46, 46)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IDTravadoAlteraRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDAlteraRevista1))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Gerenciar", PaneGuiaAlteraRevista1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
            .addComponent(jTabbedPane2)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 722, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneAreaLayout = new javax.swing.GroupLayout(PaneArea);
        PaneArea.setLayout(PaneAreaLayout);
        PaneAreaLayout.setHorizontalGroup(
            PaneAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneAreaLayout.setVerticalGroup(
            PaneAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PaneMae.add(PaneArea, "card2");

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
            .addComponent(PaneMae, javax.swing.GroupLayout.PREFERRED_SIZE, 862, Short.MAX_VALUE)
            .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//-------------------------Inicio das ações de revista ------------------------------------//
    private void BotaoAdicionarRevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoAdicionarRevistaMouseClicked
        PaneRevista.setVisible(true);
        PaneArea.setVisible(false);
        PaneEspecificacao.setVisible(false);
        setLblColor(BotaoAdicionarRevista);
        ResetColor(BotaoAdicionarArea);
        ResetColor(BotaoAdicionarEspecificacao);
    }//GEN-LAST:event_BotaoAdicionarRevistaMouseClicked

    private void BotaoAdicionarAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoAdicionarAreaMouseClicked
        PaneRevista.setVisible(false);
        PaneArea.setVisible(true);
        PaneEspecificacao.setVisible(false);
        setLblColor(BotaoAdicionarArea);
        ResetColor(BotaoAdicionarRevista);
        ResetColor(BotaoAdicionarEspecificacao);
    }//GEN-LAST:event_BotaoAdicionarAreaMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void BotaoNovaBuscaConsultaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaBuscaConsultaRevistaActionPerformed
        atualizarConsultaRevista();
        DestravaCamposConsultaRevista();
        LimpaCamposConsultaRevista();
    }//GEN-LAST:event_BotaoNovaBuscaConsultaRevistaActionPerformed

    private void BotaoBuscaConsultaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscaConsultaRevistaActionPerformed
        ListaBuscaRevista = null;
        Revistas rev = new Revistas();
        RevistaDAO revistaDAO = new RevistaDAO();

        try {

            if (!ConsultaTituloMenuRevistas.getText().isEmpty()) {
                rev.setTitulo(ConsultaTituloMenuRevistas.getText());
            }

            if (ConsultaEspecificacaoMenuRevistas.getSelectedItem() != null) {
                rev.setEspecificacao((String) ConsultaEspecificacaoMenuRevistas.getSelectedItem());
            }

            if (ConsultaAreaMenuRevistas.getSelectedItem() != null) {
                rev.setArea((String) ConsultaAreaMenuRevistas.getSelectedItem());
            }

            //System.out.println(BuscaAreaMenuRevistas.getSelectedItem());
            ListaBuscaRevista = revistaDAO.ListaBuscaRevista(rev);

            ConsultaRevistaComFiltro();
            //TravaCamposConsultaRevista();
        } catch (Exception E) {
            System.out.println(E.getMessage());
            JOptionPane.showMessageDialog(null, "Problema no BotaoBuscaConsultaRevista do DashBoard, consulta de revista falhou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_BotaoBuscaConsultaRevistaActionPerformed

    private void ConsultaEspecificacaoMenuRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaEspecificacaoMenuRevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConsultaEspecificacaoMenuRevistasActionPerformed

    private void ConsultaAreaMenuRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaAreaMenuRevistasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConsultaAreaMenuRevistasActionPerformed

    private void BotaoLimpaCamposAlterarRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaCamposAlterarRevistasActionPerformed
        LimpaCamposAlteraRevista();
        TravaCamposAlteraRevista();
        TravaBotoeAlteraRevista();
        BotaoLimpaCamposAlterarRevistas.setEnabled(false);
    }//GEN-LAST:event_BotaoLimpaCamposAlterarRevistasActionPerformed

    private void BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed
        DestravaCamposDoPesquisarRevistasParaAlteracao();
        TravaCamposAlteraRevista();
        TravaBotoeAlteraRevista();
        ResetaCamposAlterarRevistas();
        atualizarTabelaRevista();
        LimpaCamposAlteraRevista();
        BotaoLimpaCamposAlterarRevistas.setEnabled(false);
    }//GEN-LAST:event_BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed


    private void BotaoRemoverRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverRevistasActionPerformed

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
                    LimpaCamposAlteraRevista();
                    TravaCamposAlteraRevista();
                    atualizarConsultaRevista();
                    TravaBotoeAlteraRevista();
                    ResetaCamposAlterarRevistas();

                    //atualizarTabelaCliente();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Problema no BotaoRemoverRevistas1ActionPerformed, remoção de revista falhou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_BotaoRemoverRevistasActionPerformed

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
        AlteraAreaMenuRevistas.getModel().setSelectedItem(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 2).toString());
        AlteraEspecificacaoMenuRevistas.getModel().setSelectedItem(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 3).toString());
        AlteraAnoMenuRevistas.setText(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 4).toString());
        AlteraQuantidadeMenuRevistas.setText(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 5).toString());
        AlteraOrigemMenuRevistas.getModel().setSelectedItem(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 6).toString());

        BotaoLimpaCamposAlterarRevistas.setEnabled(true);
        TravaCamposAlteraRevista();
        TravaBotoeAlteraRevista();
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
                rev.setData(Integer.parseInt(AlteraAnoMenuRevistas.getText()));
                rev.setOrigem((String) AlteraOrigemMenuRevistas.getSelectedItem());
                rev.setQuantidade(Integer.parseInt(AlteraQuantidadeMenuRevistas.getText()));

                if (JOptionPane.showConfirmDialog(null, "Deseja mesmo alterar essa revista?", "pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    revistaDAO.AlterarRevista(rev);
                    JOptionPane.showMessageDialog(null, "Revista alterada com sucesso", "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    atualizarTabelaRevista();
                   
                    LimpaCamposAlteraRevista();
                    TravaCamposAlteraRevista();
                    TravaBotoeAlteraRevista();
                    atualizarConsultaRevista();
                    ResetaCamposAlterarRevistas();
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

    private void campoQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoQuantidadeActionPerformed

    private void BotaoNovaRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaRevista1ActionPerformed
        DestravaCamposDoNovaRevista();
        limpaCamposNovaRevista();
        DestravaBotoesCadRevista();
        BotaoNovaRevista1.setEnabled(false);
    }//GEN-LAST:event_BotaoNovaRevista1ActionPerformed

    //Aqui cria-se uma nova revista
    private void BotaoNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaRevistaActionPerformed
        if (campoTitulo.getText().isEmpty() || campoData.getText().isEmpty() || campoQuantidade.getText().isEmpty() || ComboBoxEspecificacaoNovaRevista.getSelectedItem() == null || ComboBoxAreaNovaRevista.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Há campos não preenchidos", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Revistas rev = new Revistas();
            try {

                rev.getID();
                rev.setTitulo(campoTitulo.getText());
                rev.setEspecificacao((String) ComboBoxEspecificacaoNovaRevista.getSelectedItem());
                rev.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                rev.setOrigem((String) ComboBoxOrigem.getSelectedItem());
                rev.setData(Integer.parseInt(campoData.getText()));
                rev.setArea((String) ComboBoxAreaNovaRevista.getSelectedItem());

                RevistaDAO revistaDAO = new RevistaDAO();
                revistaDAO.InserirNovaRevistas(rev);
                JOptionPane.showMessageDialog(null, "Revista cadastrada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

                limpaCamposNovaRevista();
                TravaCamposDoNovaRevista();
                atualizarTabelaRevista();
                atualizarConsultaRevista();
                TravaBotoesCadRevista();
                BotaoNovaRevista1.setEnabled(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problema no BotaoNovaRevistaActionPerformed do DashBoard, criação de revista falhou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoNovaRevistaActionPerformed

    private void ComboBoxAreaNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAreaNovaRevistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAreaNovaRevistaActionPerformed

    private void campoDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataActionPerformed

    private void TabelaConsultaRevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaConsultaRevistaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TabelaConsultaRevistaMouseClicked

    private void BotaoNovaRevista2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaRevista2ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Todos os dados informados serão perdidos. Desaja continuar?", "Cadastro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            limpaCamposNovaRevista();
            TravaCamposDoNovaRevista();
            TravaBotoesCadRevista();
            BotaoNovaRevista1.setEnabled(true);
        }
    }//GEN-LAST:event_BotaoNovaRevista2ActionPerformed

    private void BotaoAdicionarEspecificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoAdicionarEspecificacaoMouseClicked
        PaneRevista.setVisible(false);
        PaneArea.setVisible(false);
        PaneEspecificacao.setVisible(true);
        setLblColor(BotaoAdicionarEspecificacao);
        ResetColor(BotaoAdicionarRevista);
        ResetColor(BotaoAdicionarArea);

    }//GEN-LAST:event_BotaoAdicionarEspecificacaoMouseClicked

    private void BotaoCancelaConsultaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelaConsultaRevistaActionPerformed
        LimpaCamposConsultaRevista();
        //TravaCamposConsultaRevista();
    }//GEN-LAST:event_BotaoCancelaConsultaRevistaActionPerformed

    private void BotaoCancelaConsultaRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelaConsultaRevista1ActionPerformed
        ResetaCamposAlterarRevistas();
    }//GEN-LAST:event_BotaoCancelaConsultaRevista1ActionPerformed

    private void BotaoLimpaCamposAlterarRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaCamposAlterarRevistas1ActionPerformed
        DestravaBotoeAlteraRevista();
        DesravaCamposAlteraRevista();
    }//GEN-LAST:event_BotaoLimpaCamposAlterarRevistas1ActionPerformed

    private void ConsultaAreaMenuRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaAreaMenuRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConsultaAreaMenuRevistas1ActionPerformed

    private void ConsultaEspecificacaoMenuRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaEspecificacaoMenuRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConsultaEspecificacaoMenuRevistas1ActionPerformed

    private void BotaoBuscaConsultaRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscaConsultaRevista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoBuscaConsultaRevista1ActionPerformed

    private void BotaoNovaBuscaConsultaRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaBuscaConsultaRevista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoNovaBuscaConsultaRevista1ActionPerformed

    private void TabelaConsultaRevista1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaConsultaRevista1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TabelaConsultaRevista1MouseClicked

    private void BotaoCancelaConsultaRevista2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelaConsultaRevista2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoCancelaConsultaRevista2ActionPerformed

    private void BuscaEspecificacaoMenuRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscaEspecificacaoMenuRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscaEspecificacaoMenuRevistas1ActionPerformed

    private void BuscaAreaMenuRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscaAreaMenuRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscaAreaMenuRevistas1ActionPerformed

    private void BotaoBuscaAlterarOuRemoverRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscaAlterarOuRemoverRevista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoBuscaAlterarOuRemoverRevista1ActionPerformed

    private void BotaoAlterarRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoAlterarRevistas1ActionPerformed

    private void TabelaAlterarOuRemoverRevista1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaAlterarOuRemoverRevista1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TabelaAlterarOuRemoverRevista1MouseClicked

    private void AlteraAreaMenuRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlteraAreaMenuRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlteraAreaMenuRevistas1ActionPerformed

    private void AlteraEspecificacaoMenuRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlteraEspecificacaoMenuRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlteraEspecificacaoMenuRevistas1ActionPerformed

    private void BotaoRemoverRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoRemoverRevistas1ActionPerformed

    private void BotaoResetaPesquisaAlterarOuRemoverRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoResetaPesquisaAlterarOuRemoverRevista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoResetaPesquisaAlterarOuRemoverRevista1ActionPerformed

    private void BotaoLimpaCamposAlterarRevistas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaCamposAlterarRevistas2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoLimpaCamposAlterarRevistas2ActionPerformed

    private void BotaoCancelaConsultaRevista3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelaConsultaRevista3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoCancelaConsultaRevista3ActionPerformed

    private void BotaoLimpaCamposAlterarRevistas3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaCamposAlterarRevistas3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoLimpaCamposAlterarRevistas3ActionPerformed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2MouseClicked
//-------------------------fim das ações de revista ------------------------------------//
    public void setLblColor(JLabel lbl) {
        lbl.setBackground(new Color(150, 150, 150));
    }

    public void ResetColor(JLabel lbl) {
        lbl.setBackground(new Color(37, 103, 125));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AlteraAnoMenuRevistas;
    private javax.swing.JTextField AlteraAnoMenuRevistas1;
    private javax.swing.JComboBox<String> AlteraAreaMenuRevistas;
    private javax.swing.JComboBox<String> AlteraAreaMenuRevistas1;
    private javax.swing.JComboBox<String> AlteraEspecificacaoMenuRevistas;
    private javax.swing.JComboBox<String> AlteraEspecificacaoMenuRevistas1;
    private javax.swing.JComboBox<String> AlteraOrigemMenuRevistas;
    private javax.swing.JComboBox<String> AlteraOrigemMenuRevistas1;
    private javax.swing.JTextField AlteraQuantidadeMenuRevistas;
    private javax.swing.JTextField AlteraQuantidadeMenuRevistas1;
    private javax.swing.JTextField AlteraTituloMenuRevistas;
    private javax.swing.JTextField AlteraTituloMenuRevistas1;
    private javax.swing.JLabel BotaoAdicionarArea;
    private javax.swing.JLabel BotaoAdicionarEspecificacao;
    private javax.swing.JLabel BotaoAdicionarRevista;
    private javax.swing.JButton BotaoAlterarRevistas;
    private javax.swing.JButton BotaoAlterarRevistas1;
    private javax.swing.JButton BotaoBuscaAlterarOuRemoverRevista;
    private javax.swing.JButton BotaoBuscaAlterarOuRemoverRevista1;
    private javax.swing.JButton BotaoBuscaConsultaRevista;
    private javax.swing.JButton BotaoBuscaConsultaRevista1;
    private javax.swing.JButton BotaoCancelaConsultaRevista;
    private javax.swing.JButton BotaoCancelaConsultaRevista1;
    private javax.swing.JButton BotaoCancelaConsultaRevista2;
    private javax.swing.JButton BotaoCancelaConsultaRevista3;
    private javax.swing.JButton BotaoLimpaCamposAlterarRevistas;
    private javax.swing.JButton BotaoLimpaCamposAlterarRevistas1;
    private javax.swing.JButton BotaoLimpaCamposAlterarRevistas2;
    private javax.swing.JButton BotaoLimpaCamposAlterarRevistas3;
    private javax.swing.JButton BotaoNovaBuscaConsultaRevista;
    private javax.swing.JButton BotaoNovaBuscaConsultaRevista1;
    private javax.swing.JButton BotaoNovaRevista;
    private javax.swing.JButton BotaoNovaRevista1;
    private javax.swing.JButton BotaoNovaRevista2;
    private javax.swing.JButton BotaoRemoverRevistas;
    private javax.swing.JButton BotaoRemoverRevistas1;
    private javax.swing.JButton BotaoResetaPesquisaAlterarOuRemoverRevista;
    private javax.swing.JButton BotaoResetaPesquisaAlterarOuRemoverRevista1;
    private javax.swing.JComboBox<String> BuscaAreaMenuRevistas;
    private javax.swing.JComboBox<String> BuscaAreaMenuRevistas1;
    private javax.swing.JComboBox<String> BuscaEspecificacaoMenuRevistas;
    private javax.swing.JComboBox<String> BuscaEspecificacaoMenuRevistas1;
    private javax.swing.JTextField BuscaTituloMenuRevistas;
    private javax.swing.JTextField BuscaTituloMenuRevistas1;
    private javax.swing.JComboBox<String> ComboBoxAreaNovaRevista;
    private javax.swing.JComboBox<String> ComboBoxEspecificacaoNovaRevista;
    private javax.swing.JComboBox<String> ComboBoxOrigem;
    private javax.swing.JComboBox<String> ConsultaAreaMenuRevistas;
    private javax.swing.JComboBox<String> ConsultaAreaMenuRevistas1;
    private javax.swing.JComboBox<String> ConsultaEspecificacaoMenuRevistas;
    private javax.swing.JComboBox<String> ConsultaEspecificacaoMenuRevistas1;
    private javax.swing.JTextField ConsultaTituloMenuRevistas;
    private javax.swing.JTextField ConsultaTituloMenuRevistas1;
    private javax.swing.JTextField IDTravadoAlteraRevista;
    private javax.swing.JTextField IDTravadoAlteraRevista1;
    private javax.swing.JPanel PaneArea;
    private javax.swing.JPanel PaneEspecificacao;
    private javax.swing.JPanel PaneGuiaAlteraRevista;
    private javax.swing.JPanel PaneGuiaAlteraRevista1;
    private javax.swing.JPanel PaneGuiaConsulta;
    private javax.swing.JPanel PaneGuiaConsulta1;
    private javax.swing.JPanel PaneGuiaNovaRevista;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel PaneRevista;
    private javax.swing.JPanel SideBoard;
    private javax.swing.JTable TabelaAlterarOuRemoverRevista;
    private javax.swing.JTable TabelaAlterarOuRemoverRevista1;
    private javax.swing.JTable TabelaConsultaRevista;
    private javax.swing.JTable TabelaConsultaRevista1;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblData1;
    private javax.swing.JLabel lblData2;
    private javax.swing.JLabel lblData5;
    private javax.swing.JLabel lblEspecificacao;
    private javax.swing.JLabel lblEspecificacao2;
    private javax.swing.JLabel lblEspecificacao3;
    private javax.swing.JLabel lblEspecificacao4;
    private javax.swing.JLabel lblEspecificacao5;
    private javax.swing.JLabel lblEspecificacao6;
    private javax.swing.JLabel lblEspecificacao7;
    private javax.swing.JLabel lblEspecificacao8;
    private javax.swing.JLabel lblEspecificacao9;
    private javax.swing.JLabel lblIDAlteraRevista;
    private javax.swing.JLabel lblIDAlteraRevista1;
    private javax.swing.JLabel lblOrigem;
    private javax.swing.JLabel lblOrigem1;
    private javax.swing.JLabel lblOrigem10;
    private javax.swing.JLabel lblOrigem11;
    private javax.swing.JLabel lblOrigem2;
    private javax.swing.JLabel lblOrigem4;
    private javax.swing.JLabel lblOrigem5;
    private javax.swing.JLabel lblOrigem6;
    private javax.swing.JLabel lblOrigem8;
    private javax.swing.JLabel lblOrigem9;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JLabel lblTitulo5;
    private javax.swing.JLabel lblTitulo6;
    private javax.swing.JLabel lblTitulo7;
    // End of variables declaration//GEN-END:variables

}
