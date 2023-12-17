

import java.util.ArrayList;

public class NewsAgency {
    public final ArrayList<NewsCategory> newsCategories = new ArrayList<>();

    public void showAllNewsCategories() {
        for (NewsCategory newsCategory : this.newsCategories) {
            System.out.println(newsCategory);
        }
    }

    public void showAllNews() {
        for (NewsCategory newsCategory : this.newsCategories) {
            System.out.println("Category: " + newsCategory.getCategoryName());
            for (News news : newsCategory.getNewsList()) {
                System.out.println(news);
            }
        }
    }

    public void addNewsCategory(NewsCategory newsCategory) {
        for (NewsCategory nc : this.newsCategories) {
            if (nc.getCategoryId() == newsCategory.getCategoryId() || nc.getCategoryName().equals(newsCategory.getCategoryName())) {
                System.out.println("News category already exists");
                return;
            }
        }
        this.newsCategories.add(newsCategory);
    }

    public void updateNewsCategory(int categoryId, String categoryName) {
        for (NewsCategory nc : this.newsCategories) {
            if (nc.getCategoryId() == categoryId) {
                nc.setCategoryName(categoryName);
                return;
            }
        }
    }

    public void deleteNewsCategory(int categoryId) {
        for (NewsCategory nc : this.newsCategories) {
            if (nc.getCategoryId() == categoryId) {
                this.newsCategories.remove(nc);
                return;
            }
        }
    }

    public void addNews(int categoryId, News news) {
        for (NewsCategory newsCategory : this.newsCategories) {
            if (newsCategory.getCategoryId() == categoryId) {
                for (News n : newsCategory.getNewsList()) {
                    if (n.getNewsId() == news.getNewsId() || n.getTitle().equals(news.getTitle())) {
                        System.out.println("News already exists");
                        return;
                    }
                }
                newsCategory.addNews(news);
                return;
            }
        }
    }

    public void updateNews(int categoryId, int newsId, News input) {
        for (NewsCategory newsCategory : this.newsCategories) {
            if (newsCategory.getCategoryId() == categoryId) {
                for (News news : newsCategory.getNewsList()) {
                    if (news.getNewsId() == newsId) {
                        news.setTitle(input.getTitle());
                        news.setText(input.getText());
                        return;
                    }
                }
            }
        }
    }

    public void deleteNews(int categoryId, int newsId) {
        for (NewsCategory newsCategory : this.newsCategories) {
            if (newsCategory.getCategoryId() == categoryId) {
                newsCategory.deleteNews(newsId);
                return;
            }
        }
    }
}
