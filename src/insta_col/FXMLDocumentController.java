package instacol;

import control.BaseDatos;
import control.usuario;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigoescobarlopez
 */
public class FXMLDocumentController implements Initializable {
    
  
    
    @FXML
    private TextField nombreTex;
    @FXML
    private TextField apellidoTex;
    @FXML
    private TextField correoTex;
    @FXML
    private TextField claveTex;
    @FXML
    private TextField fechaTex;
    @FXML
    private TextField idTex;
    @FXML
    private ImageView imagenvista;
    
    String srcimg;
    
         
    
    @FXML
    private void enviarBD(ActionEvent event) throws ParseException {
        LinkedList<usuario> usuario = new LinkedList<>();
        String nombre=nombreTex.getText();
        String apellido=apellidoTex.getText();
        String correo = correoTex.getText();
        String clave = claveTex.getText();
        int idusuario = Integer.parseInt(idTex.getText());
        String fecha = fechaTex.getText();
        
        usuario usr = new usuario(nombre, apellido, correo, clave, idusuario, fecha);
        usuario.add(usr);
        usr.insertarUsuario(usuario);
    }
    
    @FXML
    private void traerBD(ActionEvent event) throws ParseException {
        String idBuscar = idTex.getText();
        boolean conexion;
        BaseDatos objbases = new BaseDatos();
        LinkedList<usuario> usuarioB = new LinkedList();
        conexion = objbases.crearConexion();
        if (conexion) {
            usuarioB = objbases.buscarId(idBuscar);
                if (!idBuscar.isEmpty()) {
                    nombreTex.setText(usuarioB.get(0).getNombre());
                    apellidoTex.setText(usuarioB.get(0).getApellido());
                    correoTex.setText(usuarioB.get(0).getCorreo());
                    claveTex.setText(usuarioB.get(0).getClave());
                    idTex.setText(String.valueOf(usuarioB.get(0).getIdusuario()));
                    fechaTex.setText(usuarioB.get(0).getFecha());
          }else{
                    JOptionPane.showInputDialog("no hay conexion");
                }
       }
    
    }
    
    @FXML
    private void borrarId(ActionEvent event) {
        boolean conexion;
        BaseDatos objbases = new BaseDatos();
        conexion = objbases.crearConexion();
        int idborrar = Integer.parseInt(idTex.getText());
        if (conexion) {
            objbases.borarId(idborrar);
        }else{ 
            JOptionPane.showInputDialog("no hay conexion");
                }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        conexion = objBases.crearConexion();
        if (conexion) {
        System.out.println("se conepto ");
        } else {
            JOptionPane.showInputDialog("no se pudo realizar la conexión");
        }
    }
    @FXML
    private void BuscarImagen(ActionEvent event) throws MalformedURLException {
        Stage st = new Stage(StageStyle.UTILITY);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //filtro para solo traer imagenes
        fileChooser.getExtensionFilters().addAll(
         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        //trae la ruta del archivo
        File file = fileChooser.showOpenDialog(st);
        Image image = new Image(file.toURI().toString());
        
        imagenvista.setImage(image);
        
        srcimg=file.getAbsolutePath();
        
        
        

    }
    
}
