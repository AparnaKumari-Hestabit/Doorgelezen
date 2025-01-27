package doorgelezen.models

enum class FulfilmentMethod(val acronym: String) {
    BOL("FBB"),
    RETAILER("FBR");

    companion object {
        private val acronymMap = values().associateBy(FulfilmentMethod::acronym)
        fun fromAcronym(acronym: String) = acronymMap[acronym]
    }
}