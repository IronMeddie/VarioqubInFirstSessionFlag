package com.example.varioqubtest.varioqub

import com.yandex.varioqub.config.Varioqub

class GetRemoteConfigString(key: String, default: String, withCache: Boolean = true) :
    BaseGetRemoteConfigValue<String>(key, withCache, Varioqub::getString, default)