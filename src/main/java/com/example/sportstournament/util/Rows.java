package com.example.sportstournament.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Turns native query rows (Object[]) into named JSON objects. */
public final class Rows {

    private Rows() {
    }

    public static List<Map<String, Object>> toMaps(List<Object[]> rows, String... names) {
        List<Map<String, Object>> out = new ArrayList<>();
        for (Object[] r : rows) {
            Map<String, Object> m = new LinkedHashMap<>();
            for (int i = 0; i < names.length; i++) {
                m.put(names[i], r[i]);
            }
            out.add(m);
        }
        return out;
    }
}
