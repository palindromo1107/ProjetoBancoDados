package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import util.ConnectionFactory;

public class ProdutoDao {

    public void adicionar(Produto produto) throws Exception {
        String sql = "INSERT INTO produtos " +
                "(nome, valor) "
                +
                "VALUES (?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.execute();
        }
    }

    public List<Produto> listar() throws Exception {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection c = ConnectionFactory.getConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("valor")));
            }
        }
        return lista;
    }

    public Produto buscar(int id) throws Exception {
        String sql = "SELECT * FROM produtos WHERE id=?";
        try (Connection c = ConnectionFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Produto a = new Produto();
                a.setId(id);
                a.setNome(rs.getString("nome"));
                a.setValor((rs.getInt("valor")));
                return a;
            }
        }
        return null;
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM produtos WHERE id=?";
        try (Connection c = ConnectionFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

}