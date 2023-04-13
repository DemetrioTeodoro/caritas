/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caritas.model.DAO;

import caritas.model.Entity.Enxoval;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author demetrio
 */
public class EnxovalDAO {
    
    /**
     *
     * @param enxoval
     * @return
     */
    public Enxoval cadastrarEnxoval(Enxoval enxoval) {
        
        Connection conexao = Database.getConnection();
        String sql = " INSERT INTO enxoval ( total, ano) "
                        + " VALUES ( ?, ?)";

        PreparedStatement stmt = Database.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
        try {
                stmt.setInt(1, enxoval.getTotal());
                stmt.setInt(2, enxoval.getYear());
                stmt.execute();



        } catch (SQLException e) {
                System.out.println(" Erro ao salvar enxoval. Causa: " + e.getMessage());
        } finally {
                Database.closePreparedStatement(stmt);
                Database.closeConnection(conexao);
        }

        return enxoval;
        
    }
    
    public boolean atualizarEnxoval(Enxoval enxoval) {
        
        Connection conexao = Database.getConnection();

        String sql = " UPDATE enxoval SET total=?, ano=? WHERE id = ?";
        PreparedStatement stmt = Database.getPreparedStatement(conexao, sql);
        int registrosAlterados = 0;

        try {
                stmt.setInt(1, enxoval.getTotal());
                stmt.setInt(2, enxoval.getYear());
                stmt.setInt(3, enxoval.getId());

                registrosAlterados = stmt.executeUpdate();

        } catch (SQLException e) {
                System.out.println("Erro ao atualizar enxoval.");
                System.out.println("Erro: " + e.getMessage());
        } finally {
                Database.closeStatement(stmt);
                Database.closeConnection(conexao);
        }

        return registrosAlterados > 0;
                
    }
    
    public ArrayList<Enxoval> listarEnxoval() {

        ArrayList<Enxoval> enxovais = new ArrayList<Enxoval>();

        Connection conexao = Database.getConnection();
        String sql = " SELECT * FROM enxoval ";

        PreparedStatement stmt = Database.getPreparedStatement(conexao, sql);

        try {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                        Enxoval enxoval = construirResultSetEnxoval(rs);
                        enxovais.add(enxoval);
                }

        } catch (SQLException e) {
                System.out.println("Erro ao consultar enxoval.");
                System.out.println("Erro: " + e.getMessage());
        } finally {
                Database.closePreparedStatement(stmt);
                Database.closeConnection(conexao);
        }

        return enxovais;
        
    }
    
    private Enxoval construirResultSetEnxoval(ResultSet rs) {
        
        Enxoval enxoval = new Enxoval();

        try {
                enxoval.setId(rs.getInt("id"));
                enxoval.setTotal(rs.getInt("total"));
                enxoval.setYear(rs.getInt("ano"));

        } catch (Exception e) {
                System.out.println("Erro ao construir resultSet enxoval. Causa:" + e.getMessage());
        }
        return enxoval;
        
    }
    
}
