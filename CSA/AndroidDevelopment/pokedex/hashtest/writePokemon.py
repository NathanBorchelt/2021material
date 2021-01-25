try:
    with open("pokeNames.txt","r") as sourcePokemon:
        with open("pokeOut.txt","w") as destinationPokemion
            allPokemon = sourcePokemon.readlines()
            allPokemonList = allPokemon.split("\n")

            for pokemon in range(len(allPokemonList)):
                destinationPokemion.write("new Pokemon(\""+allPokemonList[pokemon]+"\", (short) "+ pokemon +"),\n")

except:
    print("No source file with the name \"pokeNames.txt\"")