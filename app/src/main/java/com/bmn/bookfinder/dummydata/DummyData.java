package com.bmn.bookfinder.dummydata;


import com.bmn.bookfinder.R;
import com.bmn.bookfinder.models.Topic;

import java.util.ArrayList;

public class DummyData {

    public static ArrayList<Topic> getDummyTopics() {
        ArrayList<Topic> dummyTopics = new ArrayList<>();

        dummyTopics.add(new Topic("Biography", R.drawable.biography, R.color.card_blue_color));
        dummyTopics.add(new Topic("Business", R.drawable.business, R.color.card_purple_color));
        dummyTopics.add(new Topic("Children", R.drawable.children, R.color.card_green_color));
        dummyTopics.add(new Topic("Cookery", R.drawable.cookery, R.color.card_pink_color));
        dummyTopics.add(new Topic("Fiction", R.drawable.fiction, R.color.card_blue_to_purple_color));
        dummyTopics.add(new Topic("Graphic Novels", R.drawable.graphic_novels, R.color.card_dark_yellow_color));
        dummyTopics.add(new Topic("Biography", R.drawable.biography, R.color.card_blue_color));
        dummyTopics.add(new Topic("Business", R.drawable.business, R.color.card_purple_color));
        dummyTopics.add(new Topic("Children", R.drawable.children, R.color.card_green_color));

        return dummyTopics;
    }

}
