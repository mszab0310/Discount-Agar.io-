import java.awt.*;
import java.util.Random;

public class GameObject {
    private int r;
    private int x;
    private int y;
    private Color color = Color.RED;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r += r;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public GameObject(){

        x = Dimensions.PANEL_WIDTH / 2;
        y = Dimensions.PANEL_HEIGHT / 2;
        Random random = new Random();
        r = 10 + random.nextInt(50);
    }

    public void randomizeColor(){
        Random random = new Random();
        x = random.nextInt(Dimensions.PANEL_WIDTH - (r*2));
        y = random.nextInt(Dimensions.PANEL_HEIGHT - (r*2));
        switch (random.nextInt(4)) {
            case 0 -> color = Color.YELLOW;
            case 1 -> color = Color.RED;
            case 2 -> color = Color.BLUE;
            case 3 -> color = Color.GREEN;
        }

    }

    private boolean xInRange(int x,int r){
        if(this.x >= x && this.x <= (x + r*2)){
            return  true;
        }
        return this.x <= x && this.x + this.r * 2 >= x;
    }

    private boolean yInRange(int y,int r){
        if(this.y >= y && this.y <= (y + r*2)){
            return  true;
        }
        return this.y <= y && this.y + this.r * 2 >= y;
    }

    public boolean touches(GameObject o ){
        return this.xInRange(o.getX(), o.getR()) && this.yInRange(o.getY(), o.getR());
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x,y,r*2,r*2);
    }

    public void move(int xVel, int yVel){
        System.out.println();
        if(x  >= 0 && x <= Dimensions.PANEL_WIDTH - (r*2)){
            if(x + xVel < 0 || x + xVel > Dimensions.PANEL_WIDTH - (r*2)){
                xVel = 0;
            }
            x += xVel;
        }
        if(y >= 0 && y  <= Dimensions.PANEL_HEIGHT - (r*2)){
            if(y + yVel < 0 || y + yVel > Dimensions.PANEL_HEIGHT - (r*2)){
                yVel = 0;
            }
            y += yVel;
        }
    }

}
