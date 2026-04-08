package com.example.models;

import jakarta.persistence.*;

@Entity
public class Automovel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;
    private String placa;

    @Enumerated(EnumType.STRING)
    private StatusAutomovel status = StatusAutomovel.DISPONIVEL;

    public Automovel() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public StatusAutomovel getStatus() { return status; }
    public void setStatus(StatusAutomovel status) { this.status = status; }
}