public class PlasticBag implements Container {
    Portion portion;

    public PlasticBag(Portion portion){
        this.portion = portion;
    }

    public Portion getPortion() {
        return portion;
    }

    public void setPortion(Portion portion) {
        this.portion = portion;
    }

    public String toString(){
        return "PlasticBag with portion = " + portion.toString();
    }
}
