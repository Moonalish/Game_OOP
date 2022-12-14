package utilz;

public class Constants {
    
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
        
    }
    
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING_RIGHT = 1;
        public static final int RUNNING_LEFT = 2;
        public static final int get_Damege = 3;
        
        public static int GetSprirteAmount(int player_action){
            switch(player_action){
                case IDLE:
                    return 3;
                case RUNNING_RIGHT:
                    return 4;
                case RUNNING_LEFT:
                    return 4;               
                default:
                    return 1;
            }
        }
    }
}