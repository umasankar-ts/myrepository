package portfolio.umasankar.umasankar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MotionEventCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.text.Html
import android.util.Log
import android.view.MotionEvent
import android.view.View
import portfolio.umasankar.umasankar.Drawer.FragmentDrawer

class Home : AppCompatActivity(), FragmentDrawer.FragmentDrawerListener  {
    private var drawerLayout: DrawerLayout? = null
    private var mToogle: ActionBarDrawerToggle? = null
    private var toolbar: Toolbar? = null
    private var drawerFragment: FragmentDrawer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawerlayout) as DrawerLayout
        mToogle = ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        toolbar = findViewById(R.id.drawer_toolbar) as Toolbar
        setSupportActionBar(toolbar)

        drawerLayout!!.addDrawerListener(mToogle!!)
        mToogle!!.syncState()

        drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as FragmentDrawer
        drawerFragment!!.setUp(R.id.fragment_navigation_drawer, drawerLayout!!, toolbar!!)
        drawerFragment!!.setDrawerListener(this)
        displayView(1)
    }

    override fun onDrawerItemSelected(view: View, position: Int) {
        displayView(position)
    }

    private fun displayView(position: Int) {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        when (position) {
            0 -> {
                /*   fragment = ViewAlbum()
                   title = getString(R.string.view_albums)*/

                /*val studioAlbum = Intent(this, AlbumCategory::class.java)
                val dbHelper = DbHelper(this)
                val studioAlbums = dbHelper.studioAlbum
                if (studioAlbums != null && studioAlbums.count > 0) {
                    if (studioAlbums.moveToFirst()) {
                        studioAlbum.putExtra("album_id", studioAlbums.getInt(studioAlbums.getColumnIndexOrThrow(DBStatics.ALBUMS_ID)))
                        studioAlbum.putExtra("album_name", studioAlbums.getString(studioAlbums.getColumnIndexOrThrow(DBStatics.ALBUMS_ALBUM_NAME)))
                        studioAlbum.putExtra("updated_at", studioAlbums.getInt(studioAlbums.getColumnIndexOrThrow(DBStatics.ALBUMS_UPDATED_AT)))
                        studioAlbum.putExtra("album_type", studioAlbums.getInt(studioAlbums.getColumnIndexOrThrow(DBStatics.ALBUMS_ALBUM_TYPE)))
                    }
                }
                startActivity(studioAlbum)*/
            }

        }

        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_body, fragment)
            fragmentTransaction.commit()

            // set the toolbar title
            supportActionBar!!.title = title
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var DEBUG_TAG = "tag"
        val action = MotionEventCompat.getActionMasked(event)
        when (action) {
            MotionEvent.ACTION_DOWN -> {

                Log.d(DEBUG_TAG, "Action was DOWN")
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(DEBUG_TAG, "Action was MOVE")
                return true
            }
            MotionEvent.ACTION_UP -> {
                Log.d(DEBUG_TAG, "Action was UP")
                return true
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.d(DEBUG_TAG, "Action was CANCEL")
                return true
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Log.d(DEBUG_TAG, "Movement occurred outside bounds " + "of current screen element")
                return true
            }
            else -> return super.onTouchEvent(event)
        }

    }
}
