package com.luiz.pokeapp.data.remote.external.model
import com.google.gson.annotations.SerializedName
data class PokemonResponse(
    val abilities: List<Ability>,
    val base_experience: Int,
    val cries: Cries,
    val forms: List<Form>,
    val game_indices: List<Index>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Mfe>,
    val name: String,
    val order: Int,
    val past_abilities: List<Any>,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int,
    val generationI: GenerationI,
    val generationIi: GenerationIi,
    val generationIii: GenerationIii,
    val generationIv: GenerationIv,
    val generationV: GenerationV,
    val generationVi: GenerationVi,
    val generationVii: GenerationVii,
    val generationViii: GenerationViii
) {

    data class Ability(
        val ability: Ability2,
        val is_hidden: Boolean,
        val slot: Int
    )

    data class Ability2(
        val name: String,
        val url: String
    )

    data class Cries(
        val latest: String,
        val legacy: String
    )

    data class Form(
        val name: String,
        val url: String
    )

    data class Index(
        val game_index: Int,
        val version: Version
    )

    data class Version(
        val name: String,
        val url: String
    )

    data class Mfe(
        val move: Move,
        val version_group_details: List<VersionGroupDetail>
    )

    data class Move(
        val name: String,
        val url: String
    )

    data class VersionGroupDetail(
        val level_learned_at: Int,
        val move_learn_method: MoveLearnMethod,
        val version_group: VersionGroup
    )

    data class MoveLearnMethod(
        val name: String,
        val url: String
    )

    data class VersionGroup(
        val name: String,
        val url: String
    )

    data class Species(
        val name: String,
        val url: String
    )

    data class Sprites(
        val back_default: String?,
        val back_female: Any?,
        val back_shiny: String?,
        val back_shiny_female: Any?,
        val front_default: String?,
        val front_female: Any?,
        val front_shiny: String?,
        val front_shiny_female: Any?,
        val other: Other,
        val versions: Versions
    )

    data class Other(
        val dream_world: DreamWorld,
        val home: Home,
        @SerializedName("official-artwork") val officialArtwork: OfficialArtwork,
        val showdown: Showdown
    )

    data class DreamWorld(
        val front_default: String,
        val front_female: Any?
    )

    data class Home(
        val front_default: String,
        val front_female: Any?,
        val front_shiny: String,
        val front_shiny_female: Any?
    )

    data class OfficialArtwork(
        val front_default: String,
        val front_shiny: String
    )

    data class Showdown(
        val back_default: String,
        val back_female: Any?,
        val back_shiny: String,
        val back_shiny_female: Any?,
        val front_default: String,
        val front_female: Any?,
        val front_shiny: String,
        val front_shiny_female: Any?
    )

    data class Versions(
        @SerializedName("generation-i") val generationI: GenerationI,
        @SerializedName("generation-ii") val generationIi: GenerationIi,
        @SerializedName("generation-iii") val generationIii: GenerationIii,
        @SerializedName("generation-iv") val generationIv: GenerationIv,
        @SerializedName("generation-v") val generationV: GenerationV,
        @SerializedName("generation-vi") val generationVi: GenerationVi,
        @SerializedName("generation-vii") val generationVii: GenerationVii,
        @SerializedName("generation-viii") val generationViii: GenerationViii
    )

    data class GenerationI(
        @SerializedName("red-blue") val redBlue: RedBlue,
        val yellow: Yellow
    )

    data class RedBlue(
        val back_default: Any?,
        val back_gray: Any?,
        val back_transparent: Any?,
        val front_default: Any?,
        val front_gray: Any?,
        val front_transparent: Any?
    )

    data class Yellow(
        val back_default: Any?,
        val back_gray: Any?,
        val back_transparent: Any?,
        val front_default: Any?,
        val front_gray: Any?,
        val front_transparent: Any?
    )

    data class GenerationIi(
        val crystal: Crystal,
        val gold: Gold,
        val silver: Silver
    )

    data class Crystal(
        val back_default: Any?,
        val back_shiny: Any?,
        val back_shiny_transparent: Any?,
        val back_transparent: Any?,
        val front_default: Any?,
        val front_shiny: Any?,
        val front_shiny_transparent: Any?,
        val front_transparent: Any?
    )

    data class Gold(
        val back_default: Any?,
        val back_shiny: Any?,
        val front_default: Any?,
        val front_shiny: Any?,
        val front_transparent: Any?
    )

    data class Silver(
        val back_default: Any?,
        val back_shiny: Any?,
        val front_default: Any?,
        val front_shiny: Any?,
        val front_transparent: Any?
    )

    data class GenerationIii(
        val emerald: Emerald,
        @SerializedName("firered-leaf-green") val fireredLeafgreen: FireredLeafgreen,
        @SerializedName("ruby-sapphire") val rubySapphire: RubySapphire
    )

    data class Emerald(
        val front_default: String,
        val front_shiny: String
    )

    data class FireredLeafgreen(
        val back_default: Any?,
        val back_shiny: Any?,
        val front_default: Any?,
        val front_shiny: Any?
    )

    data class RubySapphire(
        val back_default: String,
        val back_shiny: String,
        val front_default: String,
        val front_shiny: String
    )


    data class DiamondPearl(
        val back_default: String?,
        val back_shiny: String?,
        val front_default: String?,
        val front_shiny: String?
    )

    data class HeartgoldSoulsilver(
        val back_default: String?,
        val back_shiny: String?,
        val front_default: String?,
        val front_shiny: String?
    )

    data class Platinum(
        val back_default: String?,
        val back_shiny: String?,
        val front_default: String?,
        val front_shiny: String?
    )

    data class Stat(
        val base_stat: Int,
        val effort: Int,
        val stat: StatDetail
    )

    data class StatDetail(
        val name: String,
        val url: String
    )

    data class Type(
        val slot: Int,
        val type: TypeDetail
    )

    data class TypeDetail(
        val name: String,
        val url: String
    )

    data class GenerationIv(
        @SerializedName("diamond-pearl") val diamondPearl: DiamondPearl,
        @SerializedName("heart-gold-soul-silver") val heartgoldSoulsilver: HeartgoldSoulsilver,
        val platinum: Platinum
    )

    data class GenerationV(
        @SerializedName("black-white") val blackWhite: BlackWhite
    )

    data class BlackWhite(
        val animated: Animated,
        val backDefault: String,
        val backFemale: Any?,
        val backShiny: String,
        val backShinyFemale: Any?,
        val frontDefault: String,
        val frontFemale: Any?,
        val frontShiny: String,
        val frontShinyFemale: Any?
    )

    data class GenerationVi(
        @SerializedName("omega-ruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire,
        @SerializedName("x-y") val xY: XY
    )

    data class OmegarubyAlphasapphire(
        val frontDefault: String,
        val frontFemale: Any?,
        val frontShiny: String,
        val frontShinyFemale: Any?
    )

    data class XY(
        val frontDefault: String,
        val frontFemale: Any?,
        val frontShiny: String,
        val frontShinyFemale: Any?
    )

    data class GenerationVii(
        val icons: Icons,
        @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon
    )

    data class UltraSunUltraMoon(
        val frontDefault: String,
        val frontFemale: Any?,
        val frontShiny: String,
        val frontShinyFemale: Any?
    )

    data class GenerationViii(
        val icons: Icons2
    )

    data class Icons(
        val frontDefault: String,
        val frontFemale: Any?
    )

    data class Icons2(
        val frontDefault: String,
        val frontFemale: Any?
    )

    data class Animated(
        val backDefault: String,
        val backFemale: Any?,
        val backShiny: String,
        val backShinyFemale: Any?,
        val frontDefault: String,
        val frontFemale: Any?,
        val frontShiny: String,
        val frontShinyFemale: Any?
    )
}