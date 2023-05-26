package com.nikolai.ihavepaws.model.base

import com.nikolai.ihavepaws.model.MessageCallback

interface BaseReducer {
    var messagesCallback: MessageCallback?
}