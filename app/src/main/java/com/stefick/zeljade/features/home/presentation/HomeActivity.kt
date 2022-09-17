package com.stefick.zeljade.features.home.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.stefick.zeljade.core.api.CompendiumRemoteService
import com.stefick.zeljade.core.models.CategoryItemResponse
import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), HomeContract.View{

    private lateinit var mPresenter: HomePresenter
    private lateinit var mBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mPresenter =
            HomePresenter(
                this,
                CompendiumRepository(CompendiumRemoteService()),
                lifecycleScope
            )

        mBinding.button.setOnClickListener { mPresenter.getDataByCategory("equipment") }

    }

    override fun displayData(response: CategoryItemResponse) {
        Toast.makeText(this, "We received data: ${response.data.size} ", Toast.LENGTH_LONG).show()
    }

    override fun displayError(throwable: Throwable) {
        Toast.makeText(this, "We couldn't receive data :( ", Toast.LENGTH_LONG).show()
        throwable.printStackTrace()

    }
}