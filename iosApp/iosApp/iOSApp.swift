import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        DiInitializerKt.doInitKoinInIos()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
