
import javax.xml.transform.TransformerException;
import java.util.Scanner;

public class Main {
    public static final String PATH = "src\\news_agency.xml";
    static Scanner scanner = new Scanner(System.in);
    static NewsAgency newsAgency = new NewsAgency();

    public static void showMenu() {
        System.out.println("1. Show all news categories");
        System.out.println("2. Show all news");
        System.out.println("3. Add a new news category");
        System.out.println("4. Add a new news");
        System.out.println("5. Update a news category");
        System.out.println("6. Update a news");
        System.out.println("7. Delete a news category");
        System.out.println("8. Delete a news");
        System.out.println("9. Save all data");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) throws TransformerException {
        String choice;
        newsAgency = Parser.loadData(PATH);
        while (true) {
            showMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> newsAgency.showAllNewsCategories();
                case "2" -> newsAgency.showAllNews();
                case "3" -> {
                    System.out.println("Enter news category ID: ");
                    int newCategoryId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter news category name: ");
                    String newCategoryName = scanner.nextLine();
                    newsAgency.addNewsCategory(new NewsCategory(newCategoryId, newCategoryName));
                }
                case "4" -> {
                    System.out.println("Enter news ID: ");
                    int newNewsId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter news title: ");
                    String newNewsTitle = scanner.nextLine();
                    System.out.println("Enter news text: ");
                    String newNewsText = scanner.nextLine();
                    System.out.println("Enter news category ID: ");
                    int newCategoryId = Integer.parseInt(scanner.nextLine());
                    newsAgency.addNews(newCategoryId, new News(newNewsId, newNewsTitle, newNewsText));
                }
                case "5" -> {
                    System.out.println("Enter news category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new news category name: ");
                    String newCategoryName = scanner.nextLine();
                    newsAgency.updateNewsCategory(categoryId, newCategoryName);
                }
                case "6" -> {
                    System.out.println("Enter news ID: ");
                    int newsId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter news category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new news title: ");
                    String newNewsTitle = scanner.nextLine();
                    System.out.println("Enter new news text: ");
                    String newNewsText = scanner.nextLine();
                    newsAgency.updateNews(categoryId, newsId, new News(newsId, newNewsTitle, newNewsText));
                }
                case "7" -> {
                    System.out.println("Enter news category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    newsAgency.deleteNewsCategory(categoryId);
                }
                case "8" -> {
                    System.out.println("Enter news ID: ");
                    int newsId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter news category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    newsAgency.deleteNews(categoryId, newsId);
                }
                case "9" -> Parser.saveData(PATH, newsAgency);
                case "0" -> System.exit(0);
                default -> System.out.println("Incorrect choice");
            }
        }
    }
}
