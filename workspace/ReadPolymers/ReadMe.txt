In this exercise, I use perl to coding this type of program 
with text input and output. How do I solve the wild-card cases?
I replace each wild-card to the possible substitutions when 
reading each character, and then append the previous generated
string to a new one, then store back into the hash. Every time I 
search the whole hash for the key whose length is same as the length 
of current finished reading part of the input polymers. Then I read
the value of the keys, and append current character or possible 
substitutions to the key. After finished the iteration of current line
, therefore, I can get all possible keys' combinations and values by 
searching the hash's keys whose length is the same as the length of 
current input polymers. 

To run this perl file:

1. Please change current working directory to the one with ReadPolymers.pl
2. Put the dna.input and ReadPolymers.pl in the same folder
3. perl ReadPolymers.pl dna.input
4. The result is in the dna.output file.


Total Time to Finished : 3 hours and 20 mins