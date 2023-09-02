/**
 * @author : Gathsara
 * created : 8/10/2023 -- 5:09 PM
 **/

package dto;

public class CustomerDTO {
    String id;
    String name;
    String address;
    int tp;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String name, String address, int tp) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tp = tp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }
}
