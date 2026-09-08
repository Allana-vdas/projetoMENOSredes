package org.example.dao;

import org.example.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setEscolaId(resultSet.getInt("escola_id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setUsuario(resultSet.getString("usuario"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setTipo(resultSet.getString("tipo"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        }

        return usuarios;
    }

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (escola_id, nome, usuario, senha, tipo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getEscolaId());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getUsuario());
            statement.setString(4, usuario.getSenha());
            statement.setString(5, usuario.getTipo());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage(), e);
        }
    }

    public void atualizar(Integer id, Usuario usuario) {
        String sql = "UPDATE usuarios SET escola_id = ?, nome = ?, usuario = ?, senha = ?, tipo = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getEscolaId());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getUsuario());
            statement.setString(4, usuario.getSenha());
            statement.setString(5, usuario.getTipo());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage(), e);
        }
    }
}