
import Foundation
import SharedCode
import UIKit

class  BaseViewController<T:BaseContractView,P:BaseContractPresenter> :
            UIViewController,BaseContractView{

    lazy var presenter:P = [P]() as! P

    override func viewDidLoad() {
        presenter.onPresenterCreate(view: self)
    }
    
    deinit{
        presenter.onDestroy()
    }
}
