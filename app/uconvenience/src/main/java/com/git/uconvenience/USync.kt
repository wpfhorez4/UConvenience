package com.git.uconvenience

import java.util.*

class USync {
    private val lock = Object()
    private var loop = true
    private var startLockTime: Long = 0
    private var currentLockTime: Long = 0

    interface USyncInterface {
        fun syncFail()
    }

    fun nowLock(syncFail: USyncInterface? = null, waitMillis: Long = 3, timeoutMillis: Long = 3000) {
        synchronized(lock) {
            startLockTime = Date().time
            currentLockTime = startLockTime
            while(loop) {
                if(currentLockTime - startLockTime > timeoutMillis) {
                    syncFail?.syncFail()
                    break
                } else {
                    currentLockTime = Date().time
                }
                lock.wait(waitMillis)
            }
            loop = true
        }
    }

    fun nowNotifyAll() {
        synchronized(lock) {
            loop = false
            lock.notifyAll()
        }
    }

    fun nowNotify() {
        synchronized(lock) {
            loop = false
            lock.notify()
        }
    }
}