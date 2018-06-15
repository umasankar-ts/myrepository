package portfolio.umasankar.umasankar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        FreezeScreen()
    }

    fun FreezeScreen() {

        Handler().postDelayed({
            proceedToNextPage()
        }, 2000)
    }

    private fun proceedToNextPage() {
        startActivity(Intent(this,Home::class.java))
    }
}
