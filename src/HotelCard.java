import java.util.ArrayList;

class HotelCard {
    private String name;
    private Player belongsTo = null; // It doesn't belong to anyone initially
    private int id;
    private int value, sell;
    private int entrance; // entrance cost for this hotel
    private ArrayList<Tuple> card = new ArrayList<Tuple>(6);
    private Tuple pool;
    private int rank = -1; // ranks the building of the hotel (0-5max), initiallized -1 since it isn't built at all
    private boolean inUse = true;
    private boolean complete =  false;

    HotelCard(String Name, int ID, int Value, int Sell, int Entrance, ArrayList<Tuple> Card, Tuple Pool) {
		name = Name;
        id = ID;
        value = Value;
        sell = Sell;
        entrance = Entrance;
        card = Card;
        pool = Pool;
    }

    public boolean getComplete() {
    	return complete;
    }

    public void setComplete(boolean flag) {
    	complete = flag;
    }
	
    public Player getBelongsTo() {
    	return belongsTo;
    }

    public void setBelongsTo(Player pl) {
    	belongsTo = pl;
    }

    public int getRank() {
    	return rank;
    }

    public void incRank() {
    	rank++;
    }

    public boolean getInUse() {
    	return inUse;
    }

    public void setInUse(boolean flag) {
    	inUse = flag;
    }

    public String getName() {
    	return name;
    }

    public void setName(String Name) {
    	name = Name;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        id = ID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int Value) {
        value = Value;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int Sell) {
        sell = Sell;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int Entrance) {
        entrance = Entrance;
    }

    public Tuple getPool() {
        return pool;
    }
    
    public void setPool(Tuple Pool) {
        pool = Pool;
    }

    public void setCard(ArrayList<Tuple> Card) {
        card = Card;
    }

    public ArrayList<Tuple> getCard() {
    	return card;
    }

}

