

public class News {
    private int newsId;
    private String title;
    private String text;

    public News(int newsId, String title, String text) {
        this.newsId = newsId;
        this.title = title;
        this.text = text;
    }

    public int getNewsId() {
        return newsId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return "News ID: " + newsId + ", Title: " + title + ", Text: " + text;
    }
}
