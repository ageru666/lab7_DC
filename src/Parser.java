
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Objects;

public class Parser {
    private static DocumentBuilder db = null;

    public static class SimpleErrorHandler implements ErrorHandler {
        public void warning(SAXParseException exception) {
            System.out.println("Warning: " + exception.getLineNumber());
            System.out.println(exception.getMessage());
        }

        public void error(SAXParseException exception) {
            System.out.println("Error: " + exception.getLineNumber());
            System.out.println(exception.getMessage());
        }

        public void fatalError(SAXParseException exception) throws SAXException {
            System.out.println("Fatal error: " + exception.getLineNumber());
            System.out.println(exception.getMessage());
        }
    }

    public static NewsAgency loadData(String path) {
        NewsAgency newsAgency = new NewsAgency();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        dbf.setValidating(true);

        try {
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new Parser.SimpleErrorHandler());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            doc = db.parse(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Element root = Objects.requireNonNull(doc).getDocumentElement();
        if (root.getNodeName().equals("news_agency")) {
            NodeList newsCategories = root.getElementsByTagName("category");
            for (int i = 0; i < newsCategories.getLength(); i++) {
                Element newsCategory = (Element) newsCategories.item(i);

                int categoryId = Integer.parseInt(newsCategory.getAttribute("id"));
                String categoryName = newsCategory.getAttribute("name");
                NewsCategory category = new NewsCategory(categoryId, categoryName);

                NodeList newsList = newsCategory.getElementsByTagName("news");
                for (int j = 0; j < newsList.getLength(); j++) {
                    Element newsElement = (Element) newsList.item(j);
                    News news = new News(
                            Integer.parseInt(newsElement.getAttribute("id")),
                            newsElement.getAttribute("title"),
                            newsElement.getTextContent()
                    );
                    category.addNews(news);
                }
                newsAgency.newsCategories.add(category);
            }
        }
        return newsAgency;
    }

    public static void saveData(String path, NewsAgency newsAgency) throws TransformerException {
        Document doc = db.newDocument();
        Element root = doc.createElement("news_agency");
        doc.appendChild(root);

        for (NewsCategory category : newsAgency.newsCategories) {
            Element newsCategoryElement = doc.createElement("category");
            newsCategoryElement.setAttribute("id", String.valueOf(category.getCategoryId()));
            newsCategoryElement.setAttribute("name", category.getCategoryName());
            root.appendChild(newsCategoryElement);

            for (News news : category.getNewsList()) {
                Element newsElement = doc.createElement("news");
                newsElement.setAttribute("id", String.valueOf(news.getNewsId()));
                newsElement.setAttribute("title", news.getTitle());
                newsElement.setTextContent(news.getText());
                newsCategoryElement.appendChild(newsElement);
            }
        }

        Source source = new DOMSource(doc);
        Result result = new StreamResult(new File(path));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "news_agency_schema.xsd");
        transformer.transform(source, result);
    }
}
