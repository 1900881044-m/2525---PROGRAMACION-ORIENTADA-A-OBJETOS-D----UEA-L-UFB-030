import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aplicación GUI básica para gestión de elementos
 * Permite agregar, mostrar y limpiar elementos de una lista
 * 
 * @author TuNombre
 * @version 1.0
 */
public class GestionElementosApp extends JFrame {
    // Componentes de la interfaz
    private JLabel tituloLabel;
    private JLabel instruccionLabel;
    private JTextField entradaTextField;
    private JButton agregarButton;
    private JButton limpiarButton;
    private JList<String> elementosList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private JLabel seleccionLabel;

    /**
     * Constructor que inicializa la interfaz gráfica
     */
    public GestionElementosApp() {
        // Configuración de la ventana principal
        super("Gestión de Elementos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Centrar en pantalla
        
        // Inicializar componentes
        inicializarComponentes();
        
        // Configurar layout y agregar componentes
        configurarLayout();
        
        // Configurar manejadores de eventos
        configurarEventos();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Etiquetas
        tituloLabel = new JLabel("Gestión de Elementos", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        instruccionLabel = new JLabel("Ingrese un elemento:");
        seleccionLabel = new JLabel("Seleccionado: Ninguno");
        
        // Campo de texto
        entradaTextField = new JTextField(20);
        
        // Botones
        agregarButton = new JButton("Agregar");
        limpiarButton = new JButton("Limpiar Todo");
        
        // Lista y modelo
        listModel = new DefaultListModel<>();
        elementosList = new JList<>(listModel);
        scrollPane = new JScrollPane(elementosList);
    }
    
    /**
     * Configura el layout y agrega los componentes a la ventana
     */
    private void configurarLayout() {
        // Panel superior con título
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(tituloLabel, BorderLayout.CENTER);
        
        // Panel de entrada de datos
        JPanel panelEntrada = new JPanel(new FlowLayout());
        panelEntrada.add(instruccionLabel);
        panelEntrada.add(entradaTextField);
        panelEntrada.add(agregarButton);
        panelEntrada.add(limpiarButton);
        
        // Panel de información de selección
        JPanel panelSeleccion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSeleccion.add(seleccionLabel);
        
        // Configurar layout principal
        setLayout(new BorderLayout(10, 10));
        add(panelSuperior, BorderLayout.NORTH);
        add(panelEntrada, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(panelSeleccion, BorderLayout.SOUTH);
        
        // Ajustar layout para mejor distribución
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelSeleccion.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Ajustar tamaño preferido del área de lista
        scrollPane.setPreferredSize(new Dimension(450, 200));
    }
    
    /**
     * Configura los manejadores de eventos para los componentes
     */
    private void configurarEventos() {
        // Evento para el botón Agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarElemento();
            }
        });
        
        // Evento para el botón Limpiar
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTodo();
            }
        });
        
        // Evento para la lista (selección de elementos)
        elementosList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    actualizarSeleccion();
                }
            }
        });
        
        // Evento para el campo de texto (agregar con Enter)
        entradaTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarElemento();
            }
        });
    }
    
    /**
     * Agrega un elemento a la lista después de validar que no esté vacío
     */
    private void agregarElemento() {
        String texto = entradaTextField.getText().trim();
        
        // Validar que no esté vacío
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No puede agregar un elemento vacío", 
                "Entrada inválida", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Agregar a la lista y limpiar campo
        listModel.addElement(texto);
        entradaTextField.setText("");
        entradaTextField.requestFocus(); // Mantener foco en el campo de texto
    }
    
    /**
     * Limpia todos los elementos de la lista
     */
    private void limpiarTodo() {
        // Confirmar antes de limpiar
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea eliminar todos los elementos?",
            "Confirmar limpieza",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            listModel.clear();
            seleccionLabel.setText("Seleccionado: Ninguno");
        }
    }
    
    /**
     * Actualiza la etiqueta con el elemento seleccionado
     */
    private void actualizarSeleccion() {
        String seleccionado = elementosList.getSelectedValue();
        if (seleccionado != null) {
            seleccionLabel.setText("Seleccionado: " + seleccionado);
        } else {
            seleccionLabel.setText("Seleccionado: Ninguno");
        }
    }
    
    /**
     * Método principal que inicia la aplicación
     */
    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionElementosApp().setVisible(true);
            }
        });
    }
}
