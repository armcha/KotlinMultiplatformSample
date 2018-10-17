package io.github.armcha.kotlinmultiplatformexample

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.Main
import org.kotlin.mpp.mobile.createApplicationScreenMessage
import org.kotlin.mpp.mobile.data.GithubApiManager
import org.kotlin.mpp.mobile.data.ResultListener
import org.kotlin.mpp.mobile.logger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textView.text = createApplicationScreenMessage()

        val apiClient = GithubApiManager()
        //Handler().postDelayed({
            Log.e("LOG", "Starting")
            //repeat(5) {
                apiClient.getUserAsync(Dispatchers.Main, "JackWharton", object : ResultListener {
                    override fun onSuccess(result: Any) {
                        Log.e("LOG", result.toString())
                        Log.e("LOG", Thread.currentThread().name)
                    }

                    override fun onError(exception: Exception) {
                        Log.e("exception", exception.message)
                    }
                })
           // }
       // },2000)



//
//        try {
//            GlobalScope.launch(context = Dispatchers.Default) {
//                val result: UserFromJson = apiClient.getUser("armcha")
//                Log.e("LOG", result.toString())
//            }
//        } catch (ex: Exception) {
//            Log.e("ex", ex.message)
//        }


    }
}
