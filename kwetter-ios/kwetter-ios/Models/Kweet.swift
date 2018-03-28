import Foundation
struct Kweet : Codable {
	let id : String
	let text : String
	let createdAt : String
	let entities : KweetEntities
	let user : KweetUser
}
