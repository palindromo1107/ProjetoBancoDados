package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import util.ConnectionFactory;

public class CategoriaDao {

    // ! Selecionar categorias distintas
    public List<Categoria> listarCategorias() throws Exception {
        List<Categoria> lista = new ArrayList<>();

        String sql = "SELECT * FROM categorias";

        try (Connection c = ConnectionFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("id_categoria"), rs.getString("categoria")));
            }
        }

        return lista;
    }

    public Categoria busCategoria(int id) throws Exception {
        String sql = "select * from categorias where id_categoria = ?";

        try (Connection c = ConnectionFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Categoria(rs.getInt("id_categoria"), rs.getString("categoria"));
            }

        }

        return null;
    }
}