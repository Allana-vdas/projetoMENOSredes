package org.example.dao;

import org.example.model.Escola;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EscolaDAO {
    private Connection connection;

        public EscolaDAO(Connection connection) {
            this.connection = connection;
        }

        public List<Escola> listar() {
            List<Escola> escolas = new ArrayList<>();
            String sql = "SELECT * FROM escolas";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Escola escola = new Escola();
                    escola.setId(resultSet.getInt("id"));
                    escola.setNome(resultSet.getString("nome"));
                    escola.setEndereco(resultSet.getString("endereco"));
                    escola.setTelefone(resultSet.getString("telefone"));
                    escolas.add(escola);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao listar escolas: " + e.getMessage(), e);
            }

            return escolas;
        }
    }

