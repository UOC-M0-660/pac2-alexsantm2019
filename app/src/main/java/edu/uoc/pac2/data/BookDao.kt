package edu.uoc.pac2.data

/**
 * Book Dao (Data Access Object) for accessing Book Table functions.
 */

interface BookDao {
    fun getAllBooks(): List<Book>

    fun getBookById(id: Int): Book?

    fun getBookByTitle(titleBook: String): Book?

    fun saveBook(book: Book): Long
}