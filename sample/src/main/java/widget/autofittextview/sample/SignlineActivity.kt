package widget.autofittextview.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class SignlineActivity : AppCompatActivity() {
    private var mInput: EditText? = null
    private var mOutput: TextView? = null
    private var mAutofitWidthOutput:TextView? = null
    private var mAutofitHeightOutput:TextView? = null
    private var mAutofitBothOutput:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signline)

        mInput =  findViewById<View>(R.id.input) as EditText
        mOutput = findViewById<View>(R.id.output) as TextView
        mAutofitWidthOutput = findViewById<View>(R.id.output_autofit_width) as TextView
        mAutofitHeightOutput = findViewById<View>(R.id.output_autofit_height) as TextView
        mAutofitBothOutput = findViewById<View>(R.id.output_autofit_both) as TextView

        mInput?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
                // do nothing
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
                mOutput!!.text = charSequence
                mAutofitWidthOutput?.setText(charSequence)
                mAutofitHeightOutput?.setText(charSequence)
                mAutofitBothOutput?.setText(charSequence)
            }

            override fun afterTextChanged(editable: Editable) {
                // do nothing
            }
        })
    }
}