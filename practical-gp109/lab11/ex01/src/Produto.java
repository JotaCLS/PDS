import java.util.ArrayList;
import java.util.List;

// Entidade Produto
public class Produto {
    private static int codigoCount = 0;
    private int codigo;
    private String descricao;
    private double precoBase;
    private Estado estado;
    private List<Observer> observers;

    public Produto(String descricao, double precoBase) {
        this.codigo = ++codigoCount;
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.estado = Estado.STOCK;
        this.observers = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public enum Estado {
        STOCK,
        LEILAO,
        VENDAS
    }
}