package model;

public class Categoria {

    private int id;
    private String categoria;

    public Categoria(int id, String categoria) {
        this.categoria = categoria;
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getId() {
        return id;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return categoria;
    }
}