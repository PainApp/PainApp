//
//  bodyRegionController.swift
//  PocketDoc
//
//  Created by Ryan on 4/17/18.
//  Copyright © 2018 PocketDoc. All rights reserved.
//

import Foundation
import UIKit

class bodyRegionController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    var regionId: Int = -1
    let baseUrl: String = "http://18.218.162.185:8080/PocketDoc/body_regions"
    var subRegions = [Any] ()
    //let regions: [String] = ["please","work","thanks"]
    var regions: NSArray = []
    let cellReuseIdentifier = "cell"
    @IBOutlet weak var temp: UILabel!
    @IBOutlet weak var regionView: UITableView!
    
    func displayRegions(completionHandler: @escaping ([String: Any], NSError?) -> Void) {
        
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
                //print(json["id"] as! Int)
                //print(json["name"] as! String)
                //print(json["specific_regions"] as! [Any])
                completionHandler(json, nil)
                //print(((json["specific_regions"] as! NSArray)[0]) as! [String: Any])
                //print((((json["specific_regions"] as! NSArray)[0]) as! [String: Any])["name"] as! String)
                //self.subRegions = (json["specific_regions"] as! [Any])
                self.regions = json["specific_regions"] as! NSArray
                DispatchQueue.main.async {
                    self.temp.text = ((json["name"] as! String) + " Specific Regions")
                    self.regionView.reloadData()
                }
            }
        }
        task.resume()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        displayRegions() { json, error in
            print(json)
            //self.temp.text = ((json["name"] as! String) + " Specific Regions")
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.title = "PocketDoc"
        self.regionView.register(UITableViewCell.self, forCellReuseIdentifier: cellReuseIdentifier)
        regionView.delegate = self
        regionView.dataSource = self
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.regions.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:UITableViewCell = self.regionView.dequeueReusableCell(withIdentifier: cellReuseIdentifier) as UITableViewCell!
        cell.textLabel?.text = ((self.regions[indexPath.row] as! [String:Any])["name"] as! String)
        return cell
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
