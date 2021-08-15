package com.fero.submission1jetpack.utils

import com.fero.submission1jetpack.R
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity
import com.fero.submission1jetpack.data.source.remote.response.TvShow

object DataDummyTvShows {

    fun generateDummyTvShow(): List<TvShowEntity> {

        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                1 ,
                "Grey's Anatomy",
                "Follows the personal and professional live of a group of doctors at Seattle's Grey Sloan Memorial Hospital",
                "2005-03-27",
                "",

                )
        )

        tvShow.add(
            TvShowEntity(
                2,
                "The Walking Dead",
                "2021",
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                "",

            )
        )
        tvShow.add(
            TvShowEntity(
                3,
                "The Falcon and the Winter Soldier",
                "2021",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "",

            )
        )
        tvShow.add(
            TvShowEntity(
                4,
                "Naruto Shippūden",
                "2007",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "",

            )
        )
        tvShow.add(
            TvShowEntity(
                5,
                "Smallville",
                "2001",
                "The origins of the world’s greatest hero–from Krypton refugee Kal-el’s arrival on Earth through his tumultuous teen years to Clark Kent’s final steps toward embracing his destiny as the Man of Steel.",
                "",

            )
        )
        tvShow.add(
            TvShowEntity(
                6,
                "Jujutsu Kaisen",
                "2020",
                "With his days numbered, high schooler Yuji decides to hunt down and consume the remaining 19 fingers of a deadly curse so it can die with him.",
                "",

            )
        )
        tvShow.add(
            TvShowEntity(
                7,
                "The 100",
                "2014",
                "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
                "",

            )
        )
        tvShow.add(
            TvShowEntity(
                8,
                "Marvel's Agents of S.H.I.E.L.D.",
                "2013",
                "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
                "",

            )
        )
        tvShow.add(
            TvShowEntity(
                9,
                "Marvel's Avengers Assemble",
                "2013",
                "The further adventures of the Marvel Universe's mightiest general membership superhero team. With an all-star roster consisting of Iron Man, Captain America, Thor, Hulk, Hawkeye, Falcon and, occasionally--when she feels like it and only when she feels like it--Black Widow, the Avengers are a team in the truest sense. The Avengers save the world from the biggest threats imaginable--threats no single super hero could withstand.",
                "",

            )
        )
        tvShow.add(
            TvShowEntity(
                20,
                "Alice in Borderland",
                "2020",
                "With his two friends, a video-game-obsessed young man finds himself in a strange version of Tokyo where they must compete in dangerous games to win.",
                "",

            )
        )
        return tvShow
    }
}