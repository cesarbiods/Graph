package com.graphs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cesar on 4/16/17.
 */
public class Node {
    public static final String BASELINE = "https://en.wikipedia.org/wiki/";
    public static final int WIKIBEGINNING = 6;
    private Document doc;
    private List<Edge> edges;

    public Node(String current) {
        try {
            if (current.contains("#")) {
                current = current.substring(0, current.indexOf("#"));
            }
            String encoded = URLDecoder.decode(current.substring(WIKIBEGINNING), "UTF-8");
            doc = Jsoup.connect(BASELINE.concat(encoded)).get();
            edges = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Element> getLinks(Document doc) {
        return doc.getElementById("mw-content-text").select("p").select("a[href]");
    }

    public void generateEdges(int length) {
                getLinks(doc).stream()
                .filter(Node::isGoodLink)
                .limit(length)
                .forEach(l -> {
                    edges.add(new Edge(new Node(l.attr("href"))));
                    System.out.println(l.attr("href"));
                });
    }

    public static boolean isGoodLink(Element link) {
        if (link.attr("href").startsWith("/wiki/")) {
            if (!link.attr("href").contains("File:")) {
                if (!link.attr("href").contains("Help:")) {
                    if (!link.attr("href").contains("redirect=no")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getURL() {
        return doc.location();
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList edges) {
        this.edges = edges;
    }

}
