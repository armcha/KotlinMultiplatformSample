import UIKit
import SharedCode

class ViewController: BaseViewController<MovieListContractView,MovieListContractPresenter>,
                      MovieListContractView, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var indicator: UIActivityIndicatorView!
    @IBOutlet weak var tableView: UITableView!
    
    var movies:[Movie] = []
    let cellIdentifier = "MovieCellIdentifier"
    
    override lazy var presenter: MovieListContractPresenter = Injections().provideMovieListPresenter(uiDispatcher: UI())
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.fetchMovieList(movieCount: UInt32(20))
        
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: cellIdentifier)
        tableView.delegate = self
        tableView.dataSource = self
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movies.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath)
        let movie = movies[indexPath.row]
        cell.textLabel?.text = movie.title
        return cell
    }

    func showLoading() {
        print("ShowLoading")
        indicator.startAnimating()
    }
    
    func hideLoading() {
        indicator.isHidden = true
        print("HideLoading")
    }
    
    func showError(message: String?) {
        let alert = UIAlertController(title: Strings().alert_error_message, message: "",
                                      preferredStyle: .alert)
    
        alert.addAction(UIAlertAction(title: Strings().alert_positive_button_text,
                                      style: .default, handler: nil))
        self.present(alert, animated: true)
    }
    
    func onMovieListReceive(movieList: [Movie]) {
        movies = movieList
        tableView.isHidden = false
        tableView.reloadData()
    }
}
