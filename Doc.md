
**The Design Pattern Farm Documentation for effective use**
```
This simulation is terminal command based. To do anything commands must be entered for processing.
This documentations conatins all supported commands and their conditions.
```

**Simulation Commands:**
```
f (farm)

	sell <amount> 
	buy <amount> 
	dogs <amount> (keeps some predators out, availible in level two) 
	gcover <amount> (short for ground cover, keeps weeds out, availible in level three) 
	status 
	switch <farm id> 
	new 
```
```
c (crops)  
	audit 
	plant <type> <amount> 
	harvest <type> 
	remove 
```
```
l (livestock) 
	audit 
	raise <type> <amount> 
	harvest <type> 
	collect <type> 
```

**General information**
```
amount: the amount in acres
farm id: can be found with f status
type: 
	Livestock : CHICKEN, CATTLE, HOG, SHEEP
	Crop : CORN, MUSHROOMS, SOYBEANS, WHEAT

Commands are not case sensitive.
```

**Example commands:**
```
f status
l harvest hog
f audit
c remove
c plant corn 40 (plants 40 acres of corn)
f new
```