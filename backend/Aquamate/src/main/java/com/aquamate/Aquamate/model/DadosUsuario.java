package com.aquamate.Aquamate.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "dados_usuario")
public class DadosUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataNascimento;

    private String apelido;

    private Double peso;

    private Integer idade;

    private Integer altura;

    private Boolean tipoMeta;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public DadosUsuario(LocalDate dataNascimento, String apelido, Double peso , Integer idade, Integer altura, Boolean tipoMeta) {
        this.dataNascimento = dataNascimento;
        this.apelido = apelido;
        this.peso = peso;
        this.idade = idade;
        this.altura = altura;
        this.tipoMeta = tipoMeta;
    }

    public DadosUsuario() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getTipoMeta() {
        return tipoMeta;
    }

    public void setTipoMeta(Boolean tipoMeta) {
        this.tipoMeta = tipoMeta;
    }

    @Override
    public String toString() {
        return "DadosUsuario{" +
                "apelido='" + apelido + '\'' +
                ", altura=" + altura +
                ", idade=" + idade +
                ", peso=" + peso +
                ", dataNascimento=" + dataNascimento +
                ", id=" + id +
                '}';
    }

}
