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

    NodeList items;

    public RssParser(String url) {
        try {
            builderFactory = DocumentBuilderFactory.newInstance();
            builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(url);

            items = doc.getElementsByTagName("item");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
