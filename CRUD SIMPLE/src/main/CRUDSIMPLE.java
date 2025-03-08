package main;

import controllers.UsuarioDAO;
import javax.swing.JOptionPane;
import sqeumea.Usuario;
import java.util.List;

public class CRUDSIMPLE {
    
    public static void main(String[] args) {
        boolean continuar = true;
        
        while (continuar) {
            String opcion = JOptionPane.showInputDialog(null,
                "Seleccione una opción:\n" +
                "1. Crear usuario\n" +
                "2. Actualizar usuario\n" +
                "3. Eliminar usuario\n" +
                "4. Buscar usuario por ID\n" +
                "5. Ver todos los usuarios\n" +
                "0. Salir",
                "Menú CRUD",
                JOptionPane.QUESTION_MESSAGE);
            
            if (opcion == null) break; // Si el usuario cierra la ventana
            
            switch (opcion) {
                case "1": // Crear usuario
                    String nombre = JOptionPane.showInputDialog("Ingrese nombre:");
                    String email = JOptionPane.showInputDialog("Ingrese email:");
                    String edadStr = JOptionPane.showInputDialog("Ingrese edad:");
                    if (nombre != null && email != null && edadStr != null) {
                        int edad = Integer.parseInt(edadStr);
                        Usuario nuevoUsuario = new Usuario(nombre, email, edad);
                        UsuarioDAO.crearUsuario(nuevoUsuario);
                        JOptionPane.showMessageDialog(null, "Usuario creado con éxito.");
                    }
                    break;
                
                case "2": // Actualizar usuario
                    String idActualizarStr = JOptionPane.showInputDialog("Ingrese ID del usuario a actualizar:");
                    if (idActualizarStr != null) {
                        int idActualizar = Integer.parseInt(idActualizarStr);
                        String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre:");
                        String nuevoEmail = JOptionPane.showInputDialog("Ingrese nuevo email:");
                        String nuevaEdadStr = JOptionPane.showInputDialog("Ingrese nueva edad:");
                        if (nuevoNombre != null && nuevoEmail != null && nuevaEdadStr != null) {
                            int nuevaEdad = Integer.parseInt(nuevaEdadStr);
                            Usuario usuarioActualizar = new Usuario(idActualizar, nuevoNombre, nuevoEmail, nuevaEdad);
                            UsuarioDAO.actualizarUsuario(usuarioActualizar);
                            JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito.");
                        }
                    }
                    break;
                
                case "3": // Eliminar usuario
                    String idEliminarStr = JOptionPane.showInputDialog("Ingrese ID del usuario a eliminar:");
                    if (idEliminarStr != null) {
                        int idEliminar = Integer.parseInt(idEliminarStr);
                        UsuarioDAO.eliminarUsuario(idEliminar);
                        JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.");
                    }
                    break;
                
                case "4": // Buscar usuario por ID
                    String idBuscarStr = JOptionPane.showInputDialog("Ingrese ID del usuario a buscar:");
                    if (idBuscarStr != null) {
                        int idBuscar = Integer.parseInt(idBuscarStr);
                        Usuario usuarioEncontrado = null;
                        for (Usuario usuario : UsuarioDAO.obtenerUsuarios()) {
                            if (usuario.getId() == idBuscar) {
                                usuarioEncontrado = usuario;
                                break;
                            }
                        }
                        if (usuarioEncontrado != null) {
                            JOptionPane.showMessageDialog(null, 
                                "ID: " + usuarioEncontrado.getId() + "\n" +
                                "Nombre: " + usuarioEncontrado.getNombre() + "\n" +
                                "Email: " + usuarioEncontrado.getEmail() + "\n" +
                                "Edad: " + usuarioEncontrado.getEdad(), 
                                "Usuario Encontrado", 
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                        }
                    }
                    break;
                
                case "5": // Ver todos los usuarios
                    List<Usuario> usuarios = UsuarioDAO.obtenerUsuarios();
                    StringBuilder listaUsuarios = new StringBuilder("Usuarios en la base de datos:\n");
                    for (Usuario usuario : usuarios) {
                        listaUsuarios.append("ID: ").append(usuario.getId())
                                     .append(", Nombre: ").append(usuario.getNombre())
                                     .append(", Email: ").append(usuario.getEmail())
                                     .append(", Edad: ").append(usuario.getEdad())
                                     .append("\n");
                    }
                    JOptionPane.showMessageDialog(null, listaUsuarios.toString());
                    break;
                
                case "0": // Salir
                    continuar = false;
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida, intente de nuevo.");
            }
        }
    }
}
