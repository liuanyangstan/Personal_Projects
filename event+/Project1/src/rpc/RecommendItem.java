package rpc;

import entity.Item;
import org.json.JSONArray;
import recommendation.GeoRecommendation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/recommendation")
public class RecommendItem extends HttpServlet {
  private static final long serialVersionUID = 1L;


  /**
   * @see HttpServlet#HttpServlet()
   */
  public RecommendItem() {
    super();
    // TODO auto-generated constructor stub
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    response.setContentType("application/json");
//    PrintWriter writer = response.getWriter();
//
//    JSONArray array = new JSONArray();
//    try {
//      array.put(new JSONObject().put("name", "abcd").put("address", "San Francisco").put("time", "01/01/2017"));
//      array.put(new JSONObject().put("name", "1234").put("address", "San Jose").put("time", "01/01/2017"));
//    } catch (JSONException e) {
//      e.printStackTrace();
//    }
//    writer.print(array);
//    writer.close();

    HttpSession session = request.getSession(false);
    if (session == null) {
      response.setStatus(403);
      return;
    }

    String userId = session.getAttribute("user_id").toString();
    // String userId = request.getParameter("user_id");

    double lat = Double.parseDouble(request.getParameter("lat"));
    double lon = Double.parseDouble(request.getParameter("lon"));

    GeoRecommendation recommendation = new GeoRecommendation();
    List<Item> items = recommendation.recommendItems(userId, lat, lon);
    JSONArray array = new JSONArray();
    for (Item item : items) {
      array.put(item.toJSONObject());
    }

    RpcHelper.writeJsonArray(response, array);
  }
}
