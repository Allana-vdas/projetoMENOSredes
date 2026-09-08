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

    public void salvar(Escola escola) {
        String sql = "INSERT INTO escolas (nome, endereco, telefone) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, escola.getNome());
            statement.setString(2, escola.getEndereco());
            statement.setString(3, escola.getTelefone());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar escola: " + e.getMessage(), e);
        }
    }

    public void atualizar(Integer id, Escola escola) {
        String sql = "UPDATE escolas SET nome = ?, endereco = ?, telefone = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, escola.getNome());
            statement.setString(2, escola.getEndereco());
            statement.setString(3, escola.getTelefone());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar escola: " + e.getMessage(), e);
        }
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM escolas WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar escola: " + e.getMessage(), e);
        }
    }
}