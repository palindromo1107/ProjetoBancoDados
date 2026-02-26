package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import util.ConnectionFactory;

public class ProdutoDao {

    // =========================
    // INSERT
    // =========================
    public void adicionar(Produto produto) throws Exception {

        String sqlProduto = "INSERT INTO produto (nome, preco, id_categoria) VALUES (?, ?, ?)";

        String sqlEstoque = "INSERT INTO estoque (id_produto, quantidade) VALUES (?, ?)";

        try (Connection c = ConnectionFactory.getConnection()) {

            c.setAutoCommit(false);

            try (
                    PreparedStatement ps1 = c.prepareStatement(
                            sqlProduto,
                            Statement.RETURN_GENERATED_KEYS)) {

                // Inserir produto
                ps1.setString(1, produto.getNome());
                ps1.setInt(2, produto.getValor());
                ps1.setInt(3, produto.getId_categoria());
                ps1.executeUpdate();

                ResultSet rs = ps1.getGeneratedKeys();
                if (rs.next()) {
                    produto.setId(rs.getInt(1));
                }

                // Inserir estoque
                try (PreparedStatement ps2 = c.prepareStatement(sqlEstoque)) {

                    ps2.setInt(1, produto.getId());
                    ps2.setInt(2, produto.getEstoque());
                    ps2.executeUpdate();
                }

                c.commit();

            } catch (Exception e) {
                c.rollback();
                throw e;
            }
        }
    }

    // =========================
    // SELECT TODOS
    // =========================
    public List<Produto> listar() throws Exception {

        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT p.id, p.nome, p.preco, e.quantidade, " +
                "p.id_categoria, c.categoria " +
                "FROM produto p " +
                "LEFT JOIN estoque e ON p.id = e.id_produto " +
                "LEFT JOIN categorias c ON p.id_categoria = c.id_categoria";

        try (Connection c = ConnectionFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("id_categoria"),
                        rs.getString("categoria"));

                lista.add(p);
            }
        }

        return lista;
    }

    // =========================
    // SELECT POR ID
    // =========================
    public Produto buscar(int id) throws Exception {

        String sql = "SELECT p.id, p.nome, p.preco, e.quantidade, " +
                "p.id_categoria, c.categoria " +
                "FROM produto p " +
                "LEFT JOIN estoque e ON p.id = e.id_produto " +
                "LEFT JOIN categorias c ON p.id_categoria = c.id_categoria " +
                "WHERE p.id = ?";

        try (Connection c = ConnectionFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Produto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("preco"),
                            rs.getInt("quantidade"),
                            rs.getInt("id_categoria"),
                            rs.getString("categoria"));
                }
            }
        }

        return null;
    }

    // =========================
    // UPDATE
    // =========================
    public void atualizar(Produto p) throws Exception {

        String sqlProduto = "UPDATE produto SET nome = ?, preco = ?, id_categoria = ? WHERE id = ?";

        String sqlEstoque = "UPDATE estoque SET quantidade = ? WHERE id_produto = ?";

        try (Connection c = ConnectionFactory.getConnection()) {

            c.setAutoCommit(false);

            try (
                    PreparedStatement ps1 = c.prepareStatement(sqlProduto);
                    PreparedStatement ps2 = c.prepareStatement(sqlEstoque)) {

                // Atualiza produto
                ps1.setString(1, p.getNome());
                ps1.setInt(2, p.getValor());
                ps1.setInt(3, p.getId_categoria());
                ps1.setInt(4, p.getId());
                ps1.executeUpdate();

                // Atualiza estoque
                ps2.setInt(1, p.getEstoque());
                ps2.setInt(2, p.getId());
                ps2.executeUpdate();

                c.commit();

            } catch (Exception e) {
                c.rollback();
                throw e;
            }
        }
    }

    // =========================
    // DELETE
    // =========================
    public void excluir(int id) throws Exception {

        String sqlEstoque = "DELETE FROM estoque WHERE id_produto = ?";

        String sqlProduto = "DELETE FROM produto WHERE id = ?";

        try (Connection c = ConnectionFactory.getConnection()) {

            c.setAutoCommit(false);

            try (
                    PreparedStatement ps1 = c.prepareStatement(sqlEstoque);
                    PreparedStatement ps2 = c.prepareStatement(sqlProduto)) {

                ps1.setInt(1, id);
                ps1.executeUpdate();

                ps2.setInt(1, id);
                ps2.executeUpdate();

                c.commit();

            } catch (Exception e) {
                c.rollback();
                throw e;
            }
        }
    }

    // TODO CONSULTAS AVANÇADAS

    // ! Inner join + where
    // ! Ordenar por menor quantidade no estoque

    public List<Produto> produtosBaixoEstoque() throws Exception {

        List<Produto> lista = new ArrayList<>();

        String sql = """
                SELECT p.id, p.nome, p.preco,
                e.quantidade,
                c.id_categoria,
                c.categoria
                FROM produto p
                INNER JOIN categorias c ON p.id_categoria = c.id_categoria
                INNER JOIN estoque e ON p.id = e.id_produto
                WHERE e.quantidade < 10
                """;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("id_categoria"),
                        rs.getString("categoria")));
            }
        }

        return lista;
    }

    // ! Produtos acima de 100 de valor
    public List<Produto> produtosAcimaDe100() throws Exception {

        List<Produto> lista = new ArrayList<>();

        String sql = """
                SELECT p.id, p.nome, p.preco,
                        e.quantidade,
                        c.id_categoria,
                        c.categoria
                FROM produto p
                INNER JOIN categorias c ON p.id_categoria = c.id_categoria
                INNER JOIN estoque e ON p.id = e.id_produto
                WHERE p.preco > 100
                """;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("id_categoria"),
                        rs.getString("categoria")));
            }
        }

        return lista;
    }

    // ! Group by + order by
    // ! Total por Categoria

    public List<Produto> totalProdutosPorCategoria() throws Exception {

        List<Produto> lista = new ArrayList<>();

        String sql = """
                SELECT c.id_categoria,
                        c.categoria,
                        COUNT(p.id) AS total_produtos
                FROM categorias c
                INNER JOIN produto p ON c.id_categoria = p.id_categoria
                GROUP BY c.id_categoria, c.categoria
                ORDER BY total_produtos DESC
                """;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Produto(
                        0,
                        "",
                        rs.getInt("total_produtos"), // usando preco como campo auxiliar
                        0,
                        rs.getInt("id_categoria"),
                        rs.getString("categoria")));
            }
        }

        return lista;
    }

    // ! Media preço p/ categoria
    public List<Produto> precoMedioPorCategoria() throws Exception {

        List<Produto> lista = new ArrayList<>();

        String sql = """
                SELECT c.id_categoria,
                        c.categoria,
                        AVG(p.preco) AS preco_medio
                FROM categorias c
                INNER JOIN produto p ON c.id_categoria = p.id_categoria
                GROUP BY c.id_categoria, c.categoria
                ORDER BY preco_medio DESC
                """;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Produto(
                        0,
                        "",
                        rs.getInt("preco_medio"),
                        0,
                        rs.getInt("id_categoria"),
                        rs.getString("categoria")));
            }
        }

        return lista;
    }

    // ! left join
    // ! categoria sem produto repetido
    public List<Produto> categoriasComOuSemProdutos() throws Exception {

        List<Produto> lista = new ArrayList<>();

        String sql = """
                SELECT p.id, p.nome, p.preco,
                        e.quantidade,
                        c.id_categoria,
                        c.categoria
                FROM categorias c
                LEFT JOIN produto p ON c.id_categoria = p.id_categoria
                LEFT JOIN estoque e ON p.id = e.id_produto
                """;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("id_categoria"),
                        rs.getString("categoria")));
            }
        }

        return lista;
    }

    // ! sub consulta
    // ! acima da media
    public List<Produto> produtosAcimaDaMedia() throws Exception {

        List<Produto> lista = new ArrayList<>();

        String sql = """
                SELECT p.id, p.nome, p.preco,
                        e.quantidade,
                        c.id_categoria,
                        c.categoria
                FROM produto p
                LEFT JOIN categorias c ON p.id_categoria = c.id_categoria
                LEFT JOIN estoque e ON p.id = e.id_produto
                WHERE p.preco > (SELECT AVG(preco) FROM produto)
                """;

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("id_categoria"),
                        rs.getString("categoria")));
            }
        }

        return lista;
    }
}