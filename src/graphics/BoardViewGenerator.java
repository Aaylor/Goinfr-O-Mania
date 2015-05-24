package graphics;

import helpers.ExtMath;

public class BoardViewGenerator {

    private String BACKGROUND_PATH = "pictures/background/";
    private String SKY = BACKGROUND_PATH+"sora2.png";
    private String GRASS = BACKGROUND_PATH+"grass2.jpg";
    private String MAP = BACKGROUND_PATH+"map2.jpg";
    private String STP = BACKGROUND_PATH+"stp2.jpg";
    private String WOOD = BACKGROUND_PATH+"wood2.png";

    private final int ID_SKY = 0;
    private final int ID_GRASS = 1;
    private final int ID_MAP = 2;
    private final int ID_STP = 3;
    private final int ID_WOOD = 4;


    private Board board;

    public BoardViewGenerator(Board board){
        this.board = board;
    }

    public BoardView viewGeneration(){
        int n = ExtMath.getRandomBewteen(ID_SKY, ID_WOOD+1);
        switch(n){
            case ID_SKY:
                return new BoardView(board, SKY);
            case ID_GRASS:
                return new BoardView(board, GRASS);
            case ID_MAP:
                return new BoardView(board, MAP);
            case ID_STP:
                return new BoardView(board, STP);
            default:
                return new BoardView(board, WOOD);
        }
    }

}
