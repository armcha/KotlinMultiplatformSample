package domain.fetcher

internal sealed class Status {

    object Loading : Status()
    object Error : Status()
    object Success : Status()
    object EmptySuccess : Status()
    object Idle : Status()
}