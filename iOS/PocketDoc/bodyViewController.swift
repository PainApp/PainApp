//
//  bodyViewController.swift
//  PocketDoc
//
//  Created by Ryan on 4/3/18.
//  Copyright © 2018 PocketDoc. All rights reserved.
//

import Foundation
import UIKit

class bodyViewController: UIViewController {
    
    @IBOutlet weak var navBar: UINavigationBar!
    @IBOutlet weak var navTitle: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let navColor = UIColor(red: 30/255.0, green: 96/255.0, blue: 171/255.0, alpha: 1.0)
        navBar.backgroundColor = navColor
        navBar.barTintColor = navColor
        navTitle.textColor = UIColor.white
        
        let temp: CGFloat = 100
        navBar.frame = CGRect(x: 0, y: 0, width: navBar.bounds.width, height: navBar.bounds.height - temp)
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
