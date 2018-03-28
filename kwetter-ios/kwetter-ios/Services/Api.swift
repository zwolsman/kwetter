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
    private static let HOST = "145.93.129.15"
    private static let PORT = 8080
    private static let PROTOCOL = "http"
    private static let PATH = "/api"
    private static let BASE_URL = "\(PROTOCOL)://\(HOST):\(PORT)\(PATH)"
    
    private static let decoder = JSONDecoder()
    private static var token: String? = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIifQ.C4mgBFyMNf5TUhA3ddhqxbmFYADLIgZh3GYqPsoOorE"
    
    enum Endpoint {
        case timeline
        case tweets(user: String)
        case info(user: String)
        case createKweet
        var path: String {
            switch(self) {
            case .timeline:
                return "/user/timeline"
            case .tweets(let user):
                return "/user/\(user)/kweets"
            case .info(let user):
                return "/user/\(user)"
            case .createKweet:
                return "/kweet"
            }
        }
    }
    
    static var timeline:Observable<[Kweet]> {
        return request(endpoint: .timeline).map { (page: Page<Kweet>) in
            return page.content
        }
    }
    
    static func tweetsFor(user: String) -> Observable<[Kweet]> {
        return request(endpoint: .tweets(user: user))
    }
    
    static func infoFor(user: String) -> Observable<KweetUserResource> {
        return request(endpoint: .info(user: user))
    }
    
    static func post(kweet: String) -> Observable<Kweet> {
        return request(endpoint: .createKweet, query: ["text" : kweet], method: .post)
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
            request.httpBody = normalizedQuery.map({ (key, value) -> String in
                return "\(key)=\(value)"
            }).joined(separator: "&").data(using: .utf8)!
            request.addValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        }
        
        if(token != nil) {
            request.addValue("Bearer \(token!)", forHTTPHeaderField: "Authorization")
        }
        
        
        let response = URLSession.shared.rx.response(request: request)
        return response.map { _, data in
            print(String(bytes: data, encoding: .utf8)!)
            return try! decoder.decode(T.self, from: data) //TODO decode error and present to user
        }

    }
    
    enum HttpMethod : String {
        case get = "GET"
        case post = "POST"
    }
}
