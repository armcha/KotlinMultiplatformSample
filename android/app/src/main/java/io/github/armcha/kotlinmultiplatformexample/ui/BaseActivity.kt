package io.github.armcha.kotlinmultiplatformexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.kotlin.mpp.mobile.presentation.base.BaseContract

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : AppCompatActivity(),
        BaseContract.View {

    abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onPresenterCreate(this as V)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}