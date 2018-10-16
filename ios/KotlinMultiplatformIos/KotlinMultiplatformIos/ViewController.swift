import UIKit
import SharedCode

class ViewController: UIViewController,ResultListener {
   
    @IBOutlet weak var Indicator: UIActivityIndicatorView!
    
    func onSuccess(result: Any) {
        print("Success")
        let user:UserFromJson = result as! UserFromJson
        print(user.name)
        print(user.id)
        print(user.location)
        print(user.gists_url)
        Indicator.stopAnimating()
        Indicator.isHidden = true
    }
    
    func onError(exception: KotlinException) {
        print("EXC")
        print(exception.message)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 300, height: 21))
        label.center = CGPoint(x: 160, y: 285)
        label.textAlignment = .center
        label.font = label.font.withSize(25)
        label.text = CommonKt.createApplicationScreenMessage()
        view.addSubview(label)
        
        let api = GithubApiManager()
        api.getUserAsync(context:  UI(), username: "JakeWharton", resultListener: self)
    }
    
    
    

}
