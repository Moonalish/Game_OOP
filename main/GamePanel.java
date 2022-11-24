package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import java.awt.image.BufferedImage;
import utilz.File_img;


public class GamePanel extends JPanel{
        
        private Game game;
        private BufferedImage bgStart,bgPlayer,gameOver;
        private int height = 700;
        private int w = 728;
        
	public GamePanel(Game game){
            bgStart = File_img.GetSprites(File_img.BG_Start);
            bgPlayer = File_img.GetSprites(File_img.BG2_1);
            gameOver = File_img.GetSprites(File_img.BG_Game_Over);
            this.game = game;
            setPanelSize();
            addKeyListener(new KeyboardInputs(this));
	}

	private void setPanelSize() {
            Dimension size = new Dimension(w, height);//BG2
            setPreferredSize(size);
	}
        
        @Override
	public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(game.show_game==0)
            {
            g.drawImage(bgStart, 0, 0,w,height,null);
            }
            else if(game.show_game==1)
            {
                 g.drawImage(bgPlayer, 0, 0,w,height,null);
                 game.render(g);
            }
            else if(game.show_game==2)
            {
                g.drawImage(gameOver, 0, 0,w,height,null);
            }
            
	}
        
        public Game getGame(){
            return game;
        }
}
