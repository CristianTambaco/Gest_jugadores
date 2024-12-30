import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AgregarJugadorFrame extends JFrame {
    private JTextField txtNombre, txtPosicion, txtEquipo, txtEdad;
    private JButton btnAgregar;

    public AgregarJugadorFrame() {
        setTitle("Agregar Jugador");
        setSize(250, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblPosicion = new JLabel("Posición:");
        JLabel lblEquipo = new JLabel("Equipo:");
        JLabel lblEdad = new JLabel("Edad:");

        txtNombre = new JTextField(15);
        txtPosicion = new JTextField(15);
        txtEquipo = new JTextField(15);
        txtEdad = new JTextField(5);
        btnAgregar = new JButton("Agregar");

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarJugador();
            }
        });

        JPanel panel = new JPanel();
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblPosicion);
        panel.add(txtPosicion);
        panel.add(lblEquipo);
        panel.add(txtEquipo);
        panel.add(lblEdad);
        panel.add(txtEdad);
        panel.add(btnAgregar);

        add(panel);
    }

    private void agregarJugador() {
        String nombre = txtNombre.getText();
        String posicion = txtPosicion.getText();
        String equipo = txtEquipo.getText();
        int edad = Integer.parseInt(txtEdad.getText());

        try (Connection conn = ConexionDB.conectar()) {
            String sql = "INSERT INTO Jugadores (nombre, posicion, equipo, edad) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, posicion);
            ps.setString(3, equipo);
            ps.setInt(4, edad);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Jugador agregado con éxito");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgregarJugadorFrame().setVisible(true));
    }
}
