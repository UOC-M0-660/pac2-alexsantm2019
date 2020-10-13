package edu.uoc.pac2

import android.content.Context
import androidx.test.InstrumentationRegistry
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch

/**
 * Created by alex on 04/10/2020.
 */
class Ex1Test {

    private val firestoreDatabase = Firebase.firestore

    @Test
    fun databaseContainsBooks() {
        val signal = CountDownLatch(1)
        firestoreDatabase
                .collection("books")
                .get()
                .addOnSuccessListener {
                    val containsBook = it.documents.mapNotNull { it.data }.any { it[TestData.bookProperty] == TestData.bookPropertyValue }
                    assertTrue(containsBook)
                    signal.countDown()
                }
                .addOnFailureListener {
                    fail("Could not connect to Firestore")
                    signal.countDown()
                }
        signal.await()
    }
}