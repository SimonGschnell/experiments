package esfinge.experiments.plot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class BasicPanel extends JPanel {

    protected static final Color COLOR1 = new Color(55, 170, 200);
    protected static final Color COLOR2 = new Color(200, 80, 75);

    public BasicPanel() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    public abstract String getTitle();

    public abstract String getDescription();

    public JFrame showInFrame() {
        JFrame frame = new JFrame(getTitle());
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(getPreferredSize());
        frame.setVisible(true);
        return frame;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
