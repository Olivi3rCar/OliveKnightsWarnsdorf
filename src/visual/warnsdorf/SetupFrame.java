package visual.warnsdorf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupFrame extends JFrame implements ActionListener {

    private JSpinner[] spinners;
    private JLabel[] labels;
    private JButton playButton;
    public VisualBoard mainBoard;

    public SetupFrame() {
        // Frame Setup
        this.setTitle("Warnsdorf's Knight's Setup");
        this.setSize(510,530); this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon Img = new ImageIcon("knight.png");
        this.setIconImage(Img.getImage());
        this.getContentPane().setBackground(new Color(0xcccccc));
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        // Components setup
        // Texts
        this.labels = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0 : this.labels[i] = new JLabel("Width : "); break;
                case 1 : this.labels[i] = new JLabel("Length : "); break;
                case 2 : this.labels[i] = new JLabel("Starting X : "); break;
                case 3 : this.labels[i] = new JLabel("Starting Y : "); break;
                case 4 : this.labels[i] = new JLabel("Choice Type : "); break;
                default: this.labels[i] = new JLabel("???"); break;
            }
            this.labels[i].setBounds(50, 20 + (i*60), 100, 50);
            this.add(this.labels[i]);
        }

        // Number Fields
        this.spinners = new JSpinner[5];
        for (int i = 0; i < 4; i++) {
            this.spinners[i] = new JSpinner(new SpinnerNumberModel(1, 1, 16, 1));
            if (i==0||i==1) {
                this.spinners[i].setValue(8);
            }
            this.spinners[i].getEditor().setEnabled(false);
            this.spinners[i].setBounds(250, 20 + (i*60), 100, 50);
            this.add(this.spinners[i]);
        }
        String[] choiceTypes= {"default", "random", "Pohl", "Roth"};
        this.spinners[4] = new JSpinner(new SpinnerListModel(choiceTypes));
        this.spinners[4].setBounds(250, 20 + (4*60), 100, 50);
        this.add(this.spinners[4]);

        // Start Button
        this.playButton = new JButton("Start");
        this.playButton.setBounds(100, 400, 300, 50);
        this.playButton.addActionListener(this);
        this.add(playButton);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int n, m, x, y, c;
        n = (Integer) this.spinners[0].getValue();
        m = (Integer) this.spinners[1].getValue();
        x = (Integer) this.spinners[2].getValue();
        y = (Integer) this.spinners[3].getValue();
        c = switch ((String) this.spinners[4].getValue()) {
            case "default" -> 0;
            case "random" -> 1;
            case "Pohl" -> 2;
            case "Roth" -> 3;
            default -> 0;
        };
        this.setVisible(false);
        this.mainBoard = new VisualBoard(n, m, x, y);
        this.mainBoard.startAnimation(c);
    }
}
