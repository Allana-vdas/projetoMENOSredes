package org.example.dao;

import org.example.model.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setEscolaId(resultSet.getInt("escola_id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setQuantidade(resultSet.getDouble("quantidade"));
                produto.setUnidade(resultSet.getString("unidade"));
                produto.setQuantidadeMinima(resultSet.getDouble("quantidade_minima"));

                if (resultSet.getDate("data_validade") != null) {
                    produto.setDataValidade(
                            resultSet.getDate("data_validade").toLocalDate()
                    );
                }

                produtos.add(produto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage(), e);
        }

        return produtos;
    }

    public void salvar(Produto produto) {
        String sql = "INSERT INTO produtos (escola_id, nome, quantidade, unidade, quantidade_minima, data_validade) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, produto.getEscolaId());
            statement.setString(2, produto.getNome());
            statement.setDouble(3, produto.getQuantidade());
            statement.setString(4, produto.getUnidade());
            statement.setDouble(5, produto.getQuantidadeMinima());

            if (produto.getDataValidade() != null) {
                statement.setDate(6, Date.valueOf(produto.getDataValidade()));
            } else {
                statement.setDate(6, null);
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto: " + e.getMessage(), e);
        }
    }

    public void atualizar(Integer id, Produto produto) {
        String sql = "UPDATE produtos SET escola_id = ?, nome = ?, quantidade = ?, unidade = ?, quantidade_minima = ?, data_validade = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, produto.getEscolaId());
            statement.setString(2, produto.getNome());
            statement.setDouble(3, produto.getQuantidade());
            statement.setString(4, produto.getUnidade());
            statement.setDouble(5, produto.getQuantidadeMinima());

            if (produto.getDataValidade() != null) {
                statement.setDate(6, Date.valueOf(produto.getDataValidade()));
            } else {
                statement.setDate(6, null);
            }

            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto: " + e.getMessage(), e);
        }
    }
}