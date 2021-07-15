package com.dicodingsubmission.mymovies.repository

import com.dicodingsubmission.mymovies.model.Item

/**
 * Because in instrument testing just check sample data[0], data[1] - [19] left default
 */
object FakeData {

    fun getOnPlayingMovie(): List<Item> =
        arrayListOf( // using postman size = 20 movies
            Item( // data[0]
                460465, //id
                "Mortal Kombat", //title
                "", //name
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg", //backdrop
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg", //poster
                "", //first air date
                "2021-04-07", //release date
                7.7, // vote average
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            ),
            Item(1,"","","","","","",0.0,""),
            Item(2,"","","","","","",0.0,""),
            Item(3,"","","","","","",0.0,""),
            Item(4,"","","","","","",0.0,""),
            Item(5,"","","","","","",0.0,""),
            Item(6,"","","","","","",0.0,""),
            Item(7,"","","","","","",0.0,""),
            Item(8,"","","","","","",0.0,""),
            Item(9,"","","","","","",0.0,""),
            Item(10,"","","","","","",0.0,""),
            Item(11,"","","","","","",0.0,""),
            Item(12,"","","","","","",0.0,""),
            Item(13,"","","","","","",0.0,""),
            Item(14,"","","","","","",0.0,""),
            Item(15,"","","","","","",0.0,""),
            Item(16,"","","","","","",0.0,""),
            Item(17,"","","","","","",0.0,""),
            Item(18,"","","","","","",0.0,""),
            Item(19,"","","","","","",0.0,"")
        )

    fun getPopularMovie(): List<Item> =
        arrayListOf(
            Item(
                567189,
                "Tom Clancy's Without Remorse",
                "",
                "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "",
                "2021-04-29",
                7.3,
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife."
            ),
            Item(1,"","","","","","",0.0,""),
            Item(2,"","","","","","",0.0,""),
            Item(3,"","","","","","",0.0,""),
            Item(4,"","","","","","",0.0,""),
            Item(5,"","","","","","",0.0,""),
            Item(6,"","","","","","",0.0,""),
            Item(7,"","","","","","",0.0,""),
            Item(8,"","","","","","",0.0,""),
            Item(9,"","","","","","",0.0,""),
            Item(10,"","","","","","",0.0,""),
            Item(11,"","","","","","",0.0,""),
            Item(12,"","","","","","",0.0,""),
            Item(13,"","","","","","",0.0,""),
            Item(14,"","","","","","",0.0,""),
            Item(15,"","","","","","",0.0,""),
            Item(16,"","","","","","",0.0,""),
            Item(17,"","","","","","",0.0,""),
            Item(18,"","","","","","",0.0,""),
            Item(19,"","","","","","",0.0,"")
        )

    fun getTVOnTheAir(): List<Item> =
        arrayListOf(
            Item(
                60735,
                "",
                "The Flash",
                "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "2014-10-07",
                "",
                7.7,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."
            ),
            Item(1,"","","","","","",0.0,""),
            Item(2,"","","","","","",0.0,""),
            Item(3,"","","","","","",0.0,""),
            Item(4,"","","","","","",0.0,""),
            Item(5,"","","","","","",0.0,""),
            Item(6,"","","","","","",0.0,""),
            Item(7,"","","","","","",0.0,""),
            Item(8,"","","","","","",0.0,""),
            Item(9,"","","","","","",0.0,""),
            Item(10,"","","","","","",0.0,""),
            Item(11,"","","","","","",0.0,""),
            Item(12,"","","","","","",0.0,""),
            Item(13,"","","","","","",0.0,""),
            Item(14,"","","","","","",0.0,""),
            Item(15,"","","","","","",0.0,""),
            Item(16,"","","","","","",0.0,""),
            Item(17,"","","","","","",0.0,""),
            Item(18,"","","","","","",0.0,""),
            Item(19,"","","","","","",0.0,"")
        )

    fun getPopularTV(): List<Item> =
        arrayListOf(
            Item(
                88396,
                "",
                "The Falcon and the Winter Soldier",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "2021-03-19",
                "",
                7.9,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            ),
            Item(1,"","","","","","",0.0,""),
            Item(2,"","","","","","",0.0,""),
            Item(3,"","","","","","",0.0,""),
            Item(4,"","","","","","",0.0,""),
            Item(5,"","","","","","",0.0,""),
            Item(6,"","","","","","",0.0,""),
            Item(7,"","","","","","",0.0,""),
            Item(8,"","","","","","",0.0,""),
            Item(9,"","","","","","",0.0,""),
            Item(10,"","","","","","",0.0,""),
            Item(11,"","","","","","",0.0,""),
            Item(12,"","","","","","",0.0,""),
            Item(13,"","","","","","",0.0,""),
            Item(14,"","","","","","",0.0,""),
            Item(15,"","","","","","",0.0,""),
            Item(16,"","","","","","",0.0,""),
            Item(17,"","","","","","",0.0,""),
            Item(18,"","","","","","",0.0,""),
            Item(19,"","","","","","",0.0,"")
        )
}