package com.example.android.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by q on 2017-07-03.
 */

public class RssParser {
    private List<String> itemTitles;

    private URL rssUrl;
    private SAXParserFactory parserFactory;
    private SAXParser parser;
    private XMLReader reader;
    private RSSHandler handler;
    private InputSource src;

    public RssParser(String url) {
        try {
            rssUrl = new URL(url);
            parserFactory = SAXParserFactory.newInstance();
            parser = parserFactory.newSAXParser();
            reader = parser.getXMLReader();
            handler = new RSSHandler();
            reader.setContentHandler(handler);

            itemTitles = new ArrayList<String>();
            //reader.parse(src);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //mResult.setText("Cannot connect RSS!");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            //mResult.setText("Cannot connect RSS!");
        } catch (SAXException e) {
            e.printStackTrace();
            //mResult.setText("Cannot connect RSS!");
        } catch (IOException e) {
            e.printStackTrace();
            //mResult.setText("Cannot connect RSS!");
        }
    }

    public void parse() {
        try {
            src = new InputSource(rssUrl.openStream());
            reader.parse(src);
            RssFeed feed = handler.getFeed();
            ArrayList<RssItem> items = (ArrayList<RssItem>)feed.getList();

            Log.i("RSS Size", String.valueOf(items.size()));
            for(int i=0; i<items.size(); i++) {
                itemTitles.add(items.get(i).getTitle());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getItemTitles() {
        return itemTitles;
    }


    public class RSSHandler extends DefaultHandler {

        final int STATE_UNKNOW = 0;
        final int STATE_TITLE = 1;
        final int STATE_DESCRIPTION = 2;
        final int STATE_LINK = 3;
        final int STATE_PUBDATE = 4;
        int currentState = STATE_UNKNOW;

        RssFeed feed;
        RssItem item;

        boolean itemFound = false;

        RSSHandler() {
        }

        RssFeed getFeed() {
            return feed;
        }

        @Override
        public void startDocument() throws SAXException {
            feed = new RssFeed();
            item = new RssItem();
        }

        @Override
        public void endDocument() throws SAXException {
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if (localName.equalsIgnoreCase("item")) {
                itemFound = true;
                item = new RssItem();
                currentState = STATE_UNKNOW;
            } else if (localName.equalsIgnoreCase("title")) {
                currentState = STATE_TITLE;
            } else if (localName.equalsIgnoreCase("description")) {
                currentState = STATE_DESCRIPTION;
            } else if (localName.equalsIgnoreCase("link")) {
                currentState = STATE_LINK;
            } else if (localName.equalsIgnoreCase("pubdate")) {
                currentState = STATE_PUBDATE;
            } else {
                currentState = STATE_UNKNOW;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (localName.equalsIgnoreCase("item")) {
                feed.addItem(item);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {

            String strCharacters = new String(ch, start, length);

            if (itemFound == true) {
                // "item" tag found, it's item's parameter
                switch (currentState) {
                    case STATE_TITLE:
                        item.setTitle(strCharacters);
                        break;
                    case STATE_DESCRIPTION:
                        item.setDescription(strCharacters);
                        break;
                    case STATE_LINK:
                        item.setLink(strCharacters);
                        break;
                    case STATE_PUBDATE:
                        item.setPubdate(strCharacters);
                        break;
                    default:
                        break;
                }
            } else {
                // not "item" tag found, it's feed's parameter
                switch (currentState) {
                    case STATE_TITLE:
                        feed.setTitle(strCharacters);
                        break;
                    case STATE_DESCRIPTION:
                        feed.setDescription(strCharacters);
                        break;
                    case STATE_LINK:
                        feed.setLink(strCharacters);
                        break;
                    case STATE_PUBDATE:
                        feed.setPubdate(strCharacters);
                        break;
                    default:
                        break;
                }
            }
            currentState = STATE_UNKNOW;
        }
    }


    public class RssFeed {
        private String title = null;
        private String description = null;
        private String link = null;
        private String pubdate = null;
        private List<RssItem> itemList;

        RssFeed() {
            itemList = new ArrayList<RssItem>(0);
        }

        void addItem(RssItem item) {
            itemList.add(item);
        }

        RssItem getItem(int location) {
            return itemList.get(location);
        }

        List<RssItem> getList() {
            return itemList;
        }

        void setTitle(String value) {
            title = value;
        }

        void setDescription(String value) {
            description = value;
        }

        void setLink(String value) {
            link = value;
        }

        void setPubdate(String value) {
            pubdate = value;
        }

        String getTitle() {
            return title;
        }

        String getDescription() {
            return description;
        }

        String getLink() {
            return link;
        }

        String getPubdate() {
            return pubdate;
        }
    }


    public class RssItem {

        private String title = null;
        private String description = null;
        private String link = null;
        private String pubdate = null;

        RssItem() {
        }

        void setTitle(String value) {
            title = value;
        }

        void setDescription(String value) {
            description = value;
        }

        void setLink(String value) {
            link = value;
        }

        void setPubdate(String value) {
            pubdate = value;
        }

        String getTitle() {
            return title;
        }

        String getDescription() {
            return description;
        }

        String getLink() {
            return link;
        }

        String getPubdate() {
            return pubdate;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
