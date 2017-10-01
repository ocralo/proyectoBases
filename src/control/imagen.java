/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author rodrigoescobarlopez
 */
public class imagen {
    private String imagen;
    private int id,megusta,codimagen_Perfil;
    

    public imagen() {
    }

    public imagen(String imagen, int id, int megusta, int codimagen_Perfil) {
        this.imagen = imagen;
        this.id = id;
        this.megusta = megusta;
        this.codimagen_Perfil = codimagen_Perfil;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMegusta() {
        return megusta;
    }

    public void setMegusta(int megusta) {
        this.megusta = megusta;
    }

    public int getCodimagen_Perfil() {
        return codimagen_Perfil;
    }

    public void setCodimagen_Perfil(int codimagen_Perfil) {
        this.codimagen_Perfil = codimagen_Perfil;
    }

    @Override
    public String toString() {
        return "imagen{" + "imagen=" + imagen + ", id=" + id + ", megusta=" + megusta + ", codimagen_Perfil=" + codimagen_Perfil + '}';
    }
    
    
    public byte[] convertirImagenAByte(String ImageName) throws IOException {
         // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }
}