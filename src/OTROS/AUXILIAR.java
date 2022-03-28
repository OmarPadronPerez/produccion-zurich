package OTROS;

import GRAFICA.PRINCIPAL;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class AUXILIAR {

    public static void verPantalla(JDesktopPane escritorio, JInternalFrame frame) {
        if (!frame.isShowing()) {
            PRINCIPAL.jdpEscritorio.add(frame);
            int x = (escritorio.getWidth() - frame.getWidth()) / 2;
            int y = (escritorio.getHeight() - frame.getHeight()) / 2;
            if (y < 45) {
                y = 45;
            }
            frame.setLocation(x, y);
            frame.show();
        }
    }

    public static boolean isInt(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isFloat(String cadena) {
        try {
            Float.parseFloat(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
