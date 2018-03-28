//
//  TimelineTableViewCell.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 24/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import UIKit
import Kingfisher
import SwiftyAttributes
import RelativeFormatter

class KweetTableViewCell: UITableViewCell {
    
    @IBOutlet weak var profileImage: UIImageView!
    @IBOutlet weak var fullName: UILabel!
    @IBOutlet weak var userName: UILabel!
    @IBOutlet weak var timeAgo: UILabel!
    @IBOutlet weak var content: UITextView!
    private let processor = ResizingImageProcessor(referenceSize: CGSize(width: 200, height: 200)) >> RoundCornerImageProcessor(cornerRadius: 100)

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setup(kweet: Kweet) {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSZ"
        let date = Date(timeIntervalSince1970: kweet.createdAt / 1000)
        
        let attributedString = NSMutableAttributedString(string: kweet.text)
        
        debugPrint(kweet.entities)
        for url in kweet.entities.urls {
            attributedString.addAttribute(NSAttributedStringKey.link, value: url.displayUrl, range: url.range)
        }
        for hashtag in kweet.entities.hashtags {
            attributedString.addAttribute(NSAttributedStringKey.link, value: hashtag.text, range: hashtag.range)
        }
        for userMention in kweet.entities.userMentions {
            attributedString.addAttribute(NSAttributedStringKey.link, value: userMention.name, range: userMention.range)
        }
        
        
        content.attributedText = attributedString
        userName.text = "@" + kweet.user.username
        timeAgo.text = date.relativeFormatted()
        
        profileImage.kf.setImage(with: URL(string: kweet.user.profileImageUrl)!, placeholder: nil, options: [.processor(processor)])
    }

}
