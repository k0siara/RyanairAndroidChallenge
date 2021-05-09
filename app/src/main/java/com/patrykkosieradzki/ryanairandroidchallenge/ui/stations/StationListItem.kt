package com.patrykkosieradzki.ryanairandroidchallenge.ui.stations

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

class StationGroupListItem(
    override val name: String,
    val code: String
) : StationListItem {
    override val type = StationListItemType.GROUP_ITEM
}
