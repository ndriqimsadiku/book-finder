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

    public static ArrayList<Topic> getFirstUseTopics() {
        ArrayList<Topic> dummyTopics = new ArrayList<>();
        dummyTopics.add(new Topic("Biography", "http://books.google.com/books/content?id=0xqrU5lnD7AC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Business", "http://books.google.com/books/content?id=z5RS9uT1hkIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Children", "http://books.google.com/books/content?id=noJMA1rqLjUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Cookery", "http://books.google.com/books/content?id=qLYPB76dIvMC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Fiction", "http://books.google.com/books/content?id=AHqxx2Xw3qsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Satire", "https://books.google.com/books/content?id=mGcAAAAAYAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Drama", "http://books.google.com/books/content?id=Tx1oU0pEt_0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Action", "http://books.google.com/books/content?id=CNHrJrRbrPsC&printsec=frontcover&img=1&zoom=1&source=gbs_api"));
        dummyTopics.add(new Topic("Adventure", "http://books.google.com/books/content?id=SXlf834V0V4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Travel", "http://books.google.com/books/content?id=uPwsDgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Religious", "http://books.google.com/books/content?id=sOUBiAPxfigC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Science", "http://books.google.com/books/content?id=8YLrBwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("History", "http://books.google.com/books/content?id=iWnbAAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Math", "http://books.google.com/books/content?id=bhh9pNtNfWsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Poetry", "http://books.google.com/books/content?id=bl0WDAAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"));
        dummyTopics.add(new Topic("Anthologies", "http://books.google.com/books/content?id=I1L3GW81bM4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        dummyTopics.add(new Topic("Encyclopedia", "http://books.google.com/books/content?id=7CAjAQAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"));
        dummyTopics.add(new Topic("Dictionaries", "http://books.google.com/books/content?id=rLcjqEUzabYC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"));
        return dummyTopics;
    }

}
