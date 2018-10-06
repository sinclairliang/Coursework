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
```

[![Screenshot_from_2018-10-02_22-01-44.png](https://i.postimg.cc/cCF5H0HR/Screenshot_from_2018-10-02_22-01-44.png)](https://postimg.cc/BP1C7W2b)


updates 02/10/2018: 

I made the women are able to choose first this time, more like in the movie. Here is the result, same.

[![Screenshot_from_2018-10-02_22-20-15.png](https://i.postimg.cc/J4mpwyp9/Screenshot_from_2018-10-02_22-20-15.png)](https://postimg.cc/mhXYCrfw)


So the book was right!



Proofs

```
Observations:
1. Men propose to women in decreasing order of preference.
2. Once a woman is matched, she never becomes unmatched; she only "trades up."
```

## Proof 0 (Proof of Correctness): ##
Claim: Algorithm terminates after at most n2iterations of while loop.
```
proof
```
Each time through the while loop a man proposes to a new woman. There are only n^2 possible proposals.



## Proof 1 (Proof of Correctness:  Perfection): ##

Claim: All men and women get matched

```
Proof by contradiction
```

1. Suppose there exists one man *m* is not matched upon the termination of algorithm;
2. Then some woman, *w* is also not matched upon the termination;
3. By Observation 2: Once a woman is matched, she never becomes unmatched; she only "trades up.", 
4.  
 	 ==>we can imply: *w* was never proposed to.

5. But *m* proposed to everyone because he ends up unmatched.
6. (1) and (5) contradicts.

## Proof 2 (Proof of Correctness:  Stability) :  ##

Claim: All pairs are stable
```
Proof by contradiction
```

```
S*
A-Y
B-Z
```


1. Suppose *A-Z* is an unstable pair: each prefers each other to their currently partner in G-S matching S* above shown;
2. We can then derive two cases in which this can happen:
3. Case 1: *Z* never proposed to *A*
   
   ==> *Z* prefers his G-S matching S* over A;

   ==> *A-Z* is then stable;

4. Case 2: *Z* has proposed to A

   ==> *A* rejected *Z* (Right away or later);

   ==> *A* prefers her current G-S matching S* over *Z*

   ==> *A-Z* is stable';

5. In either case, *A-Z* is _stable_.
6. QED


:construction::construction: 

## Proof 3 (Man Optimality) ##

Claim: All executions of GS  S* yield man-optimal assignment, which is a stable matching!

```
Proof by contradiction
```

```
a valid partner: the one with whom a stable pairing can be formed
```

1. Suppose some man is paired with someone that is not his best partner. Man proposes in decreasing order of preference 
   
    ==> some man is rejected by a valid partner.

2. Let *Y* be such man, such **first** man got rejected by his valid partner; *A* be the such first woman that has rejected *Y*.
   
   ==> *A* rejected *Y*

   Then in S* *A* pairs up with someone else, say *Z*

   ```
   A-Z (A likes Z more than Y)
   ```

3. Suppose S to be the stable matching in which *A* and *Y* are matched.

```
A-Y
```
4. When *Y* is rejected, *A* then forms a matching with another man, say *Z*, whom she prefers to *Y*.
5. Since we have previously proven:
   
```
"All men and women get matched" [Claim 2]
```
6. Also in *S*, let *B-Z* be a matching
    
    ==> Now we have such matching shown as *S*

```
Y-A
Z-B
```

7. Is *S* really stable?? Let's see. *Z* has not been rejected by any valid partner at the point when *Y* got rejected, since *Y* was the **first** one got rejected as above stated. 

    ==> *Z* has not been rejected yet, so in S* GS matching, *Z* prefers *A* to any other girl otherwise he would have been rejected.

    ==> However, we have assumed Z-B is a stable matching, which is now false;






~~Java implementation~~


