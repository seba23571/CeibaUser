package com.supraweb.ceibauser

import android.app.Application
import com.supraweb.ceibauser.data.db.AppDataBase
import com.supraweb.ceibauser.data.network.MyApi
import com.supraweb.ceibauser.data.network.NetworkConnectionInterceptor
import com.supraweb.ceibauser.data.repositories.UserRepository
import com.supraweb.ceibauser.ui.publish.PublishViewModelFactory
import com.supraweb.ceibauser.ui.user.UserViewModelFactory
import com.supraweb.data.preferences.PreferencesProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication  : Application() ,  KodeinAware{

    override val kodein = Kodein.lazy {
         import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetworkConnectionInterceptor(    instance()     ) }
        bind() from singleton { MyApi(instance() ) }
        bind()  from singleton { AppDataBase(instance()) }

        bind()  from singleton { PreferencesProvider(instance()) }


        bind() from singleton {   UserRepository( instance()    ,instance()    ,instance()      )        }
//    org.kodein.di.Kodein$NotFoundException: No binding found for bind<PublishViewModelFactory>() with ?<PublishFragment>().? { ? }



        bind() from provider {   UserViewModelFactory(   instance()          )            }

        bind() from provider {   PublishViewModelFactory(   instance()          )            }









    }

}