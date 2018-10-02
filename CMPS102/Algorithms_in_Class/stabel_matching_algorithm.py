"""
Tribute to https://www.youtube.com/watch?v=FhRf0j068ZA
and Pride and Prejudice by Jane Austen
"""

preferred_rankings_men = {
	'Binley':  ['Jane', 'Elizabeth', 'Lydia', 'Charlotte'],
	'Darcy':   ['Elizabeth', 'Jane', 'Charlotte', "Lydia"],
	'Wickham': ['Lydia', 'Jane', 'Elizabeth', 'Charlotte'],
	'Collins': ['Jane', 'Elizabeth', 'Lydia', 'Charlotte']
}

preferred_rankings_women = {
	'Jane' :     ['Binley', 'Wickham', 'Darcy', 'Collins'],
	'Elizabeth': ['Wickham', 'Darcy', 'Binley', 'Collins'],
	'Lydia':     ['Binley', 'Wickham', 'Darcy', 'Collins'],
	'Charlotte': ['Binley', 'Darcy', 'Collins', 'Wickham']
}

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

if __name__ == '__main__':
	main()