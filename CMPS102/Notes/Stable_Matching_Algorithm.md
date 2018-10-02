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

Here is the implementation in Python 3 code:

```python
tentative_engagements = []

free_men = []

def init_free_men():
	for man in preferred_rankings_men.keys():
		free_men.append(man)


def stable_matching():
	while (len(free_men) > 0):
		for man in free_men:
			begin_matching(man)

def begin_matching(man):
	print("Matching for this man: {}".format(man))
	for woman in preferred_rankings_men[man]:
		taken_match = [couple for couple in tentative_engagements if woman in couple]

		if (len(taken_match) == 0):
			tentative_engagements.append([man, woman])

			free_men.remove(man)
			print("{} is no longer a free man and now tentatively engaged to {}".format(man, woman))
			break

		elif (len(taken_match) > 0):
			print("Person {} is taken already...".format(woman))
			current_guy_ranking = preferred_rankings_women[woman].index(taken_match[0][0])
			potential_guy_ranking = preferred_rankings_women[woman].index(man)

			if (current_guy_ranking < potential_guy_ranking):
				print("She ({}) is satisfied with {}...".format(taken_match[0][1],man))
			else:
				print("{} is better than the old guy {}".format(man, taken_match[0][0]))
				print("{} is free agian".format(taken_match[0][0]))

				free_men.remove(man)
				free_men.append(taken_match[0][0])

				taken_match[0][0] = man
				break
 




def main():
	print("Hello World!")
	init_free_men()
	stable_matching()

	print("----COMPLETE----")
	for couple in tentative_engagements:
	# print(tentative_engagements)
		print("{} is engaged to {}".format(couple[1], couple[0]))
```

