package com.example.varioqubtest

import android.app.Application
import android.util.Log
import com.example.varioqubtest.varioqub.Variohelper
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import com.yandex.varioqub.appmetricaadapter.AppMetricaAdapter
import com.yandex.varioqub.config.FetchError
import com.yandex.varioqub.config.OnFetchCompleteListener
import com.yandex.varioqub.config.Varioqub
import com.yandex.varioqub.config.VarioqubSettings

class AppTest : Application() {

    override fun onCreate() {
        super.onCreate()
        initAppMetrica()
        initVarioqub()
    }

    private fun initAppMetrica() {
        val config2 =
            YandexMetricaConfig.newConfigBuilder(Const.API_KEY)
                .withLogs()
                .build();
        YandexMetrica.activate(this, config2)
    }

    private fun initVarioqub() {
        val settings = VarioqubSettings.Builder(Const.VARIOQUB_APP_ID)
            .withLogs()
            .withThrottleInterval(2)
            .build()
        Varioqub.init(
            settings,
            AppMetricaAdapter(this),
            this
        )
        Varioqub.setDefaults(R.xml.ab_defaults)
    }
}