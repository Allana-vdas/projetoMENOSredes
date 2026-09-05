package org.example.controller;

import org.example.dao.EscolaDAO;
import org.example.model.Escola;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@RestController
public class EscolaController {

        private final EscolaDAO escolaDAO;

        public EscolaController() {
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mariadb://192.168.56.30:3306/menos?useSSL=false",
                        "menos_app",
                        "menos123"
                );
                this.escolaDAO = new EscolaDAO(connection);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao conectar com o banco em EscolaController: " + e.getMessage());
            }
        }

        @GetMapping("/escolas")
        public List<Escola> listar() {
            return escolaDAO.listar();
        }
    }
