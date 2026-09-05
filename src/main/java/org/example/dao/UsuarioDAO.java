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
    }

