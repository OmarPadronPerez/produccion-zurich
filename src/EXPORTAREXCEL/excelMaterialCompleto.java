package EXPORTAREXCEL;

import OTROS.AUXILIAR;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelMaterialCompleto {

    Date hoy = new Date();
    SimpleDateFormat fSalida = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat fSalidaSim = new SimpleDateFormat("dd-MM-yyyy");
    CellStyle titDoc;
    CellStyle normal;
    CellStyle sTitulo;
    CellStyle prioridad;
    CellStyle Slinea;
    CellStyle normalCentrado;
    CellStyle actual;
    Sheet sheetP;
    Sheet sheetU;
    int tamañoLinea;

    public void crear(List<List<String>> paquete, List<List<String>> cantidad) {
        String rutasalida = System.getProperty("user.home") + "/Material de cotizacion.xlsx";
        FileOutputStream fileout = null;
        tamañoLinea=paquete.get(0).size()+1;
        try {
            Workbook book = new XSSFWorkbook();
            sheetP = book.createSheet("Paquetes");
            sheetU = book.createSheet("Unidades");

            cEstiloNormal(sheetP);
            cEstiloTutiloDoc(sheetP);
            cEstiloLiea(sheetP);

            int desface = agregarTitulo(book, sheetP);
            agregarTitulo(book, sheetU);

            hacerlinea(sheetP, desface);
            hacerlinea(sheetU, desface);

            desface = desface + 3;

            llenarHoja(sheetU, cantidad, desface);
            desface = llenarHoja(sheetP, paquete, desface);

            hacerlinea(sheetP, desface);
            hacerlinea(sheetU, desface);

            acomodar(book, sheetP, tamañoLinea);
            acomodar(book, sheetU, tamañoLinea);

            fileout = new FileOutputStream(rutasalida);
            book.write(fileout);
            abrirarchivo(rutasalida);
            fileout.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(excelMaterialCompleto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(excelMaterialCompleto.class.getName()).log(Level.SEVERE, null, ex);
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

    public void hacerlinea(Sheet sheet, int numFila) {
        for (int l = 0; l < 3; l++) {
            Row fila = sheet.getRow(numFila);
            if (fila == null) {
                fila = sheet.createRow(numFila);
            }
            for (int a = 0; a < tamañoLinea; a++) {
                Cell celda = fila.createCell(a);
                if (celda == null) {
                    celda = fila.createCell(a);
                }
                celda.setCellValue(" ");
                if (l == 1) {
                    celda.setCellStyle(Slinea);
                } else {
                    celda.setCellStyle(normal);
                }
            }
            fila.setHeight((short) 60);
            numFila++;
        }
    }

    /*public int llenarHoja(Sheet sheet, List<List<String>> lista, int ajuste) {

        for (int a = 0; a < lista.size(); a++) {
            List<String> lis1 = lista.get(a);

            for (int b = 0; b < lis1.size(); b++) {
                if (sheet.getRow(ajuste) == null) {
                    Row row = sheet.createRow(ajuste);
                }
                if (AUXILIAR.isFloat(lis1.get(b))) {
                    sheet.getRow(ajuste).createCell(b).setCellValue(Float.valueOf(lis1.get(b)));
                } else {
                    sheet.getRow(ajuste).createCell(b).setCellValue(lis1.get(b));
                }
                sheet.getRow(ajuste).getCell(b).setCellStyle(normal);
            }
            ajuste++;
        }
        return ajuste;
    }*/
    public int llenarHoja(Sheet sheet, List<List<String>> lista, int ajuste) {

        for (int a = 0; a < lista.size(); a++) {
            List<String> lis1 = lista.get(a);
            if (a == 2) {
                    hacerlinea(sheet, ajuste);
                    ajuste = ajuste + 3;
                }
            
            for (int b = 0; b < lis1.size(); b++) {
                if (sheet.getRow(ajuste) == null) {
                    Row row = sheet.createRow(ajuste);
                }
                
                if (AUXILIAR.isFloat(lis1.get(b))) {
                    sheet.getRow(ajuste).createCell(b).setCellValue(Float.valueOf(lis1.get(b)));
                } else {
                    sheet.getRow(ajuste).createCell(b).setCellValue(lis1.get(b));
                }
                sheet.getRow(ajuste).getCell(b).setCellStyle(normal);

            }
            //System.out.println("");
            ajuste++;
        }
        return ajuste;
    }

    public void acomodar(Workbook wb, Sheet sheet, int c) {
        int ancho = 300 * 25;
        sheet.setColumnWidth(0, 260 * 25);
        //sheet.setColumnWidth(1, 300 * 25);
        for (int a = 1; a < c; a++) {
            sheet.setColumnWidth(a, ancho);
        }

    }

    public int agregarTitulo(Workbook wb, Sheet sheet) {

        int tamano = 1;
        short alto = 1000;
        try {
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
            pict.resize(tamano);
            if (sheet.getRow(0) == null) {
                Row row = sheet.createRow(0);
                row.setHeight(alto);
            }
            if (sheet.getRow(1) == null) {
                Row row = sheet.createRow(1);
                row.setHeight(alto);
            }
            sheet.getRow(0).createCell(tamano).setCellValue("MUEBLERIA ZURICH SA.CV");
            //sheet.getRow(0).createCell(tamano + 1).setCellStyle(titDoc);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(excelMaterialCompleto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(excelMaterialCompleto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tamano;
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
        sTitulo.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
    }

    public void cEstiloNormal(Sheet sheet) {
        normal = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        normal.setWrapText(true);
        normal.setVerticalAlignment(VerticalAlignment.CENTER);
        normal.setFont(font);
        normal.setWrapText(true);
    }

    public void cEstilofechAct(Sheet sheet) {
        actual = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        actual.setVerticalAlignment(VerticalAlignment.CENTER);
        actual.setAlignment(HorizontalAlignment.CENTER);
        actual.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        actual.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        actual.setFont(font);
    }

    public void cEstiloNormalcentrado(Sheet sheet) {
        normalCentrado = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        normalCentrado.setVerticalAlignment(VerticalAlignment.CENTER);
        normalCentrado.setAlignment(HorizontalAlignment.RIGHT);
        normalCentrado.setFont(font);
    }

    public void cEstiloLiea(Sheet sheet) {
        Slinea = sheet.getWorkbook().createCellStyle();
        Slinea.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Slinea.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    }

}
