/*
 * Ejercicio: Captura de tecla "C" en Java Swing
 *
 * Objetivo:
 * Implementar en Java Swing la captura de la tecla 'C' sobre un componente específico
 * y ejecutar una acción programada como respuesta al evento.
 *
 * Autor: [Shirley Peñarreta]
 * Fecha: [05/10/2025]
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CapturaTeclaC extends JFrame {

    private JList<String> lista;
    private DefaultListModel<String> modeloLista;
    private JButton boton;

    public CapturaTeclaC() {
        // Configuración de la ventana principal
        setTitle("Ejercicio: Captura de tecla 'C' en Java Swing");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana
        setLayout(new BorderLayout());

        // Crear modelo con algunos elementos de ejemplo
        modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Elemento 1");
        modeloLista.addElement("Elemento 2");
        modeloLista.addElement("Elemento 3");
        modeloLista.addElement("Elemento 4");

        // Crear la lista con el modelo
        lista = new JList<>(modeloLista);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar la lista dentro de un scroll
        JScrollPane scroll = new JScrollPane(lista);
        add(scroll, BorderLayout.CENTER);

        // Crear un botón de ejemplo
        boton = new JButton("Botón de prueba");
        add(boton, BorderLayout.SOUTH);

        // -------------------------------------
        // Captura de la tecla "C"
        // -------------------------------------
        // Se usa Key Bindings porque es más recomendable que KeyListener en Swing
        // La acción se ejecutará únicamente cuando la lista tenga el foco
        InputMap inputMap = lista.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = lista.getActionMap();

        // Asignar la tecla "C" a una acción llamada "accionC"
        inputMap.put(KeyStroke.getKeyStroke('C'), "accionC");

        actionMap.put("accionC", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lista.getSelectedIndex();

                if (index != -1) {
                    // Imprimir mensaje en consola
                    System.out.println("✅ Se presionó la tecla 'C' sobre: " + modeloLista.get(index));

                    // Alternar estado de selección (si está seleccionado, se deselecciona, y viceversa)
                    if (lista.isSelectedIndex(index)) {
                        lista.clearSelection();
                    } else {
                        lista.setSelectedIndex(index);
                    }
                } else {
                    System.out.println("⚠ Se presionó la tecla 'C' pero no hay ningún elemento seleccionado.");
                }
            }
        });
    }

    public static void main(String[] args) {
        // Crear la GUI dentro del hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            CapturaTeclaC ventana = new CapturaTeclaC();
            ventana.setVisible(true);
        });
    }
}
