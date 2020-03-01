package com.example.viewgyak.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
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

@Suppress("DEPRECATION")
@SuppressLint("ClickableViewAccessibility")
class PasswordEditText : RelativeLayout {

    private var picturePwd: Int = android.R.drawable.ic_menu_view
    private var pictureDraw: Drawable = resources.getDrawable(picturePwd)

    constructor(context: Context) : super(context){ init(context, null)}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){init(context, attrs)}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_password_edittext, this, true)

        if(attrs == null)
            return
        val attributes = context.obtainStyledAttributes(attrs,R.styleable.PasswordEditText)
        try{
            picturePwd = attributes.getResourceId(R.styleable.PasswordEditText_picturePwd,android.R.drawable.ic_menu_view)
            pictureDraw = resources.getDrawable(picturePwd)
        }finally{
            attributes.recycle()
        }
        ivPassword.setImageDrawable(pictureDraw)
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