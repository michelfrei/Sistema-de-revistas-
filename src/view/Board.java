/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.Conexao;
import DAO.*;
import Extra.JTextFieldLimit;
import controller.SetupAutoComplete;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Board extends javax.swing.JFrame {

    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Revistas> ListaRevista;
    List<Revistas> ListaBuscaRevista;

    List<Area> ListaArea;
    List<Area> ListaBuscaArea;

    List<Especificacao> ListaEspecificacao;
    List<Especificacao> ListaBuscaEspecificacao;

    List<Registro> ListaRegistro;
    List<Registro> ListaBuscaRegistro;

    List<Editora> ListaEditora;
    List<Editora> ListaBuscaEditora;

    public Board() {
        initComponents();
        setIcon();
        inicioRevista();
        inicioFerramentas();
        PaneRevista.setVisible(true);
        PaneFerramentas.setVisible(false);
        setLblColor(BotaoRevista);
        ResetColor(BotaoFerramenta);
        ResetColor(BotaoRegistro);
        LimpaCombos();

    }

    private void inicioFerramentas() {
        atualizarConsultaArea();
        atualizarConsultaEspecificacao();
        atualizarConsultaEditora();
        AlteraIDMenuEspecificacao.setVisible(false);
        lblIDAlteraEsp.setVisible(false);
        AlteraIDMenuArea.setVisible(false);
        lblIDAlteraArea.setVisible(false);
        AlteraIDMenuEditora.setVisible(false);
        lblIDAlteraEditora.setVisible(false);
        TravaCamposNovoTipo();
        TravaCamposConsultaAreaAlterarOuRemover();
        TravaCamposConsultaEspAlterarOuRemover();
        TravaCamposConsultaTipo();
        TravaTudoEspAlterarOuRemover();
        TravaTudoAreaAlterarOuRemover();
        TravaTudoEditoraAlterarOuRemover();
        TravaCamposConsultaEditoraAlterarOuRemover();
        BotaoLiberaCamposAlterarArea.setEnabled(false);
        BotaoLiberarAcoesEsp.setEnabled(false);
        BotaoLiberarAcoesEditora.setEnabled(false);
        ComboBoxEditoraRegistro();
        ComboBoxAreaRegistro();
    }

    private void inicioRevista() {
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
        TravaCamposDoPesquisarRevistasParaAlteracao();
        BotaoBuscaConsultaRevista.setEnabled(false);
        BotaoLimpaConsultaRevista.setEnabled(false);
        BotaoLiberaCamposAlterarRevistas.setEnabled(false);
    }

    private void TravaBotoesCadRevista() {
        BotaoSalvarNovaRevista.setEnabled(false);
        BotaoCancelarNovaRevista2.setEnabled(false);
    }

    private void DestravaBotoesCadRevista() {
        BotaoSalvarNovaRevista.setEnabled(true);
        BotaoCancelarNovaRevista2.setEnabled(true);
    }

    private void limpaCamposNovaRevista() {
        campoTitulo.setText("");
        campoData.setText("");
        campoQuantidade.setText("");
        ComboBoxAreaNovaRevista.setSelectedItem(null);
        ComboBoxEspecificacaoNovaRevista.setSelectedItem(null);
        ComboBoxOrigem.getModel().setSelectedItem("Doação");
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
        BotaoBuscaAlterarOuRemoverRevista.setEnabled(false);
        BotaoLimpaBuscaRevista.setEnabled(false);
    }

    private void DestravaCamposDoPesquisarRevistasParaAlteracao() {
        BuscaTituloMenuRevistas.setEnabled(true);
        BuscaAreaMenuRevistas.setEnabled(true);
        BuscaEspecificacaoMenuRevistas.setEnabled(true);
        BotaoBuscaAlterarOuRemoverRevista.setEnabled(true);
        BotaoLimpaBuscaRevista.setEnabled(true);
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
    private void TravaCamposNovoTipo() {
        CampoNovoTituloTipo.setEnabled(false);
        ComboBoxNovoTipo.setEnabled(false);
        BotaoCancelaNovoTipo.setEnabled(false);
        BotaoSalvarNovoTipo.setEnabled(false);
    }

    private void LimpaCamposNovoTipo() {
        CampoNovoTituloTipo.setText("");
        ComboBoxNovoTipo.setSelectedItem("Área");
    }

    private void DestravaCamposNovoTipo() {
        CampoNovoTituloTipo.setEnabled(true);
        ComboBoxNovoTipo.setEnabled(true);
        BotaoCancelaNovoTipo.setEnabled(true);
        BotaoSalvarNovoTipo.setEnabled(true);
    }

    //--------------------------------------------------------------------
    private void DestravaCamposConsultaTipo() {
        ConsultaTipoComboBox.setEnabled(true);
        BotaoConsultaArea.setEnabled(true);
        ConsultaNomeMenuTipo.setEnabled(true);
        BotaoLimpaConsulta.setEnabled(true);
    }

    private void TravaCamposConsultaTipo() {
        ConsultaTipoComboBox.setEnabled(false);
        BotaoConsultaArea.setEnabled(false);
        ConsultaNomeMenuTipo.setEnabled(false);
        BotaoLimpaConsulta.setEnabled(false);
    }

    private void LimpaCamposConsultaTipo() {
        ConsultaNomeMenuTipo.setText("");
        ConsultaTipoComboBox.setSelectedItem("Área");
    }

    //------------------------------------------------------------------
    private void TravaCamposConsultaAreaAlterarOuRemover() {
        BuscaTituloMenuArea.setEnabled(false);
        BotaoBuscaAlterarOuRemoverArea.setEnabled(false);
        BotaoLimpaAlterarOuRemoverArea.setEnabled(false);
    }

    private void DestravaCamposConsultaAreaAlterarOuRemover() {
        BuscaTituloMenuArea.setEnabled(true);
        BotaoBuscaAlterarOuRemoverArea.setEnabled(true);
        BotaoLimpaAlterarOuRemoverArea.setEnabled(true);
    }

    private void LimpaCamposConsultaAreaAlterarOuRemover() {
        BuscaTituloMenuArea.setText("");
    }

    //-------------------------------------------------------------------
    private void TravaCamposConsultaEspAlterarOuRemover() {
        BuscaTituloMenuEspecificacao.setEnabled(false);
        BotaoBuscaAlterarOuRemoverEsp.setEnabled(false);
        BotaoLimpaAlterarOuRemoverEsp.setEnabled(false);
    }

    private void DestravaCamposConsultaEspAlterarOuRemover() {
        BuscaTituloMenuEspecificacao.setEnabled(true);
        BotaoBuscaAlterarOuRemoverEsp.setEnabled(true);
        BotaoLimpaAlterarOuRemoverEsp.setEnabled(true);
    }

    private void LimpaCamposConsultaEspAlterarOuRemover() {
        BuscaTituloMenuEspecificacao.setText("");
    }

    //-------------------------------------------------------------------
    private void LimpaCamposEspAlterarOuRemover() {
        AlteraNomeMenuEspecificacao.setText("");
        AlteraIDMenuEspecificacao.setText("");
    }

    private void DestravaTudoEspAlterarOuRemover() {
        AlteraNomeMenuEspecificacao.setEnabled(true);
        BotaoLimpaCamposAlterarEsp.setEnabled(true);
        BotaoRemoverEsp.setEnabled(true);
        BotaoAlterarEsp.setEnabled(true);
    }

    private void TravaTudoEspAlterarOuRemover() {
        AlteraNomeMenuEspecificacao.setEnabled(false);
        BotaoLimpaCamposAlterarEsp.setEnabled(false);
        BotaoRemoverEsp.setEnabled(false);
        BotaoAlterarEsp.setEnabled(false);
    }

    /*private void TravaCamposNovoTipo() {
        CampoNovoTituloTipo.setEnabled(false);
        ComboBoxNovoTipo.setEnabled(false);
        BotaoCancelaNovoTipo.setEnabled(false);
        BotaoSalvarNovoTipo.setEnabled(false);
    }*/
    //--------------------------------------------------------------------
    private void LimpaCamposAreaAlterarOuRemover() {
        AlteraNomeMenuArea.setText("");
        AlteraIDMenuArea.setText("");
    }

    private void DestravaTudoAreaAlterarOuRemover() {
        AlteraNomeMenuArea.setEnabled(true);
        BotaoLimpaCamposAlterarArea.setEnabled(true);
        BotaoRemoverArea.setEnabled(true);
        BotaoAlterarArea.setEnabled(true);
    }
  
    private void TravaTudoAreaAlterarOuRemover() {
        AlteraNomeMenuArea.setEnabled(false);
        BotaoLimpaCamposAlterarArea.setEnabled(false);
        BotaoRemoverArea.setEnabled(false);
        BotaoAlterarArea.setEnabled(false);
    }
    //-----------------------------------------------------------------------
    private void LimpaCamposEditoraAlterarOuRemover() {
        AlteraNomeMenuEditora.setText("");
        AlteraIDMenuEditora.setText("");
    }

    private void DestravaTudoEditoraAlterarOuRemover() {
        AlteraNomeMenuEditora.setEnabled(true);
        BotaoLimpaCamposAlterarEditora.setEnabled(true);
        BotaoRemoverEditora.setEnabled(true);
        BotaoAlterarEditora.setEnabled(true);
    }

    private void TravaTudoEditoraAlterarOuRemover() {
        AlteraNomeMenuEditora.setEnabled(false);
        BotaoLimpaCamposAlterarEditora.setEnabled(false);
        BotaoRemoverEditora.setEnabled(false);
        BotaoAlterarEditora.setEnabled(false);
    }
    
    private void TravaCamposConsultaEditoraAlterarOuRemover() {
        BuscaTituloMenuEditora.setEnabled(false);
        BotaoBuscaAlterarOuRemoverEditora.setEnabled(false);
        BotaoLimpaAlterarOuRemoverEditora.setEnabled(false);
    }

    private void DestravaCamposConsultaEditoraAlterarOuRemover() {
        BuscaTituloMenuEditora.setEnabled(true);
        BotaoBuscaAlterarOuRemoverEditora.setEnabled(true);
        BotaoLimpaAlterarOuRemoverEditora.setEnabled(true);
    }

    private void LimpaCamposConsultaEditoraAlterarOuRemover() {
        BuscaTituloMenuEditora.setText("");
    }

    //----------------Limpa todas combobox da tela de cadastro de revista-----------------
    public void LimpaCombos() {
        ComboBoxAreaNovaRevista.setSelectedItem(null);
        ComboBoxEspecificacaoNovaRevista.setSelectedItem(null);
        BuscaEspecificacaoMenuRevistas.setSelectedItem(null);
        BuscaAreaMenuRevistas.setSelectedItem(null);
        AlteraAreaMenuRevistas.setSelectedItem(null);
        AlteraEspecificacaoMenuRevistas.setSelectedItem(null);
        ConsultaAreaMenuRevistas.setSelectedItem(null);
        ConsultaEspecificacaoMenuRevistas.setSelectedItem(null);
        ComboBoxEditoraRegistro.setSelectedItem(null);
        ComboBoxAreaRegistro.setSelectedItem(null);
    }

    //-----------------Esvazia as combobox + chamada de combobox -------------------------
    public void ClearComboBoxEspecificacao() {
        ComboBoxEspecificacaoNovaRevista.removeAllItems();
        BuscaEspecificacaoMenuRevistas.removeAllItems();
        AlteraEspecificacaoMenuRevistas.removeAllItems();
        ConsultaEspecificacaoMenuRevistas.removeAllItems();
    }

    public void ClearComboBoxArea() {
        ComboBoxAreaNovaRevista.removeAllItems();
        BuscaAreaMenuRevistas.removeAllItems();
        AlteraAreaMenuRevistas.removeAllItems();
        ConsultaAreaMenuRevistas.removeAllItems();

    }

    public void ComboboxArea() {
        ComboBoxAreaNovaRevista();
        BuscaAreaMenuRevistas();
        AlteraAreaMenuRevistas();
        ConsultaAreaMenuRevistas();
    }

    public void ComboboxEspecificacao() {
        ComboBoxEspecificacaoNovaRevista();
        BuscaEspecificacaoMenuRevistas();
        AlteraEspecificacaoMenuRevistas();
        ConsultaEspecificacaoRevistas();
    }

    public void teste() {
        RevistaDAO revistaDAO = new RevistaDAO();
        //Preenche o combobox com as razões sociais
        ArrayList<String> listaRevistas = new ArrayList<>();

        try {
            for (Revistas revista : revistaDAO.ListaRevista()) {
                listaRevistas.add(revista.getTitulo());
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }

        SetupAutoComplete.setupAutoComplete(campoTitulo, listaRevistas);

    }

    //------------------------------------------------------------------------------------
    //ComboBox do PaneRevista, referente a aba PaneGuiaNovaRevista
    private void ComboBoxAreaNovaRevista() { //ok
        try {
            String SQL = "Select * from revista.Area order by id asc";
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
    private void ComboBoxEspecificacaoNovaRevista() {//ok
        try {
            String SQL = "Select * from revista.Especificação order by id asc";
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
    private void BuscaEspecificacaoMenuRevistas() {//ok
        try {
            String SQL = "Select * from revista.Especificação order by id asc";
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
    private void BuscaAreaMenuRevistas() { //ok
        try {
            String SQL = "Select * from revista.Area order by id asc";
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
    private void AlteraAreaMenuRevistas() { //ok
        try {
            String SQL = "Select * from revista.Area order by id asc";
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
    private void AlteraEspecificacaoMenuRevistas() {//ok
        try {
            String SQL = "Select * from revista.Especificação order by id asc";
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
    private void ConsultaAreaMenuRevistas() {//ok
        try {
            String SQL = "Select * from revista.Area order by id asc";
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
    private void ConsultaEspecificacaoRevistas() {//ok
        try {
            String SQL = "Select * from revista.Especificação order by id asc";
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
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
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
        TabelaAlterarOuRemoverRevista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        TabelaAlterarOuRemoverRevista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
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
        TabelaConsultaRevista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        TabelaConsultaRevista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaConsultaRevista.setRowHeight(25);
        TabelaConsultaRevista.updateUI();
    }
    
    
        private void ComboBoxEditoraRegistro() { //ok
        try {
            String SQL = "Select * from revista.editora order by id asc";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                ComboBoxEditoraRegistro.addItem(Nome);

            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }
    private void ComboBoxAreaRegistro() { //ok
        try {
            String SQL = "Select * from revista.area order by id asc";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            rs = stmt.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("Nome");
                ComboBoxAreaRegistro.addItem(Nome);

            }

        } catch (Exception e) {
            System.out.println("problema na combobox");
        }
    }
    
    //------------------------------------ fim das operacoes de lista referente a revista
    //------------------------------------ inicio das operacoes de lista referente a ferramenta
    public void atualizarConsultaArea() {

        Area area = new Area();
        AreaDAO areaDAO = new AreaDAO();
        try {
            ListaArea = areaDAO.ListaArea();
        } catch (SQLException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaArea.size()][2];
        int i = 0;
        for (Area ar : ListaArea) {
            dados[i][0] = String.valueOf(ar.getId());
            dados[i][1] = ar.getNome();
            i++;
        }
        String tituloColuna[] = {"id", "Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverArea.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaAlterarOuRemoverArea.setRowHeight(25);
        TabelaAlterarOuRemoverArea.updateUI();
    }

    public void ConsultaAreaAlterar() {
        Area area = new Area();
        String dados[][] = new String[ListaBuscaArea.size()][2];
        int i = 0;
        for (Area ar : ListaBuscaArea) {
            dados[i][0] = String.valueOf(ar.getId());
            dados[i][1] = ar.getNome();

            i++;
        }
        String tituloColuna[] = {"id", "Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverArea.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaAlterarOuRemoverArea.setRowHeight(25);
        TabelaAlterarOuRemoverArea.updateUI();
    }

    //------------------------------------------------------------------------------------
    public void atualizarConsultaEditora() {

        Editora editora = new Editora();
        EditoraDAO editoraDAO = new EditoraDAO();
        try {
            ListaEditora = editoraDAO.ListaEditora();
        } catch (SQLException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaEditora.size()][2];
        int i = 0;
        for (Editora ed : ListaEditora) {
            dados[i][0] = String.valueOf(ed.getId());
            dados[i][1] = ed.getNome();
            i++;
        }
        String tituloColuna[] = {"id", "Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverEditora.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverEditora.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverEditora.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverEditora.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverEditora.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverEditora.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaAlterarOuRemoverEditora.setRowHeight(25);
        TabelaAlterarOuRemoverEditora.updateUI();
    }

    public void ConsultaAlterarEditora() {
        Editora editora = new Editora();
        String dados[][] = new String[ListaBuscaEditora.size()][2];
        int i = 0;
        for (Editora ed : ListaBuscaEditora) {
            dados[i][0] = String.valueOf(ed.getId());
            dados[i][1] = ed.getNome();

            i++;
        }
        String tituloColuna[] = {"id", "Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverEditora.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverEditora.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverEditora.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverEditora.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverEditora.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverEditora.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaAlterarOuRemoverEditora.setRowHeight(25);
        TabelaAlterarOuRemoverEditora.updateUI();
    }

    //------------------------------------------------------------------------------------
    public void atualizarConsultaEspecificacao() {

        Especificacao especificacao = new Especificacao();
        EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();

        try {
            ListaEspecificacao = especificacaoDAO.ListaEspecificacao();
            //ListaArea = areaDAO.ListaArea();
        } catch (SQLException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaEspecificacao.size()][2];
        int i = 0;
        for (Especificacao esp : ListaEspecificacao) {
            dados[i][0] = String.valueOf(esp.getId());
            dados[i][1] = esp.getNome();
            i++;
        }
        String tituloColuna[] = {"id", "Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverEspecificacao.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverEspecificacao.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverEspecificacao.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverEspecificacao.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverEspecificacao.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverEspecificacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaAlterarOuRemoverEspecificacao.setRowHeight(25);
        TabelaAlterarOuRemoverEspecificacao.updateUI();
    }

    public void ConsultaEspecificacaoAlterar() {
        Especificacao especificacao = new Especificacao();
        String dados[][] = new String[ListaBuscaEspecificacao.size()][2];
        int i = 0;
        for (Especificacao esp : ListaBuscaEspecificacao) {
            dados[i][0] = String.valueOf(esp.getId());
            dados[i][1] = esp.getNome();

            i++;
        }
        String tituloColuna[] = {"id", "Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverEspecificacao.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverEspecificacao.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverEspecificacao.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverEspecificacao.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverEspecificacao.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverEspecificacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaAlterarOuRemoverEspecificacao.setRowHeight(25);
        TabelaAlterarOuRemoverEspecificacao.updateUI();
    }

    //------------------------------------------------------------------------------------
    //TabelaConsultaTipo
    public void ConsultaAreaEspecificacao() {
        Area area = new Area();
        String dados[][] = new String[ListaBuscaArea.size()][1];
        int i = 0;
        for (Area ar : ListaBuscaArea) {
            dados[i][0] = ar.getNome();
            i++;
        }
        String tituloColuna[] = {"Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaConsultaTipo.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaConsultaTipo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaConsultaTipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaConsultaTipo.setRowHeight(25);
        TabelaConsultaTipo.updateUI();
    }

    public void ConsultaEspecificacaoArea() {
        Especificacao especificacao = new Especificacao();
        String dados[][] = new String[ListaBuscaEspecificacao.size()][1];
        int i = 0;
        for (Especificacao esp : ListaBuscaEspecificacao) {
            dados[i][0] = esp.getNome();

            i++;
        }
        String tituloColuna[] = {"Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaConsultaTipo.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaConsultaTipo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaConsultaTipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaConsultaTipo.setRowHeight(25);
        TabelaConsultaTipo.updateUI();
    }

    public void ConsultaEditora() {
        Editora editora = new Editora();
        String dados[][] = new String[ListaBuscaEditora.size()][1];
        int i = 0;
        for (Editora ed : ListaBuscaEditora) {
            dados[i][0] = ed.getNome();
            i++;
        }
        String tituloColuna[] = {"Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaConsultaTipo.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaConsultaTipo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaConsultaTipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaConsultaTipo.setRowHeight(25);
        TabelaConsultaTipo.updateUI();
    }

    //-----------------------------------------------------------------------------------
    /*public void atualizarConsultaRegistro() {

        Editora editora = new Editora();
        EditoraDAO editoraDAO = new EditoraDAO();
        try {
            ListaEditora = editoraDAO.ListaEditora();
        } catch (SQLException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados[][] = new String[ListaEditora.size()][2];
        int i = 0;
        for (Editora ed : ListaEditora) {
            dados[i][0] = String.valueOf(ed.getId());
            dados[i][1] = ed.getNome();
            i++;
        }
        String tituloColuna[] = {"id", "Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverArea.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaAlterarOuRemoverArea.setRowHeight(25);
        TabelaAlterarOuRemoverArea.updateUI();
    }

    public void ConsultaAreaAlterar() {
        Area area = new Area();
        String dados[][] = new String[ListaBuscaArea.size()][2];
        int i = 0;
        for (Area ar : ListaBuscaArea) {
            dados[i][0] = String.valueOf(ar.getId());
            dados[i][1] = ar.getNome();

            i++;
        }
        String tituloColuna[] = {"id", "Nome"};
        DefaultTableModel tabelaCliente = new DefaultTableModel();
        tabelaCliente.setDataVector(dados, tituloColuna);
        TabelaAlterarOuRemoverArea.setModel(new DefaultTableModel(dados, tituloColuna) {
            boolean[] canEdit = new boolean[]{
                false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(0).setPreferredWidth(20);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(1).setPreferredWidth(500);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverArea.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        TabelaAlterarOuRemoverArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TabelaAlterarOuRemoverArea.setRowHeight(25);
        TabelaAlterarOuRemoverArea.updateUI();
    }*/
    //------------------------------------------------------------------------------------
    public void ResetaTabelaConsultaTipo() {
        ((DefaultTableModel) TabelaConsultaTipo.getModel()).setRowCount(0);
    }

    public void ResetaTabelaAlterarOuRemoverArea() {
        ((DefaultTableModel) TabelaAlterarOuRemoverArea.getModel()).setRowCount(0);
    }

    public void ResetaTabelaAlterarOuRemoverEspecificacao() {
        ((DefaultTableModel) TabelaAlterarOuRemoverEspecificacao.getModel()).setRowCount(0);
    }
    
    public void ResetaTabelaAlterarOuRemoverEditora() {
        ((DefaultTableModel) TabelaAlterarOuRemoverEditora.getModel()).setRowCount(0);
    }
    
    public void ResetaTabelaConsultaRevista() {
        ((DefaultTableModel) TabelaConsultaRevista.getModel()).setRowCount(0);
    }

    public void ResetaTabelaAlterarOuRemoverRevista() {
        ((DefaultTableModel) TabelaAlterarOuRemoverRevista.getModel()).setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SideBoard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        BotaoFerramenta = new javax.swing.JLabel();
        BotaoRevista = new javax.swing.JLabel();
        BotaoRegistro = new javax.swing.JLabel();
        PaneMae = new javax.swing.JPanel();
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
        BotaoSalvarNovaRevista = new javax.swing.JButton();
        lblEspecificacao = new javax.swing.JLabel();
        BotaoAdicionarNovaRevista = new javax.swing.JButton();
        lblData2 = new javax.swing.JLabel();
        campoQuantidade = new javax.swing.JTextField();
        campoData = new javax.swing.JTextField();
        BotaoCancelarNovaRevista2 = new javax.swing.JButton();
        ComboBoxEspecificacaoNovaRevista = new javax.swing.JComboBox<>();
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
        BotaoLimpaConsultaRevista = new javax.swing.JButton();
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
        BotaoLimpaBuscaRevista = new javax.swing.JButton();
        BotaoLiberaCamposAlterarRevistas = new javax.swing.JButton();
        PaneFerramentas = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        CampoNovoTituloTipo = new javax.swing.JTextField();
        ComboBoxNovoTipo = new javax.swing.JComboBox<>();
        lblOrigem3 = new javax.swing.JLabel();
        BotaoAdicionarNovoTipo = new javax.swing.JButton();
        BotaoCancelaNovoTipo = new javax.swing.JButton();
        BotaoSalvarNovoTipo = new javax.swing.JButton();
        PaneGuiaConsulta1 = new javax.swing.JPanel();
        ConsultaNomeMenuTipo = new javax.swing.JTextField();
        lblTitulo6 = new javax.swing.JLabel();
        ConsultaTipoComboBox = new javax.swing.JComboBox<>();
        lblEspecificacao6 = new javax.swing.JLabel();
        BotaoConsultaArea = new javax.swing.JButton();
        BotaoNovaConsultaArea = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelaConsultaTipo = new javax.swing.JTable();
        BotaoLimpaConsulta = new javax.swing.JButton();
        PaneGuiaAlteraRevista1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        BuscaTituloMenuArea = new javax.swing.JTextField();
        lblTitulo4 = new javax.swing.JLabel();
        lblIDAlteraArea = new javax.swing.JLabel();
        AlteraIDMenuArea = new javax.swing.JTextField();
        BotaoBuscaAlterarOuRemoverArea = new javax.swing.JButton();
        BotaoAlterarArea = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        TabelaAlterarOuRemoverArea = new javax.swing.JTable();
        lblTitulo7 = new javax.swing.JLabel();
        AlteraNomeMenuArea = new javax.swing.JTextField();
        BotaoRemoverArea = new javax.swing.JButton();
        BotaoResetaPesquisaAlterarOuRemoverArea = new javax.swing.JButton();
        BotaoLimpaCamposAlterarArea = new javax.swing.JButton();
        BotaoLimpaAlterarOuRemoverArea = new javax.swing.JButton();
        BotaoLiberaCamposAlterarArea = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        PaneGuiaAlteraRevista2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        BuscaTituloMenuEspecificacao = new javax.swing.JTextField();
        lblTitulo8 = new javax.swing.JLabel();
        lblIDAlteraEsp = new javax.swing.JLabel();
        AlteraIDMenuEspecificacao = new javax.swing.JTextField();
        BotaoBuscaAlterarOuRemoverEsp = new javax.swing.JButton();
        BotaoAlterarEsp = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblTitulo9 = new javax.swing.JLabel();
        AlteraNomeMenuEspecificacao = new javax.swing.JTextField();
        BotaoRemoverEsp = new javax.swing.JButton();
        BotaoResetaPesquisaAlterarOuRemoverEsp = new javax.swing.JButton();
        BotaoLimpaCamposAlterarEsp = new javax.swing.JButton();
        BotaoLimpaAlterarOuRemoverEsp = new javax.swing.JButton();
        BotaoLiberarAcoesEsp = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        TabelaAlterarOuRemoverEspecificacao = new javax.swing.JTable();
        PaneGuiaAlteraRevista4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        BuscaTituloMenuEditora = new javax.swing.JTextField();
        lblTitulo13 = new javax.swing.JLabel();
        lblIDAlteraEditora = new javax.swing.JLabel();
        AlteraIDMenuEditora = new javax.swing.JTextField();
        BotaoBuscaAlterarOuRemoverEditora = new javax.swing.JButton();
        BotaoAlterarEditora = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lblTitulo16 = new javax.swing.JLabel();
        AlteraNomeMenuEditora = new javax.swing.JTextField();
        BotaoRemoverEditora = new javax.swing.JButton();
        BotaoResetaPesquisaAlterarOuRemoverEditora = new javax.swing.JButton();
        BotaoLimpaCamposAlterarEditora = new javax.swing.JButton();
        BotaoLimpaAlterarOuRemoverEditora = new javax.swing.JButton();
        BotaoLiberarAcoesEditora = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        TabelaAlterarOuRemoverEditora = new javax.swing.JTable();
        PaneRegistro = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        PaneGuiaNovaRevista1 = new javax.swing.JPanel();
        ComboBoxOrigemRegistro = new javax.swing.JComboBox<>();
        lblData3 = new javax.swing.JLabel();
        lblOrigem7 = new javax.swing.JLabel();
        lblTitulo10 = new javax.swing.JLabel();
        ComboBoxAreaRegistro = new javax.swing.JComboBox<>();
        campoTituloRegistro = new javax.swing.JTextField();
        BotaoSalvarNovaRevista1 = new javax.swing.JButton();
        lblEspecificacao1 = new javax.swing.JLabel();
        BotaoAdicionarNovaRevista1 = new javax.swing.JButton();
        campoDataRegistro = new javax.swing.JTextField();
        BotaoCancelarNovaRevista3 = new javax.swing.JButton();
        lblData6 = new javax.swing.JLabel();
        campoRegistroRegistro = new javax.swing.JTextField();
        campoLocalRegistro = new javax.swing.JTextField();
        lblData8 = new javax.swing.JLabel();
        lblEspecificacao9 = new javax.swing.JLabel();
        ComboBoxEditoraRegistro = new javax.swing.JComboBox<>();
        PaneGuiaConsulta2 = new javax.swing.JPanel();
        ConsultaTituloMenuRevistas1 = new javax.swing.JTextField();
        lblTitulo11 = new javax.swing.JLabel();
        lblOrigem9 = new javax.swing.JLabel();
        ConsultaAreaMenuRevistas1 = new javax.swing.JComboBox<>();
        ConsultaEspecificacaoMenuRevistas1 = new javax.swing.JComboBox<>();
        lblEspecificacao7 = new javax.swing.JLabel();
        BotaoBuscaConsultaRevista1 = new javax.swing.JButton();
        BotaoNovaBuscaConsultaRevista1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TabelaConsultaRevista1 = new javax.swing.JTable();
        BotaoLimpaConsultaRevista1 = new javax.swing.JButton();
        PaneGuiaAlteraRevista3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        BuscaTituloMenuRevistas1 = new javax.swing.JTextField();
        lblTitulo12 = new javax.swing.JLabel();
        BuscaEspecificacaoMenuRevistas1 = new javax.swing.JComboBox<>();
        lblOrigem10 = new javax.swing.JLabel();
        BuscaAreaMenuRevistas1 = new javax.swing.JComboBox<>();
        lblEspecificacao8 = new javax.swing.JLabel();
        BotaoBuscaAlterarOuRemoverRevista1 = new javax.swing.JButton();
        BotaoAlterarRevistas1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        TabelaAlterarOuRemoverRevista1 = new javax.swing.JTable();
        BotaoRemoverRevistas1 = new javax.swing.JButton();
        BotaoResetaPesquisaAlterarOuRemoverRevista1 = new javax.swing.JButton();
        BotaoLimpaCamposAlterarRevistas1 = new javax.swing.JButton();
        BotaoLimpaBuscaRevista1 = new javax.swing.JButton();
        BotaoLiberaCamposAlterarRevistas1 = new javax.swing.JButton();
        campoRegistroRegistro2 = new javax.swing.JTextField();
        lblTitulo15 = new javax.swing.JLabel();
        campoTituloRegistro2 = new javax.swing.JTextField();
        lblData12 = new javax.swing.JLabel();
        campoLocalRegistro2 = new javax.swing.JTextField();
        lblData14 = new javax.swing.JLabel();
        campoDataRegistro2 = new javax.swing.JTextField();
        lblData15 = new javax.swing.JLabel();
        ComboBoxAreaRegistro2 = new javax.swing.JComboBox<>();
        lblEspecificacao12 = new javax.swing.JLabel();
        ComboBoxOrigemRegistro2 = new javax.swing.JComboBox<>();
        lblOrigem13 = new javax.swing.JLabel();
        ComboBoxEditoraRegistro2 = new javax.swing.JComboBox<>();
        lblEspecificacao11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PaneGuiaNovaRevista2 = new javax.swing.JPanel();
        ComboBoxEditoraRegistro1 = new javax.swing.JComboBox<>();
        lblEspecificacao10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UEMG Frutal - Revistas");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);

        SideBoard.setBackground(new java.awt.Color(37, 103, 125));
        SideBoard.setForeground(new java.awt.Color(37, 103, 125));
        SideBoard.setMaximumSize(new java.awt.Dimension(300, 400));

        jLabel1.setFont(new java.awt.Font("Utsaah", 0, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("UEMG Frutal");

        BotaoFerramenta.setBackground(new java.awt.Color(37, 103, 125));
        BotaoFerramenta.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoFerramenta.setForeground(new java.awt.Color(255, 255, 255));
        BotaoFerramenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoFerramenta.setText("Ferramentas");
        BotaoFerramenta.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoFerramenta.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoFerramenta.setOpaque(true);
        BotaoFerramenta.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoFerramenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoFerramentaMouseClicked(evt);
            }
        });

        BotaoRevista.setBackground(new java.awt.Color(37, 103, 125));
        BotaoRevista.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoRevista.setForeground(new java.awt.Color(255, 255, 255));
        BotaoRevista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoRevista.setText("Revista");
        BotaoRevista.setOpaque(true);
        BotaoRevista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoRevistaMouseClicked(evt);
            }
        });

        BotaoRegistro.setBackground(new java.awt.Color(37, 103, 125));
        BotaoRegistro.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        BotaoRegistro.setForeground(new java.awt.Color(255, 255, 255));
        BotaoRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotaoRegistro.setText("Registro");
        BotaoRegistro.setMaximumSize(new java.awt.Dimension(139, 25));
        BotaoRegistro.setMinimumSize(new java.awt.Dimension(139, 25));
        BotaoRegistro.setOpaque(true);
        BotaoRegistro.setPreferredSize(new java.awt.Dimension(139, 25));
        BotaoRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotaoRegistroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout SideBoardLayout = new javax.swing.GroupLayout(SideBoard);
        SideBoard.setLayout(SideBoardLayout);
        SideBoardLayout.setHorizontalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BotaoRevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotaoFerramenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(BotaoRegistro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SideBoardLayout.setVerticalGroup(
            SideBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotaoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneMae.setBackground(new java.awt.Color(255, 255, 255));
        PaneMae.setLayout(new java.awt.CardLayout());

        PaneRevista.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setDoubleBuffered(false);
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(44, 106, 129));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Revista acadêmica");

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
                .addGap(35, 35, 35)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(22, 22, 22))
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
        PaneGuiaNovaRevista.setMinimumSize(new java.awt.Dimension(1097, 681));

        ComboBoxOrigem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxOrigem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doação", "Aquisição" }));
        ComboBoxOrigem.setBorder(null);
        ComboBoxOrigem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboBoxOrigem.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
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
        lblTitulo.setText("Título da revista");

        lblOrigem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem1.setText("Especificação");

        ComboBoxAreaNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxAreaNovaRevista.setToolTipText("");
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
        campoTitulo.setDocument(new JTextFieldLimit(40, true));
        campoTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoTituloMouseClicked(evt);
            }
        });
        campoTitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoTituloKeyPressed(evt);
            }
        });

        BotaoSalvarNovaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoSalvarNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoSalvarNovaRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Save Close_24px.png"))); // NOI18N
        BotaoSalvarNovaRevista.setText("Salvar");
        BotaoSalvarNovaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarNovaRevistaActionPerformed(evt);
            }
        });

        lblEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao.setText("Área");

        BotaoAdicionarNovaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoAdicionarNovaRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Add Book_24px.png"))); // NOI18N
        BotaoAdicionarNovaRevista.setText("Nova revista");
        BotaoAdicionarNovaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAdicionarNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAdicionarNovaRevistaActionPerformed(evt);
            }
        });

        lblData2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData2.setText("Quantidade inicial");

        campoQuantidade.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        campoQuantidade.setDocument(new JTextFieldLimit(10, false, true));
        campoQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoQuantidadeActionPerformed(evt);
            }
        });

        campoData.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        campoData.setPreferredSize(new java.awt.Dimension(35, 26));
        campoData.setDocument(new JTextFieldLimit(10, false, true));
        campoData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataActionPerformed(evt);
            }
        });

        BotaoCancelarNovaRevista2.setBackground(new java.awt.Color(255, 255, 255));
        BotaoCancelarNovaRevista2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoCancelarNovaRevista2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Remove Book_24px.png"))); // NOI18N
        BotaoCancelarNovaRevista2.setText("Cancelar");
        BotaoCancelarNovaRevista2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelarNovaRevista2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarNovaRevista2ActionPerformed(evt);
            }
        });

        ComboBoxEspecificacaoNovaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEspecificacaoNovaRevista.setToolTipText("");
        ComboBoxEspecificacaoNovaRevista.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ComboBoxEspecificacaoNovaRevista.setDoubleBuffered(true);
        ComboBoxEspecificacaoNovaRevista.setEditor(null);
        ComboBoxEspecificacaoNovaRevista.setFocusable(false);
        ComboBoxEspecificacaoNovaRevista.setMaximumSize(new java.awt.Dimension(35, 26));
        ComboBoxEspecificacaoNovaRevista.setName("Selecione"); // NOI18N
        ComboBoxEspecificacaoNovaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEspecificacaoNovaRevistaActionPerformed(evt);
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
                        .addComponent(BotaoAdicionarNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoCancelarNovaRevista2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotaoSalvarNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)
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
                .addContainerGap(142, Short.MAX_VALUE))
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
                    .addComponent(ComboBoxAreaNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboBoxEspecificacaoNovaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblOrigem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(PaneGuiaNovaRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoSalvarNovaRevista)
                    .addComponent(BotaoAdicionarNovaRevista)
                    .addComponent(BotaoCancelarNovaRevista2))
                .addContainerGap(318, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastrar", PaneGuiaNovaRevista);

        PaneGuiaConsulta.setBackground(new java.awt.Color(255, 255, 255));

        ConsultaTituloMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaTituloMenuRevistas.setMaximumSize(new java.awt.Dimension(25, 25));
        ConsultaTituloMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaTituloMenuRevistas.setDocument(new JTextFieldLimit(40, true));

        lblTitulo5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo5.setText("Título da revista");

        lblOrigem6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem6.setText("Área");

        ConsultaAreaMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaAreaMenuRevistas.setToolTipText("");
        ConsultaAreaMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ConsultaAreaMenuRevistas.setFocusable(false);
        ConsultaAreaMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaAreaMenuRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaAreaMenuRevistasActionPerformed(evt);
            }
        });

        ConsultaEspecificacaoMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaEspecificacaoMenuRevistas.setToolTipText("");
        ConsultaEspecificacaoMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
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

        TabelaConsultaRevista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        BotaoLimpaConsultaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaConsultaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaConsultaRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoLimpaConsultaRevista.setText("Limpa busca");
        BotaoLimpaConsultaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaConsultaRevista.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaConsultaRevista.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaConsultaRevista.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoLimpaConsultaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaConsultaRevistaActionPerformed(evt);
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
                        .addComponent(BotaoLimpaConsultaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(ConsultaAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addContainerGap(94, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
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
                    .addComponent(BotaoLimpaConsultaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoNovaBuscaConsultaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar", PaneGuiaConsulta);

        PaneGuiaAlteraRevista.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaAlteraRevista.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel4.setText("Propriedades da revista");
        jLabel4.setPreferredSize(new java.awt.Dimension(407, 41));

        lblData1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData1.setText("Ano");

        BuscaTituloMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaTituloMenuRevistas.setMaximumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuRevistas.setDocument(new JTextFieldLimit(40, true));

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo2.setText("Título da revista");

        BuscaEspecificacaoMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaEspecificacaoMenuRevistas.setToolTipText("");
        BuscaEspecificacaoMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
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
        BuscaAreaMenuRevistas.setToolTipText("");
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
        BotaoAlterarRevistas.setToolTipText("");
        BotaoAlterarRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarRevistasActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel5.setText("Escolha uma revista");

        jSeparator2.setForeground(new java.awt.Color(37, 103, 125));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        TabelaAlterarOuRemoverRevista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        lblTitulo3.setText("Título da revista");

        AlteraTituloMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraTituloMenuRevistas.setMaximumSize(new java.awt.Dimension(25, 25));
        AlteraTituloMenuRevistas.setMinimumSize(new java.awt.Dimension(25, 25));

        lblOrigem5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem5.setText("Especificação");

        AlteraAreaMenuRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraAreaMenuRevistas.setToolTipText("");
        AlteraAreaMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
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
        AlteraEspecificacaoMenuRevistas.setToolTipText("");
        AlteraEspecificacaoMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
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
        AlteraOrigemMenuRevistas.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
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
        AlteraAnoMenuRevistas.setDocument(new JTextFieldLimit(10, false, true));

        BotaoLimpaBuscaRevista.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaBuscaRevista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaBuscaRevista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoLimpaBuscaRevista.setText("Limpa busca");
        BotaoLimpaBuscaRevista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaBuscaRevista.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaBuscaRevista.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaBuscaRevista.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoLimpaBuscaRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaBuscaRevistaActionPerformed(evt);
            }
        });

        BotaoLiberaCamposAlterarRevistas.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLiberaCamposAlterarRevistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLiberaCamposAlterarRevistas.setText("Ativar alterações");
        BotaoLiberaCamposAlterarRevistas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLiberaCamposAlterarRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLiberaCamposAlterarRevistasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaAlteraRevistaLayout = new javax.swing.GroupLayout(PaneGuiaAlteraRevista);
        PaneGuiaAlteraRevista.setLayout(PaneGuiaAlteraRevistaLayout);
        PaneGuiaAlteraRevistaLayout.setHorizontalGroup(
            PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addComponent(BotaoResetaPesquisaAlterarOuRemoverRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotaoBuscaAlterarOuRemoverRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BotaoLimpaBuscaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                                .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrigem4)
                                    .addComponent(BuscaAreaMenuRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
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
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)))
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
                                .addComponent(BotaoLiberaCamposAlterarRevistas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(BotaoLimpaBuscaRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(BotaoLiberaCamposAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoLimpaCamposAlterarRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoRemoverRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoAlterarRevistas))
                        .addGap(46, 46, 46)
                        .addGroup(PaneGuiaAlteraRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IDTravadoAlteraRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDAlteraRevista))))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneRevistaLayout = new javax.swing.GroupLayout(PaneRevista);
        PaneRevista.setLayout(PaneRevistaLayout);
        PaneRevistaLayout.setHorizontalGroup(
            PaneRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneRevistaLayout.setVerticalGroup(
            PaneRevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PaneMae.add(PaneRevista, "card3");

        PaneFerramentas.setBackground(new java.awt.Color(255, 255, 255));
        PaneFerramentas.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(44, 106, 129));
        jPanel2.setPreferredSize(new java.awt.Dimension(627, 134));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ferramentas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
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

        lblTitulo1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo1.setText("Nome do título:");

        CampoNovoTituloTipo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CampoNovoTituloTipo.setMaximumSize(new java.awt.Dimension(25, 25));
        CampoNovoTituloTipo.setMinimumSize(new java.awt.Dimension(25, 25));
        CampoNovoTituloTipo.setDocument(new JTextFieldLimit(100, true));

        ComboBoxNovoTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxNovoTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Área", "Especificação", "Editora" }));
        ComboBoxNovoTipo.setToolTipText("Selecione");
        ComboBoxNovoTipo.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ComboBoxNovoTipo.setDoubleBuffered(true);
        ComboBoxNovoTipo.setFocusable(false);
        ComboBoxNovoTipo.setMaximumSize(new java.awt.Dimension(35, 26));
        ComboBoxNovoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxNovoTipoActionPerformed(evt);
            }
        });

        lblOrigem3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem3.setText("Tipo:");

        BotaoAdicionarNovoTipo.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarNovoTipo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoAdicionarNovoTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Add Book_24px.png"))); // NOI18N
        BotaoAdicionarNovoTipo.setText("Adicionar");
        BotaoAdicionarNovoTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAdicionarNovoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAdicionarNovoTipoActionPerformed(evt);
            }
        });

        BotaoCancelaNovoTipo.setBackground(new java.awt.Color(255, 255, 255));
        BotaoCancelaNovoTipo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoCancelaNovoTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Remove Book_24px.png"))); // NOI18N
        BotaoCancelaNovoTipo.setText("Cancelar");
        BotaoCancelaNovoTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelaNovoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelaNovoTipoActionPerformed(evt);
            }
        });

        BotaoSalvarNovoTipo.setBackground(new java.awt.Color(255, 255, 255));
        BotaoSalvarNovoTipo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoSalvarNovoTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Save Close_24px.png"))); // NOI18N
        BotaoSalvarNovoTipo.setText("Salvar");
        BotaoSalvarNovoTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarNovoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarNovoTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(BotaoAdicionarNovoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209)
                        .addComponent(BotaoCancelaNovoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotaoSalvarNovoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblTitulo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 546, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(CampoNovoTituloTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(48, 48, 48)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrigem3)
                            .addComponent(ComboBoxNovoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(128, 128, 128))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblTitulo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CampoNovoTituloTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblOrigem3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxNovoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(97, 97, 97)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoSalvarNovoTipo)
                    .addComponent(BotaoAdicionarNovoTipo)
                    .addComponent(BotaoCancelaNovoTipo))
                .addContainerGap(422, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Cadastrar", jPanel7);

        PaneGuiaConsulta1.setBackground(new java.awt.Color(255, 255, 255));

        ConsultaNomeMenuTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaNomeMenuTipo.setMaximumSize(new java.awt.Dimension(25, 25));
        ConsultaNomeMenuTipo.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaNomeMenuTipo.setDocument(new JTextFieldLimit(40, true));

        lblTitulo6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo6.setText("Título");

        ConsultaTipoComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaTipoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Área", "Especificação", "Editora" }));
        ConsultaTipoComboBox.setToolTipText("");
        ConsultaTipoComboBox.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ConsultaTipoComboBox.setDoubleBuffered(true);
        ConsultaTipoComboBox.setEditor(null);
        ConsultaTipoComboBox.setFocusable(false);
        ConsultaTipoComboBox.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaTipoComboBox.setName("Selecione"); // NOI18N
        ConsultaTipoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaTipoComboBoxActionPerformed(evt);
            }
        });

        lblEspecificacao6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao6.setText("Tipo");

        BotaoConsultaArea.setBackground(new java.awt.Color(255, 255, 255));
        BotaoConsultaArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoConsultaArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_24px.png"))); // NOI18N
        BotaoConsultaArea.setText("Busca");
        BotaoConsultaArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoConsultaArea.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoConsultaArea.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoConsultaArea.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoConsultaArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoConsultaAreaActionPerformed(evt);
            }
        });

        BotaoNovaConsultaArea.setBackground(new java.awt.Color(255, 255, 255));
        BotaoNovaConsultaArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoNovaConsultaArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Zoom In_24px.png"))); // NOI18N
        BotaoNovaConsultaArea.setText("Nova pesquisa");
        BotaoNovaConsultaArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoNovaConsultaArea.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoNovaConsultaArea.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoNovaConsultaArea.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoNovaConsultaArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoNovaConsultaAreaActionPerformed(evt);
            }
        });

        TabelaConsultaTipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TabelaConsultaTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome"
            }
        ));
        TabelaConsultaTipo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabelaConsultaTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaConsultaTipoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TabelaConsultaTipo);

        BotaoLimpaConsulta.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoLimpaConsulta.setText("Limpa busca");
        BotaoLimpaConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaConsulta.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaConsulta.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaConsulta.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoLimpaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaConsulta1Layout = new javax.swing.GroupLayout(PaneGuiaConsulta1);
        PaneGuiaConsulta1.setLayout(PaneGuiaConsulta1Layout);
        PaneGuiaConsulta1Layout.setHorizontalGroup(
            PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaConsulta1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaConsulta1Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                        .addComponent(BotaoNovaConsultaArea, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoConsultaArea, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoLimpaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PaneGuiaConsulta1Layout.createSequentialGroup()
                        .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo6)
                            .addComponent(ConsultaNomeMenuTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ConsultaTipoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEspecificacao6))))
                .addGap(78, 78, 78))
        );
        PaneGuiaConsulta1Layout.setVerticalGroup(
            PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                        .addComponent(lblTitulo6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConsultaNomeMenuTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaConsulta1Layout.createSequentialGroup()
                        .addComponent(lblEspecificacao6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConsultaTipoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(PaneGuiaConsulta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoConsultaArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoLimpaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoNovaConsultaArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Consultar", PaneGuiaConsulta1);

        PaneGuiaAlteraRevista1.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaAlteraRevista1.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel7.setText("Propriedades");
        jLabel7.setPreferredSize(new java.awt.Dimension(407, 41));

        BuscaTituloMenuArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaTituloMenuArea.setMaximumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuArea.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuArea.setDocument(new JTextFieldLimit(40, true));

        lblTitulo4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo4.setText("Título da área");

        lblIDAlteraArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIDAlteraArea.setText("ID:");

        AlteraIDMenuArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AlteraIDMenuArea.setEnabled(false);

        BotaoBuscaAlterarOuRemoverArea.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaAlterarOuRemoverArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaAlterarOuRemoverArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_24px.png"))); // NOI18N
        BotaoBuscaAlterarOuRemoverArea.setText("Busca");
        BotaoBuscaAlterarOuRemoverArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaAlterarOuRemoverArea.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverArea.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverArea.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaAlterarOuRemoverAreaActionPerformed(evt);
            }
        });

        BotaoAlterarArea.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAlterarArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoAlterarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Save as_24px.png"))); // NOI18N
        BotaoAlterarArea.setText("Alterar");
        BotaoAlterarArea.setToolTipText("");
        BotaoAlterarArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarAreaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel8.setText("Escolha uma área");

        jSeparator3.setForeground(new java.awt.Color(37, 103, 125));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        TabelaAlterarOuRemoverArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TabelaAlterarOuRemoverArea.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelaAlterarOuRemoverArea.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabelaAlterarOuRemoverArea.setSurrendersFocusOnKeystroke(true);
        TabelaAlterarOuRemoverArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaAlterarOuRemoverAreaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TabelaAlterarOuRemoverArea);

        lblTitulo7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo7.setText("Título da área");

        AlteraNomeMenuArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraNomeMenuArea.setMaximumSize(new java.awt.Dimension(25, 25));
        AlteraNomeMenuArea.setMinimumSize(new java.awt.Dimension(25, 25));
        AlteraNomeMenuArea.setDocument(new JTextFieldLimit(40, true));

        BotaoRemoverArea.setBackground(new java.awt.Color(255, 255, 255));
        BotaoRemoverArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoRemoverArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Trash_24px.png"))); // NOI18N
        BotaoRemoverArea.setText("Remover");
        BotaoRemoverArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoRemoverArea.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverArea.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverArea.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoRemoverArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverAreaActionPerformed(evt);
            }
        });

        BotaoResetaPesquisaAlterarOuRemoverArea.setBackground(new java.awt.Color(255, 255, 255));
        BotaoResetaPesquisaAlterarOuRemoverArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Zoom In_24px.png"))); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverArea.setText("Nova busca");
        BotaoResetaPesquisaAlterarOuRemoverArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoResetaPesquisaAlterarOuRemoverArea.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverArea.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverArea.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoResetaPesquisaAlterarOuRemoverAreaActionPerformed(evt);
            }
        });

        BotaoLimpaCamposAlterarArea.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaCamposAlterarArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaCamposAlterarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Broom_24px.png"))); // NOI18N
        BotaoLimpaCamposAlterarArea.setText("Limpar");
        BotaoLimpaCamposAlterarArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaCamposAlterarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaCamposAlterarAreaActionPerformed(evt);
            }
        });

        BotaoLimpaAlterarOuRemoverArea.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaAlterarOuRemoverArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaAlterarOuRemoverArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoLimpaAlterarOuRemoverArea.setText("Limpa");
        BotaoLimpaAlterarOuRemoverArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaAlterarOuRemoverArea.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaAlterarOuRemoverArea.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaAlterarOuRemoverArea.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoLimpaAlterarOuRemoverArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaAlterarOuRemoverAreaActionPerformed(evt);
            }
        });

        BotaoLiberaCamposAlterarArea.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLiberaCamposAlterarArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLiberaCamposAlterarArea.setText("Ativar alterações");
        BotaoLiberaCamposAlterarArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLiberaCamposAlterarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLiberaCamposAlterarAreaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaAlteraRevista1Layout = new javax.swing.GroupLayout(PaneGuiaAlteraRevista1);
        PaneGuiaAlteraRevista1.setLayout(PaneGuiaAlteraRevista1Layout);
        PaneGuiaAlteraRevista1Layout.setHorizontalGroup(
            PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                        .addComponent(BotaoResetaPesquisaAlterarOuRemoverArea, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(BotaoBuscaAlterarOuRemoverArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoLimpaAlterarOuRemoverArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BuscaTituloMenuArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo7)
                    .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                                .addComponent(lblIDAlteraArea)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlteraIDMenuArea, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(BotaoLiberaCamposAlterarArea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotaoLimpaCamposAlterarArea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoRemoverArea, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoAlterarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AlteraNomeMenuArea, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                        .addComponent(BuscaTituloMenuArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoBuscaAlterarOuRemoverArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoResetaPesquisaAlterarOuRemoverArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoLimpaAlterarOuRemoverArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaAlteraRevista1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTitulo7)
                        .addGap(4, 4, 4)
                        .addComponent(AlteraNomeMenuArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(BotaoLiberaCamposAlterarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoLimpaCamposAlterarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoRemoverArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoAlterarArea))
                        .addGap(85, 85, 85)
                        .addGroup(PaneGuiaAlteraRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AlteraIDMenuArea, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDAlteraArea))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Gerenciar área", PaneGuiaAlteraRevista1);

        PaneGuiaAlteraRevista2.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaAlteraRevista2.setToolTipText("");
        PaneGuiaAlteraRevista2.setPreferredSize(new java.awt.Dimension(1097, 714));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel9.setText("Propriedades");
        jLabel9.setPreferredSize(new java.awt.Dimension(407, 41));

        BuscaTituloMenuEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaTituloMenuEspecificacao.setMaximumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuEspecificacao.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuEspecificacao.setDocument(new JTextFieldLimit(40, true));

        lblTitulo8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo8.setText("Título da especificação");

        lblIDAlteraEsp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIDAlteraEsp.setText("ID:");

        AlteraIDMenuEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AlteraIDMenuEspecificacao.setEnabled(false);

        BotaoBuscaAlterarOuRemoverEsp.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaAlterarOuRemoverEsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaAlterarOuRemoverEsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_24px.png"))); // NOI18N
        BotaoBuscaAlterarOuRemoverEsp.setText("Busca");
        BotaoBuscaAlterarOuRemoverEsp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaAlterarOuRemoverEsp.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverEsp.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverEsp.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaAlterarOuRemoverEspActionPerformed(evt);
            }
        });

        BotaoAlterarEsp.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAlterarEsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoAlterarEsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Save as_24px.png"))); // NOI18N
        BotaoAlterarEsp.setText("Alterar");
        BotaoAlterarEsp.setToolTipText("");
        BotaoAlterarEsp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarEspActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel10.setText("Escolha uma especificação");

        jSeparator4.setForeground(new java.awt.Color(37, 103, 125));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblTitulo9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo9.setText("Título da especificação");

        AlteraNomeMenuEspecificacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraNomeMenuEspecificacao.setMaximumSize(new java.awt.Dimension(25, 25));
        AlteraNomeMenuEspecificacao.setMinimumSize(new java.awt.Dimension(25, 25));
        AlteraNomeMenuEspecificacao.setDocument(new JTextFieldLimit(40, true));

        BotaoRemoverEsp.setBackground(new java.awt.Color(255, 255, 255));
        BotaoRemoverEsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoRemoverEsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Trash_24px.png"))); // NOI18N
        BotaoRemoverEsp.setText("Remover");
        BotaoRemoverEsp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoRemoverEsp.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverEsp.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverEsp.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoRemoverEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverEspActionPerformed(evt);
            }
        });

        BotaoResetaPesquisaAlterarOuRemoverEsp.setBackground(new java.awt.Color(255, 255, 255));
        BotaoResetaPesquisaAlterarOuRemoverEsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverEsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Zoom In_24px.png"))); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverEsp.setText("Nova busca");
        BotaoResetaPesquisaAlterarOuRemoverEsp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoResetaPesquisaAlterarOuRemoverEsp.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverEsp.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverEsp.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoResetaPesquisaAlterarOuRemoverEspActionPerformed(evt);
            }
        });

        BotaoLimpaCamposAlterarEsp.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaCamposAlterarEsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaCamposAlterarEsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Broom_24px.png"))); // NOI18N
        BotaoLimpaCamposAlterarEsp.setText("Limpar");
        BotaoLimpaCamposAlterarEsp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaCamposAlterarEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaCamposAlterarEspActionPerformed(evt);
            }
        });

        BotaoLimpaAlterarOuRemoverEsp.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaAlterarOuRemoverEsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaAlterarOuRemoverEsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoLimpaAlterarOuRemoverEsp.setText("Limpa");
        BotaoLimpaAlterarOuRemoverEsp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaAlterarOuRemoverEsp.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaAlterarOuRemoverEsp.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaAlterarOuRemoverEsp.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoLimpaAlterarOuRemoverEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaAlterarOuRemoverEspActionPerformed(evt);
            }
        });

        BotaoLiberarAcoesEsp.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLiberarAcoesEsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLiberarAcoesEsp.setText("Ativar alterações");
        BotaoLiberarAcoesEsp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLiberarAcoesEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLiberarAcoesEspActionPerformed(evt);
            }
        });

        TabelaAlterarOuRemoverEspecificacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TabelaAlterarOuRemoverEspecificacao.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelaAlterarOuRemoverEspecificacao.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabelaAlterarOuRemoverEspecificacao.setSurrendersFocusOnKeystroke(true);
        TabelaAlterarOuRemoverEspecificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaAlterarOuRemoverEspecificacaoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TabelaAlterarOuRemoverEspecificacao);

        javax.swing.GroupLayout PaneGuiaAlteraRevista2Layout = new javax.swing.GroupLayout(PaneGuiaAlteraRevista2);
        PaneGuiaAlteraRevista2.setLayout(PaneGuiaAlteraRevista2Layout);
        PaneGuiaAlteraRevista2Layout.setHorizontalGroup(
            PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevista2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaAlteraRevista2Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(lblTitulo8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PaneGuiaAlteraRevista2Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevista2Layout.createSequentialGroup()
                                .addComponent(BotaoResetaPesquisaAlterarOuRemoverEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addComponent(BotaoBuscaAlterarOuRemoverEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoLimpaAlterarOuRemoverEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6)
                            .addComponent(BuscaTituloMenuEspecificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo9)
                    .addGroup(PaneGuiaAlteraRevista2Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PaneGuiaAlteraRevista2Layout.createSequentialGroup()
                                .addComponent(lblIDAlteraEsp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlteraIDMenuEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(BotaoLiberarAcoesEsp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotaoLimpaCamposAlterarEsp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoRemoverEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoAlterarEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AlteraNomeMenuEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PaneGuiaAlteraRevista2Layout.setVerticalGroup(
            PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevista2Layout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaAlteraRevista2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscaTituloMenuEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoBuscaAlterarOuRemoverEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoResetaPesquisaAlterarOuRemoverEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoLimpaAlterarOuRemoverEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaAlteraRevista2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTitulo9)
                        .addGap(4, 4, 4)
                        .addComponent(AlteraNomeMenuEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(BotaoLiberarAcoesEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoLimpaCamposAlterarEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoRemoverEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoAlterarEsp))
                        .addGap(85, 85, 85)
                        .addGroup(PaneGuiaAlteraRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AlteraIDMenuEspecificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDAlteraEsp))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaneGuiaAlteraRevista2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(PaneGuiaAlteraRevista2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PaneGuiaAlteraRevista2.getAccessibleContext().setAccessibleName("Gerenciar Especificacao");

        jTabbedPane2.addTab("Gerenciar especificação", jPanel8);

        PaneGuiaAlteraRevista4.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaAlteraRevista4.setToolTipText("");
        PaneGuiaAlteraRevista4.setPreferredSize(new java.awt.Dimension(1097, 714));

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel13.setText("Propriedades");
        jLabel13.setPreferredSize(new java.awt.Dimension(407, 41));

        BuscaTituloMenuEditora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaTituloMenuEditora.setMaximumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuEditora.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuEspecificacao.setDocument(new JTextFieldLimit(40, true));

        lblTitulo13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo13.setText("Título da editora");

        lblIDAlteraEditora.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIDAlteraEditora.setText("ID:");

        AlteraIDMenuEditora.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AlteraIDMenuEditora.setEnabled(false);

        BotaoBuscaAlterarOuRemoverEditora.setBackground(new java.awt.Color(255, 255, 255));
        BotaoBuscaAlterarOuRemoverEditora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoBuscaAlterarOuRemoverEditora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Search_24px.png"))); // NOI18N
        BotaoBuscaAlterarOuRemoverEditora.setText("Busca");
        BotaoBuscaAlterarOuRemoverEditora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoBuscaAlterarOuRemoverEditora.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverEditora.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverEditora.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoBuscaAlterarOuRemoverEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoBuscaAlterarOuRemoverEditoraActionPerformed(evt);
            }
        });

        BotaoAlterarEditora.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAlterarEditora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoAlterarEditora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Save as_24px.png"))); // NOI18N
        BotaoAlterarEditora.setText("Alterar");
        BotaoAlterarEditora.setToolTipText("");
        BotaoAlterarEditora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarEditoraActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel14.setText("Escolha um editora");

        jSeparator6.setForeground(new java.awt.Color(37, 103, 125));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblTitulo16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo16.setText("Título da editora");

        AlteraNomeMenuEditora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AlteraNomeMenuEditora.setMaximumSize(new java.awt.Dimension(25, 25));
        AlteraNomeMenuEditora.setMinimumSize(new java.awt.Dimension(25, 25));
        AlteraNomeMenuEspecificacao.setDocument(new JTextFieldLimit(40, true));

        BotaoRemoverEditora.setBackground(new java.awt.Color(255, 255, 255));
        BotaoRemoverEditora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoRemoverEditora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Trash_24px.png"))); // NOI18N
        BotaoRemoverEditora.setText("Remover");
        BotaoRemoverEditora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoRemoverEditora.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverEditora.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoRemoverEditora.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoRemoverEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoRemoverEditoraActionPerformed(evt);
            }
        });

        BotaoResetaPesquisaAlterarOuRemoverEditora.setBackground(new java.awt.Color(255, 255, 255));
        BotaoResetaPesquisaAlterarOuRemoverEditora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverEditora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/Zoom In_24px.png"))); // NOI18N
        BotaoResetaPesquisaAlterarOuRemoverEditora.setText("Nova busca");
        BotaoResetaPesquisaAlterarOuRemoverEditora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoResetaPesquisaAlterarOuRemoverEditora.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverEditora.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverEditora.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoResetaPesquisaAlterarOuRemoverEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoResetaPesquisaAlterarOuRemoverEditoraActionPerformed(evt);
            }
        });

        BotaoLimpaCamposAlterarEditora.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaCamposAlterarEditora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaCamposAlterarEditora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Broom_24px.png"))); // NOI18N
        BotaoLimpaCamposAlterarEditora.setText("Limpar");
        BotaoLimpaCamposAlterarEditora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaCamposAlterarEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaCamposAlterarEditoraActionPerformed(evt);
            }
        });

        BotaoLimpaAlterarOuRemoverEditora.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaAlterarOuRemoverEditora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaAlterarOuRemoverEditora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoLimpaAlterarOuRemoverEditora.setText("Limpa");
        BotaoLimpaAlterarOuRemoverEditora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaAlterarOuRemoverEditora.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaAlterarOuRemoverEditora.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaAlterarOuRemoverEditora.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoLimpaAlterarOuRemoverEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaAlterarOuRemoverEditoraActionPerformed(evt);
            }
        });

        BotaoLiberarAcoesEditora.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLiberarAcoesEditora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLiberarAcoesEditora.setText("Ativar alterações");
        BotaoLiberarAcoesEditora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLiberarAcoesEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLiberarAcoesEditoraActionPerformed(evt);
            }
        });

        TabelaAlterarOuRemoverEditora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TabelaAlterarOuRemoverEditora.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelaAlterarOuRemoverEditora.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TabelaAlterarOuRemoverEditora.setSurrendersFocusOnKeystroke(true);
        TabelaAlterarOuRemoverEditora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaAlterarOuRemoverEditoraMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(TabelaAlterarOuRemoverEditora);

        javax.swing.GroupLayout PaneGuiaAlteraRevista4Layout = new javax.swing.GroupLayout(PaneGuiaAlteraRevista4);
        PaneGuiaAlteraRevista4.setLayout(PaneGuiaAlteraRevista4Layout);
        PaneGuiaAlteraRevista4Layout.setHorizontalGroup(
            PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevista4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaAlteraRevista4Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(lblTitulo13))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PaneGuiaAlteraRevista4Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevista4Layout.createSequentialGroup()
                                .addComponent(BotaoResetaPesquisaAlterarOuRemoverEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addComponent(BotaoBuscaAlterarOuRemoverEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoLimpaAlterarOuRemoverEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane8)
                            .addComponent(BuscaTituloMenuEditora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo16)
                    .addGroup(PaneGuiaAlteraRevista4Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PaneGuiaAlteraRevista4Layout.createSequentialGroup()
                                .addComponent(lblIDAlteraEditora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlteraIDMenuEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(BotaoLiberarAcoesEditora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotaoLimpaCamposAlterarEditora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoRemoverEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoAlterarEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AlteraNomeMenuEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PaneGuiaAlteraRevista4Layout.setVerticalGroup(
            PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevista4Layout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaAlteraRevista4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscaTituloMenuEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoBuscaAlterarOuRemoverEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoResetaPesquisaAlterarOuRemoverEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoLimpaAlterarOuRemoverEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaAlteraRevista4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTitulo16)
                        .addGap(4, 4, 4)
                        .addComponent(AlteraNomeMenuEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(BotaoLiberarAcoesEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoLimpaCamposAlterarEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoRemoverEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoAlterarEditora))
                        .addGap(85, 85, 85)
                        .addGroup(PaneGuiaAlteraRevista4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AlteraIDMenuEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDAlteraEditora))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Gerenciar editora", PaneGuiaAlteraRevista4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
            .addComponent(jTabbedPane2)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 726, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaneFerramentasLayout = new javax.swing.GroupLayout(PaneFerramentas);
        PaneFerramentas.setLayout(PaneFerramentasLayout);
        PaneFerramentasLayout.setHorizontalGroup(
            PaneFerramentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PaneFerramentasLayout.setVerticalGroup(
            PaneFerramentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PaneMae.add(PaneFerramentas, "card2");

        jPanel4.setBackground(new java.awt.Color(44, 106, 129));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Registro");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane3.setAutoscrolls(true);
        jTabbedPane3.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane3.setDoubleBuffered(true);
        jTabbedPane3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        PaneGuiaNovaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaNovaRevista1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PaneGuiaNovaRevista1.setMinimumSize(new java.awt.Dimension(1097, 681));

        ComboBoxOrigemRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxOrigemRegistro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doação", "Aquisição" }));
        ComboBoxOrigemRegistro.setBorder(null);
        ComboBoxOrigemRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboBoxOrigemRegistro.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ComboBoxOrigemRegistro.setEditor(null);
        ComboBoxOrigemRegistro.setFocusCycleRoot(true);
        ComboBoxOrigemRegistro.setFocusable(false);
        ComboBoxOrigemRegistro.setLightWeightPopupEnabled(false);
        ComboBoxOrigemRegistro.setMinimumSize(new java.awt.Dimension(25, 25));

        lblData3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData3.setText("Ano");

        lblOrigem7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem7.setText("Origem");

        lblTitulo10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo10.setText("Registro");

        ComboBoxAreaRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxAreaRegistro.setToolTipText("");
        ComboBoxAreaRegistro.setDoubleBuffered(true);
        ComboBoxAreaRegistro.setEditor(null);
        ComboBoxAreaRegistro.setFocusable(false);
        ComboBoxAreaRegistro.setMaximumSize(new java.awt.Dimension(35, 26));
        ComboBoxAreaRegistro.setName("Selecione"); // NOI18N
        ComboBoxAreaRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxAreaRegistroActionPerformed(evt);
            }
        });

        campoTituloRegistro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTituloRegistro.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTituloRegistro.setMinimumSize(new java.awt.Dimension(25, 25));
        campoTituloRegistro.setDocument(new JTextFieldLimit(150, true, false));

        BotaoSalvarNovaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoSalvarNovaRevista1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoSalvarNovaRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Save Close_24px.png"))); // NOI18N
        BotaoSalvarNovaRevista1.setText("Salvar");
        BotaoSalvarNovaRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoSalvarNovaRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarNovaRevista1ActionPerformed(evt);
            }
        });

        lblEspecificacao1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao1.setText("Área");

        BotaoAdicionarNovaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoAdicionarNovaRevista1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoAdicionarNovaRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Add Book_24px.png"))); // NOI18N
        BotaoAdicionarNovaRevista1.setText("Nova revista");
        BotaoAdicionarNovaRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAdicionarNovaRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAdicionarNovaRevista1ActionPerformed(evt);
            }
        });

        campoDataRegistro.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        campoDataRegistro.setPreferredSize(new java.awt.Dimension(35, 26));
        campoDataRegistro.setDocument(new JTextFieldLimit(4, false, true));
        campoDataRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataRegistroActionPerformed(evt);
            }
        });

        BotaoCancelarNovaRevista3.setBackground(new java.awt.Color(255, 255, 255));
        BotaoCancelarNovaRevista3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BotaoCancelarNovaRevista3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Remove Book_24px.png"))); // NOI18N
        BotaoCancelarNovaRevista3.setText("Cancelar");
        BotaoCancelarNovaRevista3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoCancelarNovaRevista3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarNovaRevista3ActionPerformed(evt);
            }
        });

        lblData6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData6.setText("Título");

        campoRegistroRegistro.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        campoRegistroRegistro.setPreferredSize(new java.awt.Dimension(35, 26));
        campoRegistroRegistro.setDocument(new JTextFieldLimit(4, false, true));
        campoRegistroRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoRegistroRegistroActionPerformed(evt);
            }
        });

        campoLocalRegistro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoLocalRegistro.setMaximumSize(new java.awt.Dimension(25, 25));
        campoLocalRegistro.setMinimumSize(new java.awt.Dimension(25, 25));
        campoLocalRegistro.setDocument(new JTextFieldLimit(50, true, false));

        lblData8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData8.setText("Local");

        lblEspecificacao9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao9.setText("Editora");

        ComboBoxEditoraRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEditoraRegistro.setToolTipText("");
        ComboBoxEditoraRegistro.setDoubleBuffered(true);
        ComboBoxEditoraRegistro.setEditor(null);
        ComboBoxEditoraRegistro.setFocusable(false);
        ComboBoxEditoraRegistro.setMaximumSize(new java.awt.Dimension(35, 26));
        ComboBoxEditoraRegistro.setName("Selecione"); // NOI18N
        ComboBoxEditoraRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEditoraRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaNovaRevista1Layout = new javax.swing.GroupLayout(PaneGuiaNovaRevista1);
        PaneGuiaNovaRevista1.setLayout(PaneGuiaNovaRevista1Layout);
        PaneGuiaNovaRevista1Layout.setHorizontalGroup(
            PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                        .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                                .addComponent(BotaoAdicionarNovaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotaoCancelarNovaRevista3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BotaoSalvarNovaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PaneGuiaNovaRevista1Layout.createSequentialGroup()
                                .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrigem7)
                                    .addComponent(ComboBoxOrigemRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(158, 158, 158)
                                .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEspecificacao1)
                                    .addComponent(ComboBoxAreaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                                .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTitulo10)
                                    .addComponent(campoRegistroRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                                        .addComponent(lblData6)
                                        .addGap(705, 705, 705))
                                    .addComponent(campoTituloRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(124, Short.MAX_VALUE))
                    .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                        .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEspecificacao9)
                            .addComponent(ComboBoxEditoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                                .addComponent(lblData8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(campoLocalRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData3)
                            .addComponent(campoDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(132, 132, 132))))
        );
        PaneGuiaNovaRevista1Layout.setVerticalGroup(
            PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                        .addComponent(lblTitulo10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRegistroRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                        .addComponent(lblData6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTituloRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                            .addComponent(lblData8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(campoLocalRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                            .addComponent(lblData3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(campoDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                        .addComponent(lblEspecificacao9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxEditoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                        .addComponent(lblEspecificacao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxAreaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaNovaRevista1Layout.createSequentialGroup()
                        .addComponent(lblOrigem7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxOrigemRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(PaneGuiaNovaRevista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoSalvarNovaRevista1)
                    .addComponent(BotaoAdicionarNovaRevista1)
                    .addComponent(BotaoCancelarNovaRevista3))
                .addContainerGap(320, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Cadastrar", PaneGuiaNovaRevista1);

        PaneGuiaConsulta2.setBackground(new java.awt.Color(255, 255, 255));

        ConsultaTituloMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaTituloMenuRevistas1.setMaximumSize(new java.awt.Dimension(25, 25));
        ConsultaTituloMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaTituloMenuRevistas.setDocument(new JTextFieldLimit(40, true));

        lblTitulo11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo11.setText("Título da revista");

        lblOrigem9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem9.setText("Área");

        ConsultaAreaMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaAreaMenuRevistas1.setToolTipText("");
        ConsultaAreaMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ConsultaAreaMenuRevistas1.setFocusable(false);
        ConsultaAreaMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        ConsultaAreaMenuRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaAreaMenuRevistas1ActionPerformed(evt);
            }
        });

        ConsultaEspecificacaoMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConsultaEspecificacaoMenuRevistas1.setToolTipText("");
        ConsultaEspecificacaoMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
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

        lblEspecificacao7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao7.setText("Especificação");

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

        TabelaConsultaRevista1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        jScrollPane5.setViewportView(TabelaConsultaRevista1);

        BotaoLimpaConsultaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaConsultaRevista1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaConsultaRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoLimpaConsultaRevista1.setText("Limpa busca");
        BotaoLimpaConsultaRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaConsultaRevista1.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaConsultaRevista1.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaConsultaRevista1.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoLimpaConsultaRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaConsultaRevista1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneGuiaConsulta2Layout = new javax.swing.GroupLayout(PaneGuiaConsulta2);
        PaneGuiaConsulta2.setLayout(PaneGuiaConsulta2Layout);
        PaneGuiaConsulta2Layout.setHorizontalGroup(
            PaneGuiaConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaConsulta2Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(PaneGuiaConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PaneGuiaConsulta2Layout.createSequentialGroup()
                        .addComponent(BotaoNovaBuscaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoBuscaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoLimpaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaConsulta2Layout.createSequentialGroup()
                        .addGroup(PaneGuiaConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo11)
                            .addComponent(ConsultaTituloMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PaneGuiaConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ConsultaEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEspecificacao7))
                        .addGap(18, 18, 18)
                        .addGroup(PaneGuiaConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrigem9)
                            .addComponent(ConsultaAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addContainerGap(94, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaConsulta2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        PaneGuiaConsulta2Layout.setVerticalGroup(
            PaneGuiaConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaConsulta2Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(PaneGuiaConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PaneGuiaConsulta2Layout.createSequentialGroup()
                        .addComponent(lblTitulo11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConsultaTituloMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaConsulta2Layout.createSequentialGroup()
                        .addComponent(lblEspecificacao7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConsultaEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaConsulta2Layout.createSequentialGroup()
                        .addComponent(lblOrigem9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ConsultaAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(PaneGuiaConsulta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoBuscaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoLimpaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoNovaBuscaConsultaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Consultar", PaneGuiaConsulta2);

        PaneGuiaAlteraRevista3.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaAlteraRevista3.setToolTipText("");

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel11.setText("Propriedades da revista");
        jLabel11.setPreferredSize(new java.awt.Dimension(407, 41));

        BuscaTituloMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaTituloMenuRevistas1.setMaximumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaTituloMenuRevistas.setDocument(new JTextFieldLimit(40, true));

        lblTitulo12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo12.setText("Título da revista");

        BuscaEspecificacaoMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaEspecificacaoMenuRevistas1.setToolTipText("");
        BuscaEspecificacaoMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
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

        lblOrigem10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem10.setText("Área");

        BuscaAreaMenuRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BuscaAreaMenuRevistas1.setToolTipText("");
        BuscaAreaMenuRevistas1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        BuscaAreaMenuRevistas1.setFocusable(false);
        BuscaAreaMenuRevistas1.setMinimumSize(new java.awt.Dimension(25, 25));
        BuscaAreaMenuRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscaAreaMenuRevistas1ActionPerformed(evt);
            }
        });

        lblEspecificacao8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao8.setText("Especificação");

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
        BotaoAlterarRevistas1.setToolTipText("");
        BotaoAlterarRevistas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoAlterarRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarRevistas1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel12.setText("Escolha uma revista");

        jSeparator5.setForeground(new java.awt.Color(37, 103, 125));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        TabelaAlterarOuRemoverRevista1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        jScrollPane7.setViewportView(TabelaAlterarOuRemoverRevista1);

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

        BotaoLimpaCamposAlterarRevistas1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaCamposAlterarRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaCamposAlterarRevistas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Broom_24px.png"))); // NOI18N
        BotaoLimpaCamposAlterarRevistas1.setText("Limpar");
        BotaoLimpaCamposAlterarRevistas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaCamposAlterarRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaCamposAlterarRevistas1ActionPerformed(evt);
            }
        });

        BotaoLimpaBuscaRevista1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLimpaBuscaRevista1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLimpaBuscaRevista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/EntradaRevista/Clear Search_24px.png"))); // NOI18N
        BotaoLimpaBuscaRevista1.setText("Limpa busca");
        BotaoLimpaBuscaRevista1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLimpaBuscaRevista1.setMaximumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaBuscaRevista1.setMinimumSize(new java.awt.Dimension(145, 33));
        BotaoLimpaBuscaRevista1.setPreferredSize(new java.awt.Dimension(145, 33));
        BotaoLimpaBuscaRevista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimpaBuscaRevista1ActionPerformed(evt);
            }
        });

        BotaoLiberaCamposAlterarRevistas1.setBackground(new java.awt.Color(255, 255, 255));
        BotaoLiberaCamposAlterarRevistas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoLiberaCamposAlterarRevistas1.setText("Ativar alterações");
        BotaoLiberaCamposAlterarRevistas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotaoLiberaCamposAlterarRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLiberaCamposAlterarRevistas1ActionPerformed(evt);
            }
        });

        campoRegistroRegistro2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        campoRegistroRegistro2.setPreferredSize(new java.awt.Dimension(35, 26));
        campoRegistroRegistro.setDocument(new JTextFieldLimit(4, false, true));
        campoRegistroRegistro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoRegistroRegistro2ActionPerformed(evt);
            }
        });

        lblTitulo15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo15.setText("Registro");

        campoTituloRegistro2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoTituloRegistro2.setMaximumSize(new java.awt.Dimension(25, 25));
        campoTituloRegistro2.setMinimumSize(new java.awt.Dimension(25, 25));
        campoTituloRegistro.setDocument(new JTextFieldLimit(75, true, false));

        lblData12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData12.setText("Título");

        campoLocalRegistro2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoLocalRegistro2.setMaximumSize(new java.awt.Dimension(25, 25));
        campoLocalRegistro2.setMinimumSize(new java.awt.Dimension(25, 25));
        campoTituloRegistro.setDocument(new JTextFieldLimit(75, true, false));

        lblData14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData14.setText("Local");

        campoDataRegistro2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        campoDataRegistro2.setPreferredSize(new java.awt.Dimension(35, 26));
        campoDataRegistro.setDocument(new JTextFieldLimit(4, false, true));
        campoDataRegistro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDataRegistro2ActionPerformed(evt);
            }
        });

        lblData15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblData15.setText("Ano");

        ComboBoxAreaRegistro2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxAreaRegistro2.setToolTipText("");
        ComboBoxAreaRegistro2.setDoubleBuffered(true);
        ComboBoxAreaRegistro2.setEditor(null);
        ComboBoxAreaRegistro2.setFocusable(false);
        ComboBoxAreaRegistro2.setMaximumSize(new java.awt.Dimension(35, 26));
        ComboBoxAreaRegistro2.setName("Selecione"); // NOI18N
        ComboBoxAreaRegistro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxAreaRegistro2ActionPerformed(evt);
            }
        });

        lblEspecificacao12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao12.setText("Área");

        ComboBoxOrigemRegistro2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxOrigemRegistro2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doação", "Aquisição" }));
        ComboBoxOrigemRegistro2.setBorder(null);
        ComboBoxOrigemRegistro2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboBoxOrigemRegistro2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ComboBoxOrigemRegistro2.setEditor(null);
        ComboBoxOrigemRegistro2.setFocusCycleRoot(true);
        ComboBoxOrigemRegistro2.setFocusable(false);
        ComboBoxOrigemRegistro2.setLightWeightPopupEnabled(false);
        ComboBoxOrigemRegistro2.setMinimumSize(new java.awt.Dimension(25, 25));

        lblOrigem13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblOrigem13.setText("Origem");

        ComboBoxEditoraRegistro2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEditoraRegistro2.setToolTipText("");
        ComboBoxEditoraRegistro2.setDoubleBuffered(true);
        ComboBoxEditoraRegistro2.setEditor(null);
        ComboBoxEditoraRegistro2.setFocusable(false);
        ComboBoxEditoraRegistro2.setMaximumSize(new java.awt.Dimension(35, 26));
        ComboBoxEditoraRegistro2.setName("Selecione"); // NOI18N
        ComboBoxEditoraRegistro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEditoraRegistro2ActionPerformed(evt);
            }
        });

        lblEspecificacao11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao11.setText("Editora");

        javax.swing.GroupLayout PaneGuiaAlteraRevista3Layout = new javax.swing.GroupLayout(PaneGuiaAlteraRevista3);
        PaneGuiaAlteraRevista3.setLayout(PaneGuiaAlteraRevista3Layout);
        PaneGuiaAlteraRevista3Layout.setHorizontalGroup(
            PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                                .addComponent(BotaoResetaPesquisaAlterarOuRemoverRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addComponent(BotaoBuscaAlterarOuRemoverRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BotaoLimpaBuscaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrigem10)
                                    .addComponent(BuscaAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BuscaEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEspecificacao8))
                                .addGap(66, 66, 66))
                            .addComponent(BuscaTituloMenuRevistas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(lblTitulo12))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                            .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitulo15)
                                .addComponent(campoRegistroRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                                    .addComponent(lblEspecificacao11)
                                    .addGap(327, 327, 327))
                                .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                                    .addComponent(ComboBoxEditoraRegistro2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(31, 31, 31))))
                        .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(73, 73, 73)))
                    .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrigem13)
                            .addComponent(ComboBoxOrigemRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEspecificacao12)
                            .addComponent(ComboBoxAreaRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblData12)
                    .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                            .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                                    .addComponent(lblData14)
                                    .addGap(0, 291, Short.MAX_VALUE))
                                .addComponent(campoLocalRegistro2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblData15)
                                .addComponent(campoDataRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(campoTituloRegistro2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BotaoLiberaCamposAlterarRevistas1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BotaoLimpaCamposAlterarRevistas1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoRemoverRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoAlterarRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        PaneGuiaAlteraRevista3Layout.setVerticalGroup(
            PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscaTituloMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                                .addComponent(lblOrigem10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BuscaAreaMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblEspecificacao8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BuscaEspecificacaoMenuRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotaoBuscaAlterarOuRemoverRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoResetaPesquisaAlterarOuRemoverRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoLimpaBuscaRevista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTitulo15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRegistroRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addComponent(lblEspecificacao11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxEditoraRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblData12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTituloRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addComponent(lblData14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoLocalRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addComponent(lblData15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDataRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addComponent(lblOrigem13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxOrigemRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PaneGuiaAlteraRevista3Layout.createSequentialGroup()
                        .addComponent(lblEspecificacao12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxAreaRegistro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(BotaoLiberaCamposAlterarRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaneGuiaAlteraRevista3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoLimpaCamposAlterarRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoRemoverRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoAlterarRevistas1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Gerenciar", PaneGuiaAlteraRevista3);

        PaneGuiaNovaRevista2.setBackground(new java.awt.Color(255, 255, 255));
        PaneGuiaNovaRevista2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PaneGuiaNovaRevista2.setMinimumSize(new java.awt.Dimension(1097, 681));

        ComboBoxEditoraRegistro1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEditoraRegistro1.setToolTipText("");
        ComboBoxEditoraRegistro1.setDoubleBuffered(true);
        ComboBoxEditoraRegistro1.setEditor(null);
        ComboBoxEditoraRegistro1.setFocusable(false);
        ComboBoxEditoraRegistro1.setMaximumSize(new java.awt.Dimension(35, 26));
        ComboBoxEditoraRegistro1.setName("Selecione"); // NOI18N
        ComboBoxEditoraRegistro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEditoraRegistro1ActionPerformed(evt);
            }
        });

        lblEspecificacao10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEspecificacao10.setText("Editora");

        javax.swing.GroupLayout PaneGuiaNovaRevista2Layout = new javax.swing.GroupLayout(PaneGuiaNovaRevista2);
        PaneGuiaNovaRevista2.setLayout(PaneGuiaNovaRevista2Layout);
        PaneGuiaNovaRevista2Layout.setHorizontalGroup(
            PaneGuiaNovaRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaNovaRevista2Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(PaneGuiaNovaRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEspecificacao10)
                    .addComponent(ComboBoxEditoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(831, Short.MAX_VALUE))
        );
        PaneGuiaNovaRevista2Layout.setVerticalGroup(
            PaneGuiaNovaRevista2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneGuiaNovaRevista2Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(lblEspecificacao10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxEditoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(481, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1097, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PaneGuiaNovaRevista2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PaneGuiaNovaRevista2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("tab4", jPanel6);

        javax.swing.GroupLayout PaneRegistroLayout = new javax.swing.GroupLayout(PaneRegistro);
        PaneRegistro.setLayout(PaneRegistroLayout);
        PaneRegistroLayout.setHorizontalGroup(
            PaneRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane3)
        );
        PaneRegistroLayout.setVerticalGroup(
            PaneRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneRegistroLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE))
        );

        PaneMae.add(PaneRegistro, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaneMae, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SideBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PaneMae, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//-------------------------Inicio das ações de revista ------------------------------------//
    private void BotaoRevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoRevistaMouseClicked
        PaneRevista.setVisible(true);
        PaneFerramentas.setVisible(false);
        PaneRegistro.setVisible(false);
        setLblColor(BotaoRevista);
        ResetColor(BotaoFerramenta);
        ResetColor(BotaoRegistro);
    }//GEN-LAST:event_BotaoRevistaMouseClicked

    private void BotaoFerramentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoFerramentaMouseClicked
        PaneRevista.setVisible(false);
        PaneFerramentas.setVisible(true);
        PaneRegistro.setVisible(false);
        setLblColor(BotaoFerramenta);
        ResetColor(BotaoRevista);
        ResetColor(BotaoRegistro);
    }//GEN-LAST:event_BotaoFerramentaMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void BotaoNovaBuscaConsultaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaBuscaConsultaRevistaActionPerformed
        atualizarConsultaRevista();
        DestravaCamposConsultaRevista();
        LimpaCamposConsultaRevista();
        BotaoBuscaConsultaRevista.setEnabled(true);
        BotaoLimpaConsultaRevista.setEnabled(true);
        ClearComboBoxEspecificacao();
        ComboboxEspecificacao();
        ClearComboBoxArea();
        ComboboxArea();
        LimpaCombos();
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
            ListaBuscaRevista = revistaDAO.ListaBuscaRevista(rev);

            ConsultaRevistaComFiltro();
        } catch (Exception E) {
            System.out.println(E.getMessage());
            JOptionPane.showMessageDialog(null, "Problema no BotaoBuscaConsultaRevista do DashBoard, consulta de revista falhou", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(E.getMessage());
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
        BotaoLiberaCamposAlterarRevistas.setEnabled(false);
    }//GEN-LAST:event_BotaoLimpaCamposAlterarRevistasActionPerformed

    private void BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed
        DestravaCamposDoPesquisarRevistasParaAlteracao();
        TravaCamposAlteraRevista();
        TravaBotoeAlteraRevista();
        ResetaCamposAlterarRevistas();
        atualizarTabelaRevista();
        LimpaCamposAlteraRevista();
        BotaoLimpaCamposAlterarRevistas.setEnabled(false);
        ClearComboBoxEspecificacao();
        ComboboxEspecificacao();
        ClearComboBoxArea();
        ComboboxArea();
        LimpaCombos();
    }//GEN-LAST:event_BotaoResetaPesquisaAlterarOuRemoverRevistaActionPerformed


    private void BotaoRemoverRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverRevistasActionPerformed

        if (IDTravadoAlteraRevista.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma revista para exclui-la", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir essa revista?", "pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Revistas rev = new Revistas();
                RevistaDAO revistaDAO = new RevistaDAO();

                rev.setID(Integer.parseInt(IDTravadoAlteraRevista.getText()));
                revistaDAO.RemoverRevista(rev);
                try {
                    BotaoLiberaCamposAlterarRevistas.setEnabled(false);

                    limpaCamposNovaRevista();
                    atualizarTabelaRevista();
                    LimpaCamposAlteraRevista();
                    TravaCamposAlteraRevista();
                    atualizarConsultaRevista();
                    TravaBotoeAlteraRevista();
                    ResetaCamposAlterarRevistas();

                    JOptionPane.showMessageDialog(null, "Revista Removida com sucesso", "Sistema", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + e.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(e.getMessage());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Náo foi possível remover essa revista! Erro:" + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(ex.getMessage());
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
        ClearComboBoxEspecificacao();
        ComboboxEspecificacao();
        ClearComboBoxArea();
        ComboboxArea();

        TabelaAlterarOuRemoverRevista.getTableHeader().setReorderingAllowed(false);
        IDTravadoAlteraRevista.setText(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 0).toString());
        AlteraTituloMenuRevistas.setText(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 1).toString());
        AlteraAreaMenuRevistas.getModel().setSelectedItem(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 2).toString());
        AlteraEspecificacaoMenuRevistas.getModel().setSelectedItem(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 3).toString());
        AlteraAnoMenuRevistas.setText(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 4).toString());
        AlteraQuantidadeMenuRevistas.setText(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 5).toString());
        AlteraOrigemMenuRevistas.getModel().setSelectedItem(TabelaAlterarOuRemoverRevista.getValueAt(TabelaAlterarOuRemoverRevista.getSelectedRow(), 6).toString());

        BotaoLiberaCamposAlterarRevistas.setEnabled(true);
        BotaoLimpaCamposAlterarRevistas.setEnabled(true);
        TravaCamposAlteraRevista();
        TravaBotoeAlteraRevista();
    }//GEN-LAST:event_TabelaAlterarOuRemoverRevistaMouseClicked

    private void BotaoAlterarRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarRevistasActionPerformed
        if (IDTravadoAlteraRevista.getText().isEmpty() || AlteraTituloMenuRevistas.getText().isEmpty() || AlteraAnoMenuRevistas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Há campos vazios, preencha esses campos, para proceder com a alteração", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else if (JOptionPane.showConfirmDialog(null, "Deseja mesmo alterar essa revista?", "pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Revistas rev = new Revistas();
                RevistaDAO revistaDAO = new RevistaDAO();

                rev.setID(Integer.parseInt(IDTravadoAlteraRevista.getText()));
                rev.setTitulo(AlteraTituloMenuRevistas.getText());
                rev.setEspecificacao((String) AlteraEspecificacaoMenuRevistas.getSelectedItem());
                rev.setArea((String) AlteraAreaMenuRevistas.getSelectedItem());
                rev.setData(Integer.parseInt(AlteraAnoMenuRevistas.getText()));
                rev.setOrigem((String) AlteraOrigemMenuRevistas.getSelectedItem());
                rev.setQuantidade(Integer.parseInt(AlteraQuantidadeMenuRevistas.getText()));

                revistaDAO.AlterarRevista(rev);

                try {
                    atualizarTabelaRevista();

                    LimpaCamposAlteraRevista();
                    TravaCamposAlteraRevista();
                    TravaBotoeAlteraRevista();
                    atualizarConsultaRevista();
                    ResetaCamposAlterarRevistas();
                    BotaoLiberaCamposAlterarRevistas.setEnabled(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Náo foi possível alterar essa revista! Erro:" + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(ex.getMessage());
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
        TravaCamposAlteraRevista();
        TravaBotoeAlteraRevista();
        BotaoLimpaCamposAlterarRevistas.setEnabled(false);
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

    private void BotaoAdicionarNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAdicionarNovaRevistaActionPerformed
        DestravaCamposDoNovaRevista();
        limpaCamposNovaRevista();
        DestravaBotoesCadRevista();
        BotaoAdicionarNovaRevista.setEnabled(false);
        ClearComboBoxEspecificacao();
        ComboboxEspecificacao();
        ClearComboBoxArea();
        ComboboxArea();
        LimpaCombos();
        teste();
    }//GEN-LAST:event_BotaoAdicionarNovaRevistaActionPerformed

    //Aqui cria-se uma nova revista
    private void BotaoSalvarNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarNovaRevistaActionPerformed
        if (campoTitulo.getText().isEmpty() || campoData.getText().isEmpty() || campoQuantidade.getText().isEmpty() || ComboBoxEspecificacaoNovaRevista.getSelectedItem() == null || ComboBoxAreaNovaRevista.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Há campos não preenchidos", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                Revistas rev = new Revistas();

                rev.getID();
                rev.setTitulo(campoTitulo.getText());
                rev.setEspecificacao((String) ComboBoxEspecificacaoNovaRevista.getSelectedItem());
                rev.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                rev.setOrigem((String) ComboBoxOrigem.getSelectedItem());
                rev.setData(Integer.parseInt(campoData.getText()));
                rev.setArea((String) ComboBoxAreaNovaRevista.getSelectedItem());

                RevistaDAO revistaDAO = new RevistaDAO();
                revistaDAO.InserirNovaRevistas(rev);
                try {
                    limpaCamposNovaRevista();
                    TravaCamposDoNovaRevista();
                    atualizarTabelaRevista();
                    atualizarConsultaRevista();
                    TravaBotoesCadRevista();
                    BotaoAdicionarNovaRevista.setEnabled(true);

                    JOptionPane.showMessageDialog(null, "Revista cadastrada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Essa revista já existe! Erro:" + ex.getMessage(), "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoSalvarNovaRevistaActionPerformed

    private void ComboBoxAreaNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAreaNovaRevistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAreaNovaRevistaActionPerformed

    private void campoDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataActionPerformed

    private void TabelaConsultaRevistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaConsultaRevistaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TabelaConsultaRevistaMouseClicked

    private void BotaoCancelarNovaRevista2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarNovaRevista2ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Todos os dados informados serão perdidos. Desaja continuar?", "Cadastro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            limpaCamposNovaRevista();
            TravaCamposDoNovaRevista();
            TravaBotoesCadRevista();
            BotaoAdicionarNovaRevista.setEnabled(true);
        }
    }//GEN-LAST:event_BotaoCancelarNovaRevista2ActionPerformed

    private void BotaoLimpaConsultaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaConsultaRevistaActionPerformed
        LimpaCamposConsultaRevista();
        ResetaTabelaConsultaRevista();
        ClearComboBoxEspecificacao();
        ComboboxEspecificacao();
        ClearComboBoxArea();
        ComboboxArea();
        LimpaCombos();
    }//GEN-LAST:event_BotaoLimpaConsultaRevistaActionPerformed

    private void BotaoLimpaBuscaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaBuscaRevistaActionPerformed
        ResetaCamposAlterarRevistas();
        ResetaTabelaAlterarOuRemoverRevista();
        TravaCamposAlteraRevista();
        TravaBotoeAlteraRevista();
        BotaoLimpaCamposAlterarRevistas.setEnabled(false);
        ClearComboBoxEspecificacao();
        ComboboxEspecificacao();
        ClearComboBoxArea();
        ComboboxArea();
        LimpaCombos();
    }//GEN-LAST:event_BotaoLimpaBuscaRevistaActionPerformed

    private void BotaoLiberaCamposAlterarRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLiberaCamposAlterarRevistasActionPerformed
        DestravaBotoeAlteraRevista();
        DesravaCamposAlteraRevista();
    }//GEN-LAST:event_BotaoLiberaCamposAlterarRevistasActionPerformed

    private void ConsultaTipoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaTipoComboBoxActionPerformed
        if (ConsultaTipoComboBox.getModel().getSelectedItem() == "Área") {
            Area area = new Area();
            AreaDAO areaDAO = new AreaDAO();
            try {
                ListaBuscaArea = areaDAO.ListaBuscaArea(area);
                ConsultaAreaEspecificacao();
            } catch (SQLException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
            ConsultaNomeMenuTipo.setText("");
            ConsultaNomeMenuTipo.requestFocus();
        } else if (ConsultaTipoComboBox.getModel().getSelectedItem() == "Especificação") {
            Especificacao especificacao = new Especificacao();
            EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();
            try {
                ListaBuscaEspecificacao = especificacaoDAO.ListaBuscaEspecificacao(especificacao);
                ConsultaEspecificacaoArea();
            } catch (SQLException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
            ConsultaNomeMenuTipo.setText("");
            ConsultaNomeMenuTipo.requestFocus();
        } else if (ConsultaTipoComboBox.getModel().getSelectedItem() == "Editora") {
            Editora editora = new Editora();
            EditoraDAO editoraDAO = new EditoraDAO();
            try {
                ListaBuscaEditora = editoraDAO.ListaBuscaEditora(editora);
                ConsultaEditora();
            } catch (SQLException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
            ConsultaNomeMenuTipo.setText("");
            ConsultaNomeMenuTipo.requestFocus();
        }

    }//GEN-LAST:event_ConsultaTipoComboBoxActionPerformed

    private void BotaoConsultaAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoConsultaAreaActionPerformed
        ListaBuscaArea = null;
        ListaBuscaEspecificacao = null;
        ListaBuscaEditora = null;

        if (ConsultaTipoComboBox.getModel().getSelectedItem() == "Área") {

            Area area = new Area();
            AreaDAO areaDAO = new AreaDAO();

            try {

                area.setNome(ConsultaNomeMenuTipo.getText());
                ListaBuscaArea = areaDAO.ListaBuscaArea(area);

                ConsultaAreaEspecificacao();
            } catch (Exception E) {
                System.out.println(E.getMessage());
            }
        } else if (ConsultaTipoComboBox.getModel().getSelectedItem() == "Especificação") {

            Especificacao especificacao = new Especificacao();
            EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();

            try {
                especificacao.setNome(ConsultaNomeMenuTipo.getText());

                ListaBuscaEspecificacao = especificacaoDAO.ListaBuscaEspecificacao(especificacao);
                ConsultaEspecificacaoArea();
            } catch (Exception E) {
                System.out.println(E.getMessage());
            }
        } else if (ConsultaTipoComboBox.getModel().getSelectedItem() == "Editora") {

            Editora editora = new Editora();
            EditoraDAO editoraDAO = new EditoraDAO();

            try {
                editora.setNome(ConsultaNomeMenuTipo.getText());

                ListaBuscaEditora = editoraDAO.ListaBuscaEditora(editora);
                ConsultaEditora();
            } catch (Exception E) {
                System.out.println(E.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoConsultaAreaActionPerformed

    private void BotaoNovaConsultaAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoNovaConsultaAreaActionPerformed
        DestravaCamposConsultaTipo();
        ResetaTabelaConsultaTipo();
    }//GEN-LAST:event_BotaoNovaConsultaAreaActionPerformed

    private void TabelaConsultaTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaConsultaTipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TabelaConsultaTipoMouseClicked

    private void BotaoLimpaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaConsultaActionPerformed
        LimpaCamposConsultaTipo();
        ResetaTabelaConsultaTipo();
    }//GEN-LAST:event_BotaoLimpaConsultaActionPerformed

    private void BotaoBuscaAlterarOuRemoverAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscaAlterarOuRemoverAreaActionPerformed
        ListaBuscaArea = null;
        Area area = new Area();
        AreaDAO areaDAO = new AreaDAO();
        TravaTudoAreaAlterarOuRemover();
        try {
            if (!BuscaTituloMenuArea.getText().isEmpty()) {
                area.setNome(BuscaTituloMenuArea.getText());
            }

            ListaBuscaArea = areaDAO.ListaBuscaArea(area);

            ConsultaAreaAlterar();
        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Problema no botão BotaoBuscaAlterarOuRemoverArea", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(E.getMessage());
        }


    }//GEN-LAST:event_BotaoBuscaAlterarOuRemoverAreaActionPerformed

    private void BotaoAlterarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarAreaActionPerformed
        if (AlteraNomeMenuArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma área!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        if (JOptionPane.showConfirmDialog(null, "Essa área será alterada. Desaja continuar?", "pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

            try {
                Area area = new Area();
                area.setId(Integer.parseInt(AlteraIDMenuArea.getText()));
                area.setNome(AlteraNomeMenuArea.getText());

                AreaDAO areaDAO = new AreaDAO();
                areaDAO.AlterarArea(area);

                try {
                    atualizarConsultaArea();

                    LimpaCamposAreaAlterarOuRemover();
                    TravaTudoAreaAlterarOuRemover();

                    ClearComboBoxArea();
                    ComboboxArea();
                    LimpaCombos();
                    BotaoLiberaCamposAlterarArea.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Área alterada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Náo foi possível alterar essa área! Erro: " + e.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoAlterarAreaActionPerformed

    private void TabelaAlterarOuRemoverAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaAlterarOuRemoverAreaMouseClicked
        TabelaAlterarOuRemoverArea.getTableHeader().setReorderingAllowed(false);
        AlteraIDMenuArea.setText(TabelaAlterarOuRemoverArea.getValueAt(TabelaAlterarOuRemoverArea.getSelectedRow(), 0).toString());
        AlteraNomeMenuArea.setText(TabelaAlterarOuRemoverArea.getValueAt(TabelaAlterarOuRemoverArea.getSelectedRow(), 1).toString());

        TravaTudoAreaAlterarOuRemover();
        BotaoLiberaCamposAlterarArea.setEnabled(true);
        BotaoLiberaCamposAlterarArea.setEnabled(true);
    }//GEN-LAST:event_TabelaAlterarOuRemoverAreaMouseClicked

    private void BotaoRemoverAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverAreaActionPerformed
        if (AlteraNomeMenuArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma área!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        if (JOptionPane.showConfirmDialog(null, "Essa área será removida. Desaja continuar?", "Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Area area = new Area();
                area.setId(Integer.parseInt(AlteraIDMenuArea.getText()));

                AreaDAO areaDAO = new AreaDAO();
                areaDAO.RemoverArea(area);

                try {
                    atualizarConsultaArea();

                    LimpaCamposAreaAlterarOuRemover();
                    TravaTudoAreaAlterarOuRemover();
                    BotaoLiberaCamposAlterarArea.setEnabled(false);

                    ClearComboBoxArea();
                    ComboboxArea();
                    LimpaCombos();
                    JOptionPane.showMessageDialog(null, "Área removida com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Náo foi possível remover essa área! Erro:" + e.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoRemoverAreaActionPerformed

    private void BotaoResetaPesquisaAlterarOuRemoverAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoResetaPesquisaAlterarOuRemoverAreaActionPerformed
        DestravaCamposConsultaAreaAlterarOuRemover();
        atualizarConsultaArea();
        TravaTudoAreaAlterarOuRemover();
    }//GEN-LAST:event_BotaoResetaPesquisaAlterarOuRemoverAreaActionPerformed

    private void BotaoLimpaCamposAlterarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaCamposAlterarAreaActionPerformed
        LimpaCamposAreaAlterarOuRemover();
        TravaTudoAreaAlterarOuRemover();
        BotaoLiberaCamposAlterarArea.setEnabled(false);
    }//GEN-LAST:event_BotaoLimpaCamposAlterarAreaActionPerformed

    private void BotaoLimpaAlterarOuRemoverAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaAlterarOuRemoverAreaActionPerformed
        LimpaCamposConsultaAreaAlterarOuRemover();
        TravaTudoAreaAlterarOuRemover();
        ResetaTabelaAlterarOuRemoverArea();
    }//GEN-LAST:event_BotaoLimpaAlterarOuRemoverAreaActionPerformed

    private void BotaoLiberaCamposAlterarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLiberaCamposAlterarAreaActionPerformed
        DestravaTudoAreaAlterarOuRemover();
        BotaoLiberaCamposAlterarArea.setEnabled(false);
    }//GEN-LAST:event_BotaoLiberaCamposAlterarAreaActionPerformed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void BotaoAdicionarNovoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAdicionarNovoTipoActionPerformed
        DestravaCamposNovoTipo();
        BotaoAdicionarNovoTipo.setEnabled(false);
    }//GEN-LAST:event_BotaoAdicionarNovoTipoActionPerformed

    private void BotaoCancelaNovoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelaNovoTipoActionPerformed

        if (JOptionPane.showConfirmDialog(null, "Todos os dados informados serão perdidos. Desaja continuar?", "Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

            LimpaCamposNovoTipo();
            TravaCamposNovoTipo();
            BotaoAdicionarNovoTipo.setEnabled(true);
        }
    }//GEN-LAST:event_BotaoCancelaNovoTipoActionPerformed

    private void BotaoSalvarNovoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarNovoTipoActionPerformed
        if (CampoNovoTituloTipo.getText().isEmpty() || ComboBoxNovoTipo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Preencha o campo título!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else if (ComboBoxNovoTipo.getModel().getSelectedItem() == "Área") {
            try {
                Area area = new Area();
                area.getId();
                area.setNome(CampoNovoTituloTipo.getText().toUpperCase());

                AreaDAO areaDAO = new AreaDAO();
                areaDAO.InserirArea(area);

                try {
                    atualizarConsultaArea();

                    LimpaCamposNovoTipo();
                    TravaCamposNovoTipo();
                    BotaoAdicionarNovoTipo.setEnabled(true);

                    ClearComboBoxArea();
                    ComboboxArea();
                    LimpaCombos();

                    JOptionPane.showMessageDialog(null, "Área adicionada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage(), "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Essa área já existe! Erro: " + ex.getMessage(), "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(ex.getMessage());
            }

        } else if (ComboBoxNovoTipo.getModel().getSelectedItem() == "Especificação") {
            try {
                Especificacao especificacao = new Especificacao();
                especificacao.getId();
                especificacao.setNome(CampoNovoTituloTipo.getText());

                EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();
                especificacaoDAO.InserirEspecificacao(especificacao);

                try {
                    atualizarConsultaEspecificacao();

                    LimpaCamposNovoTipo();
                    TravaCamposNovoTipo();
                    BotaoAdicionarNovoTipo.setEnabled(true);

                    ClearComboBoxEspecificacao();
                    ComboboxEspecificacao();
                    LimpaCombos();

                    JOptionPane.showMessageDialog(null, "Especificação adicionada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException Ex) {
                JOptionPane.showMessageDialog(null, "Essa especificação já existe! Erro: " + Ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(Ex.getMessage());
            }
        } else if (ComboBoxNovoTipo.getModel().getSelectedItem() == "Editora") {
            try {
                Editora editora = new Editora();
                editora.getId();
                editora.setNome(CampoNovoTituloTipo.getText());

                EditoraDAO editoraDAO = new EditoraDAO();
                editoraDAO.InserirEditora(editora);

                try {
                    atualizarConsultaEditora();

                    LimpaCamposNovoTipo();
                    TravaCamposNovoTipo();
                    BotaoAdicionarNovoTipo.setEnabled(true);
                    
                    ClearComboBoxEspecificacao();
                    ComboboxEspecificacao();
                    LimpaCombos();

                    JOptionPane.showMessageDialog(null, "Especificação adicionada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException Ex) {
                JOptionPane.showMessageDialog(null, "Essa especificação já existe! Erro: " + Ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(Ex.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoSalvarNovoTipoActionPerformed

    private void ComboBoxNovoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxNovoTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxNovoTipoActionPerformed

    private void BotaoLiberarAcoesEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLiberarAcoesEspActionPerformed
        DestravaTudoEspAlterarOuRemover();
        BotaoLiberarAcoesEsp.setEnabled(false);
    }//GEN-LAST:event_BotaoLiberarAcoesEspActionPerformed

    private void BotaoLimpaAlterarOuRemoverEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaAlterarOuRemoverEspActionPerformed
        LimpaCamposConsultaEspAlterarOuRemover();
        TravaTudoEspAlterarOuRemover();
        ResetaTabelaAlterarOuRemoverEspecificacao();
    }//GEN-LAST:event_BotaoLimpaAlterarOuRemoverEspActionPerformed

    private void BotaoLimpaCamposAlterarEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaCamposAlterarEspActionPerformed
        LimpaCamposEspAlterarOuRemover();
        TravaTudoEspAlterarOuRemover();
        BotaoLiberarAcoesEsp.setEnabled(false);
    }//GEN-LAST:event_BotaoLimpaCamposAlterarEspActionPerformed

    private void BotaoResetaPesquisaAlterarOuRemoverEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoResetaPesquisaAlterarOuRemoverEspActionPerformed
        atualizarConsultaEspecificacao();
        DestravaCamposConsultaEspAlterarOuRemover();

        LimpaCamposConsultaEspAlterarOuRemover();
        LimpaCamposEspAlterarOuRemover();
        TravaTudoEspAlterarOuRemover();
    }//GEN-LAST:event_BotaoResetaPesquisaAlterarOuRemoverEspActionPerformed

    private void BotaoRemoverEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverEspActionPerformed
        if (AlteraNomeMenuEspecificacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma especificação!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }

        if (JOptionPane.showConfirmDialog(null, "Essa especificação será removida. Desaja continuar?", "Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Especificacao especificacao = new Especificacao();
                especificacao.setId(Integer.parseInt(AlteraIDMenuEspecificacao.getText()));
                EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();
                especificacaoDAO.RemoverEspecificacao(especificacao);

                try {
                    atualizarConsultaEspecificacao();

                    LimpaCamposEspAlterarOuRemover();
                    TravaTudoEspAlterarOuRemover();

                    ClearComboBoxEspecificacao();
                    ComboboxEspecificacao();
                    LimpaCombos();

                    JOptionPane.showMessageDialog(null, "Especificação removida com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Náo foi possível remover essa especificação! Erro:" + e.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoRemoverEspActionPerformed

    private void BotaoAlterarEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarEspActionPerformed
        if (AlteraNomeMenuEspecificacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma especificação!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        if (JOptionPane.showConfirmDialog(null, "Essa especificação será alterada. Desaja continuar?", "pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

            try {
                Especificacao especificacao = new Especificacao();
                especificacao.setId(Integer.parseInt(AlteraIDMenuEspecificacao.getText()));
                especificacao.setNome(AlteraNomeMenuEspecificacao.getText());

                EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();
                especificacaoDAO.AlterarEspecificacao(especificacao);

                try {
                    atualizarConsultaEspecificacao();

                    LimpaCamposEspAlterarOuRemover();
                    TravaTudoEspAlterarOuRemover();
                    TravaTudoEspAlterarOuRemover();

                    BotaoLiberarAcoesEsp.setEnabled(true);

                    ClearComboBoxEspecificacao();
                    ComboboxEspecificacao();
                    LimpaCombos();

                    JOptionPane.showMessageDialog(null, "Especificação alterada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Náo foi possível alterar essa especificação! Erro:" + e.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoAlterarEspActionPerformed

    private void BotaoBuscaAlterarOuRemoverEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscaAlterarOuRemoverEspActionPerformed
        ListaBuscaEspecificacao = null;
        Especificacao especificacao = new Especificacao();
        EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();
        TravaTudoEspAlterarOuRemover();

        try {
            if (!BuscaTituloMenuEspecificacao.getText().isEmpty()) {
                especificacao.setNome(BuscaTituloMenuEspecificacao.getText());
            }

            ListaBuscaEspecificacao = especificacaoDAO.ListaBuscaEspecificacao(especificacao);

            ConsultaEspecificacaoAlterar();
        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Problema no botão BotaoBuscaAlterarOuRemoverEsp", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(E.getMessage());

        }
    }//GEN-LAST:event_BotaoBuscaAlterarOuRemoverEspActionPerformed

    private void TabelaAlterarOuRemoverEspecificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaAlterarOuRemoverEspecificacaoMouseClicked
        TabelaAlterarOuRemoverEspecificacao.getTableHeader().setReorderingAllowed(false);
        AlteraIDMenuEspecificacao.setText(TabelaAlterarOuRemoverEspecificacao.getValueAt(TabelaAlterarOuRemoverEspecificacao.getSelectedRow(), 0).toString());
        AlteraNomeMenuEspecificacao.setText(TabelaAlterarOuRemoverEspecificacao.getValueAt(TabelaAlterarOuRemoverEspecificacao.getSelectedRow(), 1).toString());

        TravaTudoEspAlterarOuRemover();
        BotaoLiberarAcoesEsp.setEnabled(true);
    }//GEN-LAST:event_TabelaAlterarOuRemoverEspecificacaoMouseClicked

    private void ComboBoxEspecificacaoNovaRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEspecificacaoNovaRevistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEspecificacaoNovaRevistaActionPerformed

    private void BotaoRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotaoRegistroMouseClicked
        PaneRevista.setVisible(false);
        PaneFerramentas.setVisible(false);
        PaneRegistro.setVisible(true);
        ResetColor(BotaoRevista);
        ResetColor(BotaoFerramenta);
        setLblColor(BotaoRegistro);
    }//GEN-LAST:event_BotaoRegistroMouseClicked

    private void ComboBoxAreaRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAreaRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAreaRegistroActionPerformed

    private void BotaoSalvarNovaRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarNovaRevista1ActionPerformed
        if (campoRegistroRegistro.getText().isEmpty() || campoTituloRegistro.getText().isEmpty() || campoLocalRegistro.getText().isEmpty() || campoDataRegistro.getText().isEmpty() 
                || ComboBoxEditoraRegistro.getSelectedItem() == null || ComboBoxAreaRegistro.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Há campos vazios, preencha-os", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                Registro reg = new Registro();

                reg.setRegistro(Integer.parseInt(campoRegistroRegistro.getText()));
                reg.setTitulo(campoTituloRegistro.getText());
                reg.setLocal(campoLocalRegistro.getText());
                reg.setArea((String) ComboBoxAreaRegistro.getSelectedItem());
                reg.setAno(Integer.parseInt(campoDataRegistro.getText()));
                reg.setEditora((String) ComboBoxEditoraRegistro.getSelectedItem());                
                reg.setOrigem((String) ComboBoxOrigemRegistro.getSelectedItem());

                RegistroDAO registroDAO = new RegistroDAO();
                registroDAO.InserirNovaRegistro(reg);
                try {
                    /*limpaCamposNovaRevista();
                    TravaCamposDoNovaRevista();
                    atualizarTabelaRevista();
                    atualizarConsultaRevista();
                    TravaBotoesCadRevista();
                    BotaoAdicionarNovaRevista.setEnabled(true);*/

                    JOptionPane.showMessageDialog(null, "Revista cadastrada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Essa revista já existe! Erro:" + ex.getMessage(), "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoSalvarNovaRevista1ActionPerformed

    private void BotaoAdicionarNovaRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAdicionarNovaRevista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoAdicionarNovaRevista1ActionPerformed

    private void campoDataRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataRegistroActionPerformed

    private void BotaoCancelarNovaRevista3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarNovaRevista3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoCancelarNovaRevista3ActionPerformed

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

    private void BotaoLimpaConsultaRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaConsultaRevista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoLimpaConsultaRevista1ActionPerformed

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

    private void BotaoRemoverRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoRemoverRevistas1ActionPerformed

    private void BotaoResetaPesquisaAlterarOuRemoverRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoResetaPesquisaAlterarOuRemoverRevista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoResetaPesquisaAlterarOuRemoverRevista1ActionPerformed

    private void BotaoLimpaCamposAlterarRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaCamposAlterarRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoLimpaCamposAlterarRevistas1ActionPerformed

    private void BotaoLimpaBuscaRevista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaBuscaRevista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoLimpaBuscaRevista1ActionPerformed

    private void BotaoLiberaCamposAlterarRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLiberaCamposAlterarRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoLiberaCamposAlterarRevistas1ActionPerformed

    private void jTabbedPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void campoRegistroRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoRegistroRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoRegistroRegistroActionPerformed

    private void campoTituloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTituloKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Teste");
        }
    }//GEN-LAST:event_campoTituloKeyPressed

    private void campoTituloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoTituloMouseClicked
        System.out.println("Teste");
    }//GEN-LAST:event_campoTituloMouseClicked

    private void campoRegistroRegistro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoRegistroRegistro2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoRegistroRegistro2ActionPerformed

    private void campoDataRegistro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDataRegistro2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDataRegistro2ActionPerformed

    private void ComboBoxAreaRegistro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAreaRegistro2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAreaRegistro2ActionPerformed

    private void BotaoBuscaAlterarOuRemoverEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoBuscaAlterarOuRemoverEditoraActionPerformed
        ListaBuscaEditora = null;
        Editora editora = new Editora();
        EditoraDAO editoraDAO = new EditoraDAO();
        TravaTudoEspAlterarOuRemover();

        try {
            if (!BuscaTituloMenuEditora.getText().isEmpty()) {
                editora.setNome(BuscaTituloMenuEditora.getText());
            }

            ListaBuscaEditora = editoraDAO.ListaBuscaEditora(editora);

            ConsultaAlterarEditora();
        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Algo de errado ocorreu" + E.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(E.getMessage());

        }
    }//GEN-LAST:event_BotaoBuscaAlterarOuRemoverEditoraActionPerformed

    private void BotaoAlterarEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarEditoraActionPerformed
        if (AlteraNomeMenuEditora.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma editora!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
        if (JOptionPane.showConfirmDialog(null, "Essa editora será alterada. Desaja continuar?", "pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

            try {
                Editora editora = new Editora();
                editora.setId(Integer.parseInt(AlteraIDMenuEditora.getText()));
                editora.setNome(AlteraNomeMenuEditora.getText());

                EditoraDAO editoraDAO = new EditoraDAO();
                editoraDAO.AlterarEditora(editora);

                try {
                    atualizarConsultaEditora();

                    LimpaCamposEditoraAlterarOuRemover();
                    TravaTudoEditoraAlterarOuRemover();
                    TravaTudoEditoraAlterarOuRemover();

                    BotaoLiberarAcoesEditora.setEnabled(true);

                    //ClearComboBoxEspecificacao();
                    //ComboboxEspecificacao();
                    //LimpaCombos();

                    JOptionPane.showMessageDialog(null, "Editora alterada com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Náo foi possível alterar essa especificação! Erro:" + e.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoAlterarEditoraActionPerformed

    private void BotaoRemoverEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoRemoverEditoraActionPerformed
        if (AlteraNomeMenuEditora.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma editora!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
        }

        if (JOptionPane.showConfirmDialog(null, "Essa especificação será Editora. Desaja continuar?", "Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Editora editora = new Editora();
                editora.setId(Integer.parseInt(AlteraIDMenuEditora.getText()));
                EditoraDAO editoraDAO = new EditoraDAO();
                editoraDAO.RemoverEditora(editora);

                try {
                    atualizarConsultaEditora();

                    LimpaCamposEditoraAlterarOuRemover();
                    TravaTudoEditoraAlterarOuRemover();

                    //ClearComboBoxEditora();
                    //ComboboxEspecificacao();
                    //LimpaCombos();
                    JOptionPane.showMessageDialog(null, "Editora removida com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algo de errado ocorreu! Erro: " + ex.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(ex.getMessage());
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Náo foi possível remover essa editora! Erro:" + e.getMessage(), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_BotaoRemoverEditoraActionPerformed

    private void BotaoResetaPesquisaAlterarOuRemoverEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoResetaPesquisaAlterarOuRemoverEditoraActionPerformed
        atualizarConsultaEditora();
        DestravaCamposConsultaEditoraAlterarOuRemover();

        LimpaCamposConsultaEditoraAlterarOuRemover();
        LimpaCamposEditoraAlterarOuRemover();
        TravaTudoEditoraAlterarOuRemover();
    }//GEN-LAST:event_BotaoResetaPesquisaAlterarOuRemoverEditoraActionPerformed

    private void BotaoLimpaCamposAlterarEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaCamposAlterarEditoraActionPerformed
        LimpaCamposEditoraAlterarOuRemover();
        TravaTudoEditoraAlterarOuRemover();
        BotaoLiberarAcoesEditora.setEnabled(false);
    }//GEN-LAST:event_BotaoLimpaCamposAlterarEditoraActionPerformed

    private void BotaoLimpaAlterarOuRemoverEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimpaAlterarOuRemoverEditoraActionPerformed
        LimpaCamposConsultaEditoraAlterarOuRemover();
        TravaTudoEditoraAlterarOuRemover();
        ResetaTabelaAlterarOuRemoverEditora();
    }//GEN-LAST:event_BotaoLimpaAlterarOuRemoverEditoraActionPerformed

    private void BotaoLiberarAcoesEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLiberarAcoesEditoraActionPerformed
        DestravaTudoEditoraAlterarOuRemover();
        BotaoLiberarAcoesEditora.setEnabled(false);
    }//GEN-LAST:event_BotaoLiberarAcoesEditoraActionPerformed

    private void TabelaAlterarOuRemoverEditoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaAlterarOuRemoverEditoraMouseClicked
        TabelaAlterarOuRemoverEditora.getTableHeader().setReorderingAllowed(false);
        AlteraIDMenuEditora.setText(TabelaAlterarOuRemoverEditora.getValueAt(TabelaAlterarOuRemoverEditora.getSelectedRow(), 0).toString());
        AlteraNomeMenuEditora.setText(TabelaAlterarOuRemoverEditora.getValueAt(TabelaAlterarOuRemoverEditora.getSelectedRow(), 1).toString());

        TravaTudoEditoraAlterarOuRemover();
        BotaoLiberarAcoesEditora.setEnabled(true);
    }//GEN-LAST:event_TabelaAlterarOuRemoverEditoraMouseClicked

    private void ComboBoxEditoraRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEditoraRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEditoraRegistroActionPerformed

    private void ComboBoxEditoraRegistro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEditoraRegistro1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEditoraRegistro1ActionPerformed

    private void ComboBoxEditoraRegistro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEditoraRegistro2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEditoraRegistro2ActionPerformed
//-------------------------fim das ações de revista ------------------------------------//

    public void setLblColor(JLabel lbl) {
        lbl.setBackground(new Color(150, 150, 150));
    }

    public void ResetColor(JLabel lbl) {
        lbl.setBackground(new Color(37, 103, 125));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AlteraAnoMenuRevistas;
    private javax.swing.JComboBox<String> AlteraAreaMenuRevistas;
    private javax.swing.JComboBox<String> AlteraEspecificacaoMenuRevistas;
    private javax.swing.JTextField AlteraIDMenuArea;
    private javax.swing.JTextField AlteraIDMenuEditora;
    private javax.swing.JTextField AlteraIDMenuEspecificacao;
    private javax.swing.JTextField AlteraNomeMenuArea;
    private javax.swing.JTextField AlteraNomeMenuEditora;
    private javax.swing.JTextField AlteraNomeMenuEspecificacao;
    private javax.swing.JComboBox<String> AlteraOrigemMenuRevistas;
    private javax.swing.JTextField AlteraQuantidadeMenuRevistas;
    private javax.swing.JTextField AlteraTituloMenuRevistas;
    private javax.swing.JButton BotaoAdicionarNovaRevista;
    private javax.swing.JButton BotaoAdicionarNovaRevista1;
    private javax.swing.JButton BotaoAdicionarNovoTipo;
    private javax.swing.JButton BotaoAlterarArea;
    private javax.swing.JButton BotaoAlterarEditora;
    private javax.swing.JButton BotaoAlterarEsp;
    private javax.swing.JButton BotaoAlterarRevistas;
    private javax.swing.JButton BotaoAlterarRevistas1;
    private javax.swing.JButton BotaoBuscaAlterarOuRemoverArea;
    private javax.swing.JButton BotaoBuscaAlterarOuRemoverEditora;
    private javax.swing.JButton BotaoBuscaAlterarOuRemoverEsp;
    private javax.swing.JButton BotaoBuscaAlterarOuRemoverRevista;
    private javax.swing.JButton BotaoBuscaAlterarOuRemoverRevista1;
    private javax.swing.JButton BotaoBuscaConsultaRevista;
    private javax.swing.JButton BotaoBuscaConsultaRevista1;
    private javax.swing.JButton BotaoCancelaNovoTipo;
    private javax.swing.JButton BotaoCancelarNovaRevista2;
    private javax.swing.JButton BotaoCancelarNovaRevista3;
    private javax.swing.JButton BotaoConsultaArea;
    private javax.swing.JLabel BotaoFerramenta;
    private javax.swing.JButton BotaoLiberaCamposAlterarArea;
    private javax.swing.JButton BotaoLiberaCamposAlterarRevistas;
    private javax.swing.JButton BotaoLiberaCamposAlterarRevistas1;
    private javax.swing.JButton BotaoLiberarAcoesEditora;
    private javax.swing.JButton BotaoLiberarAcoesEsp;
    private javax.swing.JButton BotaoLimpaAlterarOuRemoverArea;
    private javax.swing.JButton BotaoLimpaAlterarOuRemoverEditora;
    private javax.swing.JButton BotaoLimpaAlterarOuRemoverEsp;
    private javax.swing.JButton BotaoLimpaBuscaRevista;
    private javax.swing.JButton BotaoLimpaBuscaRevista1;
    private javax.swing.JButton BotaoLimpaCamposAlterarArea;
    private javax.swing.JButton BotaoLimpaCamposAlterarEditora;
    private javax.swing.JButton BotaoLimpaCamposAlterarEsp;
    private javax.swing.JButton BotaoLimpaCamposAlterarRevistas;
    private javax.swing.JButton BotaoLimpaCamposAlterarRevistas1;
    private javax.swing.JButton BotaoLimpaConsulta;
    private javax.swing.JButton BotaoLimpaConsultaRevista;
    private javax.swing.JButton BotaoLimpaConsultaRevista1;
    private javax.swing.JButton BotaoNovaBuscaConsultaRevista;
    private javax.swing.JButton BotaoNovaBuscaConsultaRevista1;
    private javax.swing.JButton BotaoNovaConsultaArea;
    private javax.swing.JLabel BotaoRegistro;
    private javax.swing.JButton BotaoRemoverArea;
    private javax.swing.JButton BotaoRemoverEditora;
    private javax.swing.JButton BotaoRemoverEsp;
    private javax.swing.JButton BotaoRemoverRevistas;
    private javax.swing.JButton BotaoRemoverRevistas1;
    private javax.swing.JButton BotaoResetaPesquisaAlterarOuRemoverArea;
    private javax.swing.JButton BotaoResetaPesquisaAlterarOuRemoverEditora;
    private javax.swing.JButton BotaoResetaPesquisaAlterarOuRemoverEsp;
    private javax.swing.JButton BotaoResetaPesquisaAlterarOuRemoverRevista;
    private javax.swing.JButton BotaoResetaPesquisaAlterarOuRemoverRevista1;
    private javax.swing.JLabel BotaoRevista;
    private javax.swing.JButton BotaoSalvarNovaRevista;
    private javax.swing.JButton BotaoSalvarNovaRevista1;
    private javax.swing.JButton BotaoSalvarNovoTipo;
    private javax.swing.JComboBox<String> BuscaAreaMenuRevistas;
    private javax.swing.JComboBox<String> BuscaAreaMenuRevistas1;
    private javax.swing.JComboBox<String> BuscaEspecificacaoMenuRevistas;
    private javax.swing.JComboBox<String> BuscaEspecificacaoMenuRevistas1;
    private javax.swing.JTextField BuscaTituloMenuArea;
    private javax.swing.JTextField BuscaTituloMenuEditora;
    private javax.swing.JTextField BuscaTituloMenuEspecificacao;
    private javax.swing.JTextField BuscaTituloMenuRevistas;
    private javax.swing.JTextField BuscaTituloMenuRevistas1;
    private javax.swing.JTextField CampoNovoTituloTipo;
    private javax.swing.JComboBox<String> ComboBoxAreaNovaRevista;
    private javax.swing.JComboBox<String> ComboBoxAreaRegistro;
    private javax.swing.JComboBox<String> ComboBoxAreaRegistro2;
    private javax.swing.JComboBox<String> ComboBoxEditoraRegistro;
    private javax.swing.JComboBox<String> ComboBoxEditoraRegistro1;
    private javax.swing.JComboBox<String> ComboBoxEditoraRegistro2;
    private javax.swing.JComboBox<String> ComboBoxEspecificacaoNovaRevista;
    private javax.swing.JComboBox<String> ComboBoxNovoTipo;
    private javax.swing.JComboBox<String> ComboBoxOrigem;
    private javax.swing.JComboBox<String> ComboBoxOrigemRegistro;
    private javax.swing.JComboBox<String> ComboBoxOrigemRegistro2;
    private javax.swing.JComboBox<String> ConsultaAreaMenuRevistas;
    private javax.swing.JComboBox<String> ConsultaAreaMenuRevistas1;
    private javax.swing.JComboBox<String> ConsultaEspecificacaoMenuRevistas;
    private javax.swing.JComboBox<String> ConsultaEspecificacaoMenuRevistas1;
    private javax.swing.JTextField ConsultaNomeMenuTipo;
    private javax.swing.JComboBox<String> ConsultaTipoComboBox;
    private javax.swing.JTextField ConsultaTituloMenuRevistas;
    private javax.swing.JTextField ConsultaTituloMenuRevistas1;
    private javax.swing.JTextField IDTravadoAlteraRevista;
    private javax.swing.JPanel PaneFerramentas;
    private javax.swing.JPanel PaneGuiaAlteraRevista;
    private javax.swing.JPanel PaneGuiaAlteraRevista1;
    private javax.swing.JPanel PaneGuiaAlteraRevista2;
    private javax.swing.JPanel PaneGuiaAlteraRevista3;
    private javax.swing.JPanel PaneGuiaAlteraRevista4;
    private javax.swing.JPanel PaneGuiaConsulta;
    private javax.swing.JPanel PaneGuiaConsulta1;
    private javax.swing.JPanel PaneGuiaConsulta2;
    private javax.swing.JPanel PaneGuiaNovaRevista;
    private javax.swing.JPanel PaneGuiaNovaRevista1;
    private javax.swing.JPanel PaneGuiaNovaRevista2;
    private javax.swing.JPanel PaneMae;
    private javax.swing.JPanel PaneRegistro;
    private javax.swing.JPanel PaneRevista;
    private javax.swing.JPanel SideBoard;
    private javax.swing.JTable TabelaAlterarOuRemoverArea;
    private javax.swing.JTable TabelaAlterarOuRemoverEditora;
    private javax.swing.JTable TabelaAlterarOuRemoverEspecificacao;
    private javax.swing.JTable TabelaAlterarOuRemoverRevista;
    private javax.swing.JTable TabelaAlterarOuRemoverRevista1;
    private javax.swing.JTable TabelaConsultaRevista;
    private javax.swing.JTable TabelaConsultaRevista1;
    private javax.swing.JTable TabelaConsultaTipo;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoDataRegistro;
    private javax.swing.JTextField campoDataRegistro2;
    private javax.swing.JTextField campoLocalRegistro;
    private javax.swing.JTextField campoLocalRegistro2;
    private javax.swing.JTextField campoQuantidade;
    private javax.swing.JTextField campoRegistroRegistro;
    private javax.swing.JTextField campoRegistroRegistro2;
    private javax.swing.JTextField campoTitulo;
    private javax.swing.JTextField campoTituloRegistro;
    private javax.swing.JTextField campoTituloRegistro2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblData1;
    private javax.swing.JLabel lblData12;
    private javax.swing.JLabel lblData14;
    private javax.swing.JLabel lblData15;
    private javax.swing.JLabel lblData2;
    private javax.swing.JLabel lblData3;
    private javax.swing.JLabel lblData6;
    private javax.swing.JLabel lblData8;
    private javax.swing.JLabel lblEspecificacao;
    private javax.swing.JLabel lblEspecificacao1;
    private javax.swing.JLabel lblEspecificacao10;
    private javax.swing.JLabel lblEspecificacao11;
    private javax.swing.JLabel lblEspecificacao12;
    private javax.swing.JLabel lblEspecificacao2;
    private javax.swing.JLabel lblEspecificacao3;
    private javax.swing.JLabel lblEspecificacao4;
    private javax.swing.JLabel lblEspecificacao5;
    private javax.swing.JLabel lblEspecificacao6;
    private javax.swing.JLabel lblEspecificacao7;
    private javax.swing.JLabel lblEspecificacao8;
    private javax.swing.JLabel lblEspecificacao9;
    private javax.swing.JLabel lblIDAlteraArea;
    private javax.swing.JLabel lblIDAlteraEditora;
    private javax.swing.JLabel lblIDAlteraEsp;
    private javax.swing.JLabel lblIDAlteraRevista;
    private javax.swing.JLabel lblOrigem;
    private javax.swing.JLabel lblOrigem1;
    private javax.swing.JLabel lblOrigem10;
    private javax.swing.JLabel lblOrigem13;
    private javax.swing.JLabel lblOrigem2;
    private javax.swing.JLabel lblOrigem3;
    private javax.swing.JLabel lblOrigem4;
    private javax.swing.JLabel lblOrigem5;
    private javax.swing.JLabel lblOrigem6;
    private javax.swing.JLabel lblOrigem7;
    private javax.swing.JLabel lblOrigem9;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo10;
    private javax.swing.JLabel lblTitulo11;
    private javax.swing.JLabel lblTitulo12;
    private javax.swing.JLabel lblTitulo13;
    private javax.swing.JLabel lblTitulo15;
    private javax.swing.JLabel lblTitulo16;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTitulo4;
    private javax.swing.JLabel lblTitulo5;
    private javax.swing.JLabel lblTitulo6;
    private javax.swing.JLabel lblTitulo7;
    private javax.swing.JLabel lblTitulo8;
    private javax.swing.JLabel lblTitulo9;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Magazine_48px.png")));
    }

}
