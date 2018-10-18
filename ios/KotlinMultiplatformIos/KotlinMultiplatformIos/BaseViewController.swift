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
    
    var presenter:P? = nil
    
    override func viewWillAppear(_ animated: Bool) {
        createPresenter()
        presenter?.onPresenterCreate(view: self)
        print("presenter created")
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        presenter?.onDestroy()
    }
    
    
    func createPresenter(){
    
    }
}
