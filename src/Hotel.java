import java.util.*;
import java.io.*;
import java.util.Random;

public class Hotel extends javax.swing.JFrame {

    static ArrayList<HotelCard> cards = new ArrayList<>();
    static Dice dice = new Dice();
    static BuildDice dice2 = new BuildDice();
    static Board board = new Board();
    static ArrayList<Player> order = new ArrayList<>();

    // A flag indicates that the game goes on, possible values (3, 2, 1)
    static int gameon;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Hotel() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        money1 = new javax.swing.JLabel();
        money2 = new javax.swing.JLabel();
        money3 = new javax.swing.JLabel();
        roll_res = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Medialab Hotel");

        jButton1.setText("Roll Dice");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Build Hotel");

        jButton3.setText("Buy Hotel");

        jButton4.setText("Buy Entrance");

        jButton5.setText("Request 1k MLS");

        money1.setText("Player1: 12000");

        money2.setText("Player2: 12000");

        money3.setText("Player3: 12000");

        roll_res.setText("Roll is:");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jMenu1.setText("Game");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Statistics");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(money1)
                .addGap(37, 37, 37)
                .addComponent(money2)
                .addGap(50, 50, 50)
                .addComponent(money3)
                .addGap(36, 36, 36)
                .addComponent(roll_res)
                .addContainerGap(371, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(money1)
                    .addComponent(money2)
                    .addComponent(money3)
                    .addComponent(roll_res))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(73, 73, 73))))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        roll_res.setText("Roll is:" + 3);
    }         

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hotel().setVisible(true);
            }
        });

        Scanner scanner;

        // Read Hotel Cards from .txt files
        for (int id=1; id<=8; id++) {
            String hotel_txt = "" + id + ".txt";
            try { 
                scanner = new Scanner(new FileReader(hotel_txt));
                String line;
                String name = null;
                int count = 0;
                int value = 0, sell = 0, entrance = 0;
                ArrayList<Tuple> card = new ArrayList<>();

                while (scanner.hasNextLine()) {
                    Tuple t;
                    line = scanner.nextLine();
                    String[] spl = line.split(",");
                    if (count == 0) 
                        name = spl[0];
                    else if (count == 1) {
                        value = Integer.parseInt(spl[0]);
                        sell = Integer.parseInt(spl[1]);
                    }
                    else if (count == 2) 
                        entrance = Integer.parseInt(spl[0]);
                    else {
                        t = new Tuple(Integer.parseInt(spl[0]), Integer.parseInt(spl[1]));
                        card.add(t);
                    }
                    count++;
                }
                int card_size = count - 3;
                Tuple pool = card.get(card_size - 1);
                HotelCard hotel_card = new HotelCard(name, id, value, sell, entrance, card, pool);
                cards.add(hotel_card);
                // System.out.println(hotel_card.getName());
            } catch(FileNotFoundException e) {
                System.err.println("Exception is: " + e.toString());
            }
        }

        // Read Hotel Game Board and add to it all game hotel cards
        board.readBoard("board.txt"); 
        board.setHotels(cards);

        /* 
         * Player1 ---> y
         * Player2 ---> g
         * Player3 ---> b
         */
        Player player1 = new Player(1, "yellow", 12000, board.S.a, board.S.b, 'y');
        Player player2 = new Player(2, "green", 12000, board.S.a, board.S.b, 'g');
        Player player3 = new Player(3, "blue", 12000, board.S.a, board.S.b, 'b');
        player1.setPrev(4, 11);
        player2.setPrev(4, 11);
        player3.setPrev(4, 11);

        // Starter Position at the beginning of the game
        board.legal[board.S.a][board.S.b] = 1;

        // Create a Tuple for each player's position and movement
        Tuple move = new Tuple();

        Scanner s = new Scanner(System.in);

        // Players rounds are set in a random order
        int result1 = 0, result2 = 0, result3 = 0;
        // Player1 rolls dice
        result1 = dice.roll(); 
        System.out.println(ANSI_YELLOW + "Player" + player1.getID() + " rolls dice: " + result1 + ANSI_RESET);  
        System.out.println(ANSI_YELLOW + "Player" + player1.getID() + " please press any button" + ANSI_RESET);
        String str = s.nextLine();
        System.out.println();
        //Player2 rolls dice
        result2 = dice.roll();
        System.out.println(ANSI_GREEN + "Player" + player2.getID() + " rolls dice: " + result2 + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Player" + player2.getID() + " please press any button" + ANSI_RESET);
        str = s.nextLine();
        System.out.println();
        //Player3 rolls dice
        result3 = dice.roll();
        System.out.println(ANSI_BLUE + "Player" + player3.getID() + " rolls dice: " + result3 + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Player" + player3.getID() + " please press any button" + ANSI_RESET);
        str = s.nextLine();
        System.out.println();
        // Create the order
        if (result1 >= result2 && result1 >= result3) {
            order.add(player1);
            if (result2 >= result3) {
                order.add(player2);
                order.add(player3);
            }
            else {
                order.add(player3);
                order.add(player2);
            }
        }
        else if (result2 >= result1 && result2 >= result3) {
            order.add(player2);
            if (result1 >= result3) {
                order.add(player1);
                order.add(player3);
            }
            else {
                order.add(player3);
                order.add(player1);
            }
        }
        else if (result3 >= result1 && result3 >= result2) {
            order.add(player3);
            if (result1 >= result2) {
                order.add(player1);
                order.add(player2);
            }
            else {
                order.add(player2);
                order.add(player1);
            }
        }       
        System.out.println("Players order is:");
        for (Player player : order) {
            System.out.println("Player" + player.getID());
        }

        System.out.println();

        // All three players at the beginning of the game
        int gameon = 3;

        int round = 0;
        while (gameon > 1) {
            // New Round
            round++;
            System.out.println("Round: " + round);
            for (Player player : order) {
                if (player.getStatus() == false) continue;
                System.out.println("Player" + player.getID() + " has " + player.getMLS() + " MLS");
            }
            System.out.println();

            board.showBoard();

            System.out.println();
            for (Player player : order) {
                
                if (player.getStatus() == false) continue;
                System.out.println(player.ANSI() + "Player" + player.getID() + " playes." + ANSI_RESET);
                move = player.move(board, dice, dice2);

                // Player bankrupts ---> loses
                if (!player.getStatus()) {
                    gameon--;
                }
            }
        }

        // End Game and Winner Announcement
        Player winner = null;
        for (Player player : order) {
            if (player.getStatus() == true) {
                winner = player;
                break;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(winner.ANSI() + "THE WINNER IS PLAYER" + winner.getID() + "!!!" + ANSI_RESET);
        
        return;
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel money1;
    private javax.swing.JLabel money2;
    private javax.swing.JLabel money3;
    private javax.swing.JLabel roll_res;
}
            

