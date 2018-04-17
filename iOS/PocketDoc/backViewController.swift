//
//  backViewController.swift
//  PocketDoc
//
//  Created by Stevens Mac on 4/16/18.
//  Copyright © 2018 PocketDoc. All rights reserved.
//

import Foundation
import UIKit

class backViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationItem.title = "PocketDoc"
        //self.navigationController!.navigationBar.topItem!.title = "Back"
        navigationController?.navigationBar.titleTextAttributes = [NSAttributedStringKey.foregroundColor:UIColor.white, NSAttributedStringKey.font: UIFont.systemFont(ofSize: 26)]
    }
    
    @IBAction func flipToFront(_ sender: Any) {
        self.navigationController?.popViewController(animated: false)
    }
    
    //override func viewWillAppear(_ animated: Bool) {
        //let backButton = UIBarButtonItem(title: "Back", style: .plain, target: nil, action: nil)
        //navigationItem.backBarButtonItem = backButton
        //self.navigationController!.navigationBar.backItem?.title = "Back"
    //}
    
    //override func viewWillDisappear(_ animated: Bool) {
        //if !(navigationController?.viewControllers)!.contains(self) {
            // back button was pressed
            //self.navigationController?.popToRootViewController(animated: false)
        //}
        //super.viewWillDisappear(animated)
    //}
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
