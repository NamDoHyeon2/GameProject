package background;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class RoundPanel extends JPanel {
    private int arc;
    private Color backgroundColor;

    public RoundPanel(int arc, Color backgroundColor) {
        this.arc = arc;
        this.backgroundColor = backgroundColor;
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    @Override
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g.create();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 테두리 그리기 (검정색)
        graphics.setColor(Color.BLACK);
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arc, arc);

        // 배경 그리기 (흰색)
        graphics.setColor(backgroundColor);
        graphics.fillRoundRect(1, 1, width - 2, height - 2, arc, arc);

        graphics.dispose();
    }
}
