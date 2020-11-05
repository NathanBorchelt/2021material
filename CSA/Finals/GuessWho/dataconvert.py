with open("SWData.txt", "w") as outFile:
    with open("StarWarsCharacters.csv","r") as file:
        fileLines = file.readlines()
        for line in fileLines:
            splitLine = line.split(",");
            for part in range(len(splitLine)):
                if(splitLine[part] == '' or splitLine[part] == '\n'):
                    splitLine[part] = 0
                elif(splitLine[part] == 'x' or splitLine[part] == 'x\n'):
                    splitLine[part] = 1
            outFile.write(str(splitLine))       
