package br.com.up.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import br.com.up.pokedex.network.PokeApi

import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class PokemonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val pokemon = intent.getStringExtra("pokemon")

        val nameValue : TextView = findViewById(R.id.name_value)
        val movesListView : ListView = findViewById((R.id.moves_listview))
        val typesListView : ListView = findViewById((R.id.types_listview))
        val abilitiesListView : ListView = findViewById((R.id.abilities_listview))

        val hpValue : TextView = findViewById(R.id.hp_value)
        val defenseValue : TextView = findViewById(R.id.defense_value)
        val speedValue : TextView = findViewById(R.id.speed_value)
        val specialAtValue : TextView = findViewById(R.id.special_at_value)
        val specialDfValue : TextView = findViewById(R.id.special_df_value)
        val attackValue : TextView = findViewById(R.id.attack_value)

        val imageView : ImageView = findViewById(R.id.poke_image2)


        PokeApi().getPokemonByName(pokemon!!){
                pokemon ->
            if(pokemon != null){
                nameValue.text = pokemon.name;
                val id = pokemon.id;
                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
                Picasso.get().load(url).into(imageView)

                val listMoves = arrayOfNulls<String>(pokemon.moves.size)
                val listTypes = arrayOfNulls<String>(pokemon.types.size)
                val listAbilities = arrayOfNulls<String>(pokemon.abilities.size)

                for (i in 0 until pokemon.moves.size) {
                    val move = pokemon.moves[i]
                    listMoves[i] = move.move.name
                }

                for (i in 0 until pokemon.types.size) {
                    val type = pokemon.types[i]
                    listTypes[i] = type.type.name
                }

                for (i in 0 until pokemon.abilities.size) {
                    val ability = pokemon.abilities[i]
                    listAbilities[i] = ability.ability.name
                }

                for (i in 0 until pokemon.stats.size) {
                    val stat = pokemon.stats[i]
                    if(stat.stat.name == "hp") {
                        hpValue.text = stat.base_stat;
                    }
                    if(stat.stat.name == "attack") {
                        attackValue.text = stat.base_stat;
                    }
                    if(stat.stat.name == "defense") {
                        defenseValue.text = stat.base_stat;
                    }
                    if(stat.stat.name == "special-attack") {
                        specialAtValue.text = stat.base_stat;
                    }
                    if(stat.stat.name == "special-defense") {
                        specialDfValue.text = stat.base_stat;
                    }
                    if(stat.stat.name == "speed") {
                        speedValue.text = stat.base_stat;
                    }
                }

                val adapterMoves = ArrayAdapter(this, android.R.layout.simple_list_item_1, listMoves)
                val adapterTypes = ArrayAdapter(this, android.R.layout.simple_list_item_1, listTypes)
                val adapterAbilities = ArrayAdapter(this, android.R.layout.simple_list_item_1, listAbilities)

                movesListView.adapter = adapterMoves
                typesListView.adapter = adapterTypes
                abilitiesListView.adapter = adapterAbilities
            }
        }

    }
}