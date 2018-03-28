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
class TimelineController : UITableViewController {
    
    let kweets = Variable<[Kweet]>([])
    let disposeBag = DisposeBag()
    
    override func viewDidLoad() {
        tableView.register(UINib(nibName: "KweetTableViewCell", bundle: nil), forCellReuseIdentifier: cellIdentifier)
        tableView.tableFooterView = UIView()
        tableView.dataSource = nil
        tableView.delegate = nil
        ApiService.timeline.bind(to: kweets).disposed(by: disposeBag)
        kweets.asObservable().bind(to: tableView.rx.items(cellIdentifier: cellIdentifier, cellType: KweetTableViewCell.self)) {  _, element, cell in
                cell.setup(kweet: element)
            }
            .disposed(by: disposeBag)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        if let createKweetController = segue.destination as? CreateKweetViewController {
            createKweetController.newKweet.subscribe(onNext: {[weak self] kweet in
                self?.kweets.value.insert(kweet, at: 0)
                self?.dismiss(animated: true, completion: nil)
            }).disposed(by: disposeBag)
        }
    }
}
