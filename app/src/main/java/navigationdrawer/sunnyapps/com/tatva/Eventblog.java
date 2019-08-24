package navigationdrawer.sunnyapps.com.tatva;

/**
 * Created by DELL-PC on 3/4/2019.
 */

public class Eventblog {
String name;
    String text;
    String image;
 public Eventblog(String name, String text, String image) {
        this.name = name;
        this.text = text;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Eventblog() {

    }
}

