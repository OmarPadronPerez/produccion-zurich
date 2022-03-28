package OTROS;


import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setOpaque(true);
        //System.out.println(table.getColumnCount());
        if (table.getColumnCount()>5) {
            if (Float.valueOf(table.getValueAt(row, 4).toString()) < Float.valueOf(table.getValueAt(row, 5).toString())) {

                this.setBackground(Color.RED);
                this.setForeground(Color.WHITE);
            } else {
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
            if (isSelected/*table.getSelectedRow() == row*/) {
                if (Float.valueOf(table.getValueAt(row, 4).toString()) < Float.valueOf(table.getValueAt(row, 5).toString())) {

                    this.setBackground(new Color(245, 148, 148));
                    this.setForeground(Color.BLACK);
                } else {
                    this.setBackground(new Color(128, 128, 128));
                    this.setForeground(Color.WHITE);
                }
            }
        }
        return this;
    }
}
