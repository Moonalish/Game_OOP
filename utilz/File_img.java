package utilz;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class File_img {
    public static final String PLAYER = "spacePlane.png";

    public static final String BG_Start = "SpaceX_Bg.jpg";
    public static final String BG2_1 = "spaceBg.jpg";
    public static final String BG_Game_Over = "Start_Bg.jpg";
    public static final String Meteor = "Meteor.png";
    public static final String Sheild = "Shield.png";
    
    public static BufferedImage GetSprites(String filename){
        BufferedImage img = null;
        InputStream is = File_img.class.getClassLoader().getResourceAsStream("image\\" + filename);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
    }