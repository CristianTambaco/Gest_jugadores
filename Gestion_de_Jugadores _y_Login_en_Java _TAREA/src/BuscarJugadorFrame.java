import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class BuscarJugadorFrame extends JFrame {
    private JTextField txtNombre;
    private JButton btnBuscar;
    private JTextArea txtAreaResultado;

    public BuscarJugadorFrame() {
        setTitle("Buscar Jugador");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNombre = new JLabel("Nombre del Jugador:");

        txtNombre = new JTextField(15);
        btnBuscar = new JButton("Buscar");
        txtAreaResultado = new JTextArea(10, 30);
        txtAreaResultado.setEditable(false);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarJugador();
            }
        });

        JPanel panel = new JPanel();
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(btnBuscar);
        panel.add(new JScrollPane(txtAreaResultado));

        add(panel);
    }

    private void buscarJugador() {
        String nombre = txtNombre.getText();

        try (Connection conn = ConexionDB.conectar()) {
            String sql = "SELECT * FROM Jugadores WHERE nombre LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%"); // Buscar por nombre (parcial)
            ResultSet rs = ps.executeQuery();

            txtAreaResultado.setText(""); // Limpiar resultados previos

            if (rs.next()) {
                do {
                    String jugadorInfo = "ID: " + rs.getInt("id") + "\n" +
                            "Nombre: " + rs.getString("nombre") + "\n" +
                            "Posición: " + rs.getString("posicion") + "\n" +
                            "Equipo: " + rs.getString("equipo") + "\n" +
                            "Edad: " + rs.getInt("edad") + "\n\n";
                    txtAreaResultado.append(jugadorInfo);
                } while (rs.next());
            } else {
                txtAreaResultado.setText("No se encontraron jugadores con ese nombre.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            txtAreaResultado.setText("Error en la búsqueda.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BuscarJugadorFrame().setVisible(true));
    }
}
