package helper;

import java.util.HashMap;
import java.util.List;

import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;

public class GaugeTableHelper {

    public HashMap<String, Object> gaugeDataTableToMap(Table table) {
        List<TableRow> rows = table.getTableRows();
        HashMap<String, Object> map = new HashMap<>();
        for (TableRow row : rows) {
            map.put(row.getCellValues().get(0), row.getCellValues().get(1));
        }
        return map;
    }
}
