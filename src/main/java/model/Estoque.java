package model;

public class Estoque {

    private int id;
    private int quantidade;
    private int id_produto;

    public Estoque(int id, int quantidade, int id_produto) {
        this.id = id;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public int getId_produto() {
        return id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}