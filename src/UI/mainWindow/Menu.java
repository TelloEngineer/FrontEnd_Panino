package UI.mainWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Analyzers.Classes.Dates.Token;
import Analyzers.Classes.Formats.StringAnalyzer;
import Analyzers.Classes.Grammars.GrammarV1_0;
import Analyzers.Interface.Analyzer;
import Analyzers.Interface.Grammar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener {
    private JTextArea entradaField;
    private JButton analizarButton;
    private JTable tabla;
    private DefaultTableModel tablaModel;

    public Menu() {
        setTitle("Analizador Léxico");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel principal con GridBagLayout
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        // Panel de captura en la esquina superior izquierda
        JPanel panelCaptura = new JPanel(new BorderLayout());
        entradaField = new JTextArea(10, 30);
        entradaField.setLineWrap(true);
        entradaField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(entradaField);
        panelCaptura.add(scrollPane, BorderLayout.CENTER);

        // Botón "Analizar léxicamente" en el centro
        analizarButton = new JButton("Analizar léxicamente");
        analizarButton.addActionListener(this);

       

        // Tabla en la esquina inferior izquierda
        tablaModel = new DefaultTableModel();
        tablaModel.addColumn("Lexema");
        tablaModel.addColumn("Token");
        tablaModel.addColumn("Número");
        tabla = new JTable(tablaModel);
        JScrollPane scrollPaneTabla = new JScrollPane(tabla);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panelPrincipal.add(panelCaptura, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE; // Establecer el relleno a NONE
        panelPrincipal.add(analizarButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH; // Restaurar el relleno a BOTH

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        panelPrincipal.add(scrollPaneTabla, constraints);

        add(panelPrincipal);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		Analyzer analyzer;
		analyzer = new StringAnalyzer();
		GrammarV1_0 grammar = new GrammarV1_0();
        Grammar grammarToAnalize = grammar;

        if (e.getSource() == analizarButton) {
            String entrada = entradaField.getText();
			analyzer.isFromGrammar(entrada, grammarToAnalize);
            
            

            for (Token index : grammar.getLexycal()) {
                tablaModel.addRow(new Object[]{index.getRepresentation(), index.getTipo(), index.getId()});
            }
        }
    }
}
