package com.graphs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.*;


/**
 * Created by cesar on 4/16/17.
 */

/**
 * This class models the Node objects that populate the graph
 * consisting of wikipedia pages, a map of the words in it,
 * and a list of the first couple of neighbor links
 */

public class Node implements Serializable{
    public final String BASELINE = "https://en.wikipedia.org/wiki/";
    public final int WIKIBEGINNING = 6;

    private transient Document doc;
    public String name;
    private List<Edge> edges;
    private HashMap<String, Integer> wordfreq;

    public Node(String current) {
        try {
            if (current.contains("#")) {
                current = current.substring(0, current.indexOf("#"));
            }
            String encoded = URLDecoder.decode(current.substring(WIKIBEGINNING), "UTF-8");
            doc = Jsoup.connect(BASELINE.concat(encoded)).get();
            wordFrequencies();
            name = doc.title();
            edges = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns a list of links within the body of the
     * wikipedia article
     */

    public List<Element> getLinks(Document doc) {
        return doc.getElementById("mw-content-text").select("p").select("a[href]");
    }

    /**
     * Generates the edges for each node
     * <p>
     * This method gets the article and the first length links in it by filtering
     * the list with the predicate isGoodLink to get rid of files, help sections and
     * external websites. After filtering it gets the first length articles,
     * creates nodes from them, and sets them as edges of the current node
     *
     * @param  length  int signifying the number of links each node will have
     */

    public void generateEdges(int length) {
                getLinks(doc).stream()
                .filter(Node::isGoodLink)
                .limit(length)
                .forEach(l -> {
                    Node n = new Node(l.attr("href"));
                    Edge e = new Edge(n, findSimilarity(n));
                    edges.add(e);
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

    /**
     * Populates the word frequency table
     * <p>
     * The method checks the internet to see if the site has been updated
     * since the first load, if so it replaces the document with an updated one.
     * Then it populates and array with words, no special characters, and their
     * count, or how often they are found in the body of the article.
     */

    public void wordFrequencies() throws IOException {
        if (doc.updateMetaCharsetElement()) {
            doc = Jsoup.connect(BASELINE.concat(name)).get();
        }
        wordfreq = new HashMap<>();
        String[] words = doc.getElementById("mw-content-text").select("p").text().replaceAll("[^a-zA-Z0-9]+"," ").split(" ");
        for (String s : words) {
            if (wordfreq.containsKey(s)) {
                wordfreq.put(s, wordfreq.get(s) + 1);
            } else {
                wordfreq.put(s, 1);
            }
        }
    }

    /**
     * Does cosine similarity on the word frequencies of two nodes
     * <p>
     * The method gets a set of the intersection of the two nodes' text
     * and runs cosine similarity formula on the frequencies of the words
     * the two nodes share in common, eventually returning a number from
     * 0 to 1.0, where the closer to 1.0 the most similar the nodes are
     *
     * @param  n  the node to be compared to
     * @return      a double signifying the similarity of the two nodes
     */

    public double findSimilarity(Node n) {
        Set<String> set1 = wordfreq.keySet();
        Set<String> set2 = n.getWordfreq().keySet();
        Set<String> allSet = new HashSet<>(set1);
        allSet.retainAll(set2);

        double num = 0;
        double common1 = 0;
        double common2 = 0;
        for (String k : allSet) {
            num += wordfreq.get(k) * n.getWordfreq().get(k);
            common1 += Math.pow(wordfreq.get(k), 2);
            common2 += Math.pow(n.getWordfreq().get(k), 2);
        }
        double den = Math.sqrt(common1) * Math.sqrt(common2);
        return num / den;
    }

    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(name);
        stream.writeObject(edges);
        stream.writeObject(wordfreq);
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        name = (String) stream.readObject();
        edges = (List<Edge>) stream.readObject();
        wordfreq = (HashMap<String, Integer>) stream.readObject();
    }

    public HashMap<String, Integer> getWordfreq() {
        return wordfreq;
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
