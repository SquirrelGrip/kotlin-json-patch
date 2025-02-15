package com.reidsync.kxjsonpatch

class InvalidJsonPatchException : JsonPatchApplicationException {
    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}

    constructor(cause: Throwable) : super(cause) {}
}
