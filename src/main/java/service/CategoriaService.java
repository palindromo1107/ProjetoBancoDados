package service;

import java.util.List;

import dao.CategoriaDao;
import model.Categoria;

public class CategoriaService {

    CategoriaDao dao = new CategoriaDao();

    public List<Categoria> listarCategorias() {
        try {
            List<Categoria> categorias = dao.listarCategorias();
            System.out.println("Lista de gategorias atualizada!");
            return categorias;
        } catch (Exception e) {
            System.out.println("Erro ao carregar as categorias!");
            return null;
        }
    }

    public Categoria buscarCategoria (int id) {
        try {
            return dao.busCategoria(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar a categoria");
            return null;
        }
    }

}