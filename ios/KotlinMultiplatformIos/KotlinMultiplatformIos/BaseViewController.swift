//
//  BaseViewController.swift
//  KotlinMultiplatformIos
//
//  Created by V-Mobile on 10/18/18.
//  Copyright © 2018 V-Mobile. All rights reserved.
//

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
