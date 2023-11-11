package com.appstateemitterlistener

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.UiThreadUtil.runOnUiThread
import com.facebook.react.bridge.WindowFocusChangeListener
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule

class AppstateListenerModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  companion object {
    const val NAME = "AppstateListener"
  }

  init {
    reactContext.addLifecycleEventListener(object : LifecycleEventListener {
      override fun onHostResume() {
        var map = Arguments.createMap()
        map.putString("state", "active")
        sendEvent(reactContext, "change", map)
      }

      override fun onHostDestroy() {
        var map = Arguments.createMap()
        map.putString("state", "destroyed")
        sendEvent(reactContext, "change", map)
      }

      override fun onHostPause() {
        var map = Arguments.createMap()
        map.putString("state", "inactive")
        sendEvent(reactContext, "change", map)
      }
    })

    reactContext.addWindowFocusChangeListener(object: WindowFocusChangeListener {
      override fun onWindowFocusChange(p0: Boolean) {
        var map = Arguments.createMap()
        map.putBoolean("isFocused", p0)
        sendEvent(reactContext, "window", map)
      }
    })
  }

  private fun sendEvent(reactContext: ReactContext, eventName: String, params: WritableMap?) {
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
      .emit(eventName, params)
  }

  private var listenerCount = 0

  /// Methods
  @ReactMethod
  fun initActivityListener() {
    runOnUiThread {
      (currentActivity as? AppCompatActivity)?.let {
        it.lifecycle.addObserver(object :
          DefaultLifecycleObserver {
          override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            var map = Arguments.createMap()
            map.putString("state", "create")
            sendEvent(reactApplicationContext, "activityChange", map)
          }

          override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            var map = Arguments.createMap()
            map.putString("state", "start")
            sendEvent(reactApplicationContext, "activityChange", map)
          }

          override fun onPause(owner: LifecycleOwner) {
            super.onPause(owner)
            var map = Arguments.createMap()
            map.putString("state", "pause")
            sendEvent(reactApplicationContext, "activityChange", map)
          }

          override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            var map = Arguments.createMap()
            map.putString("state", "resume")
            sendEvent(reactApplicationContext, "activityChange", map)
          }

          override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            var map = Arguments.createMap()
            map.putString("state", "stop")
            sendEvent(reactApplicationContext, "activityChange", map)
          }

          override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            var map = Arguments.createMap()
            map.putString("state", "destroy")
            sendEvent(reactApplicationContext, "activityChange", map)
          }
        })
      }
    }

  }
  @ReactMethod
  fun addListener(eventName: String) {
    if (listenerCount == 0) {
      // Set up any upstream listeners or background tasks as necessary
    }

    listenerCount += 1
  }

  @ReactMethod
  fun removeListeners(count: Int) {
    listenerCount -= count
    if (listenerCount == 0) {
      // Remove upstream listeners, stop unnecessary background tasks
    }
  }

}
