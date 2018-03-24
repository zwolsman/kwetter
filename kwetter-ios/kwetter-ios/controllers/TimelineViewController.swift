//
//  TimelineViewController.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 24/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import UIKit
import RxSwift

let cellIdentifier = "kweetCell"
class TimelineController : UITableViewController, UITextViewDelegate {
    
    let disposeBag = DisposeBag()
    
    override func viewDidLoad() {
        tableView.tableFooterView = UIView()
        tableView.dataSource = nil
        tableView.delegate = nil
        ApiService.timeline.bind(to: tableView.rx.items(cellIdentifier: cellIdentifier, cellType: TimelineTableViewCell.self)) {  _, element, cell in
                cell.setup(kweet: element)
            }
            .disposed(by: disposeBag)
    }
    
    func textView(_ textView: UITextView, shouldInteractWith URL: URL, in characterRange: NSRange, interaction: UITextItemInteraction) -> Bool {
        print(URL)
        return true
    }
}
