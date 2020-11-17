General Farm Simulator Layout

Farms will exist inside of a world, which may contain multiple farms. Farms are made with 
a farm factory.

Farmers are just farmers.

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