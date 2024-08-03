//
//  IOSPreferences.swift
//  iosApp
//
//  Created by Hari Agus Widakdo on 02/08/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import ComposeApp

class IOSPreferences: Preferences {
    
    private let DARK_THEME_KEY = "dark_theme_key"
    private let defaults = UserDefaults.standard
    
    func setDarkThemeEnabled(isEnabled: Bool) {
        defaults.set(isEnabled, forKey: DARK_THEME_KEY)
    }
    
    func isDarkThemeEnabled() -> Bool {
        return defaults.bool(forKey: DARK_THEME_KEY)
    }
    
}
