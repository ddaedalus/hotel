import java.util.Random;
    
/* BuildDice possible results:
 * H : buy free
 * K : buy x1 price
 * 2 : buy x2 price
 * X : don't buy
*/

class BuildDice {
    public String result; 
    public String roll() {
        int rand;
        Random random = new Random(); 
        rand = random.nextInt(19) + 1; 

        if (rand >= 1 && rand <= 10)
            result = "K";
        else if (rand >= 11 && rand <= 13)
            result = "H";
        else if (rand >= 14 && rand <= 16)
            result = "2";
        else 
            result = "X";

        return result;
    }
}