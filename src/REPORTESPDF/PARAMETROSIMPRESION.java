package REPORTESPDF;

import jasper.ConfiguracaoReport;
import jasper.SwingExporterService;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;

public class PARAMETROSIMPRESION {
    public static void impresionReporte(ArrayList<?> nombreLista, Map<String, Object> parametros, String nombreReporte, String ruta) {
    
    try {
      DataSource ds = new DataSource();
      ds.setLista(nombreLista);
      ConfiguracaoReport cf = new ConfiguracaoReport();
      
      cf.setRepositorioName(ruta);
      cf.setReportName(nombreReporte);
      cf.setParametros(parametros);
      cf.setBeanDataSource(ds);
      SwingExporterService.generateReport(cf, false);
    }catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, e);
    } 
  }
}
