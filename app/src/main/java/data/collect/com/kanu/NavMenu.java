package data.collect.com.kanu;

/**
 * Created by george on 1/22/2016.
 */
public class NavMenu {
    NavMenu (int img,String tittle,String desc){
        setImg(img);
        setDescription(desc);
        setTittle(tittle);
    }
    public int img;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String tittle;
    public String description;
}
