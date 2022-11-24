
package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilz.File_img;
import java.util.Random;
public class Meteor extends Entity{
   
    private Random random;
    private int direction;
    private boolean dmg = true;
    private int Speed=4;
    int top_or_down=0;
    int left_or_right=0;
    
    public Meteor(int sX,int sY) {
        super(0, 0, sX, sY);
        loadAnimations();
    }
    
    

    @Override
    public void loadAnimations() {
        BufferedImage img = File_img.GetSprites(File_img.Meteor);

        animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                        animations[j][i] = img.getSubimage((i*32)+50, (j*32)+8, 32, 32);
    }

    @Override
    public void update() {
        meteorMove();
        updateAnimationTick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animations[direction][aniIndex],(int)x,(int)y,scaleX,scaleY,null);
    }

    @Override
    public void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 4){
                aniIndex = 0;
            }
        }
    }
    
    private void meteorMove(){
        if(direction == 0){
            y+=Speed;
            if(y>900){
                random = new Random();
                direction = random.nextInt(3); // 0 = ด้านบน 1 มาจากซ้าย 2 มาจากขวา
 
                x = random.nextInt(500);
                top_or_down = random.nextInt(2);
                left_or_right = random.nextInt(2);
                if(direction==0)
                {
                    x = random.nextInt(550);
                    y= 0;
                }
                else if(direction == 1)
                {   
                    x = 700;
                    y = 0;
                }
                else if(direction == 2 )
                {
                    x = 0;
                    y = 0;
                }
                if(top_or_down==0&&direction != 0)
                {
                    y = 0;
                }
                else if(top_or_down==1&&direction != 0)
                    
                {
                    y =30;
                }
                dmg = true;
            }
        }
       else if(direction == 1){
            x-=Speed;
            y+=Speed;
            if(x<-70){
                direction= 0;
                x = random.nextInt(550);
                y= 0;
               dmg = true;
            }
        }
       else if(direction == 2){
            x+=Speed;
            y+=Speed;
            if(x>900){
                direction= 0;
                x = random.nextInt(550);
                y= 0;
               dmg = true;
            }
        } 
    }
    public Rectangle getDamageArea(){
        return new Rectangle(x,y,scaleX,scaleY);
    }
    
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isDmg() {
        return dmg;
    }

    public void setCanDmg(boolean Dmg) {
        this.dmg = Dmg;
    }
    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int speed) {
        this.Speed = speed;
    }
}
