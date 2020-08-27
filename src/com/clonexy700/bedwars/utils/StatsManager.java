package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;

import java.sql.ResultSet;
import java.util.UUID;

public class StatsManager {
    private Main main;

    public StatsManager(Main main) {
        this.main = main;
    }

    public void setup() {
        main.mySQL.update("CREATE TABLE IF NOT EXISTS `stats` (`id` INT NOT NULL AUTO_INCREMENT , `uuid` TEXT NOT NULL, " +
                "`name` TEXT NOT NULL, `kills` INT NOT NULL, `deaths` INT NOT NULL, `breaks` INT NOT NULL, `wins` INT NOT NULL, " +
                " `plays` INT NOT NULL, PRIMARY KEY  (`id`)) ENGINE = MyISAM;");
    }

    public int getInt(String uuid, String col) {
        try {
            ResultSet resultSet = main.mySQL.query("select * FROM `stats` WHERE `uuid` = '" + uuid + "'");
            while (resultSet.next()) {
                return resultSet.getInt(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getString(String uuid, String col) {
        try {
            ResultSet resultSet = main.mySQL.query("SELECT * FROM `stats` WHERE `uuid` = '" + uuid + "'");
            while (resultSet.next()) {
                return resultSet.getString(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exists(String uuid) {
        if (getString(uuid, "name") == null) {
            return false;
        } else {
            return true;
        }
    }

    public void setString(String uuid, String col, String value) {
        main.mySQL.update("UPDATE `stats` SET" + col + "` = '" + value + "' WHERE `uuid` = '" + uuid + "'");
    }

    public void setInt(String uuid, String col, int count) {
        main.mySQL.update("UPDATE `stats` SET" + col + "` = '" + count + "' WHERE `uuid` = '" + uuid + "'");
    }

    public void addInt(String uuid, String col, int count) {
        setInt(uuid, col, count + getInt(uuid, col));
    }

    public void setupPlayer(String uuid) {
        if (!exists(uuid)) {
            main.mySQL.update("INSERT INTO `stats` (`uuid`, `name`, `kills`, `deaths`, `breaks`, `wins`, `plays`) VALUES" +
                    "('" + uuid + "', " + UUIDFetcher.getName(UUID.fromString(uuid)) + "', '0', '0', '0', '0', '0')");
        }
    }
}
