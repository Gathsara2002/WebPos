/**
 * @author : Gathsara
 * created : 8/23/2023 -- 8:38 PM
 **/

package servlet;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String oid = jsonObject.getString("oid");
        String date = jsonObject.getString("date");
        String total = jsonObject.getString("total");
        int count = jsonObject.getInt("count");

        try (Connection connection = pool.getConnection()) {

            connection.setAutoCommit(false);

            /*save order*/
            PreparedStatement pstm1 = connection.prepareStatement("insert into orders values(?,?,?,?)");
            pstm1.setObject(1, oid);
            pstm1.setObject(2, date);
            pstm1.setObject(3, count);
            pstm1.setObject(4, total);
            boolean isOrderSaved = pstm1.executeUpdate() > 0;

            if (!isOrderSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                System.out.println("order not saved");
            }

            /*save order details*/
            PreparedStatement pstm2 = connection.prepareStatement("insert  into orderDetails values (?,?,?,?,?,?)");

            JsonArray orderArray = jsonObject.getJsonArray("orderArray");

           /* for (JsonValue value : orderArray) {
                String cusId = value.asJsonObject().getString("cusId");
                String orderId = value.asJsonObject().getString("orderId");
                String code = value.asJsonObject().getString("code");
                String itemName = value.asJsonObject().getString("itemName");
                String qty = value.asJsonObject().getString("qty");
                String itemPrice = value.asJsonObject().getString("itemPrice");

                pstm2.setObject(1, orderId);
                pstm2.setObject(2, cusId);
                pstm2.setObject(3, code);
                pstm2.setObject(4, itemName);
                pstm2.setObject(5, qty);
                pstm2.setObject(6, itemPrice);
                boolean isOrderDetailsSaved = pstm2.executeUpdate() > 0;

                if (!isOrderDetailsSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    System.out.println("order details are not saved");
                }
            }*/

            for (int i = 0; i < orderArray.size() - 1; i++) {
                String cusId = orderArray.get(i).asJsonObject().getString("cusId");
                String orderId = orderArray.get(i).asJsonObject().getString("orderId");
                String code = orderArray.get(i).asJsonObject().getString("code");
                String itemName = orderArray.get(i).asJsonObject().getString("itemName");
                String qty = orderArray.get(i).asJsonObject().getString("qty");
                String itemPrice = orderArray.get(i).asJsonObject().getString("itemPrice");

                pstm2.setObject(1, orderId);
                pstm2.setObject(2, cusId);
                pstm2.setObject(3, code);
                pstm2.setObject(4, itemName);
                pstm2.setObject(5, qty);
                pstm2.setObject(6, itemPrice);
                boolean isOrderDetailsSaved = pstm2.executeUpdate() > 0;

                if (!isOrderDetailsSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    System.out.println("order details are not saved");
                }
            }

            /*update item qty*/
            PreparedStatement pstm3 = connection.prepareStatement("update  Item set name=?,price=?,qty=? where code=?");

            connection.commit();
            connection.setAutoCommit(true);

            System.out.println("Order placed successfully");
            JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("state", "ok");
            builder.add("message", "Successfully added !");
            builder.add("data", "");
            resp.getWriter().print(builder.build());

        } catch (SQLException e) {
            JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("state", "Error");
            builder.add("message", e.getLocalizedMessage());
            builder.add("data", "");
            resp.setStatus(500);
            resp.getWriter().print(builder.build());
        }
    }
}