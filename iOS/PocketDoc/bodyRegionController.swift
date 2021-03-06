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
    var regions: NSArray = []
    let cellSpacingHeight: CGFloat = 10
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
                //print(((json["specific_regions"] as! NSArray)[0]) as! [String: Any])
                //print((((json["specific_regions"] as! NSArray)[0]) as! [String: Any])["name"] as! String)
                //self.subRegions = (json["specific_regions"] as! [Any])
                
                
                completionHandler(json, nil)
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
        //regionView.backgroundView = nil
        regionView.backgroundColor = UIColor.white
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.title = "PocketDoc"
        self.regionView.backgroundColor = .clear
        self.regionView.register(UITableViewCell.self, forCellReuseIdentifier: cellReuseIdentifier)
        self.regionView.separatorStyle = .none
        regionView.allowsSelection = false
        regionView.delegate = self
        regionView.dataSource = self
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.regions.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        //return self.regions.count
        return 1
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return cellSpacingHeight
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let headerView = UIView()
        headerView.backgroundColor = UIColor.clear
        return headerView
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let cell:UITableViewCell = (self.regionView.dequeueReusableCell(withIdentifier: cellReuseIdentifier) as UITableViewCell?)!
//        cell.textLabel?.text = ((self.regions[indexPath.row] as! [String:Any])["name"] as! String)
//        return cell
        let cell = regionView.dequeueReusableCell(withIdentifier: cellReuseIdentifier, for: indexPath)
        
        let button : UIButton = UIButton(type:UIButtonType.custom) as UIButton
        button.frame = CGRect(origin: CGPoint(x: 0,y :60), size: CGSize(width: 343, height: 44))
        let cellHeight: CGFloat = 44.0
        button.center = CGPoint(x: view.bounds.width / 2, y: cellHeight / 2.0)
        
        button.titleLabel?.textAlignment = NSTextAlignment.center
        button.setTitleColor(.black, for: .normal)
        button.addTarget(self, action: #selector(buttonClicked), for: .touchUpInside)
        button.setTitle(((self.regions[indexPath.section] as! [String:Any])["name"] as! String), for: UIControlState.normal)
        button.tag = ((self.regions[indexPath.section] as! [String:Any])["id"] as! Int)
        
        button.titleLabel?.numberOfLines = 0
        button.backgroundColor = .clear
        button.layer.cornerRadius = 5
        button.layer.borderWidth = 1
        button.layer.borderColor = UIColor.black.cgColor

        cell.addSubview(button)
        return cell
    }
    
    @objc func buttonClicked(sender : UIButton) {
        self.performSegue(withIdentifier: "showSubregionPage", sender: sender.tag)
        print((sender.titleLabel?.text)! + " (" + String(sender.tag) + ") touched!")
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let backItem = UIBarButtonItem()
        backItem.title = "Back"
        navigationItem.backBarButtonItem = backItem
        if (segue.identifier == "showSubregionPage") {
            let subregionController = segue.destination as! bodySubregionController
            let id = sender as! Int
            subregionController.subregionId = id
        }
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
