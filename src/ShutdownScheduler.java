import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ShutdownScheduler extends JFrame {
    private final JRadioButton byDateTimeRadio = new JRadioButton("Por horário(data/hora);");
    private final JRadioButton byCountdownRadio = new JRadioButton("Por contagem(minuto).");
    private final JSpinner dateTimeSpinner;
    private final JSpinner minutesSpinner;
    private final JButton sheduleBtn = new JButton("Agendar");
    private final JButton cancelBtn = new JButton("Cancelar");
    private final JButton shutdownNowBtn = new JButton("Desligar agora");
    private final JLabel statusLabel = new JLabel("Status: pronto!");
    private final Timer uiTimer;
    private Long shuduledEpochMillis = null;

    public ShutdownScheduler() {
        super("Agendado de Desligamento");
        if (!isWindows()) {
            JOptionPane.showMessageDialog(this,
                    "Essr utilitário suporta somente windows no momento",
                    "Seu sistema não é suportado", JOptionPane.WARNING_MESSAGE);
        }
        //Spinner de date/hora
        Date initial = new Date(System.currentTimeMillis() + 10 * 60 * 1000L);
        SpinnerDateModel dateModel = new SpinnerDateModel(initial, null,
                null, Calendar.MINUTE);
        dateTimeSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateTimeSpinner,
                "dd/MM/yyyy HH:mm:ss");
        dateTimeSpinner.setEditor(dateEditor);

        //Spinner de contagem em minutos
        SpinnerNumberModel minutesModel = new SpinnerNumberModel(15, 1, 5256000, 1);
        minutesSpinner = new JSpinner(minutesModel);
        //Agrupar os "radios"
        ButtonGroup group = new ButtonGroup();
        group.add(byDateTimeRadio);
        group.add(byCountdownRadio);

        //Painel de modo
        JPanel modelPanel = new JPanel(new GridLayout(0, 1, 8, 8));
        modelPanel.setBorder(new TitledBorder("modo de agendamento"));
        modelPanel.add(byDateTimeRadio);
        modelPanel.add(byCountdownRadio);
        //painel de parâmetros
        JPanel paramsPanel = new JPanel(new GridBagLayout());
        paramsPanel.setBorder(new TitledBorder("Parâmetros"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.LINE_END;
        paramsPanel.add(new JLabel(""));
        paramsPanel.add(new JLabel("Data/Hora"),c);
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        paramsPanel.add(dateTimeSpinner, c);
        //Timer
        uiTimer = new Timer(1000, e-> System.out.println("Teste"));
        uiTimer.start();
        //configuração da janela

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(560, 240);
        setLocationRelativeTo(null);
    }

    private boolean isWindows() {
        String os = System.getProperty("os.name", "").toLowerCase();
        return os.contains("win");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShutdownScheduler().setVisible(true);
        });
    }
}