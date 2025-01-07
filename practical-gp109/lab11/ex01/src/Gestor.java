import java.util.List;

class Gestor {
    private String nome;

    public Gestor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void listarProdutos(List<Produto> produtos) {
        // Lógica para listar produtos
    }

    public void informarLicitacao(Produto produto) {
        System.out.println("Novo lance no produto: " + produto.getDescricao());
    }

    public void informarVenda(Produto produto) {
        System.out.println("Produto vendido: " + produto.getDescricao());
    }
}