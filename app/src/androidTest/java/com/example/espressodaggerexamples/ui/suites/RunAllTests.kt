package com.example.espressodaggerexamples.ui.suites

import com.example.espressodaggerexamples.ui.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@UseExperimental(InternalCoroutinesApi::class)
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainNavigationTests::class,
    ListFragmentNavigationTests::class,
    ListFragmentIntegrationTests::class,
    ListFragmentErrorTests::class,
    DetailFragmentTest::class
)
class RunAllTests