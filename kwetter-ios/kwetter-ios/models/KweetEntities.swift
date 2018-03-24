import Foundation
struct KweetEntities : Codable {
	let hashtags : [Hashtag]
	let userMentions : [UserMention]
	let urls : [Url]
}

struct Hashtag : Codable {
    let range : [Int]
    let text : String
}

struct UserMention : Codable {
    let range: [Int]
    let name: String
}

struct Url : Codable {
    let url : String
    let expandedUrl: String
    let displayUrl: String
}
