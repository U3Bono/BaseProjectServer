package utils;

import java.sql.*;
import java.util.*;

import static java.lang.System.getProperties;
import static utils.FieldUtils.*;
import static utils.SignalUtils.*;

public class DataBaseUtils {

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/baseproject?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "128071";

    private static Connection con;
    private static PreparedStatement pst;


    public static void getTable(ArrayList<String> table_list) {
        if (table_list == null)
            return;

        Properties properties = getProperties();
        String database = properties.getProperty(DB_URL);
        int startIndex = database.lastIndexOf(":");
        String databaseName = database.substring(startIndex + 1);

        DatabaseMetaData databaseMetaData;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            databaseMetaData = con.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(databaseName, null, null, new String[]{"TABLE"});

            while (resultSet.next()) {
                table_list.add(resultSet.getString("TABLE_NAME"));
            }
            con.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public static int search(String table, Object row, Map<String, Object> where) {
        StringBuilder sql = new StringBuilder("select ");
        Map<String, Object> map = getEntity(row);
        int p = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (p != 0) {
                sql.append(",");
            }
            String k = String.valueOf(entry.getKey());
            sql.append(k);
            p++;
        }
        String key = "";
        String val = "";
        for (Map.Entry<String, Object> entry : where.entrySet()) {
            key = entry.getKey();
            val = String.valueOf(entry.getValue());
            val = varType(row, val) == String.class ? '\'' + val + '\'' : val;
        }
        sql.append(" from ").append(table).append(" where ").append(key).append(" = ").append(val);
        System.out.println(sql);
        int code = DA_SEARCH_ERROR;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            pst = con.prepareStatement(sql.toString());
            Map<String, Object> result = new HashMap<>();
            ResultSet resultSet = pst.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 0; i < count; i++) {
                    result.put(resultSetMetaData.getColumnName(i + 1), resultSet.getString(i + 1));
                }
            }
            if (!result.isEmpty()) {
                code = DA_SEARCH_SUCCESS;
                setEntity(row, result);
            }
            con.close();
            pst.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return code;
    }

    public static int add(String table, Object row) {
        String sql = "insert into " + table + " ";
        Map<String, Object> map = getEntity(row);
        List<Class<?>> type = entityType(row);
        StringBuilder col = new StringBuilder("(");
        StringBuilder value = new StringBuilder("(");
        int p = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                if (p != 0) {
                    col.append(",");
                    value.append(",");
                }
                col.append(entry.getKey());
                if (type.get(p) == String.class) {
                    value.append('\'').append(entry.getValue()).append('\'');
                } else {
                    value.append(entry.getValue());
                }
            }
            p++;
        }
        col.append(")");
        value.append(")");
        sql += col + " values " + value;
        System.out.println(sql);
        int code = DA_UPDATE_ERROR;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            pst = con.prepareStatement(sql);
            code = pst.executeUpdate() == 0 ? DA_UPDATE_ERROR : DA_UPDATE_SUCCESS;
            con.close();
            pst.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }

    public static int modify(String table, Object row, Map<String, Object> where) {
        String sql = "update " + table + " set ";
        Map<String, Object> map = getEntity(row);
        List<Class<?>> type = entityType(row);
        StringBuilder col = new StringBuilder();
        int p = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() == null)
                continue;
            if (p != 0) {
                col.append(",");
            }
            if (type.get(p) == String.class) {
                col.append(entry.getKey()).append("=").append('\'').append(entry.getValue()).append('\'');
            } else {
                col.append(entry.getKey()).append("=").append(entry.getValue());
            }
            p++;
        }
        String key = "";
        String val = "";
        for (Map.Entry<String, Object> entry : where.entrySet()) {
            key = entry.getKey();
            val = String.valueOf(entry.getValue());
            val = varType(row, val) == String.class ? '\'' + val + '\'' : val;
        }
        sql += col + " where " + key + " = " + val;
        System.out.println(sql);
        int code = DA_UPDATE_ERROR;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            pst = con.prepareStatement(sql);
            code = pst.executeUpdate() == 0 ? DA_UPDATE_ERROR : DA_UPDATE_SUCCESS;
            con.close();
            pst.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }

    public static int delete(String table, Object row, Map<String, Object> where) {
        String key = "";
        String val = "";
        for (Map.Entry<String, Object> entry : where.entrySet()) {
            key = entry.getKey();
            val = String.valueOf(entry.getValue());
            val = varType(row, val) == String.class ? '\'' + val + '\'' : val;
        }
        String sql = "delete from " + table + " where " + key + " = " + val;
        System.out.println(sql);
        int code = DA_UPDATE_ERROR;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            pst = con.prepareStatement(sql);
            code = pst.executeUpdate() == 0 ? DA_UPDATE_ERROR : DA_UPDATE_SUCCESS;
            con.close();
            pst.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }

}



