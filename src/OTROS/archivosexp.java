package OTROS;

import GRAFICA.PRINCIPAL;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class archivosexp {
  
  String ruta;
  public void abrirarchivo(String archivo) {
    this.ruta = "\\\\" + PRINCIPAL.conexiones.getServidor() + "\\zurich\\archivos\\" + archivo;
    System.out.println(this.ruta);
    File objetofile = null;
    try {
      objetofile = new File(this.ruta);
      Desktop.getDesktop().open(objetofile);
    } catch (IOException ex) {
      System.out.println(ex);
    } 
  }


  
  public boolean copiarArchivo(String fromFile, String toFile) {
    File origin = new File(fromFile);
    File destination = new File(toFile);
    if (origin.exists()) {
      try {
        InputStream in = new FileInputStream(origin);
        OutputStream out = new FileOutputStream(destination);
        
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
          out.write(buf, 0, len);
        }
        in.close();
        out.close();
        return true;
      } catch (IOException ioe) {
        ioe.printStackTrace();
        System.out.println("copiarArchivo falla " + ioe);
        return false;
      } 
    }
    return false;
  }

  
}
