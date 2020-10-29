package edu.uoc.pac2.ui

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.RequestConfiguration
import com.google.firebase.firestore.FirebaseFirestore
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book
import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.coroutines.runBlocking
import java.util.*

/**
 * An activity representing a list of Books.
 */
class BookListActivity : AppCompatActivity() {

    private val TAG = "BookListActivity"
    lateinit var mAdView : AdView
    private lateinit var adapter: BooksListAdapter
    val db = FirebaseFirestore.getInstance()

    // Variables globales
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // Init UI
        initToolbar()
        initRecyclerView()

        // Get Books
        getBooks()

        // TODO: Add books data to Firestore [Use once for new projects with empty Firestore Database]
        // addBooksDataToFirestoreDatabase()

        //Anuncios Admob
        loadAdMob()
    }

    //Funcion para configurar Admob
    private fun loadAdMob(){
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    // Init Top Toolbar
    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    // Init RecyclerView
    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.book_list)
        // Set Layout Manager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // Init Adapter
        adapter = BooksListAdapter(emptyList()) { item -> clickBookDetail(item) }       // Recibo parametro clickListener desde BookListAdapter


        recyclerView.adapter = adapter
    }

    // Recibido el parametro desde BookListAdapter, construyo funcion para mostrar detalle de libro
    fun clickBookDetail(item: Book) {
        val intent = Intent(this, BookDetailActivity::class.java).apply {
            putExtra(BookDetailFragment.ARG_ITEM_ID, item.uid)
        }
        startActivity(intent)

        // Animacion se lanza al momento de mostrar detalle de Libro
        overridePendingTransition(R.anim.translate_in_bottom, R.anim.translate_out_bottom)
    }

    // TODO: Get Books and Update UI
    private fun getBooks() {

        // Añado metodo para leer datos desde Firestore, usando la opcion de escuchar cambios en la BDD

        var myApp = this.application as MyApplication

        //1. Muestro los datos en local
        loadBooksFromLocalDb()

        // 2. Reviso si hay internet y leo datos desde Firestore
        if (myApp.hasInternetConnection()) {
            loadBooksFromFirestoreAndSaveInLocal()        // Cargo datos desde Firestore
        }
    }

    private fun loadBooksFromFirestoreAndSaveInLocal() {

        val docRef = db.collection("books")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            // Si existe snapshot, entonces agrego resultado a books
            if (snapshot != null) {
                val books: List<Book> = snapshot.documents.mapNotNull { it.toObject(Book::class.java) }

                //3. Al recibir respuesta, guardo los datos en local
                saveBooksToLocalDatabase(books)
            }
        }
    }

    // TODO: Load Books from Room
    private fun loadBooksFromLocalDb() {
        var books: List<Book> = ArrayList()
        // Tarea asincronas
        class loadBooksFromLocalDb: AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                books = (application as MyApplication).getBooksInteractor().getAllBooks()
                return null
            }

            // Ejecuto en main thread
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                adapter.setBooks(books)
            }
        }
        loadBooksFromLocalDb().execute()
    }

    // TODO: Save Books to Local Storage
    private fun saveBooksToLocalDatabase(books: List<Book>) {

        // Tarea asincronas
        class saveBooksToLocalDatabase: AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                val bookInteractor = (application as MyApplication).getBooksInteractor()
                bookInteractor.saveBooks(books)
                return null
            }

            // Ejecuto en main thread
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                adapter.setBooks(books)
            }
        }
        saveBooksToLocalDatabase().execute()

    }

}
