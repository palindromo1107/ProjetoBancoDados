package service;

import java.util.List;
import java.util.Map;

import dao.ProdutoDao;
import model.Produto;

public class ProdutoService {

    private ProdutoDao produtoDAO = new ProdutoDao();

    // =========================
    // CRUD
    // =========================

    public void cadastrarProduto(Produto produto) {
        validarProduto(produto);
        try {
            produtoDAO.adicionar(produto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    public List<Produto> listarProdutos() {
        try {
            return produtoDAO.listar();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
    }

    public Produto buscarProdutoPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        try {
            Produto produto = produtoDAO.buscar(id);

            if (produto == null) {
                throw new RuntimeException("Produto não encontrado");
            }

            return produto;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produto", e);
        }
    }

    public void atualizarProduto(Produto produto) {
        validarProduto(produto);

        if (produto.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido para atualização");
        }

        try {
            produtoDAO.atualizar(produto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public void removerProduto(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        try {
            produtoDAO.excluir(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir produto", e);
        }
    }

    // =========================
    // CONSULTAS AVANÇADAS
    // =========================

    // =========================
    // quantidade
    // =========================
    public List<Produto> quantidadeEstoqueDesc() {
        try {
            return produtoDAO.produtosBaixoEstoque();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar em ordem alfabética", e);
        }
    }

    // =========================
    // preco acima de 100
    // =========================
    public List<Produto> produtosAcimaDe100() {

        try {
            return produtoDAO.produtosAcimaDe100();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produtos por categoria", e);
        }
    }

    // =========================
    // total por categoria
    // =========================
    public List<Produto> totalProdutosCategoria() {
        try {
            return produtoDAO.totalProdutosPorCategoria();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produtos acima da média", e);
        }
    }

    // =========================
    // media preco
    // =========================
    public List<Produto> mediaPreco() {
        try {
            return produtoDAO.precoMedioPorCategoria();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produtos por menor valor", e);
        }
    }

    // =========================
    // categoria
    // =========================
    public List<Produto> buscaCategoria() {
        try {
            return produtoDAO.categoriasComOuSemProdutos();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ordenar por quantidade", e);
        }
    }

    // =========================
    // acima da media
    // =========================
    public List<Produto> acimaMedia() {
        try {
            return produtoDAO.produtosAcimaDaMedia();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao calcular média por categoria", e);
        }
    }

    // =========================
    // VALIDAÇÃO
    // =========================

    private void validarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }

        if (produto.getValor() < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo");
        }

        if (produto.getEstoque() < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }

        if (produto.getId_categoria() <= 0) {
            throw new IllegalArgumentException("Categoria inválida");
        }
    }
}