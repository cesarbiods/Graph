package com.graphs;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by graphs on 4/12/17.
 */
public class Main {
    public static void main(String[] args)  {
        String baseurl = "https://en.wikipedia.org/wiki/";
        String favorite = "Metroid_Prime:_Trilogy";
        String start = baseurl.concat(favorite);
        try {
            Document doc = Jsoup.connect(start).get();
            doc.select("table").remove();
            Element content = doc.getElementById("content");
            Element bodyContent = content.getElementById("bodyContent");
            Element mw = bodyContent.getElementById("mw-content-text");
            Elements links = mw.getElementsByTag("a"); //migrate thing it funky

            for (int i = 0; i < 10; i++) {
                String linkHref = links.get(i).attr("href");
                String linkText = links.get(i).text();
                System.out.println(linkHref + "#####" + linkText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}