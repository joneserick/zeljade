package com.stefick.zeljade.features.base

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.ColorInt
import com.stefick.zeljade.R
import com.stefick.zeljade.databinding.FragmentActivityBinding

open class DefaultFragmentActivity() : BaseFragmentActivity(), ToolbarHost {

    protected var binding: FragmentActivityBinding? = null

    final override val defaultContainerId: Int = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentActivityBinding.inflate(layoutInflater)

        setContentView(binding?.root)
    }

    override fun getActionBarTitle(): CharSequence =
        binding?.toolbar?.title ?: ""

    override fun setActionBarColor(colorDrawable: ColorDrawable) {
        binding?.toolbar?.background = colorDrawable
    }

    override fun setActionBarColor(color: Int) {
        binding?.toolbar?.setBackgroundColor(color)
        changeStatusBarColor(color)

    }

    override fun setActionBarIcon(drawable: Drawable, onClickListener: View.OnClickListener?) {
        binding?.toolbarAction?.apply {
            setImageDrawable(drawable)
            setOnClickListener(onClickListener)
        }
    }

    override fun setActionBarIcon(resource: Int, onClickListener: View.OnClickListener?) {
        binding?.toolbarAction?.apply {
            setImageResource(resource)
            setOnClickListener(onClickListener)
        }
    }

    override fun setActionBarTitle(title: CharSequence) {
        binding?.toolbarTitle?.text = title
    }

    override fun setActionBarTitle(resource: Int) {
        binding?.toolbarTitle?.setText(resource)
    }

    override fun setActionBarVisibility(visible: Boolean) {
        binding?.toolbar?.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun changeStatusBarColor(@ColorInt color: Int): Boolean {
        val decorView = window?.decorView ?: return false

        if (window == null)
            return false

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val insetsController = decorView.windowInsetsController ?: return false
            insetsController.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                @Suppress("DEPRECATION")
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }

        return true
    }


}