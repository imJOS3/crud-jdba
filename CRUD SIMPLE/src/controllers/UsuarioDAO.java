package controllers;

import sqeumea.Usuario;
import db.Database;
import java.util.ArrayList;  // Cambié de 'java.awt.List' a 'java.util.ArrayList'
import java.util.List;       // Importamos java.util.List
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    public static void crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, email, edad) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getEdad());
            stmt.executeUpdate();
            System.out.println("Usuario creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>(); // Usamos ArrayList de java.util
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = Database.connect(); 
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                int edad = rs.getInt("edad");
                usuarios.add(new Usuario(id, nombre, email, edad));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public static void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = Database.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuario eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, edad = ? WHERE id = ?";

        try (Connection conn = Database.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getEdad());
            stmt.setInt(4, usuario.getId());
            stmt.executeUpdate();
            System.out.println("Usuario actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
