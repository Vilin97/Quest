import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        boolean test = false;
        if (test) {
            Hero hero1 = new Warrior("H1",500, new Stats(400,400,400),10000,5);
            //Monster monster1 = new Dragon("M1", 1, 100, 0.5);
            Market market = new Market();
            market.displayGoods();
            hero1.tradeWithMarket(market);
            hero1.tradeWithMarket(market);
        }
        else{
            Quest quest = new Quest();
            quest.play();
        }
    }
}
