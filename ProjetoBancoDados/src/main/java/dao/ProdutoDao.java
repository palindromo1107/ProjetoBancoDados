package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import util.ConnectionFactory;

public class ProdutoDao {

    // INSERT
    public void adicionar(Produto produto) throws Exception {
        String sql = "INSERT INTO produtos (nome, valor) VALUES (?, ?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }
        }
    }

    // SELECT TODOS
    public List<Produto> listar() throws Exception {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("valor")
                );
                lista.add(p);
            }
        }

        return lista;
    }

    // SELECT POR ID
    public Produto buscar(int id) throws Exception {
        String sql = "SELECT * FROM produtos WHERE id = ?";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("valor")
                );
            }
        }

        return null;
    }

    // UPDATE
    public void atualizar(Produto produto) throws Exception {
        String sql = "UPDATE produtos SET nome = ?, valor = ? WHERE id = ?";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.setInt(3, produto.getId());
            ps.executeUpdate();
        }
    }

    // DELETE
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
