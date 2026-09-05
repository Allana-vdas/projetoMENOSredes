package org.example.controller;

import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@RestController
public class UsuarioController {

        private final UsuarioDAO usuarioDAO;

        public UsuarioController() {
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mariadb://192.168.56.30:3306/menos?useSSL=false",
                        "menos_app",
                        "menos123"
                );
                this.usuarioDAO = new UsuarioDAO(connection);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao conectar com o banco em UsuarioController: " + e.getMessage());
            }
        }

        @GetMapping("/usuarios")
        public List<Usuario> listar() {
            return usuarioDAO.listar();
        }
    }

