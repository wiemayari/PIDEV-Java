package tn.esprit.services;

import tn.esprit.entities.Medcin;
import tn.esprit.interfaces.IService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedcinServices implements IService<Medcin> {
    private Connection connection;

    public MedcinServices() {
    }

    public MedcinServices(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Medcin medcin) {
        String query = "INSERT INTO med (id, nom, prenom, specialite) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, medcin.getId());
            stmt.setString(2, medcin.getNom());
            stmt.setString(3, medcin.getPrenom());
            stmt.setString(4, medcin.getSpecialite());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Medcin medcin) {
        String query = "UPDATE med SET nom = ?, prenom = ?, specialite = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medcin.getNom());
            stmt.setString(2, medcin.getPrenom());
            stmt.setString(3, medcin.getSpecialite());
            stmt.setInt(4, medcin.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Medcin medcin) {
        String query = "DELETE FROM med WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, medcin.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medcin> getAll() {
        List<Medcin> medcins = new ArrayList<>();
        String query = "SELECT * FROM med";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String specialite = rs.getString("specialite");
                medcins.add(new Medcin(id, nom, prenom, specialite));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medcins;
    }

    @Override
    public Medcin getOne(int id) {
        String query = "SELECT * FROM med WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String specialite = rs.getString("specialite");
                return new Medcin(id, nom, prenom, specialite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Medcin> getAllByEtablissement(int etablissementId) {
        List<Medcin> medcins = new ArrayList<>();
        String query = "SELECT * FROM med WHERE etab_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, etablissementId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String specialite = rs.getString("specialite");
                medcins.add(new Medcin(id, nom, prenom, specialite));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medcins;
    }

}
