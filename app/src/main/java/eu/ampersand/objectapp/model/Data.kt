package eu.ampersand.objectapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    @SerializedName("CPU model")
    val cpuModel: String?,
    @SerializedName("Capacity")
    val capacityC: String?,
    @SerializedName("Case Size")
    val caseSize: String,
    @SerializedName("Color")
    val colorC: String?,
    @SerializedName("Description")
    val descriptionD: String?,
    @SerializedName("Generation")
    val generationG: String?,
    @SerializedName("Hard disk size")
    val hardDiskSize: String?,
    @SerializedName("Price")
    val priceP: String?,
    @SerializedName("Screen size")
    val screenSize: Double?,
    @SerializedName("Strap color")
    val strapColour: String?,
    @SerializedName("capacity")
    val capacity: String?,
    @SerializedName("capacity GB")
    val capacityGB: Int?,
    @SerializedName("color")
    val color: String,
    @SerializedName("generation")
    val generation: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("year")
    val year: Int
): Parcelable