public class Tupperware implements Container {
    Portion portion;

    protected Tupperware(Portion portion){
        this.portion = portion;
    }

    public Portion getPortion() {
        return portion;
    }

    public void setPortion(Portion portion) {
        this.portion = portion;
    }

    public String toString(){
        return "Tupperware with portion = " + portion.toString();
    }
}
