package com.project.sudoku_web.model;

public class Celula {

    private int linha;
    private int coluna;
    private int valor;
    private boolean fixo;
    
    public Celula() {
        this.linha = 0;
        this.coluna = 0;
        this.valor = 0;
        this.fixo = false;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public int getValor() {
        return valor;
    }

    public boolean isFixo() {
        return fixo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public void setFixo(boolean fixo) {
        this.fixo = fixo;
    }

}
