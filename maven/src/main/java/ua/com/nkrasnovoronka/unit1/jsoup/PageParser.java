package ua.com.nkrasnovoronka.unit1.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageParser {

    public void printPageTitle(String url){
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            String title = doc.title();
            System.out.println(title);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
