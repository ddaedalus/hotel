class Entrance {
    private Tuple position;
    private HotelCard hotel;
    private Player player;
    private boolean inUse = true;

    Entrance(Tuple t, HotelCard h, Player p) {
        position = t;
        hotel = h;
        player = p;
    }

	public boolean getInUse() {
		return inUse;
	}

	public void setInUse(boolean flag) {
		inUse = flag;
	}

    public Tuple getPos() {
        return position;
    }

    public void setPos(int a, int b) {
        position.a = a;
        position.b = b;
    }

    public HotelCard getHotel() {
        return hotel;
    }

    public void setHotel(HotelCard hc) {
        hotel = hc;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) {
        player = p;
    }

}
