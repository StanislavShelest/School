package shelest.temperature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temperature implements ActionListener {
    private JTextField numberInput;
    private JTextField numberOutput;

    JButton celsiusInput;
    JButton celsiusOutput;


    private Temperature() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        int widthFrame = 600;
        int heightFrame = 170;
        frame.setSize(widthFrame, heightFrame);

        frame.setTitle("Перевод температур");
        frame.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        frame.setLocation((width - widthFrame) / 2, (height - heightFrame) / 2);

        this.addComponentsToFrame(frame);

        frame.setVisible(true);
    }

    private void addComponentsToFrame(JFrame frame) {
        GridBagConstraints accommodation = new GridBagConstraints();

        celsiusInput = new JButton("Цельсия");
        celsiusInput.addActionListener(this);
        accommodation.fill = GridBagConstraints.NORTH;
        accommodation.gridx = 0;
        accommodation.gridy = 0;
        frame.add(celsiusInput, accommodation);

        JButton fahrenheitInput = new JButton("Фаренгейт");
        fahrenheitInput.addActionListener(this);
        accommodation.fill = GridBagConstraints.NORTH;
        accommodation.gridx = 1;
        accommodation.gridy = 0;
        frame.add(fahrenheitInput, accommodation);

        JButton kelvinInput = new JButton("Кельвин");
        kelvinInput.addActionListener(this);
        accommodation.fill = GridBagConstraints.NORTH;
        accommodation.insets = new Insets(0, 0, 0, 50);
        accommodation.gridx = 2;
        accommodation.gridy = 0;
        frame.add(kelvinInput, accommodation);


        celsiusOutput = new JButton("Цельсия");
        celsiusOutput.addActionListener(this);
        accommodation.fill = GridBagConstraints.NORTH;
        accommodation.insets = new Insets(0, 0, 0, 0);
        accommodation.gridx = 3;
        accommodation.gridy = 0;
        frame.add(celsiusOutput, accommodation);

        JButton fahrenheitOutput = new JButton("Фаренгейт");
        accommodation.fill = GridBagConstraints.NORTH;
        accommodation.gridx = 4;
        accommodation.gridy = 0;
        frame.add(fahrenheitOutput, accommodation);

        JButton kelvinOutput = new JButton("Кельвин");
        accommodation.fill = GridBagConstraints.NORTH;
        accommodation.gridx = 5;
        accommodation.gridy = 0;
        frame.add(kelvinOutput, accommodation);


        numberInput = new JTextField("Введите температуру");
        accommodation.fill = GridBagConstraints.HORIZONTAL;
        accommodation.ipady = 10;
        accommodation.gridx = 0;
        accommodation.gridy = 1;
        accommodation.gridwidth = 3;
        accommodation.insets = new Insets(20, 0, 0, 50);
        frame.add(numberInput, accommodation);

        numberOutput = new JTextField("Переведенная температура");
        accommodation.fill = GridBagConstraints.HORIZONTAL;
        accommodation.ipady = 10;
        accommodation.gridx = 3;
        accommodation.gridy = 1;
        accommodation.gridwidth = 3;
        accommodation.insets = new Insets(20, 0, 0, 0);
        frame.add(numberOutput, accommodation);


        JButton translate = new JButton("Перевести");
        accommodation.fill = GridBagConstraints.HORIZONTAL;
        accommodation.gridx = 0;
        accommodation.gridy = 3;
        accommodation.gridwidth = 6;
        frame.add(translate, accommodation);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {

        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Temperature();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(celsiusInput)){
            numberInput.setText("Вход");
        }
        if (e.getSource().equals(celsiusOutput)){
            numberInput.setText("выход");
        }


    }
}
