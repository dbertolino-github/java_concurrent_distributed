package skeletonPaackage;

import java.io.IOException;
import java.util.Random;

import proxyPackage.IServer;

/**
 * Created by ignazio on 4/3/16.
 */
public class RealServer implements IServer{
    private static final String NAME = "Pippo";
    private static final String SURNAME = "Caio";

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

    public RealServer() {
        initBoard();
        initShips();
    }

    @Override
    public String startNewGame(String nameSurname) throws IOException, IllegalArgumentException
    {
        initBoard();
        initShips();
        String[] sp = nameSurname.split("\\s");
        if (sp.length != 2)
            throw new IllegalArgumentException("Expected 3 elements. Found: " + nameSurname);
        this.name = sp[0];
        this.surname = sp[1];

        return NAME+" "+SURNAME;
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
    @Override
    public String getBoard(){
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


    @Override
    public String shoot(int r, int c) throws IOException {
        String hintStr = "";
        board[r][c]=0;
        attempts++;
        for(int ship=0 ; ship<ships.length ; ship++){
            if( r==ships[ship][0] && c==ships[ship][1]){
                hintStr += String.format("You hit a ship located in (%d,%d)\n", r + 1, c + 1);
                shotHit++;
                board[r][c]=1;
                if (shotHit==NUM_SHIPS){
                    hintStr += String.format("\nBattleship Java game finished!!!\n You hit %d ships in %d attempts", NUM_SHIPS, attempts);
                }
                break;
            }
        }
        hintStr += hint(r, c);
        return hintStr;
    }

    private String hint( int r, int c){
        int row=0, column=0;
        for(int line=0 ; line < ships.length ; line++){
            if(ships[line][0]==r)
                row++;
            if(ships[line][1]==c)
                column++;
        }

        String result = String.format("\nHint %d: \nRow %d -> %d ships\n" +
                "Column %d -> %d ships\n",attempts,r+1,row,c+1,column);
        return result;
    }

    @Override
    public int getNumMissing() {
        return NUM_SHIPS - shotHit;
    }

    @Override
    public double getScore() {
        return (100.0*shotHit)/attempts;
    }

    @Override
    public void close() throws IOException {
        System.out.println("Game Over");
    }


}
