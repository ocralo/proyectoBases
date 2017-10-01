package control;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigoescobarlopez
 */
public class BaseDatos {
    Connection conexion;
    Statement st;
    
    public BaseDatos() { }

    public Connection getConexion() {
        return conexion;
    }
    
    /**
     * Método utilizado para establecer la conexión con la base de datos insta_col
     *
     * @return estado regresa el estado de la conexión, true si se estableció la
     * conexión, falso en caso contrario
     */
     public boolean crearConexion() {
        try {
            String usuario = "crud";
            String clave = "12345";
            String bd = "insta_col";
            Class.forName("com.mysql.jdbc.Driver");                                      //user  //pass
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd, usuario, clave);
            st = conexion.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            return false;
        }

        return true;
    }
 
     /**
     * Método utilizado para establecer la conexión con la base de datos insta_col
     *
     * @param insert
     * @param name
     * @param apellido
     * @param correo
     * @param clave
     * @param fecha
     * @param iduser
     * @return estado regresa el estado de la conexión, true si se estableció la
     * conexión, falso en caso contrario
     */
    public boolean sqlInsertusuario(String insert,String name,String apellido,String correo,String clave,String fecha,int iduser ) {
       
        PreparedStatement ps = null;
        try {
            
            System.out.println(name+"\n"+apellido+"\n"+correo+"\n"+clave+"\n"+fecha+"\n"+iduser+"\n");
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement(insert);
            ps.setString(1, name);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, clave);
            ps.setString(5, fecha);
            ps.setInt(6, iduser);
            
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Método utilizado para establecer la conexión con la base de datos insta_col
     *
     * @param buscarId
     * @return estado regresa el estado de la conexión, true si se estableció la
     * conexión, falso en caso contrario
     */
    public LinkedList buscarId(String buscarId ) {
             LinkedList<usuario> listaUsuario = new LinkedList();
             
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios WHERE id_usuario ='" + buscarId+"'");
            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String apellidoUsuario = rs.getString("apellido_usuario");
                String correoUsuario = rs.getString("correo");
                String claveUsuario = rs.getString("clave");
                String fechaNaUsuario = rs.getDate("fecha_nacimiento").toString();
                int idUsuario = Integer.parseInt(rs.getString("id_usuario"));
                
                usuario us = new usuario(nombreUsuario, apellidoUsuario, correoUsuario, claveUsuario, idUsuario, fechaNaUsuario);
                
                listaUsuario.add(us);
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUsuario;
    
    }
    
    /**
     * Método utilizado para establecer la conexión con la base de datos insta_col
     *
     * @param id
     */
    public void borarId(int id){
        
        String sql = "DELETE FROM usuarios WHERE id_usuario=" + id +"";
        PreparedStatement ps;
         try {
            ps=conexion.prepareStatement(sql);
            ps.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

    

