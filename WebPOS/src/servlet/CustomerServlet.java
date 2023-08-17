/**
 * @author : Gathsara
 * created : 8/10/2023 -- 12:28 PM
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

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("select * from Customer");
            ResultSet resultSet = pstm.executeQuery();
            /*add response header*/
            resp.addHeader("Content-Type", "application/json");

            /*create json array*/
            JsonArrayBuilder allCustomers = Json.createArrayBuilder();

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                int tp = resultSet.getInt(4);

                /*create json object to add json array*/
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
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("cusId");
        String name = req.getParameter("cusName");
        String address = req.getParameter("cusAddress");
        String tp = req.getParameter("cusTp");
        String option = req.getParameter("option");


        /*use switch because add , delete , update all task execute under post method*/

        switch (option) {

            case "ADD":
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
                    PreparedStatement pstm = connection.prepareStatement("insert into Customer values(?,?,?,?)");
                    pstm.setObject(1, id);
                    pstm.setObject(2, name);
                    pstm.setObject(3, address);
                    pstm.setObject(4, tp);
                    boolean isAdded = pstm.executeUpdate() > 0;

                    if (isAdded) {
                        System.out.println("customer added successfully");
                    }

                    /*no need to redirect request because use of ajax, page is not refresh */
                    /*resp.sendRedirect("customer");*/

                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "DELETE":
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
                    PreparedStatement pstm = connection.prepareStatement("delete from Customer where id=?");
                    pstm.setObject(1, id);

                    boolean isDeleted = pstm.executeUpdate() > 0;

                    if (isDeleted) {
                        System.out.println("customer deleted successfully");
                    }

                    /*resp.sendRedirect("customer");*/

                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "UPDATE":
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
                    PreparedStatement pstm = connection.prepareStatement("update  Customer set name=?,address=?,telephone=? where id=?");
                    pstm.setObject(1, name);
                    pstm.setObject(2, address);
                    pstm.setObject(3, tp);
                    pstm.setObject(4, id);

                    boolean isUpdated = pstm.executeUpdate() > 0;

                    if (isUpdated) {
                        System.out.println("customer updated");
                    }

                    /*resp.sendRedirect("customer");*/

                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                resp.sendRedirect("customer.html");
                break;
        }
    }
}
