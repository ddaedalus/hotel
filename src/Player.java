import java.util.*;
import java.io.*;
import java.lang.Exception;
import java.text.ParseException;


class Player {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private int id;
    private String color;
    private int mls;
    private int posX, posY, prevx, prevy;
    private ArrayList<HotelCard> hotel_list = new ArrayList<HotelCard>();
    private ArrayList<Entrance> entrances = new ArrayList<Entrance>();
    
    // r->1, g->2, b->3
    private char misc;

    /* Status
     * true: active
     * false: inactive
     */
    private boolean status = true;

    Player(int ID, String Color, int Money, int X, int Y, char c) {
        id = ID;
        color = Color;
        mls = Money;
        posX = X;
        posY = Y;
        misc = c;
    }   

    // Returns player's color
    public String ANSI() {
        if (misc == 'y') return ANSI_YELLOW;
        if (misc == 'b') return ANSI_BLUE;
        return ANSI_GREEN;
    }

    public char getMisc() {
        return misc;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean flag) {
        status = flag;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        id = ID;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "<<Player " + id + " - " + color + ">>";
    }

    public int getMLS() {
        return mls;
    }

    public void setMLS(int new_mls) {
        mls = new_mls;
    }

    public String wantsEntrance(String ss) {
        if (ss.equals("capital")) {
            System.out.println(ANSI() + "Player" + id + " has passed from Capital. Do you want to buy an entrance?" + ANSI_RESET);
            System.out.println(ANSI() + "If yes, type \"yes\", else \"no\"" + ANSI_RESET);
        }
        else if (ss.equals("E")) {
            System.out.println(ANSI() + "Player" + id + " is on \"E\". Do you want to buy an entrance?" + ANSI_RESET);
            System.out.println(ANSI() + "If yes, type \"yes\", else if you want to build/upgrade type \"no\"" + ANSI_RESET);
        }
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        if (str.equals("yes")) {
            return "yes";
        }
        if (str.equals("no")) return "no";    
        System.out.println(ANSI() + "Please type \"yes\" or \"no\" in order to buy an entrance or not:" + ANSI_RESET);
        return wantsEntrance("not again prints");      
    }

    public String wantsBuild() {
        System.out.println(ANSI() + "If yes, type \"yes\", else \"no\"" + ANSI_RESET);
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        if (str.equals("yes")) {
            return "yes";
        }
        if (str.equals("no")) return "no";    
        System.out.println(ANSI() + "Please type \"yes\" or \"no\" in order to build or not:" + ANSI_RESET);
        return wantsBuild();      
    }

    public String wantsBuy() {
        System.out.println(ANSI() + "If yes, type \"yes\", else \"no\"" + ANSI_RESET);
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        if (str.equals("yes")) {
            return "yes";
        }
        if (str.equals("no")) return "no";    
        System.out.println(ANSI() + "Please type \"yes\" or \"no\" in order to buy a hotel or not:" + ANSI_RESET);
        return wantsBuy();      
    }

    public void bank() {
        System.out.println(ANSI() + "Player" + id + " has passed from Bank. Does he want extra MLS?" + ANSI_RESET);
        System.out.println(ANSI() + "If yes, type \"yes\", else \"no\"" + ANSI_RESET);
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        if (str.equals("yes")) {
            mls += 1000;
            System.out.println(ANSI() + "Player" + id + " has now " + mls + " MLS." + ANSI_RESET);
            System.out.println();
            return;
        }
        if (str.equals("no")) return;    
        System.out.println(ANSI() + "Please type \"yes\" or \"no\"" + ANSI_RESET);
        bank();
    }

    public int getX() {
        return posX;
    }

    public void setX(int X) {
        posX = X;
    }

    public Tuple getPos() {
        Tuple pos = new Tuple(posX, posY);
        return pos;
    }

    public int getY() {
        return posY;
    }
    
    public void setY(int Y) {
        posY = Y;
    }

    public void bankrupt(Board board, Tuple pos) {
        setStatus(false);

        System.out.println(ANSI() + "Player" + id + " LOST (BANKRUPTED)" + ANSI_RESET);

        // Render all player's hotels out of the game
        for (HotelCard hc : hotel_list) 
            hc.setInUse(false);
        
        // Render all player's entrances out of the game
        for (Entrance entrance : entrances)
            entrance.setInUse(false);
    
        // Remove player from board
        board.setShow(pos, board.board[pos.a][pos.b]);         

        return;
    }

    public int isNum(char c) {
        int a = (int) c - 48;
        if (a >= 0 && a <= 9) return a;
        return -1;
    } 

    // Search a hotel to buy an entrance or build from your hotel card list
    public HotelCard searchHotel() {
        System.out.println(ANSI() + "Here is your hotel card list:" + ANSI_RESET);
        // Presentation of player's hotel card list
        for (HotelCard hc : hotel_list) {
            System.out.print(ANSI() + "Hotel Name: " + hc.getName() + ANSI_RESET);
            System.out.print(ANSI() + ", Hotel ID: " + hc.getID() + ANSI_RESET);
            System.out.println(ANSI() + ", Hotel Ranking: " + hc.getRank() + ANSI_RESET);
        }
        System.out.println(ANSI() + "Please type the id of the hotel you choose" + ANSI_RESET);
        boolean answer = false;
        while (answer == false) {
            Scanner s = new Scanner(System.in);
            String str = s.nextLine();
            int ID;
            try { 
                ID = Integer.parseInt(str);
                // Search for the hotel card with user's given ID 
                for (HotelCard hc : hotel_list) 
                    if (hc.getID() == ID) return hc;       
                    System.out.println(ANSI() + "Please type a valid Hotel ID from the above Hotels shown" + ANSI_RESET);           
            } catch (Exception e) {
                System.out.println(ANSI() + "Please type a valid ID" + ANSI_RESET);
            }
        }
        // Junk 
        return null;
    }

    // Search an adjacent hotel to buy  
    public HotelCard searchAdjacentHotels(Board board, Tuple res) {
        ArrayList<HotelCard> hotels = board.getHotels();
        ArrayList<HotelCard> adjacent_hotels = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        int x1, x2, y1, y2, x3, y3, x4, y4;

        // Presentation of player's adjacent hotels
        System.out.println(ANSI() + "Here are the available adjacent hotels:" + ANSI_RESET);
        // right
        x1 = res.a;
        y1 = res.b + 1;
        // up               
        x2 = res.a - 1;
        y2 = res.b;
        // left
        x3 = res.a;
        y3 = res.b - 1;   
        // down
        x4 = res.a + 1;
        y4 = res.b;
        
        if (isNum(board.board[x1][y1]) != -1) {
            ids.add(isNum(board.board[x1][y1]));
        }
        if (isNum(board.board[x2][y2]) != -1) {
            ids.add(isNum(board.board[x2][y2]));
        }
        if (isNum(board.board[x3][y3]) != -1) {
            ids.add(isNum(board.board[x3][y3]));
        }
        if (isNum(board.board[x4][y4]) != -1) {
            ids.add(isNum(board.board[x4][y4]));
        }
        for (int id : ids) {
            for (HotelCard hc : hotels) {
                if (hc.getID() == id) {
                    System.out.print(ANSI() + "Hotel Name: " + hc.getName() + ANSI_RESET);
                    System.out.print(ANSI() + ", Hotel ID: " + hc.getID() + ANSI_RESET);   
                    System.out.println(ANSI() + ", Hotel Ranking: " + hc.getRank() + ANSI_RESET);  
                    adjacent_hotels.add(hc);                 
                }
            }
        }

        // Choose one adjacent hotel
        System.out.println("Please type the id of the hotel you want to buy");
        boolean answer = false;
        while (answer == false) {
            Scanner s = new Scanner(System.in);
            String str = s.nextLine();
            int ID;
            try { 
                ID = Integer.parseInt(str);
                // Search for the hotel card with user's given ID 
                for (HotelCard hc : adjacent_hotels) 
                    if (hc.getID() == ID) return hc;              
                System.out.println(ANSI() + "Please type a valid Hotel ID from the above Hotels shown" + ANSI_RESET);
            } catch (Exception e) {
                System.out.println(ANSI() + "Please type a valid Hotel ID from the above Hotels shown" + ANSI_RESET);
            }
        }
        return null;
    }

    public void setPrev(int x, int y) {
        prevx = x;
        prevy = y;
    }

    // Player moves when he throws the dice --- returns his final position in a Tuple
    public Tuple move(Board board, Dice dice, BuildDice bdice) {
        // Throw Dice
        int result = dice.roll();
        System.out.println(ANSI() + "Player" + id + " rolls dice" + ANSI_RESET);
        System.out.println(ANSI() + "Player" + id + " please press any button" + ANSI_RESET);
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        System.out.println("Dice: " + result);
        System.out.println();

        // Render legal your previous position
        board.legal[posX][posY] = 0;     

        boolean capital = false, bank = false;
        Tuple res = new Tuple();
        Tuple player_pos = new Tuple(posX, posY);
        board.setShow(player_pos, board.board[posX][posY]);

        for (int i=0; i<result; i++) {
            int x1, x2, y1, y2, x3, y3, x4, y4;

            // right
            x1 = posX;
            y1 = posY + 1;
            // up               
            x2 = posX - 1;
            y2 = posY;
            // left
            x3 = posX;
            y3 = posY - 1;
            // down
            x4 = posX + 1;
            y4 = posY;
            
            char c1 = board.board[x1][y1];
            char c2 = board.board[x2][y2];
            char c3 = board.board[x3][y3];
            char c4 = board.board[x4][y4];

            if (board.board[posX][posY] == 'S') {
                res.a = 6;
                res.b = 11;                
            }
            else if ((c1 == 'B' || c1 == 'C' || c1 == 'H' || c1 == 'S' || c1 == 'E' || c1 == 'e') 
            && (prevx != x1 || prevy != y1)) {
                res.a = x1;
                res.b = y1;
            }
            else if ((c2 == 'B' || c2 == 'C' || c2 == 'H' || c2 == 'S' || c2 == 'E' || c2 == 'e') 
            && (prevx != x2 || prevy != y2)) {
                res.a = x2;
                res.b = y2;
            }
            else if ((c3 == 'B' || c3 == 'C' || c3 == 'H' || c3 == 'S' || c3 == 'E' || c3 == 'e') 
            && (prevx != x3 || prevy != y3)) {
                res.a = x3;
                res.b = y3;
            }
            else if ((c4 == 'B' || c4 == 'C' || c4 == 'H' || c4 == 'S' || c4 == 'E' || c4 == 'e') 
            && (prevx != x4 || prevy != y4)) {
                res.a = x4;
                res.b = y4;
            }

            prevx = posX;
            prevy = posY;
            // (res.a, res.b) is my next position
            posX = res.a;
            posY = res.b;

            // Check if player passes Bank  --->   + 1000 MLS
            if (posX == board.B.a && posY == board.B.b) 
                bank = true;
        
            // Check if player passes Capital ---> entrance ??
            if (posX == board.C.a && posY == board.C.b)
                capital = true;

            // if last round of dice result
            if (i == result - 1) {
                int legal = board.setLegal(prevx, prevy, posX, posY);
                if (legal == 0) {
                    res.a = posX;
                    res.b = posY;
                }
                // Someone else is on this position
                else if (legal == 1) {
                    boolean ans = false;
                    while (!ans) {
                        // right
                        x1 = posX;
                        y1 = posY + 1;
                        // up               
                        x2 = posX - 1;
                        y2 = posY;
                        // left
                        x3 = posX;
                        y3 = posY - 1;
                        // down
                        x4 = posX + 1;
                        y4 = posY;
            
                        c1 = board.board[x1][y1];
                        c2 = board.board[x2][y2];
                        c3 = board.board[x3][y3];
                        c4 = board.board[x4][y4];
                        
                        if (board.board[posX][posY] == 'S') {
                            res.a = 6;
                            res.b = 11;                
                        }
                        else if ((c1 == 'B' || c1 == 'C' || c1 == 'H' || c1 == 'S' || c1 == 'E' || c1 == 'e') 
                        && (prevx != x1 || prevy != y1)) {
                            res.a = x1;
                            res.b = y1;
                        }
                        else if ((c2 == 'B' || c2 == 'C' || c2 == 'H' || c2 == 'S' || c2 == 'E' || c2 == 'e') 
                        && (prevx != x2 || prevy != y2)) {
                            res.a = x2;
                            res.b = y2;
                        }
                        else if ((c3 == 'B' || c3 == 'C' || c3 == 'H' || c3 == 'S' || c3 == 'E' || c3 == 'e') 
                        && (prevx != x3 || prevy != y3)) {
                            res.a = x3;
                            res.b = y3;
                        }
                        else if ((c4 == 'B' || c4 == 'C' || c4 == 'H' || c4 == 'S' || c4 == 'E' || c4 == 'e') 
                        && (prevx != x4 || prevy != y4)) {
                            res.a = x4;
                            res.b = y4;
                        }

                        prevx = posX;
                        prevy = posY;
                        // (res.a, res.b) is my next position
                        posX = res.a;
                        posY = res.b;

                        legal = board.setLegal(prevx, prevy, posX, posY);
                        if (legal == 0) {
                            ans = true;
                        }
                        
                        // Check if player passes Bank  --->   + 1000 MLS
                        if (posX == board.B.a && posY == board.B.b) 
                            bank = true;
    
                        // Check if player passes Capital ---> entrance ??
                        if (posX == board.C.a && posY == board.C.b)
                            capital = true;                  
                   
                    } // endwhile

                } // endelseif(legal==1)

                // Check if player passes Bank  --->   + 1000 MLS
                if (posX == board.B.a && posY == board.B.b)
                    bank = true;
                // Check if player passes Capital ---> entrance ??
                if (posX == board.C.a && posY == board.C.b)
                    capital = true;

            } // endif(i==result-1)
   
        } // endfor

         /* ΔΙΝΩ ΠΡΟΤΕΡΑΙΟΤΗΤΑ ΠΡΩΤΑ ΣΤΟ ΝΑ ΠΑΙΡΝΩ ΧΡΗΜΑΤΑ ΑΠΟ ΤΗΝ ΤΡΑΠΕΖΑ
         * ΜΕΤΑ ΣΤΟ ΝΑ ΠΛΗΡΩΣΩ ΣΤΟΝ ΑΝΤΙΠΑΛΟ ΜΟΥ
         * ΜΕΤΑ ΣΤΟ ΝΑ ΜΠΟΡΩ ΝΑ ΑΓΟΡΑΣΩ ΕΙΣΟΔΟ Ή ΝΑ ΧΤΙΣΩ Ή ΝΑ ΑΓΟΡΑΣΩ ΞΕΝΟΔΟΧΕΙΟ
         */

        System.out.println(ANSI() + "Player" + id + " is moving to position (" + res.a + ", " + res.b + ") " + board.board[res.a][res.b] + "." + ANSI_RESET); 
        System.out.println();
        if (!hotel_list.isEmpty()) {
            System.out.println(ANSI() + "Player" + id + " hotels:" + ANSI_RESET);
            for (HotelCard hc : hotel_list) {
                System.out.println(ANSI() + hc.getName() + ANSI_RESET);
            }
        }

        // Find player's position on the show board
        board.setShow(res, getMisc());

        // Player has passed from Bank, if (bank == true)
        if (bank)
            bank();

        // Check if player is on a rival's entrance 
        pay(res, dice, board);
        // Check for bankrupt
        if (status == false) return null;

        /* Player has passed from Capital, if (capital == true)
         * Player moves to E  --> wants to buy an entrance or upgrade??
         */  
        if ((capital || board.board[res.a][res.b] == 'E' || board.board[res.a][res.b] == 'e') 
        && !hotel_list.isEmpty()) {
            String cap = null, build = "no";
            if (capital) cap = wantsEntrance("capital");
            else if (board.board[res.a][res.b] == 'E' || board.board[res.a][res.b] == 'e')  {
                cap = wantsEntrance("E");
                if (cap.equals("no")) {
                    System.out.println(ANSI() + "Want to build or upgrade a hotel?" + ANSI_RESET);
                    build = wantsBuild();
                }
            }
            // wantsEntrance() result
            if (cap.equals("yes")) {
                System.out.println(ANSI() + "Player" + id + " MLS is: " + mls + "." + ANSI_RESET);
                HotelCard cap_hotel = searchHotel();
                System.out.println(ANSI() + "Give the (line, column) of entrance position"+ ANSI_RESET);
                int line = -1, column = -1;
                boolean answer = false;
                System.out.println(ANSI() + "Give line" + ANSI_RESET);
                while (answer == false) {
                    s = new Scanner(System.in);
                    str = s.nextLine();
                    try {
                        line = Integer.parseInt(str);
                        answer = true;           
                    } catch (Exception e) {
                        System.out.println(ANSI() + "Give integer line" + ANSI_RESET);
                    }              
                }
                System.out.println(ANSI() + "Give column" + ANSI_RESET);
                answer = false;
                while (answer == false) {
                    s = new Scanner(System.in);
                    str = s.nextLine();
                    try {
                        column = Integer.parseInt(str);
                        answer = true;           
                    } catch (Exception e) {
                        System.out.println(ANSI() + "Give integer column" + ANSI_RESET);
                    }              
                }               
                Tuple pos = new Tuple(line, column);
                buyEntrance(cap_hotel, pos, board);
            }
            // wantsBuild() result
            else if (build.equals("yes")) {
                System.out.println(ANSI() + "Player" + id + " MLS is: " + mls + ANSI_RESET);
                HotelCard cap_hotel = searchHotel();
                build(cap_hotel, bdice);
            }
        }
        
        // Player is on "H"  --> Wants to buy??
        else if (board.board[res.a][res.b] == 'H') {
            String buy;
            System.out.println(ANSI() + "Player" + id + " MLS is: " + mls + ANSI_RESET);
            System.out.println(ANSI() + "Want to buy a hotel?" + ANSI_RESET);
            buy = wantsBuy();
            if (buy.equals("yes")) {  
                HotelCard buy_hotel = searchAdjacentHotels(board, res);       
                buyHotel(buy_hotel);
            }
        }

        // After all, player's movement reach to an end...
        return res;      
    }

    public void pay(Tuple pos, Dice dice, Board board) {
        // Check if player has been on a position that is an entrance
        if (board.legal_entrances[pos.a][pos.b] == 0 
            || board.entrances[pos.a][pos.b] == null 
            || board.entrances[pos.a][pos.b].getInUse() == false) return;
        
        Entrance entrance = board.entrances[pos.a][pos.b];
        
        // Check if entrance is player's entrance
        if (entrance.getPlayer().getID() == id) return;

        Player rival = entrance.getPlayer();
        HotelCard rival_hotel = entrance.getHotel();
        
        System.out.println(ANSI() + "Player" + id + " is on an entrance of a Player" + rival.getID()+ ANSI_RESET);

        // Roll Dice
        System.out.println(ANSI() + "Player" + id + " rolls dice for payment." + ANSI_RESET);
        System.out.println(ANSI() + "Player" + id + " please press any button" + ANSI_RESET);
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        int result = dice.roll();
        System.out.println(ANSI() + "Dice Result: " + result + ANSI_RESET);

        // Pay rival player
        int payment = rival_hotel.getCard().get(rival_hotel.getRank()).b * 6;

        // Deal falls through ----> bankrupt
        if (getMLS() <= payment) {
            payment -= getMLS();
            rival.setMLS(rival.getMLS() + payment);
            System.out.println(ANSI() + "Player" + id + " pays to Player" + rival.getID() + " " + payment + " MLS." + ANSI_RESET);
            System.out.println(ANSI() + "Player" + id + " bankrupts" + ANSI_RESET);
            System.out.println(ANSI() + "Player" + rival.getID() + " has now " + rival.getMLS() + " MLS." + ANSI_RESET);
            bankrupt(board, pos);
            return;
        }

        // Normal Payment
        rival.setMLS(rival.getMLS() + payment);
        setMLS(getMLS() - payment);
        System.out.println(ANSI() + "Player" + id + " pays to Player" + rival.getID() + " " + payment + " MLS." + ANSI_RESET);
        System.out.println(ANSI() + "Player" + id + " has now " + mls + " MLS." + ANSI_RESET);
        System.out.println(ANSI() + "Player" + rival.getID() + " has now " + rival.getMLS() + " MLS." + ANSI_RESET);
        return;
    }

    public void buyHotel(HotelCard hotel) {
		if (hotel.getBelongsTo() == this) {
			System.out.println(ANSI() + "Invalid: Player has already bought this hotel." + ANSI_RESET);
			return;
		}
        if (mls <= hotel.getValue()) {
            System.out.println(ANSI() + "Invalid: You have not such money to buy this hotel!" + ANSI_RESET);
            return;
        }

        if (hotel.getInUse() && hotel.getRank() > -1) {
            System.out.println(ANSI() + "Invalid: You can't buy this hotel! It belongs to another player." + ANSI_RESET);
            return;
        }

        if (hotel.getInUse() == false) {
            System.out.println(ANSI() + "Invalid: You can't buy this hotel! It is not in use anymore." + ANSI_RESET);
            return;
        }

        // Can buy a hotel that belongs to another player with rank = -1 (not built)
        if (hotel.getInUse() && hotel.getRank() == -1 && hotel.getBelongsTo() != null && hotel.getBelongsTo().getID() != id) {
            // Have the money required ??
            if (mls <= hotel.getSell()) {
                System.out.println(ANSI() + "Invalid: You have not such money to buy this hotel from the other player!" + ANSI_RESET);
                return;
            }
            // Pay for this hotel to the other player equal to the sell of the hotel 
            mls -= hotel.getSell();
            int others_mls = hotel.getBelongsTo().getMLS();
            hotel.getBelongsTo().setMLS(hotel.getSell() + others_mls);
            
            // Remove the hotel from other's player arraylist
            hotel.getBelongsTo().hotel_list.remove(hotel);
            int others_id = hotel.getBelongsTo().getID();

            // Add this hotel card to player's hotel_list
            hotel_list.add(hotel);
            hotel.setBelongsTo(this);

            System.out.println(ANSI() + "Player" + id + " succesfully bought hotel" + hotel.getID() + " " + hotel.getName() 
                    + " from Player" + others_id + "." + ANSI_RESET);
            return;
        }

        // Pay for this hotel, if reqirements are ok
        mls -= hotel.getValue();

        // Add this hotel card to player's hotel_list, if requirements are ok
        hotel_list.add(hotel);
        hotel.setBelongsTo(this);

		System.out.println(ANSI() + "Player" + id + " succesfully bought hotel" + hotel.getID() + " " + hotel.getName() + "." + ANSI_RESET);
        System.out.println();
        //System.out.println(hotel.getCard().get(1));
        return;
    }


	/*
	 * Οπως και στο αληθινο επιτραπεζιο παιρνω την παραδοχη οτι ο χρηστης επιλεγει ο ιδιος 
     * το που θελει να βαλει την εισοδο και οτι αυτη δεν επιλεγεται τυχαια απο το ιδιο παιχνιδι
	 */
    public void buyEntrance(HotelCard hotel, Tuple pos, Board board) {
        if (pos.a > 11 || pos.a < 0 || pos.b > 14 || pos.b < 0) {
            System.out.println("Invalid Entrance Position!");
            return;           
        }
        if (board.legal_entrances[pos.a][pos.b] == 1 || 
                (board.entrances[pos.a][pos.b] != null && board.entrances[pos.a][pos.b].getInUse() == false)
                || board.legal_entrances_pos[pos.a][pos.b] != 1) {
            System.out.println("Invalid: You can't buy entrance here!");
            return;
        }
            
        if (hotel.getEntrance() >= mls) {
            System.out.println("Invalid: You can't buy entrance here! You don't have enough money to buy!");
            return;
        }

        if (hotel.getBelongsTo().getID() != id) {
            System.out.println("Invalid: You can't buy entrance here! This hotel doesn't belong to you!");
            return;
        }

        if (hotel.getRank() == -1) {
            System.out.println("Invalid: You can't buy entrance here! Your hotel must be built first!");
            return;
        }       

        // I am ok to buy entrance here!
        // Pay for this entrance
        mls -= hotel.getEntrance();

        // Render entrance legal on board
        board.legal_entrances[pos.a][pos.b] = 1;
        Entrance entrance = new Entrance(pos, hotel, this);

        // Put entrance on board
        board.entrances[pos.a][pos.b] = entrance;

        // Add entrance to player's entrances
        entrances.add(entrance);

        // Change entrance appearance on the board
        board.setShow(pos, 'e');
        board.board[pos.a][pos.b] = 'e';

		System.out.println(ANSI() + "Player" + id + " succesfully bought entrance in " + pos.a + ", " + pos.b + " for " + hotel.getName() + "." + ANSI_RESET);
        System.out.println();
    
        return;
    }
    
    /*
     * Build / Upgrade a hotel
     * Παιρνω την παραδοχη οτι αν η κανονικη αρχικη τιμη που εχει να πληρωσει ξεπερνα τα mls του τοτε δεν τον αφηνει το παιχνιδι να ριξει 
     * ζαρι και ετσι δεν τον αφηνει να χτισει, μολονοτι υπαρχει πιθανοτητα να μην πληρωσει και τιποτα
     */
    public void build(HotelCard hotel, BuildDice dice) {
        System.out.println(ANSI() + "Player" + id + " wants to build/upgrade hotel: " + hotel.getName() + ANSI_RESET);

        if (hotel.getBelongsTo().getID() != id || hotel.getInUse() == false) {
            System.out.println("Invalid! This hotel doesn't belong to the player.");
            return;
        }
        if (hotel.getComplete()) {
            System.out.println("Invalid! This hotel is complete.");
            return;
        }
        if (hotel.getCard().get(hotel.getRank() + 1).a >= mls) {
            System.out.println("Invalid! Not enough money!");
            return;
        }

        int payment = 0;
        
        // Roll BuildDice
        System.out.println(ANSI() + "Player" + id + " please press any button" + ANSI_RESET);
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        String result = dice.roll();
        System.out.println(ANSI() + "Player" + id + " rolls build dice: " + result + ANSI_RESET);

        if (result.equals("K")) payment = hotel.getCard().get(hotel.getRank() + 1).a;
        else if (result.equals("H")) payment = 0;
        else if (result.equals("X")) {
            System.out.println("Invalid!");
            return;
        }
        else if (result.equals("2")) {
            payment = hotel.getCard().get(hotel.getRank() + 1).a * 2;
            if (payment >= mls) {
                System.out.println(ANSI() + "Invalid! Deal falls through." + ANSI_RESET);
                return;
            }
        }

        // Upgrade rank
        hotel.incRank();
        if (hotel.getRank() == hotel.getCard().size() - 1) 
            hotel.setComplete(true);

        // Pay for the building
        mls -= payment;
    
		System.out.println(ANSI() + "Player" + id + " built hotel" + hotel.getID() + " " + hotel.getName() + " with rank " + hotel.getRank() +
							" paying " + payment + ". Remaining money: " + mls + "." + ANSI_RESET);
        System.out.println();

        return;
    }

}
