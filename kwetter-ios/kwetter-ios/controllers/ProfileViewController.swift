//
//  ProfileViewController.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 25/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import Foundation
import UIKit
import RxSwift
import Kingfisher

class ProfileViewController : UIViewController {
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var kweetCountLabel: UILabel!
    @IBOutlet weak var followerCountLabel: UILabel!
    @IBOutlet weak var followingCountLabel: UILabel!
    @IBOutlet weak var profileImage: UIImageView!
    
    let userInfo = Variable<KweetUserResource?>(nil)
    let disposeBag = DisposeBag()
    
    var user = "tester"
    override func viewDidLoad() {
        setupTableview()
        setupInfo()
    }
    
    
    func setupInfo() {
        userInfo.asObservable().map { $0?.username ?? "Loading.." }.bind(to: navigationItem.rx.title).disposed(by: disposeBag)
        userInfo.asObservable().map { "\($0?.kweetCount ?? 0)" }.bind(to: kweetCountLabel.rx.text).disposed(by: disposeBag)
        userInfo.asObservable().map { "\($0?.followersCount ?? 0)" }.bind(to: followerCountLabel.rx.text).disposed(by: disposeBag)
        userInfo.asObservable().map { "\($0?.friendsCount ?? 0)" }.bind(to: followingCountLabel.rx.text).disposed(by: disposeBag)
        userInfo.asObservable().map { $0?.profileImageUrl ?? "" }.subscribe(onNext: {[weak self] in
            guard let url = URL(string: $0) else { return }
            let processor = ResizingImageProcessor(referenceSize: CGSize(width: 200, height: 200)) >> RoundCornerImageProcessor(cornerRadius: 100)
            self?.profileImage.kf.setImage(with: url, placeholder: nil, options: [.processor(processor)])
        }).disposed(by: disposeBag)
        ApiService.infoFor(user: user).bind(to: userInfo).disposed(by: disposeBag)
    }
    
    func setupTableview() {
        tableView.register(UINib(nibName: "KweetTableViewCell", bundle: nil), forCellReuseIdentifier: cellIdentifier)
        tableView.tableFooterView = UIView()
        tableView.dataSource = nil
        tableView.delegate = nil
        ApiService.tweetsFor(user: user).bind(to: tableView.rx.items(cellIdentifier: cellIdentifier, cellType: KweetTableViewCell.self)) {  _, element, cell in
            cell.setup(kweet: element)
            }
            .disposed(by: disposeBag)
    }
}
