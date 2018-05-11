//
//  ViewController.swift
//  PocketDoc
//
//  Created by Ryan on 4/3/18.
//  Copyright © 2018 PocketDoc. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var homeTitle: UILabel!
    @IBOutlet weak var start: UIButton!
    //@IBOutlet weak var search: UIButton!
    @IBOutlet weak var tutorial: UIButton!
    @IBOutlet weak var about: UIButton!
    @IBOutlet weak var navBar: UINavigationItem!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        let backColor = UIColor(red: 30/255.0, green: 96/255.0, blue: 171/255.0, alpha: 1.0)
        view.backgroundColor = backColor
        homeTitle.textColor = UIColor.white
        start.setTitleColor(UIColor.white, for: .normal)
        //search.setTitleColor(UIColor.white, for: .normal)
        tutorial.setTitleColor(UIColor.white, for: .normal)
        about.setTitleColor(UIColor.white, for: .normal)
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
        super.viewWillAppear(animated)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        super.viewWillDisappear(animated)
    }

    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

