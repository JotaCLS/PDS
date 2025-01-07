public class Main {
    public static void main(String[] args) {
        // Criar produtos
        Produto produto1 = new Produto("Smartphone", 500.0);
        Produto produto2 = new Produto("Notebook", 1000.0);
        Produto produto3 = new Produto("Tablet", 300.0);
        Produto produto4 = new Produto("Câmera", 200.0);
        Produto produto5 = new Produto("Fone de Ouvido", 50.0);

        // Criar gestor
        Gestor gestor = new Gestor("João");

        // Criar observador e associar ao gestor
        ProdutoObserver observer = new ProdutoObserver(gestor);
        produto1.addObserver(observer);
        produto2.addObserver(observer);
        produto3.addObserver(observer);
        produto4.addObserver(observer);
        produto5.addObserver(observer);

        // Simulação de mudança de estado para leilão
        produto1.setEstado(Produto.Estado.LEILAO);
        produto2.setEstado(Produto.Estado.LEILAO);
        produto3.setEstado(Produto.Estado.LEILAO);
        produto4.setEstado(Produto.Estado.LEILAO);
        produto5.setEstado(Produto.Estado.LEILAO);

        // Simulação de venda de um produto
        produto3.setEstado(Produto.Estado.VENDAS);
    }
}