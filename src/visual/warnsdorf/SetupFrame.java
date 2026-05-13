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
        int size = 500;
        this.setTitle("Warnsdorf's Knight's Setup");
        this.setSize(size+10,size+30); this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon Img = new ImageIcon("knight.png");
        this.setIconImage(Img.getImage());
        this.getContentPane().setBackground(new Color(0xcccccc));
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        // Components setup
        // Position fields
        int offX, offY, distY, globWidth, globHeight;
        offX = 50; offY = 20; distY = 60; globWidth = 200; globHeight = 50;
        // Texts
        this.labels = new JLabel[6];
        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 0 : this.labels[i] = new JLabel("Width : "); break;
                case 1 : this.labels[i] = new JLabel("Length : "); break;
                case 2 : this.labels[i] = new JLabel("Starting X : "); break;
                case 3 : this.labels[i] = new JLabel("Starting Y : "); break;
                case 4 : this.labels[i] = new JLabel("Choice Type : "); break;
                case 5 : this.labels[i] = new JLabel("Delay (ms) : "); break;
                default: this.labels[i] = new JLabel("???"); break;
            }
            this.labels[i].setBounds(offX, offY + (i*distY), globWidth, globHeight);
            this.add(this.labels[i]);
        }

        // Number Fields
        this.spinners = new JSpinner[6];
        for (int i = 0; i < 4; i++) {
            this.spinners[i] = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
            if (i==0||i==1) {
                this.spinners[i].setValue(8);
            }
            this.spinners[i].getEditor().setEnabled(false);
            this.spinners[i].setBounds(size - globWidth - offX, offY + (i*distY), globWidth, globHeight);
            this.add(this.spinners[i]);
        }
        String[] choiceTypes= {"default", "random", "Pohl", "Roth"};
        this.spinners[4] = new JSpinner(new SpinnerListModel(choiceTypes));
        this.spinners[4].setBounds(size - globWidth - offX, offY + (4*distY), globWidth, globHeight);

        this.spinners[5] = new JSpinner(new SpinnerNumberModel(20, 0, 1000, 10));
        this.spinners[5].setBounds(size - globWidth - offX, offY + (5*distY), globWidth, globHeight);
        this.add(this.spinners[4]); this.add(this.spinners[5]);

        // Start Button
        this.playButton = new JButton("Start");
        this.playButton.setBounds(100, 400, 300, 50);
        this.playButton.addActionListener(this);
        this.add(playButton);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int n, m, x, y, c, d;
        n = (Integer) this.spinners[0].getValue();
        m = (Integer) this.spinners[1].getValue();
        x = Math.min((Integer) this.spinners[2].getValue(), n);
        y = Math.min((Integer) this.spinners[3].getValue(), m);
        c = switch ((String) this.spinners[4].getValue()) {
            case "default" -> 0;
            case "random" -> 1;
            case "Pohl" -> 2;
            case "Roth" -> 3;
            default -> 0;
        };
        d = (Integer) this.spinners[5].getValue();
        this.setVisible(false);
        this.mainBoard = new VisualBoard(n, m, x, y);
        this.mainBoard.startAnimation(c, d);
    }
}
