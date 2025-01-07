public class ProdutoObserver implements Observer {
    private Gestor gestor;

    public ProdutoObserver(Gestor gestor) {
        this.gestor = gestor;
    }

    @Override
    public void update(Produto produto) {
        if (produto.getEstado() == Produto.Estado.LEILAO) {
            gestor.informarLicitacao(produto);
        } else if (produto.getEstado() == Produto.Estado.VENDAS) {
            gestor.informarVenda(produto);
        }
    }
}
