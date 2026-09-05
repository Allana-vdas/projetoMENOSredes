package org.example.model;

public class Usuario {
        private Integer id;
        private Integer escolaId;
        private String nome;
        private String usuario;
        private String senha;
        private String tipo;

        public Usuario() {}

        public Usuario(Integer id, Integer escolaId, String nome, String usuario, String senha, String tipo) {
            this.id = id;
            this.escolaId = escolaId;
            this.nome = nome;
            this.usuario = usuario;
            this.senha = senha;
            this.tipo = tipo;
        }

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public Integer getEscolaId() { return escolaId; }
        public void setEscolaId(Integer escolaId) { this.escolaId = escolaId; }
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getUsuario() { return usuario; }
        public void setUsuario(String usuario) { this.usuario = usuario; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
    }

