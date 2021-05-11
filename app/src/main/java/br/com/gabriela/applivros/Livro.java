package br.com.gabriela.applivros;

public class Livro {

    public static final int ANO_MINIMO = 2000;

    public int id;
    public String nome, categoria;
    private int ano;

    public Livro() {

    }

    public Livro(String nome, int ano) {
        this.nome = nome;
        this.setAno(ano);

    }

    public Livro(int id, String nome, int ano, String categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.setAno(ano);
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if (ano >= ANO_MINIMO)
             this.ano = ano;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + categoria + " | " + ano;
    }
}
