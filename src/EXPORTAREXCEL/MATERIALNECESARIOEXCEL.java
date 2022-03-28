package EXPORTAREXCEL;

import CLASES.material;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MATERIALNECESARIOEXCEL {

    Date hoy = new Date();
    SimpleDateFormat fSalida = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat fSalidaSim = new SimpleDateFormat("dd/MM/yyyy");
    CellStyle titDoc;
    CellStyle normal;
    CellStyle sTitulo;
    CellStyle prioridad;
    CellStyle normalDinero;
    CellStyle Slinea;
    Sheet sheet;

    public void modificarplantilla(Object[] titulo, ArrayList<material> lista,String componente,String modelo) {

        try {
            /*String rutaArchivo = (new File("")).getAbsolutePath();
            rutaArchivo = rutaArchivo + "\\archivos\\reportesExcel/plantilla.xlsx";
            FileInputStream file = new FileInputStream(rutaArchivo);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);*/
            String rutasalida = System.getProperty("user.home") + "/Consentrado de materiales.xlsx";
        FileOutputStream fileout = null;
        Workbook wb = new XSSFWorkbook();
            sheet = wb.createSheet("Paquetes");
            
            
            /*estilos*/
            cEstiloNormalDinero(sheet);
            cEstiloTutiloDoc(sheet);
            cEstiloTutiloTab(sheet);
            cEstiloPrioridad(sheet);
            cEstiloNormal(sheet);
            cEstiloLiea(sheet);

            
            InputStream is = new FileInputStream("archivos/imagenes/LOGO2.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
            CreationHelper help = wb.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
            ClientAnchor anchor = help.createClientAnchor();
            
            anchor.setAnchorType(AnchorType.DONT_MOVE_AND_RESIZE);
            anchor.setCol1(0);
            anchor.setRow1(0);
            draw.createPicture(anchor, imgIndex);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1.1D, 4);
            //pict.resize();
            

            /*titulo*/
            int ordenTit=2;
            Row filatTit = sheet.getRow(0);
            if (filatTit == null) {
                filatTit = sheet.createRow(0);
            }
            Cell celdaTit = filatTit.createCell(ordenTit);
            if (celdaTit == null) {
                celdaTit = filatTit.createCell(ordenTit);
            }
            celdaTit.setCellValue("MUEBLERIA ZURICH SA.CV.");
            celdaTit.setCellStyle(titDoc);
            celdaTit = filatTit.createCell(titulo.length-1);
            celdaTit.setCellValue(fSalida.format(hoy));
            celdaTit.setCellStyle(normal);
            filatTit = sheet.getRow(1);
            if (filatTit == null) {
                filatTit = sheet.createRow(1);
            }
            celdaTit = filatTit.createCell(ordenTit);
            if (celdaTit == null) {
                celdaTit = filatTit.createCell(ordenTit);
            }
            celdaTit.setCellValue("MATERIAL PARA PRODUCION");
            celdaTit.setCellStyle(titDoc);
            
            filatTit = sheet.getRow(2);
            if (filatTit == null) {
                filatTit = sheet.createRow(2);
            }
            celdaTit = filatTit.createCell(ordenTit);
            if (celdaTit == null) {
                celdaTit = filatTit.createCell(ordenTit);
            }
            celdaTit.setCellValue(componente+" DE "+modelo);
            celdaTit.setCellStyle(titDoc);
            
            /*fecha*/
            filatTit = sheet.getRow(3);
            if (filatTit == null) {
                filatTit = sheet.createRow(3);
            }
            //linea
            Row linea = sheet.getRow(5);
            if (linea == null) {
                linea = sheet.createRow(5);
            }
            
            hacerlinea(sheet,4,titulo.length);
            

            //incertar Titulos
            int numFila = 7;
            for (int a = 0; a < titulo.length; a++) {
                Row fila = sheet.getRow(numFila);
                if (fila == null) {
                    fila = sheet.createRow(numFila);
                }
                Cell celda = fila.createCell(a);
                if (celda == null) {
                    celda = fila.createCell(a);
                }

                celda.setCellValue(titulo[a].toString());
                celda.setCellStyle(sTitulo);
            }
            numFila++;
            //contenido

            for (material mat : lista) {
                int numCol=0;
                Row fila = sheet.getRow(numFila);
                if (fila == null) {
                    fila = sheet.createRow(numFila);
                }
                Cell celda = fila.createCell(numCol);
                if (celda == null) {
                    celda = fila.createCell(numCol);
                }
                
                celda.setCellValue(mat.getMaterial());
                celda.setCellStyle(normal);
                numCol++;
                
                fila = sheet.getRow(numFila);
                if (fila == null) {
                    fila = sheet.createRow(numFila);
                }
                celda = fila.createCell(numCol);
                if (celda == null) {
                    celda = fila.createCell(numCol);
                }
                celda.setCellValue(mat.getTipo());
                celda.setCellStyle(normal);
                numCol++;
                
                fila = sheet.getRow(numFila);
                if (fila == null) {
                    fila = sheet.createRow(numFila);
                }
                celda = fila.createCell(numCol);
                if (celda == null) {
                    celda = fila.createCell(numCol);
                }
                celda.setCellValue(mat.getCantidad());
                celda.setCellStyle(normal);
                numCol++;
                
                fila = sheet.getRow(numFila);
                if (fila == null) {
                    fila = sheet.createRow(numFila);
                }
                celda = fila.createCell(numCol);
                if (celda == null) {
                    celda = fila.createCell(numCol);
                }
                celda.setCellValue(mat.getCantidaPaquetes());
                celda.setCellStyle(normal);
                numCol++;
                
                fila = sheet.getRow(numFila);
                if (fila == null) {
                    fila = sheet.createRow(numFila);
                }
                celda = fila.createCell(numCol);
                if (celda == null) {
                    celda = fila.createCell(numCol);
                }
                celda.setCellValue(mat.getPrecioUnidad());
                celda.setCellStyle(normalDinero);
                numCol++;
                
                fila = sheet.getRow(numFila);
                if (fila == null) {
                    fila = sheet.createRow(numFila);
                }
                celda = fila.createCell(numCol);
                if (celda == null) {
                    celda = fila.createCell(numCol);
                }
                
                celda.setCellValue(mat.getCantidad()*mat.getPrecioUnidad());
                celda.setCellStyle(normalDinero);
                numCol++;
                numFila++;
            }
            
            hacerlinea(sheet,numFila,titulo.length);
            
            sheet.setColumnWidth(0, 10000);
            sheet.setColumnWidth(1, 7000);
            sheet.autoSizeColumn((short)5);
            
            /*for(int j = 2; j < titulo.length; j++) { 
                sheet.autoSizeColumn((short)j); 
            }*/

            /*file.close();
            String rutasalida = System.getProperty("user.home") + "/material necesario.xlsx";
            FileOutputStream fileout = new FileOutputStream(rutasalida);
            wb.write(fileout);
            fileout.close();
            File archivoXLS = new File(rutasalida);
            Desktop.getDesktop().open(archivoXLS);*/
            
            fileout = new FileOutputStream(rutasalida);
            wb.write(fileout);
            abrirarchivo(rutasalida);
            fileout.close();
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Cierre el archivo anterior o guarde con otro nombre");
            Logger.getLogger(MATERIALNECESARIOEXCEL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MATERIALNECESARIOEXCEL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void abrirarchivo(String ruta) {
        try {
            File archivoXLS = new File(ruta);
            Desktop.getDesktop().open(archivoXLS);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(excelMaterialCompleto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(excelMaterialCompleto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void hacerlinea(Sheet sheet, int numFila, int tam) {
        for (int l = 0; l < 3; l++) {
            Row fila = sheet.getRow(numFila);
            if (fila == null) {
                fila = sheet.createRow(numFila);
            }
            for (int a = 0; a < tam; a++) {
                Cell celda = fila.createCell(a);
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

    public void cEstiloTutiloDoc(Sheet sheet) {
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

    public void cEstiloTutiloTab(Sheet sheet) {
        sTitulo = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setBold(true);
        sTitulo.setFont(font);
        sTitulo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        sTitulo.setFillForegroundColor ( HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex() );
    }

   public void cEstiloPrioridad(Sheet sheet) {
        prioridad = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        prioridad.setFont(font);
        prioridad.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        prioridad.setFillForegroundColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
    }

    public void cEstiloNormal(Sheet sheet) {
        normal = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        normal.setFont(font);
    }
    public void cEstiloNormalDinero(Sheet sheet) {
        normalDinero = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        normalDinero.setFont(font);
        normalDinero.setDataFormat((short)7);
    }
    public void cEstiloLiea(Sheet sheet) {
        Slinea = sheet.getWorkbook().createCellStyle();
        Slinea.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Slinea.setFillForegroundColor ( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
    }
}
