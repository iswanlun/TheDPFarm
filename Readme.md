**General Farm Simulator Layout**

```
Farms will exist inside of a world, which may contain multiple farms. Farms are made with 
a farm factory.

The enemy will be the bank wich will reside inside of the world as well, static bank.

Farms will have assets, crops and livestock.

Crops will come from the crops director, which construct new crops in the farm, adjust bank amounts,
populate the farm, etc.

Livestock will come from the live stock director, which constructs them from the animal interface, 
and adjusts the farm appropriatly.

Livestock, crops, and the bank will be observers to the cycles of the day and night.

Crops will be of the type plants, and will use the state pattern. Plants my be crops
or weeds of many types, and may be aflicted by disease or bugs, etc.

Livestock will be of type animals and will use the state pattern. Animals can be livestock or predators.
Animals can be sick, healthy, dead, alive, etc.

The farm will level up by gaining decorators. Each decorator will add features to the farm. Winning is getting all
the decorators, losing is going too far into debt.
```

**Implementations Specifications**

```
Observer Pattern:
    DayAndNightObserver.java
    DayAndNightObservable.java
    Implementation:
    DayNightManager.java
    Plant.java
    Animal.java

    Observer is deprectated since java 9, so custom created instead.

    Functionality requirments:
    1. The simulation should run on cycles. A cycle is considered to be of 2 parts - 1 daytime and 1 night time
    2. Animal product (milk, wool, and so on) is replenished after every 2nd day.  Forexample, if a sheep is sheared for 
    its wool then the farmers must wait 2 cycles (day and night) to collect the wool again.
    3. During night cycles predators come out. Predators could be foxes or wolves that may attack/eat the animals. Predators 
    could also be rabbits or some other animal that eats crops. Predators could also be moles that damage the soil.
```
```
Decorator Pattern:
    Farm.java
    FarmLevelOne.java
    FarmLavelTwo.java
    FarmLavelThree.java

    Functionality requirments:
    1. Farms are automatically upgraded once the farm has acquired enough currency.
    2. Difficulty increases with levels, tax rate changes, groundcover and dogs become availible reduce attacks by predators and weeds
    predators and weeds become more likely as levels increase.
    3. Crops are grown on farms and have a chance to become diseased, this changes as levels increase
```
```
Builder Pattern:
    AcreBuilder.java
    AcreDirector.java
    CropBuilder.java
    LivestockBuilder.java

    Functionality requirements:
    1. Animals have a natural life cycle just like in real life.
    2. Farms can be of different types, such as an animal farm, a crop farm, a hybrid farmand so on. You can choose to make something up too.
    3. Animals (and plants) reside on farms; it is up to you to decide the total number of animals thatyour farm(s) will hold. Think of 
    typical farm animals such a cows and pigs, but you can be creative if you wish
```