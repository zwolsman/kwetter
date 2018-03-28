import Foundation
struct Kweet : Codable {
	let id : String
	let text : String
	let createdAt : Double
	let entities : KweetEntities
	let user : KweetUser
}
