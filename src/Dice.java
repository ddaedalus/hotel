import java.util.Random;

class Dice {
    int result;
    public int roll() {
        Random random = new Random(); 
        result = random.nextInt(6) + 1; 
        return result;
    }
}
