package worker;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import java.sql.*;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class Worker {
  public static void main(String[] args) {
    try {
      String redisHost = System.getenv("REDIS_HOST");
      String pgHost = System.getenv("POSTGRES_HOST");
      Jedis redis = connectToRedis(redisHost);
      Connection dbConn = connectToDB(pgHost);

      System.err.println("Watching vote queue");

      while (true) {
        String voteJSON = redis.blpop(0, "votes").get(1);
        JSONObject voteData = new JSONObject(voteJSON);
        String voterID = voteData.getString("voter_id");
        String vote = voteData.getString("vote");

        System.err.printf("Processing vote for '%s' by '%s'\n", vote, voterID);
        updateVote(dbConn, voterID, vote);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  static void updateVote(Connection dbConn, String voterID, String vote) throws SQLException {
    PreparedStatement insert = dbConn.prepareStatement(
      "INSERT INTO votes (id, vote) VALUES (?, ?)");
    insert.setString(1, voterID);
    insert.setString(2, vote);

    try {
      insert.executeUpdate();
    } catch (SQLException e) {
      PreparedStatement update = dbConn.prepareStatement(
        "UPDATE votes SET vote = ? WHERE id = ?");
      update.setString(1, vote);
      update.setString(2, voterID);
      update.executeUpdate();
    }
  }

  static Jedis connectToRedis(String host) {
    Jedis conn = new Jedis(host);

    while (true) {
      try {
        conn.keys("*");
        break;
      } catch (JedisConnectionException e) {
        System.err.println("Waiting for redis");
        sleep(1000);
      }
    }

    System.err.println("Connected to redis");
    return conn;
  }

  static Connection connectToDB(String host) throws SQLException {
    Connection conn = null;

    try {

      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://${POSTGRES_HOST}/5432?user=${POSTGRES_USER}&password=${POSTGRES_PASS}";

      // Do something with the Connection

         System.out.println("Test Connection Successful");

      } catch (SQLException ex) {

         // handle any errors

         System.out.println("SQLException: " + ex.getMessage());

         System.out.println("SQLState: " + ex.getSQLState());

         System.out.println("VendorError: " + ex.getErrorCode());

      } catch (ClassNotFoundException ex) {

         System.out.println("Class Not Found: " + ex.getMessage());

      PreparedStatement st = conn.prepareStatement(
        "CREATE TABLE IF NOT EXISTS votes (id VARCHAR(255) NOT NULL UNIQUE, vote VARCHAR(255) NOT NULL)");
      st.executeUpdate();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }

    System.err.println("Connected to db");
    return conn;
  }

  static void sleep(long duration) {
    try {
      Thread.sleep(duration);
    } catch (InterruptedException e) {
      System.exit(1);
    }
  }
}
