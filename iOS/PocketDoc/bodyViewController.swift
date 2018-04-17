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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let navColor = UIColor(red: 30/255.0, green: 96/255.0, blue: 171/255.0, alpha: 1.0)
        navigationController?.navigationBar.barTintColor = navColor
        self.navigationController?.navigationBar.tintColor = UIColor.white
        self.navigationItem.title = "PocketDoc"
        navigationController?.navigationBar.titleTextAttributes = [NSAttributedStringKey.foregroundColor:UIColor.white, NSAttributedStringKey.font: UIFont.systemFont(ofSize: 26)]
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let backItem = UIBarButtonItem()
        backItem.title = "Back"
        navigationItem.backBarButtonItem = backItem
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
