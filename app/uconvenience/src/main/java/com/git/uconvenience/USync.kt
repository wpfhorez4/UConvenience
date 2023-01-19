package com.git.uconvenience

import android.util.Log
import java.util.*

class USync(tag: String = "geunyeol") {
    private val USYNC_TAG: String

    private val lock = Object()
    private var loop = true
    private var startLockTime: Long = 0
    private var currentLockTime: Long = 0

    init {
        USYNC_TAG = tag
    }

    interface USyncInterface {
        fun syncFail()
    }

    fun nowLock(waitMillis: Long = 3, timeoutMillis: Long = 3000, tag: String = USYNC_TAG, syncFail: USyncInterface? = null) {
        synchronized(lock) {
            startLockTime = Date().time
            currentLockTime = startLockTime
            Log.v(tag, "Synchronized lock start")
            while(loop) {
                if(currentLockTime - startLockTime > timeoutMillis) {
                    Log.e(USYNC_TAG, "Synchronized lock timeout")
                    syncFail?.syncFail()
                    break
                } else {
                    currentLockTime = Date().time
                }
                lock.wait(waitMillis)
            }
            loop = true
            Log.d(tag, "Synchronized lock end")
        }
    }

    fun nowNotifyAll(tag: String = USYNC_TAG) {
        synchronized(lock) {
            Log.v(tag, "Synchronized notifyAll start")
            loop = false
            lock.notifyAll()
            Log.d(tag, "Synchronized notifyAll end")
        }
    }

    fun nowNotify(tag: String = USYNC_TAG) {
        synchronized(lock) {
            Log.v(tag, "Synchronized notify start")
            loop = false
            lock.notify()
            Log.d(tag, "Synchronized notify end")
        }
    }
}