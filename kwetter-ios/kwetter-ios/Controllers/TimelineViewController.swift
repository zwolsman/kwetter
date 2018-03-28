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
class TimelineController : UITableViewController, UITextViewDelegate, UIPopoverPresentationControllerDelegate {
    
    let disposeBag = DisposeBag()
    
    override func viewDidLoad() {
        tableView.register(UINib(nibName: "KweetTableViewCell", bundle: nil), forCellReuseIdentifier: cellIdentifier)
        tableView.tableFooterView = UIView()
        tableView.dataSource = nil
        tableView.delegate = nil
        ApiService.timeline.bind(to: tableView.rx.items(cellIdentifier: cellIdentifier, cellType: KweetTableViewCell.self)) {  _, element, cell in
                cell.setup(kweet: element)
            }
            .disposed(by: disposeBag)
    }
    
    func textView(_ textView: UITextView, shouldInteractWith URL: URL, in characterRange: NSRange, interaction: UITextItemInteraction) -> Bool {
        print(URL)
        return true
    }
    
    func adaptivePresentationStyle(for controller: UIPresentationController) -> UIModalPresentationStyle {
        return .none
    }
}
