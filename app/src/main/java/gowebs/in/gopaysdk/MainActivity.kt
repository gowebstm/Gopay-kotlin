package gowebs.`in`.gopaysdk

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import gowebs.`in`.gopay_sdk.Gopay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val result = Gopay()
                .accessGopay(this@MainActivity)
                .authKey("5f95306a4ea073178d629526eaa802dd22b49e97")
                .authToken("7f0604d4001960f371aea0d66e627b3d3eddaa01")
                .authPackage("com.samrat.gowebs.com")
                .verify()
            result?.let {
                Toast.makeText(this@MainActivity, "Verification successful!", Toast.LENGTH_SHORT).show()
            }

            Gopay().PaymentInit(this@MainActivity).createOrder("Ease","8585814444","", "gomatka.co",1,"Test Payment")
        }
    }

}