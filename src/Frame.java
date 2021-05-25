import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame {

    private JFrame frame;
    private Panel panel;
    private GameObject player;
    private GameObject entity;
    private int score;
    private JLabel label;
    private int gameTime;

    public Frame() {
        frame = new JFrame("Agar.io");
        player = new GameObject();
        entity = new GameObject();
        label = new JLabel();
        gameTime = 0;
        score = 0;
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.YELLOW);
        label.setText("SCORE : " + score);
        entity.randomizeColor();
        panel = new Panel(player, entity);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(label,BorderLayout.SOUTH);
        panel.setFocusable(true);
        keyListener();
        startMovement();
        frame.setSize(Dimensions.FRAME_WIDTH, Dimensions.FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void keyListener() {
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    player.move(0, -5);
                    panel.repaint();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player.move(0, 5);
                    panel.repaint();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.move(5, 0);
                    panel.repaint();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.move(-5, 0);
                    panel.repaint();
                }
            }

        });
    }

    private void startMovement(){
        Thread thread = new Thread(() -> {
           while (gameTime < 200){
               if(entity.getR() <= 0){
                   entity = new GameObject();
                   entity.randomizeColor();
                   panel.setEntity(entity);
                   panel.repaint();
               }
               entity.setR(-1);
               try {
                   Thread.sleep(300);
                   gameTime += 1;
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               if(player.touches(entity)){
                   if(entity.getColor() == player.getColor()){
                       player.setR(10);
                       score += 1;
                   }else{
                       player.setR(-5);
                       if(player.getR() <= 0){
                           gameTime = 200;
                       }
                       score -= 1;
                   }
                   label.setText("SCORE : " + score);
                   entity = new GameObject();
                   entity.randomizeColor();
                   panel.setEntity(entity);
                   panel.repaint();
               }
               panel.repaint();
           }
           label.setText("GAME OVER! YOUR SCORE : " + score);
        });
        thread.start();
    }

}
