import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardFrame extends JFrame {
    private JButton btnVerJugadores, btnAgregarJugador, btnBuscarJugador, btnEliminarJugador;

    public DashboardFrame() {
        setTitle("Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnVerJugadores = new JButton("Ver Jugadores");
        btnAgregarJugador = new JButton("Agregar Jugador");
        btnBuscarJugador = new JButton("Buscar Jugador");
        btnEliminarJugador = new JButton("Eliminar Jugador");

        btnVerJugadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VerJugadoresFrame().setVisible(true);
            }
        });

        btnAgregarJugador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AgregarJugadorFrame().setVisible(true);
            }
        });

        btnBuscarJugador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BuscarJugadorFrame().setVisible(true);
            }
        });

        btnEliminarJugador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EliminarJugadorFrame().setVisible(true);
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(btnVerJugadores);
        panel.add(btnAgregarJugador);
        panel.add(btnBuscarJugador);
        panel.add(btnEliminarJugador);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardFrame().setVisible(true));
    }
}
