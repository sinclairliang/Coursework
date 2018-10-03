"""
Tribute to https://www.youtube.com/watch?v=FhRf0j068ZA
and Pride and Prejudice by Jane Austen
"""

preferred_rankings_men = {
	'Binley':  ['Jane', 'Elizabeth', 'Lydia', 'Charlotte'],
	'Wickham': ['Lydia', 'Jane', 'Elizabeth', 'Charlotte'],
	'Darcy':   ['Elizabeth', 'Jane', 'Charlotte', "Lydia"],
	'Collins': ['Jane', 'Elizabeth', 'Lydia', 'Charlotte']
}

preferred_rankings_women = {
	'Jane' :     ['Binley', 'Wickham', 'Darcy', 'Collins'],
	'Lydia':     ['Binley', 'Wickham', 'Darcy', 'Collins'],
	'Elizabeth': ['Wickham', 'Darcy', 'Binley', 'Collins'],
	'Charlotte': ['Binley', 'Darcy', 'Collins', 'Wickham']
}

tentative_engagements = []

free_women = []

def init_free_women():
	for woman in preferred_rankings_women.keys():
		free_women.append(woman)


def stable_matching():
	while (len(free_women) > 0):
		for woman in free_women:
			begin_matching(woman)

def begin_matching(woman):
	print("Matching for this woman: {}".format(woman))
	for man in preferred_rankings_women[woman]:
		taken_match = [couple for couple in tentative_engagements if man in couple]

		if (len(taken_match) == 0):
			tentative_engagements.append([woman, man])

			free_women.remove(woman)
			print("{} is no longer a free woman and now tentatively engaged to {}".format(woman, man))
			break

		elif (len(taken_match) > 0):
			print("Person {} is taken already...".format(man))
			current_girl_ranking = preferred_rankings_men[man].index(taken_match[0][0])
			potential_girl_ranking = preferred_rankings_men[man].index(woman)

			if (current_girl_ranking < potential_girl_ranking):
				print("He ({}) is satisfied with {}...".format(taken_match[0][1],taken_match[0][0]))
			else:
				print("{} is better than the old girl {}".format(woman, taken_match[0][0]))
				print("{} is free agian".format(taken_match[0][0]))

				free_women.remove(woman)
				free_women.append(taken_match[0][0])

				taken_match[0][0] = woman
				break
	print("\n")	
 




def main():
	print("Hello World!")
	init_free_women()
	stable_matching()

	print("\t----COMPLETE----\t")
	print("\n")	
	for couple in tentative_engagements:
		print("{} is engaged to {}".format(couple[1], couple[0]))

if __name__ == '__main__':
	main()