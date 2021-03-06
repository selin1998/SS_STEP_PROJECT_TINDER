package org.tinder.step.dao;

import lombok.extern.log4j.Log4j2;
import org.tinder.step.db.DatabaseConnection;
import org.tinder.step.entity.Activity;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class ActivityDAO implements DAO<Activity> {
    DatabaseConnection db = new DatabaseConnection();
    Connection con;

    public ActivityDAO() {
        this.con = db.connection();
    }

    public boolean add(Activity act) {
        boolean result = false;
        int user_id = act.getUser_id();
        ZonedDateTime logout_time = act.getLogout_time();

        String sql2 = "INSERT INTO activity(user_id,logout_time) VALUES(?,?) ON CONFLICT ON CONSTRAINT activity_pk DO UPDATE SET logout_time=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql2);
            Timestamp timestamp = new Timestamp(logout_time.toInstant().getEpochSecond() * 1000L);
            ps.setInt(1, user_id);
            ps.setTimestamp(2, timestamp);
            ps.setTimestamp(3, timestamp);
            result = ps.execute();

        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    @Override
    public boolean remove(Activity object) {
        return false;
    }

    public Optional<Activity> get(int id) {
        Activity act = null;

        String sql = "SELECT * FROM activity WHERE user_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                act = new Activity(rs.getInt("user_id")
                        , rs.getTimestamp("logout_time").toInstant().atZone(ZoneId.systemDefault()));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());

        }
        return Optional.ofNullable(act);
    }


    @Override
    public List<Activity> getAll() {
        String sql = "SELECT * FROM activity";


        List<Activity> activities = new ArrayList<>();
        Activity act = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                act = new Activity(rs.getInt("user_id")
                        , rs.getTimestamp("logout_time").toInstant().atZone(ZoneId.systemDefault()));
                activities.add(act);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return activities;


    }

}
