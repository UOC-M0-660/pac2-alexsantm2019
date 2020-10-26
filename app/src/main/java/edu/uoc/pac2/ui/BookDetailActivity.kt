package edu.uoc.pac2.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_detail.*

/**
 * An activity representing a single Book detail screen.
 */
class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val itemID = intent.getIntExtra(BookDetailFragment.ARG_ITEM_ID, -1)
            val fragment = BookDetailFragment.newInstance(itemID)
            supportFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, fragment)
                    .commit()
        }
    }

    // TODO: Override finish animation for actionbar back arrow
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            backToBookListActivity()
            return true
        }
        return false

    }

    // TODO: Override finish animation for phone back button
    override fun onBackPressed() {
        //super.onBackPressed()
        backToBookListActivity()
    }

    private fun backToBookListActivity(){
        finish()

        // Animacion se lanza al momento de regresar a la lista de libros
        overridePendingTransition(R.anim.translate_in_top, R.anim.translate_out_top)
    }


}