package com.kzrnm.nanabunnnoomikuji.twitter

import twitter4j.Status

val Status.url
    get() = "https://twitter.com/${user.screenName}/status/${id}"
