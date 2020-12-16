public class TopOfChristmas2020{
    public static void main(String[] args) {
        String[] allToys = {"Blume Doll",
            "Linkimals Smooth Moves Sloth",
            "Cubby the Curious Bear",
            "Oranguetwang Kids Game",
            "Artie 3000 The Coding Robot",
            "The Ultimate Walking Buzz Lightyear",
            "Lucky Fortune Bracelets",
            "Candy Locks Doll",
            "Code 'n Learn Kinderbot",
            "Pictionary Air",
            "Off the Hook Style Studio",
            "Hatching Toothless",
            "Treasure X Aliens",
            "Botzees",
            "Fingerlings Light Up Narwhal"};

        System.out.println(getIndexOf(allToys,"Blume Doll"));
    }
    public static int getIndexOf(String[] allItems, String name){
        for(int i=0; i<allItems.length; i++)
            if(allItems[i].containsIgnoreCase(name)){
                return i;
            }
            return -1;
        }
}