package org.irmc.industrialrevival.utils;

import java.util.List;

public class ChartUtil {

    public static String generateChart(List<String> header, List<List<String>> rows) {
        // 判断输入是否合法
        if (header == null || rows == null || rows.isEmpty()) {
            return "";
        }

        // 计算每列的最大宽度
        int[] maxWidths = new int[header.size()];
        for (int i = 0; i < header.size(); i++) {
            maxWidths[i] = header.get(i).length();
        }
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                maxWidths[i] = Math.max(maxWidths[i], row.get(i).length());
            }
        }

        // 构建表格
        StringBuilder table = new StringBuilder();
        // 构建表头
        buildRow(table, header, maxWidths, true);
        // 构建分隔线
        buildSeparator(table, maxWidths);
        // 构建数据行
        for (List<String> row : rows) {
            buildRow(table, row, maxWidths, false);
        }

        return table.toString();
    }

    private static void buildRow(StringBuilder table, List<String> row, int[] maxWidths, boolean isHeader) {
        for (int i = 0; i < row.size(); i++) {
            String cell = row.get(i);
            table.append(isHeader ? "|" : "| ").append(cell);
            // 右对齐
            table.append(" ".repeat(Math.max(0, maxWidths[i] - cell.length())));
            table.append(" ");
        }
        table.append("|\n");
    }

    private static void buildSeparator(StringBuilder table, int[] maxWidths) {
        for (int width : maxWidths) {
            table.append("+");
            table.append("-".repeat(Math.max(0, width + 2)));
        }
        table.append("+\n");
    }
}
