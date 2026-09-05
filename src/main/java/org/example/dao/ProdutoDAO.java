package org.example.dao;

import org.example.model.Produto;
import java.sql.Connection;
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
    }

