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
    private List<String> itemTitles;

    DocumentBuilderFactory builderFactory;
    DocumentBuilder builder;
    Document doc;

    NodeList feed;
    ArrayList<Node> items;

    public RssParser(String url) {
        try {
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

                // get the "title elem" in this item (only one)
                NodeList titleList = ele.getElementsByTagName("title");
                Element titleEle = (Element)titleList.item(0);

                // get the "text node" in the title (only one)
                Node titleNode = titleEle.getChildNodes().item(0);
                Log.i("titles", titleNode.getNodeValue());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
