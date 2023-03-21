public class Rock
{
  private int buy;
  private int lv;
  private String gem;
  private int lvupcoin;
  private int money;
  private int score;
  public String[] name = {"Nothing", "Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby", "Amethyst", "Emerald", "Sapphire", "Opal"};
  /* Nothing - 20.3%
   Iron - 17.7%
   Bronze - 15.2% 
   Silver - 12.2%
   ------------------
   Gold - 10.3%
   Platinum - 7.2%
   Diamond - 5.6%
   Ruby - 4.2%
   ------------------
   Amethyst - 3.3%
   Emerald - 2.5%
   Sapphire - 1.4%
   Opal - 0.1% 
*/
  public double[] chances = {203, 177, 152, 122, 103, 72, 56, 42, 33, 25, 14, 1};
  public double[] multipliers = {1,2,3,4,5,6,7,8,9,10,11,12};
  public int[] coin = {0, 10, 20, 50, 70, 100, 500, 700, 1000, 3000, 5000, 10000};
  public int[] rockscore = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200};

  public Rock()
  {
    lv = 1;
    lvupcoin = 1000;
    buy = 100;
    gem = "";
    money = 0;
    score = 0;
  }
//NEW UPDATE: SELLING ROCK YOU MINE
  public int getSell()
  {
    return moneyExchange() * 2;
  }

  public int rockSell()
  {
    return buy * 2;
  }
// -----------------------------------------
  public int getBuy()
  {
    return buy;
  }

  public String ranGem()
  {
    int total = 0;
    int rannum = (int)(Math.random() * apc() + 1);
    for (int i = 0; i < name.length; i++)
    {
      total += chances[i];
      if (rannum <= (total))
      {
        return name[i];
      }
    }
    return "Error";
  }

  public String getGem()
  {
    gem = ranGem();
    return gem;
  }

  public String puregem()
  {
    return gem;
  }

  public int moneyExchange()
  {
    for (int i = 0; i < name.length; i++)
    {
      if (gem == name[i])
      {
        return coin[i];
      }
    }
    return -1;
  }

  public int moneyExchange(String item)
  {
    for (int i = 0; i < name.length; i++)
    {
      if (item == name[i])
      {
        return coin[i];
      }
    }
    return -1;
  }

  public int scoreExchange()
  {
    for (int i = 0; i < name.length; i++)
    {
      if (gem == name[i])
      {
        return rockscore[i];
      }
    }
    return -1;
  }

  public int getScore()
  {
    score = scoreExchange();
    return score;
  }

  public int getMoney()
  {
    money = moneyExchange();
    return money;
  }

  public int getLUC()
  {
    return lvupcoin;
  }

  public int getLv()
  {
    return lv;
  }

  public void levelUp()
  {
    lv++;
    lvupcoin = 1000*lv;
    buy = 100*lv;
    for (int i = 0; i < name.length; i++)
    {
      chances[i] += multipliers[i];
    }
    if (lv%5 == 0)
    {
      for (int i = 1; i < name.length; i++)
      {
        coin[i] *= 1.5;
      }
    }
  }

  public int apc()
  {
    int total = 0;
    for (int i = 0; i < name.length; i++)
    {
      total += chances[i];
    }
    return total;
  }
}
