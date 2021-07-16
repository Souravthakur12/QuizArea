package apps.develop.quizarea.Model;

public class CategoryCard {

    private String title,questions,difficulty,image1,image2 ,catid;


    public CategoryCard() {
    }

    public CategoryCard(String title, String questions, String difficulty, String image1, String image2,String catid) {
        this.title = title;
        this.questions = questions;
        this.difficulty = difficulty;
        this.image1 = image1;
        this.image2 = image2;
        this.catid = catid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }
}
