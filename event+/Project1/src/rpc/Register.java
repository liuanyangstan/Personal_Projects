package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {

  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Register() {
    super();
    // TODO auto-generated constructor stub
  }


  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    DBConnection connection = DBConnectionFactory.getConnection();
    try {
      JSONObject input = RpcHelper.readJSONObject(request);
      String userId = input.getString("user_id");
      String password = input.getString("password");
      String firstname = input.getString("first_name");
      String lastname = input.getString("last_name");

      JSONObject obj = new JSONObject();
      if (connection.registerUser(userId, password, firstname, lastname)) {
        obj.put("status", "OK");
      } else {
        obj.put("status", "User Already Exists");
      }
      RpcHelper.writeJsonObject(response, obj);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      connection.close();
    }
  }

}
