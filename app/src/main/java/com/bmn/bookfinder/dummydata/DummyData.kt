package com.bmn.bookfinder.dummydata

import com.bmn.bookfinder.R
import com.bmn.bookfinder.models.Topic
import java.util.*

object DummyData {
    val dummyTopics: ArrayList<Topic>
        get() {
            val dummyTopics = ArrayList<Topic>()
            dummyTopics.add(
                Topic(
                    text = "Biography",
                    image = R.drawable.biography,
                    backgroundColor = R.color.card_blue_color
                )
            )
            dummyTopics.add(
                Topic(
                    text = "Business",
                    image = R.drawable.business,
                    backgroundColor = R.color.card_purple_color
                )
            )
            dummyTopics.add(
                Topic(
                    text = "Children",
                    image = R.drawable.children,
                    backgroundColor = R.color.card_green_color
                )
            )
            dummyTopics.add(
                Topic(
                    text = "Cookery",
                    image = R.drawable.cookery,
                    backgroundColor = R.color.card_pink_color
                )
            )
            dummyTopics.add(
                Topic(
                    text = "Fiction",
                    image = R.drawable.fiction,
                    backgroundColor = R.color.card_blue_to_purple_color
                )
            )
            dummyTopics.add(
                Topic(
                    text = "Graphic Novels",
                    image = R.drawable.graphic_novels,
                    backgroundColor = R.color.card_dark_yellow_color
                )
            )
            dummyTopics.add(
                Topic(
                    text = "Biography",
                    image = R.drawable.biography,
                    backgroundColor = R.color.card_blue_color
                )
            )
            dummyTopics.add(
                Topic(
                    text = "Business",
                    image = R.drawable.business,
                    backgroundColor = R.color.card_purple_color
                )
            )
            dummyTopics.add(
                Topic(
                    text = "Children",
                    image = R.drawable.children,
                    backgroundColor = R.color.card_green_color
                )
            )
            return dummyTopics
        }

    @JvmStatic
    val firstUseTopics: ArrayList<Topic>
        get() {
            val dummyTopics = ArrayList<Topic>()
            dummyTopics.add(
                Topic(
                    1,
                    text = "Biography",
                    thumbnailUrl = "http://books.google.com/books/content?id=0xqrU5lnD7AC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    2,
                    text = "Business",
                    thumbnailUrl = "http://books.google.com/books/content?id=z5RS9uT1hkIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    3,
                    text = "Children",
                    thumbnailUrl = "http://books.google.com/books/content?id=noJMA1rqLjUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    4,
                    text = "Cookery",
                    thumbnailUrl = "http://books.google.com/books/content?id=qLYPB76dIvMC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    5,
                    text = "Fiction",
                    thumbnailUrl = "http://books.google.com/books/content?id=AHqxx2Xw3qsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    6,
                    text = "Satire",
                    thumbnailUrl = "https://books.google.com/books/content?id=mGcAAAAAYAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    7,
                    text = "Drama",
                    thumbnailUrl = "http://books.google.com/books/content?id=Tx1oU0pEt_0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    8,
                    text = "Action",
                    thumbnailUrl = "http://books.google.com/books/content?id=CNHrJrRbrPsC&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    9,
                    text = "Adventure",
                    thumbnailUrl = "http://books.google.com/books/content?id=SXlf834V0V4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    10,
                    text = "Travel",
                    thumbnailUrl = "http://books.google.com/books/content?id=uPwsDgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    11,
                    text = "Religious",
                    thumbnailUrl = "http://books.google.com/books/content?id=sOUBiAPxfigC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    12,
                    text = "Science",
                    thumbnailUrl = "http://books.google.com/books/content?id=8YLrBwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    13,
                    text = "History",
                    thumbnailUrl = "http://books.google.com/books/content?id=iWnbAAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    14,
                    text = "Math",
                    thumbnailUrl = "http://books.google.com/books/content?id=bhh9pNtNfWsC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    15,
                    text = "Poetry",
                    thumbnailUrl = "http://books.google.com/books/content?id=bl0WDAAAQBAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    16,
                    text = "Anthologies",
                    thumbnailUrl = "http://books.google.com/books/content?id=I1L3GW81bM4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    17,
                    text = "Encyclopedia",
                    thumbnailUrl = "http://books.google.com/books/content?id=7CAjAQAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                )
            )
            dummyTopics.add(
                Topic(
                    18,
                    text = "Dictionaries",
                    thumbnailUrl = "http://books.google.com/books/content?id=rLcjqEUzabYC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                )
            )
            return dummyTopics
        }
}