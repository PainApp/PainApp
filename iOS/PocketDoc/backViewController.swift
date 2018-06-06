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
    
    let baseUrl: String = "http://18.218.162.185:8080/PocketDoc/body_regions"
    
    @IBAction func imageClicked(_ sender: AnyObject) {
        self.performSegue(withIdentifier: "showRegionPage2", sender: sender.view!.tag)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let backItem = UIBarButtonItem()
        backItem.title = "Back"
        navigationItem.backBarButtonItem = backItem
        if (segue.identifier == "showRegionPage2") {
            let regionController = segue.destination as! bodyRegionController
            let id = sender as! Int
            regionController.regionId = id
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationItem.title = "PocketDoc"
        //self.navigationController!.navigationBar.topItem!.title = "Back"
        navigationController?.navigationBar.titleTextAttributes = [NSAttributedStringKey.foregroundColor:UIColor.white, NSAttributedStringKey.font: UIFont.systemFont(ofSize: 26)]
    }
    
    @IBAction func flipToFront(_ sender: Any) {
        self.navigationController?.popViewController(animated: false)
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
