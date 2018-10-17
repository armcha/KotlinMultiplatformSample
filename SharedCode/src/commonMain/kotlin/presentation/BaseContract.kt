package presentation

interface BaseContract {

    interface View

    interface Presenter<V : BaseContract.View> {

        var view: V?

        fun attachView(view: V)

        fun detachView()

        fun onPresenterCreate()

        fun onDestroy()
    }
}

