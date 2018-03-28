import Foundation
struct KweetEntities : Codable {
	let hashtags : [Hashtag]
	let userMentions : [UserMention]
	let urls : [Url]
}

struct Hashtag : Codable {
    let _range : [Int]
    let text : String
    enum CodingKeys: String, CodingKey {
        case _range = "range"
        case text
    }
    
    var range : NSRange {
        return NSRange(location: _range[0], length: _range[1] - _range[0] + 1)
    }
}

struct UserMention : Codable {
    let _range: [Int]
    let name: String
    
    enum CodingKeys: String, CodingKey {
        case _range = "range"
        case name
    }
    
    var range : NSRange  {
        return NSRange(location: _range[0], length: _range[1] - _range[0] + 1)
    }
}

struct Url : Codable {
    let _range: [Int]
    let url : String
    let expandedUrl: String
    let displayUrl: String
    
    var range : NSRange  {
        return NSRange(location: _range[0], length: _range[1] - _range[0] + 1)
    }
    
    enum CodingKeys: String, CodingKey {
        case _range = "range"
        case url
        case expandedUrl
        case displayUrl
    }
    
}
