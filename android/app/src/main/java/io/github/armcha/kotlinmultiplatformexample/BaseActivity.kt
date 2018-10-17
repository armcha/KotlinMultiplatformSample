package io.github.armcha.kotlinmultiplatformexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import presentation.BaseContract

/**
 *
 * Created by Arman Chatikyan on 17 Oct 2018
 *
 */

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : AppCompatActivity(),
    BaseContract.View {

    protected val presenter: P by lazy {
        createPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onPresenterCreate()
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        presenter.detachView()
        super.onDestroy()
    }

    abstract fun createPresenter(): P
}