//
//  AlwaysPopupSegue.swift
//  kwetter-ios
//
//  Created by Marvin Zwolsman on 28/03/2018.
//  Copyright © 2018 Marvin Zwolsman. All rights reserved.
//

import Foundation
import UIKit

class AlwaysPopupSegue : UIStoryboardSegue, UIPopoverPresentationControllerDelegate
{
    override init(identifier: String?, source: UIViewController, destination: UIViewController)
    {
        super.init(identifier: identifier, source: source, destination: destination)
        destination.modalPresentationStyle = UIModalPresentationStyle.popover
        destination.popoverPresentationController!.delegate = self
    }
    func adaptivePresentationStyle(for controller: UIPresentationController) -> UIModalPresentationStyle {
        return UIModalPresentationStyle.none
    }
}
