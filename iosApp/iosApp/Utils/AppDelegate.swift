//
//  AppDelegate.swift
//  iosApp
//
//  Created by Hari Agus Widakdo on 02/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import ComposeApp
import SwiftUI

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        PreferencesFactory().setInstance(preferences: IOSPreferences())
        return true
    }
    
}
