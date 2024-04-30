public class Property extends Field {
    private int serieID;
    private Player owner;

    public Property(int id, String label, int cost, int income, int serieID) {
        super(id, label, cost, income);
        this.serieID = serieID;

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String onLand(Player p) {
        String msg = super.onLand(p);
        if(owner == null){
          if(  p.hasSufficientFunds(this.getCost())) {
              this.setOption("buy");
              msg += "\nVil du købe " + super.getLabel() + " til " + this.getCost() + "? (Y/N)";
          }else{
                  msg = "Du har ikke midlerne til denne grund";
                  // Todo:
                  // Tjek om spilleren ejer nogen ejendomme
                  // this.setOption("pawn");
                  // msg += "\nVil du pantsætte ";
                  // hvis ikke, sæt ejendommmen på auktion
          }
        }else if(owner != p){
            this.setOption("payRent");
            msg += "\nDu skal betale " + super.getIncome() + ". Indforstået Y/N";
        }
        return msg;
    }

    @Override
    protected String onAccept(Player p) {
        String msg = "";
        if (this.getOption().equalsIgnoreCase("buy")){


              msg = "Du har købt "+this.getLabel();
              this.owner = p;

        }else if (this.getOption().equalsIgnoreCase("payRent")){
            msg ="Du har betalt til "+this.owner; //Evt ændre fra label til ejer af "Deed"
            p.pay(this.getIncome(),owner);
        }
        return msg;
    }

    @Override
    protected String onReject(Player p) {
        String msg = "";
        if (getOption().equalsIgnoreCase("buy")){
            msg = "Så ryger feltet på auktion...";

        }else if (getOption().equalsIgnoreCase("payRent")){
           msg = "Du er ikke længere med i spillet, makker!";
        }
        return msg;
    }
}






