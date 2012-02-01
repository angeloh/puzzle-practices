# Sorting.pl

# given an int array, we can use sortNum() to sort.
@my_int_array = (23, 13, 43, 56, 72, 1, 10);
print "int array (before): @my_int_array\n";
@my_int_array = sortNum('desc', @my_int_array );
# @my_int_array = sortNum('asc', @my_int_array );
print "int array (after): @my_int_array\n";

#given a str array, we can use sortStr() to sort.
@my_str_array = ('orange', 'burgundy', 'mustard', 'white', 'green', 'red', 'pink');
print "str array (before): @my_str_array\n";
@my_str_array = sortStr('desc', @my_str_array );
# @my_str_array = sortStr('asc', @my_str_array );
print "str array (after): @my_str_array\n";

# function to sort an integer array
sub sortNum
{	
	my($comp, @not_sorted) = @_;		
	if (lc($comp) eq 'desc') {
		@sorted = reverse sort { $a <=> $b } @not_sorted;   # reverse numerical sort
	} else {
		@sorted = sort { $a <=> $b } @not_sorted;   # numerical sort
	}	
	return @sorted;
}

# function to sort an string array
sub sortStr
{	
	my($comp, @not_sorted) = @_;	
	if (lc($comp) eq 'desc') {
		@sorted = reverse sort {lc($a) cmp lc($b) } @not_sorted; # reverse alphabetical sort
	} else {
		@sorted = sort {lc($a) cmp lc($b) } @not_sorted; # alphabetical sort
	}	
	return @sorted;
}

