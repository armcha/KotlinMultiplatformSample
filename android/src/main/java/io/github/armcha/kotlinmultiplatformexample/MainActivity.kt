package io.github.armcha.kotlinmultiplatformexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kotlin.mpp.mobile.createApplicationScreenMessage
import org.kotlin.mpp.mobile.data.GithubApiManager
import org.kotlin.mpp.mobile.domain.models.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = createApplicationScreenMessage()

        val apiClient = GithubApiManager()

        try {
            GlobalScope.launch(context = Dispatchers.Default) {
                val result: User = apiClient.getUser("armcha")
                Log.e("LOG", result.name)
            }
        } catch (ex: Exception) {
            Log.e("ex", ex.message)
        }

    }
}
