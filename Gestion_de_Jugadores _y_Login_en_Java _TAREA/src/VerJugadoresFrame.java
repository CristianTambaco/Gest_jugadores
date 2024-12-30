import javax.swing.*;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class VerJugadoresFrame extends JFrame {
    private JTable tblJugadores;

    public VerJugadoresFrame() {
        setTitle("Ver Jugadores");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Nombre", "Posición", "Equipo", "Edad"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tblJugadores = new JTable(model);

        try (Connection conn = ConexionDB.conectar()) {
            String sql = "SELECT * FROM Jugadores";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("posicion"),
                        rs.getString("equipo"),
                        rs.getInt("edad")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(tblJugadores);
        add(scrollPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VerJugadoresFrame().setVisible(true));
    }
}
