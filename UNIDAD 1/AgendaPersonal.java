import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaPersonal extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JSpinner fechaSpinner;
    private JSpinner horaSpinner;
    private JTextField descripcionField;

    public AgendaPersonal() {
        setTitle("Agenda Personal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Crear modelo de tabla
        String[] columnNames = {"Fecha (dd/MM/yyyy)", "Hora (HH:mm)", "Descripción"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        // Configurar spinner de fecha
        fechaSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor fechaEditor = new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy");
        fechaSpinner.setEditor(fechaEditor);
        
        // Configurar spinner de hora
        horaSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor horaEditor = new JSpinner.DateEditor(horaSpinner, "HH:mm");
        horaSpinner.setEditor(horaEditor);

        descripcionField = new JTextField();

        inputPanel.add(new JLabel("Fecha:"));
        inputPanel.add(fechaSpinner);
        inputPanel.add(new JLabel("Hora:"));
        inputPanel.add(horaSpinner);
        inputPanel.add(new JLabel("Descripción:"));
        inputPanel.add(descripcionField);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton agregarBtn = new JButton("Agregar");
        JButton eliminarBtn = new JButton("Eliminar seleccionado");
        JButton salirBtn = new JButton("Salir");

        buttonPanel.add(agregarBtn);
        buttonPanel.add(eliminarBtn);
        buttonPanel.add(salirBtn);

        // Configurar layout principal
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Listeners de botones
        agregarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEvento();
            }
        });

        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEvento();
            }
        });

        salirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void agregarEvento() {
        String descripcion = descripcionField.getText().trim();
        
        // Validar descripción
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "La descripción no puede estar vacía", 
                "Validación", 
                JOptionPane.WARNING_MESSAGE);
            descripcionField.requestFocus();
            return;
        }

        // Formatear fecha y hora
        SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
        
        Date fecha = (Date) fechaSpinner.getValue();
        Date hora = (Date) horaSpinner.getValue();
        
        String fechaStr = fechaFormat.format(fecha);
        String horaStr = horaFormat.format(hora);

        // Agregar a la tabla
        tableModel.addRow(new Object[]{fechaStr, horaStr, descripcion});
        
        // Limpiar campo y dar foco
        descripcionField.setText("");
        descripcionField.requestFocus();
    }

    private void eliminarEvento() {
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Selecciona un evento primero",
                "Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Eliminar el evento seleccionado?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgendaPersonal().setVisible(true);
            }
        });
    }
}
