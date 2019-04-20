package com.intuit.sendhil.gitexplorer

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.intuit.sendhil.gitexplorer.di.AppComponent
import com.intuit.sendhil.gitexplorer.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import timber.log.Timber
import timber.log.Timber.DebugTree



class GitExplorerApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {


    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()
        component.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
}