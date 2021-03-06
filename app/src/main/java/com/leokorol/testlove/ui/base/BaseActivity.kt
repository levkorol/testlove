package com.leokorol.testlove.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.leokorol.testlove.TestApp
import com.leokorol.testlove.data_base.AuthManagerTest

open class BaseActivity : AppCompatActivity() {

    open val isTestListenersEnabled = false

    override fun onResume() {
        super.onResume()
        if (isTestListenersEnabled) enableTestListeners()
    }

    override fun onPause() {
        super.onPause()
        if (isTestListenersEnabled) disableTestListeners()
    }

    protected open fun onResultsDone(count: Int) {}
   // protected open fun onResultDoneTwo(count: Int) {}

    private fun enableTestListeners() {
        // подписываем AuthManagerTest на результаты
        AuthManagerTest.isConnectedPartner(TestApp.getUserCode(), {
            AuthManagerTest.subscribePartnerTestResults(TestApp.getPartnerCode())
            AuthManagerTest.subscribeMyTestResults(TestApp.getUserCode())
        }, {})

        AuthManagerTest.setTest1Listener { my, partner ->
            onResultsDone(equalAnswerCount(my, partner))
        }

        AuthManagerTest.setTest2Listener { my, partner ->
            onResultsDone(equalAnswerCount(my, partner))
        }

        AuthManagerTest.setTest3Listener { my, partner ->
            onResultsDone(equalAnswerCount(my, partner))
        }
    }

    private fun equalAnswerCount(
        my: List<List<Any>>?,
        partner: List<List<Any>>?
    ): Int {
        var equalAnswer = -1
        if (my != null && partner != null && my.size == partner.size) {
            for (i in my.indices) {
                if (my[i] == partner[i]) {
                    equalAnswer++
                }
            }
        }
        return equalAnswer
    }

    private fun disableTestListeners() {
        AuthManagerTest.setTest1Listener { _, _ -> }
        AuthManagerTest.setTest2Listener { _, _ -> }
        AuthManagerTest.setTest3Listener { _, _ -> }
    }
}
