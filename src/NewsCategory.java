import java.util.ArrayList;

public class NewsCategory {
    private int categoryId;
    private String categoryName;
    private ArrayList<News> newsList;

    public NewsCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.newsList = new ArrayList<>();
    }

    public void addNews(News news) {
        this.newsList.add(news);
    }

    public ArrayList<News> getNewsList() {
        return this.newsList;
    }

    public void deleteNews(int newsId) {
        newsList.removeIf(news -> news.getNewsId() == newsId);
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category ID: " + categoryId + ", Category Name: " + categoryName;
    }
}
