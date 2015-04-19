package com.ithinkbest.weather17.dummy;

import com.ithinkbest.weather17.NetworkActivity;
import com.ithinkbest.weather17.WeatherEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem2> ITEM_MAP = new HashMap<String, DummyItem2>();

    static {
        // Add 3 sample items.
//        addItem(new DummyItem("1", "Item 1"));
//        addItem(new DummyItem("2", "Item 2"));
//        addItem(new DummyItem("3", "Item 3"));
        int k=0;
        for (WeatherEntry entry:NetworkActivity.entries){
            k++;
            DummyItem item=new DummyItem( ""+(k),entry.getLocationName());
            DummyItem2 item2=new DummyItem2( ""+(k),entry.getDetailText());

            addItem(item,item2);
        }
    }

    private static void addItem(DummyItem item,DummyItem2 item2) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item2);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;


        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;

        }

        @Override
        public String toString() {
            return id+". "+content;
        }
    }

    public static class DummyItem2 {
        public String id;
        public String content;


        public DummyItem2(String id, String content) {
            this.id = id;
            this.content = content;

        }

        @Override
        public String toString() {
            return "yyy"+content;
        }
    }
}
