import UIKit
import SharedCode

class ViewController: BaseViewController<MovieListContractView,MovieListContractPresenter>, MovieListContractView{
    
    override lazy var presenter: MovieListContractPresenter = Injections().provideMovieListPresenter(uiDispatcher: UI())
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.fetchMovieList()
    }

    func showLoading() {
        print("ShowLoading")
    }
    
    func hideLoading() {
        print("HideLoading")
    }
    
    func showError(message: String?) {
        print("Show Error")
        print(message ?? "")
    }
    
    func onMovieListReceive(movieList: [Movie]) {
        movieList.forEach { (Movie) in
            print(Movie.title)
        }
    }
}
