package Utils;

import javax.swing.*;

// Clase Utils.

public class UtilsJOptionPane {
    public static void OPERACION_CANCEALDA(){
        JOptionPane.showMessageDialog(null,"ERROR. La operación se ha cancelado",
                "ERROR (OPERACIÓN CANCELADA)",JOptionPane.ERROR_MESSAGE);
    }
    public static void OPERACION_CONFIRMADA(){
        JOptionPane.showMessageDialog(null,"ÉXITO. La operación ha sido confirmada",
                "ÉXITO (OPERACIÓN CONFIRMADA)",JOptionPane.INFORMATION_MESSAGE);
    }
}
