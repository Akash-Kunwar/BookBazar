package navigationdrawer.sunnyapps.com.tatva;

/**
 * Created by DELL-PC on 3/4/2019.
 */

public class User {
String name,usn,classs,event,phoneno;

    public User(String name, String usn, String classs, String event,String phoneno) {
        this.name = name;
        this.usn = usn;
        this.classs = classs;
        this.event = event;
        this.phoneno=phoneno;
    }

    public User() {
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
