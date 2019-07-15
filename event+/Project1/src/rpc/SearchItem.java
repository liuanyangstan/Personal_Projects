package rpc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;
import external.TicketMasterAPI;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class rpc.SearchItem
 */
@WebServlet("/search")
public class SearchItem extends HttpServlet {
  private static final long serialVersionUID = 1L;


  /**
   * @see HttpServlet#HttpServlet()
   */
  public SearchItem() {
    super();
    // TODO auto-generated constructor stub
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    JSONArray array = new JSONArray();
//
//    try {
//      array.put(new JSONObject().put("username", "abcd").put("address", "San Francisco").put("time", "01/01/2017"));
//      array.put(new JSONObject().put("username", "1234").put("address", "San Jose").put("time", "01/02/2017"));
//
//    } catch (JSONException e) {
//      e.printStackTrace();
//    }
    HttpSession session = request.getSession(false);
    if (session == null) {
      response.setStatus(403);
      return;
    }

    String userId = session.getAttribute("user_id").toString();
    // String userId = request.getParameter("user_id");
    double lat = Double.parseDouble(request.getParameter("lat"));
    double lon = Double.parseDouble(request.getParameter("lon"));
    // Term can be empty or null.
    String term = request.getParameter("term");

    DBConnection connection = DBConnectionFactory.getConnection();
    try {
      List<Item> items = connection.searchItems(lat, lon, term);
      Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);

      JSONArray array = new JSONArray();
      for (Item item : items) {
        JSONObject obj = item.toJSONObject();
        obj.put("favorite", favoritedItemIds.contains(item.getItemId()));
        array.put(obj);
      }
      RpcHelper.writeJsonArray(response, array);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      connection.close();
    }

//    TicketMasterAPI tmAPI = new TicketMasterAPI();
//    List<Item> items = tmAPI.search(lat, lon, null);
//
//    JSONArray array = new JSONArray();
//    for (Item item : items) {
//      array.put(item.toJSONObject());
//    }
//
//    RpcHelper.writeJsonArray(response, array);
  }
}
