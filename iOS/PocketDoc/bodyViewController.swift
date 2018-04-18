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
    
    @IBOutlet weak var temp: UILabel!
    let baseUrl: String = "http://18.218.162.185:8080/PocketDoc/body_regions"

    @IBAction func imageClicked(_ sender: AnyObject) {
        self.performSegue(withIdentifier: "showRegionPage", sender: sender.view!.tag)
    }
    
    @IBAction func getRegions(_ sender: AnyObject) {

        let url = URL(string: baseUrl)
        let task = URLSession.shared.dataTask(with: url!) { data, response, error in
            guard let data = data, error == nil else {
                print(error?.localizedDescription ?? "No data")
                return
            }
            let json = try? JSONSerialization.jsonObject(with: data, options: [])
            if let json = json as? [String: Any] {
                print(json)
            }
        }
        task.resume()
    }
    
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
        if (segue.identifier == "showRegionPage") {
            let regionController = segue.destination as! bodyRegionController
            let id = sender as! Int
            regionController.regionId = id
        }
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
