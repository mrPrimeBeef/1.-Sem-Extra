import java.util.ArrayList;

public class Chance extends Field{
    public Chance(int id, String label, int cost, int income) {
        super(id, label, cost, income);
    }

    @Override
    public String onLand(Player p) {

        CardDeck deck = Main.games.get(0).getDeck();
        Card card = deck.getNext();
        String s = super.onLand(p);
        this.setCost(card.getCost());
        this.setIncome(card.getIncome());
        this.setOption(card.getEvent());
        s += "\n Træk et kort fra bunken: " + card.getMessage();
        
        return s;
    }
    @Override
    public String onAccept(Player p){

        //TODO p.startPassed() og implementing af methode
        String msg = "";
        switch (this.getOption()) {
            case "payment":
                p.pay(this.getCost());
                msg = "Du har betalt.";
                break;
            case "reward":
                p.receive(this.getIncome());
                msg = "Du har modtaget penge.";
                break;
            case "collect":
                p.collect(this.getIncome());
                msg = "Du har modtaget penge fra alle.";
                break;
            case "-3":
                p.updatePosition(-3);
                msg = "Ryk tre felter tilbage";
                break;
            case "1":
                p.updatePosition(41 - p.getPosition());
                break;
            case "3":
                p.updatePosition(3);
                break;
            case "11":
                p.updatePosition((51 - p.getPosition()) % 40);
                break;
            case "16":
                p.updatePosition((56 - p.getPosition()) % 40);
                break;
            case "25":
                p.updatePosition((65 - p.getPosition()) % 40);
                break;
            case "33":
                p.updatePosition((73 - p.getPosition()) % 40);
                break;
            case "38":
                p.updatePosition((78 - p.getPosition()) % 40);
                break;
            case "40":
                p.updatePosition(40 - p.getPosition());
                break;
            case "rederi":
                p.updatePosition((16 - p.getPosition() % 10 ) % 10 );
                break;


        }
        return msg;
    }
}
