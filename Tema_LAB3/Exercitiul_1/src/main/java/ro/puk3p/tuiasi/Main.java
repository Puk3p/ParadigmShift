package ro.puk3p.tuiasi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class RSS {
    private String title;
    private String link;
    private String description;
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public RSS(String title, String link, String description, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            Document data = Jsoup.connect("http://rss.cnn.com/rss/edition.rss").get();

            Elements items = data.select("item");

            List<RSS> rssItems = new ArrayList<>();

            for (Element item : items) {
                String title = item.select("title").text();
                String link = item.select("link").text();
                String description = item.select("description").text();
                String pubDate = item.select("pubDate").text();

                RSS rssItem = new RSS(title, link, description, pubDate);
                rssItems.add(rssItem);
            }

            for (RSS rssItem : rssItems) {
                System.out.println("Titlu: " + rssItem.getTitle());
                System.out.println();
                System.out.println("Link: " + rssItem.getLink());
                System.out.println();
                System.out.println("Description: " + rssItem.getDescription());
                System.out.println();
                System.out.println("Publish Date: " + rssItem.getPubDate());
            }
        } catch (IOException luminita_GuzovaTIIIII) {
            luminita_GuzovaTIIIII.printStackTrace();
        }
    }
}