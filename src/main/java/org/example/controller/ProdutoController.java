package org.example.controller;

import org.example.dao.ProdutoDAO;
import org.example.model.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@RestController
public class ProdutoController {

        private final ProdutoDAO produtoDAO;

        public ProdutoController() {
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mariadb://192.168.56.30:3306/menos?useSSl=false",
                        "menos_app",
                        "menos123"
                );

                this.produtoDAO = new ProdutoDAO(connection);

            } catch (Exception e) {
                throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage());
            }
        }

        @GetMapping("/produtos")
        public List<Produto> listar() {
            return produtoDAO.listar();
        }
    }
