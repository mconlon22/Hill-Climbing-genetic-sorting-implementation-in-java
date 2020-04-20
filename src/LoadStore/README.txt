/*~~ Team Random ~~
Martin Conlon - 16403502
Martynas Dra�d�iulis - 17444044

~~ Task 5 ~~
-Implemented a hillClimbing method which gradually improves the energy of our solution
-The main issue we had was the way to structure accepting worsening random changes we found that on average we improved 1/20 times and that the average 
improvenent was only .675 as opposed to our average worsening of energy of 2.6 meaning that to be improving at a steady rate we need to have a relatively low chance of 
accepting a worsening change because of this we ave our likleyhood of accepting a worsening change to start at around 1% and then as we get closer to our local minimum we decrease it to .001% and below
otherwise we would be worsening faster then we are improving, because of this we used the 1-boltzan dist probability and changing our temp between 50 and 150 changing the temperature based on the number of epochs we are doing