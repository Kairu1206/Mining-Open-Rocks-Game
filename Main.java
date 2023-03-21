//FIX: CHAR EXP DOES NOT UPDATE
import java.util.*;
class Main {
  static Rock rock = new Rock();
  public static void main(String[] args) 
  {
    Scanner input = new Scanner(System.in);
    int choice = 0;
    clear();
    intro();
    System.out.println("Now, let's prepare for you to play the game!");
    enter();
    System.out.println("First, enter your name!");
    System.out.println();
    System.out.print("Name: ");
    String uname = input.nextLine();
    while (true)
    {
      clear();
      System.out.println("Now, pick your starting money!");
      System.out.println();
      System.out.println("Easy - $1000 [1]\nNormal - $100 [2]\nHardcore - $0 [3]");
      System.out.println();
      System.out.print("Starting money: ");
      int uchoice = input.nextInt();
      if (uchoice == 1)
      {
        choice = 1000;
        break;
      }
      else if (uchoice == 2)
      {
        choice = 100;
        break;
      }
      else if (uchoice == 3)
      {
        choice = 0;
        break;
      }
      else
      {
        System.out.println("Invalid input!");
        enter();
      }
      
    }
    Character user = new Character(uname, choice);
    int count1 = 0; //num acheivement
    int count2 = 0; //score
    int count3 = 0; //energy restore
    boolean lend = true;
    clear();
    System.out.println("Now, you are ready to play the game!");
    enter();
    clear();
    System.out.println("Let's look at your chance and money exchange board!");
    enter();
    System.out.println("Your chance board and money exchange board");
    chance();
    System.out.println("Last thing before you start the game!");
    System.out.println();
    System.out.println("Let's take a look at your control!");
    enter();
    key();
    System.out.println("That's it!\nGLHF!!!");
    enter();
    newsupdate();
    enter();
    
    while(user.getMoney() >= 0 && lend && user.getMoney() < 1000000)
    {
      clear();
      System.out.print("Coin: " + user.getMoney());
      System.out.print("     ");
      System.out.print("Points: " + user.getScore());
      System.out.print("     ");
      System.out.print("Number of rocks have bought: " + user.getNumrock());
      System.out.print("     ");
      System.out.println("Number of achievement: " + user.getNumachieve());
      System.out.print("Level: " + user.getLevel() + " ");
      expart(user.getExp(), user.getExpLimit(), user.getLevel());
      System.out.print("          ");
      System.out.print("Energy ");
      energyart(user.getEnergy() / 10);
      if(user.getEnergy() != user.getEnergyLimit())
      {
        System.out.print(" " + count3 + "/3 +[]");
      }
      System.out.println();
      System.out.println("Bag: " + user.getNumItem() + "/" + user.getBagLimit());
      System.out.println();
      System.out.println("Welcome " + uname + " to the Shop!");
      System.out.println();
      shopart();
      if (user.getMoney() >= rock.getLUC())
      {
        System.out.println();
        System.out.println("------------------------");
        System.out.println("|  Upgrade avaliable   |");
        System.out.println("------------------------");
      }
      System.out.println();
      System.out.println("Upgrade cost: " + rock.getLUC() + "   (Next level upgrade cost: " + (1000*(rock.getLv() + 1)) + ")");
      System.out.println("Rock cost: " + rock.getBuy() + "   (Next level buy cost: " + (100*(rock.getLv() + 1)) + ")");
      System.out.println("Sell unopened rock for: " + rock.rockSell());
      System.out.println();
      System.out.println("Do you want to [U]pgrade or [B]uy Rock or see your [C]hance board or see the control [K]ey or [S]ell Rock or go [M]ining or [O]pen bag?");
      String ans = input.nextLine();
      clear();
      if (ans.toLowerCase().equals("u"))
      {
        clear();

          if (user.getMoney() >= rock.getLUC())
          {
          System.out.println("You spend " + rock.getLUC() + " coin for upgrade the rock.");
          enter();
          user.moneyUpdate(user.getMoney() - rock.getLUC());
          user.upgradescore(rock.getLv());
          count2 += 100*rock.getLv();
          System.out.println("Congratulation! Succesfully increased rock level from " + rock.getLv() + " to " + (rock.getLv() + 1));
          rock.levelUp();
          System.out.println("Here's your new chance board!");
          chance();
          if (rock.getLv()%5 == 0)
          {
            System.out.println("Congratulation! You reach level " + rock.getLv());
            System.out.println();
            System.out.println("Here's some gift for you!");
            System.out.println(" +" + (rock.getLv()*1000) + " coin");
            user.moneyUpdate(user.getMoney() + (rock.getLv()*1000));
            enter();
          }
          continue;
        }
          else
          {
          System.out.println("Oops! Not enough money for upgrade!\nYou need " + (rock.getLUC() - user.getMoney()) + " more coin to upgrade!\nKeep it up!!!");
          enter();
          continue;
        }
      }
      else if (ans.toLowerCase().equals("b"))
      {
        clear();
        System.out.println("You spend " + rock.getBuy() + " coin for the rock.");
        enter();
        user.moneyUpdate(user.getMoney() - rock.getBuy());

        rockart();
        System.out.println("You get " + rock.getGem() + " from the rock!");
        System.out.println("It's worth " + rock.getMoney() + " coin.");
        enter();
        user.moneyUpdate(user.getMoney() + rock.getMoney());
        user.update(rock.getScore());
        count2 += rock.getScore();
        user.updateExp();
//ACHIEVEMENT
        if (count2 >= 1000 && user.getScore() != 0)
        {
          System.out.println("Congratulation! Your score has reached " + user.getScore() + ".");
          System.out.println();
          System.out.println("Here's something for you!");
          System.out.println("+" + (user.getScore()) + " coin");
          user.moneyUpdate(user.getMoney() + (user.getScore()));
          user.achievementUpdate();
          count1++;
          count2 = 0;
          enter();
        }
        if (user.getNumrock()%10 == 0 && user.getNumrock() != 0)
        {
          System.out.println("Congratulation! You have bought " + user.getNumrock() + " rocks in total.");
          System.out.println();
          System.out.println("Here's something for you!");
          System.out.println("+" + rock.getBuy() + " coin");
          user.moneyUpdate(user.getMoney() + rock.getBuy());
          user.achievementUpdate();
          count1++;
          enter();
        }
        if (count1 == 10 && user.getNumachieve() != 0)
        {
          System.out.println("Congratulation! You have earned " + user.getNumachieve() + " achievements.");
          System.out.println();
          System.out.println("Here's something for you!");
          System.out.println("+" + (user.getNumachieve() * 1000) + " coin");
          user.moneyUpdate(user.getMoney() + (user.getNumachieve() * 1000));
          count1 = 0;
          enter();
        }
//ENERGY COUNT
        if (user.getEnergy() != user.getEnergyLimit())
        {
          count3++;
          if(count3 == 3)
          {
            user.energyUp();
            count3 = 0;
          }
        }
/*                          -------------- FIX THIS ONE -------------------
//EXP COUNT
        if(user.getExp() == user.getExpLimit())
        {
          user.incLv();
        }
*/        
      }
      else if (ans.toLowerCase().equals("c"))
      {
        clear();
        System.out.println("Your chance board and money exchange board");
        chance();
      }
      else if (ans.toLowerCase().equals("k"))
      {
        clear();
        key();
      }

      else if (ans.toLowerCase().equals("s"))
      {
        System.out.println("Do you want to sell [A]ll items or [O]ne specific item?");
        String sellinput = input.nextLine();
        clear();
        int receive = 0;
        if(sellinput.toLowerCase().equals("a"))
        {
          for(int i = 0; i < user.getNumItem(); i++)
          {
            if(user.getItem(i).toLowerCase().equals("rock"))
            {
              receive += user.getMoney() + rock.rockSell();
            }
            else
            {
              receive += user.getMoney() + rock.moneyExchange(user.getItem(i));
            }
            user.updateExp();
          }
          user.moneyUpdate(receive);
          user.emptyBag();
          enter();
        }
        else
        {
          System.out.println("Which item do you want to sell? (enter the number in front of the item)");
          System.out.println();
          user.showBag();
          System.out.println();
          System.out.print("Enter here: ");
          int itemindex = input.nextInt();
          clear();
          input.nextLine();
          if(user.getItem(itemindex - 1).toLowerCase().equals("rock"))
          {
            receive += user.getMoney() + rock.rockSell();
            user.moneyUpdate(receive);
          }
          else
          {
            receive += user.getMoney() + rock.moneyExchange(user.getItem(itemindex - 1));
            user.moneyUpdate(receive);
          }
          user.updateExp();
          user.emptyBag(itemindex - 1);
          System.out.println("+" + receive + " coin!");
          receive = 0;
          enter();
        }
        
      }

      else if (ans.toLowerCase().equals("m"))
      {
        clear();
        System.out.println("You go to the mine!");
        enter();
        if(user.getEnergy() > 0)
        {
          mineart();
          user.energyDown();
        }
        else
        {
          System.out.println("Not enough energy!");
          enter();
          continue;
        }
        System.out.println("You find a rock!");
        enter();


        while(true)
        {
          System.out.println("Do you want to open the rock? (Y or N)");
          String uin = input.nextLine();
          clear();
          if(uin.toLowerCase().equals("y"))
          {
            rockart();
            System.out.println("You get " + rock.getGem() + " from the rock!");
            enter();
            if(rock.puregem().toLowerCase().equals("nothing"))
            {
              System.out.println("There is nothing in the rock!");
              System.out.println("Item will not added into bag!");
            }
            else
            {
              System.out.println("Item added into bag!");
              user.addItem(rock.puregem());
            }
            enter();
            break;
          }
          else if (uin.toLowerCase().equals("n"))
          {
            System.out.println("Item added into bag!");
            user.addItem("Rock");
            enter();
            break;
          }
          else
          {
            System.out.println("Enter \"y\" or \"n\"!");
            enter();
          }
        }       
      }

      else if (ans.toLowerCase().equals("o"))
      {
        clear();
        user.showBag();
        enter();
      }
      if (user.getMoney() < 0 && user.getMoney() - rock.getBuy() < 0)
      {
        lend = false;
      }
      else
      {
        lend = true;
      }
    }

    clear();
    if(user.getMoney() > 1000000)
    {
      System.out.println("Congratulation!");
      System.out.println("You have become a millionare!");
      System.out.println("You don't have to go mine rock anymore!");
      System.out.println();
    }
    else
    {
      System.out.println("RIP!");
      System.out.println("You broke!");
      System.out.println();
    }
    System.out.println("~~~Summary of your game play~~~");
    System.out.println();
    System.out.println("Character level: " + user.getLevel());
    System.out.println("Points: " + user.getScore());
    System.out.println("Number of rocks have bought: " + user.getNumrock());
    System.out.println("Number of achievement: " + user.getNumachieve());

  }
  public static void intro()
  {
    System.out.println("WELCOME TO THE GAME!!!");
    System.out.println();
    System.out.println("Lets's start with some introduction about the game!");
    enter();
    System.out.println("Easy game! Open rock to get gems, if lucky, and exchange for money and get score.");
    enter();
    System.out.println("The higher the quality of the rock the better chance of getting gems.");
    enter();
    System.out.println("The higher the quality of the gems the better the score.");
    enter();
    System.out.println("You can only continue to lend money if you can pay back!");
    enter();
    System.out.println("You can go mining to get more income but it will cost energy!");
    enter();
    System.out.println("Your energy will recover over time!");
    enter();
    System.out.println("The higher your level the higher your energy!");
    enter();
    System.out.println("You can store your mined rocks in your bag but spaces are limited!");
    enter();
    System.out.println("You can choose to either open rocks after mined or sell it unopened!");
    enter();
    System.out.println("Your score will not be effective by mining!");
    enter();
    System.out.println("You win the game when reaching 1 million coin!");
    enter();
    System.out.println("That's all for the introduction!");
    enter();

  }

  public static void newsupdate()
  {
    clear();
    System.out.println("[NEW UPDATE]");
    System.out.println();
    System.out.println("The Mine has opened!");
    System.out.println(" - You can now go mining to earn more income through selling mined rocks!");
    System.out.println(" - You will need to spend energy to go and mine!");
    System.out.println(" - The energy will increase as your level increase!");
    System.out.println(" - Whenever you bought 3 rock, your energy will recover back 1.");
    System.out.println();
    System.out.println("Bag opened!");
    System.out.println(" - You can store mined rocks in your bag!");
    System.out.println(" - The bag space will increase as your level increase!");
    System.out.println(" - If you exceed your bag limit, your item will be deleted!");
    System.out.println();
    System.out.println("Character level opened!");
    System.out.println(" - You can now level up your character!");
    System.out.println(" - The higher the level the higher the energy!");
    System.out.println(" - The higher the level the more bag spaces you have!");
    System.out.println(" - You can gain level through buying or selling rocks! (1 exp = 1 transaction)");
    System.out.println();
    System.out.println("Money Limited!");
    System.out.println(" - When the player get to 1 million coin, the player win the game!");
    System.out.println(" - Why? Because JAVA broke after reaching 4 million coin! Q_Q so sorry");
  }

  public static void chance()
  {
    System.out.println();
    for (int i = 0; i < rock.name.length; i++)
    {
        System.out.println(rock.name[i] + " (" + (((int)((rock.chances[i]/rock.apc()*10000.0)))/100.0) + "%) - " + rock.coin[i] + " coins (" + rock.rockscore[i] + " pts)");
        
    }
    enter();
  }

  public static void key()
  {
    System.out.println("Here's your control key");
    System.out.println();
    System.out.println("U - upgrade rocks\nB - buy rocks\nC - look at your rock chance and money exchange board\nK - show the control key\nS - sell rocks\nM - go mining\nO - open bag");
    enter();
  }

  public static void enter()
  {
    Scanner input = new Scanner(System.in);
    System.out.println();
    System.out.println("Press \"Enter\"!");
    input.nextLine();
    clear();
  }

  public static void clear()
  {
    System.out.println("\033[H\033[J");
    System.out.flush();
  }

  public static void rockart()
  {
    System.out.println("   /\\");
    System.out.println("  /  \\");
    System.out.println(" /    \\");
    System.out.println(" \\    /");
    System.out.println("  \\  /");
    System.out.println("   \\/");
    enter();
    System.out.println("   /\\");
    System.out.println("  // \\");
    System.out.println(" /    \\");
    System.out.println(" \\    /");
    System.out.println("  \\  /");
    System.out.println("   \\/");
    enter();
    System.out.println("   /\\");
    System.out.println("  // \\");
    System.out.println(" / \\  \\");
    System.out.println(" \\    /");
    System.out.println("  \\  /");
    System.out.println("   \\/");
    enter();
    System.out.println("   /\\");
    System.out.println("  // \\");
    System.out.println(" / \\  \\");
    System.out.println(" \\  / /");
    System.out.println("  \\  /");
    System.out.println("   \\/");
    enter();
    System.out.println("   /\\");
    System.out.println("  // \\");
    System.out.println(" / \\  \\");
    System.out.println(" \\  / /");
    System.out.println("  \\ \\/");
    System.out.println("   \\/");
    enter();
  }

  public static void shopart()
  {
    System.out.println("     _________________________");
    System.out.println("   /         SELL & BUY        \\");
    System.out.println("  /             Rocks           \\");
    System.out.println(" /_______________________________\\");
    System.out.println("    |            ^___^         |");
    System.out.println("    |          = O   O =       |");
    System.out.println("    |           /     \\        |");
    System.out.println(" ___|__________|_______|_______|___");
    System.out.println("|                                 |");
    System.out.println("|                                 |");
  }

  public static void expart(int exp, int explimit, int level)
  {
    if(exp/explimit*100 >= 10)
    {
      System.out.print("[" + ((int)(((double)exp /explimit * 10))/10) + "% to level " + level + "]");
    }
    else
    {
      System.out.print("[0" + ((int)(((double)exp /explimit * 10))/10) + "% to level " + level + "]");
    }
  }

  public static void energyart(int num)
  {
    for(int i = 0; i < num; i++)
    {
      System.out.print("[]");
    }
  }

  public static void mineart()
  {
    System.out.println("  ________");
    System.out.println(" /        \\_______");
    System.out.println("/__________________\\");
    enter();
    System.out.println("  ______");
    System.out.println(" /      \\____");
    System.out.println("/____________\\");
    enter();
    System.out.println();
    System.out.println(" ____________");
    System.out.println("/____________\\");
    enter();
  }
}


  
