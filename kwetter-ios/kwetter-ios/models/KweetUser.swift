import Foundation
struct KweetUser : Codable {
	let username : String
	let profileImageUrl : String
	let followersCount : Int
	let friendsCount : Int
}
