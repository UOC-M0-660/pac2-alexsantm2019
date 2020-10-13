package edu.uoc.pac2

import edu.uoc.pac2.data.Book

/**
 * Created by alex on 04/10/2020.
 */
object TestData {
    const val bookProperty = "title"
    const val bookPropertyValue = "The girl on the train"
    const val bookListPosition = 1
    val book = Book(
            uid = 2,
            author = "Paula Hawkins",
            description = "EVERY DAY THE SAME\n\nRachel takes the same commuter train every morning and night. Every day she rattles down the track, flashes past a stretch of cozy suburban homes, and stops at the signal that allows her to daily watch the same couple breakfasting on their deck. She’s even started to feel like she knows them. Jess and Jason, she calls them. Their life—as she sees it—is perfect. Not unlike the life she recently lost.\n\nUNTIL TODAY\n\nAnd then she sees something shocking. It’s only a minute until the train moves on, but it’s enough. Now everything’s changed. Unable to keep it to herself, Rachel goes to the police. But is she really as unreliable as they say? Soon she is deeply entangled not only in the investigation but in the lives of everyone involved. Has she done more harm than good?",
            publicationDate = "13/01/2015",
            title = "The girl on the train",
            urlImage = "https://upload.wikimedia.org/wikipedia/en/3/34/The_Girl_on_The_Train.jpg",
    )
    const val networkWaitingMillis = 5000L
}