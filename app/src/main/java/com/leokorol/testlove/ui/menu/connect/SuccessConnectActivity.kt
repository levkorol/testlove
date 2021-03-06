package com.leokorol.testlove.ui.menu.connect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leokorol.testlove.R
import com.leokorol.testlove.ui.menu.MenuLauncherActivity
import com.leokorol.testlove.ui.menu.togetherTests.MenuTestsActivity
import com.leokorol.testlove.utils.replaceActivity
import kotlinx.android.synthetic.main.activity_success_connect.*

class SuccessConnectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_connect)

        clickListeners()

    }

    private fun clickListeners() {
        buttonGoTests.setOnClickListener { replaceActivity(MenuTestsActivity()) }
        sgoMenuActivity.setOnClickListener { replaceActivity(MenuLauncherActivity()) }
    }
}