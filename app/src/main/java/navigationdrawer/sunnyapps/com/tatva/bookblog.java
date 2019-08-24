package navigationdrawer.sunnyapps.com.tatva;

/**
 * Created by DELL-PC on 3/17/2019.
 */

public class bookblog {
    String name,price,sem,sub,condition,phone,imageurl,uid;

    public bookblog() {
    }

    public bookblog(String name, String price, String sem, String sub, String condition, String phone, String imageurl, String uid) {
        this.name = name;
        this.price = price;
        this.sem = sem;
        this.sub = sub;
        this.condition = condition;
        this.phone = phone;
        this.imageurl = imageurl;
        this.uid = uid;

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
