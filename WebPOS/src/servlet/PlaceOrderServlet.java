/**
 * @author : Gathsara
 * created : 8/23/2023 -- 8:38 PM
 **/

package servlet;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       /* *//*get customer details*//*
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("select * from Customer");
            ResultSet resultSet = pstm.executeQuery();

            resp.addHeader("Content-Type", "application/json");

            *//*create json array*//*
            JsonArrayBuilder allCustomers = Json.createArrayBuilder();

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                int tp = resultSet.getInt(4);

                *//*create json object to add json array*//*
                JsonObjectBuilder customerObj = Json.createObjectBuilder();
                customerObj.add("id", id);
                customerObj.add("name", name);
                customerObj.add("address", address);
                customerObj.add("tp", tp);

                allCustomers.add(customerObj.build());
            }

            resp.getWriter().print(allCustomers.build());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/

       /* *//*get item details*//*
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("select * from Item");
            ResultSet resultSet = pstm.executeQuery();

            resp.addHeader("Content-Type", "application/json");

            JsonArrayBuilder allItems = Json.createArrayBuilder();

            while (resultSet.next()) {
                String code = resultSet.getString(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                double qty = resultSet.getDouble(4);

                JsonObjectBuilder itemObj = Json.createObjectBuilder();
                itemObj.add("code", code);
                itemObj.add("name", name);
                itemObj.add("price", price);
                itemObj.add("qty", qty);

                allItems.add(itemObj.build());
            }

            resp.getWriter().print(allItems.build());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/
    }
}
