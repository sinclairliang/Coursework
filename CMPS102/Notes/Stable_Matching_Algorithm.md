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

Here is the implementation in Python 3 code snippet:

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

......

```
[complete implementation in python](https://github.com/sinclairliang/Coursework/blob/master/CMPS102/Algorithms_in_Class/stabel_matching_algorithm.py)

Cool, now let's run it with some funny character names.

// memes

[![Mr.Darcygiff.jpg](https://i.postimg.cc/brkWtn50/Mr.Darcygiff.jpg)](https://postimg.cc/jnqMVDtC)



[![9015e1aacd3bd5d6e6d9622a52a3bcf7--pride-and-prejudice-characters.jpg](https://i.postimg.cc/J0NYnLyP/9015e1aacd3bd5d6e6d9622a52a3bcf7--pride-and-prejudice-characters.jpg)](https://postimg.cc/ctJB9PBn)

[![jane-austen-meme-26.jpg](https://i.postimg.cc/RVTwZF0j/jane-austen-meme-26.jpg)](https://postimg.cc/HVVrSTX2)

// end of memes

Here I have put their preferences into dictionaries

```python
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
```

[![Screenshot_from_2018-10-02_02-23-39.png](https://i.postimg.cc/ncQc3SyT/Screenshot_from_2018-10-02_02-23-39.png)](https://postimg.cc/ppRHTCXn)

So the book was right!

:construction::construction: 
Java implementation