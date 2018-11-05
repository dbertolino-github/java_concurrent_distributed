package skeletonPaackage;

import java.util.Random;

/**
 * Created by ignazio on 4/3/16.
 */
public class BattleShipBoard {
    public static final int BOARD_SIZE = 5;
    public static final int NUM_SHIPS = 3;
    private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    private int[][] ships = new int[NUM_SHIPS][2];
    private int attempts= 0;
    private int shotHit = 0;
    @SuppressWarnings("unused")
	private String name;
    @SuppressWarnings("unused")
	private String surname;

    public BattleShipBoard() {
        initBoard();
        initShips();
    }

    /**
     * The board is initialized with values ​​'-1'.
     * -1 means: No shot was given in that block.
     */
    private void initBoard() {
        for (int row = 0; row < BOARD_SIZE; row++)
            for (int column = 0; column < BOARD_SIZE; column++)
                board[row][column] = -1;
    }

    /**
     * this method randomly select 3 pairs of integers numbers,
     * which are the location of the 3 ships
     */
    private void initShips() {
        Random random = new Random();

        for (int ship = 0; ship < NUM_SHIPS; ship++) {
            ships[ship][0] = random.nextInt(BOARD_SIZE);
            ships[ship][1] = random.nextInt(BOARD_SIZE);

            //let's check if that shot was already tried
            //if it was, just finish the do...while when a new pair was randomly selected
            for (int last = 0; last < ship; last++) {
                while ((ships[ship][0] == ships[last][0]) && (ships[ship][1] == ships[last][1])) {
                    ships[ship][0] = random.nextInt(BOARD_SIZE);
                    ships[ship][1] = random.nextInt(BOARD_SIZE);
                }
            }

        }
    }


    /**
     * Legend pro user:
     * ~: Water in the block. Still not been shot.
     * *: Shot fired, there's nothing there.
     * X: shot fired, there was a ship.
     */
    public String showBoard(){
        StringBuilder sb = new StringBuilder();
        sb.append("\t1 \t2 \t3 \t4 \t5\n");

        for(int row=0 ; row < BOARD_SIZE ; row++ ){
            sb.append((row + 1) + "");
            for(int column=0 ; column < BOARD_SIZE ; column++ ){
                if(board[row][column]==-1){
                    sb.append("\t" + "~");
                }else if(board[row][column]==0){
                    sb.append("\t" + "*");
                }else if(board[row][column]==1){
                    sb.append("\t" + "X");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String hit(int[] shoot){

        String hintStr = "";
        board[shoot[0]][shoot[1]]=0;
        attempts++;
        for(int ship=0 ; ship<ships.length ; ship++){
            if( shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
                hintStr += String.format("You hit a ship located in (%d,%d)\n", shoot[0] + 1, shoot[1] + 1);
                shotHit++;
                board[shoot[0]][shoot[1]]=1;
                if (shotHit==NUM_SHIPS){
                    hintStr += String.format("\nBattleship Java game finished!!!\n You hit %d ships in %d attempts", NUM_SHIPS, attempts);
                }
                break;
            }
        }
        hintStr += hint( shoot);
        return hintStr;
    }

    private String hint(int[] shoot){
        int row=0, column=0;

        for(int line=0 ; line < ships.length ; line++){
            if(ships[line][0]==shoot[0])
                row++;
            if(ships[line][1]==shoot[1])
                column++;
        }

        String result = String.format("\nHint %d: \nRow %d -> %d ships\n" +
                "Column %d -> %d ships\n",attempts,shoot[0]+1,row,shoot[1]+1,column);
        return result;
    }

    public int getNumMissingShips() {
        return NUM_SHIPS - shotHit;
    }

    public double getScore() {
        return (100.0*shotHit)/attempts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

