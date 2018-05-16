package portfolio.umasankar.umasankar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getSupportActionBar()?.setTitle((Html.fromHtml("<font color=\"#ffffff\">" + "Test" + "</font>")));

    }
}
