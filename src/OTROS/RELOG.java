package OTROS;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class RELOG extends Thread{
    private JLabel lbl;
  
  public RELOG(JLabel lbl) { 
      this.lbl = lbl; 
  }

  
  public void run() {
    while (true) {
      Date hoy = new Date();
      SimpleDateFormat s = new SimpleDateFormat("hh:mm a");
      this.lbl.setText(s.format(hoy));
      try {
        sleep(1000L);
      }
      catch (Exception exception) {}
    } 
  }

  
  public static String fecha(int tipo){
        String fecha=null;
        Date dia=new Date();
        SimpleDateFormat formato;
        
        if(tipo==1){
            formato=new SimpleDateFormat("EEEEE   dd MMMMM yyyy");
        }
        else{
            formato=new SimpleDateFormat("dd/MMM/yyyy");
        }
        fecha=formato.format(dia).toUpperCase();
        return fecha;
    }
}
