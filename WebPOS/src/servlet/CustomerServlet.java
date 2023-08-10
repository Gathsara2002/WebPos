/**
 * @author : Gathsara
 * created : 8/10/2023 -- 12:28 PM
 **/

package servlet;

import dto.CustomerDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webPos", "root", "1234");
            PreparedStatement pstm = connection.prepareStatement("select * from Customer");
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                int tp = Integer.parseInt(resultSet.getString(4));

                allCustomers.add(new CustomerDTO(id, name, address, tp));
            }
            req.setAttribute("keyOne", allCustomers);

            req.getRequestDispatcher("customer.jsp").forward(req, resp);

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

                    resp.sendRedirect("customer.jsp");

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

                    resp.sendRedirect("customer.jsp");

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

                    resp.sendRedirect("customer.jsp");

                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                resp.sendRedirect("customer.jsp");
                break;
        }
    }
}
