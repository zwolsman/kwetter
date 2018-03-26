//
//  Api.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 24/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa

class ApiService {
    private static let HOST = "192.168.178.45"
    private static let PORT = 8080
    private static let PROTOCOL = "http"
    private static let PATH = "/api"
    private static let BASE_URL = "\(PROTOCOL)://\(HOST):\(PORT)\(PATH)"
    
    private static let decoder = JSONDecoder()
 
    
    enum Endpoint {
        case timeline
        case tweets(user: String)
        case info(user: String)
        var path: String {
            switch(self) {
            case .timeline:
                return "/user/"
            case .tweets(let user):
                return "/user/\(user)/kweets"
            case .info(let user):
                return "/user/\(user)"
            }
        }
    }
    
    static var timeline:Observable<[Kweet]> {
        return request(endpoint: .timeline, query: ["user": "tester"]) //TODO login stuff
    }
    
    static func tweetsFor(user: String) -> Observable<[Kweet]> {
        return request(endpoint: .tweets(user: user))
    }
    
    static func infoFor(user: String) -> Observable<KweetUserResource> {
        return request(endpoint: .info(user: user))
    }
    
    private static func request<T>(endpoint: Endpoint, query: [String: CustomStringConvertible] = [:], method: HttpMethod = HttpMethod.get) -> Observable<T> where T : Codable {
        guard let url = URL(string: BASE_URL)?.appendingPathComponent(endpoint.path), var components = URLComponents(url: url, resolvingAgainstBaseURL: true) else {
            fatalError("Could not construct the url")
        }
        let normalizedQuery = query.mapValues { value -> String in
            value.description
        }
        
        if method != .post {
            components.queryItems = query.flatMap { (key, value) in
                return URLQueryItem(name: key, value: value.description)
            }
        }
        
        guard let finalURL = components.url else {
            return Observable.empty()
        }
        
        var request = URLRequest(url: finalURL)
        request.httpMethod = method.rawValue
        
        if method == .post {
            request.httpBody = try! JSONEncoder().encode(normalizedQuery)
            request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        }
        
        
        let response = URLSession.shared.rx.response(request: request)
        return response.map { _, data in
            if let json = try? decoder.decode(T.self, from: data) {
                return json
            }
            debugPrint(String(bytes: data, encoding: .utf8)!)
            fatalError("Error decodoing stuff")
        }

    }
    
    enum HttpMethod : String {
        case get = "GET"
        case post = "POST"
    }
}
