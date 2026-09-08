package org.example.controller;

import org.example.dao.ProdutoDAO;
import org.example.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoDAO produtoDAO;

    public ProdutoController() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://192.168.56.30:3306/menos?useSSL=false",
                    "menos_app",
                    "menos123"
            );

            this.produtoDAO = new ProdutoDAO(connection);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoDAO.listar();
    }

    @PostMapping
    public String salvar(@RequestBody Produto produto) {
        produtoDAO.salvar(produto);
        return "Produto cadastrado com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Integer id, @RequestBody Produto produto) {
        produtoDAO.atualizar(id, produto);
        return "Produto atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id) {
        produtoDAO.deletar(id);
        return "Produto removido com sucesso!";
    }
}