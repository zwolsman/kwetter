//
//  KweetUserResource.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 25/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import Foundation

struct KweetUserResource : Codable {
    let username : String
    let profileImageUrl : String
    let followersCount : Int
    let friendsCount : Int
    let kweetCount: Int
}
