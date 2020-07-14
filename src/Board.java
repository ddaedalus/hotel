import java.util.*;
import java.io.*;

class Board {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Game Board
    char[][] board = new char[12][15];
    private char[][] show = new char[12][15];
    // Starter, Bank, Citizen
    Tuple S = new Tuple();
    Tuple B = new Tuple();
    Tuple C = new Tuple();
    private ArrayList<HotelCard> hotels;
	
    /*
     * 1: invalid
     * 0: valid
     */
    int[][] legal = new int[12][15];
    int[][] legal_entrances = new int[12][15];
    int[][] legal_entrances_pos = new int [12][15];
	
    // An array of all entrance objects on the board
    Entrance[][] entrances = new Entrance[12][15];

    public void readBoard(String board_txt) {
        try {     
            Scanner scanner = new Scanner(new FileReader(board_txt));
            int x = 0; 
            while (scanner.hasNextLine()) {
                String line;
                line = scanner.nextLine();
                String[] spl = line.split(",");

                int length = spl.length;
                for (int i=0; i<length; i++) {
                    board[x][i] = spl[i].charAt(0);
                    show[x][i] = board[x][i];
                    if (board[x][i] == 'E' || board[x][i] == 'e' || board[x][i] == 'H') {
                        legal_entrances_pos[x][i] = 1;
                    }
                    else legal_entrances_pos[x][i] = 0;
                }

                for (int y=0; y<15; y++) {
                    if (spl[y].equals("S")) {
                        S.a = x;
                        S.b = y;
                    }
                    else if (spl[y].equals("B")) {
                        B.a = x;
                        B.b = y;
                    }
                    else if (spl[y].equals("C")) {
                        C.a = x;
                        C.b = y;
                    }
                }
                x++;
            }
        } catch(FileNotFoundException e) {
            System.err.println("Exception is: " + e.toString());
        }	
    }

    // Returns the final position of player after he throws a dice 
    public int setLegal(int prevx, int prevy, int x, int y) {

        if (legal[x][y] == 0) {
            legal[x][y] = 1;
            return 0;
		}
        else if (legal[x][y] == 1) 
            return 1;
		return -1;
    }            

    public ArrayList<HotelCard> getHotels() {
        return hotels;
    } 

    public void setHotels(ArrayList<HotelCard> htl) {
        hotels = htl;
    }

    public char[][] getShow() {
        return show;
    }

    public void setShow(Tuple t, char c) {
        show[t.a][t.b] = c;
        return;
    } 

    public void showBoard() {
        System.out.println("Board Game:");
        for (int x=0; x<12; x++) {
            for (int y=0; y<15; y++) {
                if (show[x][y] == 'y') 
                    System.out.print(ANSI_YELLOW + "\033[3m" + show[x][y] + "\033[0m" + ANSI_RESET + "   ");
                else if (show[x][y] == 'g')
                    System.out.print(ANSI_GREEN + "\033[3m" + show[x][y] + "\033[0m" + ANSI_RESET + "   "); 
                else if (show[x][y] == 'b')
                    System.out.print(ANSI_BLUE + "\033[3m" + show[x][y] + "\033[0m" + ANSI_RESET + "   "); 
                else if (show[x][y] == 'C' || show[x][y] == 'B' || show[x][y] == 'H' 
                || show[x][y] == 'e' || show[x][y] == 'E' || show[x][y] == 'S')
                    System.out.print(ANSI_RED + show[x][y] + ANSI_RESET +  "   "); 
                else 
                    System.out.print(show[x][y] +  "   ");
            }
            System.out.println();
        }

        return;
    }
}
