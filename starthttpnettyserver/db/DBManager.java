package starthttpnettyserver.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import starthttpnettyserver.entity.GlobalStatistic;
import starthttpnettyserver.entity.IPRequest;
import starthttpnettyserver.entity.UrlRedirect;

public class DBManager {

    public static final String TABLE_REQUEST_COUNT = "http_server_db.request_count";
    public static final String TABLE_URL_COUNT = "url_redirect";
    public static final String TABLE_GLOBAL_STATISTIC = "global_statistic";

    public static void addIpCount(String ip) {
        String query = "SELECT ip_count FROM " + TABLE_REQUEST_COUNT + " WHERE ip = '" + ip + "';";
        ResultSet result = DatabaseExecutor.carryOutSelect(query);
        try {
            if (!result.next()) {
                String queryInsertNewIP = "INSERT INTO " + TABLE_REQUEST_COUNT + " (ip, ip_count, last_query_time) VALUES ('" + ip + "', '1', '" + new Date() + "');";
                DatabaseExecutor.carryOutInsert(queryInsertNewIP);
            } else {
                int ip_count = result.getInt("ip_count") + 1;
                String queryUpadateIPCount = "UPDATE " + TABLE_REQUEST_COUNT + " SET ip_count = '" + ip_count + "', last_query_time =  '" + new Date() + "' WHERE ip =  '" + ip + "';";
                DatabaseExecutor.carryOutInsert(queryUpadateIPCount);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void addURLRedirect(String url) {

        String query = "SELECT url_count FROM " + TABLE_URL_COUNT + " WHERE url = '" + url + "';";

        ResultSet result = DatabaseExecutor.carryOutSelect(query);

        try {
            if (!result.next()) {
                String queryInsertNewurl = "INSERT INTO " + TABLE_URL_COUNT + " (url, url_count) VALUES ('" + url + "', '1');";
                DatabaseExecutor.carryOutInsert(queryInsertNewurl);

            } else {
                int url_count = result.getInt("url_count") + 1;
                String queryUrlIPCount = "UPDATE " + TABLE_URL_COUNT + " SET url_count = '" + url_count + "' WHERE url =  '" + url + "';";
                DatabaseExecutor.carryOutInsert(queryUrlIPCount);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void addAllStatisticRequest(String src_ip, String uri, int sent_bytes, int received_bytes, int speed_transmission) {
        String queryInsertNewurl = "INSERT INTO " + TABLE_GLOBAL_STATISTIC + "( src_ip, uri, time_stemp, sent_bytes, received_bytes, speed_transmission ) VALUES ('" + src_ip + "', '" + uri + "', '" + new Date() + "', '" + sent_bytes + "', '" + received_bytes + "', '" + speed_transmission + "');";
        DatabaseExecutor.carryOutInsert(queryInsertNewurl);
    }

    public static int getNumberOfAllRequest() {
        int num = 0;
        String query = "SELECT ip_count FROM " + TABLE_REQUEST_COUNT + ";";
        ResultSet result = DatabaseExecutor.carryOutSelect(query);
        try {
            while (result.next()) {
                int count = result.getInt("ip_count");
                num += count;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return num;
    }
    public static List<IPRequest> getAllIPRequest() {
        List<IPRequest> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_REQUEST_COUNT + ";";
        ResultSet result = DatabaseExecutor.carryOutSelect(query);
        try {
            while (result.next()) {
                list.add(new IPRequest(result.getString("ip"), result.getInt("ip_count"), result.getString("last_query_time")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public static List<UrlRedirect> getAllUrlRedirect() {
        List<UrlRedirect> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_URL_COUNT + ";";
        ResultSet result = DatabaseExecutor.carryOutSelect(query);
        try {
            while (result.next()) {
                list.add(new UrlRedirect(result.getString("url"), result.getInt("url_count")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    public static List<GlobalStatistic> getAllStatistic() {

        List<GlobalStatistic> list = new ArrayList();

        String query = "SELECT * FROM " + TABLE_GLOBAL_STATISTIC + ";";
        ResultSet result = DatabaseExecutor.carryOutSelect(query);
        try {
            while (result.next()) {
                list.add(new GlobalStatistic(result.getString("src_ip"), result.getString("uri"), result.getString("time_stemp"), result.getInt("sent_bytes"), result.getInt("received_bytes"), result.getInt("speed_transmission")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public static int searchUniqueRequest() {
        String request = "SELECT SUM(ip_count) FROM " + TABLE_REQUEST_COUNT + " WHERE ip_count = '1';";
        ResultSet result = DatabaseExecutor.carryOutSelect(request);

        int theNumberOfUniqueQueries = 0;
        try {
            result.next();
            theNumberOfUniqueQueries = result.getInt("SUM(ip_count)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return theNumberOfUniqueQueries;
    }
}
