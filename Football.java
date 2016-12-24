
package football;

import java.sql.*;


public class Football {
    
private static final String USERNAME="HIDDEN";
private static final String PASSWORD="HIDDEN";
private static final String CONN_STRING="jdbc:mysql://localhost:3306/football";


public static void print_contents(String a){
     try
    {
      Connection conn = null;
      conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);

      String query = ("SELECT * FROM " + a);

      Statement st = conn.createStatement();
      
      ResultSet rs = st.executeQuery(query);
      
      while (rs.next())
      {
        int id = rs.getInt("points");
        String Name = rs.getString("playername");
        
        String position = rs.getString("position");
       String team = rs.getString("team");
        String salary = rs.getString("salary");
        
        System.out.format("%s, %s, %s, %s, %s\n",id, Name, position, team, salary);
      }
      System.out.println("-------------------------------\n");
      st.close();
    
    }
       catch (Exception e)
      {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
}

public static void DeleteRow(int points) throws SQLException
  {
  try
    {
      Connection conn = null;
      conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
      String query = "delete from searchPlayer where points = ?";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt(1, points);

      preparedStmt.execute();
      
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
  }
@SuppressWarnings("empty-statement")
    public static void main(String[] args) throws SQLException {
       Connection conn = null;
       conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
           System.out.println("connected");
           
           print_contents("searchPlayer");
       
          try {
           conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
           System.out.println("connected");
           Statement stmt = (Statement) conn.createStatement();
           String insert = "INSERT INTO searchPlayer(playername,points,position,team,salary)" 
                  + "VALUES('Harold',12,'wide receiver','jets','2,000,000')";
           
           stmt.executeUpdate(insert);
           
       }catch (SQLException e){
           
           System.err.println(e);
           
       }
             
        print_contents("searchPlayer");
        DeleteRow(12);

       
        System.out.println("-------------------------------\n");

    print_contents("searchPlayer");
 }
    
}
     
