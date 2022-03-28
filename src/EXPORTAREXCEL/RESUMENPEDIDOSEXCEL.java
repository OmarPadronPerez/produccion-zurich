package EXPORTAREXCEL;

import CLASES.PEDIDO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

public class RESUMENPEDIDOSEXCEL {

    Date hoy = new Date();
    SimpleDateFormat fSalida = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat fSalidaSim = new SimpleDateFormat("dd/MM/yyyy");
    CellStyle titDoc;
    CellStyle normal;
    CellStyle sTitulo;
    CellStyle prioridad;
    CellStyle Slinea;

    public void modificarplantilla(Object[] titulo, ArrayList<PEDIDO> lista) {

        try {
            String rutaArchivo = (new File("")).getAbsolutePath();
            rutaArchivo = rutaArchivo + "\\archivos\\reportesExcel/plantilla.xlsx";
            FileInputStream file = new FileInputStream(rutaArchivo);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            /*estilos*/
            cEstiloTutiloTab(sheet);
            cEstiloPrioridad(sheet);
            cEstiloNormal(sheet);
            cEstiloLiea(sheet);
            cEstiloTutiloDoc(sheet);
            /*logo*/
            
            InputStream is = new FileInputStream("archivos/imagenes/LOGO2.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
            CreationHelper help = wb.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
            ClientAnchor anchor = help.createClientAnchor();
            
            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
            anchor.setCol1(0);
            anchor.setRow1(0);
            draw.createPicture(anchor, imgIndex);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(3.1D, 4);
            
            int centro=5;
            /*titulo*/
            XSSFRow filatTit = sheet.getRow(0);
            if (filatTit == null) {
                filatTit = sheet.createRow(0);
            }
            XSSFCell celdaTit = filatTit.createCell(centro);
            if (celdaTit == null) {
                celdaTit = filatTit.createCell(centro);
            }
            celdaTit.setCellValue("MUEBLERIA ZURICH SA.CV.");
            celdaTit.setCellStyle(titDoc);
            
            celdaTit = filatTit.createCell(titulo.length-1);
            celdaTit.setCellValue(fSalida.format(hoy));
            celdaTit.setCellStyle(normal);
            
            hacerlinea(sheet,4,titulo.length);
            
            filatTit = sheet.getRow(1);
            if (filatTit == null) {
                filatTit = sheet.createRow(1);
            }
            celdaTit = filatTit.createCell(centro);
            if (celdaTit == null) {
                celdaTit = filatTit.createCell(centro);
            }
            
            celdaTit.setCellValue("RESUMEN DE PEDIDOS");
            celdaTit.setCellStyle(titDoc);
            /*fecha*/
            

            
            //incertar Titulos
            int numFila = 7;
            for (int a = 0; a < titulo.length; a++) {
                XSSFRow fila = sheet.getRow(numFila);
                if (fila == null) {
                    fila = sheet.createRow(numFila);
                }
                XSSFCell celda = fila.createCell(a);
                if (celda == null) {
                    celda = fila.createCell(a);
                }

                celda.setCellValue(titulo[a].toString());
                celda.setCellStyle(sTitulo);
            }
            numFila++;
            //contenido
            for (PEDIDO ped : lista) {
                XSSFRow fila = sheet.getRow(numFila);
                if (fila == null) {
                    fila = sheet.createRow(numFila);
                }
                int iCol = 0;

                XSSFCell celda = fila.createCell(iCol);
                celda.setCellValue(ped.getFolio());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(ped.getPrioridad());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(ped.getCliente());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(ped.getComponente());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(ped.getModelo());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(ped.getColor());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(ped.getTapizado());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(ped.getCantidad());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(ped.getEcho());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(ped.getEntregado());
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(fSalidaSim.format(ped.getFechaCreacion()));
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                iCol++;
                celda = fila.createCell(iCol);
                celda.setCellValue(fSalidaSim.format(ped.getFechaEntrega()));
                if (ped.getPrioridad().equals("Normal")) {
                    celda.setCellStyle(normal);
                } else {
                    celda.setCellStyle(prioridad);
                }
                
                numFila++;
            }
            hacerlinea(sheet,numFila,titulo.length);
            
            for(int j = 0; j < titulo.length; j++) {
                if(j!=centro){
                    sheet.autoSizeColumn((short)j); 
                }
                
            }

            file.close();
            String rutasalida = System.getProperty("user.home") + "/Resumen de pedidos.xlsx";
            FileOutputStream fileout = new FileOutputStream(rutasalida);
            wb.write(fileout);
            fileout.close();
            File archivoXLS = new File(rutasalida);
            Desktop.getDesktop().open(archivoXLS);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Cierre el archivo anterior o guarde con otro nombre");
            Logger.getLogger(RESUMENPEDIDOSEXCEL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            
            Logger.getLogger(RESUMENPEDIDOSEXCEL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void cEstiloTutiloDoc(XSSFSheet sheet) {
        titDoc = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        titDoc.setVerticalAlignment(VerticalAlignment.CENTER);
        titDoc.setAlignment(HorizontalAlignment.CENTER);
        titDoc.setFont(font);
    }
    
    public void cEstiloTutiloTab(XSSFSheet sheet) {
        sTitulo = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setBold(true);
        sTitulo.setFont(font);
        sTitulo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        sTitulo.setFillForegroundColor ( HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex() );
    }

   public void cEstiloPrioridad(XSSFSheet sheet) {
        prioridad = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        prioridad.setFont(font);
        prioridad.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        prioridad.setFillForegroundColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
    }

    public void cEstiloNormal(XSSFSheet sheet) {
        normal = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        normal.setFont(font);
    }
    public void cEstiloLiea(XSSFSheet sheet) {
        Slinea = sheet.getWorkbook().createCellStyle();
        Slinea.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Slinea.setFillForegroundColor ( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
    }
    public void hacerlinea(XSSFSheet sheet, int numFila, int tam) {
        for (int l = 0; l < 3; l++) {
            XSSFRow fila = sheet.getRow(numFila);
            if (fila == null) {
                fila = sheet.createRow(numFila);
            }
            for (int a = 0; a < tam; a++) {
                XSSFCell celda = fila.createCell(a);
                if (celda == null) {
                    celda = fila.createCell(a);
                }
                celda.setCellValue(" ");
                if(l==1){
                    celda.setCellStyle(Slinea);
                }else{
                    celda.setCellStyle(normal);
                }
            }
            fila.setHeight((short)60);
            numFila++;
        }
    }
}
