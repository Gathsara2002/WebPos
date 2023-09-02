/**
 * @author : Gathsara
 * created : 8/25/2023 -- 9:54 PM
 **/

package dto;

public class OrderDTO {
    private String orderId;
    private String date;
    int itemCount;
    double total;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String date, int itemCount, double total) {
        this.orderId = orderId;
        this.date = date;
        this.itemCount = itemCount;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
