package com.patrykkosieradzki.ryanairandroidchallenge.domain.exceptions

sealed class ApiException(
    errorMessage: String
) : RuntimeException(errorMessage) {
    data class UnknownApiException(
        val errorMessage: String
    ) : ApiException(
        errorMessage
    )

    data class NetworkError(val errorMessage: String) : ApiException(errorMessage)
    data class OtherError(val errorMessage: String) : ApiException(errorMessage)
}
