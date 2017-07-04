package com.example.android.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 * Created by q on 2017-07-03.
 */

public class RssParser {
    private List<NewsItem> newsList;

    DocumentBuilderFactory builderFactory;
    DocumentBuilder builder;
    Document doc;

    NodeList feed;
    ArrayList<Node> items;

    public RssParser(String url) {
        try {
            newsList = new ArrayList<>();

            builderFactory = DocumentBuilderFactory.newInstance();
            builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(url);

            feed = doc.getElementsByTagName("item");
            items = new ArrayList<>();

            int i;
            for(i=0; i<feed.getLength(); i++) {
                Node node = feed.item(i);
                if(node.getNodeType() != Node.ELEMENT_NODE) continue;
                Element ele = (Element)node;

                String title = parseItem(ele, "title");
                String link = parseItem(ele, "link");
                String description = parseItem(ele, "description");

                newsList.add(new NewsItem(title, link, description));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String parseItem(Element ele, String tag) {
        NodeList nodeList = ele.getElementsByTagName(tag);
        Element nodeEle = (Element)nodeList.item(0);

        Node tagNode = nodeEle.getChildNodes().item(0);
        return tagNode.getNodeValue();
    }

    public String getNewsTitle(int pos) {
        return newsList.get(pos).getTitle();
    }

    public String getNewsURL(int pos) {
        return newsList.get(pos).getURL();
    }

    public String getNewsDescription(int pos) {
        return newsList.get(pos).getDescription();
    }

    public int getNewsCount() {
        return newsList.size();
    }


    public class NewsItem {
        private String title;
        private String url;
        private String description;

        public NewsItem(String newsTitle, String newsURL, String newsDescription) {
            this.title = newsTitle;
            this.url = newsURL;
            this.description = newsDescription;
        }

        public void setTitle(String newTitle) {
            this.title = newTitle;
        }

        public void setURL(String newURL) {
            this.url = newURL;
        }

        public void setDescription(String newDescription) {
            this.description = newDescription;
        }

        public String getTitle() {
            return this.title;
        }

        public String getURL() {
            return this.url;
        }

        public String getDescription() {
            return this.description;
        }
    }
}
