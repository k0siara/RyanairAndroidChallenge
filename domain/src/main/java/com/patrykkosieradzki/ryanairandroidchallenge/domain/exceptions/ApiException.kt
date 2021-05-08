package com.patrykkosieradzki.ryanairandroidchallenge.domain.exceptions

sealed class ApiException(
    open val errorMessage: String
) : RuntimeException(errorMessage) {
    data class UnknownApiException(
        override val errorMessage: String
    ) : ApiException(
        errorMessage
    )

    data class NetworkError(override val errorMessage: String) : ApiException(errorMessage)
    data class OtherError(override val errorMessage: String) : ApiException(errorMessage)
}
