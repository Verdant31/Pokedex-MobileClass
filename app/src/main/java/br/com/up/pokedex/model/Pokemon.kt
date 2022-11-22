package br.com.up.pokedex.model

data class DeepAbility (
    val name: String,
    val url: String,
)

data class DeepMove (
    val name: String,
    val url: String,
    )


data class DeepType (
    val name: String,
    val url: String,
)

data class DeepStat (
    val name: String,
    val url : String
        )

data class Ability (
    val ability: DeepAbility,
)

data class Move (
    val move: DeepMove,
)

data class Type (
    val type : DeepType
    )


data class Stat (
    val stat : DeepStat,
    val base_stat : String
)
data class Pokemon(
    val url : String,
    val name : String,
    val id: String,
    val abilities :  List<Ability>,
    val moves : List<Move>,
    val types : List<Type>,
    val stats : List<Stat>
)
