package com.leokorol.testlove.activites.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.leokorol.testlove.R
import com.leokorol.testlove.TestApp
import com.leokorol.testlove.activites.results.ResultActivityTestThree
import com.leokorol.testlove.activites.results.ResultActivityTestTwo
import com.leokorol.testlove.activites.results.ResultsActivityTestOne
import com.leokorol.testlove.activites.tests.TestOneQuestions
import com.leokorol.testlove.activites.tests.TestThreeQuestions
import com.leokorol.testlove.activites.tests.TestTwoQuestions
import com.leokorol.testlove.data_base.AuthManager
import com.leokorol.testlove.data_base.AuthManager2
import com.leokorol.testlove.utils.replaceActivity
import com.leokorol.testlove.utils.showToast
import kotlinx.android.synthetic.main.activity_tests_menu.*

class MenuTestsActivity : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_tests_menu)
        deleteProgressListeners()
        clickListeners()
        checkLastSession()

        AuthManager2.setTest1Listener { my, partner ->
            showToast("Вы оба пришли тест 1. Можете посмотреть результаты в результатах")
        }

        AuthManager2.setTest2Listener { my, partner ->
            showToast("Вы оба пришли тест 2. Можете посмотреть результаты в результатах")
        }

        AuthManager2.setTest3Listener { my, partner ->
            showToast("Вы оба пришли тест 3. Можете посмотреть результаты в результатах")
        }

    }

    private fun deleteProgressListeners() {
        tvSessionDeleteTest1.setOnClickListener {
            TestApp.sharedPref?.edit()?.putInt(TestApp.LAST_QUESTION_1, 0)?.apply()
            tvSessionInfoTest1.text = "Текущий прогресс Теста №1: 0/60"
        }

        tvSessionDeleteTest2.setOnClickListener {
            TestApp.sharedPref?.edit()?.putInt(TestApp.LAST_QUESTION_2, 0)?.apply()
            tvSessionInfoTest2.text = "Текущий прогресс Теста №2: 0/45"
        }

        tvSessionDeleteTest3.setOnClickListener {
            TestApp.sharedPref?.edit()?.putInt(TestApp.LAST_QUESTION_3, 0)?.apply()
            tvSessionInfoTest3.text = "Текущий прогресс Теста №3: 0/40"
        }
    }

    private fun checkLastSession() {

        val lastSession = TestApp.sharedPref?.getString(TestApp.SESSiON_CODE, "")

        if (lastSession!!.isNotEmpty()) {

            val lastQuestion1 = TestApp.sharedPref?.getInt(TestApp.LAST_QUESTION_1, 0)
            val lastQuestion2 = TestApp.sharedPref?.getInt(TestApp.LAST_QUESTION_2, 0)
            val lastQuestion3 = TestApp.sharedPref?.getInt(TestApp.LAST_QUESTION_3, 0)

            tvSessionInfoTest1.text = "Текущий прогресс Теста №1: $lastQuestion1/60"
            tvSessionInfoTest2.text = "Текущий прогресс Теста №2: $lastQuestion2/45"
            tvSessionInfoTest3.text = "Текущий прогресс Теста №3: $lastQuestion3/40"

            val database = FirebaseDatabase.getInstance()
            database.reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    //                  val database = FirebaseDatabase.getInstance()
//                    val sessionsRef = database.getReference("sessions")

                    // TODO забирать данные по ответам тут

                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }

    private fun clickListeners() {
        goMenuActivity?.setOnClickListener { replaceActivity(MenuLauncherActivity()) }

        testOneButton.setOnClickListener { goToTestOneTitle() }
        testTwoButton.setOnClickListener { goToTestTwoActivity() }
        testThreeButton.setOnClickListener { goToTestThreeActivity() }

        tvSessionResultTest1.setOnClickListener { replaceActivity(ResultsActivityTestOne()) }
        tvSessionResultTest2.setOnClickListener { replaceActivity(ResultActivityTestTwo()) }
        tvSessionResultTest3.setOnClickListener { replaceActivity(ResultActivityTestThree()) }

    }

    private fun goToTestOneTitle() {
        AuthManager.instance.currentPart = 1
        replaceActivity(TestOneQuestions())
    }

    private fun goToTestTwoActivity() {
        AuthManager.instance.currentPart = 2
        replaceActivity(TestTwoQuestions())
    }

    private fun goToTestThreeActivity() {
        AuthManager.instance.currentPart = 3
        replaceActivity(TestThreeQuestions())
    }

}
