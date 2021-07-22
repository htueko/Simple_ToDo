package com.htueko.simpletodo.common.domain.model.exception

import java.io.IOException

class NetworkUnavailableException(message: String = "Network Unavailable") :
    IOException(message)

