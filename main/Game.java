package main;

import entities.Meteor;
import entities.Player;
import entities.Shield;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Game {

    int count = 0;
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread Thread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private Shield shield;
    private ArrayList<Meteor> meteor = new ArrayList<Meteor>();
    public int sec = 0;
    public int show_game = 0;
    public int  min= 0;
    public int  High_min= 0;
    public int High_sec = 0;
    public int s_sec = 0;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        player = new Player(265, 200, 60, 60);
        meteor.add(new Meteor(80, 80));
        meteor.add(new Meteor(60, 60));
        shield = new Shield(40, 40);// 40 40 size
    }

    private void startGameLoop() {

        playerThread.start();
        meteorThread.start();
        shieldThread.start();
    }

    public void render(Graphics g) {
        player.render(g);
        meteor.get(0).render(g);
        meteor.get(1).render(g);
        shield.render(g);
        g.setColor(Color.white);
        g.setFont(new Font("Gwen", Font.BOLD, 20));
        g.drawString("Time : " +min+"."+sec, 550, 30);
        g.setFont(new Font("Gwen", Font.BOLD, 20));
        g.drawString("High Time: " +High_min+"."+High_sec, 550, 60);
    }
    Thread playerThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    if (show_game == 1) {
                        player.update();

                        shield.update();
            
                        score();
                        PlayerGetDmg();
                        checkGetShield();
                        check_Hp();
                    }
                    gamePanel.repaint();
                    Thread.sleep(3);
                
                } catch (Exception e) {
                }
            }
        }
    });
    Thread meteorThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    if (show_game == 1) {
                        meteor.get(0).update();
                        meteor.get(1).update();
                    }
                    gamePanel.repaint();
                    Thread.sleep(2000);
                
                } catch (Exception e) {
                }
            }
        }
    });
    Thread shieldThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    if (show_game == 1) {
                        meteor.get(0).update();
                        meteor.get(1).update();
                    }
                    gamePanel.repaint();
                    Thread.sleep( 3);
                
                } catch (Exception e) {
                }
            }
        }
    });


    public Player getplayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public void score() { // เพิ่มความเร็
        count++;
        if (count > 2 * 60) {
            sec++;
            if (sec >= 60) {
                sec = 0;
                min += 1;
            }
            if (sec > 30) {
                meteor.get(0).setSpeed(6);
                meteor.get(1).setSpeed(6);
            } else if (min >= 1) {
                meteor.get(0).setSpeed(8);
                meteor.get(1).setSpeed(8);
            }
            count = 0;
        }
    }

    public void PlayerGetDmg() {
        if (player.getPlayerArea().intersects(meteor.get(0).getDamageArea()) && meteor.get(0).isDmg()  == true) {
            player.gotDMG(1);
            meteor.get(0).setCanDmg(false);
        }else if (player.getPlayerArea().intersects(meteor.get(1).getDamageArea()) && meteor.get(1).isDmg() ==true) {
            player.gotDMG(1);
            meteor.get(1).setCanDmg(false);
        }
    }

    public void checkGetShield() {
        if (player.getPlayerArea().intersects(shield.getShieldArea())) {
            player.hp = player.hp + 3;
            if (player.hp > 15) {
                player.hp = 15;
            }
            shield.delay = false;
        }
    }

    public void check_Hp() {
        if (player.hp <= 0) {
            if (High_min <= min) {
                High_min = min;
            }if (High_min <= min && High_sec <= sec) {
                High_sec =sec;
            }
            show_game = 2;
            player.hp = 15;
            sec = 0;
            min = 0;
            meteor.get(0).setSpeed(4);
            meteor.get(1).setSpeed(4);
            shield.delay = false;
        }
    }

}