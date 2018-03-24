//
//  TimelineViewController.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 24/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import UIKit

class TimelineController : UITableViewController {
    
    let filename = "mockup"
    var kweets = [Kweet]()
    override func viewDidLoad() {
        if let path = Bundle.main.path(forResource: filename, ofType: "json") {
            do {
                let data = try Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
                let jsonResult = try JSONDecoder().decode([Kweet].self, from: data)
                kweets += jsonResult
                print("Loaded kweets")
                debugPrint(kweets)
            } catch {
                // handle error
                print("OH GOD")
            }
        }
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 0
    }
}
