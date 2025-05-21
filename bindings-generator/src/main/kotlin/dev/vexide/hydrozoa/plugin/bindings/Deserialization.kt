package dev.vexide.hydrozoa.plugin.bindings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SdkModule(
    val name: String,
    val items: List<SdkItem>,
    val enums: List<SdkEnum>,
)

@Serializable
data class SdkItem(
    val name: String,
    val params: List<Param>,
    val returns: Type?,
) {
    @Serializable
    data class Param(
        val name: String,
        val type: Type,
    )

    @Serializable
    sealed class Type {
        @Serializable
        @SerialName("Bool")
        object Bool : Type()

        @Serializable
        @SerialName("Int")
        object Int : Type()

        @Serializable
        @SerialName("Long")
        object Long : Type()

        @Serializable
        @SerialName("Float")
        object Float : Type()

        @Serializable
        @SerialName("Double")
        object Double : Type()

        @Serializable
        @SerialName("StringPtr")
        object StringPtr : Type()

        @Serializable
        @SerialName("Named")
        data class Named(val name: String) : Type()

        @Serializable
        @SerialName("Pointer")
        data class Pointer(val destination: Type) : Type()
    }
}

@Serializable
data class SdkEnum(
    val name: String,
    @SerialName("underlying_type")
    val underlyingType: SdkItem.Type,
    val variants: Map<String, Double>,
)