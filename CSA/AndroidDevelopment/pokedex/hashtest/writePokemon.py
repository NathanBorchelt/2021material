with open("pokeNames.txt","r") as sourcePokemon:
    with open("pokeOut.txt","w") as destinationPokemion:
        allPokemon = sourcePokemon.readlines()
        for pokemon in range(len(allPokemon)):
            allPokemon[pokemon] = allPokemon[pokemon].replace("\n","")

            destinationPokemion.write("new Pokemon(\""+allPokemon[pokemon]+"\", (short) "+ str(pokemon) +"),\n")