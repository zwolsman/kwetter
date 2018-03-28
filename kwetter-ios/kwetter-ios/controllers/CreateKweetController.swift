//
//  CreateKweetController.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 24/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import Foundation
import UIKit
import RxSwift
import Kingfisher
import MBCircularProgressBar

class CreateKweetViewController : UIViewController, UITextViewDelegate {
    let disposeBag = DisposeBag()
    let userInfo = Variable<KweetUserResource?>(nil)
    @IBOutlet weak var textView: UITextView!
    
    @IBOutlet weak var progress: MBCircularProgressBarView!
    @IBOutlet weak var profileImage: UIImageView!
    
    override func viewDidLoad() {
        textView.becomeFirstResponder()
        ApiService.infoFor(user: "tester").bind(to: userInfo).disposed(by: disposeBag)

        userInfo.asObservable().map { $0?.profileImageUrl ?? "" }.subscribe(onNext: {[weak self] in
            guard let url = URL(string: $0) else { return }
            let processor = ResizingImageProcessor(referenceSize: CGSize(width: 200, height: 200)) >> RoundCornerImageProcessor(cornerRadius: 100)
            self?.profileImage.kf.setImage(with: url, placeholder: nil, options: [.processor(processor)])
        }).disposed(by: disposeBag)
    }
    
    func textViewDidChange(_ textView: UITextView) {
        progress.value = CGFloat(textView.text.count)
    }
    
}
