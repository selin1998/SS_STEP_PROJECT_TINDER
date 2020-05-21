package org.tinder.step.dao;

import org.tinder.step.db.DatabaseConnection;
import org.tinder.step.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserDAO implements DAO<User> {
    DatabaseConnection db=new DatabaseConnection();
    Connection con;

    public UserDAO() throws SQLException {

        this.con = db.connection();
    }

    @Override
    public User get(int id) {
        String sql="SELECT * FROM users WHERE user_id=? ";
        User user=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                user=new User(rs.getInt("user_id")
                        ,rs.getString("login")
                        , rs.getString("password")
                        , rs.getString("name")
                        , rs.getString("surname")
                        , rs.getString("job")
                        , rs.getString("photoLink"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public List<User> getAllBy(Predicate<User> p) {
        return getAll().stream().filter(p).collect(Collectors.toList());
    }

    @Override
    public List<User> getAll() {
        String sql="SELECT * FROM users";
        User user=null;
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                user=new User(rs.getInt("user_id")
                        , rs.getString("login")
                        , rs.getString("password")
                        , rs.getString("name")
                        , rs.getString("surname")
                        , rs.getString("job")
                        , rs.getString("photolink"));
                users.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean add(User user)  {
        boolean result=false;
        String sql="INSERT INTO users (login,password,name,surname,job,photoLink,user_id) VALUES(?,?,?,?,?,?,?)";
        String sql2="SELECT MAX(user_id) from users ";

        PreparedStatement ps2= null;
        try {
            ps2 = con.prepareStatement(sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs= null;
        try {
            rs = ps2.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int max=0;
            while(true){
                try {
                    if (!rs.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    max=rs.getInt("max")+1;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }


        try {
            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,user.getLogin());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getName());
            ps.setString(4,user.getSurname());
            ps.setString(5,user.getJob());
            ps.setString(6,user.getPhotoLink());
            ps.setInt(7,max);
            ps.executeUpdate();
            result=true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;


    }

        @Override
    public boolean remove(User user) {
        String sql="DELETE FROM users WHERE user_id=?";
        boolean result=false;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,user.getUser_id());
            ps.executeUpdate();
            result=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //get user_id by login and password
    public int getUserId(String login,String psw) {
        String sql="SELECT user_id FROM users WHERE login=? and password=?";
        int result=0;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,login);
            ps.setString(2,psw);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                result = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // get user by login and password
    public User getByLoginAndPassword(User user) {  //String login,String psw
        User result = null;
        String sql = "SELECT * FROM users WHERE login=? AND password=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,user.getLogin());
            ps.setString(2,user.getPassword());
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                result=new User(rs.getInt("user_id")
                        , rs.getString("login")
                        , rs.getString("password")
                        , rs.getString("name")
                        , rs.getString("surname")
                        , rs.getString("job")
                        , rs.getString("photoLink"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public User getUserByLogin(User user) {
        String sql="SELECT * FROM users WHERE login=?";
        User result=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,user.getLogin());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int user_id = rs.getInt("user_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String photoLink = rs.getString("photoLink");
                String job = rs.getString("job");

                result=new User(user_id,login,password,name,surname,job,photoLink);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}