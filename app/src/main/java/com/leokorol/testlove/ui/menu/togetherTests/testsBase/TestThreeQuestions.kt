package com.leokorol.testlove.ui.menu.togetherTests.testsBase

import com.leokorol.testlove.R
import com.leokorol.testlove.TestApp
import com.leokorol.testlove.ui.base.BaseTestQuestionsActivity
import com.leokorol.testlove.ui.menu.togetherTests.testsTexts.Questions

class TestThreeQuestions : BaseTestQuestionsActivity(
    Questions.Part3,
    R.layout.activity_test_three_questions,
    R.drawable.threetitlebg,
    ANSWERS_3,
    2,
    TestApp.LAST_QUESTION_3
)