package org.example.model;

import java.time.LocalDate;

public class Produto {
    private Integer id;
    private Integer escolaId;
    private String nome;
    private Double quantidade;
    private String unidade;
    private Double quantidadeMinima;
    private LocalDate dataValidade;

    public Produto() {
    }

    public Produto(Integer id, Integer escolaId, String nome, Double quantidade,
                   String unidade, Double quantidadeMinima, LocalDate dataValidade) {
        this.id = id;
        this.escolaId = escolaId;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.quantidadeMinima = quantidadeMinima;
        this.dataValidade = dataValidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEscolaId() {
        return escolaId;
    }

    public void setEscolaId(Integer escolaId) {
        this.escolaId = escolaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Double quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}