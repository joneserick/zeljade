package com.stefick.zeljade.features.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.stefick.zeljade.R

abstract class BaseFragmentActivity : AppCompatActivity() {

    protected abstract val defaultContainerId: Int

    fun changeFragment(
        fragment: Fragment,
        containerId: Int,
        addToBackstack: Boolean = true
    ): Boolean {

        if (containerId == View.NO_ID)
            return false

        val fm = supportFragmentManager

        if (isDeadManager(fm))
            return false

        val transaction = fm.beginTransaction()
            .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            .replace(containerId, fragment)

        if (addToBackstack)
            transaction.addToBackStack(fragment.tag)

        transaction.commit()

        return true
    }

    fun changeFragment(
        fragment: Fragment,
        addToBackstack: Boolean = true
    ): Boolean {

        return changeFragment(fragment, defaultContainerId, addToBackstack)
    }

    fun closeFragment(): Boolean {
        val fm = supportFragmentManager

        if (isDeadManager(fm))
            return false

        fm.popBackStack()

        return true
    }

    fun clearBackStack(): Boolean {

        val fm = supportFragmentManager

        if (isDeadManager(fm))
            return false

        fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        return true
    }

    private fun isDeadManager(fragmentManager: FragmentManager): Boolean =
        fragmentManager.isStateSaved || fragmentManager.isDestroyed

}