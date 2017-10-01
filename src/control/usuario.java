/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import java.util.LinkedList;

/**
 *
 * @author rodrigoescobarlopez
 */
public class usuario {

    public static void add(usuario usr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String nombre,apellido,correo,clave;
    private int idusuario;
    private String fecha;

    public usuario(String nombre, String apellido, String correo, String clave, int idusuario, String fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.clave = clave;
        this.idusuario = idusuario;
        this.fecha = fecha;
    }

     public usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "usuario{" + "nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", clave=" + clave + ", idusuario=" + idusuario + ", fecha=" + fecha + '}';
    }
       
     
    public boolean insertarUsuario(LinkedList<usuario> user){
        String sql;
        BaseDatos objBases=new BaseDatos();
        boolean conexion=false;
        boolean insertar=false;
        sql="insert into usuarios "
                + "(nombre_usuario,apellido_usuario,correo,clave,fecha_nacimiento,id_usuario) values(?,?,?,?,?,?)";
        for (usuario user1 : user) {conexion=objBases.crearConexion();
            if (conexion) {
                insertar = objBases.sqlInsertusuario(sql,
                        user1.getNombre(),
                        user1.getApellido(),
                        user1.getCorreo(),
                        user1.getClave(),
                        user1.getFecha(),
                        user1.getIdusuario());
                System.out.println("entre");
            }
        }
        return insertar;
    
    
    }
    
    
    
    
}
