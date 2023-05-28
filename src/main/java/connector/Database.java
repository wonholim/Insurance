package connector;

import java.sql.*;
public class Database {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private String user = "root";
    private String pw = "12345678";
    public Database() {
        this.con = null;
        this.stmt = null;
        this.rs = null;
    }
    public Connection initConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("jdbcError");
        }
        try {
          this.con = DriverManager.getConnection("jdbc:mysql://localhost/JDBC?serverTimezone=UTC", user, pw);
          this.stmt = this.con.createStatement();
          this.stmt.executeUpdate("USE JDBC");

          return con;
        } catch (SQLException e) {
            System.out.println("connectionError");
        }
        return null;
    }

    public void releaseConnection(Connection con) {
        try {
            if (con != null)
                con.close();
            System.out.println("DB 연결이 정상적으로 해제되었습니다.");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public boolean create(String query) {
        try {
            stmt = con.createStatement();
            if (!stmt.execute(query)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String query) {
        try {
            stmt = con.createStatement();
            if (!stmt.execute(query)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet retrieve(String query) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(String query) {
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean authentication(String query, String[] user) {
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user[0]);
            statement.setString(2, user[1]);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isRegister(String query, String userName) {
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, userName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean checkUnderWriting(String query, String[] employee) {
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, employee[0]);
            statement.setInt(2, Integer.parseInt(employee[1]));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
