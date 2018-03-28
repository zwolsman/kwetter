//
//  Page.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 28/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import Foundation

struct Page<T> : Codable where T : Codable {
    let content: [T]
    let page: Int
    let size: Int
    let totalElements: Int
    let numberOfElements: Int
    let first: Bool
    let last: Bool
    let totalPages: Int
}
