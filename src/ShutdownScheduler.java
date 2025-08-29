import javax.swing.*;
import java.awt.*;
public class ShutdownScheduler extends JFrame {
    public ShutdownScheduler(){
        super("Agendado de Desligamento");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(560, 240);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new ShutdownScheduler().setVisible(true);
        });

    }}

