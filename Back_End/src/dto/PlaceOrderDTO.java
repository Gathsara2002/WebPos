/**
 * @author : Gathsara
 * created : 8/23/2023 -- 8:25 PM
 **/

package dto;

public class PlaceOrderDTO {
    private String oid;
    private String cid;
    private String code;
    private String itemName;
    private String price;
    private String qty;
    private int total;
    private String date;

    public PlaceOrderDTO() {
    }

    public PlaceOrderDTO(String oid, String cid, String code, String itemName, String price, String qty, int total, String date) {
        this.oid = oid;
        this.cid = cid;
        this.code = code;
        this.itemName = itemName;
        this.price = price;
        this.qty = qty;
        this.total = total;
        this.date = date;
    }

    public PlaceOrderDTO(String code, String itemName, String price, String qty, int total) {
        this.code = code;
        this.itemName = itemName;
        this.price = price;
        this.qty = qty;
        this.total = total;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
