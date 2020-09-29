/*
 * Activity 3.6.3
 */
public class PetSimulator
{
  public static void main(String[] args)
  {
    Pet[] doubleCatDoubleDog = new Pet[10];
    doubleCatDoubleDog[0] = new Pet("Doggy 1",Pet.DOG);
    doubleCatDoubleDog[1] = new Pet("Doggy 2",Pet.DOG);
    doubleCatDoubleDog[2] = new Pet("Cat 1",Pet.CAT);
    doubleCatDoubleDog[3] = new Pet("Cat 2",Pet.CAT);

    for (Pet p : doubleCatDoubleDog){
        if(p!=null){
            p.feed();
        }
    }
    for (Pet p : doubleCatDoubleDog){
        if (p!=null){
            p.setOwner("Bander");
        }
    }
    for (Pet p : doubleCatDoubleDog){
      if (p!=null && !(p.getType()==Pet.CAT)){
        p.makeNoise();
        p.walk();
      }
    }
    for (Pet p : doubleCatDoubleDog){
      if (p!=null && (p.getType()==Pet.CAT)){
        p.makeNoise();
        p.walk();
      }
    }
    for (Pet p : doubleCatDoubleDog){
      if (p!=null){
        p.giveTreat();
      }
    }
    for (Pet p : doubleCatDoubleDog){
      if (p!=null && (p.getType()==Pet.CAT)){
        p.groom();
      }
    }
    for (Pet p : doubleCatDoubleDog){
      if (p!=null){
        p.play();
      }
    }

    for (Pet p : doubleCatDoubleDog){
      if (p!=null && (p.getType()==Pet.CAT)){
        p.sleep();
        p.feed();
      }
    }

    System.out.println("--- MY PETS ---");
    for (Pet p : doubleCatDoubleDog){
      if (p!=null){
        System.out.println(p);
      }
    }
    
    doubleCatDoubleDog[4] = new Pet("Balto",Pet.DOG);
    doubleCatDoubleDog[5] = new Pet("Air Bud",Pet.DOG);

    for(Pet p : doubleCatDoubleDog){
      if((p!=null) &&(p.getOwner()==null)){
        p.setOwner("The Pat Mayes");
      }
    }
    
    System.out.println("--- MY PETS ---");
    // show the state of all of your pets

  }
}