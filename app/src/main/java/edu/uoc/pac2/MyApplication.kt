package edu.uoc.pac2

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.room.Room
import edu.uoc.pac2.data.ApplicationDatabase
import edu.uoc.pac2.data.BooksInteractor


/**
 * Entry point for the Application.
 */
class MyApplication : Application() {

    private lateinit var booksInteractor: BooksInteractor

    override fun onCreate() {
        super.onCreate()

        // Init Room Database
        val appBook = Room.databaseBuilder(this, ApplicationDatabase::class.java, "book.db").allowMainThreadQueries().build()

        // Init BooksInteractor
        booksInteractor = BooksInteractor(appBook.bookDao())
    }

    fun getBooksInteractor(): BooksInteractor {
        return booksInteractor
    }

    @Suppress("DEPRECATION")
    fun hasInternetConnection(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }


    @Suppress("DEPRECATION")
    fun isConnectingToInternet(mContext: Context?): Boolean {
        if (mContext == null) return false
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network: Network? = connectivityManager.activeNetwork
                if (network != null) {
                    val nc = connectivityManager.getNetworkCapabilities(network)
                    return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                }
            } else {
                val networkInfos = connectivityManager.allNetworkInfo
                for (tempNetworkInfo in networkInfos) {
                    if (tempNetworkInfo.isConnected) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun isInternetConnected():Boolean{

        val connectivityManager = this.getSystemService(android.content.Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                else -> false
            }
        }
        else {
            return connectivityManager.activeNetworkInfo != null &&
                    connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
        }
    }

}