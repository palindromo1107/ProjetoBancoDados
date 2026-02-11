package service;

import java.util.List;

import dao.ProdutoDao;
import model.Produto;

public class ProdutoService {

    private ProdutoDao produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDao();
    }

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
            throw new RuntimeException("Erro ao listar os produtos", e);
        }
    }

    public Produto buscarProdutoPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Erro ao buscar produto");
        }
        try {
            return produtoDAO.buscar(id);
        } catch (Exception e) {
            throw new RuntimeException("Id não encontrado", e);
        }
    }

    public void removerProduto(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        try {
            produtoDAO.excluir(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir o produto", e);
        }
    }

    // ===== regras privadas =====

    private void validarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }

        if (produto.getValor() <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
    }
}
