import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private GameObject player;
    private GameObject entity;

    public Panel(GameObject player,GameObject entity){
        this.player = player;
        this.entity = entity;
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(Dimensions.PANEL_WIDTH,Dimensions.PANEL_HEIGHT));

    }

    public void setEntity(GameObject entity) {
        this.entity = entity;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        entity.draw(g);
    }
}
