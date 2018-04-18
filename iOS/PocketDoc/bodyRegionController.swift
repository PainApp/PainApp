//
//  bodyRegionController.swift
//  PocketDoc
//
//  Created by Ryan on 4/17/18.
//  Copyright © 2018 PocketDoc. All rights reserved.
//

import Foundation
import UIKit

class bodyRegionController: UIViewController {
    
    var regionId: Int = -1
    let baseUrl: String = "http://18.218.162.185:8080/PocketDoc/body_regions"
    //var id: Int = -1
    //var region: String = ""
    var subRegions = [Any] ()
    @IBOutlet weak var temp: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    func displayRegions() {
        
        let jsonID: [String: Int] = ["id":regionId]
        let jsonData = try? JSONSerialization.data(withJSONObject: jsonID)
        let url = URL(string: baseUrl)
        var request = URLRequest(url: url!)
        request.httpMethod = "POST"
        request.httpBody = jsonData
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("application/json", forHTTPHeaderField: "Accept")

        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data = data, error == nil else {
                print(error?.localizedDescription ?? "No data")
                return
            }
            let json = try? JSONSerialization.jsonObject(with: data, options: [])
            if let json = json as? [String: Any] {
                //print(json)
                print(json["id"] as! Int)
                print(json["name"] as! String)
                print(json["specific_regions"] as! [Any])
                //print(((json["specific_regions"] as! NSArray)[0]) as! [String: Any])
                //print((((json["specific_regions"] as! NSArray)[0]) as! [String: Any])["name"] as! String)
                self.subRegions = (json["specific_regions"] as! [Any])
                DispatchQueue.main.async {
                    //self.id = (json["id"] as! Int)
                    // UPDATE TABLE VIEW
                    self.temp.text = ((json["name"] as! String) + " Specific Regions")
                    //self.tableView.reloadData()
                }
            }
        }
        task.resume()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        displayRegions()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.title = "PocketDoc"
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
