
package portfolio.umasankar.umasankar.Drawer

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import portfolio.umasankar.umasankar.Drawer.NavDrawerItem


import portfolio.umasankar.umasankar.R


import java.util.Collections

/**
 * Created by UmaShankar on 23-11-2016.
 */

class NavigationDrawerAdapter(private val context: Context, data: MutableList<NavDrawerItem>) : RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder>() {
    //internal var data: MutableList<NavDrawerItem> = emptyList<NavDrawerItem>() as MutableList<NavDrawerItem>
    //internal var data: MutableList<NavDrawerItem> = emptyList<NavDrawerItem>()

    var data : MutableList<NavDrawerItem> = Collections.emptyList()

    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.data = data
    }

    fun delete(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.nav_drawer_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = data[position]
        holder.title.text = current.title
        holder.icon.text = current.icon
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var icon: TextView

        init {
            title = itemView.findViewById(R.id.title) as TextView
            icon = itemView.findViewById(R.id.title_icon) as TextView
            val font_awesome = Typeface.createFromAsset(context.assets, "fonts/fontawesome.ttf")
            icon.typeface = font_awesome

        }
    }
}