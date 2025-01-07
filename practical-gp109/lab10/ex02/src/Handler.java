public interface Handler {
    void cook(String plate);
    void setNext(Handler handler);
}
