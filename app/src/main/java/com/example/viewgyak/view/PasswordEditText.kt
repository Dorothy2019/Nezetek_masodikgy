package com.example.viewgyak.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.IBinder
import android.text.Editable
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.example.viewgyak.R
import kotlinx.android.synthetic.main.view_password_edittext.view.*

@SuppressLint("ClickableViewAccessibility")
class PasswordEditText : RelativeLayout {
    companion object {
        private const val PICTURE_NONE = 0
        private const val PICTURE_PRESENT = 1
    }
    private var dividerType: Int = PICTURE_NONE

    constructor(context: Context) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_password_edittext, this, true)

        ivPassword.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    setTransformationMethod(null)
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    setTransformationMethod(PasswordTransformationMethod.getInstance())
                    true
                }
                else -> false
            }
        }

        setTransformationMethod(PasswordTransformationMethod.getInstance())
    }

    private fun setTransformationMethod(method: TransformationMethod?) {
        val ss = etPassword.selectionStart
        val se = etPassword.selectionEnd
        etPassword.transformationMethod = method
        etPassword.setSelection(ss, se)
    }

    fun getText(): Editable? {
        return etPassword.text
    }

    fun setError(str: CharSequence) {
        etPassword.error = str
    }

    fun setText(text: CharSequence) {
        etPassword.setText(text)
    }

    override fun getWindowToken(): IBinder? {
        return etPassword.windowToken
    }

}