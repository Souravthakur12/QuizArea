package apps.develop.quizarea.Model;

public class LatestData {
    String image;
    String title;
    LatestData(){}

    public LatestData(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
