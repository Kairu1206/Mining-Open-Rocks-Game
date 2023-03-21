import java.util.*;
public class Character
{
  private String name;
  private int money;
  private int numachieve;
  private int score;
  private int numrock;
  private int energy;
  private int energylimit;
  private ArrayList<String> bag;
  private int baglimit;
  private int level;
  private int exp;
  private int explimit;

  public Character(String tname, int tmoney)
  {
    name = tname;
    money = tmoney;
    numachieve = 0;
    score = 0;
    numrock = 0;
    energylimit = 100;
    energy = energylimit;
    bag = new ArrayList<String>();
    level = 1;
    baglimit = 10;
    exp = 0;
    explimit = 10;
  }

  public void moneyUpdate(int newmoney)
  {
    money = newmoney;
  }

  public int getMoney()
  {
    return money;
  }

  public int getScore()
  {
    return score;
  }

  public int getNumrock()
  {
    return numrock;
  }
//SCORE
  public void upgradescore(int lv)
  {
    score += 100 * lv;
  }

  public void update(int newscore)
  {
    score += newscore;
    numrock++;
  }
//ACHIEVEMENT
  public void achievementUpdate()
  {
    numachieve++;
  }

  public int getNumachieve()
  {
    return numachieve;
  }
//ENERGY
  public void energyDown()
  {
    energy -= 10;
  }

  public void energyUp()
  {
    energy += 10;
  }

  public int getEnergy()
  {
    return energy;
  }

  public int getEnergyLimit()
  {
    return energylimit;
  }
  
  public void upgradeEnergy()
  {
    energylimit = 100 * level;
    energy = energylimit;
  }
//BAG
  public void increaseBagSize()
  {
    baglimit = level * 10;
  }

  public void addItem(String rock)
  {
    if(bag.size() >= baglimit)
    {
      System.out.println("Bag Full! Item will be deleted!");
    }
    else
    {
      System.out.println(rock + " has been added to bag!");
      bag.add(rock);
    }
  }

  public int getBagLimit()
  {
    return baglimit;
  }

  public int getNumItem()
  {
    return bag.size();
  }

  public void showBag()
  {
    System.out.println("Bag:");
    System.out.println();
    int num = 1;
    for(String item : bag)
    {
      System.out.println(num + ". " + item);
      num++;
    }
  }
  
  public String getItem(int index)
  {
    return bag.get(index);
  }

  public void emptyBag(int index)
  {
    bag.remove(index);
    System.out.println("Sell successful!");
  }

  public void emptyBag()
  {
    while(bag.size() > 0)
    {
      bag.remove(0);
    }
    System.out.println("Sell successful!");
  }
//LEVEL
  public void incLv()
  {
    exp = 0; 
    level++;
    explimit = level * 10;
    upgradeEnergy();
    increaseBagSize();
  }

  public int getLevel()
  {
    return level;
  }

  public int getExp()
  {
    return exp;
  }

  public void updateExp()
  {
    exp++;
  }

  public int getExpLimit()
  {
    return explimit;
  }
}