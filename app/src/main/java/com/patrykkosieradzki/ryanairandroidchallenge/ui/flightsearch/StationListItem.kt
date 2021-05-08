package com.patrykkosieradzki.ryanairandroidchallenge.ui.flightsearch

enum class StationListItemType {
    HEADER, GROUP_ITEM
}

interface StationListItem {
    val name: String
    val type: StationListItemType
}

class StationHeaderItem(
    override val name: String
) : StationListItem {

    override val type = StationListItemType.HEADER
}

class StationGroupItem(
    override val name: String
) : StationListItem {

    override val type = StationListItemType.GROUP_ITEM
}
