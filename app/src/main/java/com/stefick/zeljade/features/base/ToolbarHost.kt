package com.stefick.zeljade.features.base

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View.OnClickListener
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ToolbarHost {
    fun getActionBarTitle(): CharSequence
    fun setActionBarColor(colorDrawable: ColorDrawable)
    fun setActionBarColor(@ColorInt color: Int)
    fun setActionBarIcon(drawable: Drawable, onClickListener: OnClickListener?)
    fun setActionBarIcon(@DrawableRes resource: Int, onClickListener: OnClickListener?)
    fun setActionBarTitle(title: CharSequence)
    fun setActionBarTitle(@StringRes resource: Int)
    fun setActionBarVisibility(visible: Boolean)
}