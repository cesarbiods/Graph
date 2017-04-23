package com.graphs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cesar on 4/16/17.
 */
public class Node {
    public static final String baseline = "https://en.wikipedia.org/wiki/";
    private Document doc;
    private List edges;
    public static final int wikiBeginning = 6;

    public Node(String current) {
        try {
            if (current.contains("#")) {
                current = current.substring(0, current.indexOf("#"));
            }
            String encoded = URLDecoder.decode(current.substring(wikiBeginning), "UTF-8");
            doc = Jsoup.connect(baseline.concat(encoded)).get();
            edges = new ArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Element> getLinks(Document doc) {
        doc.select("table").remove();
        doc.select("sup").remove();
        Element content = doc.getElementById("content");
        Element bodyContent = content.getElementById("bodyContent");
        Element mw = bodyContent.getElementById("mw-content-text");
        mw.getElementsByClass("hatnote").remove();
        mw.getElementsByClass("toc").remove();
        mw.getElementsByClass("mw-editsection").remove();
        Elements fullLinks = mw.getElementsByTag("a");
        return fullLinks.subList(0,10);
    }

    public void generateEdges() {
        List<Element> goodLinks = getLinks(doc);
        for (Element l: goodLinks) {
            edges.add(new Node(l.attr("href")));
            System.out.println(l.attr("href"));
        }
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public List<Element> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList edges) {
        this.edges = edges;
    }

}
