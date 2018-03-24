//
//  TimelineTableViewCell.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 24/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import UIKit
import Kingfisher

class TimelineTableViewCell: UITableViewCell {

    @IBOutlet weak var profileImage: UIImageView!
    @IBOutlet weak var fullName: UILabel!
    @IBOutlet weak var userName: UILabel!
    @IBOutlet weak var timeAgo: UILabel!
    @IBOutlet weak var content: UITextView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setup(kweet: Kweet) {
        content.text = kweet.text
        userName.text = "@" + kweet.user.username
        timeAgo.text = "0s"
        profileImage.kf.setImage(with: URL(string: kweet.user.profileImageUrl)!)
    }

}
