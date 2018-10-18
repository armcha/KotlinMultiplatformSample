import UIKit
import SharedCode

class ViewController: UIViewController, MovieListContractView,ListenerMovieSecond{
    func start() {
        print("Starts")
    }
    
    func succes(result: [Movie]) {
        print("Success")
        print(result)
    
    }
    
    
    ///override func createPresenter() {
        lazy var presenter = MovieListPresenter(uiDispatcher: UI(),
                                       movieRepository: MovieDataRepository(traktTvApiManager: TraktTvApiManager(),
                                                                            omdbApiManager:OmdbApiManager(),
                                                                            localCache: MovieCache.init()))
//}
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        print("Start fetching")
        //presenter.fetchMovieList()
//        presenter.fetchMovieList { ([Movie]) -> KotlinUnit in
//            print("FETCH SUCCES")
//            return KotlinUnit.init()
//        }
        //let traktTvApiManager = TraktTvApiManager()
        //traktTvApiManager.getMovieList(dispatcher: UI(), resultListener: self)
        
        var obj = MovieDataRepository(traktTvApiManager: TraktTvApiManager(),
        omdbApiManager:OmdbApiManager(),
        localCache: MovieCache.init())
        
        obj.getMovieList(dispatcher: UI(), resultListener: self)
        
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
//        movieList.forEach { (Movie) in
//            print(Movie.title)
//        }
        print(movieList)
    }
    
    
}
