public class PlasticBottle implements Container {
    Portion portion;

    protected PlasticBottle(Portion portion){
        this.portion = portion;
    }

    @Override
    public Portion getPortion() {
        return portion;
    }

    public void setPortion(Portion portion) {
        this.portion = portion;
    }

    public String toString(){
        return "PlasticBottle with portion = " + portion.toString();
    }
}
