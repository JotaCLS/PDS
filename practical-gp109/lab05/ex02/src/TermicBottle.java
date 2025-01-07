public class TermicBottle implements Container{
    Portion portion;

    protected TermicBottle (Portion portion){
        this.portion = portion;
    }

    public Portion getPortion() {
        return portion;
    }

    public void setPortion(Portion portion) {
        this.portion = portion;
    }

    public String toString(){
        return "TermicBottle with portion = " + portion.toString();
    }
}
