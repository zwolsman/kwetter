//
//  TimelineViewController.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 24/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import UIKit

let cellIdentifier = "kweetCell"
class TimelineController : UITableViewController {
    
    let filename = "mockup"
    var kweets = [Kweet]()
    override func viewDidLoad() {
        if let path = Bundle.main.path(forResource: filename, ofType: "json") {
            do {
                let data = try Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
                let jsonResult = try JSONDecoder().decode([Kweet].self, from: data)
                kweets += jsonResult
                tableView.reloadData()
                print("Loaded kweets")
            } catch {
                // handle error
                print("OH GOD")
            }
        }
    }
    
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return kweets.count
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier) as! TimelineTableViewCell
        cell.setup(kweet: kweets[indexPath.row])
        return cell
    }
}
