/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caritas.model.DAO;

import caritas.model.Entity.Peca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author demetrio
 */
public class PecaDAO {
    
    /**
     *
     * @param peca
     * @return
     */
    public Peca cadastrarPeca(Peca peca) {
        
        Connection conexao = Database.getConnection();
        String sql = " INSERT INTO peca ( name, amount) "
                        + " VALUES ( ?, ?)";

        PreparedStatement stmt = Database.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
        try {
                stmt.setString(1, peca.getName());
                stmt.setInt(2, peca.getAmount());
                stmt.execute();



        } catch (SQLException e) {
                System.out.println(" Erro ao salvar peca. Causa: " + e.getMessage());
        } finally {
                Database.closePreparedStatement(stmt);
                Database.closeConnection(conexao);
        }

        return peca;
        
    }
    
    public boolean atualizarPeca(Peca peca) {
        
        Connection conexao = Database.getConnection();

        String sql = " UPDATE peca SET name=?, amount=? WHERE id = ?";
        PreparedStatement stmt = Database.getPreparedStatement(conexao, sql);
        int registrosAlterados = 0;

        try {
                stmt.setString(1, peca.getName());
                stmt.setInt(2, peca.getAmount());
                stmt.setInt(3, peca.getId());

                registrosAlterados = stmt.executeUpdate();

        } catch (SQLException e) {
                System.out.println("Erro ao atualizar peca.");
                System.out.println("Erro: " + e.getMessage());
        } finally {
                Database.closeStatement(stmt);
                Database.closeConnection(conexao);
        }

        return registrosAlterados > 0;
                
    }
    
    public ArrayList<Peca> listarPecas() {

        ArrayList<Peca> pecas = new ArrayList<Peca>();

        Connection conexao = Database.getConnection();
        String sql = " SELECT * FROM peca ";

        PreparedStatement stmt = Database.getPreparedStatement(conexao, sql);

        try {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                        Peca peca = construirResultSetPeca(rs);
                        pecas.add(peca);
                }

        } catch (SQLException e) {
                System.out.println("Erro ao consultar pecas.");
                System.out.println("Erro: " + e.getMessage());
        } finally {
                Database.closePreparedStatement(stmt);
                Database.closeConnection(conexao);
        }

        return pecas;
        
    }

    public boolean deletarPeca(int id) {
        
        String sql = " DELETE FROM peca WHERE id = ?";

        Connection conexao = Database.getConnection();
        PreparedStatement preparedStatement = Database.getPreparedStatement(conexao, sql);
        boolean excluiu = false;
        try {
                preparedStatement.setInt(1, id);
                
                int codigoRetornoUpdate = preparedStatement.executeUpdate();

                excluiu = (codigoRetornoUpdate == Database.CODIGO_RETORNO_SUCESSO_EXCLUSAO);
                
        } catch (SQLException ex) {
                System.out.println(" Erro ao excluir peca. Id: " + id + " .Causa: " + ex.getMessage());
        } finally {
                Database.closePreparedStatement(preparedStatement);
                Database.closeConnection(conexao);
        }
        return excluiu;
    }
    
    public boolean existsPartName(String partName) {
        
        ArrayList<String> partNames = new ArrayList<String>();
        
        String sql = " SELECT * FROM peca " + " WHERE name LIKE '" + partName + "'";
        
        Connection conexao = Database.getConnection();
        PreparedStatement stmt = Database.getPreparedStatement(conexao, sql);

        boolean exists = false;

        try {
            
                ResultSet rs = stmt.executeQuery();
                exists = rs.next();
                
                if (exists == true) {
                    while (rs.next()) {
                        Peca peca = construirResultSetPeca(rs);
                        partNames.add(peca.getName());
                    }
                    
                    for (String name : partNames) {
                        if (name.toLowerCase().equals(partName.toLowerCase())) {
                            exists = true;
                        }
                    }
                }

        } catch (Exception e) {
                System.out.println("Erro ao verificar se nome já está sendo utilizado. Causa:" + e.getMessage());
        } finally {
                Database.closeStatement(stmt);
                Database.closeConnection(conexao);
        }

        return exists;
        
    }
    
    private Peca construirResultSetPeca(ResultSet rs) {
        
        Peca peca = new Peca();

        try {
                peca.setId(rs.getInt("id"));
                peca.setName(rs.getString("name"));
                peca.setAmount(rs.getInt("amount"));

        } catch (Exception e) {
                System.out.println("Erro ao construir resultSet peca. Causa:" + e.getMessage());
        }
        return peca;
        
    }
    
}
