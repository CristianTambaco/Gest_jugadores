import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EliminarJugadorFrame extends JFrame {
    private JTextField txtNombre;
    private JButton btnEliminar;
    private JLabel lblMensaje;

    public EliminarJugadorFrame() {
        setTitle("Eliminar Jugador");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNombre = new JLabel("Nombre del Jugador:");

        txtNombre = new JTextField(15);
        btnEliminar = new JButton("Eliminar");
        lblMensaje = new JLabel("");

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarJugador();
            }
        });

        JPanel panel = new JPanel();
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(btnEliminar);
        panel.add(lblMensaje);

        add(panel);
    }

    private void eliminarJugador() {
        String nombre = txtNombre.getText();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Por favor ingrese un nombre.");
            return;
        }

        try (Connection conn = ConexionDB.conectar()) {
            // Primero, verificamos si el jugador existe
            String sqlCheck = "SELECT * FROM Jugadores WHERE nombre = ?";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setString(1, nombre);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                // Si el jugador existe, lo eliminamos
                String sqlDelete = "DELETE FROM Jugadores WHERE nombre = ?";
                PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
                psDelete.setString(1, nombre);
                int rowsAffected = psDelete.executeUpdate();

                if (rowsAffected > 0) {
                    lblMensaje.setText("Jugador eliminado con éxito.");
                } else {
                    lblMensaje.setText("Error al eliminar jugador.");
                }
            } else {
                lblMensaje.setText("No se encontró un jugador con ese nombre.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            lblMensaje.setText("Error al eliminar jugador.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EliminarJugadorFrame().setVisible(true));
    }
}
