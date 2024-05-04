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

    private String telefone;

    private String pesoInicial;

    private String pesoAtual;

    private Integer idade;

    private Integer altura;

//    @Enumerated(value = EnumType.STRING)
//    private String tipoMeta;

   @OneToOne
   @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public DadosUsuario(LocalDate dataNascimento, String apelido, String telefone, String pesoInicial, String pesoAtual, Integer idade, Integer altura) {
        this.dataNascimento = dataNascimento;
        this.apelido = apelido;
        this.telefone = telefone;
        this.pesoInicial = pesoInicial;
        this.pesoAtual = pesoAtual;
        this.idade = idade;
        this.altura = altura;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(String pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public String getPesoAtual() {
        return pesoAtual;
    }

    public void setPesoAtual(String pesoAtual) {
        this.pesoAtual = pesoAtual;
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

//    public String getTipoMeta() {
//        return tipoMeta;
//    }
//
//    public void setTipoMeta(String tipoMeta) {
//        this.tipoMeta = tipoMeta;
//    }


    @Override
    public String toString() {
        return "DadosUsuario{" +
                "apelido='" + apelido + '\'' +
                ", altura=" + altura +
                ", idade=" + idade +
                ", pesoAtual='" + pesoAtual + '\'' +
                ", pesoInicial='" + pesoInicial + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", id=" + id +
                '}';
    }
}
