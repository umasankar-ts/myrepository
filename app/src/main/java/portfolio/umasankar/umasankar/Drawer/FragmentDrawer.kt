package portfolio.umasankar.umasankar.Drawer


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast




import org.w3c.dom.Text

import java.util.ArrayList

import android.content.Context.MODE_PRIVATE
import android.support.v4.view.MotionEventCompat
import portfolio.umasankar.umasankar.R


/**
 * A simple [Fragment] subclass.
 */
class FragmentDrawer : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var user_name: TextView? = null
    private var adapter: NavigationDrawerAdapter? = null
    private var containerView: View? = null
    private var drawerListener: FragmentDrawerListener? = null
    private val preferences: SharedPreferences? = null

    fun setDrawerListener(listener: FragmentDrawerListener) {
        this.drawerListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // drawer labels
        titles = activity!!.resources.getStringArray(R.array.nav_drawer_labels)
        titles_icons = activity!!.resources.getStringArray(R.array.nav_drawer_label_icons)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflating view layout
        val layout = inflater.inflate(R.layout.fragement_sidedrawer_menu, container, false)
        recyclerView = layout.findViewById(R.id.drawerList) as RecyclerView
        user_name = layout.findViewById(R.id.sidedrawer_user_name) as TextView





        user_name!!.text = "hi"

        adapter = NavigationDrawerAdapter(activity as Context, data as MutableList<NavDrawerItem>)
        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.addOnItemTouchListener(RecyclerTouchListener(activity!!, recyclerView!!, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                drawerListener!!.onDrawerItemSelected(view, position)
                mDrawerLayout!!.closeDrawer(containerView!!)
            }

            override fun onLongClick(view: View?, position: Int) {
                Toast.makeText(activity, "Dnt Long Press", Toast.LENGTH_SHORT).show()
            }
        }))

        return layout
    }


    fun setUp(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        containerView = activity!!.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        mDrawerToggle = object : ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                //toolbar.alpha = 1 - slideOffset / 2
            }
        }

        mDrawerLayout!!.addDrawerListener(mDrawerToggle!!)
        mDrawerLayout!!.post { mDrawerToggle!!.syncState() }

    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(context: Context, recyclerView: RecyclerView, private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }


    }

    interface FragmentDrawerListener {
        fun onDrawerItemSelected(view: View, position: Int)
    }

    companion object {

        private val TAG = FragmentDrawer::class.java.simpleName
        private var titles: Array<String>? = null
        private var titles_icons: Array<String>? = null

        // preparing navigation drawer items
        val data: List<NavDrawerItem>
            get() {
                val data = ArrayList<NavDrawerItem>()
                for (i in titles!!.indices) {
                    val navItem = NavDrawerItem()
                    navItem.title = titles!![i]
                    navItem.icon = titles_icons!![i]

                    data.add(navItem)
                }
                return data
            }
    }
}