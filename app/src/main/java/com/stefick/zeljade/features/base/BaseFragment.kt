package com.stefick.zeljade.features.base

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.view.MenuItem.OnActionExpandListener
import android.widget.SearchView
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.stefick.zeljade.R

abstract class BaseFragment<TBinding : ViewBinding> : Fragment(), ToolbarHost {

    private val mToolbarHost: ToolbarHost?
        get() {
            val safeActivity = activity

            if (safeActivity is ToolbarHost)
                return safeActivity

            return null
        }

    protected var binding: TBinding? = null
        private set

    val fragmentActivity: BaseFragmentActivity?
        get() {
            val safeActivity = activity

            if (safeActivity is BaseFragmentActivity)
                return safeActivity

            return null
        }

    protected var searchView: SearchView? = null

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        searchView =
            SearchView(fragmentActivity?.supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.action_search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
            setOnActionExpandListener(object : OnActionExpandListener {

                override fun onMenuItemActionExpand(menu: MenuItem): Boolean {
                    searchView?.let {
                        it.isIconified = false
                        it.requestFocusFromTouch()
                    }
                    return true
                }

                override fun onMenuItemActionCollapse(p0: MenuItem): Boolean {
                    searchView?.setQuery("", true)
                    return true
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setActionBarVisibility(visible: Boolean) {
        val safeActivity = activity ?: return

        when (safeActivity) {
            is ToolbarHost -> {
                safeActivity.setActionBarVisibility(visible)
            }
            is AppCompatActivity -> {
                if (visible)
                    safeActivity.supportActionBar?.show()
                else
                    safeActivity.supportActionBar?.hide()
            }
            else -> {
                if (visible)
                    safeActivity.actionBar?.show()
                else
                    safeActivity.actionBar?.hide()
            }
        }
    }

    override fun getActionBarTitle(): CharSequence {
        val safeActivity = activity ?: return ""

        return when (safeActivity) {
            is ToolbarHost -> {
                safeActivity.getActionBarTitle()
            }
            is AppCompatActivity -> {
                safeActivity.supportActionBar?.title ?: ""
            }
            else -> {
                safeActivity.actionBar?.title ?: ""
            }
        }
    }

    override fun setActionBarTitle(title: CharSequence) {
        val safeActivity = activity ?: return

        when (safeActivity) {
            is ToolbarHost -> {
                safeActivity.setActionBarTitle(title)
            }
            is AppCompatActivity -> {
                safeActivity.supportActionBar?.title = title
            }
            else -> {
                safeActivity.actionBar?.title = title
            }
        }
    }

    override fun setActionBarTitle(resource: Int) {
        val toolbarHost = mToolbarHost

        if (toolbarHost != null) {
            toolbarHost.setActionBarTitle(resource)
            return
        }

        setActionBarTitle(getText(resource))
    }

    override fun setActionBarColor(color: Int) {
        val toolbarHost = mToolbarHost

        if (toolbarHost != null) {
            toolbarHost.setActionBarColor(color)
            return
        }

        setActionBarColor(ColorDrawable(color))
    }

    override fun setActionBarColor(colorDrawable: ColorDrawable) {
        val safeActivity = activity ?: return

        when (safeActivity) {
            is ToolbarHost -> {
                safeActivity.setActionBarColor(colorDrawable)
                return
            }
            is AppCompatActivity -> {
                safeActivity.supportActionBar?.setBackgroundDrawable(colorDrawable)
            }
            else -> {
                safeActivity.actionBar?.setBackgroundDrawable(colorDrawable)
            }
        }
    }

    override fun setActionBarIcon(resource: Int, onClickListener: View.OnClickListener?) {
        mToolbarHost?.setActionBarIcon(resource, onClickListener)
    }

    override fun setActionBarIcon(drawable: Drawable, onClickListener: View.OnClickListener?) {
        mToolbarHost?.setActionBarIcon(drawable, onClickListener)
    }

    abstract fun onCreateViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): TBinding

    open fun onBackPressed(): Boolean {
        return false
    }

    fun removeFromBackStack() {
        fragmentActivity?.let {
            if (it.supportFragmentManager.fragments.isNotEmpty()) {
                it.supportFragmentManager.popBackStack()
            }
        }
    }

    final override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val safeBinding = onCreateViewBinding(inflater, container, savedInstanceState)

        binding = safeBinding

        return safeBinding.root
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    fun finish() {
        val safeActivity = activity ?: return

        if (safeActivity is BaseFragmentActivity)
            safeActivity.closeFragment()
        else
            activity?.finish()
    }
}