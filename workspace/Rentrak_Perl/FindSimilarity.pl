# FindSimilarity.pl

@arrA = (23, 13, 43, 56, 72, 1, 10);
@arrB = (1 , 10, 13, 45, 47, 66, 73, 43);

print "The elements in first array : @arrA\n";
print "The elements in seocnd array : @arrB\n";

@arrA = sortNum('asc', @arrA);
@arrB = sortNum('asc', @arrB);

@result = findSimilarity(\@arrA, \@arrB);

print "The elements in both array : @result\n";

# function to find the intersection elements from two arrays
sub findSimilarity{
	my $x = 0;
	my $y = 0;
	my @arrC;
	
	($arrA, $arrB) = @_;
	my $arrASize = @{$arrA};
	my $arrBSize = @{$arrB};
	
	while ($x<$arrASize && $y<$arrBSize) {
		if ($arrA->[$x] < $arrB->[$y]) {
			$x = $x + 1;
		} elsif ($arrA->[$x] > $arrB->[$y]) {
			$y = $y + 1
		} else {
			push (@arrC, $arrA->[$x]);
			$x = $x + 1;
			$y = $y + 1;
		}
	}
	return @arrC;
}

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
