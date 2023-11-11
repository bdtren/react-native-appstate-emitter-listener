import React

@objc(AppstateEmitterListener)
class AppstateEmitterListener: RCTEventEmitter {
    
    override func supportedEvents() -> [String]! {
        return ["change"]
    }
    
    public override init() {
        super.init()
        let nc = NotificationCenter.default
        nc.addObserver(self, selector: #selector(appMovedToBackground), name: UIApplication.didEnterBackgroundNotification, object: nil)
        nc.addObserver(self, selector: #selector(appMovedToForeground), name: UIApplication.willEnterForegroundNotification, object: nil)
           
    }
    
    @objc func appMovedToBackground() {
        sendEvent(withName: "change", body: ["state": "inactive"])
    }

    @objc func appMovedToForeground() {
        sendEvent(withName: "change", body: ["state": "active"])
    }
    
}

