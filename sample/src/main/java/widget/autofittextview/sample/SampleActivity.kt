package widget.autofittextview.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class SampleActivity: Activity() {
    private var signButton: Button? = null
    private  var multiButton:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        signButton = findViewById(R.id.signline_example_button)
        signButton?.setOnClickListener {
            val signIntent = Intent(this@SampleActivity, SignlineActivity::class.java)
            startActivity(signIntent)
        }

        multiButton = findViewById(R.id.multiline_example_button)
        multiButton?.setOnClickListener {
            val multiIntent = Intent(this@SampleActivity, MutilineActivity::class.java)
            startActivity(multiIntent)
        }

    }


}