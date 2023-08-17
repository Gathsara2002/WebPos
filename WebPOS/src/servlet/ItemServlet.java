/**
 * @author : Gathsara
 * created : 8/10/2023 -- 6:18 PM
 **/

package servlet;

import dto.ItemDTO;

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
import java.util.ArrayList;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("select * from Item");
            ResultSet resultSet = pstm.executeQuery();

            JsonArrayBuilder allItems = Json.createArrayBuilder();

            while (resultSet.next()) {
                String code = resultSet.getString(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                double qty = resultSet.getDouble(4);

                JsonObjectBuilder itemObj = Json.createObjectBuilder();
                itemObj.add("code",code);
                itemObj.add("name",name);
                itemObj.add("price",price);
                itemObj.add("qty",qty);

                allItems.add(itemObj.build());
            }

            resp.getWriter().print(allItems.build());


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String name = req.getParameter("itemName");
        String qty = req.getParameter("qty");
        String price = req.getParameter("price");
        String option = req.getParameter("option");

        switch (option) {

            case "ADD":
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
                    PreparedStatement pstm = connection.prepareStatement("insert into Item values (?,?,?,?)");
                    pstm.setObject(1, code);
                    pstm.setObject(2, name);
                    pstm.setObject(3, price);
                    pstm.setObject(4, qty);

                    int i = pstm.executeUpdate();
                    if (i > 0) {
                        System.out.println("item saved");
                    }
                    /*resp.sendRedirect("item");*/


                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "DELETE":
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
                    PreparedStatement pstm = connection.prepareStatement("delete from Item where code=?");
                    pstm.setObject(1, code);

                    int i = pstm.executeUpdate();
                    if (i > 0) {
                        System.out.println("item deleted");
                    }
                    /*resp.sendRedirect("item");*/


                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "UPDATE":
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
                    PreparedStatement pstm = connection.prepareStatement("update  Item set name=?,price=?,qty=? where code=?");
                    pstm.setObject(1, name);
                    pstm.setObject(2, price);
                    pstm.setObject(3, qty);
                    pstm.setObject(4, code);

                    int i = pstm.executeUpdate();
                    if (i > 0) {
                        System.out.println("item updated");
                    }
                    /*resp.sendRedirect("item");*/

                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                resp.sendRedirect("item");
                break;
        }
    }
}
