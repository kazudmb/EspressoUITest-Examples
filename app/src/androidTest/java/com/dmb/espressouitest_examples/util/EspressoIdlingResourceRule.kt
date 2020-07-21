package com.dmb.espressouitest_examples.util

import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.lang.Exception

//class EspressoIdlingResourceRule : TestRule {
//    override fun apply(base: Statement?, description: Description?): Statement {
//        return IdlingResourcesStatement(base)
//    }
//
//    class IdlingResourcesStatement(private val base: Statement?): Statement() {
//
//        private val idlingResource = EspressoIdlingResource.countingIdlingResource
//
//        override fun evaluate() {
//            // Before
//            IdlingRegistry.getInstance().register(idlingResource)
//            try {
//                base?.evaluate()
//                    ?: throw Exception("Error evaluating test. Base statement is null.")
//            }finally {
//                // After
//                IdlingRegistry.getInstance().unregister(idlingResource)
//            }
//        }
//
//    }
//}

class EspressoIdlingResourceRule : TestWatcher() {

    // After
    private val idlingRegistry = EspressoIdlingResource.countingIdlingResource
    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingRegistry)
        super.finished(description)
    }

    // Before
    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingRegistry)
        super.starting(description)
    }
}
