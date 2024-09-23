package presentation;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

/**
 * Utility class for creating table models using reflection.
 */
public class ReflectionTableUtils {

    /**
     * Creates a DefaultTableModel from a list of objects using reflection to extract field names and values.
     *
     * @param objects the list of objects to be converted into a table model
     * @return a DefaultTableModel representing the objects
     */
    public static DefaultTableModel createTableModel(List<?> objects) {
        if (objects == null || objects.isEmpty()) {
            return new DefaultTableModel();
        }

        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();


        Field[] fields = objects.get(0).getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            columnNames.add(field.getName());
        }

        for (Object obj : objects) {
            Vector<Object> vector = new Vector<>();
            for (Field field : fields) {
                try {
                    vector.add(field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
}
