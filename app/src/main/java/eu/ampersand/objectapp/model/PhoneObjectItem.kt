package eu.ampersand.objectapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneObjectItem(
    val `data`: Data?,
    val id: String?,
    val name: String?
) : Parcelable {
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhoneObjectItem

        if (`data` != other.`data`) return false
        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }
}