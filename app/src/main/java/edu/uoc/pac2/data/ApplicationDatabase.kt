package edu.uoc.pac2.data

/**
 * Room Application Database
 */

abstract class ApplicationDatabase {
    abstract fun bookDao(): BookDao
}