
package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstants.GetSprirteAmount;
import static utilz.Constants.PlayerConstants.IDLE;
import static utilz.Constants.PlayerConstants.*;
import utilz.File_img;

public class Player extends Entity {

    private int playerAction = IDLE;
    private boolean left, right, up, down;
    private boolean moving = false;
    private final int playerSpeed = 2;
    public int hp = 15;

    public Player(int x, int y, int sX, int sY) {
        super(x, y, sX, sY);
        loadAnimations();

    }

    @Override
    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, scaleX, scaleY, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Gwen", Font.BOLD, 20));
        g.drawString("Can HIT : " + hp, 50, 30);
        if (hp <= 3) {
            g.setColor(Color.RED);
            g.setFont(new Font("Gwen", Font.BOLD, 20));
            g.drawString("EMERGENCY GET MORE SHIELD", 200, 150);
        }else{}
    }

    @Override
    public void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSprirteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    @Override
    public void loadAnimations() {
        BufferedImage img = File_img.GetSprites(File_img.PLAYER);

        animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage((i * 32) + 47, (j * 32) + 10, 32, 32);
    }

    private void setAnimation() {
        if (!moving) {
            playerAction = IDLE;
        }
    }

    private void updatePos() {
        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            playerAction = RUNNING_LEFT;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            playerAction = RUNNING_RIGHT;
            moving = true;
        }
        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }

        if (x > 680) {
            x = 680;
        } else if (x < 0) {
            x = 0;
        }

        if (y < 0) {
            y = 0;
        } else if (y > 630) {
            y = 630;
        }
    }

    public Rectangle getPlayerArea() {
        return new Rectangle((int) x, (int) y, scaleX, scaleY);
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void gotDMG(int dmg) {
        hp -= dmg;
    }

}
