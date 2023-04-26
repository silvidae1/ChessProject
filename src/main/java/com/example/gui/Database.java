package com.example.gui;
import java.sql.*;

public class Database
{
    static final String DB_URL = "jdbc:postgresql://db.fe.up.pt:5432/pswa0205";
    static final String USER = "pswa0205";
    static final String PASSWORD = "potedeaço";

    private static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Connection c;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        return c;
    }

    public static void create_Account(String username, String password)
    {
        Connection c;
        Statement stmt;

        try {
            c = getConnection();
            stmt = c.createStatement();

            String sql = "INSERT INTO chess.players(username,password,games,victories,defeats,draws,rating)" + "VALUES('"+username+"', '"+password+"', 0, 0, 0, 0, 0)";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
        System.out.println("Account created successfully");
    }

    public static void delete_Account(String username)
    {
        Connection c;
        Statement stmt;

        try
        {
            c = getConnection();
            stmt = c.createStatement();

            String sql = "DELETE from chess.players where username = '"+username+"';";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
        System.out.println("Account deleted successfully");
    }
    public static boolean checks_if_exists_Username(String username)
    {
        Connection c;
        Statement stmt;

        try
        {
            c = getConnection();
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM chess.players;");
            while(rs.next())
            {
                if(username.equals(rs.getString("username")))
                {
                    rs.close();
                    stmt.close();
                    c.close();

                    System.out.println("Username already exists");

                    return true;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
        return false;
    }
    public static boolean login_Account(String username, String password)
    {
        Connection c;
        Statement stmt;

        try
        {
            c = getConnection();
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM chess.players");
            while(rs.next())
            {
                if(username.equals(rs.getString("username")) && password.equals(rs.getString("password")))
                {
                    rs.close();
                    stmt.close();
                    c.close();

                    System.out.println("Account logged in");

                    return true;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
        return false;
    }

    public static boolean change_Password(String username, String old_password, String new_password)
    {
        Connection c;
        Statement stmt;

        try
        {
            c = getConnection();
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM chess.players where username = '"+username+"'");
            rs.next();
            if(old_password.equals(rs.getString("password")))
            {
                String sql = "UPDATE chess.players set password = '"+new_password+"' where username = '"+username+"';";
                stmt.executeUpdate(sql);
                c.commit();
                return true;
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
        return false;
    }

    public static void update_Database(String username_1, String username_2, int game)
    {
        Connection c;
        Statement stmt;

        try
        {
            c = getConnection();
            stmt = c.createStatement();

            update_Games(username_1, stmt);
            update_Games(username_2, stmt);

            switch (game) {
                case 0 -> // DRAW
                {
                    update_Draws(username_1, stmt);
                    update_Draws(username_2, stmt);
                }
                case 1 -> // PLAYER 1
                {
                    update_Victories(username_1, stmt);
                    update_Defeats(username_2, stmt);
                }
                case 2 -> // PLAYER 2
                {
                    update_Victories(username_2, stmt);
                    update_Defeats(username_1, stmt);
                }
            }
            c.commit();

            stmt.close();
            c.close();
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
    }
    private static void update_Games(String username, Statement stmt) throws SQLException
    {
        ResultSet rs = stmt.executeQuery("SELECT * FROM chess.players where username = '"+username+"'");
        rs.next();
        int games = rs.getInt("games") + 1;
        String sql = "Update chess.players set games = '"+games+"' where username = '"+ username +"'";
        stmt.executeUpdate(sql);
        rs.close();
    }
    private static void update_Victories(String username, Statement stmt) throws SQLException
    {
        ResultSet rs = stmt.executeQuery("SELECT * FROM chess.players where username = '"+username+"'");
        rs.next();
        int victories = rs.getInt("victories") + 1;
        String sql = "Update chess.players set victories = '"+victories+"' where username = '"+ username +"'";
        stmt.executeUpdate(sql);
        rs.close();
    }
    private static void update_Defeats(String username, Statement stmt) throws SQLException
    {
        ResultSet rs = stmt.executeQuery("SELECT * FROM chess.players where username = '"+username+"'");
        rs.next();
        int defeats = rs.getInt("defeats") + 1;
        String sql = "Update chess.players set defeats = '"+defeats+"' where username = '"+ username +"'";
        stmt.executeUpdate(sql);
        rs.close();
    }
    private static void update_Draws(String username, Statement stmt) throws SQLException
    {
        ResultSet rs = stmt.executeQuery("SELECT * FROM chess.players where username = '"+username+"'");
        rs.next();
        int draws = rs.getInt("draws") + 1;
        String sql = "Update chess.players set draws = '"+draws+"' where username = '"+ username +"'";
        stmt.executeUpdate(sql);
        rs.close();
    }
    public static int[] stats_Players(String username)
    {
        int[] stats = new int[5];

        Connection c;
        Statement stmt;

        try {
            c = getConnection();
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM chess.players where username = '" + username + "'");
            while (rs.next()){
                stats[0] = rs.getInt("games");
                stats[1] = rs.getInt("victories");
                stats[2] = rs.getInt("defeats");
                stats[3] = rs.getInt("draws");
                stats[4] = rs.getInt("rating");
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
        return stats;
    }
}


