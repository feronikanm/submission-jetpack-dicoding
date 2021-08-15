package com.fero.submission1jetpack.utils

import com.fero.submission1jetpack.R
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.data.source.remote.response.Movie

object DataDummyMovies {

    fun generateDummyMovie(): List<MovieEntity>{

        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                1,
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                "",

                )
        )
        movie.add(
            MovieEntity(
                2,
                "Godzilla vs. Kong",
                "2021",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "",

            )
        )
        movie.add(
            MovieEntity(
                3,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "2021",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "",

            )
        )
        movie.add(
            MovieEntity(
                4,
                "Friends: The Reunion",
                "2021",
                "The cast of Friends reunites for a once-in-a-lifetime celebration of the hit series, an unforgettable evening filled with iconic memories, uncontrollable laughter, happy tears, and special guests.",
                "",

            )
        )
        movie.add(
            MovieEntity(
                5,
                "Zack Snyder's Justice League",
                "2021",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "",

            )
        )
        movie.add(
            MovieEntity(
                6,
                "22 vs. Earth",
                "2021",
                "Set before the events of ‘Soul’, 22 refuses to go to Earth, enlisting a gang of 5 new souls in attempt of rebellion. However, 22’s subversive plot leads to a surprising revelation about the meaning of life.",
                "",

            )
        )
        movie.add(
            MovieEntity(
                7,
                "Raya and the Last Dragon",
                "2021",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "",

            )
        )
        movie.add(
            MovieEntity(
                8,
                "Maya the Bee: The Golden Orb",
                "2021",
                "When Maya, a headstrong little bee, and her best friend Willi, rescue an ant princess they find themselves in the middle of an epic bug battle that will take them to strange new worlds and test their friendship to its limits.",
                "",

            )
        )
        movie.add(
            MovieEntity(
                9,
                "Tom & Jerry",
                "2021",
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                "",

            )
        )
        movie.add(
            MovieEntity(
                10,
               "Miraculous World: New York, United HeroeZ",
               "2020",
               "During a school field trip, Ladybug and Cat Noir meet the American superheroes, whom they have to save from an akumatised super-villain. They discover that Miraculous exist in the United States too.",
                "",

            )
        )
        return movie
    }


}