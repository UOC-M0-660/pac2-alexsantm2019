package edu.uoc.pac2.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.GenericTransitionOptions.with
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book
import kotlinx.android.synthetic.main.activity_book_detail.*
import java.lang.reflect.Array.get


/**
 * A fragment representing a single Book detail screen.
 * This fragment is contained in a [BookDetailActivity].
 */
class BookDetailFragment : Fragment() {

    private var myBook: Book? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtengo detalle del libro:
        loadBook()
    }

    // TODO: Get Book for the given {@param ARG_ITEM_ID} Book id
    private fun loadBook() {

        // Tarea asincronas
        class loadBook: AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                val myApp = activity?.application as MyApplication
                val booksInteractor = myApp.getBooksInteractor()
                arguments?.let {
                    if (it.containsKey(ARG_ITEM_ID)) {
                        myBook = booksInteractor.getBookById(it.getInt(ARG_ITEM_ID))

                        // Coloco titulo de libro en la barra
                            activity?.toolbar_layout?.title = myBook?.title

                        // Activo acciòn de boton SHARE
                            activity?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
                                shareContent(myBook!!)
                            }
                    }
                }
                return null
            }

            // Ejecuto en main thread
            override fun onPostExecute(result: Void?) {
                myBook?.let { initUI(it) }
            }
        }
        loadBook().execute()
    }

    // TODO: Init UI with book details
    @SuppressLint("ResourceType", "LongLogTag")
    private fun initUI(book: Book) {

        view?.findViewById<TextView>(R.id.book_author)?.text = book.author
        view?.findViewById<TextView>(R.id.book_date)?.text = book.publicationDate
        view?.findViewById<TextView>(R.id.book_detail)?.text = book.description

        // Cargo imagen en Toolbar
        val imageView = activity?.findViewById(R.id.toolbar_image) as ImageView
        Picasso.with(context)
                .load(book.urlImage)
                .placeholder(R.drawable.el_juego_de_ender)
                .error(R.drawable.el_juego_de_ender)
                .into(imageView)
    }

    // TODO: Share Book Title and Image URL
    private fun shareContent(book: Book) {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Titulo: ${book?.title}, imagen: ${book?.urlImage}")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    companion object {
        /**
         * The fragment argument representing the item title that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "itemIdKey"

        fun newInstance(itemId: Int): BookDetailFragment {
            val fragment = BookDetailFragment()
            val arguments = Bundle()
            arguments.putInt(ARG_ITEM_ID, itemId)
            fragment.arguments = arguments
            return fragment
        }
    }
}