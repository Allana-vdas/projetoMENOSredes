package org.example.controller;

import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
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

    @GetMapping
    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    @PostMapping
    public String salvar(@RequestBody Usuario usuario) {
        usuarioDAO.salvar(usuario);
        return "Usuário cadastrado com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuarioDAO.atualizar(id, usuario);
        return "Usuário atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id) {
        usuarioDAO.deletar(id);
        return "Usuário removido com sucesso!";
    }
}