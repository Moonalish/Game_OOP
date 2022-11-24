
package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import utilz.File_img;

public class Shield extends Entity {

    public boolean delay = true;
    public int counter = 0;
    int round = 0;
    private Random random;
    public int shield;

    public Shield(int sX, int sY) {
        super(0, -50, sX, sY);
        loadAnimations();
    }

    @Override
    public void loadAnimations() {
        BufferedImage img = File_img.GetSprites(File_img.Sheild);

        animations = new BufferedImage[6][6];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage((i * 32) + 50, (j * 32) + 8, 32, 32);
    }

    @Override
    public void update() {
        Delay();
        updateAnimationTick();
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(animations[shield][aniIndex], (int) x, (int) y, scaleX, scaleY, null);
    }

    @Override
    public void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 6) {
                aniIndex = 0;
            }
        }
    }

    public void Delay() {
        counter++;
        if (counter > 10*100 && delay == true) {
                random = new Random();
                x = random.nextInt(400);
                round = random.nextInt(4);
                if (round == 0) {
                    y = 420;
                } else if (round == 1) {
                    y = 320;
                }
                else if (round == 2) {
                    y = 610;
                }
                else if (round == 3) {
                    y = 530;
                }
                counter = 0;
            }
         else if (delay == false) {
            delay = true;
            x = 0;
            y = -50;
        }
    }

    public Rectangle getShieldArea() {
        return new Rectangle(x, y, scaleX, scaleY);
    }
}
