### There is always someone for everyone ###

##### What?!! Really? #####

##### Yes, it is mathematically proven #####

```
Gale-Shapley (1962)
Initialise each person to be free
    while(some man is free and hasn't proposed to every woman)
    {
        choose such man m
        w = 1st woman on m's list to whom m hasn't yet proposed
        if(w is available)
            assign m to w to be engaged
        else if(w prefers m to m')
            assign m and w to be engaged and m' to be free
        else
            w rejects m
    }
```

