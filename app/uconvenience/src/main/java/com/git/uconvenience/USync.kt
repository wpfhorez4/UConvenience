package com.git.uconvenience

import android.util.Log
import java.util.*

class USync {
    private val USYNC_TAG = "geunyeol"

    private val lock = Object()
    private var loop = true
    private var startLockTime: Long = 0
    private var currentLockTime: Long = 0

    fun nowLock(waitMillis: Long, timeoutMillis: Long) {
        synchronized(lock) {
            startLockTime = Date().time
            currentLockTime = startLockTime
            loggerD("SynchronizedModel lock start")
            while(loop) {
                if(currentLockTime - startLockTime > timeoutMillis) {
                    Log.e(USYNC_TAG, "SynchronizedModel lock timeout")
                    break
                } else {
                    currentLockTime = Date().time
                }
                lock.wait(waitMillis)
            }
            loop = true
            loggerD("SynchronizedModel lock end")
        }
    }

    fun nowNotifyAll() {
        synchronized(lock) {
            loggerD("SynchronizedModel notifyAll start")
            loop = false
            lock.notifyAll()
            loggerD("SynchronizedModel notifyAll end")
        }
    }

    fun nowNotify() {
        synchronized(lock) {
            loggerD("SynchronizedModel notify start")
            loop = false
            lock.notify()
            loggerD("SynchronizedModel notify end")
        }
    }

    private fun loggerD(msg: String) { Log.d(USYNC_TAG, msg) }
}