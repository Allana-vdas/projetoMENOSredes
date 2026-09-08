package org.example.controller;

import org.example.dao.EscolaDAO;
import org.example.model.Escola;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@RestController
@RequestMapping("/escolas")
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

    @GetMapping
    public List<Escola> listar() {
        return escolaDAO.listar();
    }

    @PostMapping
    public String salvar(@RequestBody Escola escola) {
        escolaDAO.salvar(escola);
        return "Escola cadastrada com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Integer id, @RequestBody Escola escola) {
        escolaDAO.atualizar(id, escola);
        return "Escola atualizada com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id) {
        escolaDAO.deletar(id);
        return "Escola removida com sucesso!";
    }
}